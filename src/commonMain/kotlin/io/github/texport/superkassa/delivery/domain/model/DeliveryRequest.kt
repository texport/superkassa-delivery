package io.github.texport.superkassa.delivery.domain.model

/**
 * Запрос на доставку.
 *
 * @param payloadUrl URL чека (при payloadType=LINK).
 * @param payloadBytes Документ в бинарном виде (PDF/IMAGE/HTML/ESC_POS). Один из payloadUrl или payloadBytes должен быть задан.
 */
data class DeliveryRequest(
    val cashboxId: String,
    val documentId: String,
    val channel: DeliveryChannel,
    val destination: String? = null,
    val payloadUrl: String? = null,
    val payloadBytes: ByteArray? = null
) {
    init {
        require(payloadUrl != null || payloadBytes != null) {
            "[EN] Either payloadUrl or payloadBytes must be set / " +
                "[RU] Либо payloadUrl, либо payloadBytes должен быть задан / " +
                "[KK] Немесе payloadUrl немесе payloadBytes орнатылуы керек"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is DeliveryRequest) return false

        if (cashboxId != other.cashboxId) return false
        if (documentId != other.documentId) return false
        if (channel != other.channel) return false
        if (destination != other.destination) return false
        if (payloadUrl != other.payloadUrl) return false
        if (payloadBytes == null && other.payloadBytes != null) {
            return false
        }
        if (payloadBytes != null && other.payloadBytes == null) {
            return false
        }
        if (payloadBytes != null && other.payloadBytes != null &&
            !payloadBytes.contentEquals(other.payloadBytes)
        ) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = cashboxId.hashCode()
        result = 31 * result + documentId.hashCode()
        result = 31 * result + channel.hashCode()
        result = 31 * result + (destination?.hashCode() ?: 0)
        result = 31 * result + (payloadUrl?.hashCode() ?: 0)
        result = 31 * result + (payloadBytes?.contentHashCode() ?: 0)
        return result
    }
}
