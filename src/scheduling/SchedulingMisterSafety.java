package scheduling;

import workshop.Task;
import workshop.Workshop;

import java.util.List;

public class SchedulingMisterSafety implements Scheduling {

    @Override
    public Workshop schedule(int nbMachines, List<Task> tasks) {
        Workshop w = new Workshop(nbMachines);
        List<Task> tasks_cp = Scheduling.copyTasks(tasks);
        w.scheduleTasks(tasks_cp);
        return w;
    }
}
