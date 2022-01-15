package com.hiteshchopra.github.data.remote.model.search

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.data.mapper.DataModel
import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.domain.model.search.SearchResultDomain

data class SearchResultData(
  @SerializedName("incomplete_results")
  val incompleteResults: Boolean?,
  @SerializedName("items")
  val items: List<SearchResultDataItem>?,
  @SerializedName("total_count")
  val totalCount: Int?
) : DataModel()

class SearchResultEntityMapper(private val searchItemEntityMapper: SearchResultItemEntityMapper) :
  EntityMapper<SearchResultDomain, SearchResultData> {
  override fun mapToDomain(entity: SearchResultData): SearchResultDomain {
    return SearchResultDomain(
      incompleteResults = entity.incompleteResults,
      searchResultDataItems = entity.items?.map { searchItemEntityMapper.mapToDomain(it) },
      totalCount = entity.totalCount
    )
  }
}