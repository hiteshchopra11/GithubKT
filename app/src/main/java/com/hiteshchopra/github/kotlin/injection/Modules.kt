package com.hiteshchopra.github.kotlin.injection

import com.hiteshchopra.github.kotlin.MainActivityVM
import com.hiteshchopra.github.kotlin.model.RepoItemOwnerUI
import com.hiteshchopra.github.kotlin.model.RepoItemUIMapper
import com.hiteshchopra.github.kotlin.model.RepoOwnerUIMapper
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
  single {
    RepoOwnerUIMapper()
  }

  single {
    RepoItemUIMapper(get())
  }

  viewModel { MainActivityVM(get(), get()) }
}