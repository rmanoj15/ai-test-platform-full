package com.example.aiplatform.service;

import com.example.aiplatform.model.DiffContext;
import com.example.aiplatform.model.PullRequestEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiffService {

    public DiffContext buildDiffContext(PullRequestEvent event) {
        DiffContext ctx = new DiffContext();
        ctx.setRepoFullName(event.getRepoFullName());
        ctx.setPullRequestNumber(event.getPullRequestNumber());
        ctx.setDiffSummary("Stub diff summary for PR " + event.getPullRequestNumber());
        ctx.setChangedFiles(List.of("src/main/Example.java"));
        ctx.setLanguage("java");
        ctx.setTestFramework("testng");
        return ctx;
    }
}
