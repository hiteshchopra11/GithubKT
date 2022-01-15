package com.hiteshchopra.github.domain.model.search

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.DomainModel

data class SearchResultDomainLicence(
  @SerializedName("key")
  val key: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("node_id")
  val nodeId: String?,
  @SerializedName("spdx_id")
  val spdxId: String?,
  @SerializedName("url")
  val url: String?
) : DomainModel()