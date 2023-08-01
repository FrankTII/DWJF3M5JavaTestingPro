@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  DWJF3M5JavaTestingPro startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and DWJ_F3_M5_JAVA_TESTING_PRO_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\DWJF3M5JavaTestingPro-1.0-SNAPSHOT.jar;%APP_HOME%\lib\cucumber-java-7.13.0.jar;%APP_HOME%\lib\cucumber-junit-7.13.0.jar;%APP_HOME%\lib\cucumber-core-7.13.0.jar;%APP_HOME%\lib\cucumber-expressions-16.1.2.jar;%APP_HOME%\lib\datatable-7.13.0.jar;%APP_HOME%\lib\cucumber-gherkin-messages-7.13.0.jar;%APP_HOME%\lib\cucumber-gherkin-7.13.0.jar;%APP_HOME%\lib\cucumber-plugin-7.13.0.jar;%APP_HOME%\lib\docstring-7.13.0.jar;%APP_HOME%\lib\apiguardian-api-1.1.2.jar;%APP_HOME%\lib\junit-4.13.2.jar;%APP_HOME%\lib\html-formatter-20.3.1.jar;%APP_HOME%\lib\junit-xml-formatter-0.2.0.jar;%APP_HOME%\lib\gherkin-26.2.0.jar;%APP_HOME%\lib\messages-22.0.0.jar;%APP_HOME%\lib\tag-expressions-5.0.1.jar;%APP_HOME%\lib\ci-environment-9.2.0.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar


@rem Execute DWJF3M5JavaTestingPro
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %DWJ_F3_M5_JAVA_TESTING_PRO_OPTS%  -classpath "%CLASSPATH%"  %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable DWJ_F3_M5_JAVA_TESTING_PRO_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%DWJ_F3_M5_JAVA_TESTING_PRO_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
