import com.google.cloud.tools.jib.api.buildplan.ImageFormat
import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.7.2"
    id("com.google.cloud.tools.jib") version "3.3.1"

}

version = "1.1"
group = "dev.dayoung"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-client")
    implementation("io.micronaut.kubernetes:micronaut-kubernetes-informer")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
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

jib {
    if(Os.isFamily(Os.FAMILY_MAC)) {
        dockerClient.executable = "/opt/homebrew/bin/podman"
    }
    from.image = "gcr.io/distroless/java17-debian11:nonroot"
    to {
        image = "ghcr.io/ohnoitsyou/k8s-dashboard"
        tags = setOf("latest", "$version")
    }
    container {
        format = ImageFormat.OCI
        ports= listOf("8080")
    }
}