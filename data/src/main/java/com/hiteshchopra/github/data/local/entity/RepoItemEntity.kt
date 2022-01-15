package com.hiteshchopra.github.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hiteshchopra.github.data.remote.model.repo.RepoOwnerData

@Entity(tableName = "repos")
data class RepoItemEntity(
  @PrimaryKey(autoGenerate = false) var id: Int,
  @ColumnInfo(name = "name") var name: String,
  @TypeConverters(RepoOwnerData::class)
  @ColumnInfo(name = "repo_owner") var owner: RepoOwnerData,
  @ColumnInfo(name = "language") var language: String,
  @ColumnInfo(name = "archived") var archived: Boolean,
  @ColumnInfo(name = "open_issues_count") var openIssuesCount: Int,
  @ColumnInfo(name = "description") var description: String,
  @ColumnInfo(name = "html_url") var htmlUrl: String,
)