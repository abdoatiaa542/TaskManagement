package com.example.task_management_api.service.task;

import com.example.task_management_api.dto.ApiResponse;
import com.example.task_management_api.dto.task.TaskRequest;

public interface TaskService {

    ApiResponse createTask(TaskRequest request);

    ApiResponse getTasks();

    ApiResponse updateTask(Long taskId, TaskRequest request);

    ApiResponse deleteTask(Long taskId);
}
