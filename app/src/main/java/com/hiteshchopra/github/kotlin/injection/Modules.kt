package com.hiteshchopra.github.kotlin.injection

import com.hiteshchopra.github.kotlin.model.Mappers.RepoItemUIMapper
import com.hiteshchopra.github.kotlin.model.Mappers.RepoOwnerUIMapper
import com.hiteshchopra.github.kotlin.model.Mappers.SearchItemUIMapper
import com.hiteshchopra.github.kotlin.model.Mappers.SearchOwnerUIMapper
import com.hiteshchopra.github.kotlin.ui.screens.home.HomeScreenVM
import com.hiteshchopra.github.kotlin.ui.screens.search.RepoSearchVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
  single {
    SearchItemUIMapper(get())
  }

  single {
    SearchOwnerUIMapper()
  }
  single {
    RepoOwnerUIMapper()
  }

  single {
    RepoItemUIMapper(get())
  }

  viewModel { HomeScreenVM(get(), get()) }
  viewModel { RepoSearchVM(get(), get()) }
}