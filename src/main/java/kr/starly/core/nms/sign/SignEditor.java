package kr.starly.core.nms.sign;

import org.bukkit.entity.Player;

public interface SignEditor {

    void open(Player player, String... lines);
}
