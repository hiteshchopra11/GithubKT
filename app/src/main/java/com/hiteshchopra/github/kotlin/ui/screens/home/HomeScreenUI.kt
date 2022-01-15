package com.hiteshchopra.github.kotlin.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.hiteshchopra.github.kotlin.R
import com.hiteshchopra.github.kotlin.ui.components.PetsList
import com.hiteshchopra.github.kotlin.ui.screens.Screen
import org.koin.androidx.compose.viewModel

@Composable
fun HomeScreen(navHostController: NavHostController) {
  Column(modifier = Modifier.fillMaxHeight()) {
    SearchViewTopAppBar(
      onSearchBarClick = {
        navHostController.navigate(route = Screen.Search.route)
      })
    HomeScreenContent(navHostController)
  }
}

@Composable
fun HomeScreenContent(navHostController: NavHostController) {
  val homeScreenVM by viewModel<HomeScreenVM>()
  PetsList(navHostController = navHostController, items = homeScreenVM.paginatedRepos)
}

@Composable
fun SearchViewTopAppBar(onSearchBarClick: () -> Unit) {
  TopAppBar(title = { Text("GithubKT") }, actions = {
    IconButton(
      modifier = Modifier,
      onClick = { onSearchBarClick() }) {
      Icon(
        Filled.Search,
        contentDescription = stringResource(id = R.string.search)
      )
    }
  })
}