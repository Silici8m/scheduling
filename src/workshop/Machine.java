package workshop;

import java.util.Iterator;
import java.util.LinkedList;

public class Machine {
    private static int counter = 0;

    private final int id;
    private final LinkedList<Task> tasks;
    private double penaltyCost;
    private int completionTime;

    public Machine() {
        this.id = counter;
        counter++;
        this.tasks = new LinkedList<>();
        this.penaltyCost = 0;
        this.completionTime = 0;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public double getPenaltyCost() {
        return penaltyCost;
    }

    public boolean addTask(Task t) {
        if (t == null) {
            return false;
        }
        if (!t.setStartingTime(completionTime)) {
            return false;
        }
        this.penaltyCost += t.getPenality();
        this.completionTime += t.getProcessingTime();
        return tasks.add(t);
    }

    private String tasksToString() {
        Iterator<Task> it = tasks.iterator();
        String s = "";
        while (it.hasNext()) {
            s = s.concat("\n\t" + it.next());
        }
        return s;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", penaltyCost=" + penaltyCost +
                ", completionTime=" + completionTime +
                ", tasks=[" + tasksToString() + "]" +
                '}';
    }

}
