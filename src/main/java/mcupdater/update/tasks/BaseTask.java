package mcupdater.update.tasks;

/**
 * Created by KJ4IPS on 11/7/2014.
 */
public abstract class BaseTask implements ITask {

    @Override
    public boolean needsNetwork() {
        return false;
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
