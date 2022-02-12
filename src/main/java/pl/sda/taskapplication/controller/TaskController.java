package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.taskapplication.dto.TaskDto;
import pl.sda.taskapplication.service.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // wyświetl wszystkie zadania
    // GET localhost:8080/todo
    // dlaczego "/" psuł????
    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("modelTaskList", taskService.getAll());
        model.addAttribute("task", new TaskDto());

        return "tasksTemplate";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable("id") long id, Model model) {
        // TODO
        return "taskTemplate";
    }

    // POST /todo
    // stwórz nowe zadanie
    @PostMapping
    public String createTodo(@Valid TaskDto task, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("modelTaskList", taskService.getAll());
            model.addAttribute("task", task);
            return "tasksTemplate";
        }

        taskService.save(task);
        return "redirect:/task";
    }
}
