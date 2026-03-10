package workshop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.max;

public class Workshop {
    private static int counter = 0;

    private final int id;
    private int totalCompletionTime;
    private double totalPenaltyCost;
    private final ArrayList<Machine> machines;

    public Workshop(int nbMachines) {
        this.id = counter;
        counter++;
        this.totalCompletionTime = 0;
        this.totalPenaltyCost = 0.0;
        this.machines = new ArrayList<>();

        for (int i = 0; i < nbMachines; i++) {
            this.machines.add(new Machine());
        }
    }


    public void updateCriteria() {
        Iterator<Machine> it = machines.iterator();
        int newTotalCompletionTime = 0;
        double newTotalPenaltyCost = 0.0;
        while (it.hasNext()) {
            Machine m = it.next();
            newTotalCompletionTime = max(newTotalCompletionTime, m.getCompletionTime());
            newTotalPenaltyCost += m.getPenaltyCost();
        }
        this.totalCompletionTime = newTotalCompletionTime;
        this.totalPenaltyCost = newTotalPenaltyCost;
    }

    private Machine getMachine(int posMachine) {
        return this.machines.get(posMachine);
    }

    private boolean addTask(Task t, int posMachine) {
        return getMachine(posMachine).addTask(t);
    }

    private int getIdMachineSmallestCompletionTime() {
        int bestMachineId = -1;
        int smallestCompletionTime = Integer.MAX_VALUE;
        for (int i = 0; i < machines.size(); i++) {
            Machine m = machines.get(i);
            if (m.getCompletionTime() < smallestCompletionTime) {
                smallestCompletionTime = m.getCompletionTime();
                bestMachineId = i;
            }
        }
        return bestMachineId;
    }

    public void scheduleTasks(List<Task> tasks) {
        for (Task t : tasks) {
            addTask(t, getIdMachineSmallestCompletionTime());
        }
        updateCriteria();
    }


    public void scheduleTasksOrderedByDeadline(List<Task> tasks) {
        Comparator<Task> c = (t1, t2) -> t1.getDeadline() - t2.getDeadline();
        tasks.sort(c);
        scheduleTasks(tasks);
    }

    public void scheduleTasksProcessingTimeOpt(List<Task> tasks) {
        Comparator<Task> c = (t1, t2) -> t2.getProcessingTime() - t1.getProcessingTime();
        tasks.sort(c);
        scheduleTasks(tasks);
    }

    private String machinesToString() {
        String s = "";
        for (Machine m : machines) {
            s = s.concat("  " + m + "\n");
        }
        return s;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id=" + id +
                ", nbMachines=" + machines.size() +
                ", totalPenaltyCost=" + totalPenaltyCost +
                ", totalCompletionTime=" + totalCompletionTime +
                ", machines=[\n" + machinesToString() + "]" +
                '}';
    }
}
