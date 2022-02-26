package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/task", produces = { "application/json", "text/xml" })
@CrossOrigin(origins = "*")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    // HTTP GET
    // /api/task --> zwraca wszystkie task

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getAll();
    }

    // /api/task/{id} --> zwraca task o konkretnym id
    // /api/task/1
    // /api/task/200

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") long id) {
        ResponseEntity<TaskDto> response;

        try {
            TaskDto taskDto = taskService.findById(id);
            response = new ResponseEntity<>(taskDto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    // HTTP POST
    // /api/task --> tworzy nowy task

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto postTask(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    // HTTP PUT
    // /api/task/{id} --> aktualizuje task o konrektrnym id

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTask(@PathVariable("id") long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        taskService.update(taskDto);
    }

    // HTTP PATCH
    // /api/task/{id} --> aktualizuje task o konrektrnym id

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchTask(@PathVariable("id") long id, @RequestBody TaskDto taskDto) {
        TaskDto saved = taskService.findById(id);
        taskDto.setId(id);

//        if (!taskDto.getDescription().equals(saved.getDescription())) {
//            saved.setDescription(taskDto.getDescription());
//        }

        if (taskDto.getDescription() != null) {
            saved.setDescription(taskDto.getDescription());
        }

        if (taskDto.getName() != null) {
            saved.setName(taskDto.getName());
        }

        if (taskDto.getType() != null) {
            saved.setType(taskDto.getType());
        }

        taskService.update(saved);
    }

    // HTTP DELETE
    // /api/task/{id} --> usuwa task o konkretnym id

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") long id) {
        try {
            taskService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            // do nothing
        }
    }
}
