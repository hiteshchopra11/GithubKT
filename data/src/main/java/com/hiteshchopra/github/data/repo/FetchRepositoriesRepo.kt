package com.hiteshchopra.github.data.repo

import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.data.remote.model.GithubRepoItemData
import com.hiteshchopra.github.data.remote.model.GithubRepoItemMapper
import com.hiteshchopra.github.data.sources.GithubRemoteSource
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.SafeResult
import com.hiteshchopra.github.domain.model.GithubRepoItemDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import java.lang.RuntimeException

class FetchRepositoriesRepo(
  private val githubRemoteSource: IGithubRemoteSource,
  private val githubRepoItemMapper: GithubRepoItemMapper
) : IFetchRepositoriesRepo {
  override suspend fun fetchRepositories(): SafeResult<List<GithubRepoItemDomain>> {
    return when (val result = githubRemoteSource.getPosts()) {
      is SafeResult.Success<*> -> {
        val success = (result.data as ArrayList<*>).map { githubRepoItemData ->
          githubRepoItemMapper.mapToDomain(githubRepoItemData as GithubRepoItemData)
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