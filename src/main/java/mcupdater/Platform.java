package mcupdater;

import java.io.File;

public class Platform {

    public static enum OS {
        WINDOWS,
        MACOS,
        SOLARIS,
        LINUX,
        UNKNOWN;
    }

    public static OS getPlatform() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        }
        if (osName.contains("mac")) {
            return OS.MACOS;
        }
        if (osName.contains("linux")) {
            return OS.LINUX;
        }
        if (osName.contains("unix")) {
            return OS.LINUX;
        }
        if (osName.contains("sun")) {
            return OS.SOLARIS;
        }
        return OS.UNKNOWN;
    }

    public static File getMinecraftHome() {
        String userHome = System.getProperty("user.home", ".");
        File workingDirectory;
        switch (getPlatform()) {
        case SOLARIS:
        case LINUX:
            workingDirectory = new File(userHome, ".minecraft/");
            break;
        case WINDOWS:
            String applicationData = System.getenv("APPDATA");
            String folder = applicationData != null ? applicationData : userHome;

            workingDirectory = new File(folder, ".minecraft/");
            break;
        case MACOS:
            workingDirectory = new File(userHome, "Library/Application Support/minecraft");
            break;
        default:
            workingDirectory = new File(userHome, "minecraft/");
        }
        return workingDirectory;
    }
}
