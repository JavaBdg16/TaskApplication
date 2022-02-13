package pl.sda.taskapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.taskapplication.controller.CommentController;
import pl.sda.taskapplication.dto.CommentDto;
import pl.sda.taskapplication.entity.Comment;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.mapper.CommentMapper;
import pl.sda.taskapplication.repository.CommentRepository;
import pl.sda.taskapplication.repository.TaskRepository;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    public void save(CommentDto commentDto) {
        Comment comment = CommentMapper.map(commentDto);
        Task task = taskRepository.findById(commentDto.getTaskId()).orElseThrow(
                () -> new NoSuchElementException());

        comment.setTask(task);
        commentRepository.save(comment);
    }
}
