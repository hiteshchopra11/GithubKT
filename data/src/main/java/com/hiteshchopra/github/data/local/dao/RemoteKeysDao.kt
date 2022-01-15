package com.hiteshchopra.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hiteshchopra.github.data.local.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(remoteKeyEntity: List<RemoteKeysEntity>)

  @Query("SELECT * FROM remote_keys WHERE repoId = :repoId")
  suspend fun remoteKeysRepoId(repoId: Long): RemoteKeysEntity?

  @Query("DELETE FROM remote_keys")
  suspend fun clearRemoteKeys()
}