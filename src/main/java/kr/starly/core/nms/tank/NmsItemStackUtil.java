package kr.starly.core.nms.tank;


import kr.starly.core.nms.wrapper.ItemStackWrapper;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

public class NmsItemStackUtil {

    private static NmsItemStackUtil instance;

    private final Method bukkitCopyMethod;
    private final Method nmsCopyMethod;
    @Getter private final Method setTagMethod;
    @Getter private final Method getTagMethod;
    @Getter private final NmsNbtTagCompoundUtil nbtCompoundUtil;
    private final NmsItemUtil nmsItemSupport;

    public static NmsItemStackUtil getInstance() {
        if (instance == null) {
            try {
                instance = new NmsItemStackUtil();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to initialize NmsItemStackUtil.", ex);
            }
        }
        return instance;
    }

    private NmsItemStackUtil() throws NoSuchMethodException {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        Class<?> craftItemStack = nmsOtherUtil.CraftItemStack();
        Class<?> NMSItemStack = nmsOtherUtil.ItemStack();
        setTagMethod = nmsOtherUtil.ItemStack_setTag();
        getTagMethod = nmsOtherUtil.ItemStack_getTag();

        bukkitCopyMethod = craftItemStack.getDeclaredMethod("asBukkitCopy", NMSItemStack);
        nmsCopyMethod = craftItemStack.getDeclaredMethod("asNMSCopy", ItemStack.class);

        nbtCompoundUtil = new NmsNbtTagCompoundUtil();
        nmsItemSupport = new NmsItemUtil();
    }

    public ItemStack asBukkitCopy(ItemStackWrapper nmsItemStack) {
        try {
            return (ItemStack) bukkitCopyMethod.invoke(null, nmsItemStack.getNmsItemStack());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to convert NMS ItemStack to Bukkit ItemStack.", ex);
        }
    }

    @Nullable
    public ItemStackWrapper asNMSCopy(ItemStack itemStack) {
        try {
            return new ItemStackWrapper(nmsCopyMethod.invoke(null, itemStack), nmsItemSupport, this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert Bukkit ItemStack to NMS ItemStack.", e);
        }
    }

    @Nullable
    public ItemStackWrapper asWrapperCopy(ItemStack itemStack) {
        return asNMSCopy(itemStack);
    }
}
