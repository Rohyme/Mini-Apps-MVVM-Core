package com.rohyme.core.presentation.appUtils.states

/**
 * Created By Rohyme
 */
sealed class PaginatedStateView {
    data class Success<out T>(val data: T) : PaginatedStateView()
    data class PaginationSuccess<out T>(val data :T): PaginatedStateView()
    data class Errors(val error: Throwable?) : PaginatedStateView()
    data class PaginationError(val error: Throwable?) : PaginatedStateView()

    object Empty : PaginatedStateView()
    object Loading : PaginatedStateView()
    object PaginationLoading : PaginatedStateView()
    object PaginationFinished: PaginatedStateView()

}