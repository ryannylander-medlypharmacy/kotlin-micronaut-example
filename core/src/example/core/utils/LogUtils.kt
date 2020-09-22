// ktlint-disable filename

package example.core.utils

import org.slf4j.LoggerFactory

object Log {

    fun debug(tag: String, message: String?) {
        LoggerFactory.getLogger(tag).debug(message)
    }

    fun info(tag: String, message: String?) {
        LoggerFactory.getLogger(tag).info(message)
    }

    fun error(tag: String, message: String?, throwable: Throwable? = null) {
        LoggerFactory.getLogger(tag).error(message, throwable)
    }

    fun warn(tag: String, message: String?) {
        LoggerFactory.getLogger(tag).warn(message)
    }
}
