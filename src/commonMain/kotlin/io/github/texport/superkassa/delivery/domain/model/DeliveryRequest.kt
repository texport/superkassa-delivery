package io.github.texport.superkassa.delivery.domain.model

/**
 * Delivery request data container representing a request to dispatch a document.
 *
 * Запрос на доставку, содержащий данные для отправки документа.
 *
 * Құжатты жіберуге арналған деректерді қамтитын жеткізу сұранысы.
 *
 * @property cashboxId Target cashbox identifier.
 * @property documentId Document identifier to be dispatched.
 * @property channel Target delivery communication channel.
 * @property destination Target receiver address (e.g. Email address or Phone number).
 * @property payloadUrl URL of the document (used if payloadType=LINK).
 * @property payloadBytes Binary representation of the document (PDF/IMAGE/HTML/ESC_POS).
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
