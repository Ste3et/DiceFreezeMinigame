package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import java.sql.SQLException;

import me.Ste3et_C0st.DiceFreezeMinigame.Editor;
import me.Ste3et_C0st.DiceFreezeMinigame.Team;
import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GUI;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GuiSelector;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GuiTeam;
import me.Ste3et_C0st.DiceFreezeMinigame.System.TokenShop;

import org.bukkit.Bukkit;
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
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryClickEvent e) throws SQLException
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
											p.sendMessage(main.s + "Das von dir gewählte team hat bereits genug Spieler");
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
				}else if(e.getCurrentItem() != null && !a.isStartet() && GuiSelector.inv.get(p) != null && e.getInventory().equals(GuiSelector.inv.get(p))){
					ItemStack is = e.getCurrentItem();
					if(is.getType() != null && is.getType().equals(Material.SNOW_BALL)){
						if(is.hasItemMeta()){
							if(is.getItemMeta().hasDisplayName()){
								if(is.getItemMeta().getDisplayName().startsWith("§c")){
									String s = ChatColor.stripColor(is.getItemMeta().getDisplayName());
									if(s.endsWith("III")){
										a.addSnowball(p, 3);
										p.sendMessage(main.s + "Schneeball Stufe [" + 3 +"] gewählt");
										   e.setCancelled(true);
										   p.closeInventory();
										   p.updateInventory();
										return;
									}
									if(s.endsWith("II")){
										a.addSnowball(p, 2);
										p.sendMessage(main.s + "Schneeball Stufe [" + 2 +"] gewählt");
									} 
									if(s.endsWith("IV")){
										a.addSnowball(p, 4);
										p.sendMessage(main.s + "Schneeball Stufe [" + 4 +"] gewählt");
									}
								}else if(is.getItemMeta().getDisplayName().startsWith("§a")){
									p.sendMessage(main.s + "Du hast dieses Item bereits gewählt");
								}else if(is.getItemMeta().getDisplayName().startsWith("§6")){
									p.sendMessage(main.s + "Dieses Item ist noch gespert");
								}
								
							}
						}
					}else if(is.getType() != null && is.getType().equals(Material.APPLE)){
						if(is.hasItemMeta()){
							if(is.getItemMeta().hasDisplayName()){
								if(is.getItemMeta().getDisplayName().startsWith("§c")){
									String s = ChatColor.stripColor(is.getItemMeta().getDisplayName());
									if(s.endsWith("III")){
										a.addTimer(p, 3);
										p.sendMessage(main.s + "Timer Stufe [" + 3 +"] gewählt");
										   e.setCancelled(true);
										   p.closeInventory();
										   p.updateInventory();
										return;
									} 
									if(s.endsWith("II")){
										a.addTimer(p, 2);
										p.sendMessage(main.s + "Timer Stufe [" + 2 +"] gewählt");
									} 
									if(s.endsWith("IV")){
										a.addTimer(p, 4);
										p.sendMessage(main.s + "Timer Stufe [" + 4 +"] gewählt");
									}
								}else if(is.getItemMeta().getDisplayName().startsWith("§a")){
									p.sendMessage(main.s + "Du hast dieses Item bereits gewählt");
								}else if(is.getItemMeta().getDisplayName().startsWith("§6")){
									p.sendMessage(main.s + "Dieses Item ist noch gespert");
								}

							}
						}
					} 
				}
				   e.setCancelled(true);
				   p.closeInventory();
				   p.updateInventory();
			}else if(Team.player.get(p) != null){
				ItemStack is = e.getCurrentItem();
				if(is.getType() != null && is.getType().equals(Material.WOOL)){
					e.setCancelled(true);
					String Aname = Team.player.get(p);
					Arena a = ArenaManager.getManager().getArena(ArenaManager.getManager().getIDBackfromName(Aname));
					if(GuiTeam.inv.get(p) == null){
						return;
					}

					if(!e.getInventory().equals(GuiTeam.inv.get(p))){
						p.sendMessage("error");
						return;
					}
					
					int i = Integer.parseInt(e.getInventory().getTitle().replace("Team: ", ""));
					int am = is.getAmount() - 1;
					String teamN = GuiTeam.returnColorName(am);
					String teamC = GuiTeam.returnColor(am);
					a.createTeam(i, p.getLocation(), teamN, teamC);
					Team.check(Aname, p);
					if(i == 1){
						p.getInventory().setItem(0, null);
						p.updateInventory();
						p.getInventory().setItem(0, Editor.is(Material.WOOL,main.s + "Team 1", null, GuiTeam.returnDurability(GuiTeam.returnColor(am)), am + 1));
					}else if(i == 2){
						p.getInventory().setItem(2, null);
						p.updateInventory();
						p.getInventory().setItem(2, Editor.is(Material.WOOL,main.s + "Team 2", null, GuiTeam.returnDurability(GuiTeam.returnColor(am)), am + 1));
					}
					
					p.closeInventory();
				}
			}else if(TokenShop.tokeninv.get(p) != null){
				if(e.getInventory().equals(TokenShop.tokeninv.get(p))){
					e.setCancelled(true);
					if(e.getCurrentItem().hasItemMeta()){
						if(e.getCurrentItem().getItemMeta().hasDisplayName()){
							if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§cSchneball")){
								if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke II")){
									if(main.token.getTokens(p) >= 200){
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Snow.II");
										p.sendMessage(main.s + "Dir wurden 200 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}else if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke III")){
									if(main.token.getTokens(p) >= 500){
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Snow.III");
										main.token.removeToken(p, 500);
										p.sendMessage(main.s + "Dir wurden 500 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}else if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke IV")){
									if(main.token.getTokens(p) >= 1000){
										
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Snow.IV");
										main.token.removeToken(p, 1000);
										p.sendMessage(main.s + "Dir wurden 1000 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§cSchutzshild")){
								if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke II")){
									if(main.token.getTokens(p) >= 100){
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Save.II");
										main.token.removeToken(p, 100);
										p.sendMessage(main.s + "Dir wurden 100 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}else if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke III")){
									if(main.token.getTokens(p) >= 200){
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Save.III");
										main.token.removeToken(p, 200);
										p.sendMessage(main.s + "Dir wurden 200 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}else if(e.getCurrentItem().getItemMeta().getDisplayName().endsWith("Stärke IV")){
									if(main.token.getTokens(p) >= 400){
										Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " add Freeze.Save.IV");
										main.token.removeToken(p, 400);
										p.sendMessage(main.s + "Dir wurden 400 Tokens abgezogen");
									}else{
										p.closeInventory();
										p.sendMessage(main.s + "Dazu hast du zu wenig Tokens");
									}
								}
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§aSchneball") || e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§aSchutzshild")){
								p.sendMessage(main.s + "Du hast das bereits gekauft");
							}
						}
					}
				}
			}
    }
}
