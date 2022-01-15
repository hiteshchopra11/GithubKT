package com.hiteshchopra.github.domain.model.search

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.DomainModel

data class SearchResultDomain(
  @SerializedName("incomplete_results")
  val incompleteResults: Boolean?,
  @SerializedName("items")
  val searchResultDataItems: List<SearchResultDomainItem>?,
  @SerializedName("total_count")
  val totalCount: Int?
) : DomainModel()