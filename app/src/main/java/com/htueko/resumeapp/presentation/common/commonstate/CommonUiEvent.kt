package com.htueko.resumeapp.presentation.common.commonstate

/**
 * a model that represented everything that user can do on most of the screen.
 */
sealed class CommonUiEvent {
    /**
     * to show snack bar
     */
    object ShowSnackBar : CommonUiEvent()

    /**
     * to pop backstack
     */
    object PopBackStack : CommonUiEvent()

    /**
     * to pop backstack and send data to previous one.
     */
    data class PopBackStackAndSendData(val resumeId: Int) : CommonUiEvent()
}
