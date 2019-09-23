package com.kraken.mcgamesettings;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConsoleCommands {
	
	  Main plugin;
	  
	  String VERSION;
	  FileConfiguration options;
	  
	  boolean enabled;
	  boolean whitelist;
	  boolean opRequired;
	  boolean silentDeath;
	  boolean buildPermReq;
	  boolean destroyPermReq;
	  
	  ArrayList<String> isAllowed;
	  
	  TimeProcessing timeProc;
	  WeatherProcessing weatherProc;
	  ModeProcessing modeProc;

	
  //Constructor
	public ConsoleCommands(Main plugin) {
		
		this.plugin = plugin;
		
		this.VERSION = plugin.VERSION;
		this.options = plugin.options;
	  
	    this.enabled = plugin.enabled;
	    this.whitelist = plugin.whitelist;
	    this.opRequired = plugin.opRequired;
	    this.silentDeath = plugin.silentDeath;
	    this.buildPermReq = plugin.buildPermReq;
	    this.destroyPermReq = plugin.destroyPermReq;
	  
	    this.isAllowed = plugin.isAllowed;
	  
	    this.timeProc = plugin.timeProc;
	    this.weatherProc = plugin.weatherProc;
	    this.modeProc = plugin.modeProc;
	    
	    
	
	}
	
  //Commands
	@SuppressWarnings("deprecation")
	public boolean execute(boolean isPlayer, Player player, String command, String[] args) {
		
		//Commands
    	switch ( command.toLowerCase() ) {
    	
          //Command: mcgs < on / off / silentDeath <on/off> / opRequired <on/off> >        
    		case "mcgs":
    			
    			//Currently requires "mcgs.op" perm to use commands
    			if (isPlayer) {
    				if (!player.hasPermission("op")) {
    					player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
    					return true;
    				}
    			}
  	        	  
	            switch ( args.length ) {
	            
		            case 0:
		            	if (isPlayer) {
		            		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | CURRENT: MCGameSettings v" + VERSION + " (release)");
            			} else {
            				System.out.println("[MCGS] CURRENT: MCGameSettings v" + VERSION + " (release)");
            			}
		            	
		                return true;
		            	
		            default:
		            	switch ( args[0].toLowerCase() ) {
		            	
		            		case "on":
		            		case "enable":
		            		case "true":
		            			options.set("enabled", true);
		            			enabled = true;
		            			plugin.saveOptions();
		            			
		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | MCGameSettings is now enabled.");
		            			} else {
		            				System.out.println("[MCGS] MCGameSettings is now enabled.");
		            			}
		            			
		            			return true;
		            			
		            		case "off":
		            		case "disable":
		            		case "false":
		            			options.set("enabled", false);
		            			enabled = false;
		            			plugin.saveOptions();

		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | MCGameSettings is now disabled.");
		            			} else {
		            				System.out.println("[MCGS] MCGameSettings is now disabled.");
		            			}
		            			
		            			return true;
		            			
		            		case "silentDeath":
		            		case "silentdeath":
		            			
    	            			switch ( args[1].toLowerCase() ) {
            	            	
    	    	            		case "on":
    	    	            		case "enable":
    	    	            		case "true":
    	    	            			options.set("silentDeath", true);
    	    	            			silentDeath = true;
    	    	            			plugin.saveOptions();

    			            			if (isPlayer) {
    			            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | Death messages are now silenced.");
    			            			} else {
    			            				System.out.println("[MCGS] Death messages are now silenced.");
    			            			}
    			            			
    	    	            			return true;
    	    	            			
    	    	            		case "off":
    	    	            		case "disable":
    	    	            		case "false":
    	    	            			options.set("silentDeath", false);
    	    	            			silentDeath = false;
    	    	            			plugin.saveOptions();

    			            			if (isPlayer) {
    			            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | Death messages are no longer silenced.");
    			            			} else {
    			            				System.out.println("[MCGS] Death messages are no longer silenced.");
    			            			}
    			            			
    	    	            			return true;
    	    	            			
    	    	            		default:

    			            			if (isPlayer) {
    			            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | Try \"/mcgs silentDeath <on/off>\".");
    			            			} else {
    			            				System.out.println("[MCGS] Try \"/mcgs silentDeath <on/off>\".");
    			            			}
    			            			
    	    	            			return true;
    	            	
    	            			}
		            			
		            	  //Command: opRequired
		            	    case "oprequired":
		            	    case "opreq":
		            	    		
	            	    		switch ( args[1].toLowerCase() ) {
	            	    		
	            	    			case "on":
	            	    			case "enable":
	            	    			case "enabled":
	            	    			case "true":
	            	    				options.set("opRequired", true);
	            	    				opRequired = true;
	            	    				plugin.saveOptions();
	            	    				
	            	            		if (isPlayer) {
	            	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "The OP requirement is now enabled.");
	        	            			} else {
	        	            				System.out.println("[MCGS] The OP requirement is now enabled.");
	        	            			}
	            	    				
	            	    				return true;
	            	    				
	            	    			case "off":
	            	    			case "disable":
	            	    			case "disabled":
	            	    			case "false":
	            	    				options.set("opRequired", false);
	            	    				opRequired = false;
	            	    				plugin.saveOptions();
	            	    				
	            	    				if (isPlayer) {
	            	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "The OP requirement is now disabled.");
	        	            			} else {
	        	            				System.out.println("[MCGS] The OP requirement is now disabled.");
	        	            			}
	            	    				
	            	    				return true;
	            	    				
	            	    			default:
	            	    				if (isPlayer) {
	            	    					player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Try entering \"/mcgs opReq <on/off>\".");
	        	            			} else {
	        	            				System.out.println("[MCGS] Try entering \"/mcgs opReq <on/off>\".");
	        	            			}
	            	    				
	            	        	    	return true;
	            	        	    	
	            	    		}
	    	            			
		            		default:

		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | Try \"/mcgs <on/off>\".");
		            			} else {
		            				System.out.println("[MCGS] Try \"/mcgs <on/off>\".");
		            			}
		            			
		            			return true;
		            	
		            	}
	            	
	            }
	            
	  	  //Commands: creative, survival, adventure, spectator, god, godmode
    	    case "creative":
    	    case "survival":
    	    case "adventure":
    	    case "spectator":
    	    case "god":
    	    case "godmode":
    	    	
    	    	if ( opRequired && !player.isOp() ) {
            		
            		if (isPlayer) {
            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have MCGS privileges.");
        			} else {
        				System.out.println("[MCGS] You do not have MCGS privileges.");
        			}
            		
                    return true;
                    
            	} else if ( !enabled ) {
            		
            		if (isPlayer) {
            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "MCGS is currently disabled.");
        			} else {
        				System.out.println("[MCGS] MCGS is currently disabled.");
        			}
            		
    	    		return true;
    	    		
    	    	} else if ( !whitelist || isAllowed.contains(player.getUniqueId().toString()) ) {
		        	
		        	if (isPlayer) {
		        		modeProc.cycleMode(player, command);
		        		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Your gamemode has been set to: " + command + ".");
        			} else {
        				System.out.println("[MCGS] This is a player-only command.");
        			}
		        	
		        	return true;
		              
		        } else {

		        	if (isPlayer) {
		        		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
        			} else {
        				System.out.println("[MCGS] You do not have permission to use this command.");
        			}
		        	
		        	return true;
		        	
		        }
    	
		  //Commands: day, night, sunrise, sunset, dawn, dusk, morning, midnight
    	    case "day":
    	    case "night":
    	    case "sunrise": 
    	    case "sunset": 
    	    case "dawn": 
    	    case "dusk": 
    	    case "morning": 
    	    case "midnight": 
    	    	
            	if ( opRequired && !player.isOp() ) {

        			if (isPlayer) {
        				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have MCGS privileges.");
        			} else {
        				System.out.println("[MCGS] You do not have MCGS privileges.");
        			}
        			
                    return true;
                    
            	} else if ( !enabled ) {

        			if (isPlayer) {
        				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "MCGS is currently disabled.");
        			} else {
        				System.out.println("[MCGS] MCGS is currently disabled.");
        			}
        			
    	    		return true;
    	    		
    	    	} else if ( !whitelist || isAllowed.contains(player.getUniqueId().toString()) ) {
		        	
        			if (isPlayer) {
        				timeProc.cycleTime(player.getWorld(), command);
        				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "It is now " + command + ".");
        			} else {
        				timeProc.cycleTime( plugin.getServer().getWorlds().get(0) , command );
        				System.out.println("[MCGS] It is now " + command + ".");
        			}
        			
		            return true;
		              
		        } else {

        			if (isPlayer) {
        				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
        			} else {
        				System.out.println("[MCGS] You do not have permission to use this command.");
        			}
        			
		        	return true;
		        	
		        }
		        
		  //Commands: rain/raining, snow/snowing, thunder/lightning/storm/thunderstorm, sunny
		        
    	    case "rain":
    	    case "raining":
    	    case "snow":
    	    case "snowing":
			case "thunder":
			case "lightning":
			case "storm":
			case "thunderstorm":
    	    case "sunny":
    	    	
    	    	if ( opRequired && !player.isOp() ) {
            		
            		if (isPlayer) {
            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have MCGS privileges.");
        			} else {
        				System.out.println("[MCGS] You do not have MCGS privileges.");
        			}
            		
                    return true;
                    
            	} else if ( !enabled ) {
            		
            		if (isPlayer) {
            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "MCGS is currently disabled.");
        			} else {
        				System.out.println("[MCGS] MCGS is currently disabled.");
        			}
            		
    	    		return true;
    	    		
    	    	} else if ( !whitelist || isAllowed.contains(player.getUniqueId().toString()) ) {
		        	
		        	if (isPlayer) {
		        		weatherProc.cycleWeather(player.getWorld(), command);
		        		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "The weather has been set to: " + command + ".");
        			} else {
        				weatherProc.cycleWeather( plugin.getServer().getWorlds().get(0) , command );
        				System.out.println("[MCGS] The weather has been set to: " + command + ".");
        			}
		        	
		        	return true;
		              
		        } else {

		        	if (isPlayer) {
		        		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
        			} else {
        				System.out.println("[MCGS] You do not have permission to use this command.");
        			}
		        	
		        	return true;
		        	
		        }
		        
		  //Command: allowSetting
    	    case "allowsetting":
    	    case "allowmcgs":
		        
    	          Player targetPlayer;
    	          String targetUUID;
    	          
    	          if (player.isOp()) {
    	        	  
    	            if (args.length == 1) {
    	            	
    	            	if ( args[0].equals("*") ) {
    	            		
    	            		if ( options.getBoolean("whitelist") ) {
    	            			
    	            			whitelist = false;
    	            			options.set("whitelist", false);
    	            			plugin.saveOptions();
    	            			
    	            			if (isPlayer) {
    	            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Whitelisting is now disabled.");
    	            			} else {
    	            				System.out.println("[MCGS] Whitelisting is now disabled.");
    	            			}
        	            		
        	            		return true;
        	            		
    	            		} else {
    	            			
    	            			whitelist = true;
    	            			options.set("whitelist", true);
    	            			plugin.saveOptions();
    	            			
    	            			if (isPlayer) {
    	            				player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Whitelisting is now enabled.");
    	            			} else {
    	            				System.out.println("[MCGS] Whitelisting is now enabled.");
    	            			}
        	            		
        	            		return true;
        	            		
    	            		}
    	            		
    	            		
    	            		
    	            	}
    	            	
    	            	try {
    	            		targetPlayer = plugin.getServer().getPlayerExact(args[0]);
    	            		targetUUID = targetPlayer.getUniqueId().toString();
    	            	} catch (NullPointerException npe1) {
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Player not found online.");
	            			} else {
	            				System.out.println("[MCGS] Player not found online.");
	            			}
    	            		
    	            		return true;
    	            	}
    	              
    	            	if ( !isAllowed.contains(targetUUID) ) {
    	            	  
    	            		plugin.getConfig().set(targetUUID + ".allowed", true);
    	            		isAllowed.add(targetUUID);
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Added " + args[0] + " to the MCGS whitelist.");
	            			} else {
	            				System.out.println("[MCGS] Added " + args[0] + " to the MCGS whitelist.");
	            			}
    	                
    	            	} else {
    	            	  
    	            		plugin.getConfig().set(targetUUID + ".allowed", false);
    	            		isAllowed.remove(targetUUID);
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Removed " + args[0] + " from the MCGS whitelist.");
	            			} else {
	            				System.out.println("[MCGS] Removed " + args[0] + " from the MCGS whitelist.");
	            			}
    	                
    	            	}
    	              
    	            	plugin.saveConfig();
    	            	return true;
    	              
    	            }
    	            
    	            if (isPlayer) {
    	            	player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Try entering \"/allowSetting <player>\".");
        			} else {
        				System.out.println("[MCGS] Try entering \"/allowSetting <player>\".");
        			}
    	            
    	            return true;
    	            
    	          }
    	          
    	          if (isPlayer) {
    	        	  player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "You do not have permission to use this command.");
      			  } else {
      				  System.out.println("[MCGS] You do not have permission to use this command.");
      			  }
    	          
    	          return true;
		        
		    default:
		    	  
		    	if (isPlayer) {
		    		player.sendMessage(ChatColor.GREEN + "[MCGS]" + ChatColor.GRAY + " | " + "Command not recognized.");
    			} else {
    				System.out.println("[MCGS] Command not recognized.");
    			}
		        
		        return true;
		    
        }
		
	}
	
}
