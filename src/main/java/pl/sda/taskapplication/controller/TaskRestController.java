package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    // HTTP PUT
    // /api/task/{id} --> aktualizuje task o konrektrnym id

    // HTTP PATCH
    // /api/task/{id} --> aktualizuje task o konrektrnym id

    // HTTP DELETE
    // /api/task/{id} --> usuwa task o konkretnym id
}
