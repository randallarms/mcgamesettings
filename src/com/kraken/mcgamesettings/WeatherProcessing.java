package com.kraken.mcgamesettings;

import org.bukkit.entity.Player;

public class WeatherProcessing {
	
	  Main plugin;
	
  //Constructor
	public WeatherProcessing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Weather commands
	public void cycleWeather(Player player, String command) {
		
		switch ( command ) {
		
		  //"rain", "raining", "snow", "snowing" command processing
			case "rain": 
				player.getWorld().setWeatherDuration(18000);
				break;
		  //"rain", "raining", "snow", "snowing" command processing
			case "storm":
				player.getWorld().setStorm(true);
				break;
		  //"sunny" command processing
			default: 
				player.getWorld().setWeatherDuration(0);
				player.getWorld().setStorm(false);
				break;
				
		}
		
	}
	
}
