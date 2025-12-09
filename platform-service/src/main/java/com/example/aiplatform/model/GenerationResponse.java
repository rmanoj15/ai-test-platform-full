package com.example.aiplatform.model;

import java.util.List;

public class GenerationResponse {

    public static class GeneratedFile {
        private String path;
        private String content;
        private String testType;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTestType() {
            return testType;
        }

        public void setTestType(String testType) {
            this.testType = testType;
        }
    }

    private String status;
    private String message;
    private List<GeneratedFile> generatedFiles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GeneratedFile> getGeneratedFiles() {
        return generatedFiles;
    }

    public void setGeneratedFiles(List<GeneratedFile> generatedFiles) {
        this.generatedFiles = generatedFiles;
    }
}
