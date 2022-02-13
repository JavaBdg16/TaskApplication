package pl.sda.taskapplication.mapper;

import pl.sda.taskapplication.dto.CommentDto;
import pl.sda.taskapplication.entity.Comment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static CommentDto map(Comment comment) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setCreatedAt(dateFormat.format(comment.getCreatedAt()));

        return dto;
    }

    public static List<CommentDto> map(List<Comment> comments) {
        List<CommentDto> dtos = new ArrayList<>();
        comments.forEach(c -> dtos.add(map(c)));
        return dtos;
    }

    public static Comment map(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());

        return comment;
    }
}
