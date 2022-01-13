package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.model.GithubRepoItemData
import com.hiteshchopra.github.domain.SafeResult

interface IGithubRemoteSource {
  suspend fun getPosts(): SafeResult<ArrayList<GithubRepoItemData>>
}