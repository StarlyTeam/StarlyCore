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
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemStackNameUtil {

    private static final Pattern pattern = Pattern.compile("&([0-9a-fk-or])");
    private static Map<String, String> languageMap = new HashMap<>();
    private static Map<Language, Map<String, String>> languages = new HashMap<>();

    private ItemStackNameUtil() {}

    /**
     * 해당 함수를 호출하지마세요.
     */
    @Deprecated
    public static void initialize(JavaPlugin plugin) {
        String versionName = VersionController.getInstance().getVersion().getVersion();
        String numericPart = versionName.substring(1, 5);

        for (Language language : Language.values()) {
            languageMap = new HashMap<>();
            String resourceFileName = "lang/" + numericPart + "/" + language.getName() + (numericPart.equals("1_12") ? ".lang" : ".json");

            try (InputStream inputStream = plugin.getResource(resourceFileName)) {
                if (numericPart.equals("1_12")) {
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
                languages.put(language, languageMap);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 아이템과 언어를 인자로 받아 해당 아이템의 지정된 언어의 이름을 반환하는 메소드입니다.
     *
     * @param itemStack 언어에 따른 이름을 얻고자 하는 아이템
     * @param language  아이템 이름을 가져올 언어
     * @return 아이템의 지정된 언어의 이름. 만약 아이템에 표시 이름이 지정되어 있다면 그것을 반환합니다.
     */

    public static String getNameInLanguage(ItemStack itemStack, Language language) {
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName())
            return itemStack.getItemMeta().getDisplayName();
        try {
            String unlocalizedName = getUnlocalizedNameFromItem(itemStack);

            Map<String, String> languageMap = languages.get(language);
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
