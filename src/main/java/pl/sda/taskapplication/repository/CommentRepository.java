package pl.sda.taskapplication.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.taskapplication.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
