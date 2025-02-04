@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM generate a list of all Java files into sources.txt
dir /b /s ..\src\main\java\*.java > sources.txt

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin @sources.txt
IF ERRORLEVEL 1 (
    echo BUILD FAILURE
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin timitomo.ui.Timitomo < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

del sources.txt
if exist .\data (
    rmdir /q /s .\data
)
