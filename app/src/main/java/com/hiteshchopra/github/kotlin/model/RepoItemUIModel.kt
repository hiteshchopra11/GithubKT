package com.hiteshchopra.github.kotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.UIModel
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
  @SerializedName("id")
  val id: Int,
) : UIModel(), Parcelable