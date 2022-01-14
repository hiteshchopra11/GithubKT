package com.hiteshchopra.github.data.injection

import com.hiteshchopra.github.data.remote.model.RepoItemMapper
import com.hiteshchopra.github.data.remote.model.RepoItemPermissionMapper
import com.hiteshchopra.github.data.remote.model.RepoOwnerMapper
import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.data.remote.service.GithubRepoServiceImpl
import com.hiteshchopra.github.data.repo.FetchRepositoriesRepo
import com.hiteshchopra.github.data.sources.GithubRemoteSource
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import io.ktor.client.HttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module

@ExperimentalSerializationApi val dataModule = module {

  single {
    RepoItemPermissionMapper()
  }
  single {
    RepoOwnerMapper()
  }
  single {
    RepoItemMapper(get(), get())
  }
  single<GithubRepoService> { GithubRepoServiceImpl(HttpClient()) }
  single<IGithubRemoteSource> { GithubRemoteSource(GithubRepoService.create()) }
  single<IFetchRepositoriesRepo> { FetchRepositoriesRepo(get(), get()) }
}