package com.hiteshchopra.github.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hiteshchopra.github.data.remote.model.repo.RepoItemData

interface RepoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(repos: List<RepoItemData>)

  @Query("SELECT * FROM repos")
  fun reposByName(queryString: String): PagingSource<Int, RepoItemData>

  @Query("DELETE FROM repos")
  suspend fun clearRepos()
}