package kr.starly.core.nms.tank;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class NmsItemUtil {

    private final Class<?> ItemClass;
    private final Class<?> ItemStackClass;
    private final Method getDescriptionIdMethod;
    private final Method useMethod;

    public NmsItemUtil() {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        ItemClass = nmsOtherUtil.Item();
        ItemStackClass = nmsOtherUtil.ItemStack();
        getDescriptionIdMethod = nmsOtherUtil.Item_getDescriptionId();
        useMethod = nmsOtherUtil.Item_use();
    }
}