package pl.sda.taskapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.entity.TaskType;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    // pobierz zadania o określony typie
    // słowo Tasks jest opcjonalne
    List<Task> findTasksByType(TaskType taskType);

    List<Task> findByTypeAndCreatedAtBetween(TaskType taskType, Date start, Date end);

//    @Query("SELECT t FROM Task t INNER JOIN Comment c ON c.task.id = t.id WHERE c.text LIKE :#{searchText}")
//    List<Task> findByCommentText(String searchText);
}
