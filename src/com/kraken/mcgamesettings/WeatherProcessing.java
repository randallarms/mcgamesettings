package com.kraken.mcgamesettings;

import org.bukkit.World;

public class WeatherProcessing {
	
	  Main plugin;
	
  //Constructor
	public WeatherProcessing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Weather commands
	public void cycleWeather(World world, String command) {
		
		switch ( command ) {
		
		  //"rain", "raining", "snow", "snowing" command processing
			case "rain": 
				world.setWeatherDuration(18000);
				break;
		  //"rain", "raining", "snow", "snowing" command processing
			case "storm":
				world.setStorm(true);
				break;
		  //"sunny" command processing
			default: 
				world.setWeatherDuration(0);
				world.setStorm(false);
				break;
				
		}
		
	}
	
}
