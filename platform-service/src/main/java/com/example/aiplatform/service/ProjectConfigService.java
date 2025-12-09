package com.example.aiplatform.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProjectConfigService {

    public Map<String, Object> getProjectConfig(String repoFullName) {
        return Map.of(
                "language", "java",
                "automation", Map.of(
                        "api", "enabled",
                        "ui", "enabled",
                        "mobile", "optional"
                ),
                "commitMode", "AUTO_COMMIT",
                "validateBeforeCommit", true
        );
    }
}
