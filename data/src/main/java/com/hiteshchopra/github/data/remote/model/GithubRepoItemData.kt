package com.hiteshchopra.github.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.data.mapper.DataModel
import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.domain.model.GithubRepoItemDomain
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepoItemData(
  @SerializedName("archive_url")
  var archiveUrl: String?=null,
  @SerializedName("assignees_url")
  val assigneesUrl: String?=null,
  @SerializedName("blobs_url")
  val blobsUrl: String?=null,
  @SerializedName("branches_url")
  val branchesUrl: String?=null,
  @SerializedName("collaborators_url")
  val collaboratorsUrl: String?=null,
  @SerializedName("comments_url")
  val commentsUrl: String?,
  @SerializedName("commits_url")
  val commitsUrl: String?,
  @SerializedName("compare_url")
  val compareUrl: String?,
  @SerializedName("contents_url")
  val contentsUrl: String?,
  @SerializedName("contributors_url")
  val contributorsUrl: String?,
  @SerializedName("deployments_url")
  val deploymentsUrl: String?,
  @SerializedName("description")
  val description: String?,
  @SerializedName("downloads_url")
  val downloadsUrl: String?,
  @SerializedName("events_url")
  val eventsUrl: String?,
  @SerializedName("fork")
  val fork: Boolean?,
  @SerializedName("forks_url")
  val forksUrl: String?,
  @SerializedName("full_name")
  val fullName: String?,
  @SerializedName("git_commits_url")
  val gitCommitsUrl: String?,
  @SerializedName("git_refs_url")
  val gitRefsUrl: String?,
  @SerializedName("git_tags_url")
  val gitTagsUrl: String?,
  @SerializedName("hooks_url")
  val hooksUrl: String?,
  @SerializedName("html_url")
  val htmlUrl: String?,
  @SerializedName("id")
  val id: Int?,
  @SerializedName("issue_comment_url")
  val issueCommentUrl: String?,
  @SerializedName("issue_events_url")
  val issueEventsUrl: String?,
  @SerializedName("issues_url")
  val issuesUrl: String?,
  @SerializedName("keys_url")
  val keysUrl: String?,
  @SerializedName("labels_url")
  val labelsUrl: String?,
  @SerializedName("languages_url")
  val languagesUrl: String?,
  @SerializedName("merges_url")
  val mergesUrl: String?,
  @SerializedName("milestones_url")
  val milestonesUrl: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("node_id")
  val nodeId: String?,
  @SerializedName("notifications_url")
  val notificationsUrl: String?,
  @SerializedName("owner")
  val repoOwnerData: RepoOwnerData?,
  @SerializedName("private")
  val priv: Boolean?,
  @SerializedName("pulls_url")
  val pullsUrl: String?,
  @SerializedName("releases_url")
  val releasesUrl: String?,
  @SerializedName("stargazers_url")
  val stargazersUrl: String?,
  @SerializedName("statuses_url")
  val statusesUrl: String?,
  @SerializedName("subscribers_url")
  val subscribersUrl: String?,
  @SerializedName("subscription_url")
  val subscriptionUrl: String?,
  @SerializedName("tags_url")
  val tagsUrl: String?,
  @SerializedName("teams_url")
  val teamsUrl: String?,
  @SerializedName("trees_url")
  val treesUrl: String?,
  @SerializedName("url")
  val url: String?
) : DataModel()

class GithubRepoItemMapper(private val repoOwnerMapper: RepoOwnerMapper) :
  EntityMapper<GithubRepoItemDomain, GithubRepoItemData> {
  override fun mapToDomain(entity: GithubRepoItemData): GithubRepoItemDomain {
    return GithubRepoItemDomain(
      archiveUrl = entity.archiveUrl,
      assigneesUrl = entity.assigneesUrl,
      blobsUrl = entity.blobsUrl,
      branchesUrl = entity.branchesUrl,
      collaboratorsUrl = entity.collaboratorsUrl,
      commentsUrl = entity.commentsUrl,
      commitsUrl = entity.commitsUrl,
      compareUrl = entity.compareUrl,
      contentsUrl = entity.contentsUrl,
      contributorsUrl = entity.contributorsUrl,
      deploymentsUrl = entity.deploymentsUrl,
      description = entity.description,
      downloadsUrl = entity.downloadsUrl,
      eventsUrl = entity.eventsUrl,
      fork = entity.fork,
      forksUrl = entity.forksUrl,
      fullName = entity.fullName,
      gitCommitsUrl = entity.gitCommitsUrl,
      gitRefsUrl = entity.gitRefsUrl,
      gitTagsUrl = entity.gitTagsUrl,
      hooksUrl = entity.hooksUrl,
      htmlUrl = entity.htmlUrl,
      id = entity.id,
      issueCommentUrl = entity.issueCommentUrl,
      issueEventsUrl = entity.issueEventsUrl,
      issuesUrl = entity.issuesUrl,
      keysUrl = entity.keysUrl,
      labelsUrl = entity.labelsUrl,
      languagesUrl = entity.languagesUrl,
      mergesUrl = entity.mergesUrl,
      milestonesUrl = entity.milestonesUrl,
      name = entity.name,
      nodeId = entity.nodeId,
      notificationsUrl = entity.notificationsUrl,
      repoOwnerDomain = entity.repoOwnerData?.let { repoOwnerMapper.mapToDomain(it) },
      priv = entity.priv,
      pullsUrl = entity.pullsUrl,
      releasesUrl = entity.releasesUrl,
      stargazersUrl = entity.stargazersUrl,
      statusesUrl = entity.statusesUrl,
      subscribersUrl = entity.subscribersUrl,
      subscriptionUrl = entity.subscriptionUrl,
      tagsUrl = entity.tagsUrl,
      teamsUrl = entity.teamsUrl,
      treesUrl = entity.treesUrl,
      url = entity.url
    )
  }
}