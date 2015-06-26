package com.xebialabs.xlrelease;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xebialabs.xlrelease.layout.TaskLayout;

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_view, parent, false);
            ((TaskLayout) convertView).setTask(task);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.taskTitleView);

        // Populate the data into the template view using the data object
        tvName.setText(task.getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
