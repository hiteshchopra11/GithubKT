package com.hiteshchopra.github.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hiteshchopra.github.data.remote.model.RepoItemDomainMapper
import com.hiteshchopra.github.data.sources.GithubPagerSource
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.model.RepoItemDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import kotlinx.coroutines.flow.Flow

class FetchRepositoriesRepo(
  private val githubRemoteSource: IGithubRemoteSource,
  private val repoItemDomainMapper: RepoItemDomainMapper
) : IFetchRepositoriesRepo {
//  override suspend fun fetchRepositories(): SafeResult<List<RepoItemDomain>> {
//    return when (val result = githubRemoteSource.getPosts()) {
//      is SafeResult.Success<*> -> {
//        val success = (result.data as ArrayList<*>).map { repoItemData ->
//          repoItemMapper.mapToDomain(repoItemData as RepoItemData)
//        }
//        return SafeResult.Success(success)
//      }
//      is SafeResult.Failure -> SafeResult.Failure(result.exception)
//      SafeResult.NetworkError -> SafeResult.NetworkError
//      else -> {
//        throw RuntimeException()
//      }
//    }
//  }

  override fun fetchRepositories(): Flow<PagingData<RepoItemDomain>> {
    return Pager(
      PagingConfig(pageSize = 10)
    ) {
      GithubPagerSource(githubRemoteSource, repoItemDomainMapper)
    }.flow
  }
}