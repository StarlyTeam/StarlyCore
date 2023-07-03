package kr.starly.testplugin.util;

import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.wrapper.ItemStackWrapper;
import kr.starly.core.nms.wrapper.NBTTagCompoundWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NBTItemUtil {

    @SuppressWarnings("ConstantConditions")
    public static ItemStack getNBTItem(ItemStack itemStack) {
        ItemStack item = new ItemStack(itemStack);
        ItemStackWrapper itemStackWrapper = NmsItemStackUtil.getInstance().asNMSCopy(item);
        NBTTagCompoundWrapper nbtTagCompoundWrapper = itemStackWrapper.getTag();

        if (nbtTagCompoundWrapper == null) nbtTagCompoundWrapper = NmsItemStackUtil.getInstance().getNbtCompoundUtil().newInstance();

        nbtTagCompoundWrapper.setString("test", "test");
        itemStackWrapper.setTag(nbtTagCompoundWrapper);

        ItemMeta itemMeta = NmsItemStackUtil.getInstance().asBukkitCopy(itemStackWrapper).getItemMeta();

        item.setItemMeta(itemMeta);
        return item;
    }
}
