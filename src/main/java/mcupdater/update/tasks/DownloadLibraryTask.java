package mcupdater.update.tasks;

import mcupdater.UpdaterMain;
import mcupdater.update.libs.LocalLibrary;
import mcupdater.update.libs.RemoteLibrary;

import java.net.MalformedURLException;

/**
 * Created by KJ4IPS on 11/7/2014.
 */
public class DownloadLibraryTask extends DownloadNonexistantTask{
    public DownloadLibraryTask(UpdaterMain mcup, RemoteLibrary remoteLibrary, LocalLibrary localLibrary) throws MalformedURLException {
        super(mcup, remoteLibrary.getRemoteURL(), localLibrary.getFile());
    }
}
