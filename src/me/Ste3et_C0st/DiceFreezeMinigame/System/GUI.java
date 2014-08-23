package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {
	
	public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();
	
	public static void openGui(Player p, Arena a){
		Inventory in = Bukkit.createInventory(null, 9, "FreezeIT-§0" + a.getId() + " §r" + a.returnTeam(1).size() + " vs " +  a.returnTeam(2).size());
		if(a.teamS() == 2){
			ItemStack is = new ItemStack(Material.WOOL);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(1)) + "Team: " + a.returnString(1) + " "  + a.returnTeam(1).size() + "/" + a.getMaxPlayer() /2);
			List<String> team1s = new ArrayList<String>();
			if(a.returnTeam(1).size() > 0){

				for(Player pl : a.returnTeam(1)){
					team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# " + pl.getName());
				}
				
				int ol = a.getMaxPlayer() /2;
				ol = ol - a.returnTeam(2).size();
				
				for(int o = 0; o <= ol; o++ ){
					team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
				}
			}else{
				int ol = a.getMaxPlayer() /2;
				for(int o = 0; o <= ol; o++ ){
					team1s.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(1))  + "# FREI");
				}
			}
			im.setLore(team1s);
			is.setItemMeta(im);
			is.setDurability((short) returnDurability(a.returnColor(1)));
			
			ItemStack iS = new ItemStack(Material.WOOL);
			ItemMeta iM = iS.getItemMeta();
			iM.setDisplayName(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "Team: " + a.returnString(2)  + " "  + a.returnTeam(2).size() + "/" + a.getMaxPlayer() /2);
			List<String> team1S = new ArrayList<String>();
			if(a.returnTeam(2).size() > 0){

				for(Player pl : a.returnTeam(2)){
					team1S.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# " + pl.getName());
				}
				
				int ol = a.getMaxPlayer() /2;
				ol = ol - a.returnTeam(2).size();
				
				for(int o = 0; o <= ol; o++ ){
					team1S.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
				}
			}else{
				int ol = a.getMaxPlayer() /2;
				for(int o = 0; o <= ol; o++ ){
					team1S.add(ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + "# FREI");
				}
			}
			
			
			iM.setLore(team1S);
			iS.setItemMeta(iM);
			iS.setDurability((short) returnDurability(a.returnColor(2)));
			in.setItem(2, is);
			in.setItem(6, iS);
		}
		inv.put(p, in);
		p.openInventory(in);
	}
	
	public static short returnDurability(String s){
		if(s.equalsIgnoreCase("&a")){
			return 5;
		}else if(s.equalsIgnoreCase("&b")){
			return 3;
		}else if(s.equalsIgnoreCase("&c")){
			return 14;
		}else if(s.equalsIgnoreCase("&d")){
			return 6;
		}else if(s.equalsIgnoreCase("&e")){
			return 4;
		}else if(s.equalsIgnoreCase("&f")){
			return 0;
		}else if(s.equalsIgnoreCase("&0")){
			return 15;
		}else if(s.equalsIgnoreCase("&1")){
			return 11;
		}else if(s.equalsIgnoreCase("&2")){
			return 13;
		}else if(s.equalsIgnoreCase("&3")){
			return 9;
		}else if(s.equalsIgnoreCase("&4")){
			return 14;
		}else if(s.equalsIgnoreCase("&5")){
			return 10;
		}else if(s.equalsIgnoreCase("&6")){
			return 1;
		}else if(s.equalsIgnoreCase("&7")){
			return 8;
		}else if(s.equalsIgnoreCase("&8")){
			return 7;
		}else if(s.equalsIgnoreCase("&9")){
			return 9;
		}else{
			return 0;
		}
	}
}
