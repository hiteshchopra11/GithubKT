package com.hiteshchopra.github.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hiteshchopra.github.data.local.database.RepoDatabase
import com.hiteshchopra.github.data.local.entity.RemoteKeysEntity
import com.hiteshchopra.github.data.local.entity.RepoItemEntity
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.getSuccessOrNull
import java.io.IOException

/**
 * TODO : WORK IN PROGRESS NOT COMPLETE YET
 */

private const val GITHUB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(
  private val query: String,
  private val service: IGithubRemoteSource,
  private val repoDatabase: RepoDatabase
) : RemoteMediator<Int, RepoItemEntity>() {

  override suspend fun initialize(): InitializeAction {
    return InitializeAction.LAUNCH_INITIAL_REFRESH
  }

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, RepoItemEntity>
  ): MediatorResult {

    val page = when (loadType) {
      LoadType.REFRESH -> {
        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
        remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
      }
      LoadType.PREPEND -> {
        val remoteKeys = getRemoteKeyForFirstItem(state)
        // If remoteKeys is null, that means the refresh result is not in the database yet.
        // We can return Success with `endOfPaginationReached = false` because Paging
        // will call this method again if RemoteKeys becomes non-null.
        // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
        // the end of pagination for prepend.
        val prevKey = remoteKeys?.prevKey
          ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
        prevKey
      }
      LoadType.APPEND -> {
        val remoteKeys = getRemoteKeyForLastItem(state)
        // If remoteKeys is null, that means the refresh result is not in the database yet.
        // We can return Success with `endOfPaginationReached = false` because Paging
        // will call this method again if RemoteKeys becomes non-null.
        // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
        // the end of pagination for append.
        val nextKey = remoteKeys?.nextKey
          ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
        nextKey
      }
    }

    try {
      val apiResponse = service.getRepos(state.config.pageSize, page)

      val repos = apiResponse.getSuccessOrNull()
      val endOfPaginationReached = repos?.isEmpty()
      repoDatabase.withTransaction {
        // clear all tables in the database
        if (loadType == LoadType.REFRESH) {
          repoDatabase.remoteKeysDao().clearRemoteKeys()
          repoDatabase.reposDao().clearRepos()
        }
        val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
        val nextKey = if (endOfPaginationReached == true) null else page + 1
        val keys = repos?.map {
          RemoteKeysEntity(repoId = it.id.toLong(), prevKey = prevKey, nextKey = nextKey)
        }
        if (keys != null) {
          repoDatabase.remoteKeysDao().insertAll(keys)
        }
        if (repos != null) {
          repoDatabase.reposDao().insertAll(repos)
        }
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
    } catch (exception: IOException) {
      return MediatorResult.Error(exception)
    }
  }

  private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RepoItemEntity>): RemoteKeysEntity? {
    // Get the last page that was retrieved, that contained items.
    // From that last page, get the last item
    return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
      ?.let { repo ->
        // Get the remote keys of the last item retrieved
        repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id.toLong())
      }
  }

  private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RepoItemEntity>): RemoteKeysEntity? {
    // Get the first page that was retrieved, that contained items.
    // From that first page, get the first item
    return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
      ?.let { repo ->
        // Get the remote keys of the first items retrieved
        repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id.toLong())
      }
  }

  private suspend fun getRemoteKeyClosestToCurrentPosition(
    state: PagingState<Int, RepoItemEntity>
  ): RemoteKeysEntity? {
    // The paging library is trying to load data after the anchor position
    // Get the item closest to the anchor position
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.id?.let { repoId ->
        repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId.toLong())
      }
    }
  }
}