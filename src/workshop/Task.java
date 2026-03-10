package workshop;

import static java.lang.Math.max;

public class Task {
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
        this(t.processingTime, t.deadline, t.unitPenaltyCost);
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
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", processingTime=" + processingTime +
                ", startingTime=" + startingTime +
                ", deadline=" + deadline +
                ", unitPenaltyCost=" + unitPenaltyCost +
                '}';
    }
}
