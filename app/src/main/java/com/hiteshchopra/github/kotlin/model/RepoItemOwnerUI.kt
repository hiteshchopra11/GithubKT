package com.hiteshchopra.github.kotlin.model

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.UIModel
import com.hiteshchopra.github.domain.mapper.UiModelMapper
import com.hiteshchopra.github.domain.model.RepoItemOwnerDomain

class RepoItemOwnerUI(
  @SerializedName("avatar_url")
  val avatarUrl: String?,
  @SerializedName("login")
  val name: String?,
) : UIModel()

class RepoOwnerUIMapper : UiModelMapper<RepoItemOwnerDomain, RepoItemOwnerUI> {
  override fun mapToPresentation(model: RepoItemOwnerDomain): RepoItemOwnerUI {
    return RepoItemOwnerUI(
      avatarUrl = model.avatarUrl,
      name = model.login,
    )
  }
}