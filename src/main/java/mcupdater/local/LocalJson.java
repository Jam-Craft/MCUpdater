package mcupdater.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import mcupdater.AbstractJson;
import mcupdater.remote.RemoteJson;

public class LocalJson extends AbstractJson{

	private String modpack;
	private String version;
	private String repo;
	
	
	public LocalJson(Reader json) throws NullPointerException{
		super(json);
		modpack = object.get("modpack").getAsString();
		version = object.get("version").getAsString();
		repo = object.get("repo").getAsString();
	}
	
	public LocalJson(File file) throws NullPointerException, FileNotFoundException{
		this(new FileReader(file));
	}
	
	public String getModpackName(){
		return modpack;
	}
	
	public String getModpackVersion(){
		return version;
	}
	
	public String getRepo(){
		return repo;
	}
	
	public URL getRemotePackURL(){
		try {
			return new URL(repo + (repo.endsWith("/") ? "" : "/" ) + modpack + "/" + version + "/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private URL getRemoteJson() throws MalformedURLException{
		String s = getRemotePackURL().toString();
		return new URL(s + "pack.json");
	}
	
	public RemoteJson getRemotePack() throws IOException{
		InputStreamReader reader = new InputStreamReader(getRemoteJson().openStream());
		return new RemoteJson(reader);
	}
	
}