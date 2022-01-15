package com.hiteshchopra.github.data.injection

import com.hiteshchopra.github.data.remote.model.repo.RepoItemDomainMapper
import com.hiteshchopra.github.data.remote.model.repo.RepoItemPermissionDomainMapper
import com.hiteshchopra.github.data.remote.model.repo.RepoOwnerDomainMapper
import com.hiteshchopra.github.data.remote.model.search.SearchLicenseEntityMapper
import com.hiteshchopra.github.data.remote.model.search.SearchOwnerEntityMapper
import com.hiteshchopra.github.data.remote.model.search.SearchResultEntityMapper
import com.hiteshchopra.github.data.remote.model.search.SearchResultItemEntityMapper
import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.data.remote.service.GithubRepoServiceImpl
import com.hiteshchopra.github.data.repo.GithubRepo
import com.hiteshchopra.github.data.sources.GithubRemoteSource
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.domain.repo.IGithubRepo
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
  single {
    SearchLicenseEntityMapper()
  }
  single {
    SearchResultItemEntityMapper(get(), get())
  }
  single {
    SearchOwnerEntityMapper()
  }
  single {
    SearchResultEntityMapper(get())
  }

  single<GithubRepoService> { GithubRepoServiceImpl(HttpClient()) }
  single<IGithubRemoteSource> { GithubRemoteSource(GithubRepoService.create()) }
  single<IGithubRepo> { GithubRepo(get(), get(), get()) }
}