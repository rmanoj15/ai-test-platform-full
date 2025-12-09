package com.example.aiplatform.controller;

import com.example.aiplatform.model.DiffContext;
import com.example.aiplatform.model.PullRequestEvent;
import com.example.aiplatform.service.DiffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diff")
public class DiffController {

    private final DiffService diffService;

    public DiffController(DiffService diffService) {
        this.diffService = diffService;
    }

    @PostMapping("/context")
    public DiffContext buildDiffContext(@RequestBody PullRequestEvent event) {
        return diffService.buildDiffContext(event);
    }
}
