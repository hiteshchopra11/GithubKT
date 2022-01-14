package com.hiteshchopra.github.data.repo

import com.hiteshchopra.github.data.remote.model.RepoItemData
import com.hiteshchopra.github.data.remote.model.RepoItemMapper
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.SafeResult
import com.hiteshchopra.github.domain.model.RepoItemDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import java.lang.RuntimeException

class FetchRepositoriesRepo(
  private val githubRemoteSource: IGithubRemoteSource,
  private val repoItemMapper: RepoItemMapper
) : IFetchRepositoriesRepo {
  override suspend fun fetchRepositories(): SafeResult<List<RepoItemDomain>> {
    return when (val result = githubRemoteSource.getPosts()) {
      is SafeResult.Success<*> -> {
        val success = (result.data as ArrayList<*>).map { repoItemData ->
          repoItemMapper.mapToDomain(repoItemData as RepoItemData)
        }
        return SafeResult.Success(success)
      }
      is SafeResult.Failure -> SafeResult.Failure(result.exception)
      SafeResult.NetworkError -> SafeResult.NetworkError
      else -> {
        throw RuntimeException()
      }
    }
  }
}