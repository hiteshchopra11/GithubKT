package com.hiteshchopra.github.domain.mapper

open class DomainModel

open class UIModel

interface UiModelMapper<M : DomainModel, MV : UIModel> {
  fun mapToPresentation(model: M): UIModel
}