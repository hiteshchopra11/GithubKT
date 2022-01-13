package com.hiteshchopra.github.domain.usecase

import com.hiteshchopra.github.domain.SafeResult
import com.hiteshchopra.github.domain.model.GithubRepoItemDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo

class FetchGithubRepositoriesUseCase(private val fetchRepositoriesRepo: IFetchRepositoriesRepo) :
  BaseUseCase<SafeResult<List<GithubRepoItemDomain>>, Unit> {

  override suspend fun performAsync(): SafeResult<List<GithubRepoItemDomain>> {
    return when (val result = fetchRepositoriesRepo.fetchRepositories()) {
      is SafeResult.Success -> SafeResult.Success(result.data)
      is SafeResult.NetworkError -> result
      is SafeResult.Failure -> result
    }
  }

}