package com.kraken.mcgamesettings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SettingsListener implements Listener {
	
	Main plugin;
	
  //Constructor
	public SettingsListener(Main plugin, TimeProcessing lp) {
		
		this.plugin = plugin;
		
	}
	
  //Silent death
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		if (plugin.silentDeath) {
			e.setDeathMessage("");
		}
		
	}
      
}
