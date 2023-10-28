package kr.starly.core.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class UpdateChecker {

    @NotNull private final String  plugin;

    public String getVersion(@Nullable Consumer<String> callback) {
        try {
            URL url = new URL("https://starly.kr/api/v1/plugins/" + plugin);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "StarlyCore");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();
            br.close();

            JsonElement dataObject = jsonObject.get("data");
            if (dataObject == null) return jsonObject.get("message").getAsString();

            String version = dataObject.getAsJsonObject().get("version").getAsString();
            if (callback != null) {
                callback.accept(version);
            }

            return version;
        } catch (IOException ex) {
            ex.printStackTrace();

            return "";
        }
    }
}