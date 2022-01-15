package com.hiteshchopra.github.kotlin.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.hiteshchopra.github.domain.usecase.FetchSearchResultUseCase
import com.hiteshchopra.github.kotlin.model.Mappers.SearchItemUIMapper
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

class RepoSearchVM(
  private val fetchSearchResultUseCase: FetchSearchResultUseCase,
  private val searchItemUIMapper: SearchItemUIMapper
) : ViewModel() {
  var paginatedResponse: Flow<PagingData<RepoItemUI>>? = null

  @OptIn(FlowPreview::class) fun onSearchTextChanged(changedSearchText: String) {
    if (changedSearchText.isEmpty()) {
      return
    }
    paginatedResponse =
      fetchSearchResultUseCase.perform(changedSearchText).map { pagingDataDomain ->
        pagingDataDomain.map { repoItemDomain ->
          searchItemUIMapper.mapToPresentation(repoItemDomain)
        }
      }.debounce(500).cachedIn(viewModelScope)
  }
}
