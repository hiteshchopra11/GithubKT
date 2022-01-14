package com.hiteshchopra.github.kotlin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.google.gson.Gson
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import com.hiteshchopra.github.kotlin.ui.components.DisplayPaginatedError
import com.hiteshchopra.github.kotlin.ui.components.LoadingItem
import com.hiteshchopra.github.kotlin.ui.components.LoadingView
import kotlinx.coroutines.flow.Flow

@Composable
fun PetsList(navHostController: NavHostController, items: Flow<PagingData<RepoItemUI>>) {
//  fun navigateToPet(pet: Pets) {
//    val petJson = Gson().toJson(pet)
//    navHostController.navigate("petDetails/$petJson")
//  }

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
//            .clickable { navigateToPet(pet = pet) }
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
          loadState.refresh is LoadState.Loading -> {
            item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
          }
          loadState.append is LoadState.Loading -> {
            item { LoadingItem() }
          }
          loadState.refresh is LoadState.Error -> {
            val e = lazyPhotoItems.loadState.refresh as LoadState.Error
            item {
              DisplayPaginatedError(
                message = e.error.message ?: "Some Unknown Error Occurred",
                modifier = Modifier.fillParentMaxSize(),
                onClickRetry = { retry() }
              )
            }
          }
          loadState.append is LoadState.Error -> {
            val e = lazyPhotoItems.loadState.append as LoadState.Error
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
