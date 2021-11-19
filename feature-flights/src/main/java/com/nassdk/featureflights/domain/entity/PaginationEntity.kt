package com.nassdk.featureflights.domain.entity

data class PaginationEntity(
    val limit: Int,
    val offset: Int,
    val total: Int,
)