package mcupdater.update.tasks;

import mcupdater.UpdaterMain;
import mcupdater.download.Downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This task downloads a file, replacing the file if it exists.
 */
public class DownloadTask extends BaseTask implements IDescriptiveTask {

    protected UpdaterMain mcup;
    protected URL source;
    protected File destination;
    protected String hash;

    public DownloadTask(UpdaterMain mcup, URL source, File destination, String hash){
        this.mcup = mcup;
        this.source = source;
        this.destination = destination;
        this.hash = hash;
    }

    public DownloadTask(UpdaterMain mcup, URL source, File destination){
        this(mcup, source, destination, null);
    }

    @Override
    public boolean run() {
        try {
            if (hash != null) {
                Downloader.downloadFileWithHashCheck(source, destination, hash);
            } else {
                Downloader.downloadFile(source, destination);
            }
        }catch (IOException e){
            mcup.getLogger().error(String.format("Could not download %s", source));
            return false;
        }
        return true;
    }

    @Override
    public boolean needsNetwork(){
        return true;
    }

    @Override
    public String getDescription() {
        return String.format("Downloading %s",source.toString());
    }
}
