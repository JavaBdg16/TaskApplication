package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.taskapplication.dto.UserDto;
import pl.sda.taskapplication.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute(value = "user") @Valid UserDto user, BindingResult errors, Model model) {

        // TODO: dlaczego nie pokazują się błędy?????
        if (errors.hasErrors()) {
            // model.addAttribute("user", user);
            return "register";
        }

        userService.registerUser(user);
        return "redirect:/login";
    }
}
