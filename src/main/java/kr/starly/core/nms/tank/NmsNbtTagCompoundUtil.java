package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import kr.starly.core.nms.wrapper.NBTTagCompoundWrapper;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Getter
public class NmsNbtTagCompoundUtil {

    private Class<?> NBTTagCompoundClass;
    private Method getStringMethod;
    private Method setStringMethod;

    NmsNbtTagCompoundUtil(String nbtTagCompoundClassName) throws ClassNotFoundException, NoSuchMethodException {
        try {
            NBTTagCompoundClass = Class.forName(nbtTagCompoundClassName);
        } catch (Exception ignored) {
            NBTTagCompoundClass = Class.forName("net.minecraft.nbt.NBTTagCompound");
        }

        Version version = VersionController.getInstance().getVersion();
        try {
            getStringMethod = NBTTagCompoundClass.getDeclaredMethod("getString", String.class);
        } catch (Exception e) {
            Map<String, String> methodNameMap = new HashMap<>();
            methodNameMap.put("v1_16_R1", "l");
            methodNameMap.put("v1_16_R2", "l");
            methodNameMap.put("v1_16_R3", "l");
            methodNameMap.put("v1_17_R1", "l");
            methodNameMap.put("v1_18_R1", "l");
            methodNameMap.put("v1_18_R2", "l");
            methodNameMap.put("v1_19_R1", "l");
            methodNameMap.put("v1_19_R2", "l");
            methodNameMap.put("v1_19_R3", "l");
            methodNameMap.put("v1_20_R1", "l");

            getStringMethod = NBTTagCompoundClass.getDeclaredMethod(methodNameMap.get(version.name()), String.class);
        }
        try {
            setStringMethod = NBTTagCompoundClass.getDeclaredMethod("setString", String.class, String.class);
        } catch (Exception e) {
            Map<String, String> methodNameMap = new HashMap<>();
            methodNameMap.put("v1_16_R1", "a");
            methodNameMap.put("v1_16_R2", "a");
            methodNameMap.put("v1_16_R3", "a");
            methodNameMap.put("v1_17_R1", "a");
            methodNameMap.put("v1_18_R1", "a");
            methodNameMap.put("v1_18_R2", "a");
            methodNameMap.put("v1_19_R1", "a");
            methodNameMap.put("v1_19_R2", "a");
            methodNameMap.put("v1_19_R3", "a");
            methodNameMap.put("v1_20_R1", "a");

            setStringMethod = NBTTagCompoundClass.getDeclaredMethod(methodNameMap.get(version.name()), String.class, String.class);
        }
    }

    /**
     * NMS 의 NBTTagCompound 를 생성해줍니다.
     *
     * @return NBTTagCompoundWrapper
     */
    public NBTTagCompoundWrapper newInstance() {
        try {
            return new NBTTagCompoundWrapper(NBTTagCompoundClass.newInstance(), this);
        } catch (Exception e) {
            return null;
        }
    }
}
