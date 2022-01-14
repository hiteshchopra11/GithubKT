package com.hiteshchopra.github.kotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.UIModel
import com.hiteshchopra.github.domain.mapper.UiModelMapper
import com.hiteshchopra.github.domain.model.RepoItemDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoItemUI(
  @SerializedName("name")
  val name: String?,
  @SerializedName("owner")
  val repoItemOwnerUI: RepoItemOwnerUI?,
  @SerializedName("language")
  val language: String?,
  @SerializedName("archived")
  val archived: Boolean?,
  @SerializedName("open_issues_count")
  val openIssuesCount: Int?,
  @SerializedName("description")
  val description: String?,
  @SerializedName("html_url")
  val htmlUrl: String?,
) : UIModel(), Parcelable

class RepoItemUIMapper(private val repoOwnerUIMapper: RepoOwnerUIMapper) :
  UiModelMapper<RepoItemDomain, RepoItemUI> {
  override fun mapToPresentation(model: RepoItemDomain): RepoItemUI {
    return RepoItemUI(
      name = model.name,
      repoItemOwnerUI = model.repoItemOwnerDomain?.let { repoOwnerUIMapper.mapToPresentation(it) },
      language = model.language,
      archived = model.archived,
      openIssuesCount = model.openIssuesCount,
      description = model.description,
      htmlUrl = model.htmlUrl
    )
  }
}