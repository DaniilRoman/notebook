package org.example.utils

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LoggerDelegate : ReadOnlyProperty<Any?, Logger> {

    companion object {
        private fun <T>createLogger(clazz: Class<T>) : Logger {
            return LogManager.getLogger(clazz)
        }
    }

    private var log: Logger? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger {
        if (log == null) {
            log = createLogger(thisRef!!.javaClass)
        }
        return log!!
    }
}