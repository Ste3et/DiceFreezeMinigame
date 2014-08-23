package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Message;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnPlayerDamageEvent implements Listener {
	
	@EventHandler
	public void onRightClick(PlayerInteractEntityEvent e){
	 	if((Player) e.getRightClicked() != null){
	 		Player rightcklicked =(Player) e.getRightClicked();
	 		Player rightclicker = e.getPlayer();
	 		if(rightcklicked instanceof Player && rightclicker instanceof Player){
	 			if(ArenaManager.getManager().isInGame(rightcklicked) && ArenaManager.getManager().isInGame(rightclicker)){
	 				Arena a1 = ArenaManager.getManager().returnArena(rightclicker);
	 				Arena a2 = ArenaManager.getManager().returnArena(rightcklicked);
	 				if(a1.equals(a2)){
	 					if(a1.teamS() > 0){
	 						int team1 = a1.getTeam(rightcklicked);
	 						int team2 = a1.getTeam(rightclicker);
	 						if(team1 == team2){
	 							if(a1.isFroozen(rightcklicked) && !a1.isFroozen(rightclicker)){
	 								if(a1.isPLayerFreezeTimer(rightcklicked)){
		 								a1.cancelPLayerFreezeTimer(rightcklicked);
	 								}
	 								a1.unfroozenPlayer(rightcklicked);

	 								if(a1.isShield(rightcklicked)){
	 									a1.cancelShield(rightcklicked);
	 								}
	 								
	 								rightcklicked.teleport(a1.returnLocation(team1));
	 								rightclicker.playSound(rightclicker.getLocation(), Sound.ITEM_PICKUP, 1, 1);
	 								a1.givePlayer(rightcklicked);
	 								
	 								
	 							}
	 						}
	 					}
	 				}
	 			}
	 		}
	 	}
	}
	
	@EventHandler
	 public void onDMG(EntityDamageEvent e) {
		 if(e.getEntity() instanceof Player){
			 Player p = (Player) e.getEntity();
			 if(ArenaManager.getManager().isInGame(p)){
				 e.setCancelled(true);
			 }
		 }
	 }
	
	 @SuppressWarnings("deprecation")
	@EventHandler
	    public void onHit(EntityDamageByEntityEvent e) {
	        if(e.getDamager() instanceof Projectile) {
	            Projectile proj = (Projectile) e.getDamager();
	                if(proj.getType() == EntityType.SNOWBALL) {
	                    if(proj.getShooter() instanceof Player) {
	                        Player shooter = (Player)proj.getShooter();
	                        if(e.getEntity() instanceof Player){
	                            Player hit = (Player) e.getEntity();
	                            if(!shooter.equals(hit)){
	                            	Arena a = null;
	                            	Arena b = null;
	                            	if(ArenaManager.getManager().isInGame(shooter)){
		                            	a = ArenaManager.getManager().returnArena(shooter);
		                            }else{
		                            	return;
		                            }
	                            	
	                            	if(ArenaManager.getManager().isInGame(hit)){
	                            		b = ArenaManager.getManager().returnArena(hit);
	                            	}else{
	                            		return;
	                            	}
	                            	
	                            	if(a.equals(b)){
	                            		if(a.teamS() > 0){
	    	                	        	int a1 = a.getTeam(hit);
	    	                	        	int a2 = a.getTeam(shooter);
	    	                	        	
	    	                	        	if(a1 == a2){
	    	                	        		e.setCancelled(true);
	    	                	        	}
	                            			
	                            			
	                            			if(a.getTeam(shooter) != 999){
	                            				if(a.getTeam(shooter) == a.getTeam(hit)){
	                            					return;
	                            				}
	                            			}
	                            		}
	                            		
	                            		if(!a.isFroozen(hit) && !a.isShield(hit)){
	                            			shooter.playSound(shooter.getLocation(), Sound.ITEM_PICKUP, 1, 1);
	                            			a.freeze(hit);
		                            		if(a.teamS() > 0){
		                            			int team = a.getTeam(shooter);
		                            			int teamHit = a.getTeam(hit);
		                            			int getFrozen = a.getFroozenTeamCount(teamHit);
		                            			a.setfrozen(teamHit, getFrozen + 1);
		                            			Boolean team1 = false;
		                            			Boolean team2 = false;
		                            			Boolean team3 = false;
		                            			Boolean team4 = false;
		                            			
	                            				int tPoints = a.getString(ChatColor.translateAlternateColorCodes('&', a.returnColor(team)) + a.returnString(team)) + 1;
	                            				
	                            				a.addString(ChatColor.translateAlternateColorCodes('&', a.returnColor(team)) + a.returnString(team), tPoints);
	                            				a.setScoreboard();
	                            				
		                            			if(a.getFroozenTeamCount(1) == a.returnTeam(1).size()){
		                            				team1 = true;
		                            			}
		                            			
		                            			if(a.getFroozenTeamCount(2) == a.returnTeam(2).size()){
		                            				team2 = true;
		                            			}
		                            			
		                            			if(a.getFroozenTeamCount(3) == a.returnTeam(3).size()){
		                            				team3 = true;
		                            			}
		                            			
		                            			if(a.getFroozenTeamCount(4) == a.returnTeam(4).size()){
		                            				team4 = true;
		                            			}
		                            			
		                            			if(a.teamS() == 2){
		                            				
		                            				
		                            				if(team1 == true || team2 == true){
		                            					for(Player p : a.returnTeam(team)){
		                            						p.sendMessage(main.s + "Dein Team hat das Spiel gewonnen");
		                            						p.sendMessage(main.s + Message.replace("Du hast %MONEY% Gold gewonnen", "%MONEY%", a.getMoney() + ""));
		                            						main.econ.depositPlayer(p.getName(), a.getMoney());
		                            					}
		                            					
				                            			ArenaManager.getManager().finishGame(a.getId());
				                            			e.setCancelled(true);
		                            				}
		                            			}else if(a.teamS() == 4){
		                            				if((team1 == true && team2 == true && team3 == true && team4 == false) ||
		                            				   (team1 == false && team2 == true && team3 == true && team4 == true) ||
		                            				   (team1 == true && team2 == false && team3 == true && team4 == true) ||
		                            				   (team1 == true && team2 == true && team3 == false && team4 == true)){
		                            					for(Player p : a.returnTeam(team)){
		                            						p.sendMessage(main.s + "Dein Team hat das Spiel gewonnen");
		                            						p.sendMessage(main.s + Message.replace("Du hast %MONEY% Gold gewonnen", "%MONEY%", a.getMoney() + ""));
		                            						main.econ.depositPlayer(p.getName(), a.getMoney());
		                            					}
		                            					
				                            			ArenaManager.getManager().finishGame(a.getId());
				                            			e.setCancelled(true);
		                            				}
		                            			}                     			
		                            		}else{
			                            		a.addString(shooter.getName(),a.getString(shooter.getName()) + 1);
			                            		a.setScoreboard();
			                            		if(a.getPlayers().size() - 1 == a.getFroozenPlayers().size()){
			                            			shooter.sendMessage(main.s + "Du hast das Spiel gewonnen");
			                            			shooter.sendMessage(main.s + Message.replace("Du hast %MONEY% Gold gewonnen", "%MONEY%", a.getMoney() * 2 + ""));
			                            			main.econ.depositPlayer(shooter.getName(), a.getMoney() * 2);
			                            			ArenaManager.getManager().finishGame(a.getId());
			                            			e.setCancelled(true);
			                            		}
		                            		}
	                            		}
	                            	}else{
	                            		return;
	                            	}
	                            }
	                            
	                            if(ArenaManager.getManager().isInGame(shooter)){
	                            	e.setCancelled(true);
	                            }
                            	
                            	if(ArenaManager.getManager().isInGame(hit)){
                            		e.setCancelled(true);
                            	}
	                        }
	                    }
	                }
	        }else{
	        	if(e.getDamager() instanceof Player) {
                    if(ArenaManager.getManager().isInGame((Player) e.getDamager())){
                    	e.setCancelled(true);
                    }
	        	}
	        	
	        	if(e.getEntity() instanceof Player){
                	if(ArenaManager.getManager().isInGame((Player) e.getEntity() )){
                		e.setCancelled(true);
                	}
	        	}
	        }
	        
		 	if(e.getDamager() instanceof Player){
			 		if(ArenaManager.getManager().isInGame((Player) e.getDamager())){
			 			e.setCancelled(true);
			 		}
		 	}
	 }
}
