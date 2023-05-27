package kr.starly.core.nms.sign;

import kr.starly.core.nms.sign.impl.SignEditor_v1_12_R1;
import kr.starly.core.nms.version.Version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SignEditorFactory {

    private static final Map<Version, SignEditor> signEditors = new HashMap<>();

    static {
        signEditors.put(Version.v1_12_R1, new SignEditor_v1_12_R1());
    }

    public static Map<Location, Material> openNewSign(Player player, String... lines) {
        return null;
    }
}
