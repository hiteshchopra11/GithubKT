package com.hiteshchopra.github.data.sources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hiteshchopra.github.data.remote.model.RepoItemDomainMapper
import com.hiteshchopra.github.domain.SafeResult.NetworkError
import com.hiteshchopra.github.domain.SafeResult.Success
import com.hiteshchopra.github.domain.getErrorOrNull
import com.hiteshchopra.github.domain.getSuccessOrNull
import com.hiteshchopra.github.domain.model.RepoItemDomain
import java.io.IOException

class GithubPagerSource(
  private val githubRemoteSource: IGithubRemoteSource,
  private val repoItemDomainMapper: RepoItemDomainMapper
) : PagingSource<Int, RepoItemDomain>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItemDomain> {
    return try {
      // Start refresh at page 1 if undefined.
      val page = params.key ?: 1
      when (val result =
        githubRemoteSource.getPosts(
          IGithubRemoteSource.PAGE_SIZE_DEFAULT,
          page
        )
      ) {
        is Success -> {
          Log.e("RESULTIS", result.getSuccessOrNull()?.map { repoItemDomainMapper.mapToDomain(it) }.toString())
          val paginatedContent = result.getSuccessOrNull() ?: return LoadResult.Error(Exception())
          LoadResult.Page(
            data = paginatedContent.map { repoItemDomainMapper.mapToDomain(it) },
            prevKey = if (page == 1) null else page - 1,
            nextKey = page + 1
          )
        }
        is NetworkError -> {
          LoadResult.Error(IOException())
        }
        else -> {
          LoadResult.Error(result.getErrorOrNull()?.exception ?: Exception())
        }
      }
    } catch (exception: Exception) {
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, RepoItemDomain>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}
