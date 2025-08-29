@echo off
echo ==========================================
echo Building Bajaj Finserv Health Qualifier
echo ==========================================

echo Step 1: Installing Maven Wrapper if Maven is not available...
if not exist "mvnw.cmd" (
    echo Maven Wrapper not found. You need to install Maven first.
    echo Please install Maven from: https://maven.apache.org/download.cgi
    echo Or use the Spring Boot Maven wrapper.
    pause
    exit /b 1
)

echo Step 2: Building the application...
call mvn clean package -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo ==========================================
echo Build completed successfully!
echo JAR file location: target\finserv-health-qualifier-1.0.0.jar
echo ==========================================

echo To run the application:
echo java -jar target\finserv-health-qualifier-1.0.0.jar

pause
