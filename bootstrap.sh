#!/usr/bin/env bash
set -euo pipefail

echo "=== AI Test Platform bootstrap ==="

if ! command -v java >/dev/null 2>&1; then
  echo "Java is required but not found. Please install Java 17+."
  exit 1
fi

if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven is required but not found. Please install Maven 3.8+."
  exit 1
fi

echo "Building platform-service..."
(
  cd platform-service
  mvn clean package -DskipTests
)

JAR_PATH="platform-service/target/ai-platform-service-1.0.0.jar"

if [ ! -f "$JAR_PATH" ]; then
  echo "Error: JAR not found at $JAR_PATH"
  exit 1
fi

echo "Running platform-service..."
echo "Make sure you have set LLM_API_KEY and GITHUB_TOKEN if you want full functionality."

java -jar "$JAR_PATH"
