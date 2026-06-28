package io.github.texport.superkassa.delivery.application.service

import io.github.texport.superkassa.delivery.domain.model.DeliveryRequest
import io.github.texport.superkassa.delivery.domain.model.DeliveryResult
import io.github.texport.superkassa.delivery.domain.port.DeliveryAdapter
import io.github.texport.superkassa.delivery.logging.getLogger

/**
 * Сервис доставки чеков.
 */
class DeliveryService(
    adapters: List<DeliveryAdapter>
) {
    private val logger = getLogger(DeliveryService::class)
    private val adapterByChannel = adapters.associateBy { it.channel }

    fun deliver(request: DeliveryRequest): DeliveryResult {
        val adapter = adapterByChannel[request.channel]
            ?: return DeliveryResult(
                false,
                "[EN] No adapter for channel ${request.channel} / " +
                    "[RU] Нет адаптера для канала ${request.channel} / " +
                    "[KK] ${request.channel} арнасы үшін адаптер жоқ"
            )
        logger.info("Delivery start. channel={}, documentId={}", request.channel, request.documentId)
        val result = adapter.send(request)
        if (result.ok) {
            logger.info("Delivery success. documentId={}", request.documentId)
        } else {
            logger.warn("Delivery failed. documentId={}, message={}", request.documentId, result.message)
        }
        return result
    }
}
