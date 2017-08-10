package com.kraken.mcgamesettings;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ModeProcessing {
	
	  Main plugin;
	
  //Constructor
	public ModeProcessing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Gamemode cycle commands
	public void cycleMode(Player player, String command) {
		
		switch ( command ) {
		
		  //"creative" command processing
			case "creative":
			case "god":
			case "godmode":
				player.setGameMode(GameMode.CREATIVE);
				break;
		  //"survival" command processing
			case "survival":
				player.setGameMode(GameMode.SURVIVAL);
				break;
		  //"adventure" command processing
			case "adventure":
				player.setGameMode(GameMode.ADVENTURE);
				break;
		  //"spectator" command processing
			case "spectator":
				player.setGameMode(GameMode.SPECTATOR);
				break;
				
		}
		
	}
	
}
