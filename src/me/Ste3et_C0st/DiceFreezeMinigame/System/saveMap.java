package me.Ste3et_C0st.DiceFreezeMinigame.System;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.Ste3et_C0st.DiceFreezeMinigame.main;

public class saveMap
{
  public static config cc;
  public static FileConfiguration fc;
  
  public static void saveMap2(int i)
  {
    main.debugMSG("Dice main Map Save hat begonnen");
    main.debugMSG("==================================");
		Arena a = ArenaManager.getManager().getArena(i);
		main.debugMSG(" Arena [" + i + "]");
	    String name = "mapdata.yml";
	    String folder = "/map/" + i + "";
	    cc = new config(null);
	    fc = cc.getConfig(name, folder);
	    
	    fc.set("Arena", "");
	    cc.saveConfig(name, fc, folder);
	    
	    cc = new config(null);
	    fc = cc.getConfig(name, folder);
	    fc.set("Arena.name", a.name);
	    main.debugMSG("  - Name Saving");
	    fc.set("Arena.world", a.corner1.getWorld().getName());
	    main.debugMSG("  - World Name Saving");
	    fc.set("Arena.minPlayer", a.getMin());
	    main.debugMSG("  - Min Player Saving");
	    fc.set("Arena.maxPlayer", a.getMaxPlayes());
	    main.debugMSG("  - Max Player Saving");
	    fc.set("Arena.roundTimer", a.getRoundTimer());
	    main.debugMSG("  - Round Timer Saving");
	    fc.set("Arena.lobbyTimer", a.getLobbyTimer());
	    main.debugMSG("  - Min lobby Timer Saving");
	    fc.set("Arena.money", a.getMoney());
	    main.debugMSG("  - Money Saving");
	    fc.set("Arena.corner1.x", a.corner1.getX());
	    fc.set("Arena.corner1.y", a.corner1.getY());
	    fc.set("Arena.corner1.z", a.corner1.getZ());
	    main.debugMSG("  - Corner 1 Saving");
	    fc.set("Arena.lobby.x", a.lobby.getX());
	    fc.set("Arena.lobby.y", a.lobby.getY());
	    fc.set("Arena.lobby.z", a.lobby.getZ());
	    fc.set("Arena.lobby.yaw", a.lobby.getYaw());
	    fc.set("Arena.lobby.pitch", a.lobby.getPitch());
	    fc.set("Arena.lobby.world", a.lobby.getWorld().getName());
	    main.debugMSG("  - Lobby Saving");
	    fc.set("Arena.corner2.x", a.corner2.getX());
	    fc.set("Arena.corner2.y", a.corner2.getY());
	    fc.set("Arena.corner2.z", a.corner2.getZ());
	    main.debugMSG("  - Corner 2 Saving");
	    fc.set("Arena.arena.x", a.arena.getX());
	    fc.set("Arena.arena.y", a.arena.getY());
	    fc.set("Arena.arena.z", a.arena.getZ());
	    fc.set("Arena.arena.yaw", a.arena.getYaw());
	    fc.set("Arena.arena.pitch", a.arena.getPitch());
	    fc.set("Arena.arena.world", a.arena.getWorld().getName());
	    main.debugMSG("  - Arena Saving");
	    fc.set("Arena.exit.x", a.exit.getX());
	    fc.set("Arena.exit.y", a.exit.getY());
	    fc.set("Arena.exit.z", a.exit.getZ());
	    fc.set("Arena.exit.yaw", a.exit.getYaw());
	    fc.set("Arena.exit.pitch", a.exit.getPitch());
	    fc.set("Arena.exit.world", a.exit.getWorld().getName());
	    main.debugMSG("  - Exit Saving");
	    if(a.teamS() > 0){
	    	if(a.teamS() == 2){
	    		if(a.returnString(1) != ""){
		    		fc.set("Arena.size", a.teamS());
			    	fc.set("Arena.team.1.name", a.returnString(1));
			    	fc.set("Arena.team.1.color", a.returnColor(1));
			    	fc.set("Arena.team.1.Location.x", a.returnLocation(1).getX());
			    	fc.set("Arena.team.1.Location.y", a.returnLocation(1).getY());
			    	fc.set("Arena.team.1.Location.z", a.returnLocation(1).getZ());
			    	fc.set("Arena.team.1.Location.yaw", a.returnLocation(1).getYaw());
			    	fc.set("Arena.team.1.Location.pitch", a.returnLocation(1).getPitch());
	    		}

	    		if(a.returnString(2) != ""){
			    	fc.set("Arena.team.2.name", a.returnString(2));
			    	fc.set("Arena.team.2.color", a.returnColor(2));
			    	fc.set("Arena.team.2.Location.x", a.returnLocation(2).getX());
			    	fc.set("Arena.team.2.Location.y", a.returnLocation(2).getY());
			    	fc.set("Arena.team.2.Location.z", a.returnLocation(2).getZ());
			    	fc.set("Arena.team.2.Location.yaw", a.returnLocation(2).getYaw());
			    	fc.set("Arena.team.2.Location.pitch", a.returnLocation(2).getPitch());
	    		}
	    	}else if(a.teamS() == 4){
	    		fc.set("Arena.size", a.teamS());
		    	fc.set("Arena.team.1.name", a.returnString(1));
		    	fc.set("Arena.team.1.color", a.returnColor(1));
		    	fc.set("Arena.team.1.Location.x", a.returnLocation(1).getX());
		    	fc.set("Arena.team.1.Location.y", a.returnLocation(1).getY());
		    	fc.set("Arena.team.1.Location.z", a.returnLocation(1).getZ());
		    	fc.set("Arena.team.1.Location.yaw", a.returnLocation(1).getYaw());
		    	fc.set("Arena.team.1.Location.pitch", a.returnLocation(1).getPitch());
		    	
		    	fc.set("Arena.team.2.name", a.returnString(2));
		    	fc.set("Arena.team.2.color", a.returnColor(2));
		    	fc.set("Arena.team.2.Location.x", a.returnLocation(2).getX());
		    	fc.set("Arena.team.2.Location.y", a.returnLocation(2).getY());
		    	fc.set("Arena.team.2.Location.z", a.returnLocation(2).getZ());
		    	fc.set("Arena.team.2.Location.yaw", a.returnLocation(2).getYaw());
		    	fc.set("Arena.team.2.Location.pitch", a.returnLocation(2).getPitch());
		    	
		    	fc.set("Arena.team.3.name", a.returnString(3));
		    	fc.set("Arena.team.3.color", a.returnColor(3));
		    	fc.set("Arena.team.3.Location.x", a.returnLocation(3).getX());
		    	fc.set("Arena.team.3.Location.y", a.returnLocation(3).getY());
		    	fc.set("Arena.team.3.Location.z", a.returnLocation(3).getZ());
		    	fc.set("Arena.team.3.Location.yaw", a.returnLocation(3).getYaw());
		    	fc.set("Arena.team.3.Location.pitch", a.returnLocation(3).getPitch());
		    	
		    	fc.set("Arena.team.4.name", a.returnString(4));
		    	fc.set("Arena.team.4.color", a.returnColor(4));
		    	fc.set("Arena.team.4.Location.x", a.returnLocation(4).getX());
		    	fc.set("Arena.team.4.Location.y", a.returnLocation(4).getY());
		    	fc.set("Arena.team.4.Location.z", a.returnLocation(4).getZ());
		    	fc.set("Arena.team.4.Location.yaw", a.returnLocation(4).getYaw());
		    	fc.set("Arena.team.4.Location.pitch", a.returnLocation(4).getPitch());
	    	}

	    }
	    
	    
	    if(a.getSign().size() > 0){
	    	main.debugMSG("   - Signs found");
	    	int u = 0;
	    	for(Location l : a.getSign()){
	    		main.debugMSG("    - (" + u + ") Sign save");
	    		fc.set("Arena.sign." + u + ".x", l.getX());
	    		fc.set("Arena.sign." + u + ".y", l.getY());
	    		fc.set("Arena.sign." + u + ".z", l.getZ());
	    		fc.set("Arena.sign." + u + ".world", l.getWorld().getName());
	    		u++;
	    	}
	    }
	    cc.saveConfig(name, fc, folder);
	    main.debugMSG("----------------------------------");
    main.debugMSG("==================================");
  }
}
