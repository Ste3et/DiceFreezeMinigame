package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiTeam {
	
	public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();
	
	public static void openGui(Player p, Arena a, int i){
		Inventory in = Bukkit.createInventory(null, 18, "Team: " + i);
		if(a.teamS() == 2){
			int u = 15;
			for(int o = 0; o <= u; o++){
				ItemStack is = new ItemStack(Material.WOOL);
				ItemMeta im = is.getItemMeta();
				String cc = returnColor(o);
				im.setDisplayName("Team [" + i + "]: " + ChatColor.translateAlternateColorCodes('&', cc) + returnColorName(o) + " setzen");
				is.setItemMeta(im);
				is.setAmount(o + 1);
				is.setDurability(returnDurability(cc));
				in.setItem(o, is);
			}
		}
		inv.put(p, in);
		p.openInventory(in);
	}
	
	public static String returnColorName(int i){
		if(i == 0){
			return "Grün";
		}else if(i == 1){
			return "Blau";
		}else if(i == 2){
			return "Rot";
		}else if(i == 3){
			return "Rosa";
		}else if(i == 4){
			return "Gelb";
		}else if(i == 5){
			return "Weiß";
		}else if(i == 6){
			return "Schwarz";
		}else if(i == 7){
			return "Blau";
		}else if(i == 8){
			return "Grün";
		}else if(i == 9){
			return "Türkis";
		}else if(i == 10){
			return "Rot";
		}else if(i == 11){
			return "Lila";
		}else if(i == 12){
			return "Orange";
		}else if(i == 13){
			return "Grau";
		}else if(i == 14){
			return "Grau";
		}else if(i == 15){
			return "Türkis";
		}else{
			return "Weiß";
		}
	}
	
	public static String returnColor(int i){
		if(i == 0){
			return "&a";
		}else if(i == 1){
			return "&b";
		}else if(i == 2){
			return "&c";
		}else if(i == 3){
			return "&d";
		}else if(i == 4){
			return "&e";
		}else if(i == 5){
			return "&f";
		}else if(i == 6){
			return "&0";
		}else if(i == 7){
			return "&1";
		}else if(i == 8){
			return "&2";
		}else if(i == 9){
			return "&3";
		}else if(i == 10){
			return "&4";
		}else if(i == 11){
			return "&5";
		}else if(i == 12){
			return "&6";
		}else if(i == 13){
			return "&7";
		}else if(i == 14){
			return "&8";
		}else if(i == 15){
			return "&9";
		}else{
			return "&f";
		}
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
