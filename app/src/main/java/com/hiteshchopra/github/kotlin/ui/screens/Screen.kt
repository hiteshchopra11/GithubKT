package com.hiteshchopra.github.kotlin.ui.screens

const val DEFAULT_ARGUMENT_KEY = "repoItem"

sealed class Screen(val route: String) {
  object Home : Screen(route = "home_screen")
  object Details : Screen(route = "details_screen") {
    fun passRepoItem(repoItem: String): String {
      return "details_screen/$repoItem"
    }
  }
}