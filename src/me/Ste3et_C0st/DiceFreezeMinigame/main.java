package me.Ste3et_C0st.DiceFreezeMinigame;

import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnCommand;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnFallingSand;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnInventoryOpenEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerBlockPlace;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerDamageEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerDeath;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerDrop;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerInteractEntityEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerInterarcEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerMoveEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.OnPlayerQuitEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.Listener.SignEvent;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Message;
import me.Ste3et_C0st.DiceFreezeMinigame.System.loader;
import me.Ste3et_C0st.DiceFreezeMinigame.System.saveMap;
import me.Ste3et_C0st.DiceTokenAPI.TokenAPI;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;

public class main extends JavaPlugin{
	private static main instance;
	public static EffectManager effectManager;
	public static String s = "§7[§2FreezeIT§7] ";
	public static TokenAPI token;
	public static Permission perms = null;
	public void onEnable(){
        EffectLib lib = EffectLib.instance();
        effectManager = new EffectManager(lib);
		loader.loadMap();
		Message.loadDefaults("de_DE");
		Message.loadLanguage("de_DE");
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerInterarcEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDamageEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnFallingSand(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDrop(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerQuitEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerBlockPlace(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryOpenEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnCommand(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerInteractEntityEvent(), this);
		instance = this;
		token = (TokenAPI) Bukkit.getPluginManager().getPlugin("DiceTokenAPI");
		setupPermissions();
	}
	
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	  
	
	public void onDisable(){
		  instance = null;
		  if(ArenaManager.getManager().arenaSize > 0){
			  int i = ArenaManager.getManager().arenaSize;
			  for(int o = 0; o <= i; o++){
				  if(ArenaManager.getManager().getArena(o) != null){
					  if(ArenaManager.getManager().getArena(o).getPlayers().size() > 0){
						  ArenaManager.getManager().finishGame(o);
					  }
				  }
			  }
		  }
	}
	
	  public static main getInstance() {
			return instance;
		  }
	
	  public static void debugMSG(String s){
			  System.out.print(s);
	  }
	  
	  public static boolean isInt(String string) {
		  	try {
		  	Integer.parseInt(string);
		  	} catch (NumberFormatException nFE) {
		  	return false;
		  	}
		  	return true;
		  	}
		  
		  public static boolean isDouble(String string) {
			  	try {
			  	Double.parseDouble(string);
			  	} catch (NumberFormatException nFE) {
			  	return false;
			  	}
			  	return true;
			  	}
		  
	  @SuppressWarnings("deprecation" )
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(cmd.getName().equalsIgnoreCase("freeze")){
					if(args.length == 0){
						p.sendMessage("§8======-" + s + "§8-======");
						p.sendMessage("§7/freeze leave");
						p.sendMessage("§7/freeze team spawn");
						p.sendMessage("§7/freeze team setsize <arena> <int>");
						p.sendMessage("§7/freeze team setname <name>");
						p.sendMessage("§7/freeze team setcolor <colorecode>");
						p.sendMessage("§7/freeze team create <arena> <int>");
						p.sendMessage("§8============================");
						return true;
					}else if(args.length == 1){
						if(args[0].equalsIgnoreCase("leave")){
							if(p.hasPermission("FreezeIT.spieler")){
								if(ArenaManager.getManager().isInGame(p)){
									ArenaManager.getManager().removePlayer(p);
									p.getInventory().clear();
									p.updateInventory();
								}else{
									p.sendMessage("Du befindest dich nicht in einer Arena");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
						}else if(args[0].equalsIgnoreCase("list")){
							for(int i = 1; i <= ArenaManager.getManager().arenaSize; i++){
								if(ArenaManager.getManager().getArena(i) != null){
									p.sendMessage("[" + i +"]" + " " + ArenaManager.getManager().getArena(i).getName() + " id: " + ArenaManager.getManager().getArena(i).getName());
								}
								
							}
						}
						return true;
					}else if(args.length == 2){
						if(args[0].equalsIgnoreCase("new")){
							if(p.hasPermission("FreezeIT.admin")){
								Editor.enter(args[1], p);
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
						}else if(args[0].equalsIgnoreCase("team")){
							if(p.hasPermission("FreezeIT.admin")){
								if(args[1].equalsIgnoreCase("spawn")){
									//l = p.getLocation();
									p.sendMessage(s + "Spawn wurde gesetzt");
								}	
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
						}else if(args[0].equalsIgnoreCase("save")){
							if(p.hasPermission("FreezeIT.admin")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									int i = ArenaManager.getManager().getIDBackfromName(args[1]);
									saveMap.saveMap2(i);
									p.sendMessage(s + "Arena Wurde gespeichert");
								}else{
									p.sendMessage(s + "Diese Map Existiert nicht");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
						}else{
							p.sendMessage("§8======-" + s + "§8-======");
							p.sendMessage("§7/freeze leave");
							p.sendMessage("§7/freeze team spawn");
							p.sendMessage("§7/freeze team setsize <arena> <int>");
							p.sendMessage("§7/freeze team setname <name>");
							p.sendMessage("§7/freeze team setcolor <colorecode>");
							p.sendMessage("§7/freeze team create <arena> <int>");
							p.sendMessage("§8============================");
						}
						
						return true;
					}else if(args.length == 3){
						if(args[0].equalsIgnoreCase("join")){
							if(p.hasPermission("FreezeIT.player")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[1]));
									if(a.returnTeamFromColor(args[2]) != 0){
										ArenaManager.getManager().addPlayer(p, ArenaManager.getManager().getIDBackfromName(args[1]), args[2]);
									}
								}else{
									p.sendMessage(s +Message.message.get(16));
								}	
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
						}else if(args[0].equalsIgnoreCase("team")){
							if(p.hasPermission("FreezeIT.admin")){
								if(args[1].equalsIgnoreCase("setname")){
									//name = args[2];
									p.sendMessage(s + "Team name wurde gesetzt");
								}else if(args[1].equalsIgnoreCase("setcolor")){
									//color = args[2];
									p.sendMessage(s + "Team farbe wurde gesetzt");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
							
						}else if(args[0].equalsIgnoreCase("setmoney")){
							if(p.hasPermission("FreezeIT.admin")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									if(isInt(args[2]) || isDouble(args[2])){
										Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[1]));
										p.sendMessage(s + Message.replace("Gewinn wurde auf:§c %MONEY% §7gesetzt", "%MONEY%", args[2]));
										a.setMoney(Double.parseDouble(args[2]));
										
									}else{
										p.sendMessage(s + "Falsche argumente");
									}
								}else{
									p.sendMessage(s + "Arena not exist");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
							
						}else if(args[0].equalsIgnoreCase("setmin")){
							if(p.hasPermission("FreezeIT.admin")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									if(isInt(args[2])){
										Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[1]));
										p.sendMessage(s + Message.replace("Minimale Spieler zahl gesetzt: %SPIELER%", "%SPIELER%", args[2]));
										a.setMin(Integer.parseInt(args[2]));
										saveMap.saveMap2(a.id);
									}else{
										p.sendMessage(s + "Falsche argumente");
									}
								}else{
									p.sendMessage(s + "Arena not exist");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
							
						}else if(args[0].equalsIgnoreCase("setmax")){
							if(p.hasPermission("FreezeIT.admin")){
								if(ArenaManager.getManager().ArenaExist(args[1])){
									if(isInt(args[2])){
										Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[1]));
										p.sendMessage(s + Message.replace("Maximale Spieler zahl gesetzt: %SPIELER%", "%SPIELER%", args[2]));
										a.setMaxPlayers(Integer.parseInt(args[2]));
										saveMap.saveMap2(a.id);
									}else{
										p.sendMessage(s + "Falsche argumente");
									}
								}else{
									p.sendMessage(s + "Arena not exist");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
							
						}else{
							p.sendMessage("§8======-" + s + "§8-======");
							p.sendMessage("§7/freeze leave");
							p.sendMessage("§7/freeze team spawn");
							p.sendMessage("§7/freeze team setsize <arena> <int>");
							p.sendMessage("§7/freeze team setname <name>");
							p.sendMessage("§7/freeze team setcolor <colorecode>");
							p.sendMessage("§7/freeze team create <arena> <int>");
							p.sendMessage("§8============================");
						}
						return true;
					}else if(args.length == 4){
						if(args[0].equalsIgnoreCase("team")){
							if(p.hasPermission("FreezeIT.admin")){
								if(args[1].equalsIgnoreCase("setsize")){
									Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[2]));
									a.teamlength(Integer.parseInt(args[3]));
									p.sendMessage(s + "Team Größe wurde gesetzt");
								}else if(args[1].equalsIgnoreCase("create")){
									//Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(args[2]));
									//a.createTeam(Integer.parseInt(args[3]), l, name, color);
									p.sendMessage(s + "Team wurde ersetellt");
								}
							}else{
								p.sendMessage(s + Message.message.get(0));
							}
							
						}else{
							p.sendMessage("§8======-" + s + "§8-======");
							p.sendMessage("§7/freeze leave");
							p.sendMessage("§7/freeze pos1");
							p.sendMessage("§7/freeze pos2");
							p.sendMessage("§7/freeze setarena");
							p.sendMessage("§7/freeze setexit");
							p.sendMessage("§7/freeze create");
							p.sendMessage("§7/freeze team spawn");
							p.sendMessage("§7/freeze team setsize <arena> <int>");
							p.sendMessage("§7/freeze team setname <name>");
							p.sendMessage("§7/freeze team setcolor <colorecode>");
							p.sendMessage("§7/freeze team create <arena> <int>");
							p.sendMessage("§8============================");
							
						}
						return true;
					}else{
						p.sendMessage("§8======-" + s + "§8-======");
						p.sendMessage("§7/freeze leave");
						p.sendMessage("§7/freeze pos1");
						p.sendMessage("§7/freeze pos2");
						p.sendMessage("§7/freeze setarena");
						p.sendMessage("§7/freeze setexit");
						p.sendMessage("§7/freeze create");
						p.sendMessage("§7/freeze team spawn");
						p.sendMessage("§7/freeze team setsize <arena> <int>");
						p.sendMessage("§7/freeze team setname <name>");
						p.sendMessage("§7/freeze team setcolor <colorecode>");
						p.sendMessage("§7/freeze team create <arena> <int>");
						p.sendMessage("§8============================");
						return true;
					}
				}
			}
			return false;
			
	  }
}
