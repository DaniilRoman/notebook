package org.example.domain.request

data class NotebookRequest(val title: String, val text: String) {
    fun equals(): Boolean {
        return title == text
    }
}