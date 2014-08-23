package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitEvent implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent e){
		if(ArenaManager.getManager().isInGame(e.getPlayer())){
			ArenaManager.getManager().removePlayer(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerKickEvent(PlayerKickEvent e){
		if(ArenaManager.getManager().isInGame(e.getPlayer())){
			ArenaManager.getManager().removePlayer(e.getPlayer());
		}
	}
}
