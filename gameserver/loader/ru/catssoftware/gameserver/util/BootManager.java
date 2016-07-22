package ru.catssoftware.gameserver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.security.MessageDigest;	
import java.util.TimeZone;

import org.apache.log4j.xml.DOMConfigurator;


import ru.catssoftware.Config;
import ru.catssoftware.gameserver.L2GameServer;
import ru.catssoftware.info.UpdateInfo;
import ru.catssoftware.info.Version;
import ru.catssoftware.tools.util.HexUtil;
import ru.catssoftware.util.Console;
import ru.catssoftware.util.JarUtils;


public final class BootManager
{

	private static String updateURL="http://l2-service.ru/";
	private static boolean NO_CHECK_UPDATES = false;
	public static void main(String[] args) throws Throwable
	{
		for(int i =0; i<args.length;i++) { 
			if(args[i].equals("--noupdates"))
				NO_CHECK_UPDATES = true;
			else if (args[i].equals("--url")) {
				i++;
				updateURL = args[i]; 
			}
		}	
		new BootManager();
	}

	private static String MD5(File f) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			InputStream is = new FileInputStream(f);				
			byte[] buffer = new byte[8192];
			int read = 0;
			while( (read = is.read(buffer)) > 0) {
				digest.update(buffer, 0, read);
			}		
			is.close();
			byte[] md5sum = digest.digest();
			return HexUtil.hexToString(md5sum);
		} catch(Exception e) {
			return null;
		}
	}
	@SuppressWarnings("deprecation")
	public BootManager() throws Throwable
	{
		Console.printSection("Boot Manager");

		// create log folder
		new File("log").mkdirs();
		new File("cachedir").mkdirs();
		new File("log/java").mkdirs();
		new File("log/error").mkdirs();
		new File("log/audit").mkdirs();
		new File("log/chat").mkdirs();
		new File("log/ban").mkdirs();
		new File("log/item").mkdirs();
		new File("data/crests").mkdirs();
		new File("data/serial").mkdirs();
//		new Bugzilla();
		if(!NO_CHECK_UPDATES) {
		System.out.print("BootManager: Checking for updates");
		
		try {
			URL url = new URL(updateURL+"revision/"+Version.Version);
			InputStream is = url.openStream();
			if(is!=null) {
				System.out.print(".");
				LineNumberReader lnr = new LineNumberReader(new InputStreamReader(is));
				String line = lnr.readLine();
				is.close();
				if(!line.equals("0")) {
					File f = new File("cachedir/updates.jar");
					boolean needUpdate = true;
					if(f.exists()) {
						System.out.print(".");
						String md5 = MD5(f); 
						if(md5!=null)
							needUpdate = !md5.equals(line);
					}
					if(needUpdate) {
						System.out.print(".");
						if(f.exists())
							if(!f.delete()) {
								throw new Exception("Unable to delete file");
							}
						url = new URL(updateURL+"updates/"+Version.Version+"/updates.jar");
						is = url.openStream();
						if(is!=null) {
							System.out.print(".");
							FileOutputStream fos = new FileOutputStream(f);
							byte b[] = new byte[8192];
							int read = 0;
							while((read = is.read(b))>0) {
								System.out.print(".");
								fos.write(b, 0, read);
							}
							is.close();
							fos.close();
						}
					} 
					System.out.print(".");
					JarUtils.addURL(f.toURL());
					if(UpdateInfo.Version.length()>0)
						Version.Version +="u"+UpdateInfo.Version; 
				}
			    
			}
			System.out.println("done!");
		} catch(Exception e) {
			System.out.println("fail: "+e);
		}
		}
		File f = new File("./gameserver.jar");
		if(f.exists())
			JarUtils.addURL(f.toURL());
		else {
			f = new File("./config/administration/developer.properties");
			if(!f.exists()) {
				System.out.println("No gameserver.jar found, exiting...");
				return;
			}
		}
		System.out.println("BootManager: Initializing Logging.");
		initLogging();
		System.out.println("BootManager: Creating Boot Folders and Files.");
		createBootDirs();
		System.out.println("BootManager: Initializing Configs.");
		Config.loadAll();
		TimeZone.setDefault(TimeZone.getTimeZone(Config.TIME_ZONE));
		Console.printSection("Boot Manager");
		System.out.println("BootManager: Config Sucessffully Loaded.");
		System.out.println("BootManager: Preparations Done. Staring GameServer!");
		new L2GameServer();
	}

	private void createBootDirs() throws IOException
	{
		File logFolder = new File(Config.DATAPACK_ROOT, "log");
		File logFolderGame = new File(logFolder, "game");
		logFolderGame.mkdir();
		logFolder.mkdir();

		new File(Config.DATAPACK_ROOT, "data/crests").mkdirs();
		new File(Config.DATAPACK_ROOT, "data/pathnode").mkdirs();
		new File(Config.DATAPACK_ROOT, "data/faenor").mkdirs();
		new File(Config.DATAPACK_ROOT, "data/serial").mkdirs();

		System.out.println("BootManager: All Directories and Files Created!");
	}

	private void initLogging()
	{
		DOMConfigurator.configure("./config/log4j.xml");
	}
}