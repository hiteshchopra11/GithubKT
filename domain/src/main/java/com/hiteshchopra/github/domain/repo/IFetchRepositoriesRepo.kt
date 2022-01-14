package com.hiteshchopra.github.domain.repo

import com.hiteshchopra.github.domain.SafeResult
import com.hiteshchopra.github.domain.model.RepoItemDomain

interface IFetchRepositoriesRepo {
  suspend fun fetchRepositories(): SafeResult<List<RepoItemDomain>>
}