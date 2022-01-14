package com.hiteshchopra.github.kotlin.injection

import com.hiteshchopra.github.kotlin.model.RepoItemUIMapper
import com.hiteshchopra.github.kotlin.model.RepoOwnerUIMapper
import com.hiteshchopra.github.kotlin.ui.screens.home.HomeScreenVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
  single {
    RepoOwnerUIMapper()
  }

  single {
    RepoItemUIMapper(get())
  }

  viewModel { HomeScreenVM(get(), get()) }
}