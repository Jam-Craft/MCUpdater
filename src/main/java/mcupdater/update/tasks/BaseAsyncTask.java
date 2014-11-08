package mcupdater.update.tasks;

public abstract class BaseAsyncTask implements IAsyncTask{

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean isErrored() {
        return false;
    }

    @Override
    public boolean needsNetwork() {
        return false;
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
