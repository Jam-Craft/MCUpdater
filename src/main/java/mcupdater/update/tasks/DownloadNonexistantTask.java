package mcupdater.update.tasks;

import mcupdater.UpdaterMain;

import java.io.File;
import java.net.URL;

public class DownloadNonexistantTask extends DownloadTask implements IPossibleTask {

    protected boolean needed;

    public DownloadNonexistantTask(UpdaterMain mcup, URL source, File destination) {
        super(mcup, source, destination);
        needed = !destination.exists();
    }

    @Override
    public boolean isNeeded() {
        return needed;
    }

    @Override
    public boolean runIfNeeded() {
        return !needed || run();
    }
}
