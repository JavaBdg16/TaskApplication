package pl.sda.taskapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.entity.User;
import pl.sda.taskapplication.mapper.TaskMapper;
import pl.sda.taskapplication.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public TaskDto save(TaskDto taskDto) {
        Task task = TaskMapper.map(taskDto);

//        Authentication authentication =
//                SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();

        // task.setUser(user);

        // TODO: fix it!!!!!!!
        Optional<Task> task2 = taskRepository.findById(task.getId());
        if (task2.isPresent()) {
            Date ldt = task2.get().getCreatedAt();
            task.setCreatedAt(ldt);
        }

        task = taskRepository.save(task);

        return TaskMapper.map(task);
    }

    public TaskDto findById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        TaskDto taskDto = TaskMapper.map(task);

        return taskDto;
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }
}
