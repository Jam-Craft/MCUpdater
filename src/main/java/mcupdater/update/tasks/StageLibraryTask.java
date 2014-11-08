package mcupdater.update.tasks;

/**
 * Created by KJ4IPS on 11/7/2014.
 */

import mcupdater.UpdaterMain;
import mcupdater.update.libs.LocalLibrary;

/**
 * This task prepares a library for later loading
 */
public class StageLibraryTask extends BaseTask {

    private LocalLibrary library;
    private UpdaterMain mcup;

    public StageLibraryTask(UpdaterMain mcup, LocalLibrary library){
        this.library = library;
    }

    @Override
    public boolean run() {
        mcup.localLibraries.add(library);
        return true;
    }
}
