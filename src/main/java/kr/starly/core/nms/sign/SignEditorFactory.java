package kr.starly.core.nms.sign;

import kr.starly.core.nms.sign.impl.SignEditor_v1_12_R1;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class SignEditorFactory {

    private static final Map<Version, SignEditor> signEditors = new HashMap<>();
    private static final Version serverVersion;

    static {
        signEditors.put(Version.v1_12_R1, new SignEditor_v1_12_R1());

        serverVersion = VersionController.getInstance().getVersion();
    }

    public static SignEditor getSignEditor() {
        return signEditors.get(serverVersion);
    }

    public static SignEditor createSign() {
        return getSignEditor();
    }
}
