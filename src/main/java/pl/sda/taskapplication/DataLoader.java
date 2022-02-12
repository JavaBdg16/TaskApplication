package pl.sda.taskapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.sda.taskapplication.entity.Task;
import pl.sda.taskapplication.entity.TaskType;
import pl.sda.taskapplication.repository.TaskRepository;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Iterable<Task> tasks = taskRepository.findAll();
        if (!tasks.iterator().hasNext()) {

            for (int i = 1; i <= 10; i++) {
                Task task = new Task();
                task.setName("Zadanie nr " + i);
                task.setDescription("Opis zadania nr " + i);
                task.setCreatedAt(new Date());
                task.setType(TaskType.TASK);

                taskRepository.save(task);
            }
        }
    }
}
