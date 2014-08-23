package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import me.Ste3et_C0st.DiceFreezeMinigame.main;

public class loader
{
  public static config cc;
  public static FileConfiguration fc;
  
  public static void loadMap()
  {
	main.debugMSG("==================================");
	main.debugMSG("Dice main Map load hat begonnen");
	main.debugMSG("==================================");
    String name = "mapdata.yml";
    String folder = "/map";
	if(config.ExistMaps(folder) && config.isMaps(folder)){
		String[] ordner = new File("plugins/DiceFreeze" + folder).list();
		Integer is = ordner.length;
		for(int i = 0; i <= is; i++){
		    cc = new config(null);
		    if(config.ExistMaps(folder + "/" + i)){
		    	fc = cc.getConfig(name, folder + "/" + i);
			    main.debugMSG("  Arena [" + i +  "] wird geladen");
			    String Aname = fc.getString("Arena.name");
			    main.debugMSG("  - Name: " + Aname);
			    String wn = fc.getString("Arena.world");
			    main.debugMSG("  - World Loadet");
			    Integer min = fc.getInt("Arena.minPlayer");
			    main.debugMSG("  - Min Player loadet");
			    Double x1 = Double.valueOf(fc.getDouble("Arena.corner1.x"));
			    Double y1 = Double.valueOf(fc.getDouble("Arena.corner1.y"));
			    Double z1 = Double.valueOf(fc.getDouble("Arena.corner1.z"));
			    main.debugMSG("  - Corner 1 Loadet");		    
			    Double x2 = Double.valueOf(fc.getDouble("Arena.corner2.x"));
			    Double y2 = Double.valueOf(fc.getDouble("Arena.corner2.y"));
			    Double z2 = Double.valueOf(fc.getDouble("Arena.corner2.z"));
			    main.debugMSG("  - Corner 2 Loadet");	
			    Double x3 = Double.valueOf(fc.getDouble("Arena.arena.x"));
			    Double y3 = Double.valueOf(fc.getDouble("Arena.arena.y"));
			    Double z3 = Double.valueOf(fc.getDouble("Arena.arena.z"));
			    Float ya3 = Float.valueOf((float) fc.getDouble("Arena.arena.yaw"));
			    Float pi3 = Float.valueOf((float) fc.getDouble("Arena.arena.pitch"));
			    String ArenaWorld =  fc.getString("Arena.arena.world");
			    main.debugMSG("  - Spawn Loadet");	
			    Double x5 = Double.valueOf(fc.getDouble("Arena.exit.x"));
			    Double y5 = Double.valueOf(fc.getDouble("Arena.exit.y"));
			    Double z5 = Double.valueOf(fc.getDouble("Arena.exit.z"));
			    Float ya5 = Float.valueOf((float) fc.getDouble("Arena.exit.yaw"));
			    Float pi5 = Float.valueOf((float) fc.getDouble("Arena.exit.pitch"));
			    String w5 = fc.getString("Arena.exit.world");
			    Double x6 = Double.valueOf(fc.getDouble("Arena.lobby.x"));
			    Double y6 = Double.valueOf(fc.getDouble("Arena.lobby.y"));
			    Double z6 = Double.valueOf(fc.getDouble("Arena.lobby.z"));
			    Float ya6 = Float.valueOf((float) fc.getDouble("Arena.lobby.yaw"));
			    Float pi6 = Float.valueOf((float) fc.getDouble("Arena.lobby.pitch"));
			    String lobbyWorld =  fc.getString("Arena.lobby.world");
			    
			    main.debugMSG("  - Corner Exit Loadet");	
			    
			    int maxP = fc.getInt("Arena.maxPlayer");
			    int timR = fc.getInt("Arena.roundTimer");
			    int timL = fc.getInt("Arena.lobbyTimer");
			    Double mone = fc.getDouble("Arena.money");
			    
			    World w1 = Bukkit.getWorld(wn);
			    World w2 = Bukkit.getWorld(w5);
			    World w3 = Bukkit.getWorld(ArenaWorld);
			    World w4 = Bukkit.getWorld(lobbyWorld);
			    
			    Location l1 = new Location(w1, x1, y1, z1);
			    Location l2 = new Location(w1, x2, y2, z2);
			    Location l3 = new Location(w3, x3, y3, z3);
			    Location l5 = new Location(w2, x5, y5, z5);
			    Location l6 = new Location(w4, x6, y6, z6);
			    
			    l3.setPitch(pi3);
			    l5.setPitch(pi5);
			    l6.setPitch(pi6);
			    
			    l3.setYaw(ya3);
			    l5.setYaw(ya5);
			    l6.setYaw(ya6);
			    
			    ArenaManager.getManager().createArena(l1, l2, l3, l5, null, min, Aname, l6);
			    int AID = ArenaManager.getManager().getIDBackfromName(Aname);
			    main.debugMSG("  - Arena Registriert (" + AID + ")");	
			    ArenaManager.getManager().getArena(AID).setMaxPlayers(maxP);
			    main.debugMSG("    - Max Players: " + maxP);	
			    ArenaManager.getManager().getArena(AID).setLobbyTime(timL);
			    main.debugMSG("    - Lobby Time: " + timL);	
			    ArenaManager.getManager().getArena(AID).setGameTimer(timR);
			    main.debugMSG("    - Round Time: " + timR);
			    ArenaManager.getManager().getArena(AID).setMoney(mone);
			    main.debugMSG("    - Money: " + mone);
			    
			    if(fc.isSet("Arena.team")){
			    	ArenaManager.getManager().getArena(AID).teamlength(Integer.parseInt(fc.getString("Arena.size")));
			    	for(String s : fc.getConfigurationSection("Arena.team").getKeys(false)){
			    		main.debugMSG("  - Team : " + s);
			    		String TeamName = fc.getString("Arena.team." + s + ".name");
			    		String TeamColor = fc.getString("Arena.team." + s + ".color");
			    		Double x = fc.getDouble("Arena.team." + s + ".Location.x");
			    		Double y = fc.getDouble("Arena.team." + s + ".Location.y");
			    		Double z = fc.getDouble("Arena.team." + s + ".Location.z");
					    Float ya = Float.valueOf((float) fc.getDouble("Arena.team." + s + ".Location.yaw"));
					    Float pi = Float.valueOf((float) fc.getDouble("Arena.team." + s +".Location.pitch"));
			    		Location TeamSpawn = new Location(w3, x, y, z);
			    		TeamSpawn.setPitch(pi);
			    		TeamSpawn.setYaw(ya);
			    		ArenaManager.getManager().getArena(AID).createTeam(Integer.parseInt(s), TeamSpawn, TeamName, TeamColor);
			    	}
			    }
			    
			    if(fc.isSet("Arena.sign")){
			    	main.debugMSG("     - Signs found: ");
			    	int u = 0;
			    	for(String s: fc.getConfigurationSection("Arena.sign").getKeys(false)){
			    		main.debugMSG("      - Sign (" + u + ") found");
			    		Double x = fc.getDouble("Arena.sign." + s + ".x");
			    		Double y = fc.getDouble("Arena.sign." + s + ".y");
			    		Double z = fc.getDouble("Arena.sign." + s + ".z");
			    		String world = fc.getString("Arena.sign." + s + ".world");
			    		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			    		if(Bukkit.getWorld(world) != null){
			    			if(!loc.getBlock().isEmpty()){
				    			BlockState state = loc.getBlock().getState();
				    			if(state instanceof Sign){
						    		ArenaManager.getManager().getArena(AID).addSign(loc);
						    		ArenaManager.getManager().getArena(AID).updateSign(loc);
				    			}else{
				    				main.debugMSG("       - No Sign Found ");
				    			}
				    		}else{
				    			main.debugMSG("       - No Block Found ");
				    		}
			    		}else{
			    			main.debugMSG("       - World not found ");
			    		}
			    		
			    		u++;
			    	}
			    }else{
			    	main.debugMSG("     - No signs found");
			    }
			    fc = null;
			    cc = null;
			    	main.debugMSG("----------------------------------");
		    }
		    
		}
	}
	main.debugMSG("==================================");
  }
}
