package mcupdater.update.tasks;

public interface ITask {

    /**
     *  This actually performs the task (or blocks til the async task completes)
     * @return True if the task was successful, false if not
     */
    public boolean run();

    /**
     *
     * @return True if the tasks requires network to succeed, false if it does not
     */
    public boolean needsNetwork();

    /**
     *
     * @return true if the task is Async (and therefore implements IAsyncTask)
     */
    public boolean isAsync();


}
