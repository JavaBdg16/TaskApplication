package pl.sda.taskapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.taskapplication.dto.CommentDto;
import pl.sda.taskapplication.service.CommentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public String saveComment(@Valid CommentDto comment, Error error, Model model) {
        // TODO: Validate

        commentService.save(comment);

        return "redirect:/task/" + comment.getTaskId();
    }
}
