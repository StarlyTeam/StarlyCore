package kr.starly.testplugin.listener;

import kr.starly.core.StarlyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        System.out.println(StarlyCore.getInstance().getName());
//        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
//
//        String itemName = ItemStackNameUtil.getItemName(itemInMainHand);
//
//        System.out.println(itemName);
    }
}
