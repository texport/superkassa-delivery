package io.github.texport.superkassa.delivery.domain.model

/**
 * Supported delivery communication channels.
 *
 * Поддерживаемые каналы коммуникации для доставки.
 *
 * Жеткізу үшін қолдау көрсетілетін байланыс арналары.
 */
enum class DeliveryChannel {
    PRINT,
    EMAIL,
    SMS,
    WHATSAPP,
    TELEGRAM
}
