package mcupdater;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mcupdater.local.LocalLibrary;
import mcupdater.remote.RemoteLibrary;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class Updater implements ITweaker {

	private UpdaterMain mcup;
	private List<String> launchArgs = Lists.newArrayList();
	
	@Override
	public void acceptOptions(List<String> args, File gameDir, File assetsDir,
			String profile) {
		launchArgs.addAll(args);
		launchArgs.addAll(ImmutableList.of("--gameDir", gameDir.getPath(), "--assetsDir", assetsDir.getPath()));
		if(!args.contains("--accessToken"))
			launchArgs.addAll(ImmutableList.of("--accessToken", "accessToken"));
		if(!args.contains("--version"))
			launchArgs.addAll(ImmutableList.of("--version", profile));
		
		mcup = new UpdaterMain(gameDir);
		mcup.main(args.toArray(new String[args.size()]));
	}

	@Override
	public void injectIntoClassLoader(LaunchClassLoader classLoader) {
		mcup.readLibraries(new File(Platform.getMinecraftHome(), "libraries"));
		for(RemoteLibrary remote : mcup.getRemoteJson().getLibrariesList()){
			if(!remote.installed())
				try {
					remote.download();
				} catch (IOException e) {
					UpdaterMain.logger.error(String.format("Failed to download %s.", remote.getName()));
					e.printStackTrace();
				}
			
			for(LocalLibrary local : mcup.localLibraries){
				if(remote.equals(local)){
					try {
						local.loadLibrary(classLoader);
						UpdaterMain.logger.info(String.format("Loading library %s.", local.getFile().getPath()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		// Add cascaded tweaks
		for(String tweak : mcup.getRemoteJson().tweaks){
			if(!tweak.contains("liteloader"))
			registerTweak(tweak);
		}
	}

	@Override
	public String getLaunchTarget() {
		return "net.minecraft.client.main.Main";
	}

	@Override
	public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[launchArgs.size()]);
	}

	@SuppressWarnings("unchecked")
	private void registerTweak(String tweak){
		List<String> tweaks = (List<String>) Launch.blackboard.get("TweakClasses");
		tweaks.add(tweak);
	}
}
