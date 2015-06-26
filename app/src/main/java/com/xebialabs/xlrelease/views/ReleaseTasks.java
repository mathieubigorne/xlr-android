package com.xebialabs.xlrelease.views;

import java.util.List;

public class ReleaseTasks {

    private String id;

    private String title;

    private String status;

    private List<TaskFullView> tasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TaskFullView> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskFullView> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "ReleaseTasks{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}


