package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import java.sql.SQLException;

import me.Ste3et_C0st.DiceFreezeMinigame.System.TokenShop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnPlayerInteractEntityEvent implements Listener {
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) throws SQLException {
	  Entity entity = event.getRightClicked();
	  if (!(entity instanceof NPC))
	      return;
	  	  NPC npc = (NPC) entity;
	  	  
	  	if(npc.getCustomName() != null){
	  		String s = npc.getCustomName();
	  		s = ChatColor.stripColor(s);
		  	  if(s.equalsIgnoreCase("Token Shop - FreezeIT")){
		  		  TokenShop.createShop(event.getPlayer());
		  	  }
	  	}

	   // do stuff
	}
}
