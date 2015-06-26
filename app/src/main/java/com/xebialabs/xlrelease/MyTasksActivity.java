package com.xebialabs.xlrelease;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.xebialabs.xlrelease.layout.TaskLayout;


public class MyTasksActivity extends Activity {
    private static final String TAG = "MyTasksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);
        updateUI();
    }

    private void updateUI() {
        TaskAdapter tasksAdapter = new TaskAdapter(this);
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

        String taskId = task.getId();

        new CompleteTaskRequest().execute(taskId);
    }
}
