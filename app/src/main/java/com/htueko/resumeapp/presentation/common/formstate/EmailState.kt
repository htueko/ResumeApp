package com.htueko.resumeapp.presentation.common.formstate

import android.util.Patterns

class EmailState : BasicTextFieldState(
    validator = { email -> isEmailValid(email) },
    errorMessage = ::emailErrorMessage,
)

private fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun emailErrorMessage(email: String) = "error email address"
