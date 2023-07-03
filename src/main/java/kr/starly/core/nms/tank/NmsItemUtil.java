package kr.starly.core.nms.tank;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class NmsItemUtil {

    private final Class<?> NmsItemClass;
    private final Class<?> NmsItemStackClass;
    private Method jMethod;
    private Method aMethod;

    NmsItemUtil(String className, Class<?> nmsItemStackClass) throws ClassNotFoundException, NoSuchMethodException {
        NmsItemClass = Class.forName(className);
        NmsItemStackClass = nmsItemStackClass;
        jMethod = NmsItemClass.getMethod("j", nmsItemStackClass);
        aMethod = NmsItemClass.getMethod("a", NmsOtherUtil.getWorldClass(), NmsOtherUtil.getEntityHumanClass(), NmsOtherUtil.getEnumHandClass());
    }
}
