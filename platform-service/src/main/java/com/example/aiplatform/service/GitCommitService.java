package com.example.aiplatform.service;

import com.example.aiplatform.model.GenerationResponse;
import com.example.aiplatform.model.PullRequestEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class GitCommitService {

    private final WebClient webClient;
    private final String githubToken;

    public GitCommitService(
            @Value("${github.api-base-url:https://api.github.com}") String githubApiBaseUrl,
            @Value("${github.token:}") String githubToken,
            WebClient.Builder builder
    ) {
        this.githubToken = githubToken;
        this.webClient = builder.baseUrl(githubApiBaseUrl).build();
    }

    public void commitGeneratedFiles(PullRequestEvent event, GenerationResponse response) {
        if (githubToken == null || githubToken.isBlank()) {
            throw new IllegalStateException("GitHub token not configured (github.token)");
        }
        if (response.getGeneratedFiles() == null || response.getGeneratedFiles().isEmpty()) {
            return;
        }

        String repoFullName = event.getRepoFullName();
        String branch = event.getHeadBranch();

        response.getGeneratedFiles().forEach(file -> {
            String path = file.getPath();
            String content = file.getContent();
            String base64 = Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));

            Map<String, Object> body = Map.of(
                    "message", "chore: add AI-generated tests for PR #" + event.getPullRequestNumber(),
                    "content", base64,
                    "branch", branch
            );

            webClient.put()
                    .uri("/repos/{repo}/contents/{path}", repoFullName, path)
                    .header("Authorization", "Bearer " + githubToken)
                    .header("Accept", "application/vnd.github+json")
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        });
    }
}
