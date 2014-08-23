package me.Ste3et_C0st.DiceFreezeMinigame.System;

import java.util.ArrayList;
import java.util.List;

import me.Ste3et_C0st.DiceFreezeMinigame.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.kitteh.tag.TagAPI;

public class ArenaManager {
    private static ArenaManager am;

    List<Arena> arenas = new ArrayList<Arena>();
    public int arenaSize = 0;
 
    private ArenaManager(){
    }

	public static ArenaManager getManager(){
        if(am == null)
            am = new ArenaManager();
 
        return am;
    }
    
    public Arena getArena(int i){
        for(Arena a : arenas){
            if(a.getId() == i){
                return a;
            }
        }
        return null;
    }
 
    public void removeArena(Arena a){
    	for(int i = 0; i < arenas.size(); i++){
    		if(arenas.get(i).equals(a)){
    			arenas.remove(i);
    			break;
    		}
    	}
    }
    
	@SuppressWarnings("deprecation")
	public void addPlayer(Player p, int i, String team){
		if(getArena(i) != null && !getArena(i).isStartet()){
	        Arena a = getArena(i);
	        if(a == null){
	            p.sendMessage(main.s + "Diese Arena Existiert nicht");
	            return;
	        }
	        
	        if(a.isStartet()){
	        	p.sendMessage(main.s + "Die Arena ist bereits gestartet");
	        	return;
	        }
	        
	        if(a.teamS() > 0 && team == null){
	        	p.sendMessage(main.s + "Du must ein Team angeben");
	        	return;
	        }
	        
	        if(a.returnTeamFromColor(team) == 0){
	        	p.sendMessage(main.s + "Dieses Team Existiert nicht");
	        	return;
	        }
	        p.setGameMode(GameMode.ADVENTURE);
	        p.getInventory().clear();
	        
            if(!a.getSign().isEmpty()){
            	for(Location l : a.getSign()){
            			a.updateSign(l);
            					
            			}
            			
            }
	        
	        if(team == null){
	    		a.addString(p.getName(), 1);
	            a.addString(p.getName(), 0);
	            p.teleport(a.arena);
	        	if(a.getPlayers().size() >= a.getMin()){
	        		if(a.timer1 == false){
		        		a.startGame();
	        		}
	        	}else{
    				sendRound(Message.replace("Es fehlen noch %SPIELER% §7Spieler", "%SPIELER%", a.getMin() - a.getPlayers().size() + ""), a);
    			}
	        }else{
	        	int c = a.returnTeamFromColor(team);
	      		a.addTeam(p, c);
	            p.teleport(a.returnLocation(c));
	            
	            Color c1 = null;
	            
	            if(a.returnColor(c).equalsIgnoreCase("&a")){
	            	c1 = Color.fromRGB(16, 255, 8);
	            }else if(a.returnColor(c).equalsIgnoreCase("&b")){
	            	c1 = Color.fromRGB(8, 255, 255);
	            }else if(a.returnColor(c).equalsIgnoreCase("&c")){
	            	c1 = Color.fromRGB(255, 8, 8);
	            }else if(a.returnColor(c).equalsIgnoreCase("&d")){
	            	c1 = Color.fromRGB(255, 8, 251);
	            }else if(a.returnColor(c).equalsIgnoreCase("&e")){
	            	c1 = Color.fromRGB(247, 255, 8);
	            }else if(a.returnColor(c).equalsIgnoreCase("&f")){
	            	c1 = Color.fromRGB(255, 255, 255);
	            }else if(a.returnColor(c).equalsIgnoreCase("&0")){
	            	c1 = Color.fromRGB(000, 000, 000);
	            }else if(a.returnColor(c).equalsIgnoreCase("&1")){
	            	c1 = Color.fromRGB(0, 70, 145);
	            }else if(a.returnColor(c).equalsIgnoreCase("&2")){
	            	c1 = Color.fromRGB(46, 145, 0);
	            }else if(a.returnColor(c).equalsIgnoreCase("&3")){
	            	c1 = Color.fromRGB(0, 145, 128);
	            }else if(a.returnColor(c).equalsIgnoreCase("&4")){
		           c1 = Color.fromRGB(209, 48, 48);
	            }else if(a.returnColor(c).equalsIgnoreCase("&5")){
	            	c1 = Color.fromRGB(104, 0, 145);
	            }else if(a.returnColor(c).equalsIgnoreCase("&6")){
	            	c1 = Color.fromRGB(209, 192, 0);
	            }else if(a.returnColor(c).equalsIgnoreCase("&7")){
	            	c1 = Color.fromRGB(179, 179, 179);
	            }else if(a.returnColor(c).equalsIgnoreCase("&8")){
	            	c1 = Color.fromRGB(82, 82, 82);
	            }else if(a.returnColor(c).equalsIgnoreCase("&9")){
	            	c1 = Color.fromRGB(85, 3, 166);
	            }
	            
	            ItemStack lhelmet = new ItemStack(Material.WOOL, 1);
	            ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
	            ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
	            ItemStack lleggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
	            
	            
	            LeatherArmorMeta lam2 = (LeatherArmorMeta)lchest.getItemMeta();
	            LeatherArmorMeta lam3 = (LeatherArmorMeta)lboots.getItemMeta();
	            LeatherArmorMeta lam4 = (LeatherArmorMeta)lleggings.getItemMeta();
	            
	            lhelmet.setDurability(GUI.returnDurability(a.returnColor(c)));
	            
	            lam2.setColor(c1);
	            lam3.setColor(c1);
	            lam4.setColor(c1);
	            
	            
	            lchest.setItemMeta(lam2);
	            lboots.setItemMeta(lam3);
	            lleggings.setItemMeta(lam4);
	            
	            p.getInventory().setHelmet(lhelmet);
	            p.getInventory().setBoots(lboots);
	            p.getInventory().setLeggings(lleggings);
	            p.getInventory().setChestplate(lchest);
	            p.updateInventory();

	    		a.givePlayer(p);
	            
	    		if(a.teams == 2){
	    			String c0 = ChatColor.translateAlternateColorCodes('&', a.returnColor(1));
	    			String c2 = ChatColor.translateAlternateColorCodes('&', a.returnColor(2));
	                a.addString(c0 + a.returnString(1), 0);
	                a.addString(c2 + a.returnString(2), 0);
	                if(a.PlayerSize().get(1) >= a.getMin() && a.PlayerSize().get(2) >= a.getMin()){
	        			if(a.timer1 == false){
			    				a.startGame();
	        			}
	        		}else{
	        			if(a.getMin() - a.returnTeam(1).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(1).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(1)) + a.returnString(1) + " " + ö + " §7Spieler", a);
	        			}
	        			if(a.getMin() - a.returnTeam(2).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(2).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + a.returnString(2) + " " + ö + " §7Spieler", a);
	        			}
	        		}

	    		}else if(a.teamS() == 4){
	                a.addString(a.returnString(1), 0);
	                a.addString(a.returnString(2), 0);
	                a.addString(a.returnString(4), 0);
	                a.addString(a.returnString(3), 0);
	    			if(a.PlayerSize().get(1) >= a.getMin() && a.PlayerSize().get(2) >= a.getMin() && a.PlayerSize().get(3) >= a.getMin() && a.PlayerSize().get(4) >= a.getMin()){
	        			if(a.timer1 == false){
			    				a.startGame();
	        			}
	        		}else{
	        			if(a.getMin() - a.returnTeam(1).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(1).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(1)) + a.returnString(1) + " " + ö + " §7Spieler", a);
	        			}
	        			if(a.getMin() - a.returnTeam(2).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(2).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(2))  + a.returnString(2) + " " + ö + " §7Spieler", a);
	        			}
	        			
	        			if(a.getMin() - a.returnTeam(3).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(1).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(3)) + a.returnString(3) + " " + ö + " §7Spieler", a);
	        			}
	        			if(a.getMin() - a.returnTeam(4).size() > 0){
	        				int ö = a.getMin() - a.returnTeam(2).size();
	        				sendRound("Es fehlen im Team " + ChatColor.translateAlternateColorCodes('&', a.returnColor(4))  + a.returnString(4) + " " + ö + " §7Spieler", a);
	        			}
	        		}
	    		}
	        }
	        
            if(!a.getSign().isEmpty()){
            	for(Location l : a.getSign()){
            			a.updateSign(l);
            					
            			}
            			
            }
	        p.getInventory().clear();
	        p.updateInventory();
	        a.setScoreboard();
	        
		}else{
	        Arena a = getArena(i);
	        if(a == null){
	        	p.sendMessage(main.s + "Diese Arena Existiert nicht");
	            return;
	        }
	        
	        if(a.isStartet()){
	        	p.sendMessage(main.s + "Diese Arena ist bereits gestartet");
	        	return;
	        }
	        
	        if(a.teamS() > 0 && team == null){
	        	p.sendMessage(main.s + "Du must ein Team angeben");
	        	return;
	        }
	        
	        if(a.returnTeamFromColor(team) == 0){
	        	p.sendMessage(main.s + "Dieses Team Existiert nicht");
	        	return;
	        }
			
			p.sendMessage(main.s + "Du befindest dich bereits in einer FreezeIT Arena");
		}
    }
    
	public synchronized void nextRound(int i){
        Arena a = getArena(i);
        if(a == null){
        	//Invalid Arena
            return;
        }
        
        if(!a.isStartet()){
        	//Arena not running
            return;
        }
    }
    
    public Arena returnArena(Player p){
        for(Arena a : arenas){
            if(a.getPlayers().contains(p)){
            	 return a;
            }  
        }
        return null;
    }
    
  	@SuppressWarnings("deprecation")
	public void removePlayer(Player p){
          Arena a = null;
          for(Arena arena : arenas){
              if(arena.getPlayers().contains(p)){
                  a = arena;
              }
          }
          if(a == null || !a.getPlayers().contains(p)){
              //Invalid 
              return;
          }
          
          if(a.isShield(p)){
        	  a.cancelShield(p);
          }


          for(Player player : Bukkit.getOnlinePlayers()){
        	  p.showPlayer(player);
          }
          
          TagAPI.refreshPlayer(p);
          
          p.closeInventory();
	        p.getInventory().setHelmet(null);
	        p.getInventory().setChestplate(null);
	        p.getInventory().setLeggings(null);
	        p.getInventory().setBoots(null);
          p.updateInventory();
          
          if(a.teamS() > 0){
        	  int AID = a.getTeam(p);
        	  if(a.teamS() == 2){
        		  a.removePlayerTeam(AID, p);
        		  
        		  int AP1 = a.returnTeam(1).size();
        		  int AP2 = a.returnTeam(2).size();
        		  
        		  if(a.isStartet()){
        			  if(AP1 < a.getMin() || AP2 < a.getMin()){
        				  sendRound("Die Arena wurde beendet da zu wenig spieler anwesend sind", a);
        				  finishGame(a.getId());
        				  return;
        			  }
        		  }else{
        			  if(a.timer1 == true && (AP1 < a.getMin() || AP2 < a.getMin())){
            				  sendRound("Die Arena wurde beendet da zu wenig spieler anwesend sind", a);
            				  a.cancelGame();
        			  }
        		  }
        	  }else if(a.teamS() == 4){
        		  a.removePlayerTeam(AID, p);
        		  
        		  int AP1 = a.returnTeam(1).size();
        		  int AP2 = a.returnTeam(2).size();
        		  int AP3 = a.returnTeam(3).size();
        		  int AP4 = a.returnTeam(4).size();
        		  
        		  if(a.isStartet()){
        			  if(AP1 < a.getMin() || AP2 < a.getMin() || AP3 < a.getMin() || AP4 < a.getMin()){
        				  sendRound("Die Arena wurde beendet da zu wenig spieler anwesend sind", a);
        				  finishGame(a.getId());
        				  return;
        			  }
        		  }else{
        			  if(a.timer1 == true && (AP1 < a.getMin() || AP2 < a.getMin() || AP3 < a.getMin() || AP4 < a.getMin())){
        				  if(a.returnGame()){
             				  a.cancelGame(); 
        				  }
   
        				  sendRound("Die Arena wurde beendet da zu wenig spieler anwesend sind", a);
        				  return;
        			  }
        		  }
        	  }
          }else{
        	  if(a.getPlayers().size() - 1 < a.getMin()){
        		  sendRound("Die Arena wurde beendet da zu wenig spieler anwesend sind", a);
				  finishGame(a.getId());
				  return;
        	  }
        	  
        	  a.removeString(p.getName());
          }
          
    	  
  	  		p.getInventory().clear();
	        p.getInventory().setHelmet(null);
	        p.getInventory().setChestplate(null);
	        p.getInventory().setLeggings(null);
	        p.getInventory().setBoots(null);
  	  		p.updateInventory();
			if(a.isFroozen(p)){
				a.unfroozenPlayer(p);
			}
			p.teleport(a.exit);
			a.returnScore(p.getName());
			if(p.getScoreboard() != null){
				p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
			}
			a.removePlayer(p);
			if(a.isStartet()){
				a.setScoreboard();
			}

            if(!a.getSign().isEmpty()){
            	for(Location l : a.getSign()){
            			a.updateSign(l);	
            			}
            			
            }
			sendRound(Message.replace("Der Spieler§6 %SPIELER%§7 hat die Arena verlassen", "%SPIELER%", p.getName()), a);
    }
  
  	public void sendRound(String s, Arena a){
  		if(!a.getPlayers().isEmpty()){
  			for(Player p : a.getPlayers()){
  				p.sendMessage(main.s + s);
  			}
  		}
  	}
  	
    public Arena createArena(Location c1,Location c2,Location arena,Location exit,Player p,Integer min,String name, Location lobby){
        int num = arenaSize + 1;
        arenaSize++;
        
        
        Arena a = new Arena(c1, c2, arena, exit, min, name, num, lobby);
        
        arenas.add(a);
        if(p != null){
        	saveMap.saveMap2(arenaSize);
        	p.sendMessage(main.s + Message.replace(Message.message.get(9), "%ARENA%", name));
        }

        return a;
    }
    
    public Integer getIDBackfromName(String name){
    	for(Arena a : arenas){
    		if(a.getName().equalsIgnoreCase(name)){
    			return a.getId();
    		}
    	}
    	return 987554;
    }
    
    public boolean ArenaExist(String name){
    	for(Arena a : arenas){
    		if(a.getName().equalsIgnoreCase(name)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isInGame(Player p){
        for(Arena a : arenas){
            if(a.getPlayers().contains(p))
                return true;
        }
        return false;
    }
    
	@SuppressWarnings("deprecation")
	public void finishGame(int ID) {
			Arena a = getArena(ID);
			for(Player pl : a.getPlayers()){
		          for(Player player : Bukkit.getOnlinePlayers()){
		        	  pl.showPlayer(player);
		          }
			}
			if(a.teamS() == 0){
				for(Player p : a.getPlayers()){
					if(a.isPLayerFreezeTimer(p)){
						a.cancelPLayerFreezeTimer(p);
					}
					if(a.isFroozen(p)){
						a.unfroozenPlayer(p);
					}
					
					if(a.isShield(p)){
						a.cancelShield(p);
					}
					
					p.teleport(a.exit);
					p.getInventory().clear();
			        p.getInventory().setHelmet(null);
			        p.getInventory().setChestplate(null);
			        p.getInventory().setLeggings(null);
			        p.getInventory().setBoots(null);
					p.updateInventory();
					a.returnScore(p.getName());
					if(p.getScoreboard() != null){
						p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					}
				}
				a.froozen.clear();
				a.pPoints.clear();
				a.players.clear();
				a.started = false;
			}else if(a.teamS() == 2){
				for(Player p : a.getPlayers()){
					if(a.isPLayerFreezeTimer(p)){
						a.cancelPLayerFreezeTimer(p);
					}
					
					if(a.isFroozen(p)){
						a.unfroozenPlayer(p);
					}
					
					if(a.isShield(p)){
						a.cancelShield(p);
					}

					p.teleport(a.exit);
					p.getInventory().clear();
			        p.getInventory().setHelmet(null);
			        p.getInventory().setChestplate(null);
			        p.getInventory().setLeggings(null);
			        p.getInventory().setBoots(null);
					p.updateInventory();
					a.returnScore(p.getName());
					if(p.getScoreboard() != null){
						p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					}
					TagAPI.refreshPlayer(p);
				}
				a.froozen.clear();
				a.pPoints.clear();
				a.players.clear();
				a.team01.clear();
				a.team02.clear();
				a.started = false;
				a.timer1 = false;
				a.setfrozen(1, 0);
				a.setfrozen(2, 0);
			}else if(a.teamS() == 4){
				for(Player p : a.getPlayers()){
					if(a.isPLayerFreezeTimer(p)){
						a.cancelPLayerFreezeTimer(p);
					}
					if(a.isFroozen(p)){
						a.unfroozenPlayer(p);
					}
					
					if(a.isShield(p)){
						a.cancelShield(p);
					}
					p.teleport(a.exit);
					p.getInventory().clear();
			        p.getInventory().setHelmet(null);
			        p.getInventory().setChestplate(null);
			        p.getInventory().setLeggings(null);
			        p.getInventory().setBoots(null);
					p.updateInventory();
					a.returnScore(p.getName());
					if(p.getScoreboard() != null){
						p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					}
				}
				a.froozen.clear();
				a.pPoints.clear();
				a.players.clear();
				a.team01.clear();
				a.team02.clear();
				a.team03.clear();
				a.team04.clear();
				a.started = false;
				a.timer1 = false;
				a.setfrozen(1, 0);
				a.setfrozen(2, 0);
				a.setfrozen(3, 0);
				a.setfrozen(4, 0);
			}else{
				for(Player p : a.getPlayers()){
					if(a.isPLayerFreezeTimer(p)){
						a.cancelPLayerFreezeTimer(p);
					}
					if(a.isFroozen(p)){
						a.unfroozenPlayer(p);
					}
					
					if(a.isShield(p)){
						a.cancelShield(p);
					}
					p.teleport(a.exit);
					a.returnScore(p.getName());
					p.getInventory().clear();
			        p.getInventory().setHelmet(null);
			        p.getInventory().setChestplate(null);
			        p.getInventory().setLeggings(null);
			        p.getInventory().setBoots(null);
					p.updateInventory();
					if(p.getScoreboard() != null){
						p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					}
					TagAPI.refreshPlayer(p);
				}
				a.froozen.clear();
				a.pPoints.clear();
				a.players.clear();
				a.started = false;
			}
			
            if(!a.getSign().isEmpty()){
            	for(Location l : a.getSign()){
            			a.updateSign(l);
            					
            			}
            			
            }
	}

	@SuppressWarnings("deprecation")
	public void pushPlayer(Player p, int aid) {
		if(!isInGame(p)){
			Arena a = getArena(aid);
			p.teleport(a.lobby);
			p.getInventory().clear();
			ItemStack is = new ItemStack(Material.getMaterial(35));
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§c§lWähle ein Team !");
			is.setDurability((short) 8);
			is.setItemMeta(im);
			p.getInventory().remove(0);
			
			ItemStack IS = new ItemStack(Material.NETHER_STAR);
			ItemMeta IM = IS.getItemMeta();
			IM.setDisplayName(main.s + "Back To the lobby");
			IS.setItemMeta(IM);
			
			
			p.getInventory().setItem(0, is);
			p.getInventory().setItem(4, IS);
			p.updateInventory();
	        a.addPlayer(p);
            if(!a.getSign().isEmpty()){
            	for(Location l : a.getSign()){
            			a.updateSign(l);
            					
            			}
            			
            }
            
            p.setAllowFlight(false);
            p.setFlying(false);
		}
	}
}
