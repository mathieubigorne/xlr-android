package com.xebialabs.xlrelease.ui.tasks.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xebialabs.xlrelease.R;
import com.xebialabs.xlrelease.model.Task;
import com.xebialabs.xlrelease.XLReleaseApplication;
import com.xebialabs.xlrelease.core.adapter.BaseAdapter;
import com.xebialabs.xlrelease.core.api.CompleteTaskRequest;
import com.xebialabs.xlrelease.core.api.XLReleaseApiService;
import com.xebialabs.xlrelease.event.TaskActionEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TaskListingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, TaskPresenter.TaskPresenterCallback {

    @InjectView(R.id.list_tasks)
    ListView listView;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    BaseAdapter<Task, TaskView> adapter;
    TaskPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TaskPresenter(this, XLReleaseApplication.getApiService());
        adapter = new BaseAdapter<>(getActivity(), R.layout.item_view_task, new ArrayList<Task>());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_task, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, view);

        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(R.string.nav_tasks);
        EventBus.getDefault().register(this);

        presenter.list();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onRefresh() {
        presenter.list();
    }

    @Override
    public void onTasksLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onTasksLoaded(List<Task> tasks) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setData(tasks);
    }

    public void onEventMainThread(TaskActionEvent event) {
        switch (event.type) {
            case COMPLETE:
                completeTask(event.task);
                break;
        }
    }

    private void completeTask(Task task) {
        XLReleaseApiService apiService = XLReleaseApplication.getApiService();
        apiService.completeTask(task.getId(), new CompleteTaskRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   Snackbar.make(listView, R.string.snackbar_task_completed, Snackbar.LENGTH_LONG).show();
                                   presenter.list();
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("CompleteTask", "Error on complete task", throwable);
                            }
                        })
        ;
    }
}
