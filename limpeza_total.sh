#!/usr/bin/env bash
set -e

echo "🧹 Iniciando limpeza profunda do projeto..."

# === React / Node ===
echo "➡ Removendo node_modules e caches JS"
rm -rf node_modules
rm -rf .expo
rm -rf .metro-cache
rm -rf npm-debug.log
rm -rf yarn.lock

# === Android build lixo ===
echo "➡ Limpando builds Android"
rm -rf android/.gradle
rm -rf android/app/build
rm -rf android/build

# === Java/Kotlin órfãos (CRÍTICO) ===
echo "➡ Removendo código Android nativo órfão"
find android/app/src/main/java -type f ! -name "MainActivity.java" -delete
find android/app/src/main/java -type d -empty -delete

# === XML órfãos ===
echo "➡ Removendo XML de serviços quebrados"
rm -rf android/app/src/main/res/xml
rm -rf android/app/src/main/res/raw

# === Garantir pastas mínimas ===
mkdir -p android/app/src/main/java/com/stopbet/app
mkdir -p android/app/src/main/res/values

# === Strings mínimas ===
cat > android/app/src/main/res/values/strings.xml << 'STR'
<resources>
    <string name="app_name">StopBet Pro</string>
</resources>
STR

echo "✅ Limpeza concluída com sucesso."
