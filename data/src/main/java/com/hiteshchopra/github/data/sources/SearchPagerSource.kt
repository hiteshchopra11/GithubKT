package com.hiteshchopra.github.data.sources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hiteshchopra.github.data.remote.model.search.SearchResultItemEntityMapper
import com.hiteshchopra.github.data.sources.exceptions.EmptyPageException
import com.hiteshchopra.github.domain.SafeResult.NetworkError
import com.hiteshchopra.github.domain.SafeResult.Success
import com.hiteshchopra.github.domain.getErrorOrNull
import com.hiteshchopra.github.domain.getSuccessOrNull
import com.hiteshchopra.github.domain.model.search.SearchResultDomainItem
import java.io.IOException

class SearchPagerSource(
  private val query: String,
  private val githubRemoteSource: IGithubRemoteSource,
  private val searchResultItemEntityMapper: SearchResultItemEntityMapper,
) : PagingSource<Int, SearchResultDomainItem>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultDomainItem> {
    return try {
      // Start refresh at page 1 if undefined.
      val page = params.key ?: 1
      when (val result =
        githubRemoteSource.getSearchResults(
          query = query,
          perPage = IGithubRemoteSource.PAGE_SIZE_DEFAULT,
          page = page
        )
      ) {
        is Success -> {
          Log.e(
            "RESULTIS",
            result.getSuccessOrNull()?.items?.map { searchResultItemEntityMapper.mapToDomain(it) }
              .toString()
          )
          val paginatedContent = result.getSuccessOrNull() ?: return LoadResult.Error(Exception())
          if (paginatedContent.items == null || paginatedContent.items.isEmpty()) {
            LoadResult.Error(EmptyPageException())
          } else {
            LoadResult.Page(
              data = paginatedContent.items.map { searchResultItemEntityMapper.mapToDomain(it) },
              prevKey = if (page == 1) null else page - 1,
              nextKey = page + 1
            )
          }
        }
        is NetworkError -> {
          LoadResult.Error(IOException())
        }
        else -> {
          LoadResult.Error(result.getErrorOrNull()?.exception ?: Exception())
        }
      }
    } catch (exception: Exception) {
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, SearchResultDomainItem>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}
