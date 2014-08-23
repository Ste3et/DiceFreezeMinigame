package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceFreezeMinigame.Editor;
import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnPlayerInterarcEvent implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void event(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			Arena a = ArenaManager.getManager().returnArena(p);
			if(!a.isFroozen(p)){
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
					if(e.getItem() != null){
						if(e.getItem().hasItemMeta()){
							if(e.getItem().getItemMeta().hasDisplayName()){
								if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(main.s + "Snow ball")){
							        if(e.getClickedBlock() != null){
							        	if(e.getClickedBlock().getType() == Material.FURNACE || e.getClickedBlock().getType() == Material.CHEST ||
							        	   e.getClickedBlock().getType() == Material.WORKBENCH || e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE ||
							        	   e.getClickedBlock().getType() == Material.BEACON || e.getClickedBlock().getType() == Material.BREWING_STAND || e.getClickedBlock().getType() == Material.DIODE ||
							        	   e.getClickedBlock().getType() == Material.TRAPPED_CHEST || e.getClickedBlock().getType() == Material.TRAP_DOOR || 
							        	   e.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR){
							        		e.setCancelled(true);
							        		return;
							        	}
							        }
									ItemStack is = new ItemStack(Material.SNOW_BALL);
							        ItemMeta im = is.getItemMeta();
							        im.setDisplayName(main.s + "Snow ball");
							        is.setItemMeta(im);
							        p.getInventory().addItem(is);
								}else if(e.getItem().getItemMeta().getDisplayName().endsWith("Back To the lobby")){
									ArenaManager.getManager().removePlayer(p);
								}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lWähle ein Team !") || e.getItem().getItemMeta().getDisplayName().startsWith("Team")){
									GUI.openGui(p, a);
								}
							}
						}
					}else{
						e.setCancelled(true);
					}
				}
			}
		}else{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getItem() != null){
					if(e.getItem().getItemMeta().hasDisplayName()){
						if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(main.s + "Slector")){
							if(e.getClickedBlock() != null){
								e.setCancelled(true);
								if(e.getItem().getItemMeta().hasLore()){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									List<String> lore = im.getLore();
									Location l = e.getClickedBlock().getLocation();
									lore.set(6, " §bWorld:" + l.getWorld().getName());
									lore.set(7, " §bX: " + l.getBlockX());
									lore.set(8, " §bY: " + l.getBlockY());
									lore.set(9, " §bZ: " + l.getBlockZ());
									im.setLore(lore);
									is.setItemMeta(im);
									p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
									p.updateInventory();
									p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
									p.updateInventory();
									String s = null;
									if(Editor.getEditorName(p) != null){
										s = Editor.getEditorName(p);
									}else{
										p.sendMessage(main.s + "Du befindest dich nicht im Editor modus");
										return;
									}
									Editor edit = new Editor(s, p);
									edit.setc2(l, s);
									p.sendMessage(main.s + "Arena Corner 2 Wurde gesetzt");
								}
							}
						}else if(e.getItem().getItemMeta().getDisplayName().startsWith(main.s + "Set")){
							String s = e.getItem().getItemMeta().getDisplayName();
							s = s.replace(main.s + "Set ", "");
							s = ChatColor.stripColor(s);
							if(s.equalsIgnoreCase("Arenaspawn")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Exit");
								}else{
									im.setDisplayName(main.s + "Set Lobby");
								}
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}else if(s.equalsIgnoreCase("Lobby")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Arenaspawn");
								}else{
									im.setDisplayName(main.s + "Set Exit");
								}
								
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}else if(s.equalsIgnoreCase("Exit")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Lobby");
								}else{
									im.setDisplayName(main.s + "Set Arenaspawn");
								}
								
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}
						}
					}
				}
			}else if(e.getAction() == Action.LEFT_CLICK_BLOCK){
				if(e.getItem() != null){
					if(e.getItem().getItemMeta().hasDisplayName()){
						if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(main.s + "Slector")){
							if(e.getClickedBlock() != null){
								e.setCancelled(true);
								if(e.getItem().getItemMeta().hasLore()){
									ItemStack is = e.getItem();
									ItemMeta im = is.getItemMeta();
									List<String> lore = im.getLore();
									Location l = e.getClickedBlock().getLocation();
									lore.set(1, " §cWorld:" + l.getWorld().getName());
									lore.set(2, " §cX: " + l.getBlockX());
									lore.set(3, " §cY: " + l.getBlockY());
									lore.set(4, " §cZ: " + l.getBlockZ());
									im.setLore(lore);
									is.setItemMeta(im);
									p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
									p.updateInventory();
									p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
									p.updateInventory();
									String s = null;
									if(Editor.getEditorName(p) != null){
										s = Editor.getEditorName(p);
									}else{
										p.sendMessage(main.s + "Du befindest dich nicht im Editor modus");
										return;
									}
									Editor edit = new Editor(s, p);
									edit.setc1(l, s);
									p.sendMessage(main.s + "Arena Corner 1 Wurde gesetzt");
								}
							}
						}else if(e.getItem().getItemMeta().getDisplayName().startsWith(main.s + "Set")){
							if(e.getItem().getItemMeta().getDisplayName().endsWith("Arenaspawn")){
								String s = null;
								if(Editor.getEditorName(p) != null){
									s = Editor.getEditorName(p);
								}else{
									p.sendMessage(main.s + "Du befindest dich nicht im Editor modus");
									return;
								}
								Editor edit = new Editor(s, p);
								Location l = e.getClickedBlock().getLocation();
								l.setY(l.getY() + 1);
								edit.setArena(l,s);
								p.sendMessage(main.s + "Arena Spawn gesetzt");
							}
							
							if(e.getItem().getItemMeta().getDisplayName().endsWith("Lobby")){
								String s = null;
								if(Editor.getEditorName(p) != null){
									s = Editor.getEditorName(p);
								}else{
									p.sendMessage(main.s + "Du befindest dich nicht im Editor modus");
									return;
								}
								Editor edit = new Editor(s, p);
								Location l = e.getClickedBlock().getLocation();
								l.setY(l.getY() + 1);
								edit.setlobby(l, s);
								p.sendMessage(main.s + "Arena Lobby gesetzt");
							}
							
							if(e.getItem().getItemMeta().getDisplayName().endsWith("Exit")){
								String s = null;
								if(Editor.getEditorName(p) != null){
									s = Editor.getEditorName(p);
								}else{
									p.sendMessage(main.s + "Du befindest dich nicht im Editor modus");
									return;
								}
								Editor edit = new Editor(s, p);
								Location l = e.getClickedBlock().getLocation();
								l.setY(l.getY() + 1);
								edit.setExit(l,s);
								p.sendMessage(main.s + "Arena Exit gesetzt");
							}
						}
					}
				}
			}else if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR){
				if(e.getItem() != null){
					if(e.getItem().getItemMeta().hasDisplayName()){
						if(e.getItem().getItemMeta().getDisplayName().startsWith(main.s + "Set")){
							String s = e.getItem().getItemMeta().getDisplayName();
							s = s.replace(main.s + "Set ", "");
							s = ChatColor.stripColor(s);
							if(s.equalsIgnoreCase("Arenaspawn")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Exit");
								}else{
									im.setDisplayName(main.s + "Set Lobby");
								}
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}else if(s.equalsIgnoreCase("Lobby")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Arenaspawn");
								}else{
									im.setDisplayName(main.s + "Set Exit");
								}
								
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}else if(s.equalsIgnoreCase("Exit")){
								ItemStack is = e.getItem();
								ItemMeta im = is.getItemMeta();
								if(p.isSneaking()){
									im.setDisplayName(main.s + "Set Lobby");
								}else{
									im.setDisplayName(main.s + "Set Arenaspawn");
								}
								
								is.setItemMeta(im);
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
								p.updateInventory();
								p.getInventory().setItem(p.getInventory().getHeldItemSlot(), is);
								p.updateInventory();
							}
						}else if(e.getItem().getItemMeta().getDisplayName().startsWith(main.s + "Exit Select Mode")){
							if(Editor.getEditorName(p) == null){
								p.sendMessage(main.s + "Du befindest dich nicht im Editor Modus");
							}else{
								String s = Editor.getEditorName(p);
								Editor edit = new Editor(s, p);
								edit.exit(p);
							}
						}
					}
				}
			}
		}
						
					
	}
}
