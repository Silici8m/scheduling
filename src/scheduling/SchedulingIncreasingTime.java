package scheduling;

import workshop.Task;
import workshop.Workshop;

import java.util.List;

import static java.util.Collections.sort;

public class SchedulingIncreasingTime implements Scheduling{

    @Override
    public Workshop schedule(int nbMachines, List<Task> tasks) {
        Workshop w = new Workshop(nbMachines);
        List<Task> tasks_cp = Scheduling.copyTasks(tasks);
        sort(tasks_cp);
        w.scheduleTasks(tasks_cp);
        return w;
    }
}
