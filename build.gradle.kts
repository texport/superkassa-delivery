plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.jvm)
    id("maven-publish")
}

group = "kz.mybrain"
version = "1.0"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

detekt {
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = true
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "17"
}
