package kr.starly.core.nms.sign;

import kr.starly.core.nms.sign.impl.SignEditor_v1_12_R1;

import kr.starly.core.nms.version.Version;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class SignEditorFactory {

    private static final Map<Version, SignEditor> signEditors = new HashMap<>();
    private static Version serverVersion;

    static {
        signEditors.put(Version.v1_12_R1, new SignEditor_v1_12_R1());

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
        try {
            serverVersion = Version.valueOf(version);
        } catch (IllegalArgumentException e) {
            serverVersion = null;
        }
    }

    public static SignEditor getSignEditor() {
        return signEditors.get(serverVersion);
    }

    public static SignEditor createSign() {
        return getSignEditor();
    }
}
