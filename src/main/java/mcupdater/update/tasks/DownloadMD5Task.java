package mcupdater.update.tasks;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import mcupdater.UpdaterMain;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by KJ4IPS on 11/7/2014.
 */
public class DownloadMD5Task extends DownloadTask implements IPossibleTask {

    protected boolean needed;

    public DownloadMD5Task(UpdaterMain mcup, URL source, File destination, String hash){
        super(mcup, source, destination, hash);
        needed = !destination.exists() || !compareMD5(destination, hash);
    }

    protected boolean compareMD5(File file, String expected){
        try {
            return Files.hash(file, Hashing.md5()).toString().equalsIgnoreCase(expected);
        } catch (IOException e) {
            mcup.getLogger().error(String.format("Could not open file for hash check: %s", destination));
            e.printStackTrace();
            return false;
        }
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
