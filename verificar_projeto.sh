#!/usr/bin/env bash
set -e

echo "Verificando estrutura do projeto..."

[ -f package.json ] || { echo "package.json não encontrado"; exit 1; }
[ -f index.js ] || { echo "index.js não encontrado"; exit 1; }
[ -d android ] || { echo "pasta android não encontrada"; exit 1; }

[ -f android/app/src/main/AndroidManifest.xml ] || { echo "AndroidManifest.xml não encontrado"; exit 1; }
[ -f android/app/src/main/java/com/stopbet/app/MainApplication.java ] || { echo "MainApplication.java não encontrado"; exit 1; }

[ -f android/app/src/main/java/com/stopbet/app/androidnative/StopBetModule.kt ] || { echo "StopBetModule.kt não encontrado"; exit 1; }
[ -f android/app/src/main/java/com/stopbet/app/androidnative/StopBetPackage.kt ] || { echo "StopBetPackage.kt não encontrado"; exit 1; }

[ -f src/services/stopEngine.js ] || { echo "stopEngine.js não encontrado"; exit 1; }
[ -f src/services/block.js ] || { echo "block.js não encontrado"; exit 1; }
[ -f src/services/proBlocker.js ] || { echo "proBlocker.js não encontrado"; exit 1; }

echo "Estrutura OK para Codemagic"
