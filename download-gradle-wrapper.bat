@echo off
echo Downloading Gradle Wrapper JAR...

set WRAPPER_DIR=gradle\wrapper
set JAR_FILE=%WRAPPER_DIR%\gradle-wrapper.jar

if not exist "%WRAPPER_DIR%" mkdir "%WRAPPER_DIR%"

powershell -Command "Invoke-WebRequest -Uri 'https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar' -OutFile '%JAR_FILE%'"

if exist "%JAR_FILE%" (
    echo Download complete: %JAR_FILE%
) else (
    echo Failed to download gradle-wrapper.jar
)
