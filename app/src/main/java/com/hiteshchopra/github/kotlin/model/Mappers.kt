package com.hiteshchopra.github.kotlin.model

import com.hiteshchopra.github.domain.mapper.UiModelMapper
import com.hiteshchopra.github.domain.model.repo.RepoItemDomain
import com.hiteshchopra.github.domain.model.repo.RepoItemOwnerDomain
import com.hiteshchopra.github.domain.model.search.SearchResultDomainItem
import com.hiteshchopra.github.domain.model.search.SearchResultDomainOwner

object Mappers {
  class RepoOwnerUIMapper : UiModelMapper<RepoItemOwnerDomain, RepoItemOwnerUI> {
    override fun mapToPresentation(model: RepoItemOwnerDomain): RepoItemOwnerUI {
      return RepoItemOwnerUI(
        avatarUrl = model.avatarUrl,
        name = model.login,
      )
    }
  }

  class SearchOwnerUIMapper : UiModelMapper<SearchResultDomainOwner, RepoItemOwnerUI> {
    override fun mapToPresentation(model: SearchResultDomainOwner): RepoItemOwnerUI {
      return RepoItemOwnerUI(
        avatarUrl = model.avatarUrl,
        name = model.login,
      )
    }
  }

  class RepoItemUIMapper(private val repoOwnerUIMapper: RepoOwnerUIMapper) :
    UiModelMapper<RepoItemDomain, RepoItemUI> {
    override fun mapToPresentation(model: RepoItemDomain): RepoItemUI {
      return RepoItemUI(
        id = model.id,
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

  class SearchItemUIMapper(private val searchOwnerUIMapper: SearchOwnerUIMapper) :
    UiModelMapper<SearchResultDomainItem, RepoItemUI> {
    override fun mapToPresentation(model: SearchResultDomainItem): RepoItemUI {
      return RepoItemUI(
        id = model.id,
        name = model.name,
        repoItemOwnerUI = model.owner?.let { searchOwnerUIMapper.mapToPresentation(it) },
        language = model.language,
        archived = model.archived,
        openIssuesCount = model.openIssuesCount,
        description = model.description,
        htmlUrl = model.htmlUrl
      )
    }
  }
}