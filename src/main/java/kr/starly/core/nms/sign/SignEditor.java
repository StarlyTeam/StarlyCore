package kr.starly.core.nms.sign;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public interface SignEditor {

    Map<Location, Material> openNewSign(Player player, String... lines);
}
