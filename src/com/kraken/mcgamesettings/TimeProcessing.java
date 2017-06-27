package com.kraken.mcgamesettings;

import org.bukkit.entity.Player;

public class TimeProcessing {
	
	  Main plugin;
	
  //Constructor
	public TimeProcessing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Day/night cycle commands
	public void cycleTime(Player player, String command) {
		
		switch ( command ) {
		
		  //"midnight" command processing
			case "midnight":
				player.getWorld().setTime((long) 18000);
				break;
		  //"night" command processing
			case "night":
				player.getWorld().setTime((long) 13500);
				break;
		  //"sunset" command processing
			case "sunset":
				player.getWorld().setTime((long) 12500);
				break;
		  //"dusk" command processing
			case "dusk":
				player.getWorld().setTime((long) 12000);
				break;
		  //"morning" command processing
			case "morning":
				player.getWorld().setTime((long) 1000);
				break;
		  //"dawn" command processing
			case "dawn":
				player.getWorld().setTime((long) 0);
				break;
		  //"sunrise" command processing
			case "sunrise":
				player.getWorld().setTime((long) 23000);
				break;
		  //"day" command processing
			default: 
				player.getWorld().setTime((long) 6000);
				break;
				
		}
		
	}
	
}
