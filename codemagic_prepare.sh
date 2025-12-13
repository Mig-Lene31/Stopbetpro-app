#!/usr/bin/env bash

set -e

echo "🚀 Preparando ambiente Codemagic..."

node -v
npm -v
java -version

echo "📦 Instalando dependências JS..."
npm install

echo "🧹 Limpando cache Android..."
cd android
./gradlew clean || true

echo "✅ Projeto preparado para build no Codemagic"
