package kr.starly.core.util;

import com.google.gson.Gson;
import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.version.VersionController;
import kr.starly.core.nms.wrapper.ItemStackWrapper;
import kr.starly.core.nms.wrapper.ItemWrapper;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemStackNameUtil {

    private static final Map<Language, Map<String, String>> languages = new HashMap<>();

    private ItemStackNameUtil() {}

    @Deprecated
    public static void initialize(JavaPlugin plugin) {
        String versionName = VersionController.getInstance().getVersion().getVersion();
        String numericPart = versionName.substring(1, 5);

        for (Language language : Language.values()) {
            Map<String, String> languageMap = new HashMap<>();
            String resourceFileName = "lang/" + numericPart + "/" + language.getName() + (numericPart.equals("1_12") ? ".lang" : ".json");

            try (InputStream inputStream = plugin.getResource(resourceFileName)) {
                if (numericPart.equals("1_12")) {
                    languageMap.putAll(loadFromLangFormat(inputStream));
                } else {
                    Gson gson = new Gson();
                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    languageMap = gson.fromJson(reader, HashMap.class);
                }
                languages.put(language, languageMap);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to load language file: " + resourceFileName, ex);
            }
        }
    }

    private static Map<String, String> loadFromLangFormat(InputStream inputStream) {
        Map<String, String> resultMap = new HashMap<>();
        for (String line : new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.toList())) {
            if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                resultMap.put(parts[0], parts[1]);
            }
        }
        return resultMap;
    }

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
        } catch (Exception e) {
            Bukkit.getLogger().warning("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return itemStack.getType().name().toLowerCase().replace("_", " ");
    }

    private static String getUnlocalizedNameFromItem(ItemStack itemStack) {
        NmsItemStackUtil nmsItem = NmsItemStackUtil.getInstance();
        ItemStackWrapper nmsItemStack = nmsItem.asWrapperCopy(itemStack);
        ItemWrapper item = nmsItemStack.getItem();
        return item.getUnlocalizedName(nmsItemStack);
    }
}