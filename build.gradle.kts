plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.alex.poker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = ext["junitVersion"]

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}