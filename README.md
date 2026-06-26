# superkassa-delivery

[![Maven Central](https://img.shields.io/maven-central/v/kz.mybrain/superkassa-delivery.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/kz.mybrain/superkassa-delivery)
[![Version](https://img.shields.io/badge/Version-1.0-blue.svg)]()
[![Coverage](https://img.shields.io/badge/Coverage-100%25-brightgreen.svg)]()
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![CI Build](https://img.shields.io/github/actions/workflow/status/texport/superkassa-server/ci.yml?branch=main&label=CI%20Build)](https://github.com/texport/superkassa-server/actions)

---

### [Documentation in English](#documentation-in-english) &middot; [Документация на русском языке](#документация-на-русском-языке)

---

## Documentation in English

Receipt delivery dispatch library for the **Superkassa** fiscalization system. Supports multiple delivery channels including:
- Email
- SMS
- WhatsApp
- Telegram
- Esc/Pos Printing

---

### Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("kz.mybrain:superkassa-delivery:1.0")
}
```

---

### Usage Example

```kotlin
import kz.mybrain.superkassa.delivery.application.service.DeliveryService
import kz.mybrain.superkassa.delivery.domain.model.DeliveryChannel
import kz.mybrain.superkassa.delivery.domain.model.DeliveryRequest

// Initialize service with channel adapters
val deliveryService = DeliveryService(listOf(emailAdapter, telegramAdapter))

val request = DeliveryRequest(
    cashboxId = "cashbox-1",
    documentId = "doc-1",
    channel = DeliveryChannel.EMAIL,
    destination = "client@example.com",
    payloadUrl = "https://example.com/receipts/123"
)

val result = deliveryService.deliver(request)
if (result.ok) {
    println("Delivered successfully")
} else {
    println("Failed to deliver: ${result.message}")
}
```

---

## Документация на русском языке

Библиотека отправки и доставки фискальных чеков для системы фискализации **Superkassa**. Поддерживает различные каналы доставки:
- Электронная почта (Email)
- SMS-сообщения
- Сообщения в мессенджеры WhatsApp и Telegram
- Локальная печать (Esc/Pos)

---

### Установка

Добавьте зависимость в ваш `build.gradle.kts`:

```kotlin
dependencies {
    implementation("kz.mybrain:superkassa-delivery:1.0")
}
```

---

### Пример использования

```kotlin
import kz.mybrain.superkassa.delivery.application.service.DeliveryService
import kz.mybrain.superkassa.delivery.domain.model.DeliveryChannel
import kz.mybrain.superkassa.delivery.domain.model.DeliveryRequest

// Инициализация сервиса со списком адаптеров
val deliveryService = DeliveryService(listOf(emailAdapter, telegramAdapter))

val request = DeliveryRequest(
    cashboxId = "cashbox-1",
    documentId = "doc-1",
    channel = DeliveryChannel.EMAIL,
    destination = "client@example.com",
    payloadUrl = "https://example.com/receipts/123"
)

val result = deliveryService.deliver(request)
if (result.ok) {
    println("Успешно доставлено")
} else {
    println("Ошибка доставки: ${result.message}")
}
```

---

## License / Лицензия

This project is licensed under the Apache License 2.0. See [LICENSE](LICENSE) for details.

Этот проект распространяется под лицензией Apache License 2.0. Подробности см. в файле [LICENSE](LICENSE).
