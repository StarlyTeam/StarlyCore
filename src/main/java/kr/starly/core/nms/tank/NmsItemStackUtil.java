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

    /**
     * NMS 의 ItemStack 을 얻기 위한 Util 객체를 가져옵니다.
     * 이 Util 안에는 NBTTagCompound 를 얻기 위한 Util 객체를 가져갈 수 있는 메서드도 포함되어 있습니다.
     * NmsItemStackUtil#getNbtTagCompoundUtil
     *
     * @return NmsItemStackUtil
     */
    @Nullable
    public static NmsItemStackUtil getInstance() {
        try {
            if (instance == null) instance = new NmsItemStackUtil();
            return instance;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
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

    /**
     * ItemStackWrapper를 Bukkit-API의 ItemStack으로 변경해줍니다.
     *
     * @param nmsItemStack ItemStackWrapper
     * @return Bukkit-API ItemStack
     */
    public ItemStack asBukkitCopy(ItemStackWrapper nmsItemStack) {
        try {
            return (ItemStack) bukkitCopyMethod.invoke(null, nmsItemStack.getNmsItemStack());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Bukkit-API의 ItemStack을 NMS의 ItemStack으로 변경해줍니다.
     *
     * @param itemStack Bukkit-API ItemStack
     * @return ItemStackWrapper
     */
    public Object asNMSCopy(ItemStack itemStack) {
        try {
            return nmsCopyMethod.invoke(null, itemStack);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Bukkit-API의 ItemStack을 ItemStackWrapper로 변경해줍니다.
     *
     * @param itemStack Bukkit-API ItemStack
     * @return ItemStackWrapper
     */
    @Nullable
    public ItemStackWrapper asWrapperCopy(ItemStack itemStack) {
        try {
            return new ItemStackWrapper(asNMSCopy(itemStack), nmsItemSupport, this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
