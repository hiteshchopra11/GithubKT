package com.hiteshchopra.github.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hiteshchopra.github.data.local.dao.RemoteKeysDao
import com.hiteshchopra.github.data.local.dao.RepoDao
import com.hiteshchopra.github.data.local.entity.RemoteKeysEntity
import com.hiteshchopra.github.data.local.entity.RepoItemEntity

@Database(
  entities = [RepoItemEntity::class, RemoteKeysEntity::class],
  version = 1,
  exportSchema = false
)
abstract class RepoDatabase : RoomDatabase() {

  abstract fun reposDao(): RepoDao
  abstract fun remoteKeysDao(): RemoteKeysDao

  companion object {

    @Volatile
    private var INSTANCE: RepoDatabase? = null

    fun getInstance(context: Context): RepoDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE
          ?: buildDatabase(context).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(
        context.applicationContext,
        RepoDatabase::class.java, "Github.db"
      ).build()
  }
}