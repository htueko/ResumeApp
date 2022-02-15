package com.htueko.resumeapp.presentation.view.common.commonstate

/**
 * a model that represented everything that user can do on most of the screen.
 */
sealed class CommonUiEvent {
    /**
     * to navigate between screen with given [route].
     */
    data class Navigate(val route: String) : CommonUiEvent()

    /**
     * to show snack bar with [message] and optional [action].
     */
    data class ShowSnackBar(
        val message: String,
        val action: String? = null,
    ) : CommonUiEvent()

    /**
     * to go back to previous screen or quit the app.
     */
    object PopBackStack : CommonUiEvent()
}