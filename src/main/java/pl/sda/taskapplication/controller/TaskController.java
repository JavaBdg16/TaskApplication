package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.sda.taskapplication.dto.CommentDto;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.dto.UserDto;
import pl.sda.taskapplication.entity.User;
import pl.sda.taskapplication.service.TaskService;
import pl.sda.taskapplication.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // wyświetl wszystkie zadania
    // GET localhost:8080/task
    // dlaczego "/" psuł????
    @GetMapping
    public String showTasks(Model model/*, @AuthenticationPrincipal User user */) {

        // UserDto userDto = userService.getUserByUsername(principal.getName());

        model.addAttribute("modelTaskList", taskService.getAll());

        return "tasksTemplate";
    }

    // GET /task/{id} np /task/1
    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") long id, Model model) {
        try {
            TaskDto taskDto = taskService.findById(id);
            model.addAttribute("task", taskDto);
            model.addAttribute("comment", new CommentDto());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task");
        }

        return "taskTemplate";
    }

    // GET /task/create
    @GetMapping("/create")
    public String createTask(Model model) {

        model.addAttribute("task", new TaskDto());
        return "taskCreate";
    }

    // POST /task/create
    // stwórz nowe zadanie
    @PostMapping("/create")
    public String createTodo(@Valid TaskDto task, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("task", task);
            return "taskCreate";
        }

        taskService.save(task);
        return "redirect:/task";
    }
}
