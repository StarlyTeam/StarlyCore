package kr.starly.core.nms.sign.impl;

import kr.starly.core.nms.sign.SignEditor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public class SignEditor_v1_12_R1 implements SignEditor {

    @Override
    public Map<Location, Material> openNewSign(Player player, String... lines) {
        return null;
    }
}
