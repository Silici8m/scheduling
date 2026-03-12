package scheduling;

import workshop.Task;
import workshop.Workshop;

import java.util.ArrayList;
import java.util.List;

public interface Scheduling {
    Workshop schedule(int nbMachines, List<Task> tasks);

    static List<Task> copyTasks(List<Task> tasks) {
        List<Task> new_list = new ArrayList<>();
        for (Task task : tasks) {
            Task new_task = new Task(task);
            new_list.add(new_task);
        }
        return new_list;
    }
}
