package com.hiteshchopra.github.kotlin.model

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.UIModel
import com.hiteshchopra.github.domain.mapper.UiModelMapper
import com.hiteshchopra.github.domain.model.RepoItemDomain

data class RepoItemUI(
  @SerializedName("name")
  val name: String?,
  @SerializedName("owner")
  val repoItemOwnerUI: RepoItemOwnerUI?,
  @SerializedName("language")
  val language: String?,
) : UIModel()

class RepoItemUIMapper(private val repoOwnerUIMapper: RepoOwnerUIMapper) :
  UiModelMapper<RepoItemDomain, RepoItemUI> {
  override fun mapToPresentation(model: RepoItemDomain): RepoItemUI {
    return RepoItemUI(
      name = model.name,
      repoItemOwnerUI = model.repoItemOwnerDomain?.let { repoOwnerUIMapper.mapToPresentation(it) },
      language = model.language
    )
  }
}