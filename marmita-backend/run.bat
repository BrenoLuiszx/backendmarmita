@echo off
echo Compilando e executando o projeto Marmita API...
echo.

if exist "target" (
    echo Limpando build anterior...
    rmdir /s /q target
)

echo Compilando projeto...
javac -cp "src/main/java" -d "target/classes" src/main/java/com/marmita/api/*.java src/main/java/com/marmita/api/*/*.java src/main/java/com/marmita/api/*/*/*.java

if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo Executando aplicacao...
java -cp "target/classes" com.marmita.api.MarmitaApiApplication

pause