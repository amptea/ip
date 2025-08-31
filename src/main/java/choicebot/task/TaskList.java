package choicebot.task;

import java.util.ArrayList;

/**
 * Represents a collection of Task objects.
 */
public class TaskList {
    /** The list of tasks. */
    protected ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with the given list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty Tasklist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a Task from the task list.
     */
    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Returns the current task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the current task list size.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Checks if task list is empty.
     * @return True if task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Returns a Task by its index in the task list.
     *
     * @param taskNumber Index of the task to be returned.
     */
    public Task getTask(Integer taskNumber) {
        return this.taskList.get(taskNumber);
    }
}
