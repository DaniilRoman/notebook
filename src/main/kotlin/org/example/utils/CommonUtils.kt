package org.example.utils

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)