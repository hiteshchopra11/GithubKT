package com.hiteshchopra.github.kotlin.ui.screens.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.google.gson.Gson
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import com.hiteshchopra.github.kotlin.ui.components.DisplayPaginatedError
import com.hiteshchopra.github.kotlin.ui.components.LoadingItem
import com.hiteshchopra.github.kotlin.ui.components.LoadingView
import com.hiteshchopra.github.kotlin.ui.screens.Screen
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.viewModel

@Composable
fun HomeScreen(navHostController: NavHostController) {
  Column(modifier = Modifier.fillMaxHeight()) {
    TopAppBar(
      title = { Text(text = "KotlinKT") },
      backgroundColor = Color(0xFF6200EE)
    )
    HomeScreenContent(navHostController)
  }
}

@Composable
fun HomeScreenContent(navHostController: NavHostController) {
  val homeScreenVM by viewModel<HomeScreenVM>()
  PetsList(navHostController = navHostController, items = homeScreenVM.paginatedRepos)
}

@Composable
fun PetsList(navHostController: NavHostController, items: Flow<PagingData<RepoItemUI>>) {
  fun navigateToDetailsScreen(repoItemUI: RepoItemUI) {
    val itemUIJson = Uri.encode(Gson().toJson(repoItemUI))
    navHostController.navigate(Screen.Details.passRepoItem(itemUIJson))
  }

  val lazyPhotoItems = items.collectAsLazyPagingItems()

  MaterialTheme {
    val typography = MaterialTheme.typography

    LazyColumn(
      modifier = Modifier
        .absolutePadding(left = 8.dp, right = 8.dp, bottom = 8.dp)
    ) {
      items(lazyPhotoItems) { repo ->
        Row(
          modifier = Modifier
            .padding(4.dp)
            .clickable { repo?.let { navigateToDetailsScreen(repoItemUI = it) } }
        ) {
          Image(
            painter = rememberCoilPainter(repo?.repoItemOwnerUI?.avatarUrl),
            contentDescription = null,
            modifier = Modifier
              .padding(8.dp)
              .height(100.dp)
              .width(100.dp)
              .fillMaxWidth(),
            contentScale = ContentScale.Crop,
          )
          Spacer(Modifier.width(4.dp))
          Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
              "Name: ${repo?.name}",
              style = typography.h6,
              overflow = TextOverflow.Ellipsis
            )
            Text(
              "Language: ${repo?.language}",
              style = typography.body1
            )
            Text(
              "Owner: ${repo?.repoItemOwnerUI?.name}",
              style = typography.body2
            )
          }
        }
        Divider(color = Color.Black)
      }
      lazyPhotoItems.apply {
        when {
          loadState.refresh is Loading -> {
            item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
          }
          loadState.append is Loading -> {
            item { LoadingItem() }
          }
          loadState.refresh is Error -> {
            val e = lazyPhotoItems.loadState.refresh as Error
            item {
              DisplayPaginatedError(
                message = e.error.message ?: "Some Unknown Error Occurred",
                modifier = Modifier.fillParentMaxSize(),
                onClickRetry = { retry() }
              )
            }
          }
          loadState.append is Error -> {
            val e = lazyPhotoItems.loadState.append as Error
            item {
              DisplayPaginatedError(
                message = e.error.localizedMessage ?: "Some Unknown Error Occurred",
                onClickRetry = { retry() }
              )
            }
          }
        }
      }
    }
  }
}
