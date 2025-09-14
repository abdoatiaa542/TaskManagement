package com.example.task_management_api.mapper;

import com.example.task_management_api.dto.task.TaskResponse;
import com.example.task_management_api.model.Task;

public class TaskMapper {

    public static TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .build();
    }
}