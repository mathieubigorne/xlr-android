package com.xebialabs.xlrelease.core.api;

import com.xebialabs.xlrelease.core.api.model.TaskSearchView;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface XLReleaseApiService {
    @POST("/tasks/search")
    Observable<TaskSearchView> getTasks(@Query("limitTasksHint") int limitTasksHint, @Body GetTaskRequest request);

    @POST("/tasks/{taskId}/complete")
    Observable<String> completeTask(@Path("taskId") String taskId, @Body CompleteTaskRequest completeTaskRequest);

    @GET("/profile")
    Observable<Response> login();
}

