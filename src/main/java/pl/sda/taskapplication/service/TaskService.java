package pl.sda.taskapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.mapper.TaskMapper;
import pl.sda.taskapplication.repository.TaskRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getAll() {
        Iterable<Task> tasks = taskRepository.findAll();

        // Optional business logic

        List<TaskDto> taskDtos = TaskMapper.map(tasks);
        return taskDtos;
    }

    public void save(TaskDto taskDto) {
        Task task = TaskMapper.map(taskDto);
        taskRepository.save(task);
    }

    public TaskDto findById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        TaskDto taskDto = TaskMapper.map(task);

        return taskDto;
    }
}
