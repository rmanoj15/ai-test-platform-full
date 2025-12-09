package com.example.aiplatform.controller;

import com.example.aiplatform.model.*;
import com.example.aiplatform.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orchestrator")
public class OrchestratorController {

    private final DiffService diffService;
    private final ProjectConfigService projectConfigService;
    private final AiGenerationService aiGenerationService;
    private final GitCommitService gitCommitService;

    public OrchestratorController(
            DiffService diffService,
            ProjectConfigService projectConfigService,
            AiGenerationService aiGenerationService,
            GitCommitService gitCommitService
    ) {
        this.diffService = diffService;
        this.projectConfigService = projectConfigService;
        this.aiGenerationService = aiGenerationService;
        this.gitCommitService = gitCommitService;
    }

    @PostMapping("/trigger")
    public ResponseEntity<GenerationResponse> trigger(@RequestBody PullRequestEvent event) {
        DiffContext diffContext = diffService.buildDiffContext(event);
        Map<String, Object> projectConfig = projectConfigService.getProjectConfig(event.getRepoFullName());

        GenerationRequest generationRequest = new GenerationRequest();
        generationRequest.setDiffContext(diffContext);
        generationRequest.setProjectConfig(projectConfig);

        GenerationResponse generationResponse = aiGenerationService.generateTests(generationRequest);

        gitCommitService.commitGeneratedFiles(event, generationResponse);

        return ResponseEntity.accepted().body(generationResponse);
    }
}
