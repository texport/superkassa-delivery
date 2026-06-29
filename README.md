# superkassa-delivery

[![Maven Central](https://img.shields.io/maven-central/v/io.github.texport/superkassa-delivery.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.texport/superkassa-delivery)
[![Version](https://img.shields.io/badge/Version-1.1.0-blue.svg)](https://github.com/texport/superkassa-delivery/releases)
[![Coverage](https://img.shields.io/badge/Coverage-100%25-brightgreen.svg)](https://github.com/texport/superkassa-delivery/actions)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![CI Build](https://img.shields.io/github/actions/workflow/status/texport/superkassa-delivery/ci.yml?branch=main&label=CI%20Build)](https://github.com/texport/superkassa-delivery/actions)

---

### [Documentation in English](#documentation-in-english) &middot; [Документация на русском языке](#документация-на-русском-языке)

---

## Documentation in English

### Purpose and Architecture
This library serves as a **unified multiplatform abstraction layer** for dispatching and delivering fiscal receipts in the **Superkassa** ecosystem. 

Rather than hardcoding platform-specific APIs or protocol logic into the core business applications, this library decouples delivery orchestration from concrete transport channels:
1. **Core Independence**: The domain and application layers only rely on `DeliveryRequest` and `DeliveryService`.
2. **Pluggable Architecture**: Concrete delivery implementations (e.g., HTTP APIs, Android Bluetooth printing, SMTP/SMS gateway integrations) are built on top of the `DeliveryAdapter` interface and passed dynamically at runtime.
3. **Multiplatform Compatibility**: Written in pure Kotlin, it is fully compatible with Kotlin JVM (Server), Kotlin JS (Web), and Kotlin Native/Android runtimes.

---

#### Kotlin Multiplatform & Android
Add the dependency to your shared `commonMain` source set inside `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("io.github.texport:superkassa-delivery:1.1.0")
            }
        }
    }
}
```

#### Apple Swift Package Manager (SPM)
You can integrate this library directly into your iOS project using Xcode's Swift Package Manager:
1. In Xcode, select **File ➔ Add Package Dependencies...**
2. Enter the repository URL: `https://github.com/texport/superkassa-delivery.git`
3. Set the version rules to **Up to Next Major** starting with `1.1.0`.

---

### Usage Example

```kotlin
import io.github.texport.superkassa.delivery.application.service.DeliveryService
import io.github.texport.superkassa.delivery.domain.model.DeliveryChannel
import io.github.texport.superkassa.delivery.domain.model.DeliveryRequest

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

### Назначение и архитектура
Данная библиотека является **унифицированным мультиплатформенным слоем абстракции** для отправки и доставки фискальных чеков в экосистеме **Superkassa**.

Вместо того чтобы жестко привязывать бизнес-логику к конкретным API платформ или протоколам, библиотека изолирует процесс оркестрации доставки:
1. **Независимость ядра**: Доменный и прикладной слои приложения взаимодействуют исключительно с сущностями `DeliveryRequest` и сервисом `DeliveryService`.
2. **Расширяемая архитектура (Pluggable)**: Конкретные интеграции (HTTP-клиенты, печать через Bluetooth на Android, шлюзы почты/SMS) реализуют интерфейс `DeliveryAdapter` и подключаются динамически в рантайме.
3. **Кроссплатформенность**: Библиотека написана на чистом Kotlin, совместима с JVM (серверные приложения), JS (веб-клиенты) и Native/Android средами выполнения.

---

#### В Kotlin Multiplatform и Android
Добавьте зависимость в ваш общий набор исходников `commonMain` в `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("io.github.texport:superkassa-delivery:1.1.0")
            }
        }
    }
}
```

#### В Apple iOS проектах (через SPM)
Вы можете подключить библиотеку непосредственно в iOS приложение с помощью Swift Package Manager в Xcode:
1. Выберите в Xcode: **File ➔ Add Package Dependencies...**
2. Введите URL репозитория: `https://github.com/texport/superkassa-delivery.git`
3. Установите правило версии **Up to Next Major** начиная с `1.1.0`.

---

### Пример использования

```kotlin
import io.github.texport.superkassa.delivery.application.service.DeliveryService
import io.github.texport.superkassa.delivery.domain.model.DeliveryChannel
import io.github.texport.superkassa.delivery.domain.model.DeliveryRequest

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
