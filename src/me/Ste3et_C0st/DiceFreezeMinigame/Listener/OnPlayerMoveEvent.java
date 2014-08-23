package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.GUI;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerMoveEvent implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			p.setHealth(20.0);
			p.setFoodLevel(20);
			Arena a = ArenaManager.getManager().returnArena(p);
			if(!isInside(e.getPlayer().getLocation(), a.getCorner1(), a.getCorner2()) && a.isStartet()){
				if(a.teamS() > 0){
					int AID = a.getTeam(p);
					p.teleport(a.returnLocation(AID));
				}else{
					p.teleport(a.getArenaSpawn());
				}
			}
			
			if(p.getInventory().getHelmet() == null){
	        	int c = a.getTeam(p);
	            ItemStack lhelmet = new ItemStack(Material.WOOL, 1);
	            lhelmet.setDurability(GUI.returnDurability(a.returnColor(c)));
	            p.getInventory().setHelmet(lhelmet);
	            p.updateInventory();
			}
			
			if(a.isFroozen(p)){
				Location l2 = a.getFroozen(p);
				if((e.getTo().getX() != e.getFrom().getX()) || (e.getTo().getZ() != e.getFrom().getZ())){
					p.teleport(l2);
				}
				
				if(p.isOnGround()){
					if((e.getTo().getY() != e.getFrom().getY())){
						p.teleport(l2);
					}
				}
			}
		}
	}
	
	  public static Boolean isInside(Location loc, Location corner1, Location corner2)
	  {
	    double xMin = 0.0D;
	    double xMax = 0.0D;
	    double yMin = 0.0D;
	    double yMax = 0.0D;
	    double zMin = 0.0D;
	    double zMax = 0.0D;
	    double x = loc.getX();
	    double y = loc.getY();
	    double z = loc.getZ();
	    
	    xMin = Math.min(corner1.getX(), corner2.getX());
	    xMax = Math.max(corner1.getX(), corner2.getX());
	    
	    yMin = Math.min(corner1.getY(), corner2.getY());
	    yMax = Math.max(corner1.getY(), corner2.getY());
	    
	    zMin = Math.min(corner1.getZ(), corner2.getZ());
	    zMax = Math.max(corner1.getZ(), corner2.getZ());
	    if ((x >= xMin) && (x <= xMax) && (y >= yMin) && (y <= yMax) && (z >= zMin) && (z <= zMax)) {
	      return Boolean.valueOf(true);
	    }
	    return Boolean.valueOf(false);
	  }
}
