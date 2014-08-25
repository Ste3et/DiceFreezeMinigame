package me.Ste3et_C0st.DiceFreezeMinigame;

import java.util.HashMap;

import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Team {
	
	static HashMap<String, String> name = new HashMap<String, String>();
	static HashMap<String, String> color = new HashMap<String, String>();
	public static HashMap<Player, String> player = new HashMap<Player, String>();
	
	public static String getname(String s){
		if(name.get(s) != null){
			return name.get(s);
		}else{
			return null;
		}
	}
	
	public static String getcolor(String s){
		if(color.get(s) != null){
			return color.get(s);
		}else{
			return null;
		}
	}
	
	public static void setname(String a, String s){
		name.put(s, a);
	}
	
	public static void setcolor(String a, String s){
		color.put(s, a);
	}
	
	@SuppressWarnings("deprecation")
	public static void check(String a, Player p){
		if(!ArenaManager.getManager().ArenaExist(a)){
			p.sendMessage(main.s + "Die Arena Existiert nicht");
			return;
		}
		
		Arena arena = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(a));
		
		if(arena.returnString(1) == ""){
			return;
		}
		
		if(arena.returnString(2) == ""){
			return;
		}
		
		p.getInventory().setItem(8, Editor.is(Material.INK_SACK, main.s + "Team Erstellen beenden", null, 0, 1));
		p.updateInventory();
	}
	
	@SuppressWarnings("deprecation")
	public static void enterTeam(Player p, String a){
		if(ArenaManager.getManager().ArenaExist(a)){
			if(player.get(p) == null){
				p.getInventory().clear();
				p.updateInventory();
				player.put(p, a);
				p.getInventory().setItem(0, Editor.is(Material.WOOL,main.s + "Team 1", null, 0, 1));
				p.getInventory().setItem(2, Editor.is(Material.WOOL,main.s + "Team 2", null, 0, 1));
				p.getInventory().setItem(8, Editor.is(Material.INK_SACK, main.s + "§c§m" + "Team Erstelen beenden", null, 0, 1));
				p.sendMessage(main.s + "Team Modus betreten");
				p.updateInventory();
				check(a, p);
			}
		}
	}
	
	
}
