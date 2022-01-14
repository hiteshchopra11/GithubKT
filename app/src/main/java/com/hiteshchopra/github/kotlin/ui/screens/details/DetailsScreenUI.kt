package com.hiteshchopra.github.kotlin.ui.screens.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.hiteshchopra.github.kotlin.model.RepoItemUI

@Composable
fun GithubRepoDetails(repo: RepoItemUI, navHostController: NavHostController) {
  MaterialTheme {
    DetailsScreenContent(repo = repo)
  }
}

@Composable
private fun DetailsScreenContent(repo: RepoItemUI) {
  // Context and Intent required for launching intent
  val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
  val context = LocalContext.current
  Column(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxHeight()
      .verticalScroll(enabled = true, state = ScrollState(0))
  ) {
    RepoImage(repo)
    StyledText("Name: ${repo.name}")
    StyledText("Owner: ${repo.repoItemOwnerUI?.name}")
    StyledText("Language: ${repo.language}")
    StyledText("Archived: ${if (repo.archived == true) "Yes" else "No"}")
    StyledText("Number of Open Issues: ${repo.openIssuesCount}")
    StyledText("Description: ${repo.description}")
    StyledButton(text = "Open Repository", context = context, intent = intent)
  }
}

@Composable
private fun RepoImage(repo: RepoItemUI) {
  Image(
    painter = rememberCoilPainter(repo.repoItemOwnerUI?.avatarUrl),
    contentDescription = null,
    modifier = Modifier
      .height(250.dp)
      .fillMaxWidth()
      .clip(
        shape = RoundedCornerShape(
          CornerSize(16.dp),
          CornerSize(16.dp),
          CornerSize(16.dp),
          CornerSize(16.dp)
        )
      ),
    contentScale = ContentScale.Crop
  )
  Spacer(Modifier.height(16.dp))
}

@Composable
private fun StyledText(text: String) {
  Text(
    text,
    style = TextStyle(
      fontSize = 20.sp, fontFamily = FontFamily.SansSerif,
      fontWeight = FontWeight.Bold
    ),
    fontWeight = FontWeight.SemiBold,
  )
  Spacer(Modifier.height(16.dp))
}

@Composable
private fun StyledButton(text: String, context: Context, intent: Intent) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(
      onClick = {
        startActivity(context, intent, null)
      },
      colors = ButtonDefaults.textButtonColors(
        backgroundColor = Color.Red
      )
    ) {
      Text(text)
    }
  }
}
