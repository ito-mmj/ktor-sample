import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.6.20"
    application
    id("io.ktor.plugin") version "2.1.2"
    kotlin("plugin.serialization") version "1.6.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("serverKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { setUrl("https://dl.bintray.com/kotlin/ktor") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlinx") }
}

dependencies {
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
//    implementation("io.ktor:ktor-server-locations:$ktor_version")
    implementation("io.ktor:ktor-server-locations-jvm:$ktor_version")
//    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.dropwizard.metrics:metrics-core:4.1.18")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.google.code.gson:gson:2.7")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}