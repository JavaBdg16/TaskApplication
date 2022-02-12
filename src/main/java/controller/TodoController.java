package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.todoapp.model.Todo;
import pl.sda.todoapp.repository.TodoRepository;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // wyświetl wszystkie zadania
    // GET localhost:8080/todo
    // dlaczego "/" psuł????
    @GetMapping
    public String showTodos(Model model) {
        model.addAttribute("modelTodoList", todoRepository.findAll());
        model.addAttribute("todo", new Todo());

        return "todoTemplate";
    }

    // POST /todo
    // stwórz nowe zadanie
    @PostMapping
    public String createTodo(@Valid Todo todo, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("modelTodoList", todoRepository.findAll());
            model.addAttribute("todo", todo);
            return "todoTemplate";
        }

        todo.setCreateDate(new Date());
        todoRepository.save(todo);
        return "redirect:/todo";
    }
}
