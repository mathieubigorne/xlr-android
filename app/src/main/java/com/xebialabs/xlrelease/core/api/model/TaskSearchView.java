package com.xebialabs.xlrelease.core.api.model;

import java.util.List;

public class TaskSearchView {
    private List<ReleaseTasks> releaseTasks;
    private boolean limitReached;

    public List<ReleaseTasks> getReleaseTasks() {
        return releaseTasks;
    }

    public void setReleaseTasks(List<ReleaseTasks> releaseTasks) {
        this.releaseTasks = releaseTasks;
    }

    public boolean isLimitReached() {
        return limitReached;
    }

    public void setLimitReached(boolean limitReached) {
        this.limitReached = limitReached;
    }

    @Override
    public String toString() {
        return "TaskSearchView{" +
                "releaseTasks=" + releaseTasks +
                ", limitReached=" + limitReached +
                '}';
    }
}
