package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnPlayerBlockPlace implements Listener {
	
	@EventHandler
	public void onPlayerBlockPlace(BlockPlaceEvent e){
		if(ArenaManager.getManager().isInGame(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent e){
		if(e.getBlock() != null){
			if(e.getBlock().getType().equals(Material.SIGN) || e.getBlock().getType().equals(Material.WALL_SIGN) || e.getBlock().getType().equals(Material.SIGN_POST)){
				for(int i = 0; i < ArenaManager.getManager().arenaSize; i++){
					Arena a = ArenaManager.getManager().getArena(i);
						if(a.getSign().equals(e.getBlock().getLocation())){
							a.getSign().remove(e.getBlock().getLocation());
							e.getPlayer().sendMessage("Sign entfernt");
						}
				}
			}
		}

		
		if(ArenaManager.getManager().isInGame(e.getPlayer())){
			e.setCancelled(true);
		}
	}
}
