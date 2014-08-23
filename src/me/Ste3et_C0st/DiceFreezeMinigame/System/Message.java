package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Message {
	  public static config cc;
	  public static FileConfiguration fc;
	  public static List<String> message = new ArrayList<String>(); 
	  public static void loadDefaults(String s){
		  String file = s;
		  String folder = "/language";
		    cc = new config(null);
		    fc = cc.getConfig(file, folder);
		    if(s.equalsIgnoreCase("de_DE")){
		    	/* 0 */ fc.addDefault("Language.NoPermissions", "&cDazu hast du keine Berechtigung.");
		    	/* 1 */ fc.addDefault("Language.create.ArenaPos", "Arena Position&6 %POS% &7festgelegt");
		    	/* 2 */ fc.addDefault("Language.create.LobbyPos", "Lobby Spawn festgelegt");
		    	/* 3 */ fc.addDefault("Language.create.ArenaSpawn", "Arena Spawn festgelegt");
		    	/* 4 */ fc.addDefault("Language.create.ExitPos", "Exit Spawn festgelegt");
		    	/* 5 */ fc.addDefault("Language.create.MissingCorner", "Arena Corner&c %CORNER% &7fehlt");
		    	/* 6 */ fc.addDefault("Language.create.MissingLobby", "Arena Lobby fehlt");
		    	/* 7 */ fc.addDefault("Language.create.MissingSpawn", "Arena Spawn fehlt");
		    	/* 8 */ fc.addDefault("Language.create.MissingExit", "Arena Exit fehlt");
		    	/* 9 */ fc.addDefault("Language.create.create", "Arena %ARENA% wurde erstellt");
			    
		    	/* 10 */ fc.addDefault("Language.leave.Player", "Der Spieler&6 %PLAYER% &7hat die BuildIT Arena verlassen.");
		    	/* 11 */ fc.addDefault("Language.leave.Private", "Du hast die BuildIT arena verlassen.");
		    	/* 12 */ fc.addDefault("Language.leave.MinPlayer", "Die Arena wurde wegen zu wenigen Spielern Beendet.");
		    	/* 13 */ fc.addDefault("Language.leave.NotInArena", "Du befindest dich nicht in einer Arena.");
			    
		    	/* 14 */ fc.addDefault("Language.join.Player", "Der Spieler&6 %PLAYER% &7hat die BuildIT Arena betreten.");
		    	/* 15 */ fc.addDefault("Language.join.Private", "Du bist der BuildIT Arena Beigetreten.");
		    	/* 16 */ fc.addDefault("Language.join.ArenaNotExist", "Die Arena Existiert nicht");
		    	/* 17 */ fc.addDefault("Language.join.AlreadyInArena", "Du befindest dich bereits in einer BuildIT arena.");
		    	/* 18 */ fc.addDefault("Language.join.AlreadyRun", "Arena laeuft bereits.");
		    	/* 19 */ fc.addDefault("Language.join.MissingPlayer", "Es fehlen noch %MISSING% Spieler");
		    	
		    	/* 27 */ fc.addDefault("Language.help.return", "Bitte gib &c/bt %COMMAND% <side> &7ein");
		    	/* 28 */ fc.addDefault("Language.help.bad", "falsche argumente");
			    fc.options().copyDefaults(true);
			    cc.saveConfig(file, fc, folder);
		    }else{
		    	
		    	/* 0 */ fc.addDefault("Language.NoPermissions", "&cDazu hast du keine Berechtigung.");
		    	/* 1 */ fc.addDefault("Language.create.ArenaPos", "Arena Position&6 %POS% &7festgelegt");
		    	/* 2 */ fc.addDefault("Language.create.LobbyPos", "Lobby Spawn festgelegt");
		    	/* 3 */ fc.addDefault("Language.create.ArenaSpawn", "Arena Spawn festgelegt");
		    	/* 4 */ fc.addDefault("Language.create.ExitPos", "Exit Spawn festgelegt");
		    	/* 5 */ fc.addDefault("Language.create.MissingCorner", "Arena Corner&c %CORNER% &7fehlt");
		    	/* 6 */ fc.addDefault("Language.create.MissingLobby", "Arena Lobby fehlt");
		    	/* 7 */ fc.addDefault("Language.create.MissingSpawn", "Arena Spawn fehlt");
		    	/* 8 */ fc.addDefault("Language.create.MissingExit", "Arena Exit fehlt");
		    	/* 9 */ fc.addDefault("Language.create.create", "Arena %ARENA% wurde erstellt");
			    
		    	/* 10 */ fc.addDefault("Language.leave.Player", "Der Spieler&6 %PLAYER% &7hat die BuildIT Arena verlassen.");
		    	/* 11 */ fc.addDefault("Language.leave.Private", "Du hast die BuildIT arena verlassen.");
		    	/* 12 */ fc.addDefault("Language.leave.MinPlayer", "Die Arena wurde wegen zu wenigen Spielern Beendet.");
		    	/* 13 */ fc.addDefault("Language.leave.NotInArena", "Du befindest dich nicht in einer Arena.");
			    
		    	/* 14 */ fc.addDefault("Language.join.Player", "Der Spieler&6 %PLAYER% &7hat die BuildIT Arena betreten.");
		    	/* 15 */ fc.addDefault("Language.join.Private", "Du bist der BuildIT Arena Beigetreten.");
		    	/* 16 */ fc.addDefault("Language.join.ArenaNotExist", "Die Arena Existiert nicht");
		    	/* 17 */ fc.addDefault("Language.join.AlreadyInArena", "Du befindest dich bereits in einer BuildIT arena.");
		    	/* 18 */ fc.addDefault("Language.join.AlreadyRun", "Arena laeuft bereits.");
		    	/* 19 */ fc.addDefault("Language.join.MissingPlayer", "Es fehlen noch %MISSING% Spieler");
		    	
		    	/* 27 */ fc.addDefault("Language.help.return", "Bitte gib &c/bt %COMMAND% <side> &7ein");
		    	/* 28 */ fc.addDefault("Language.help.bad", "falsche argumente");
			    fc.options().copyDefaults(true);
			    cc.saveConfig(file, fc, folder);
		    }

	  }
	  
	  public static void loadLanguage(String s){
		  	String file = s;
		  	String folder = "/language";
		    cc = new config(null);
		    fc = cc.getConfig(file, folder);

		    //No Permissions (0)
		    String noPermissions = fc.getString("Language.NoPermissions");
		    String returnNoPermissions = replaceColorCodes(noPermissions);
		    message.add(returnNoPermissions);
		    
		    //Create (1-9)
		    for(String st : fc.getConfigurationSection("Language.create").getKeys(false)){
		    	String ret = replaceColorCodes(fc.getString("Language.create." + st));
		    	message.add(ret);
		    }
		    
		    //Leave (10-13)
		    for(String st : fc.getConfigurationSection("Language.leave").getKeys(false)){
		    	String ret = replaceColorCodes(fc.getString("Language.leave." + st));
		    	message.add(ret);
		    }
		    
		    //Join (14-19)
		    for(String st : fc.getConfigurationSection("Language.join").getKeys(false)){
		    	String ret = replaceColorCodes(fc.getString("Language.join." + st));
		    	message.add(ret);
		    }

		    
		    //Next-Round (27)
		    for(String st : fc.getConfigurationSection("Language.help").getKeys(false)){
		    	String ret = replaceColorCodes(fc.getString("Language.help." + st));
		    	message.add(ret);
		    }
	  }
	  
	  public static String replaceColorCodes(String s){
		  return ChatColor.translateAlternateColorCodes('&', s);
	  }
	  
	  public static String replace(String s, String a, String n){
		  return s.replace(a, n);
	  }
}
