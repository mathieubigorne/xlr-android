package com.xebialabs.xlrelease.event;

import com.xebialabs.xlrelease.model.Task;

public class TaskActionEvent {
    public Task task;
    public ActionType type;

    public TaskActionEvent(Task task, ActionType type) {
        this.task = task;
        this.type = type;
    }

    public enum ActionType {
        COMPLETE
    }
}
