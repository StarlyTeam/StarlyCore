package kr.starly.core.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;

public class ItemSerializeUtil {

    private ItemSerializeUtil() {}

    public static Optional<String> encode(ItemStack itemStack) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {

            dataOutput.writeObject(itemStack);
            return Optional.of(Base64.getEncoder().encodeToString(outputStream.toByteArray()));
        } catch (Exception e) {
            Bukkit.getLogger().severe("An error occurred in ItemSerializeUtil: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<ItemStack> decode(String serializedItemStack) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(serializedItemStack));
             BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {

            return Optional.of((ItemStack) dataInput.readObject());
        } catch (Exception e) {
            Bukkit.getLogger().severe("An error occurred in ItemSerializeUtil: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
