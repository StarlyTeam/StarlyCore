package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsItemUtil;
import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ItemWrapper {

    private NmsItemUtil support;
    @Getter private Object Item;

    public ItemWrapper(NmsItemUtil itemSupport, ItemStackWrapper nmsItemStackWrapper) {
        try {
            support = itemSupport;
            Method getItemMethod;

            Version version = VersionController.getInstance().getVersion();
            try {
                getItemMethod = itemSupport.getItemStackClass().getMethod("getItem");
            } catch (Exception e) {
                Map<String, String> methodNameMap = new HashMap<>();
                methodNameMap.put("v1_12_R1", "c");
                methodNameMap.put("v1_13_R1", "b");
                methodNameMap.put("v1_13_R2", "b");
                methodNameMap.put("v1_14_R1", "b");
                methodNameMap.put("v1_15_R1", "b");
                methodNameMap.put("v1_16_R2", "b");
                methodNameMap.put("v1_16_R3", "b");
                methodNameMap.put("v1_17_R1", "c");
                methodNameMap.put("v1_18_R1", "c");
                methodNameMap.put("v1_18_R2", "c");
                methodNameMap.put("v1_19_R1", "c");
                methodNameMap.put("v1_19_R2", "c");
                methodNameMap.put("v1_19_R3", "c");
                methodNameMap.put("v1_20_R1", "d");

                getItemMethod = itemSupport.getItemStackClass().getMethod(methodNameMap.get(version.name()));
            }
            Item = getItemMethod.invoke(nmsItemStackWrapper.getNmsItemStack());
        } catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * 아이템의 현지화되지 않은 이름을 가져옵니다.
     *
     * @param nmsItemStack ItemStackWrapper
     * @return 현지화되지 않은 이름
     */
    public String getUnlocalizedName(ItemStackWrapper nmsItemStack) {
        try {
            return (String) support.getGetDescriptionIdMethod().invoke(Item, nmsItemStack.getNmsItemStack());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
