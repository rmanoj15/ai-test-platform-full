package com.example.aiplatform.controller;

import com.example.aiplatform.model.GenerationRequest;
import com.example.aiplatform.model.GenerationResponse;
import com.example.aiplatform.service.AiGenerationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generation")
public class AiGenerationController {

    private final AiGenerationService aiGenerationService;

    public AiGenerationController(AiGenerationService aiGenerationService) {
        this.aiGenerationService = aiGenerationService;
    }

    @PostMapping("/generate-tests")
    public GenerationResponse generateTests(@RequestBody GenerationRequest request) {
        return aiGenerationService.generateTests(request);
    }
}
