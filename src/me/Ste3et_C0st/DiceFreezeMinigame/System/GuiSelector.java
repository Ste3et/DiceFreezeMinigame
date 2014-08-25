package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiSelector {
	public static HashMap<Player, Inventory> inv = new HashMap<Player, Inventory>();
	
	@SuppressWarnings("deprecation")
	public static void openGui(Player p, Arena a){
		Inventory inV = Bukkit.createInventory(null, 18, "§8Ingame Selector");
		ItemStack is = new ItemStack(Material.getMaterial(160));
		ItemMeta im;
		List<String> lore = new ArrayList<String>();
		if(!p.hasPermission("Freeze.Snow.II")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Schneball Stärke II");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(2);
			inV.setItem(0, is);
			lore.clear();
		}else{
			im = is.getItemMeta();
			String s;
			if(a.getSnowball(p) == 2){
				s = "§a";
			}else{
				s = "§c";
			}
			is.setType(Material.SNOW_BALL);
			im.setDisplayName(s + "Schneball Stärke II");
			is.setItemMeta(im);
			is.setAmount(2);
			inV.setItem(0, is);
		}
		
		if(!p.hasPermission("Freeze.Snow.III")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Schneball Stärke III");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(3);
			inV.setItem(1, is);
			lore.clear();
		}else{
			is.setType(Material.SNOW_BALL);
			im = is.getItemMeta();
			String s;
			if(a.getSnowball(p) == 3){
				s = "§a";
			}else{
				s = "§c";
			}
			is.setType(Material.SNOW_BALL);
			im.setDisplayName(s + "Schneball Stärke III");
			is.setItemMeta(im);
			is.setAmount(3);
			inV.setItem(1, is);
		}
		
		if(!p.hasPermission("Freeze.Snow.IV")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Schneball Stärke IV");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(4);
			inV.setItem(2, is);
			lore.clear();
		}else{
			im = is.getItemMeta();
			String s;
			if(a.getSnowball(p) == 4){
				s = "§a";
			}else{
				s = "§c";
			}
			is.setType(Material.SNOW_BALL);
			im.setDisplayName(s + "Schneball Stärke IV");
			is.setItemMeta(im);
			is.setAmount(4);
			inV.setItem(2, is);
		}
		
		if(!p.hasPermission("Freeze.Save.II")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Das Schutzshild Stärke II");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(2);
			inV.setItem(9, is);
			lore.clear();
		}else{
			is.setType(Material.APPLE);
			im = is.getItemMeta();
			String s;
			if(a.getTimer(p) == 2){
				s = "§a";
			}else{
				s = "§c";
			}
			im.setDisplayName(s + "Schutzshild Stärke II");
			is.setItemMeta(im);
			is.setAmount(2);
			inV.setItem(9, is);
		}
		
		if(!p.hasPermission("Freeze.Save.III")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Schutzshild Stärke III");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(3);
			inV.setItem(10, is);
			lore.clear();
		}else{
			is.setType(Material.APPLE);
			im = is.getItemMeta();
			String s;
			if(a.getTimer(p) == 3){
				s = "§a";
			}else{
				s = "§c";
			}
			im.setDisplayName(s + "Schutzshild Stärke III");
			is.setItemMeta(im);
			is.setAmount(3);
			inV.setItem(10, is);
		}
		
		if(!p.hasPermission("Freeze.Save.IV")){
			is.setType(Material.getMaterial(160));
			im = is.getItemMeta();
			im.setDisplayName("§6Schutzshild Stärke IV");
			lore.add("§7");
			lore.add("§7NOCH NICHT GEKAUFT");
			im.setLore(lore);
			is.setItemMeta(im);
			is.setDurability((short) 14);
			is.setAmount(4);
			inV.setItem(11, is);
			lore.clear();
		}else{
			String s;
			if(a.getTimer(p) == 4){
				s = "§a";
			}else{
				s = "§c";
			}
			is.setType(Material.APPLE);
			im = is.getItemMeta();
			im.setDisplayName(s + "Schutzshild Stärke IV");
			is.setItemMeta(im);
			is.setAmount(4);
			inV.setItem(11, is);
		}
		
		inv.put(p, inV);
		p.openInventory(inv.get(p));
	}
}
