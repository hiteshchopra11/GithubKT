package com.hiteshchopra.github.data.remote.model

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.data.mapper.DataModel
import com.hiteshchopra.github.data.mapper.EntityMapper
import com.hiteshchopra.github.domain.model.RepoItemOwnerDomain
import com.hiteshchopra.github.domain.model.RepoItemPermissionsDomain

data class RepoItemPermissionsData(
  @SerializedName("admin")
  val admin: Boolean?,
  @SerializedName("pull")
  val pull: Boolean?,
  @SerializedName("push")
  val push: Boolean?
) : DataModel()

class RepoItemPermissionDomainMapper : EntityMapper<RepoItemPermissionsDomain, RepoItemPermissionsData> {
  override fun mapToDomain(entity: RepoItemPermissionsData): RepoItemPermissionsDomain {
    return RepoItemPermissionsDomain(
      admin = entity.admin,
      pull = entity.pull,
      push = entity.push
    )
  }
}