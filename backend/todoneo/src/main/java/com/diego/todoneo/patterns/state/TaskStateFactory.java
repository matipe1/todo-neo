package com.diego.todoneo.patterns.state;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.diego.todoneo.models.enums.TaskStatus;

@Component
public class TaskStateFactory {
    private final Map<TaskStatus, TaskState> stateMap;

    // It map enums to their corresponding singleton state beans injected
    public TaskStateFactory(TodoState todoState,
                            InProgressState inProgressState,
                            DoneState doneState,
                            BacklogState backlogState,
                            CanceledState canceledState,
                            OverdueState overdueState) {
        this.stateMap = Map.of(
            TaskStatus.TODO, todoState,
            TaskStatus.IN_PROGRESS, inProgressState,
            TaskStatus.DONE, doneState,
            TaskStatus.BACKLOG, backlogState,
            TaskStatus.CANCELED, canceledState,
            TaskStatus.OVERDUE, overdueState
        );
    }

    public TaskState getState(TaskStatus status) {
        return stateMap.get(status);
    }
}
