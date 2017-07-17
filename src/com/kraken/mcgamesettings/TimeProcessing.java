package com.kraken.mcgamesettings;

import org.bukkit.World;

public class TimeProcessing {
	
	  Main plugin;
	
  //Constructor
	public TimeProcessing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Day/night cycle commands
	public void cycleTime(World world, String command) {
		
		switch ( command ) {
		
		  //"midnight" command processing
			case "midnight":
				world.setTime((long) 18000);
				break;
		  //"night" command processing
			case "night":
				world.setTime((long) 13500);
				break;
		  //"sunset" command processing
			case "sunset":
				world.setTime((long) 12500);
				break;
		  //"dusk" command processing
			case "dusk":
				world.setTime((long) 12000);
				break;
		  //"morning" command processing
			case "morning":
				world.setTime((long) 1000);
				break;
		  //"dawn" command processing
			case "dawn":
				world.setTime((long) 0);
				break;
		  //"sunrise" command processing
			case "sunrise":
				world.setTime((long) 23000);
				break;
		  //"day" command processing
			default: 
				world.setTime((long) 6000);
				break;
				
		}
		
	}
	
}
