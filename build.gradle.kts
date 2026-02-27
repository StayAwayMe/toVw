plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "com.example"
version = "1.0.0"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

// 配置 IntelliJ Platform 插件
intellij {
    version.set("2023.1.7")
    type.set("IC")
    plugins.set(listOf(/* 插件依赖 */))
}

tasks {
    // 设置 Java 编译版本（兼容 Java 11）
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("243.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

// 配置 JVM 使用 UTF-8 编码
tasks.withType<JavaCompile> {
    options.compilerArgs.add("-encoding")
    options.compilerArgs.add("UTF-8")
}

// Gradle JVM 使用 UTF-8
System.setProperty("file.encoding", "UTF-8")

