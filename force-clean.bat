@echo off
echo Stopping all Gradle daemons...
gradlew.bat --stop

echo Waiting 2 seconds...
timeout /t 2 /nobreak > nul

echo Deleting build directory...
rd /s /q build 2>nul

if exist build (
    echo Build directory still exists, trying to delete locked files...
    rmdir /s /q build
)

echo.
echo Building plugin...
gradlew.bat buildPlugin --no-daemon
