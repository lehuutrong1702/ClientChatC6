package com.teamc6.chatSystem.model;

public class Relationship {
    private long relationshipId;
    private String relationshipName;

    public Relationship(long relationshipId, String relationshipName) {
        this.relationshipId = relationshipId;
        this.relationshipName = relationshipName;
    }

    public long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }
}
