package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnPlayerDrop implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		if(e.getItemDrop() != null){
			if(ArenaManager.getManager().isInGame(p)){
				int Slot = p.getInventory().getHeldItemSlot();
				Item i = e.getItemDrop();
				p.getInventory().remove(Slot);
				p.getInventory().setItem(Slot, i.getItemStack());
				p.updateInventory();
				e.getItemDrop().remove();
			}
		}
	}
}
