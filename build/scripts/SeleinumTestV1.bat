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

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  SeleinumTestV1 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SELEINUM_TEST_V1_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\SeleinumTestV1-1.0-SNAPSHOT.jar;%APP_HOME%\lib\selenium-java-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-firefox-xpi-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-firefox-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-support-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-chrome-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\poi-ooxml-4.1.2.jar;%APP_HOME%\lib\poi-4.1.2.jar;%APP_HOME%\lib\jackson-dataformat-csv-2.11.0.jar;%APP_HOME%\lib\opencsv-3.7.jar;%APP_HOME%\lib\selenium-edge-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-edgehtml-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-ie-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-opera-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-safari-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-chromium-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-devtools-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-remote-driver-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-http-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-json-4.0.0-alpha-6.jar;%APP_HOME%\lib\selenium-api-4.0.0-alpha-6.jar;%APP_HOME%\lib\commons-codec-1.13.jar;%APP_HOME%\lib\commons-collections4-4.4.jar;%APP_HOME%\lib\commons-math3-3.6.1.jar;%APP_HOME%\lib\SparseBitSet-1.2.jar;%APP_HOME%\lib\poi-ooxml-schemas-4.1.2.jar;%APP_HOME%\lib\commons-compress-1.19.jar;%APP_HOME%\lib\curvesapi-1.06.jar;%APP_HOME%\lib\jackson-databind-2.11.0.jar;%APP_HOME%\lib\jackson-annotations-2.11.0.jar;%APP_HOME%\lib\jackson-core-2.11.0.jar;%APP_HOME%\lib\commons-lang3-3.3.2.jar;%APP_HOME%\lib\auto-service-1.0-rc6.jar;%APP_HOME%\lib\auto-service-annotations-1.0-rc6.jar;%APP_HOME%\lib\opentelemetry-sdk-0.4.0.jar;%APP_HOME%\lib\auto-common-0.10.jar;%APP_HOME%\lib\guava-29.0-jre.jar;%APP_HOME%\lib\okhttp-4.5.0.jar;%APP_HOME%\lib\okio-jvm-2.6.0.jar;%APP_HOME%\lib\async-http-client-2.12.1.jar;%APP_HOME%\lib\netty-reactive-streams-2.0.4.jar;%APP_HOME%\lib\opentelemetry-api-0.4.0.jar;%APP_HOME%\lib\opentelemetry-context-prop-0.4.0.jar;%APP_HOME%\lib\grpc-context-1.28.0.jar;%APP_HOME%\lib\reactor-netty-0.9.6.RELEASE.jar;%APP_HOME%\lib\netty-codec-http2-4.1.48.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.49.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.49.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.49.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.49.Final.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.49.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.49.Final.jar;%APP_HOME%\lib\netty-handler-4.1.49.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-4.1.49.Final.jar;%APP_HOME%\lib\netty-transport-4.1.49.Final.jar;%APP_HOME%\lib\async-http-client-netty-utils-2.12.1.jar;%APP_HOME%\lib\netty-buffer-4.1.49.Final.jar;%APP_HOME%\lib\reactor-core-3.3.5.RELEASE.jar;%APP_HOME%\lib\byte-buddy-1.10.9.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\xmlbeans-3.1.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-2.11.1.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.70.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.70.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\netty-resolver-4.1.49.Final.jar;%APP_HOME%\lib\netty-common-4.1.49.Final.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\javax.activation-1.2.0.jar;%APP_HOME%\lib\annotations-13.0.jar

@rem Execute SeleinumTestV1
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SELEINUM_TEST_V1_OPTS%  -classpath "%CLASSPATH%" PG1 %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SELEINUM_TEST_V1_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SELEINUM_TEST_V1_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
