package com.example.exam.domain.model;

public enum Post {
    MANAGER("Manager"),
    DEV("Dev"),
    TEST("Test"),
    DEVOPS("DevOps"),
    TECH_LEAD("Tech Lead");

    private final String displayName;

    Post(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
