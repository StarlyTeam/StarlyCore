package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.tank.NmsItemUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemStackWrapper {

    private Object nmsItemStack;
    private NmsItemUtil itemSupport;
    private NmsItemStackUtil wrapper;

    public NBTTagCompoundWrapper getTag() {
        try {
            Object obj = wrapper.getGetTagMethod().invoke(nmsItemStack);
            if (obj == null) return null;
            return new NBTTagCompoundWrapper(obj, wrapper.getNbtCompoundUtil());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setTag(NBTTagCompoundWrapper tag) {
        try {
            wrapper.getSetTagMethod().invoke(nmsItemStack, tag.getNbtTagCompound());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ItemWrapper getItem() {
        return new ItemWrapper(itemSupport, this);
    }
}