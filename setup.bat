:restart
@echo off
cls
@ECHO ##########################################################################
@ECHO.
@ECHO  Gradle setup script for Windows
@ECHO  This is to prepare the source for use with an IDE.
@ECHO  Optionally you may compile the .jar as well.
@ECHO.
@ECHO  Be sure to install JDK 8 for your OS.
@ECHO  Also be sure to set it as your JAVA_HOME.
@ECHO.
@ECHO ##########################################################################
@ECHO.

@ECHO Choose an option:
@ECHO  [1] Eclipse - (Usually does not work)
@ECHO  [2] Idea (IntelliJ) - (Does work)
@ECHO  [3] Build the source
@ECHO.

:tryagain
set /p variable="Enter choice [1-3]: "
IF "%variable%"=="1" goto eclipse
IF /I "%variable%"=="eclipse" goto eclipse

IF "%variable%"=="2" goto intelij
IF /I "%variable%"=="intelij" goto intelij
IF /I "%variable%"=="intellij" goto intelij
IF /I "%variable%"=="idea" goto intelij

IF "%variable%"=="3" goto build
IF /I "%variable%"=="build" goto build

@ECHO Incorrect option, try again.
@ECHO.
goto tryagain


:eclipse
@ECHO.
@ECHO Setting up workspace for Eclipse...
call gradlew.bat setupDecompWorkspace --refresh-dependencies eclipse
if errorlevel 1 goto fail
@ECHO.
@ECHO ##########################################################################
@ECHO.
@ECHO  Mod is ready to be opened in Eclipse.
@ECHO  If prompted for a deobfuscator, select:
@ECHO  %%USERPROFILE%%/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/unpacked/conf
@ECHO.
@ECHO ##########################################################################
pause
goto quit


:intelij
@ECHO.
@ECHO Setting up workspace for IntelliJ IDEA...
call gradlew.bat setupDecompWorkspace --refresh-dependencies idea
if errorlevel 1 goto fail
@ECHO.
@ECHO ##########################################################################
@ECHO.
@ECHO  Mod is ready to be opened in IntelliJ IDEA.
@ECHO  Open via the generated Traincraft-5.ipr or directory import.
@ECHO  If prompted for a deobfuscator, select:
@ECHO  %%USERPROFILE%%/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/unpacked/conf
@ECHO.
@ECHO ##########################################################################
pause
goto quit


:build
@ECHO.
@ECHO Attempting to build jar file...
call gradlew.bat setupDecompWorkspace --refresh-dependencies build
if errorlevel 1 goto fail
@ECHO.
@ECHO ##########################################################################
@ECHO.
@ECHO  Built jar file can be found in build/libs/
@ECHO.
@ECHO ##########################################################################
pause
goto quit


:fail
@ECHO.
@ECHO Gradle failed. See output above.
pause
exit /b 1


:quit
exit
