package com.hiteshchopra.github.data.injection

import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.data.remote.model.GithubRepoItemData
import com.hiteshchopra.github.data.remote.model.GithubRepoItemMapper
import com.hiteshchopra.github.data.remote.model.RepoOwnerData
import com.hiteshchopra.github.data.remote.model.RepoOwnerMapper
import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.data.remote.service.GithubRepoServiceImpl
import com.hiteshchopra.github.data.repo.FetchRepositoriesRepo
import com.hiteshchopra.github.data.sources.GithubRemoteSource
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.model.GithubRepoItemDomain
import com.hiteshchopra.github.domain.model.RepoOwnerDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {

  single {
    RepoOwnerMapper()
  }
  single {
    GithubRepoItemMapper(get())
  }
  single<GithubRepoService> { GithubRepoServiceImpl(HttpClient()) }
  single<IGithubRemoteSource> { GithubRemoteSource(GithubRepoService.create()) }
  single<IFetchRepositoriesRepo> { FetchRepositoriesRepo(get(), get()) }
}