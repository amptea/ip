package choicebot.task;

import java.util.ArrayList;

/**
 * Represents a collection of Task objects.
 */
public class TaskList {
    /** The list of tasks. */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty Tasklist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task from the task list.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Returns the current task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Returns the current task list size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if task list is empty.
     * @return True if task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns a Task by its index in the task list.
     *
     * @param taskNumber Index of the task to be returned.
     */
    public Task getTask(Integer taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Finds all tasks that match the given keyword.
     *
     * @param keyword Keyword to search for in taskList.
     * @return List of all tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
