package me.Ste3et_C0st.DiceFreezeMinigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Editor {
	static HashMap<String, Location> arena = new HashMap<String, Location>();
	static HashMap<String, Location> exit = new HashMap<String, Location>();
	static HashMap<String, Location> c1 = new HashMap<String, Location>();
	static HashMap<String, Location> c2 = new HashMap<String, Location>();
	static HashMap<String, Location> lobby = new HashMap<String, Location>();
	static HashMap<String, Location> l = new HashMap<String, Location>();
	public static HashMap<Player, String> player = new HashMap<Player, String>();
	
	public static void enter(String string, Player p) {
		if(ArenaManager.getManager().ArenaExist(string)){
			p.sendMessage(main.s + "Diese Arena Existiert bereits");
			return;
		}else{
			if(!isInEditor(p)){
				enterEdit(p, string);
				p.sendMessage(main.s + "Arena Editor Modus §bbetreten");
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void enterEdit(Player p, String s) {
		if(!isInEditor(p)){
			p.getInventory().clear();
			p.updateInventory();
			List<String> select = new ArrayList<String>();
			select.add("§cCorner (1):");
			select.add(" §cWorld: N/A");
			select.add(" §cX: N/A");
			select.add(" §cY: N/A");
			select.add(" §cZ: N/A");
			select.add("§bCorner (2):");
			select.add(" §bWorld: N/A");
			select.add(" §bX: N/A");
			select.add(" §bY: N/A");
			select.add(" §bZ: N/A");
			p.getInventory().setItem(0, is(Material.BONE, main.s + "Slector", select, 0, 1));
			p.getInventory().setItem(4, is(Material.DIAMOND_SWORD, main.s + "Set Arenaspawn", null, 0, 1));
			p.getInventory().setItem(8, is(Material.BOWL, main.s + "Exit Select Mode", null, 0, 1));
			p.getInventory().setItem(6, is(Material.DIAMOND, main.s + "Finish Map", null, 0, 1));
			p.updateInventory();
			player.put(p, s);
		}else{
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void exit(Player p) {
		p.getInventory().clear();
		p.updateInventory();
		String a = getEditorName(p);
		arena.remove(a);
		c1.remove(a);
		c2.remove(a);
		exit.remove(a);
		l.remove(a);
		lobby.remove(a);
		player.remove(p);
		p.sendMessage(main.s + "Arena Modus wurde verlassen");
	}
	
	public static String getEditorName(Player p) {
		if(isInEditor(p)){
			return player.get(p);
		}
		return null;
	}

	public static boolean debug(Player p){
		if(isInEditor(p)){
			String a = getEditorName(p);
			if(getArena(a) == null || getc1(a) == null || getc2(a) == null ||
			   getExit(a) == null || getlobby(a) == null){
				p.sendMessage("§8=====================");
				p.sendMessage("§7Arena Debug");
				p.sendMessage("§8=====================");
				if(arena == null){
					p.sendMessage(" §7Arena Name: §cUNKNOW");
				}else{
					p.sendMessage(" §7Arena Name: §2" + a);
				}
				
				if(getArena(a) == null){
					p.sendMessage(" §7Arena Spawn: §cMissing");
				}else{
					p.sendMessage(" §7Arena Spawn: §2Exist");
				}
				
				if(getExit(a) == null){
					p.sendMessage(" §7Arena Exit: §cMissing");
				}else{
					p.sendMessage(" §7Arena Exit: §2Exist");
				}
				
				if(getc1(a) == null){
					p.sendMessage(" §7Arena Corner 1: §cMissing");
				}else{
					p.sendMessage(" §7Arena Corner 1: §2Exist");
				}
				
				if(getc2(a) == null){
					p.sendMessage(" §7Arena Corner 2: §cMissing");
				}else{
					p.sendMessage(" §7Arena Corner 2: §2Exist");
				}
				
				if(getlobby(a) == null){
					p.sendMessage(" §7Arena Lobby: §cMissing");
				}else{
					p.sendMessage(" §7Arena Lobby: §2Exist");
				}
				
				p.sendMessage("§8=====================");
				return false;
			}else{
				p.sendMessage("§8=====================");
				p.sendMessage("§7Arena Debug");
				p.sendMessage("§8---------------------");
				p.sendMessage("§2- ALLES OK !");
				p.sendMessage("§8=====================");
				return true;
			}
		}else{
			p.sendMessage(main.s + "Du befindest dich nicht im Editor Modus");
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void create(Player p){
		if(!isInEditor(p)){
			p.sendMessage(main.s + "Du bist nicht im Editor Modus");
		}
		
		if(debug(p) == false){
			return;
		}
		
		String a = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(a)){
			p.sendMessage(main.s + "Arena Existiert bereits");
			return;
		}
		
		ArenaManager.getManager().createArena(getc1(a), getc2(a), getArena(a), getExit(a), null, 4, a, getlobby(a));
		p.sendMessage(main.s + "§2Arena wurde erstellt");
		
		p.getInventory().clear();
		p.updateInventory();
		p.getInventory().setItem(2, is(Material.WOOL, main.s + "Team Modus Betreten", null, 13, 1));
		p.getInventory().setItem(6, is(Material.WOOL, main.s + "Fertig Stellen", null, 14, 1));
		p.updateInventory();
	}
	
	public static boolean isInEditor(Player p) {
		if(player.get(p) != null){
			return true;
		}else{
			return false;
		}
		
	}

	public static Location getArena(String s){
		if(arena.get(s) != null){
			return arena.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getExit(String s){
		if(exit.get(s) != null){
			return exit.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getc1(String s){
		if(c1.get(s) != null){
			return c1.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getc2(String s){
		if(c2.get(s) != null){
			return c2.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getlobby(String s){
		if(lobby.get(s) != null){
			return lobby.get(s);
		}else{
			return null;
		}
	}
	
	public static Location getl(String s){
		if(l.get(s) != null){
			return l.get(s);
		}else{
			return null;
		}
	}
	
	
	public static void setArena(Location l, String s){
		arena.put(s, l);
	}
	
	public static void setExit(Location l, String s){
		exit.put(s, l);
	}
	
	public static void setc1(Location l, String s){
		c1.put(s, l);
	}
	
	public static void setc2(Location l, String s){
		c2.put(s, l);
	}
	
	public static void setlobby(Location l, String s){
		lobby.put(s, l);
	}
	
	public static void setl(Location a, String s){
		l.put(s, a);
	}
	
	public static ItemStack is(Material m, String s, List<String> lore, int dur, int amount){
		ItemStack i = new ItemStack(m);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(s);
		if(lore != null){
			meta.setLore(lore);
		}
		
		i.setItemMeta(meta);
		i.setDurability((short) dur);
		i.setAmount(amount);
		return i;
	}
}
