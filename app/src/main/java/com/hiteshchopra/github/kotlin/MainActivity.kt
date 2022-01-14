package com.hiteshchopra.github.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hiteshchopra.github.kotlin.ui.theme.GithubKotlinTheme

class MainActivity : ComponentActivity() {
  lateinit var navController: NavHostController
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      GithubKotlinTheme {
        navController = rememberNavController()
        SetupNavGraph(navController = navController)
      }
    }
  }
}