package kr.starly.core.nms.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.World;

@AllArgsConstructor
@Getter
public class WorldWrapper {

    private final org.bukkit.World bukkitWorld;
    private final World world;
}
