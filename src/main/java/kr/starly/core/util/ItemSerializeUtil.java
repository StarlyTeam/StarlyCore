package kr.starly.core.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemSerializeUtil {

    private ItemSerializeUtil() {}

    /**
     * ItemStack을 직렬화하여 Base64로 인코딩합니다.
     *
     * @param itemStack 인코딩할 ItemStack 객체입니다.
     * @return Base64로 인코딩된 직렬화된 문자열이 반환됩니다. 인코딩에 실패한 경우에는 null이 반환됩니다.
     */
    public static String encode(ItemStack itemStack) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {

            dataOutput.writeObject(itemStack);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Base64로 인코딩된 직렬화된 ItemStack을 디코드합니다.
     *
     * @param serializedItemStack Base64 형식으로 직렬화된 ItemStack입니다.
     * @return 디코드된 ItemStack이 반환되며, 디코드에 실패한 경우에는 null이 반환됩니다.
     */
    public static ItemStack decode(String serializedItemStack) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(serializedItemStack));
             BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {

            return (ItemStack) dataInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
