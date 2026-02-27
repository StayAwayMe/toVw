@echo off
echo Cleaning Gradle cache...
gradlew.bat --stop
rd /s /q .gradle 2>nul
rd /s /q build 2>nul
echo.
echo Building plugin...
gradlew.bat clean buildPlugin --no-daemon
