package com.example.core.logs

import com.example.core.BuildConfig
import com.example.core.utils.Constants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.reflect.KProperty

interface LoggerDelegate {
    fun debug(message: String)
    fun info(message: String)
    fun warn(message: String)
    fun error(message: String)
}

private const val LOGGER_NAME = "MyAppLogger"

private class LoggerUtils(private val tag: String?) : LoggerDelegate {
    private val logger = Logger.getLogger(LOGGER_NAME)

    private fun log(level: Level, message: String) {
        if (BuildConfig.DEBUG) {
            val timestamp =
                SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
            logger.log(level, "[$timestamp][$tag]: $message")
        }
    }

    override fun debug(message: String) {
        log(Level.FINE, message)
    }

    override fun info(message: String) {
        log(Level.INFO, message)
    }

    override fun warn(message: String) {
        log(Level.WARNING, message)
    }

    override fun error(message: String) {
        log(Level.SEVERE, message)
    }
}

class LoggerDelegateProvider(private val tag: String) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): LoggerDelegate {
        return LoggerUtils(tag)
    }
}