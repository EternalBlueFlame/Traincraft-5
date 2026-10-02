#!/usr/bin/env bash
# Verify vendored upstream API snapshots against vendored-api.sha256.
# Hashes git blob contents, so line-ending conversion in the working tree
# does not change the result. Exits non-zero on mismatch.
set -uo pipefail

cd "$(git rev-parse --show-toplevel)"

fail=0
while read -r expected tree; do
  [ -z "${expected:-}" ] && continue
  # Tracked files plus untracked ones (so a new API copy is hashed before it
  # is committed), ignoring .gitignore entries, in a stable order.
  files=$( (git ls-files "$tree"; git ls-files --others --exclude-standard "$tree") | sort -u )

  # Hash against the git blob contents, not file contents
  # to avoid newline issues on different operating systems
  manifest=""
  while IFS= read -r f; do
    [ -f "$f" ] || continue
    manifest="${manifest}$(git hash-object "$f")  ${f}"$'\n'
  done <<< "$files"
  actual=$(printf '%s' "$manifest" | sha256sum | cut -d' ' -f1)
  if [ "$actual" != "$expected" ]; then
    echo "::error::${tree} checksum mismatch: expected ${expected}, got ${actual}"
    fail=1
  else
    echo "${tree} OK"
  fi
done < vendored-api.sha256

if [ "$fail" -ne 0 ]; then
  echo "Regenerate vendored-api.sha256 if this change is an intentional upstream re-copy."
fi
exit "$fail"
