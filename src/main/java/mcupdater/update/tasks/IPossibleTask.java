package mcupdater.update.tasks;

/**
 * This is used for tasks that may or may not need to be run
 */
public interface IPossibleTask extends ITask {
    /**
     *
     * @return True if the task must be done
     */
    public boolean isNeeded();

    /**
     *
     * @return runs a task ONLY if it is needed, run forces the task
     */
    public boolean runIfNeeded();
}
