package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.tank.NmsOtherUtil;
import kr.starly.core.util.FeatherLocation;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@SuppressWarnings("unused")
public class EntityItemWrapper {

    private final NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

    @Getter private Object entityItem;
    @Getter private Integer id;

    public EntityItemWrapper(Integer id, FeatherLocation location, ItemStack itemStack) {
        try {
            this.entityItem = nmsOtherUtil.EntityItem_Constructor()
                    .newInstance(
                            location.getWorld(),
                            location.getX(), location.getY(), location.getZ(),
                            NmsItemStackUtil.getInstance().asNMSCopy(itemStack).getNmsItemStack()
                    );

            this.id = id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* MetaData
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public void setNoGravity(boolean noGravity) {
        try {
            nmsOtherUtil.Entity_setNoGravity().invoke(entityItem, noGravity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    /* Others
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public void spawn(Player target) {
        spawn(target, target.getLocation());
    }

    public void spawn(Player target, Location location) {
        try {
            nmsOtherUtil.Entity_setLocation().invoke(entityItem, location.getX(), location.getY(), location.getZ());
            nmsOtherUtil.Entity_setYawPitch().invoke(entityItem, location.getYaw(), location.getPitch());

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutSpawnEntity_Constructor(), entityItem, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void remove(Player target) {
        try {
            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityDestroy_Constructor(), new int[]{id});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void teleport(Player target, Location location) {
        teleport(target, location, false);
    }

    public void teleport(Player target, Location location, boolean savePose) {
        try {
            FeatherLocation featherLocation = nmsOtherUtil.toFeatherLocation(location);
            nmsOtherUtil.Entity_setLocation().invoke(entityItem, location.getX(), location.getY(), location.getZ());
            nmsOtherUtil.Entity_setYawPitch().invoke(entityItem, location.getYaw(), location.getPitch());
            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityTeleport_Constructor(), entityItem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyMeta(Player target) {
        sendMetaDataPacket(target);
    }

    /* Util
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    private void sendMetaDataPacket(Player target) {
        try {
            nmsOtherUtil.sendPacket(
                    target,
                    nmsOtherUtil.PacketPlayOutEntityMetadata_Constructor(),
                    id,
                    nmsOtherUtil.Entity_getDataWatcher().invoke(entityItem),
                    true
            );
        } catch (Exception ignored) {
            try {
                nmsOtherUtil.sendPacket(
                        target,
                        nmsOtherUtil.PacketPlayOutEntityMetadata_Constructor(),
                        id,
                        nmsOtherUtil.DataWatcher_getNonDefaultValues().invoke(nmsOtherUtil.Entity_getDataWatcher().invoke(entityItem))
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}