package com.example.aiplatform.service;

import com.example.aiplatform.model.DiffContext;
import com.example.aiplatform.model.GenerationRequest;
import com.example.aiplatform.model.GenerationResponse;
import com.example.aiplatform.model.GenerationResponse.GeneratedFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AiGenerationService {

    private final LlmClient llmClient;

    public AiGenerationService(LlmClient llmClient) {
        this.llmClient = llmClient;
    }

    public GenerationResponse generateTests(GenerationRequest request) {
        DiffContext ctx = request.getDiffContext();
        Map<String, Object> config = request.getProjectConfig();

        String systemPrompt = "You are a senior test automation engineer. " +
                "Generate high-quality, compilable test automation code. " +
                "Follow the project's conventions and DO NOT include explanations or comments, " +
                "only valid code for the test files.";

        String language = ctx.getLanguage() != null ? ctx.getLanguage() : "java";
        String testFramework = ctx.getTestFramework() != null ? ctx.getTestFramework() : "testng";

        String userPrompt = String.format(
                "Project language: %s%n" +
                "Test framework: %s%n%n" +
                "Diff summary:%n%s%n%n" +
                "Changed files:%n%s%n%n" +
                "Project config (YAML-like):%n%s%n%n" +
                "Task:%n" +
                "- Generate ONE test file that covers the main changes.%n" +
                "- If this is a backend service, generate API tests.%n" +
                "- Use realistic assertions.%n" +
                "- Return ONLY the full source code of the test file.%n",
                language,
                testFramework,
                safe(ctx.getDiffSummary()),
                String.join(", ", ctx.getChangedFiles() != null ? ctx.getChangedFiles() : List.of()),
                config != null ? config.toString() : "{}"
        );

        String code = llmClient.complete(systemPrompt, userPrompt);

        String path = "automation/api/ai/PR-" + ctx.getPullRequestNumber() + "-AutoTests.java";

        GeneratedFile file = new GeneratedFile();
        file.setPath(path);
        file.setContent(code);
        file.setTestType("API");

        GenerationResponse response = new GenerationResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Generated 1 test file via LLM");
        List<GeneratedFile> files = new ArrayList<>();
        files.add(file);
        response.setGeneratedFiles(files);

        return response;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}
