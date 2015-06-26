package com.xebialabs.xlrelease.ui.tasks.list;

import com.xebialabs.xlrelease.model.Task;
import com.xebialabs.xlrelease.core.api.GetTaskRequest;
import com.xebialabs.xlrelease.core.api.XLReleaseApiService;
import com.xebialabs.xlrelease.core.api.model.ReleaseTasks;
import com.xebialabs.xlrelease.core.api.model.TaskFullView;
import com.xebialabs.xlrelease.core.api.model.TaskSearchView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.google.common.collect.Lists.newArrayList;

public class TaskPresenter {

    private XLReleaseApiService apiService;
    private TaskPresenterCallback callback;

    public TaskPresenter(TaskPresenterCallback callback, XLReleaseApiService apiService) {
        this.callback = callback;
        this.apiService = apiService;
    }

    public void list() {
        callback.onTasksLoading();

        apiService.getTasks(100, new GetTaskRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<TaskSearchView, List<Task>>() {
                    @Override
                    public List<Task> call(TaskSearchView taskSearchView) {
                        List<Task> tasks = newArrayList();
                        for (ReleaseTasks releaseTasks : taskSearchView.getReleaseTasks()) {
                            for (TaskFullView taskFullView : releaseTasks.getTasks()) {
                                tasks.add(new Task(taskFullView.getId(), taskFullView.getTitle(), taskFullView.getStatus()));
                            }
                        }

                        return tasks;
                    }
                })
                .subscribe(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        callback.onTasksLoaded(tasks);
                    }
                });
    }

    public interface TaskPresenterCallback{
        void onTasksLoading();
        void onTasksLoaded(List<Task> tasks);
    }
}
