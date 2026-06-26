package io.github.texport.superkassa.delivery.domain.model

/**
 * Канал доставки чека.
 */
enum class DeliveryChannel {
    PRINT,
    EMAIL,
    SMS,
    WHATSAPP,
    TELEGRAM
}
