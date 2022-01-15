package com.hiteshchopra.github.kotlin.ui.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.hiteshchopra.github.kotlin.R.string
import com.hiteshchopra.github.kotlin.ui.components.PetsList
import com.hiteshchopra.github.kotlin.ui.components.SearchBarUI
import org.koin.androidx.compose.viewModel

private const val TAG = "RepoSearchUI"

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun RepoSearchUI(navHostController: NavHostController) {
  val repoSearchVM by viewModel<RepoSearchVM>()
  var query by rememberSaveable { mutableStateOf("") }
  SearchBarUI(
    searchText = query,
    placeholderText = stringResource(string.search_repos),
    onSearchTextChanged = {
      query = it
      repoSearchVM.onSearchTextChanged(it)
    },
    onClearClick = { query = "" },
    onNavigateBack = {
      navHostController.popBackStack()
    },
    matchesFound = repoSearchVM.paginatedResponse != null
  ) {
    if (repoSearchVM.paginatedResponse != null) {
      PetsList(navHostController = navHostController, items = repoSearchVM.paginatedResponse!!)
    }
  }
}
