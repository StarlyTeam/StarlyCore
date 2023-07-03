package kr.starly.testplugin.listener;

import kr.starly.core.util.ItemStackNameUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        String itemName = ItemStackNameUtil.getKorean(itemInMainHand);

        player.sendMessage(itemName);
    }
}
