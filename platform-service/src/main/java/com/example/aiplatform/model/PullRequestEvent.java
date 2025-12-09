package com.example.aiplatform.model;

import java.util.List;

public class PullRequestEvent {
    private String repoFullName;
    private Integer pullRequestNumber;
    private String baseBranch;
    private String headBranch;
    private String commitMode;
    private List<String> requestedTestTypes;

    public String getRepoFullName() {
        return repoFullName;
    }

    public void setRepoFullName(String repoFullName) {
        this.repoFullName = repoFullName;
    }

    public Integer getPullRequestNumber() {
        return pullRequestNumber;
    }

    public void setPullRequestNumber(Integer pullRequestNumber) {
        this.pullRequestNumber = pullRequestNumber;
    }

    public String getBaseBranch() {
        return baseBranch;
    }

    public void setBaseBranch(String baseBranch) {
        this.baseBranch = baseBranch;
    }

    public String getHeadBranch() {
        return headBranch;
    }

    public void setHeadBranch(String headBranch) {
        this.headBranch = headBranch;
    }

    public String getCommitMode() {
        return commitMode;
    }

    public void setCommitMode(String commitMode) {
        this.commitMode = commitMode;
    }

    public List<String> getRequestedTestTypes() {
        return requestedTestTypes;
    }

    public void setRequestedTestTypes(List<String> requestedTestTypes) {
        this.requestedTestTypes = requestedTestTypes;
    }
}
