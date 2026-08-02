#!/bin/bash

echo "Procurando arquivos .java..."
files=$(find . -name "*.java")

echo "1. Corrigindo declarações de parâmetro 'World getWorld()' → 'World world'"
for f in $files; do
    sed -i 's/World getWorld()/World world/g' "$f"
    sed -i 's/World  getWorld()/World world/g' "$f"   # caso tenha espaço extra
done

echo "2. Trocando chamadas getWorld(). → world."
for f in $files; do
    sed -i 's/getWorld()\./world./g' "$f"
done

echo "3. Limpando possíveis espaços estranhos"
for f in $files; do
    sed -i 's/world\. \./world./g' "$f"
done

echo ""
echo "Pronto. Agora roda o comando abaixo pra ver o que mudou:"
echo "git diff --stat"
echo ""
echo "Revisa especialmente:"
echo "  - ClientProxy.java"
echo "  - Arquivos de Entity / TileEntity"
echo "  - Qualquer lugar que realmente tivesse um método getWorld()"
