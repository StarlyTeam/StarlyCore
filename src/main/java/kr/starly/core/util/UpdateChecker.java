package kr.starly.core.util;

import lombok.AllArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

@AllArgsConstructor
public class UpdateChecker {

    private final JavaPlugin plugin;
    private final int resourceId; // TODO 스탈리 플러그인 사이트 Resource Id | 제작 예정

    public void getVersion(Consumer<String> consumer) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            // TODO 스탈리 플러그인 사이트 URL로 구현
            try (InputStream inputStream = new URL("스탈리 플러그인 사이트 주소" + resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }
}
