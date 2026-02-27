# toVW WebStorm Plugin

一个简单的 WebStorm/IntelliJ IDEA 插件，用于将数值转换为 vw 单位。

## 功能

选中任意数值（如 375），插件会将其转换为 vw 单位（基于 375px = 100vw 的比例）。

## 使用方法

1. 在编辑器中选中一个数字（如 `375`、`16`、`24` 等）
2. 右键点击，选择 "Convert to VW"
3. 或者使用快捷键 `Ctrl + Shift + V`（Windows/Linux）或 `Cmd + Shift + V`（Mac）

## 转换规则

- 设计稿宽度：375px
- 转换公式：`vw = (数值 / 375) * 100`
- 示例：
  - 375 → 100vw
  - 187.5 → 50vw
  - 37.5 → 10vw
  - 16 → 4.27vw

## 开发和构建

### 前置要求

- JDK 17 或更高版本
- Gradle 8.0+
- IntelliJ IDEA 2023.2.5 或更高版本

### 构建插件

```bash
./gradlew buildPlugin
```

生成的插件文件在 `build/distributions/toVW-1.0.0.zip`

### 安装插件

1. 在 WebStorm/IntelliJ IDEA 中打开 `Settings/Preferences`
2. 进入 `Plugins`
3. 点击齿轮图标，选择 `Install Plugin from Disk...`
4. 选择构建生成的 zip 文件

## 开发计划

- [ ] 支持自定义设计稿宽度
- [ ] 批量转换功能
- [ ] 支持反向转换（vw → px）

# toVw