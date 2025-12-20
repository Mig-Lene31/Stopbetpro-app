#!/data/data/com.termux/files/usr/bin/bash
echo "🔍 Lendo payment.js (parte 1)..."
sed -n '1,200p' ~/StopBetPro_project/src/services/payment.js

echo "🔍 Lendo payment.js (parte 2)..."
sed -n '200,400p' ~/StopBetPro_project/src/services/payment.js
