package com.hiteshchopra.github.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hiteshchopra.github.domain.SafeResult.Failure
import com.hiteshchopra.github.domain.SafeResult.NetworkError
import com.hiteshchopra.github.domain.SafeResult.Success
import com.hiteshchopra.github.domain.usecase.FetchGithubRepositoriesUseCase
import com.hiteshchopra.github.kotlin.ui.theme.GithubKotlinTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
  // inject BusinessService into property
  val useCase: FetchGithubRepositoriesUseCase by inject()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      GithubKotlinTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting("Android")
          CoroutineScope(IO).launch {
            when(val response = useCase.performAsync()){
              is Failure -> {
                Log.e("RESULTIS","FAIL")
              }
              NetworkError -> {
                Log.e("RESULTIS","NETWORKERROR")
              }
              is Success -> {
                Log.e("RESULTIS",response.toString())
              }
            }
          }
        }
      }
    }
  }
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