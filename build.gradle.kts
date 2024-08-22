plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.alex.gl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.10.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}