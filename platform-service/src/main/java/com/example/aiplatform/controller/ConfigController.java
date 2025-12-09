package com.example.aiplatform.controller;

import com.example.aiplatform.service.ProjectConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final ProjectConfigService projectConfigService;

    public ConfigController(ProjectConfigService projectConfigService) {
        this.projectConfigService = projectConfigService;
    }

    @GetMapping("/project/{repoFullName}")
    public Map<String, Object> getProjectConfig(@PathVariable String repoFullName) {
        return projectConfigService.getProjectConfig(repoFullName);
    }
}
