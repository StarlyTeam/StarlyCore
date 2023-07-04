package kr.starly.core.util;

import kr.starly.core.nms.wrapper.WorldWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FeatherLocation implements Serializable {

    private final WorldWrapper world;
    private final double x, y, z;
    private final float yaw, pitch;

    public FeatherLocation(WorldWrapper world, double x, double y, double z) {
        this(world, x, y, z, 0.0f, 0.0f);
    }

    public Location toLocation() {
        return new Location(world.getBukkitWorld(), x, y, z, yaw, pitch);
    }
}