package com.hiteshchopra.github.kotlin.ui.screens.util

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.hiteshchopra.github.kotlin.model.RepoItemUI

class ParamTypeRepoItem : NavType<RepoItemUI>(isNullableAllowed = false) {
  override fun get(bundle: Bundle, key: String): RepoItemUI? {
    return bundle.getParcelable(key)
  }

  override fun parseValue(value: String): RepoItemUI {
    return Gson().fromJson(value, RepoItemUI::class.java)
  }

  override fun put(bundle: Bundle, key: String, value: RepoItemUI) {
    bundle.putParcelable(key, value)
  }
}