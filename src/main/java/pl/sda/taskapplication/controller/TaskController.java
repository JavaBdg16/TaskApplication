package pl.sda.taskapplication.controller;

import pl.sda.taskapplication.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.taskapplication.repository.TaskRepository;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/task")
public class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // wyświetl wszystkie zadania
    // GET localhost:8080/todo
    // dlaczego "/" psuł????
    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("modelTaskList", taskRepository.findAll());
        model.addAttribute("task", new Task());

        return "taskTemplate";
    }

    // POST /todo
    // stwórz nowe zadanie
    @PostMapping
    public String createTodo(@Valid Task task, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("modelTaskList", taskRepository.findAll());
            model.addAttribute("task", task);
            return "taskTemplate";
        }

        task.setCreatedAt(new Date());
        taskRepository.save(task);
        return "redirect:/todo";
    }
}
