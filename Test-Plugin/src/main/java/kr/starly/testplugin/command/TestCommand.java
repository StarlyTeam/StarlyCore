package kr.starly.testplugin.command;

import kr.starly.testplugin.util.NBTItemUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            Player player = (Player) sender;
            player.getInventory().setItem(player.getInventory().getHeldItemSlot(), NBTItemUtil.getNBTItem(player.getInventory().getItemInMainHand()));
            return true;
        }
        return false;
    }
}
