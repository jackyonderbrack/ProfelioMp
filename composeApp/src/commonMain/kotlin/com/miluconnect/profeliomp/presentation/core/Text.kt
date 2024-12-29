package com.miluconnect.profeliomp.presentation.core

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface Text {
    data class DynamicString(val value: String): Text
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): Text

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}