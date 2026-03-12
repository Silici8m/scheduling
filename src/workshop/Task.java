package workshop;

import java.util.Objects;

import static java.lang.Math.max;

public class Task implements Comparable{
    private static final int PROCESSING_TIME_MIN = 50;
    private static final int DEADLINE_INF = Integer.MAX_VALUE;
    private final int STARTING_TIME_UNDEFINED = -1;
    private static int counter = 0;

    private final int id;
    private int processingTime;
    private int startingTime;
    private int deadline;
    private double unitPenaltyCost;


    public Task() {
        this.id = counter;
        counter++;
        this.processingTime = PROCESSING_TIME_MIN;
        this.deadline = DEADLINE_INF;
        this.startingTime = STARTING_TIME_UNDEFINED;
        this.unitPenaltyCost = 0;
    }

    public Task(int processingTime) {
        this();
        if (processingTime >= 50) {
            this.processingTime = processingTime;
        }
    }


    public Task(int processingTime, int deadline, double unitPenaltyCost) {
        this(processingTime);
        if (deadline > 2 * this.processingTime) {
            this.deadline = deadline;
        }
        if (unitPenaltyCost > 0) {
            this.unitPenaltyCost = unitPenaltyCost;
        }
    }

    public Task(Task t) {
        this.id = t.id;
        this.startingTime = t.startingTime;
        this.deadline = t.deadline;
        this.unitPenaltyCost = t.unitPenaltyCost;
        this.processingTime = t.processingTime;
    }


    public int getProcessingTime() {
        return processingTime;
    }

    public int getDeadline() {
        return deadline;
    }

    public boolean setStartingTime(int startingTime) {
        if (startingTime >= 0 && this.startingTime == STARTING_TIME_UNDEFINED) {
                 this.startingTime = startingTime;
            return true;
        }
        System.out.println("Impossible de redéfinir le startingTime (startingTime=" + startingTime + ", this.startingTime="+ this.startingTime+")");
        return false;
    }

    public double getPenality() {
        if (startingTime < 0) {
            return 0;
        }
        double delayDuration = max(0, startingTime + processingTime-deadline);
        return delayDuration*this.unitPenaltyCost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", processingTime=" + processingTime +
                ", startingTime=" + startingTime +
                ", deadline=" + deadline +
                ", unitPenaltyCost=" + unitPenaltyCost +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || getClass() != o.getClass()) { return -1; }
        Task t = (Task) o;
        if (equals(t)) { return 0; }
        if (processingTime > t.processingTime) {
            return 1;
        } else if (processingTime < t.processingTime) {
            return -1;
        } else {
            if (deadline > t.deadline) {
                return 1;
            } else if (deadline < t.deadline) {
                return -1;
            } else {
                if (unitPenaltyCost > t.unitPenaltyCost) {
                    return 1;
                } else if (unitPenaltyCost < t.unitPenaltyCost) {
                    return -1;
                } else {
                    return id - t.id;
                }
            }
        }
    }
}
