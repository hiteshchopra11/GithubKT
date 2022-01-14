package com.hiteshchopra.github.kotlin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import com.hiteshchopra.github.kotlin.ui.screens.DEFAULT_ARGUMENT_KEY
import com.hiteshchopra.github.kotlin.ui.screens.Screen
import com.hiteshchopra.github.kotlin.ui.screens.details.GithubRepoDetails
import com.hiteshchopra.github.kotlin.ui.screens.home.HomeScreen
import com.hiteshchopra.github.kotlin.ui.screens.util.ParamTypeRepoItem

@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Screen.Home.route) {
    composable(
      route = Screen.Home.route
    ) {
      HomeScreen(navController)
    }
    composable(
      route = Screen.Details.route + "/{${DEFAULT_ARGUMENT_KEY}}",
      arguments = listOf(
        navArgument(DEFAULT_ARGUMENT_KEY) {
          type = ParamTypeRepoItem()
        }
      )
    ) { navBackStackEntry ->
      val githubRepoItemUI =
        navBackStackEntry.arguments?.getParcelable<RepoItemUI>(DEFAULT_ARGUMENT_KEY)
      githubRepoItemUI?.let { repoItemUI ->
        GithubRepoDetails(
          repo = repoItemUI,
          navHostController = navController
        )
      }
    }
  }
}