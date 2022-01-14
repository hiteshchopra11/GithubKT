package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.model.RepoItemData
import com.hiteshchopra.github.domain.SafeResult

interface IGithubRemoteSource {
  companion object {
    const val PAGE_SIZE_DEFAULT = 10
  }

  suspend fun getPosts(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>>
}