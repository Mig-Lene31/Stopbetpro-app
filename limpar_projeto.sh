#!/usr/bin/env bash

echo "🧹 Limpando arquivos inúteis..."

cd ~/StopBetPro_FINAL_BUILD/front 2>/dev/null
rm -f *.pdf *.txt *.epub *.doc *.docx *.mobi *.html livros* book* a*livro* || true

cd ~/StopBetPro_FINAL_BUILD/backend 2>/dev/null
rm -f *.pdf *.txt *.epub *.doc *.docx *.mobi *.html livros* book* a*livro* || true

echo "✔ Limpeza concluída!"
