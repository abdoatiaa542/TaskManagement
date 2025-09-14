package com.example.task_management_api.service.task;

import com.example.task_management_api.dto.ApiResponse;
import com.example.task_management_api.dto.task.TaskRequest;
import com.example.task_management_api.dto.task.TaskResponse;
import com.example.task_management_api.exception.ResourceNotFoundException;
import com.example.task_management_api.mapper.TaskMapper;
import com.example.task_management_api.model.Task;
import com.example.task_management_api.model.User;
import com.example.task_management_api.repository.TaskRepository;
import com.example.task_management_api.repository.UserRepository;
import com.example.task_management_api.utils.ContextHolderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse createTask(TaskRequest request) {
        User user = ContextHolderUtils.getUser();
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(Task.Status.valueOf(request.getStatus()))
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);
        TaskResponse responen = TaskMapper.toResponse(savedTask);

        return ApiResponse.of("Task created successfully", responen);
    }

    @Override
    public ApiResponse getTasks() {
        User user = ContextHolderUtils.getUser();
        List<TaskResponse> responses = taskRepository.findByUser(user).stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
        if (responses.isEmpty()) {
            return ApiResponse.of("No tasks found");
        }
        return ApiResponse.of("Tasks fetched successfully", responses);
    }

    @Override
    public ApiResponse updateTask(Long taskId, TaskRequest request) {
        User user = ContextHolderUtils.getUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Forbidden: Cannot update another user's task");
        }
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            task.setStatus(Task.Status.valueOf(request.getStatus().toUpperCase()));
        }

//        task.setStatus(Task.Status.valueOf(request.getStatus().toUpperCase()));
        Task updatedTask = taskRepository.save(task);
        TaskResponse response = TaskMapper.toResponse(updatedTask);

        return ApiResponse.of("Task updated successfully", response);
    }


    @Override
    public ApiResponse deleteTask(Long taskId) {
        User user = ContextHolderUtils.getUser();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Forbidden: Cannot delete another user's task");
        }

        taskRepository.delete(task);

        return ApiResponse.of("Task deleted successfully");
    }


}