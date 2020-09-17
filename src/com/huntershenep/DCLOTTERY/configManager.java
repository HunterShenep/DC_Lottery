package com.huntershenep.DCLOTTERY;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class configManager {
	private static File file;
	private static FileConfiguration customFile;
	
	
	//find or generates the config.yml file
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("DCLOTTERY").getDataFolder(), "config.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	
	//Returns file
	public static FileConfiguration get() {
		return customFile;
	}
	
	
	//Saves file
	public static void save() {
		try {
			customFile.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	//Reloads file
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	
	//Add new entry
	public static void addLine(String name, String value) {
		customFile.set(name, value);
		save();
	}
	public static void addLine(String name, Double value) {
		
		customFile.set(name, value);
		save();
	}
	public static void addLine(String name, int value) {
		
		customFile.set(name, value);
		save();
	}
	public static void addLine(String name, float value) {
		
		customFile.set(name, value);
		save();
	}
	
	public static void removeLine(String name, int value) {
		//customFile.getStringList(Integer.toString(value)).remove(Integer.toString(value));
		customFile.set(Integer.toString(value), null);
		save();
	}
}
