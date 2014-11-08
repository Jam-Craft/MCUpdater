package mcupdater.update.tasks;

import mcupdater.UpdaterMain;
import mcupdater.update.mods.LocalMod;
import mcupdater.update.mods.RemoteMod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadModTask extends DownloadMD5Task{

    public DownloadModTask(UpdaterMain mcup, RemoteMod remoteMod, LocalMod localMod) throws MalformedURLException {
        super(mcup, new URL(remoteMod.getFile()), new File(localMod.getFile()), remoteMod.hasHash() ? remoteMod.getMD5() : null); // At this point, needed is set as per MD5 match
        this.needed = this.needed || (!remoteMod.getVersion().equals(localMod.getVersion()));
    }
}
