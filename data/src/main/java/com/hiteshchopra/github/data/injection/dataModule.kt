package com.hiteshchopra.github.data.injection

import com.hiteshchopra.github.data.remote.model.RepoItemDomainMapper
import com.hiteshchopra.github.data.remote.model.RepoItemPermissionDomainMapper
import com.hiteshchopra.github.data.remote.model.RepoOwnerDomainMapper
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
    RepoItemPermissionDomainMapper()
  }
  single {
    RepoOwnerDomainMapper()
  }
  single {
    RepoItemDomainMapper(get(), get())
  }
  single<GithubRepoService> { GithubRepoServiceImpl(HttpClient()) }
  single<IGithubRemoteSource> { GithubRemoteSource(GithubRepoService.create()) }
  single<IFetchRepositoriesRepo> { FetchRepositoriesRepo(get(), get()) }
}