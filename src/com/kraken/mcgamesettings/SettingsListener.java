package com.kraken.mcgamesettings;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
	
  //Build perm check
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		if ( plugin.buildPermReq && !player.hasPermission("settings.build") ) {
			event.setCancelled(true);
		}
		
	}
	
  //Destroy perm check
	@EventHandler
	public void onBlockDestroy(BlockBreakEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		if ( plugin.destroyPermReq && !player.hasPermission("settings.destroy") ) {
			event.setCancelled(true);
		}
		
	}
      
}
