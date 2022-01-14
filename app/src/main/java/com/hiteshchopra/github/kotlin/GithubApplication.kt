package com.hiteshchopra.github.kotlin

import android.app.Application
import com.hiteshchopra.github.data.injection.dataModule
import com.hiteshchopra.github.domain.injection.domainModule
import com.hiteshchopra.github.kotlin.injection.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GithubApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      /* Workaround for issue #1188 mentioned here
       * https://github.com/InsertKoinIO/koin/issues/1188#issuecomment-970240532
       **/
      androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
      androidContext(this@GithubApplication)
      modules(appModules, dataModule, domainModule)
    }
  }
}