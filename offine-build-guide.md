# 离线构建指南

## 问题
Java 11 与 Gradle 仓库的 SSL/TLS 握手失败。

## 解决方案 1：使用代理

在 `gradle.properties` 中添加：
```
systemProp.http.proxyHost=your-proxy-host
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=your-proxy-host
systemProp.https.proxyPort=8080
```

## 解决方案 2：升级 Java

下载并安装 Java 17：
https://adoptium.net/temurin/releases/?version=17

然后配置环境变量 JAVA_HOME 指向 Java 17。

## 解决方案 3：使用 Maven 构建（推荐）

将插件转换为 Maven 项目，Maven 对 Java 11 的兼容性更好。
