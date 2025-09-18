@echo off
setlocal EnableExtensions EnableDelayedExpansion
title Java Launcher

REM ====== CONFIG ======
set "SRC_DIR=src"
set "OUT_DIR=out"
set "MAIN_CLASS=Main"
REM ====================

REM Aller dans le dossier du script (important si tu doubles-cliques)
cd /d "%~dp0"

echo.
echo ============================================================
echo   Compilation et Lancement Java
echo   SRC = "%SRC_DIR%"
echo   OUT = "%OUT_DIR%"
echo   MAIN= %MAIN_CLASS%
echo ============================================================
echo.

REM Option : clean
if /i "%~1"=="clean" (
  echo [CLEAN] Suppression de "%OUT_DIR%"...
  if exist "%OUT_DIR%" rd /s /q "%OUT_DIR%"
  shift
)

REM Vérifs basiques
if not exist "%SRC_DIR%" (
  echo [ERREUR] Dossier sources "%SRC_DIR%" introuvable.
  pause & exit /b 1
)

where javac >nul 2>&1
if errorlevel 1 (
  echo [ERREUR] "javac" introuvable. Installe le JDK et ajoute %%JAVA_HOME%%\bin au PATH.
  pause & exit /b 1
)

REM Créer OUT si besoin
if not exist "%OUT_DIR%" md "%OUT_DIR%"

REM ====== Construire la liste des sources dans une variable ======
set "ALL_SOURCES="

for /r "%SRC_DIR%" %%F in (*.java) do (
  set "ALL_SOURCES=!ALL_SOURCES! "%%~fF""
)

if not defined ALL_SOURCES (
  echo [ERREUR] Aucun .java trouve sous "%SRC_DIR%".
  pause & exit /b 1
)

echo [JAVAC] Compilation...
REM IMPORTANT : pas d’argfile -> on passe tout directement (fonctionne avec espaces)
call javac -encoding UTF-8 -d "%OUT_DIR%" !ALL_SOURCES!
if errorlevel 1 (
  echo.
  echo ❌ Erreur de compilation.
  pause & exit /b %ERRORLEVEL%
)

echo ✅ Compilation OK.
echo.

REM ====== Construire le classpath : out + sorties IntelliJ si elles existent ======
set "CP=%OUT_DIR%"
for /d %%D in ("%OUT_DIR%\production\*") do set "CP=!CP!;%%~fD"
for /d %%D in ("out\production\*") do set "CP=!CP!;%%~fD"

echo [JAVA] CP = %CP%
echo [RUN] %MAIN_CLASS% %*
echo ------------------------------------------------------------
java -cp "%CP%" %MAIN_CLASS% %*
set "RC=%ERRORLEVEL%"
echo ------------------------------------------------------------
echo Fin (code %RC%).
echo.
pause
exit /b %RC%