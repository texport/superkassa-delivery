package io.github.texport.superkassa.delivery.domain.model

/**
 * Результат доставки.
 */
data class DeliveryResult(
    val ok: Boolean,
    val message: String? = null
)
