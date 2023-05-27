package kr.starly.core.nms.version;

import kr.starly.core.exception.UnSupportedVersionException;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.stream.Stream;

public class VersionController {

    private static VersionController instance;
    private static JavaPlugin plugin;

    public static void initialize(JavaPlugin plugin) {
        VersionController.plugin = plugin;
    }

    public static VersionController getInstance() {
        if (instance == null) {
            try {
                instance = new VersionController(plugin.getServer());
            } catch (UnSupportedVersionException e) { e.printStackTrace(); }
        }
        return instance;
    }

    @Getter private final Version version;

    private VersionController(Server server) throws UnSupportedVersionException {
        version = checkVersions(server);
    }

    private Version checkVersions(Server server) throws UnSupportedVersionException {
        Optional<Version> versionFilter = Stream.of(Version.values()).filter(it -> it.matches(server.getBukkitVersion())).findFirst();
        if (versionFilter.isPresent()) return versionFilter.get();
        else throw new UnSupportedVersionException(server.getVersion());
    }
}
