package kr.starly.core;

import kr.starly.core.nms.version.VersionController;
import kr.starly.core.util.ItemStackNameUtil;
import kr.starly.core.util.PlayerSkullUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class StarlyCore extends JavaPlugin {

    private static StarlyCore instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        VersionController.initialize(this);
        PlayerSkullUtil.initialize(VersionController.getInstance().getVersion(), getServer());
        ItemStackNameUtil.initialize(this);
    }
}