package io.github.texport.superkassa.delivery.domain.port

import io.github.texport.superkassa.delivery.domain.model.DeliveryChannel
import io.github.texport.superkassa.delivery.domain.model.DeliveryRequest
import io.github.texport.superkassa.delivery.domain.model.DeliveryResult

/**
 * Адаптер доставки по конкретному каналу.
 */
interface DeliveryAdapter {
    val channel: DeliveryChannel
    fun send(request: DeliveryRequest): DeliveryResult
}
