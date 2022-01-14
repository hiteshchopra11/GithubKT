package com.hiteshchopra.github.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.hiteshchopra.github.domain.SafeResult.Failure
import com.hiteshchopra.github.domain.SafeResult.NetworkError
import com.hiteshchopra.github.domain.SafeResult.Success
import com.hiteshchopra.github.domain.model.RepoItemDomain
import com.hiteshchopra.github.domain.usecase.FetchGithubRepositoriesUseCase
import com.hiteshchopra.github.kotlin.ui.PaginatedImages
import com.hiteshchopra.github.kotlin.ui.PetsList
import com.hiteshchopra.github.kotlin.ui.theme.GithubKotlinTheme
import com.hiteshchopra.github.kotlin.ui.theme.drawerBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.get
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      GithubKotlinTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          HomeScreenContent()
        }
      }
    }
  }
}

@Composable
fun HomeScreenContent() {
  // ViewModel for Detail View
  // Lazy inject ViewModel with id parameter
  val navController = rememberNavController()
  val mainActivityVM by viewModel<MainActivityVM>()
  PetsList(navHostController = navController, items = mainActivityVM.getPaginatedImages())
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GithubKotlinTheme {
    Greeting("Android")
  }
}