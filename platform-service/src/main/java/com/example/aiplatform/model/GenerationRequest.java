package com.example.aiplatform.model;

import java.util.Map;

public class GenerationRequest {
    private DiffContext diffContext;
    private Map<String, Object> projectConfig;

    public DiffContext getDiffContext() {
        return diffContext;
    }

    public void setDiffContext(DiffContext diffContext) {
        this.diffContext = diffContext;
    }

    public Map<String, Object> getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(Map<String, Object> projectConfig) {
        this.projectConfig = projectConfig;
    }
}
