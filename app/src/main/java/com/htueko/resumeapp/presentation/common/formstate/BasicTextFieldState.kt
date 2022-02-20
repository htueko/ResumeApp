package com.htueko.resumeapp.presentation.common.formstate

open class BasicTextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorMessage: (String) -> String,
)
