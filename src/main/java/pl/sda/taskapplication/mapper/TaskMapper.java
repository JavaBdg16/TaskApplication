package pl.sda.taskapplication.mapper;

import org.springframework.stereotype.Service;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.entity.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto map(Task task) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        TaskDto dto = new TaskDto();
        dto.setDescription(task.getDescription());
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setType(task.getType());
        dto.setCreatedAt(dateFormat.format(task.getCreatedAt()));

        return dto;
    }

    public static List<TaskDto> map(Iterable<Task> tasks) {

        List<TaskDto> result = new ArrayList<>();
        for (Task task : tasks) {
            result.add(map(task));
        }

        return result;
    }

    /*
    1. zapisujemy nowego taska - createdAt <-- new Date()
    2. zapisujemy istniejącego taska - createdAt się nie zmienia
     */
    public static Task map(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setType(dto.getType());

        return task;
    }
}
