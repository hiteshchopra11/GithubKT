package com.hiteshchopra.github.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table that stores the next and
 * previous page keys for each Repo
 */

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
  @PrimaryKey
  val repoId: Long,
  val prevKey: Int?,
  val nextKey: Int?
)