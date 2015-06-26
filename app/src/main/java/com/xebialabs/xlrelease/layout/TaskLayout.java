package com.xebialabs.xlrelease.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xebialabs.xlrelease.Task;

public class TaskLayout extends RelativeLayout {
    private Task task;


    public TaskLayout(Context context) {
        super(context);
    }

    public TaskLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TaskLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
