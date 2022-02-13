package pl.sda.taskapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.taskapplication.entity.Comment;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.entity.TaskType;
import pl.sda.taskapplication.entity.User;
import pl.sda.taskapplication.repository.CommentRepository;
import pl.sda.taskapplication.repository.TaskRepository;
import pl.sda.taskapplication.repository.UserRepository;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    private static final String loremipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla euismod massa libero. Ut non tempus enim, vitae maximus sapien. Curabitur sem eros, viverra in magna sit amet, vestibulum sollicitudin neque. Aenean sapien ex, vestibulum sed elit quis, sagittis bibendum ante. Mauris sapien eros, rhoncus et dignissim sed, consequat ac risus. Nulla posuere tortor ac eros vestibulum laoreet. Vivamus nisl magna, scelerisque sed nisi a, pulvinar auctor nisi. Aliquam erat volutpat. Etiam rhoncus nunc a mi vestibulum efficitur";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = new User();
        user.setUsername("jnowak");
        user.setPassword(passwordEncoder.encode("1234"));

        userRepository.save(user);

        Iterable<Task> tasks = taskRepository.findAll();
        if (!tasks.iterator().hasNext()) {

            for (int i = 1; i <= 10; i++) {
                Task task = new Task();
                task.setName("Zadanie nr " + i);
                task.setDescription("Opis zadania nr " + i);
                task.setCreatedAt(new Date());
                task.setType(TaskType.TASK);
                task = taskRepository.save(task);

                for (int j = 1; j <= 5; j++) {
                    Comment comment = new Comment();
                    comment.setText("Komentarz " + task.getName() + " " + loremipsum);
                    comment.setTask(task);

                    comment = commentRepository.save(comment);

                    // task.getComments().add(comment);
                }


            }
        }
    }
}
