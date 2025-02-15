package kr.starly.core.nms.tank;

import kr.starly.core.nms.wrapper.NBTTagCompoundWrapper;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class NmsNbtTagCompoundUtil {

    private final Class<?> NBTTagCompoundClass;
    private final Method getStringMethod;
    private final Method setStringMethod;

    public NmsNbtTagCompoundUtil() {
        NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

        NBTTagCompoundClass = nmsOtherUtil.NBTTagCompound();
        getStringMethod = nmsOtherUtil.NBTTagCompound_getString();
        setStringMethod = nmsOtherUtil.NBTTagCompound_putString();
    }

    public NBTTagCompoundWrapper newInstance() {
        try {
            return new NBTTagCompoundWrapper(NBTTagCompoundClass.newInstance(), this);
        } catch (Exception e) {
            return null;
        }
    }
}