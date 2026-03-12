package scheduling;

import workshop.Task;
import workshop.Workshop;

import java.util.List;

import static java.util.Collections.shuffle;

public class SchedulingRandom implements Scheduling {

    private static final int NB_IT = 100000;

    @Override
    public Workshop schedule(int nbMachines, List<Task> tasks) {

        Workshop best_workshop = new Workshop(nbMachines);
        List<Task> tasks_cp = Scheduling.copyTasks(tasks);
        best_workshop.scheduleTasks(tasks_cp);
        for (int i = 0; i < NB_IT; i++) {
            Workshop w = new Workshop(nbMachines);
            tasks_cp = Scheduling.copyTasks(tasks);
            shuffle(tasks_cp);
            w.scheduleTasks(tasks_cp);
            if (w.getTotalCompletionTime() <= best_workshop.getTotalCompletionTime() && w.getTotalPenaltyCost() <= best_workshop.getTotalPenaltyCost()) {
                best_workshop = w;
            }
        }

        return best_workshop;
    }
}
