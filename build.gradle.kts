plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    jacoco
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.junit.platform:junit-platform-suite:1.9.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

kotlin {
    jvmToolchain(11)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html
    }
}
application {
    mainClass.set("MainKt")
}
