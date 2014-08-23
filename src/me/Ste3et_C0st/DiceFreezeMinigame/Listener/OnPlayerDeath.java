package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerDeath implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerRespawnEvent e){
		if(ArenaManager.getManager().isInGame(e.getPlayer())){
			Player p = e.getPlayer();
			Arena a = ArenaManager.getManager().returnArena(e.getPlayer());
			if(a.teamS() > 0){
				int Team = a.getTeam(p);
				e.setRespawnLocation(a.returnLocation(Team));
			}else{
				e.setRespawnLocation(a.getArenaSpawn());
			}
		}
	}
}
