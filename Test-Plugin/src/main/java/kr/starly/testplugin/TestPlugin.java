package kr.starly.testplugin;

import kr.starly.testplugin.command.TestCommand;
import kr.starly.testplugin.listener.PlayerInteractListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {

    @Getter
    private static TestPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getCommand("test").setExecutor(new TestCommand());
    }
}
