package com.xebialabs.xlrelease.ui.tasks.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.xebialabs.xlrelease.R;
import com.xebialabs.xlrelease.model.Task;
import com.xebialabs.xlrelease.core.ui.view.BaseItemView;
import com.xebialabs.xlrelease.event.TaskActionEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import static com.xebialabs.xlrelease.event.TaskActionEvent.ActionType.COMPLETE;

public class TaskView extends LinearLayout implements BaseItemView<Task>{

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.instance_swipe_layout) SwipeLayout swipeView;
    @InjectView(R.id.task_action) ViewGroup actionView;

    private Task task;

    public TaskView(Context context) {
        super(context);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.inject(this);
    }

    @Override
    public void bindView(Task task) {
        if(swipeView != null){
            setupSwipe();
        }
        this.task = task;

        title.setText(task.getTitle());
    }

    private void setupSwipe() {
        swipeView.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeView.addDrag(SwipeLayout.DragEdge.Right, actionView);
    }

    @OnClick(R.id.task_complete)
    public void complete(){
        EventBus.getDefault().post(new TaskActionEvent(task, COMPLETE));
        swipeView.close();
    }
}
