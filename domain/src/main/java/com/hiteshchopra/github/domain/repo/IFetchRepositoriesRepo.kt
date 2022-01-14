package com.hiteshchopra.github.domain.repo

import androidx.paging.PagingData
import com.hiteshchopra.github.domain.model.RepoItemDomain
import kotlinx.coroutines.flow.Flow

interface IFetchRepositoriesRepo {
  fun fetchRepositories(): Flow<PagingData<RepoItemDomain>>
}