# AI Test Platform

An enterprise-style **AI-assisted test automation platform** that:

- Listens to **Pull Requests** (PRs)
- Builds a **diff context**
- Uses an **LLM** to generate automation tests (API / UI / Mobile)
- **Commits** generated tests back into the PR branch via GitHub API
- Is deployable via **Docker** + **docker-compose**

> This repo is a scaffold to be extended by your team and GitHub Copilot. Most logic is intentionally simple or stubbed.

## 1. High-Level Flow

1. Developer raises or updates a PR.
2. GitHub Action: `.github/workflows/ai-test-generation.yml` triggers.
3. Action calls the `platform-service` endpoint:  
   `POST /api/orchestrator/trigger`
4. The `platform-service`:
   - Builds a **diff context** (currently stubbed).
   - Loads project config (stub).
   - Calls an **LLM** to generate test code.
   - Uses **GitHub REST API** to commit generated tests into the PR branch.
5. CI pipelines (not included here) can then run those tests.

## 2. Repository Layout

```text
.
├─ .github/
│  └─ workflows/
│     └─ ai-test-generation.yml        # Calls /api/orchestrator/trigger on PRs
│
├─ docs/
│  ├─ architecture.md                  # Architecture description
│  └─ api-contracts.yaml               # OpenAPI for platform-service
│
├─ automation/
│  ├─ api/
│  │  └─ payments/
│  │     └─ Payments_AutoTests.java    # Example API test
│  ├─ ui/
│  │  └─ web/
│  │     └─ Checkout_AutoTests.spec.ts # Example Playwright test
│  └─ mobile/
│     └─ android/
│        └─ Login_AutoTests.java       # Example Appium test
│
├─ platform-service/
│  ├─ pom.xml
│  ├─ Dockerfile
│  └─ src/
│     ├─ main/
│     │  ├─ java/com/example/aiplatform/
│     │  │  ├─ AiPlatformApplication.java
│     │  │  ├─ controller/
│     │  │  │  ├─ OrchestratorController.java
│     │  │  │  ├─ DiffController.java
│     │  │  │  ├─ AiGenerationController.java
│     │  │  │  ├─ ConfigController.java
│     │  │  │  └─ HealthController.java
│     │  │  ├─ model/
│     │  │  │  ├─ PullRequestEvent.java
│     │  │  │  ├─ DiffContext.java
│     │  │  │  ├─ GenerationRequest.java
│     │  │  │  └─ GenerationResponse.java
│     │  │  └─ service/
│     │  │     ├─ LlmClient.java
│     │  │     ├─ AiGenerationService.java
│     │  │     ├─ GitCommitService.java
│     │  │     ├─ DiffService.java
│     │  │     └─ ProjectConfigService.java
│     │  └─ resources/
│     │     └─ application.yml
│     └─ test/
│        └─ java/com/example/aiplatform/
│           └─ AiPlatformApplicationTests.java
│
├─ docker-compose.yml
└─ bootstrap.sh
```

## 3. Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **Docker & docker-compose** (for containerized run)
- A **GitHub PAT** or GitHub App token with `repo` scope for committing files.

## 4. Environment Variables

For local/dev:

```bash
export LLM_API_KEY="your-llm-api-key"
export LLM_BASE_URL="https://api.openai.com"
export LLM_MODEL="gpt-4.1-mini"

export GITHUB_TOKEN="your-github-pat-or-app-token"
export GITHUB_API_BASE_URL="https://api.github.com"
```

## 5. Running Locally (without Docker)

```bash
./bootstrap.sh
```

This will:

1. Build `platform-service`
2. Run it on port `8080`.

You can check health:

```bash
curl http://localhost:8080/actuator/health
```

## 6. Running via Docker Compose

```bash
docker-compose up --build
```

## 7. Wiring With GitHub Actions

In your app repo, configure secrets:

- `AI_PLATFORM_BASE_URL`
- `AI_PLATFORM_API_KEY` (optional if you add auth to this service)

The workflow `.github/workflows/ai-test-generation.yml` calls:

`POST {AI_PLATFORM_BASE_URL}/api/orchestrator/trigger`

## 8. Extending the Platform

Extend:

- `DiffService` – real Git diffs
- `ProjectConfigService` – .ai-tests.yml or DB config
- `AiGenerationService` – domain-specific prompts
- `GitCommitService` – provider-agnostic Git integrations
