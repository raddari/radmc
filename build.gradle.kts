plugins {
    java
    id("io.freefair.lombok") version "6.4.2"
}

repositories {
    mavenCentral()
}

project.group = "io.radmc"
project.version = "0.1.0"


val javaVersion = JavaVersion.VERSION_17

val log4jVersion = "2.17.0"
val gsonVersion = "2.8.9"
val guavaVersion = "31.0.1-jre"
val jbAnnotationsVersion = "23.0.0"
val junitVersion = "5.8.2"
val lwjglVersion = "3.3.1"
val lwjglNatives = "natives-windows"
val jomlVersion = "1.10.4"

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

dependencies {
    implementation("com.google.code.gson:gson:${gsonVersion}")
    implementation("com.google.guava:guava:${guavaVersion}")

    implementation("org.jetbrains:annotations:${jbAnnotationsVersion}")

    compileOnly("org.apache.logging.log4j:log4j-api:${log4jVersion}")
    runtimeOnly("org.apache.logging.log4j:log4j-core:${log4jVersion}")

    testCompileOnly("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

    implementation(platform("org.lwjgl:lwjgl-bom:${lwjglVersion}"))

    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-glfw")
    implementation("org.lwjgl", "lwjgl-jemalloc")
    implementation("org.lwjgl", "lwjgl-openal")
    implementation("org.lwjgl", "lwjgl-opengl")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-jemalloc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openal", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
    implementation("org.joml:joml:${jomlVersion}")
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.apache.logging.log4j.LogManager")
    useJUnitPlatform()
}