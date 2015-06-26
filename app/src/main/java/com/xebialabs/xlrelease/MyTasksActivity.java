package com.xebialabs.xlrelease;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.xebialabs.xlrelease.layout.TaskLayout;


public class MyTasksActivity extends Activity {
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);
        updateUI();
    }

    public void updateUI() {
        this.tasksAdapter = new TasksAdapter(this);
        ListView listView = (ListView) findViewById(R.id.tasks_list);
        listView.setAdapter(tasksAdapter);

        new GetTasksRequest(tasksAdapter).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCompleteButtonClick(View view) {
        Task task = ((TaskLayout) view.getParent()).getTask();
        final String taskId = task.getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a comment");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Complete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String comment = inputField.getText().toString();

                        new CompleteTaskRequest(tasksAdapter, comment).execute(taskId);
                    }
                });

        builder.setNegativeButton("Cancel", null);

        builder.create().show();
    }
}
