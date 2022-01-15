package com.hiteshchopra.github.domain.model.repo

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.github.domain.mapper.DomainModel

data class RepoItemPermissionsDomain(
  @SerializedName("admin")
  val admin: Boolean?,
  @SerializedName("pull")
  val pull: Boolean?,
  @SerializedName("push")
  val push: Boolean?
) : DomainModel()