
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.6.2"
}

version = "0.1"
group = "dev.dayoung"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.spring:micronaut-spring-annotation")
    kapt("io.micronaut.spring:micronaut-spring-boot-annotation")
    kapt("io.micronaut.spring:micronaut-spring-web-annotation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-http-server")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-client")
    //implementation("io.micronaut.kubernetes:micronaut-kubernetes-discovery-client")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-informer")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("io.micronaut.spring:micronaut-spring-boot")
    runtimeOnly("io.micronaut.spring:micronaut-spring-web")
    kaptTest("io.micronaut.spring:micronaut-spring-annotation")
    kaptTest("io.micronaut.spring:micronaut-spring-boot-annotation")
    kaptTest("io.micronaut.spring:micronaut-spring-web-annotation")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("dev.dayoung.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("dev.dayoung.*")
    }
}

task<Copy>("prepare") {
    val jar = tasks.getByName<Jar>("jar")
    dependsOn(jar)
    from(jar.outputs.files.singleFile)
    into("build")
}
docker {
    copySpec()
        .from(tasks.getByName<Copy>("prepare").outputs)
        .into("build")
}