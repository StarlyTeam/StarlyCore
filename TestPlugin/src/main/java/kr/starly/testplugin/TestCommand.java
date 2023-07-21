package kr.starly.testplugin;

import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.wrapper.ItemStackWrapper;
import kr.starly.core.nms.wrapper.NBTTagCompoundWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            Player player = (Player) sender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemStackWrapper nmsItemWrapper = NmsItemStackUtil.getInstance().asNMSCopy(itemStack);
            NBTTagCompoundWrapper nbtTagCompoundWrapper = nmsItemWrapper.getTag();

            if (nbtTagCompoundWrapper == null) nbtTagCompoundWrapper = NmsItemStackUtil.getInstance().getNbtCompoundUtil().newInstance();

            nbtTagCompoundWrapper.setString("test", "test string");
            nmsItemWrapper.setTag(nbtTagCompoundWrapper);
            ItemMeta itemMeta = NmsItemStackUtil.getInstance().asBukkitCopy(nmsItemWrapper).getItemMeta();
            itemStack.setItemMeta(itemMeta);

            player.sendMessage("complete");
            return true;
        }
        return false;
    }
}
