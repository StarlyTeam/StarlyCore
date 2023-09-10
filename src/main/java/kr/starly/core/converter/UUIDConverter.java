package kr.starly.core.converter;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public class UUIDConverter {

    public static String getMinecraftUUID(String minecraftName) {
        String uuid = "";
        try (
                InputStream urlStream = new URL("https://api.mojang.com/users/profiles/minecraft/" + minecraftName).openStream();
                Scanner scanner = new Scanner(urlStream);
        ) {
            String response = scanner.useDelimiter("\\A").next();
            uuid = response.substring(response.indexOf("id") + 7, response.indexOf("id") + 39);
            uuid = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public static boolean hasMinecraftAccount(String username) {
        try (
                InputStream urlStream = new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openStream();
                Scanner scanner = new Scanner(urlStream);
        ) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getMinecraftName(UUID uuid) {
        String uuidWithoutDashes = uuid.toString().replace("-", "");
        String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuidWithoutDashes;
        try {
            URL api = new URL(url);
            Scanner scanner = new Scanner(api.openStream());
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}