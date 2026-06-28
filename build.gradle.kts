plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.nmcp)
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
    signing
    jacoco
}

group = "io.github.texport"
version = libs.versions.superkassaDelivery.get()

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm()
    
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvmToolchain(libs.versions.java.get().toInt())

    sourceSets {
        commonMain {
            dependencies {
                // Pure Kotlin common code
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        jvmMain {
            dependencies {
                implementation(libs.slf4j.api)
            }
        }
    }

    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    freeCompilerArgs.add("-Xexpect-actual-classes")
                }
            }
        }
    }
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("superkassa-delivery")
            description.set("Unified delivery dispatcher abstraction for Superkassa fiscal receipts")
            url.set("https://github.com/texport/superkassa-delivery")

            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }

            developers {
                developer {
                    id.set("sergeyivanov")
                    name.set("Sergey Ivanov")
                    email.set("ivanov.sergey.ekb@gmail.com")
                }
            }

            scm {
                connection.set("scm:git:git://github.com/texport/superkassa-delivery.git")
                developerConnection.set("scm:git:ssh://github.com/texport/superkassa-delivery.git")
                url.set("https://github.com/texport/superkassa-delivery")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("SIGNING_KEY")
    val signingPassword = System.getenv("SIGNING_PASSWORD")
    if (!signingKey.isNullOrEmpty() && !signingPassword.isNullOrEmpty()) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
    sign(publishing.publications)
}

nmcp {
    publishAllPublicationsToCentralPortal {
        username.set(project.findProperty("ossrhUsername")?.toString() ?: System.getenv("OSSRH_USERNAME"))
        password.set(project.findProperty("ossrhPassword")?.toString() ?: System.getenv("OSSRH_PASSWORD"))
        publishingType.set("AUTOMATIC")
    }
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

val jacocoTestReport = tasks.register<JacocoReport>("jacocoTestReport") {
    description = "Generates Jacoco code coverage report for the JVM target."
    dependsOn(tasks.named("jvmTest"))
    classDirectories.setFrom(files(tasks.named("compileKotlinJvm")))
    sourceDirectories.setFrom(files("src/commonMain/kotlin", "src/jvmMain/kotlin"))
    executionData.setFrom(files(layout.buildDirectory.file("jacoco/jvmTest.exec")))
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

val jacocoTestCoverageVerification = tasks.register<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    description = "Verifies Jacoco code coverage threshold for the JVM target."
    dependsOn(jacocoTestReport)
    executionData.setFrom(files(layout.buildDirectory.file("jacoco/jvmTest.exec")))
    classDirectories.setFrom(files(tasks.named("compileKotlinJvm")))
    violationRules {
        rule {
            limit {
                minimum = "0.98".toBigDecimal()
            }
        }
    }
}

tasks.check {
    dependsOn(jacocoTestCoverageVerification)
}

detekt {
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = true
    autoCorrect = true
    source.setFrom(files("src/commonMain/kotlin", "src/jvmMain/kotlin", "src/iosMain/kotlin"))
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    exclude("**/build/generated/**")
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}
