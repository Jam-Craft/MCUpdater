package mcupdater.update.tasks;

public interface IAsyncTask extends ITask {

    /**
     * Fires the task
     */
    public void fire();

    /**
     *
     * @return True if the task has completed (regardless of success)
     */
    public boolean isDone();

    /**
     *
     * @return True if the task failed in some way
     */
    public boolean isErrored();

}
