#!/bin/bash
# Pegasus-FE Android release 打包脚本（AGP 8.13 + 16KB page size）
# 用法: ./build_release_16k.sh
# 产物: pegasus-fe_android-studio-release.apk（签名 + 16KB 对齐）
#
# 说明: AGP 8.3+ 在打包时自动对 .so 做未压缩 + 16KB 对齐存储，
#       无需再手动 zipalign -P 16。本脚本保留最终验证。

set -e

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
ANDROID_PROJ="$PROJECT_DIR/src/app/platform/android"
KEYSTORE="$PROJECT_DIR/.github/workflows/sign_xl.jks"
BT35="/Users/mac1/Library/Android/sdk/build-tools/35.0.0"
JAVA_HOME_17="/Users/mac1/Library/Java/JavaVirtualMachines/jbr-17.0.14/Contents/Home"

echo "==> 1/2 构建 release APK (Gradle + AGP 8.13)"
cd "$ANDROID_PROJ"
export JAVA_HOME="$JAVA_HOME_17"
./gradlew assembleRelease --no-daemon

echo "==> 2/2 复制到项目根目录并验证"
cp build/outputs/apk/release/android-release.apk \
   "$PROJECT_DIR/pegasus-fe_android-studio-release.apk"

"$BT35/apksigner" verify --print-certs \
  "$PROJECT_DIR/pegasus-fe_android-studio-release.apk" | head -3

# AGP 8.13 已自动 16KB 对齐存储，验证通过即完成
"$BT35/zipalign" -c -P 16 -v 4 \
  "$PROJECT_DIR/pegasus-fe_android-studio-release.apk" | tail -1

echo "==> 完成: $PROJECT_DIR/pegasus-fe_android-studio-release.apk"
