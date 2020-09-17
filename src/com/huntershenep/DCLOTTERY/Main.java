package com.huntershenep.DCLOTTERY;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;



public class Main extends JavaPlugin implements Listener{
	
		
		@SuppressWarnings("deprecation")
		public void onEnable() {
			System.out.println("[DC-Lottery] Enabling DC Lottery.");
			//Required to "register" the plugin.
			Bukkit.getPluginManager().registerEvents(this, this);
			this.getCommand("lottery").setExecutor(new Commands(this));
			
			Bukkit.getWorld("world").setWeatherDuration(0);
			
			
			//config.yml setup
			configManager.setup();
			configManager.get().addDefault("DrawInterval", 60);
			configManager.get().addDefault("TicketPrice", 50);
			configManager.get().options().copyDefaults(true);
			configManager.save();
			Methods.getDrawInterval();
			Methods.getTicketPrice();
			Methods.resetTimeLeft();
			Methods.setEffTicketPrice();
			Methods.setEffDrawInterval();
			
			//DRAW LOTTERY =======================================================
			Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
			    
			    public void run() {
			    	Methods.drawWinner();
			    	Methods.resetTimeLeft();
		        }
		    //First value is value after reboot, second is value after that!
			}, Methods.drawInterval*1200L, Methods.drawInterval*1200L);
			
			
			
			//TIMER -==============================================================
			Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
			    
			    public void run() {
			    	Methods.timeLeft = Methods.timeLeft-20;
		        }
		    //First value is value after reboot, second is value after that!
			}, 1L, 20L);
			
			
		}
			
		public void onDisable() {
			System.out.println("Forcing lottery draw");
			Methods.drawWinner();
			System.out.println("[DC-Lottery] Disabling DC Lottery.");

		}	
}
