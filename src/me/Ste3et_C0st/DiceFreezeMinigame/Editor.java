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
	HashMap<String, Location> arena = new HashMap<String, Location>();
	HashMap<String, Location> exit = new HashMap<String, Location>();
	HashMap<String, Location> c1 = new HashMap<String, Location>();
	HashMap<String, Location> c2 = new HashMap<String, Location>();
	HashMap<String, Location> lobby = new HashMap<String, Location>();
	HashMap<String, Location> l = new HashMap<String, Location>();
	HashMap<String, String> name = new HashMap<String, String>();
	HashMap<String, String> color = new HashMap<String, String>();
	static HashMap<Player, String> player = new HashMap<Player, String>();
	
	public Editor(String string, Player p) {
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
	public void enterEdit(Player p, String s) {
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
			p.getInventory().setItem(0, is(Material.COCOA, main.s + "Slector", select, 0, 1));
			p.getInventory().setItem(4, is(Material.DIAMOND_SWORD, main.s + "Set Arenaspawn", null, 0, 1));
			p.getInventory().setItem(8, is(Material.BOWL, main.s + "Exit Select Mode", null, 0, 1));
			p.updateInventory();
			player.put(p, s);
		}else{
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void exit(Player p) {
		p.getInventory().clear();
		p.updateInventory();
		String arena = getEditorName(p);
		this.arena.remove(arena);
		this.c1.remove(arena);
		this.c2.remove(arena);
		this.color.remove(arena);
		this.exit.remove(arena);
		this.l.remove(arena);
		this.lobby.remove(arena);
		this.name.remove(arena);
		player.remove(p);
		p.sendMessage(main.s + "Arena Modus wurde verlassen");
	}
	
	public static String getEditorName(Player p) {
		if(isInEditor(p)){
			return player.get(p);
		}
		return null;
	}

	public boolean debug(Player p){
		if(isInEditor(p)){
			String arena = getEditorName(p);
			if(this.arena.get(arena) == null || this.c1.get(arena) == null || this.c2.get(arena) == null ||
			   this.exit.get(arena) == null || this.lobby.get(arena) == null || this.name.get(arena) == null){
				p.sendMessage("§8=====================");
				p.sendMessage("§7Arena Debug");
				p.sendMessage("§8=====================");
				if(this.name.get(arena) == null){
					p.sendMessage(" §7Arena Name: §cUNKNOW");
				}else{
					p.sendMessage(" §7Arena Name: §2" + this.name.get(arena));
				}
				
				if(this.arena.get(arena) == null){
					p.sendMessage(" §7Arena Spawn: §cMissing");
				}else{
					p.sendMessage(" §7Arena Spawn: §2Exist");
				}
				
				if(this.exit.get(arena) == null){
					p.sendMessage(" §7Arena Exit: §cMissing");
				}else{
					p.sendMessage(" §7Arena Exit: §2Exist");
				}
				
				if(this.c1.get(arena) == null){
					p.sendMessage(" §7Arena Corner 1: §cMissing");
				}else{
					p.sendMessage(" §7Arena Corner 1: §2Exist");
				}
				
				if(this.c2.get(arena) == null){
					p.sendMessage(" §7Arena Corner 2: §cMissing");
				}else{
					p.sendMessage(" §7Arena Corner 2: §2Exist");
				}
				
				if(this.lobby.get(arena) == null){
					p.sendMessage(" §7Arena Lobby: §cMissing");
				}else{
					p.sendMessage(" §7Arena Lobby: §2Exist");
				}
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
	
	public void create(Player p){
		if(!isInEditor(p)){
			p.sendMessage(main.s + "Du bist nicht im Editor Modus");
		}
		
		if(debug(p) == false){
			return;
		}
		
		String arena = player.get(p);
		
		if(ArenaManager.getManager().ArenaExist(arena)){
			p.sendMessage(main.s + "Arena Existiert bereits");
			return;
		}
		
		ArenaManager.getManager().createArena(getc1(arena), getc2(arena), getArena(arena), getExit(arena), null, 4, arena, getlobby(arena));
		p.sendMessage(main.s + "§2Arena wurde erstellt");
		exit(p);
	}
	
	private static boolean isInEditor(Player p) {
		if(player.get(p) != null){
			return true;
		}else{
			return false;
		}
		
	}

	public Location getArena(String s){
		if(this.arena.get(s) != null){
			return this.arena.get(s);
		}else{
			return null;
		}
	}
	
	public Location getExit(String s){
		if(this.exit.get(s) != null){
			return this.exit.get(s);
		}else{
			return null;
		}
	}
	
	public Location getc1(String s){
		if(this.c1.get(s) != null){
			return this.c1.get(s);
		}else{
			return null;
		}
	}
	
	public Location getc2(String s){
		if(this.c2.get(s) != null){
			return this.c2.get(s);
		}else{
			return null;
		}
	}
	
	public Location getlobby(String s){
		if(this.lobby.get(s) != null){
			return this.lobby.get(s);
		}else{
			return null;
		}
	}
	
	public Location getl(String s){
		if(this.l.get(s) != null){
			return this.l.get(s);
		}else{
			return null;
		}
	}
	
	public String getname(String s){
		if(this.name.get(s) != null){
			return this.name.get(s);
		}else{
			return null;
		}
	}
	
	public String getcolor(String s){
		if(this.color.get(s) != null){
			return this.color.get(s);
		}else{
			return null;
		}
	}
	
	
	public void setArena(Location l, String s){
		this.arena.put(s, l);
	}
	
	public void setExit(Location l, String s){
		this.exit.put(s, l);
	}
	
	public void setc1(Location l, String s){
		this.c1.put(s, l);
	}
	
	public void setc2(Location l, String s){
		this.c2.put(s, l);
	}
	
	public void setlobby(Location l, String s){
		this.lobby.put(s, l);
	}
	
	public void setl(Location l, String s){
		this.l.put(s, l);
	}
	
	public void setname(String a, String s){
		this.name.put(s, a);
	}
	
	public void setcolor(String a, String s){
		this.color.put(s, a);
	}
	
	public ItemStack is(Material m, String s, List<String> lore, int dur, int amount){
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
