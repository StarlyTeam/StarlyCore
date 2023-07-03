package kr.starly.core.util;

import com.google.gson.Gson;
import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.version.VersionController;
import kr.starly.core.nms.wrapper.ItemStackWrapper;
import kr.starly.core.nms.wrapper.ItemWrapper;
import org.apache.commons.io.IOUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemStackNameUtil {

    private static final Pattern pattern = Pattern.compile("&([0-9a-fk-or])");
    private static Map<String, String> languageMap = new HashMap<>();

    private ItemStackNameUtil() {}

    /**
     * 해당 함수를 호출하지마세요.
     */
    @Deprecated
    public static void initialize(JavaPlugin plugin) {
        String versionName = VersionController.getInstance().getVersion().name();
        String numericPart = versionName.substring(3, 5);
        int versionNumber = Integer.parseInt(numericPart);

        languageMap = new HashMap<>();
        String resourceFileName = "lang/ko_kr_" + versionNumber + (versionNumber == 12 ? ".lang" : ".json");

        try (InputStream inputStream = plugin.getResource(resourceFileName)) {
            if (versionNumber == 12) {
                for (String line : IOUtils.readLines(inputStream, StandardCharsets.UTF_8)) {
                    if (!line.isEmpty() && line.charAt(0) != '#') {
                        String[] parts = line.split("=", 2);
                        if (parts.length == 2) {
                            String key = parts[0];
                            String value = pattern.matcher(parts[1]).replaceAll("%%$1s");
                            languageMap.put(key, value);
                        }
                    }
                }
            } else {
                Gson gson = new Gson();
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                languageMap = gson.fromJson(reader, HashMap.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ItemStack의 한국어 이름을 가져옵니다.
     *
     * @param itemStack 아이템
     * @return 한국어 이름
     */
    public static String getKoreanItemName(ItemStack itemStack) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName())
            return itemStack.getItemMeta().getDisplayName();

        try {
            String unlocalizedName = getUnlocalizedNameFromItem(itemStack);

            if (languageMap.containsKey(unlocalizedName)) {
                return languageMap.get(unlocalizedName);
            } else {
                unlocalizedName += ".name";
                if (languageMap.containsKey(unlocalizedName)) {
                    return languageMap.get(unlocalizedName);
                }
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return itemStack.getType().name().toLowerCase().replace("_", " ");
    }

    private static String getUnlocalizedNameFromItem(ItemStack itemStack) {
        NmsItemStackUtil nmsItem = NmsItemStackUtil.getInstance();
        ItemStackWrapper nmsItemStack = nmsItem.asNMSCopy(itemStack);
        ItemWrapper item = nmsItemStack.getItem();
        return item.getUnlocalizedName(nmsItemStack);
    }
}
