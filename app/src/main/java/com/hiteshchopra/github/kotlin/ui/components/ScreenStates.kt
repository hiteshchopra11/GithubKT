package com.hiteshchopra.github.kotlin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hiteshchopra.github.kotlin.R

@Composable
fun LoadingView(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CircularProgressIndicator(color = Color.Black)
  }
}

@Composable
fun LoadingItem() {
  CircularProgressIndicator(
    color = Color.Black,
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth()
      .padding(16.dp)
      .wrapContentSize(Alignment.Center)
  )
}

@Composable
fun DisplayPaginatedError(
  message: String,
  modifier: Modifier = Modifier,
  onClickRetry: () -> Unit
) {
  Column(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = message,
      color = Color.Black
    )
    Spacer(modifier = Modifier.padding(0.dp, 4.dp))
    IconButton(onClick = onClickRetry) {
      Icon(
        painter = painterResource(id = R.drawable.ic_retry),
        contentDescription = "",
        tint = Color.Black
      )
    }
  }
}