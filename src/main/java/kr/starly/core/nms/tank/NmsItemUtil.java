package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Getter
public class NmsItemUtil {

    private final Class<?> ItemClass;
    private final Class<?> ItemStackClass;
    private Method getDescriptionIdMethod;
    private Method useMethod;

    NmsItemUtil(String className, Class<?> itemStackClass) throws ClassNotFoundException, NoSuchMethodException {
        Version version = VersionController.getInstance().getVersion();

        ItemClass = Class.forName(className);
        ItemStackClass = itemStackClass;
        try {
            getDescriptionIdMethod = ItemClass.getMethod("getDescriptionId", itemStackClass);
        } catch (NoSuchMethodException ignored) {
            Map<String, String> methodNameMap = new HashMap<>();
            methodNameMap.put("v1_12_R1", "j");
            methodNameMap.put("v1_13_R2", "h");
            methodNameMap.put("v1_14_R1", "f");
            methodNameMap.put("v1_15_R1", "f");
            methodNameMap.put("v1_16_R1", "f");
            methodNameMap.put("v1_16_R2", "f");
            methodNameMap.put("v1_16_R3", "f");
            methodNameMap.put("v1_17_R1", "j");
            methodNameMap.put("v1_18_R1", "j");
            methodNameMap.put("v1_18_R2", "j");
            methodNameMap.put("v1_19_R1", "j");
            methodNameMap.put("v1_19_R2", "j");
            methodNameMap.put("v1_19_R3", "j");
            methodNameMap.put("v1_20_R1", "j");

            getDescriptionIdMethod = ItemClass.getMethod(methodNameMap.get(version.name()), itemStackClass);
        }
        try {
            useMethod = ItemClass.getMethod("use", NmsOtherUtil.getInstance().getWorldClass(), NmsOtherUtil.getInstance().getEntityHumanClass(), NmsOtherUtil.getInstance().getEnumHandClass());
        } catch (NoSuchMethodException ignored) {
            Map<String, String> methodNameMap = new HashMap<>();
            methodNameMap.put("v1_12_R1", "a");
            methodNameMap.put("v1_13_R1", "a");
            methodNameMap.put("v1_13_R2", "a");
            methodNameMap.put("v1_14_R1", "a");
            methodNameMap.put("v1_15_R1", "a");
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

            useMethod = ItemClass.getMethod(methodNameMap.get(version.name()), NmsOtherUtil.getInstance().getWorldClass(), NmsOtherUtil.getInstance().getEntityHumanClass(), NmsOtherUtil.getInstance().getEnumHandClass());
        }
    }
}
