package com.rohyme.core.presentation.appUtils.states

/**
 *
 * Created By Rohyme
 */
sealed class StateView {
    data class Success<out T>(val data: T) : StateView()
    data class SuccessWithState<out T>(val data: T, val statusCode: Int) : StateView()
    data class Error(val error: Throwable?) : StateView()
    data class ErrorWithState(val error: Throwable?, val statusCode: Int) : StateView()
    object Empty : StateView()
    object Loading : StateView()
}