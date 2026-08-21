# Contributing to Traincraft

All contributors must sign the Contributor License Agreement (CLA) **[here](https://cla-assistant.io/Mrbrutal/Traincraft)** before pull requests can be merged.

---

## Prerequisites

- **JDK 8 (strictly required):** The ForgeGradle 1.2 toolchain does not support Java 9+. Verify with `java -version`.
- **7-Zip (`p7zip`):** Required to extract the pre-packaged dependency cache.

---

## Quick Setup

### 1. Extract Dependency Cache
The repo includes cached dependencies in `gradle/caches.7z.*` for offline reproducibility.

- **Linux / macOS:**
  ```bash
  mkdir -p ~/.gradle/caches && cd ~/.gradle/caches
  7z x /path/to/Traincraft-5/gradle/caches.7z.001 -y
  ```
- **Windows:** Extract `gradle/caches.7z.001` to `%USERPROFILE%\.gradle\caches\` using 7-Zip.

### 2. Setup Workspace & IDE
Run the setup script for your platform:

- **Linux / macOS:** `./setup.sh` (or `./gradlew setupDecompWorkspace --refresh-dependencies idea`)
- **Windows:** `setup.bat` (or `gradlew.bat setupDecompWorkspace --refresh-dependencies idea`)

### 3. Open in IDE
- **IntelliJ IDEA (recommended):** Open `Traincraft-5.ipr` (or the root directory). Set Project SDK to Java 1.8.
- **Eclipse:** Run `./gradlew setupDecompWorkspace --refresh-dependencies eclipse` and import as an existing project.

*Deobfuscator path (if prompted by IDE):*  
`~/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/unpacked/conf`

---

## Development Workflow

| Task | Linux / macOS | Windows |
| :--- | :--- | :--- |
| **Run Client** | `./gradlew runClient` | `gradlew.bat runClient` |
| **Run Server** | `./gradlew runServer` | `gradlew.bat runServer` |
| **Build JAR** | `./gradlew build` | `gradlew.bat build` |

Output JAR is generated in `build/libs/`.

---

## Coding Guidelines

- **Java 7 bytecode target:** Do not use Java 8+ features (lambdas, streams, `java.time`).
- **Client/Server separation:** Game logic runs server-side (`!worldObj.isRemote`). Rendering and client GUIs belong in `train.client` or behind proxies.
- **Item stacks:** Always use `ItemStack.copy()` when duplicating or moving stacks to prevent inventory desync/dupe bugs.
- **Skins:** Train textures live in `src/main/resources/assets/tc/textures/trains/` (`.png`).

---

## Submitting a Pull Request

1. **Base branch:** Always branch off **`TC4.5-1.7.10`**:
   ```bash
   git checkout -b my-fix-or-feature origin/TC4.5-1.7.10
   ```
2. **Test:** Verify `./gradlew build` passes.
3. **Push to fork:**
   ```bash
   git push -u fork my-fix-or-feature
   ```
4. **Open PR:** Target `EternalBlueFlame/Traincraft-5` branch `TC4.5-1.7.10`. Reference any relevant issue numbers (e.g., `Closes #123`).

---

## Community

- **Discord:** [Join the Traincraft Discord](https://discord.gg/SgpnCnK)
- **Issues:** [GitHub Issue Tracker](https://github.com/EternalBlueFlame/Traincraft-5/issues)
