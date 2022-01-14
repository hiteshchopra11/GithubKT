package com.hiteshchopra.github.domain.injection

import com.hiteshchopra.github.domain.usecase.FetchGithubRepositoriesUseCase
import org.koin.dsl.module

val domainModule = module{
  single { FetchGithubRepositoriesUseCase(get()) }
}