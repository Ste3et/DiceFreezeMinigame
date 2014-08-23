package me.Ste3et_C0st.DiceFreezeMinigame.Listener;

import me.Ste3et_C0st.DiceFreezeMinigame.main;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Arena;
import me.Ste3et_C0st.DiceFreezeMinigame.System.ArenaManager;
import me.Ste3et_C0st.DiceFreezeMinigame.System.Message;
import me.Ste3et_C0st.DiceFreezeMinigame.System.saveMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignEvent implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void SignChangEvent(SignChangeEvent e){
		if(e.getLine(0).equalsIgnoreCase("[Freeze]")){
			if(e.getPlayer().hasPermission("FreezeIT.admin")){
				e.getLine(0);
				e.setLine(0, "§8[§2Freeze§8]");
			
			if(!ArenaManager.getManager().ArenaExist(ChatColor.stripColor(e.getLine(1)))){
				e.setCancelled(true);
			}else{
				int Aid = ArenaManager.getManager().getIDBackfromName(ChatColor.stripColor(e.getLine(1)));
				Arena a = ArenaManager.getManager().getArena(Aid);
				e.setLine(1, "§8FreezeIT-" + a.id);
				e.setLine(2, "§8Map : §2" + e.getLine(2));
				e.setLine(3, "§8" + a.getPlayers().size() + "/" + a.getMaxPlayes());
				a.updateSign(e.getBlock().getLocation());
				saveMap.saveMap2(a.id);
			}
			}else{
				e.getPlayer().sendMessage(main.s + Message.message.get(0));
				e.setCancelled(true);
			}
			}
	}
	
	@EventHandler
	public void onPlayerClickSign(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getClickedBlock() != null){
			if(e.getClickedBlock().getType() != null){
				if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN){
					if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
						Sign sign = (Sign) e.getClickedBlock().getState();
						String l1 = ChatColor.stripColor(sign.getLine(0));
						String l2 = ChatColor.stripColor(sign.getLine(1));
						
						if(ChatColor.stripColor(l1).equalsIgnoreCase("[Freeze]")){
									l2 = l2.replace("FreezeIT-", "");
									if(ArenaManager.getManager().getArena(Integer.parseInt(l2)) != null){
										int Aid = Integer.parseInt(l2);
										Arena a = ArenaManager.getManager().getArena(Aid);
										if(ArenaManager.getManager().isInGame(p)){
											p.sendMessage(main.s + Message.message.get(17));
											return;
										}
										
										if(a.isStartet() == true){
											p.sendMessage(main.s + Message.message.get(18));
											return;
										}
										
										if(a.returnGame() == true){
											p.sendMessage(main.s + Message.message.get(18));
											return;
										}
										
										if(a.isStartet() == false){
											ArenaManager.getManager().pushPlayer(p, Aid);
										}
									}else{
										p.sendMessage(main.s + Message.message.get(16));
									}
						}
					}
				}
			}
		}

	}

}
