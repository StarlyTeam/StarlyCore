package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsItemUtil;
import kr.starly.core.nms.tank.NmsOtherUtil;
import lombok.Getter;

public class ItemWrapper {

    private NmsItemUtil support;
    @Getter private Object item;

    public ItemWrapper(NmsItemUtil itemSupport, ItemStackWrapper nmsItemStackWrapper) {
        support = itemSupport;

        try {
            item = NmsOtherUtil.getInstance().ItemStack_getItem().invoke(nmsItemStackWrapper.getNmsItemStack());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getUnlocalizedName(ItemStackWrapper nmsItemStack) {
        try {
            return (String) support.getGetDescriptionIdMethod().invoke(item, nmsItemStack.getNmsItemStack());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}