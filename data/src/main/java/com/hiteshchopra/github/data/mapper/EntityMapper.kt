package com.hiteshchopra.github.data.mapper

import com.hiteshchopra.github.domain.mapper.DomainModel

interface EntityMapper<M : DomainModel, ME : DataModel> {
  fun mapToDomain(entity: ME): M
}

open class DataModel
