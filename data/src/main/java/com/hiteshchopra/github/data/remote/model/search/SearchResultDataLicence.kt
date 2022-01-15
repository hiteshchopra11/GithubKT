package com.hiteshchopra.github.data.remote.model.search

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.data.mapper.DataModel
import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.domain.model.search.SearchResultDomainLicence

data class SearchResultDataLicence(
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
) : DataModel()

class SearchLicenseEntityMapper :
  EntityMapper<SearchResultDomainLicence, SearchResultDataLicence> {
  override fun mapToDomain(entity: SearchResultDataLicence): SearchResultDomainLicence {
    return SearchResultDomainLicence(
      key = entity.key,
      name = entity.name,
      nodeId = entity.nodeId,
      spdxId = entity.spdxId,
      url = entity.url
    )
  }
}