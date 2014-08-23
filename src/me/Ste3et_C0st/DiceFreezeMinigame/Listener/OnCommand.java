package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnCommand implements Listener {

	@EventHandler
	public void onPlayerDropItemEvent(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		if(ArenaManager.getManager().isInGame(p)){
			if(!(e.getMessage().startsWith("/hub"))){
				p.sendMessage(main.s + "Es ist nur /hub und erlaubt");
				e.setCancelled(true);
			}
		}
	}
}
