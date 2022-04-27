plugins {
    java
    id("io.freefair.lombok") version "6.4.2"
}

repositories {
    mavenCentral()
}

project.group = "io.github.raddari"
project.version = "0.1.0"


val javaVersion = JavaVersion.VERSION_17

val log4jVersion = "2.17.0"
val gsonVersion = "2.8.9"
val guavaVersion = "31.0.1-jre"
val jbAnnotationsVersion = "23.0.0"
val junitVersion = "5.8.2"

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
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.apache.logging.log4j.LogManager")
    useJUnitPlatform()
}