package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;
import org.kitteh.tag.TagAPI;

public class OnInventoryOpenEvent implements Listener {
	
	
	@EventHandler
	public void onNameTag(AsyncPlayerReceiveNameTagEvent e) {
	Player p = e.getNamedPlayer();
	if(ArenaManager.getManager().isInGame(p)){
		Arena a = ArenaManager.getManager().returnArena(p);
		if(a.getTeam(p) != 999){
			int ID = a.getTeam(p);
			String s = a.returnColor(ID);
			e.setTag(ChatColor.translateAlternateColorCodes('&', s) + p.getName());
		}
	}else{
		e.setTag(p.getName());
	}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryClickEvent e)
    {
			Player p = (Player) e.getWhoClicked();
			if(ArenaManager.getManager().isInGame(p)){
				e.setCancelled(true);
				Arena a = ArenaManager.getManager().returnArena(p);
				if(e.getCurrentItem() != null && !a.isStartet() && GUI.inv.get(p) != null && e.getInventory().equals(GUI.inv.get(p))){
					ItemStack is = e.getCurrentItem();
					if(is.getType() != null && is.getType().equals(Material.WOOL)){
						if(is.hasItemMeta()){
							if(is.getItemMeta().hasDisplayName()){
								String Color = is.getItemMeta().getDisplayName();
								Integer teamID = a.returnTeamFromColor(Color.split(" ")[1]);
								if(teamID != 0){
									Integer oldTeam = a.getTeam(p);
									if(a.getTeam(p) != 999){
										if(a.returnTeam(teamID).size() + 1 > a.returnTeam(oldTeam).size()){
											p.sendMessage(main.s + "Das von die gewählte team hat bereits genug Spieler");
											return;
										}
										
										if(oldTeam.equals(teamID)){
											e.setCancelled(true);
											p.closeInventory();
											p.sendMessage(main.s + "Du befindest dich bereits in diesem Team");
										}else{
											a.returnTeam(oldTeam).remove(p);
											a.addTeam(p, teamID);
											p.closeInventory();
											
											ItemStack iS = new ItemStack(Material.getMaterial(35));
											ItemMeta im = iS.getItemMeta();
											im.setDisplayName("Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID)) + a.returnString(teamID) + " | §rSpieler: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID)) + a.returnTeam(teamID).size() + "/" + a.getMaxPlayes() / 2);
											iS.setDurability(GUI.returnDurability(a.returnColor(teamID)));
											iS.setItemMeta(im);
											
											p.getInventory().setHelmet(null);
											p.getInventory().setItem(0, iS);
											p.updateInventory();
											
											GUI.openGui(p, a);
											a.checkTeamSize();
											p.sendMessage(main.s + "Du bist Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID) + a.returnString(teamID) + " §7beigetreten"));
											TagAPI.refreshPlayer(p);
										}
									}else{
										if(a.teamS() == 2){
											if(teamID == 1){
												if(a.returnTeam(teamID).size() > a.returnTeam(2).size()){
													p.sendMessage(main.s + "Das von die gewählte team hat bereits genug Spieler");
													return;
												}
											}else if(teamID == 2){
												if(a.returnTeam(teamID).size() > a.returnTeam(1).size()){
													p.sendMessage(main.s + "Das von die gewählte team hat bereits genug Spieler");
													return;
												}
											}
										}
										
										a.addTeam(p, teamID);
										p.closeInventory();
										
										ItemStack iS = new ItemStack(Material.getMaterial(35));
										ItemMeta im = iS.getItemMeta();
										im.setDisplayName("Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID)) + a.returnString(teamID) + " | §rSpieler: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID)) + a.returnTeam(teamID).size() + "/" + a.getMaxPlayes() / 2);
										iS.setDurability(GUI.returnDurability(a.returnColor(teamID)));
										iS.setItemMeta(im);
										p.getInventory().setHelmet(null);
										p.getInventory().setItem(0, iS);
										p.updateInventory();
										
										GUI.openGui(p, a);
										a.checkTeamSize();
										p.sendMessage(main.s + "Du bist Team: " + ChatColor.translateAlternateColorCodes('&', a.returnColor(teamID) + a.returnString(teamID) + " §7beigetreten"));
										TagAPI.refreshPlayer(p);
									}
								}
							}
						}
					}
				}
				   e.setCancelled(true);
				   p.closeInventory();
				   p.updateInventory();
			}
    }
}
