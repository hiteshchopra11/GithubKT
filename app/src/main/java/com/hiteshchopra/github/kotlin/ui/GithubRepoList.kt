package com.hiteshchopra.github.kotlin.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.hiteshchopra.github.kotlin.ui.theme.drawerBackground
import kotlinx.coroutines.flow.Flow

@Composable
fun PaginatedImages(items: Flow<PagingData<RepoItemUI>>) {
  val lazyPhotoItems = items.collectAsLazyPagingItems()
  LazyColumn(
    modifier = Modifier
      .padding(12.dp, 4.dp)
      .height(180.dp)
  ) {
    items(items = lazyPhotoItems) { item ->
      Log.e("ITEM IS", "$item")
      Card(
        modifier = Modifier
          .height(180.dp)
          .background(drawerBackground)
          .padding(4.dp)
          .width(120.dp),
        shape = RoundedCornerShape(5.dp)
      ) {
        Image(
          painter = rememberCoilPainter(item?.repoItemOwnerUI?.avatarUrl),
          contentDescription = null,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }
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
              message = e.error.localizedMessage ?: "Some Unknown Error Occurred",
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
