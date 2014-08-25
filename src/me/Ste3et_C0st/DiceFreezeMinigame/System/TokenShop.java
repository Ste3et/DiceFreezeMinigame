package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.Ste3et_C0st.DiceFreezeMinigame.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenShop {
	
	public static HashMap<Player, Inventory> tokeninv = new HashMap<Player, Inventory>();
	
	public static void createShop(Player p) throws SQLException{
		Inventory inv = Bukkit.createInventory(null, 18, main.s + "Token Shop");
		
		List<String> lore1 = new ArrayList<String>();
		
		String s = "§c";
		if(p.hasPermission("Freeze.shotgun")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§2--§6§lCOOMING SOON§2--");
			lore1.add("§7");
			lore1.add("§7Verschießt 5 Schneebälle Gleichzeitig");
			lore1.add("§7Die den Gegner enorm Schaden");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§2--§6§lCOOMING SOON§2--");
			lore1.add("§7");
			lore1.add("§7Verschießt 5 Schneebälle Gleichzeitig");
			lore1.add("§7Die den Gegner enorm Schaden");
			lore1.add("§7");
			lore1.add("§7Kostet: §6200 Tokens");
		}
		ItemStack is = new ItemStack(Material.SNOW_BALL);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(s +"Schrottflinte");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(8, is);
		lore1.clear();
		
		
		if(!p.hasPermission("Freeze.Snow.II")){
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke II");
			lore1.add("§7Läst den Spieler 15 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§7Kostet: §6200 Tokens");
		}else{
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke II");
			lore1.add("§7Läst den Spieler 15 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}
		is = new ItemStack(Material.SNOW_BALL);
		im = is.getItemMeta();
		im.setDisplayName(s + "Schneball Stärke II");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(0, is);
		lore1.clear();
		
		if(p.hasPermission("Freeze.Snow.III")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke III");
			lore1.add("§7Läst den Spieler 20 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke III");
			lore1.add("§7Läst den Spieler 20 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§7Kostet: §6500 Tokens");
		}
		is = new ItemStack(Material.SNOW_BALL);
		im = is.getItemMeta();
		im.setDisplayName(s + "Schneball Stärke III");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(1, is);
		lore1.clear();
		
		if(p.hasPermission("Freeze.Snow.IV")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke IV");
			lore1.add("§7Läst den Spieler 30 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Der Schneeball der Stärke IV");
			lore1.add("§7Läst den Spieler 30 Sekunden Einfrieren");
			lore1.add("§7");
			lore1.add("§7Kostet: §61000 Tokens");
		}
		is = new ItemStack(Material.SNOW_BALL);
		im = is.getItemMeta();
		im.setDisplayName(s +"Schneball Stärke IV");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(2, is);
		lore1.clear();
		
		if(p.hasPermission("Freeze.Save.II")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke II");
			lore1.add("§7schützt dich 5 Sekunden");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke II");
			lore1.add("§7schützt dich 5 Sekunden");
			lore1.add("§7");
			lore1.add("§7Kostet: §6100 Tokens");
		}
		is = new ItemStack(Material.APPLE);
		im = is.getItemMeta();
		im.setDisplayName(s + "Schutzshild Stärke II");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(9, is);
		lore1.clear();
		
		if(p.hasPermission("Freeze.Save.II")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke III");
			lore1.add("§7schützt dich 7 Sekunden");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke III");
			lore1.add("§7schützt dich 7 Sekunden");
			lore1.add("§7");
			lore1.add("§7Kostet: §6150 Tokens");
		}
		is = new ItemStack(Material.APPLE);
		im = is.getItemMeta();
		im.setDisplayName(s + "Schutzshild Stärke III");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(10, is);
		lore1.clear();
		
		if(p.hasPermission("Freeze.Save.II")){
			s = "§a";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke IV");
			lore1.add("§7schützt dich 10 Sekunden");
			lore1.add("§7");
			lore1.add("§2§lGEKAUFT");
		}else{
			s = "§c";
			lore1.add("§7");
			lore1.add("§7Das Schutzshild Stärke IV");
			lore1.add("§7schützt dich 10 Sekunden");
			lore1.add("§7");
			lore1.add("§7Kostet: §6200 Tokens");
		}
		is = new ItemStack(Material.APPLE);
		im = is.getItemMeta();
		im.setDisplayName(s + "Schutzshild Stärke IV");
		im.setLore(lore1);
		is.setItemMeta(im);
		inv.setItem(11, is);
		lore1.clear();
		
		is = new ItemStack(Material.EMERALD);
		im = is.getItemMeta();
		
		im.setDisplayName("§cTokens: §6" + main.token.getTokens(p) + "");
		
		is.setItemMeta(im);
		inv.setItem(17, is);
		
		tokeninv.put(p, inv);
		p.openInventory(tokeninv.get(p));
	}
}
