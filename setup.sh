#!/usr/bin/env bash

# Check Java version (warning only)
JAVA_VER=$(javac -version 2>&1 | head -n 1)
if [[ ! "$JAVA_VER" =~ "1.8" ]]; then
    echo "Warning: Java 8 (JDK 1.8) is required for building Forge 1.7.10."
    echo "Current version detected: $JAVA_VER"
    echo "Please ensure Java 8 is active in your environment."
    echo ""
fi

while true; do
    echo "##########################################################################"
    echo " Gradle setup script for Linux/macOS"
    echo " This is to prepare the source for use with an IDE."
    echo " Optionally you may compile the .jar as well."
    echo "##########################################################################"
    echo ""
    echo "Choose an option:"
    echo " [1] Eclipse - (Usually does not work)"
    echo " [2] Idea (IntelliJ) - (Does work)"
    echo " [3] Build the source"
    echo ""

    read -rp "Enter choice [1-3]: " choice
    case "${choice,,}" in
        1|eclipse)
            echo "Setting up workspace for Eclipse..."
            if ./gradlew setupDecompWorkspace --refresh-dependencies eclipse; then
                echo ""
                echo "##########################################################################"
                echo " Mod is ready to be opened in Eclipse."
                echo " If prompted for a deobfuscator, select:"
                echo " ~/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/unpacked/conf"
                echo "##########################################################################"
            else
                echo ""
                echo "Gradle failed. See output above."
                exit 1
            fi
            break
            ;;
        2|idea|intellij|intelij)
            echo "Setting up workspace for IntelliJ IDEA..."
            if ./gradlew setupDecompWorkspace --refresh-dependencies idea; then
                echo ""
                echo "##########################################################################"
                echo " Mod is ready to be opened in IntelliJ IDEA."
                echo " Open via the generated Traincraft-5.ipr or directory import."
                echo " If prompted for a deobfuscator, select:"
                echo " ~/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10/unpacked/conf"
                echo "##########################################################################"
            else
                echo ""
                echo "Gradle failed. See output above."
                exit 1
            fi
            break
            ;;
        3|build)
            echo "Attempting to build jar file..."
            if ./gradlew setupDecompWorkspace --refresh-dependencies build; then
                echo ""
                echo "##########################################################################"
                echo " Built jar file can be found in build/libs/"
                echo "##########################################################################"
            else
                echo ""
                echo "Gradle failed. See output above."
                exit 1
            fi
            break
            ;;
        *)
            echo "Incorrect option, try again."
            echo ""
            ;;
    esac
done
