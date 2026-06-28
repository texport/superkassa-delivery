package io.github.texport.superkassa.delivery.domain.model

/**
 * Поддерживаемые каналы коммуникации для доставки.
 */
enum class DeliveryChannel {
    PRINT,
    EMAIL,
    SMS,
    WHATSAPP,
    TELEGRAM
}
