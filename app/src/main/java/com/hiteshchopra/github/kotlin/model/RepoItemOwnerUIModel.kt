package com.hiteshchopra.github.kotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.UIModel
import kotlinx.parcelize.Parcelize

@Parcelize
class RepoItemOwnerUI(
  @SerializedName("avatar_url")
  val avatarUrl: String?,
  @SerializedName("login")
  val name: String?,
) : UIModel(), Parcelable