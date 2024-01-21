package kr.starly.core.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import kr.starly.core.exception.UnsupportedBukkitVersionException;
import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PlayerSkullUtil {

    private static final Version version = VersionController.getInstance().getVersion();
    private static final Map<UUID, String> skinTagMap = new HashMap<>();
    private static boolean highVersion;
    private static Server server;

    private static final Map<String, Boolean> highVersionMap = new HashMap<>();

    static {
        highVersionMap.put("v1_12_R1", false);
        highVersionMap.put("v1_13_R1", false);
        highVersionMap.put("v1_13_R2", false);
        highVersionMap.put("v1_14_R1", false);
        highVersionMap.put("v1_15_R1", false);
        highVersionMap.put("v1_16_R1", false);
        highVersionMap.put("v1_16_R2", false);
        highVersionMap.put("v1_16_R3", false);
        highVersionMap.put("v1_17_R1", false);
        highVersionMap.put("v1_18_R1", false);
        highVersionMap.put("v1_18_R2", false);
        highVersionMap.put("v1_19_R1", false);
        highVersionMap.put("v1_19_R2", false);
        highVersionMap.put("v1_19_R3", false);
        highVersionMap.put("v1_20_R1", true);
        highVersionMap.put("v1_20_R2", true);
        highVersionMap.put("v1_20_R3", true);
    }

    private PlayerSkullUtil() {}

    @Deprecated
    public static void initialize(Version version, Server server) {
        highVersion = version.isHighVersion();
        PlayerSkullUtil.server = server;
    }

    private static String getSkinTag(UUID uniqueId) {
        if (skinTagMap.containsKey(uniqueId))
            return skinTagMap.get(uniqueId);
        return null;
    }

    public static ItemStack getCustomSkull(String tempTag) {
        if (isHighVersion()) {
            return getCustomSkullUsingPlayerProfile(tempTag);
        } else {
           return getCustomSkullUsingGameProfile(tempTag);
        }
    }

    private static ItemStack getCustomSkullUsingPlayerProfile(String tempTag) {
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        PlayerTextures textures = profile.getTextures();
        try {
            URL url = new URL("https://textures.minecraft.net/texture/" + tempTag);
            textures.setSkin(url);
            profile.setTextures(textures);
            skullMeta.setOwnerProfile(profile);
            head.setItemMeta(skullMeta);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return head;
    }

    private static ItemStack getCustomSkullUsingGameProfile(String tempTag) {
        ItemStack baseItem;
        try {
            baseItem = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } catch (IllegalArgumentException ignored) {
            baseItem = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (byte) 3);
        } catch (Exception ignored) {
            return new ItemStack(Material.STONE);
        }

        String url = "https://textures.minecraft.net/texture/" + tempTag;
        ItemMeta headMeta = baseItem.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        byte[] byteArray = String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes();
        byte[] encodedData;
        try {
            encodedData = launchBase64Method(byteArray);
        } catch (Exception e) {
            return new ItemStack(Material.STONE);
        }
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
            baseItem.setItemMeta(headMeta);
            return baseItem;
        } catch (Exception e) { return new ItemStack(Material.STONE); }
    }

    public static ItemStack getPlayerSkull(UUID targetUniqueId) {
        String skinTag;
        if (skinTagMap.containsKey(targetUniqueId)) skinTag = skinTagMap.get(targetUniqueId);
        else {
            try {
                String contents = getURLContents("https://sessionserver.mojang.com/session/minecraft/profile/" + targetUniqueId);
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(contents, JsonObject.class);
                String value = jsonObject.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
                String decoded = new String(Base64.getDecoder().decode(value));
                JsonObject newObject = gson.fromJson(decoded, JsonObject.class);
                String skinUrl = newObject.getAsJsonObject("textures").getAsJsonObject("SKIN").get("url").getAsString();
                byte[] skin = ("{\"textures\":{\"SKIN\":{\"url\":\"" + skinUrl + "\"}}}").getBytes();
                byte[] encoded = Base64.getEncoder().encode(skin);
                long hash = Arrays.hashCode(encoded);
                UUID hashAsId = new UUID(hash, hash);
                skinTag = "{SkullOwner:{Id:\"" + hashAsId + "\", Properties:{textures:[{Value:\"" + value + "\"}]}}}";
                skinTagMap.put(targetUniqueId, skinTag);
            } catch (Exception e) { return new ItemStack(Material.STONE); }
        }
        ItemStack baseItem;

        try {
            if (!highVersion) baseItem = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (byte) 3);
            else baseItem = new ItemStack(Material.valueOf("PLAYER_HEAD"), 1);
        } catch (Exception ignore) {
            return new ItemStack(Material.STONE);
        }
        return server.getUnsafe().modifyItemStack(baseItem, skinTag);
    }

    private static String getURLContents(String stringUrl) throws UnsupportedBukkitVersionException {
        try {
            URL url = new URL(stringUrl);
            StringBuilder builder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                br.lines().forEach(builder::append);
                return builder.toString();
            } catch (Exception e) { throw new UnsupportedBukkitVersionException(server.getVersion()); }
        } catch (Exception e) { throw new UnsupportedBukkitVersionException(server.getVersion()); }
    }

    private static byte[] launchBase64Method(byte[] byteArray) throws UnsupportedBukkitVersionException {
        try {
            return Base64.getEncoder().encode(byteArray);
        } catch (Exception ignored) {
            try {
                Method method = Class.forName("org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64").getMethod("encodeBase64", byte[].class);
                return (byte[]) method.invoke(null, byteArray);
            } catch (Exception ignored1) { throw new UnsupportedBukkitVersionException(server.getVersion()); }
        }
    }

    private static boolean isHighVersion() {
        return highVersionMap.get(version.getVersion());
    }
}