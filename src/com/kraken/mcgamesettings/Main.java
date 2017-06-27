// ========================================================================
// |MCGAMESETTINGS v0.1
// | by Kraken | previously unreleased
// | code inspired by various Bukkit & Spigot devs -- thank you. 
// |
// | Always free & open-source! If this plugin is being sold or re-branded,
// | please let me know on the SpigotMC site, or wherever you can. Thanks!
// | Source code: https://github.com/randallarms/mcgamesettings
// ========================================================================

package com.kraken.mcgamesettings;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;

public class Main extends JavaPlugin {
  	
	public String VERSION = "0.1";
	
    private File optionsFile = new File("plugins/MCGameSettings", "options.yml");
    private FileConfiguration options = YamlConfiguration.loadConfiguration(optionsFile);
	
	TimeProcessing timeProc = new TimeProcessing(this);
    
	boolean enabled = true;
	boolean opRequired = false;
	boolean whitelist = false;
	
	ArrayList<String> isAllowed = new ArrayList<String>();
	
	@Override
    public void onEnable() {
    	
    	getLogger().info("MCGameSettings has been enabled.");
		PluginManager pm = getServer().getPluginManager();
		SettingsListener listener = new SettingsListener( getInstance(), timeProc );
		pm.registerEvents(listener, this);
		
		if ( !options.getBoolean("loaded") ) {
			
			options.set("loaded", true);
			options.set("enabled", true);
			options.set("opRequired", false);
			options.set("whitelist", false);
			
	        saveOptions();
	        
		}

        enabled = options.getBoolean("enabled");
        opRequired = options.getBoolean("opRequired");
        
        for (String id : getConfig().getKeys(false) ) {
        	
        	if ( getConfig().getBoolean(id + ".allowed") ) {
        		
        		isAllowed.add(id);
        		
        	}
        	
        }
		
    }
    
    @Override
    public void onDisable() {
    	
        getLogger().info("MCGameSettings has been disabled.");
        
    }
    
    public Main getInstance() {
    	return this;
    }
    
    public void playerAllowed(String UUIDString, boolean allowed) {
    	
      if (allowed) {
        isAllowed.add(UUIDString);
      } else {
        isAllowed.remove(UUIDString);
      }
      
    }
    
    public boolean saveOptions() {
    	
        try {
			options.save(optionsFile);
			return true;
		} catch (IOException e) {
			System.out.println("[MCGS] Failed to save options.yml file, expect errors.");
			return false;
		}
        
    }
    
  //MCGS commands
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
      //Player commands
        if ( sender instanceof Player ) {
        	
        	String command = cmd.getName();
    		Player player = (Player) sender;
    		String UUIDString = player.getUniqueId().toString();
     	
        	switch ( command.toLowerCase() ) {
        	
	        //Command: mcgs        
	    		case "mcgs":
	    			
	    			if (player.isOp()) {
      	        	  
        	            if (args.length == 1) {
        	            	
        	            	switch (args[0]) {
        	            	
        	            		case "on":
        	            		case "enable":
        	            		case "true":
        	            			options.set("enabled", true);
        	            			enabled = true;
        	            			saveOptions();
        	            			
        	            		case "off":
        	            		case "disable":
        	            		case "false":
        	            			options.set("enabled", false);
        	            			enabled = false;
        	            			saveOptions();
        	            			
        	            		default:
        	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + "Try \"/lmu <on/off>\".");
        	            			return true;
        	            	
        	            	}
        	            	
        	            } else {
        	            	player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + "CURRENT: MCGameSettings v" + VERSION + " (release)");
        	                return true;
        	            }
        	            
	    			}
        	
			  //Command: day
        	    case "day":
        	    case "night":
        	    case "sunrise": 
        	    case "sunset": 
        	    case "dawn": 
        	    case "dusk": 
        	    case "morning": 
        	    case "midnight": 
        	    	
                	if ( opRequired && !player.isOp() ) {
                		
                		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have MCGS privileges.");
                        return true;
                        
                	} else if ( !enabled ) {
        	    		
        	    		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "MCGS is currently disabled.");
        	    		return true;
        	    		
        	    	} else if ( !whitelist || isAllowed.contains(UUIDString) ) {
			        	
			              timeProc.cycleTime(player, command);
			              player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "It is now " + command + ".");
			              
			        } else {
			        	
			        	player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
			        	
			        }
        	    	
			        return true;
			        
			  //Command: allowSetting
        	    case "allowSetting":
        	    case "allowsetting":
        	    case "allowMCGS":
        	    case "allowmcgs":
			        
        	          Player targetPlayer;
        	          String targetUUID;
        	          
        	          if (player.isOp()) {
        	        	  
        	            if (args.length == 1) {
        	            	
        	            	if ( args[0].equals("*") ) {
        	            		
        	            		if ( options.getBoolean("whitelist") ) {
        	            			
        	            			whitelist = false;
        	            			options.set("whitelist", false);
        	            			saveOptions();
            	            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Whitelisting is now disabled.");
            	            		return true;
            	            		
        	            		} else {
        	            			
        	            			whitelist = true;
        	            			options.set("whitelist", true);
        	            			saveOptions();
            	            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Whitelisting is now enabled.");
            	            		return true;
            	            		
        	            		}
        	            		
        	            		
        	            		
        	            	}
        	            	
        	            	try {
        	            		targetPlayer = getServer().getPlayerExact(args[0]);
        	            		targetUUID = targetPlayer.getUniqueId().toString();
        	            	} catch (NullPointerException npe1) {
        	            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Player not found online.");
        	            		return true;
        	            	}
        	              
        	            	if ( !isAllowed.contains(targetUUID) ) {
        	            	  
        	            		getConfig().set(targetUUID + ".allowed", true);
        	            		isAllowed.add(targetUUID);
        	            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Added " + args[0] + " to the MCGS whitelist.");
        	                
        	            	} else {
        	            	  
        	            		getConfig().set(targetUUID + ".allowed", false);
        	            		isAllowed.remove(targetUUID);
        	            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Removed " + args[0] + " from the MCGS whitelist.");
        	                
        	            	}
        	              
        	            	saveConfig();
        	            	return true;
        	              
        	            }
        	            
        	            player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Try entering \"/allowSetting <player>\".");
        	            return true;
        	            
        	          }
        	          
        	          player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
        	          return true;
			        
			  //Command: opRequiredSettings
        	    case "opRequiredSettings":
        	    case "oprequiredSettings":
        	    case "opReqSettings":
        	    case "opreqSettings":
        			  
        	    	if ( args.length == 1 ) {
        	    		switch ( args[0].toLowerCase() ) {
        	    			case "on":
        	    			case "enable":
        	    			case "enabled":
        	    			case "true":
        	    				options.set("opRequired", true);
        	    				opRequired = true;
        	    				saveOptions();
        	    				return true;
        	    			case "off":
        	    			case "disable":
        	    			case "disabled":
        	    			case "false":
        	    				options.set("opRequired", false);
        	    				opRequired = false;
        	    				saveOptions();
        	    				return true;
        	    			default:
        	    				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Try entering \"/opReqSettings <on/off>\".");
        	        	    	return true;
        	    		}
        	    	}
        	    	
        	    	player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Try entering \"/opReqSettings <on/off>\".");
        	    	return true;
			        
			    default:
			    	  
			        player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Command not recognized.");
			        return true;
			    
	        }
        
        }
        
        return true;
        
    }
    
}
