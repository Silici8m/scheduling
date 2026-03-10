import workshop.Task;
import workshop.Workshop;

private ArrayList<Task> generateTaskList() {
    Task t1 = new Task(150, 300, 2.5);
    Task t2 = new Task(140, 400, 1.5);
    Task t3 = new Task(50, 200, 2.5);
    Task t4 = new Task(85, 200, 1.0);
    Task t5 = new Task(75, 160, 0.5);
    Task t6 = new Task(80, 500, 1.5);

    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(t1);
    tasks.add(t2);
    tasks.add(t3);
    tasks.add(t4);
    tasks.add(t5);
    tasks.add(t6);
    return tasks;
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Workshop w1 = new Workshop(2);
    Workshop w2 = new Workshop(2);
    Workshop w3 = new Workshop(2);

    w1.scheduleTasks(generateTaskList());
    System.out.println("w1: " + w1);

    w2.scheduleTasksOrderedByDeadline(generateTaskList());
    System.out.println("w2: " + w2);

    w3.scheduleTasksProcessingTimeOpt(generateTaskList());
    System.out.println("w3: " + w3);
}
