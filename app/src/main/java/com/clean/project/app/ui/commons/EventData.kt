package com.clean.project.app.ui.commons

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class EventData<out T>(private val content: T) {

    var handled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}