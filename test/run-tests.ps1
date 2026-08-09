$ErrorActionPreference = "Stop"

$projectRoot = Split-Path -Parent $PSScriptRoot
$junitJar = Join-Path $PSScriptRoot "lib/junit-platform-console-standalone-1.11.4.jar"
$mainOutput = Join-Path $PSScriptRoot "build/main"
$testOutput = Join-Path $PSScriptRoot "build/test"

if (-not (Test-Path -LiteralPath $junitJar)) {
    throw "JUnit library not found: $junitJar"
}

New-Item -ItemType Directory -Force -Path $mainOutput, $testOutput | Out-Null

$mainSources = Get-ChildItem -Path (Join-Path $projectRoot "src/main/java") -Recurse -Filter "*.java" |
    ForEach-Object { $_.FullName }
$testSources = Get-ChildItem -Path (Join-Path $PSScriptRoot "java") -Recurse -Filter "*.java" |
    ForEach-Object { $_.FullName }

javac -encoding UTF-8 -d $mainOutput $mainSources
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

javac -encoding UTF-8 -cp "$mainOutput;$junitJar" -d $testOutput $testSources
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

java -jar $junitJar execute --class-path "$mainOutput;$testOutput" --scan-class-path
exit $LASTEXITCODE
