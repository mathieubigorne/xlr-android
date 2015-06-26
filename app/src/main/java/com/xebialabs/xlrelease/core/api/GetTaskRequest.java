package com.xebialabs.xlrelease.core.api;

public class GetTaskRequest {
    private boolean active = true;
    private boolean assignedToMe = true;
    private boolean assignedToMyTeams = false;
    private boolean assignedToOthers = false;
    private boolean notAssigned = false;
    private String filter = "";

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAssignedToMe() {
        return assignedToMe;
    }

    public void setAssignedToMe(boolean assignedToMe) {
        this.assignedToMe = assignedToMe;
    }

    public boolean isAssignedToMyTeams() {
        return assignedToMyTeams;
    }

    public void setAssignedToMyTeams(boolean assignedToMyTeams) {
        this.assignedToMyTeams = assignedToMyTeams;
    }

    public boolean isAssignedToOthers() {
        return assignedToOthers;
    }

    public void setAssignedToOthers(boolean assignedToOthers) {
        this.assignedToOthers = assignedToOthers;
    }

    public boolean isNotAssigned() {
        return notAssigned;
    }

    public void setNotAssigned(boolean notAssigned) {
        this.notAssigned = notAssigned;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
