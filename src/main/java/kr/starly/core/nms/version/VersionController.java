package kr.starly.core.nms.version;

import kr.starly.core.exception.UnsupportedBukkitVersionException;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.stream.Stream;

public class VersionController {

    private static VersionController instance;
    private static JavaPlugin plugin;

    public static void initialize(JavaPlugin inputPlugin) {
        if (plugin != null) {
            throw new IllegalStateException("VersionController has already been initialized.");
        }
        VersionController.plugin = inputPlugin;
    }

    public static VersionController getInstance() {
        if (plugin == null) {
            throw new IllegalStateException("VersionController has not been initialized. Call initialize() first.");
        }
        if (instance == null) {
            try {
                instance = new VersionController(plugin.getServer());
            } catch (UnsupportedBukkitVersionException exception) {
                exception.printStackTrace();
            }
        }
        return instance;
    }

    @Getter
    private final Version version;

    private VersionController(Server server) throws UnsupportedBukkitVersionException {
        version = checkVersions(server);
    }

    private Version checkVersions(Server server) throws UnsupportedBukkitVersionException {
        Optional<Version> matchVersion = Stream.of(Version.values()).filter(it -> it.matches(server.getBukkitVersion())).findFirst();
        return matchVersion.orElseThrow(() -> new UnsupportedBukkitVersionException(server.getVersion()));
    }
}