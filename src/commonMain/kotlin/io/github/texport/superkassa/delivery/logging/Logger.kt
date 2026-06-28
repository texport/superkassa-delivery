package io.github.texport.superkassa.delivery.logging

expect class Logger {
    fun info(message: String, arg1: Any?, arg2: Any?)
    fun info(message: String, arg1: Any?)
    fun warn(message: String, arg1: Any?, arg2: Any?)
}

expect fun getLogger(clazz: kotlin.reflect.KClass<*>): Logger
