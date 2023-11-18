package kr.starly.core.nms.wrapper;

import kr.starly.core.nms.tank.NmsItemStackUtil;
import kr.starly.core.nms.tank.NmsOtherUtil;
import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import kr.starly.core.util.FeatherLocation;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@SuppressWarnings("unused")
public class ArmorStandWrapper {

    private final NmsOtherUtil nmsOtherUtil = NmsOtherUtil.getInstance();

    @Getter private Integer id;
    @Getter private FeatherLocation location;
    @Getter private Object entityArmorStand;
    private Object defaultHeadPose;

    public ArmorStandWrapper(Integer id, FeatherLocation location, Object entityArmorStand) {
        this.id = id;
        this.location = location;
        this.entityArmorStand = entityArmorStand;

        try {
            defaultHeadPose = nmsOtherUtil.EntityArmorStand_getHeadPose().invoke(entityArmorStand);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /* MetaData
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    @Getter private String displayName;
    public void setDisplayName(String displayName) {
        this.displayName = displayName;

        try {
            nmsOtherUtil.Entity_setCustomName().invoke(entityArmorStand, nmsOtherUtil.toVersionString(displayName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean small;
    public void setSmall(boolean small) {
        this.small = small;

        try {
            nmsOtherUtil.EntityArmorStand_setSmall().invoke(entityArmorStand, small);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean invisible;
    public void setInvisible(boolean invisible) {
        this.invisible = invisible;

        try {
            nmsOtherUtil.Entity_setInvisible().invoke(entityArmorStand, invisible);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean customNameVisible;
    public void setCustomNameVisible(boolean customNameVisible) {
        this.customNameVisible = customNameVisible;

        try {
            nmsOtherUtil.Entity_setCustomNameVisible().invoke(entityArmorStand, customNameVisible);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean marker;
    public void setMarker(boolean marker) {
        this.marker = marker;

        try {
            nmsOtherUtil.EntityArmorStand_setMarker().invoke(entityArmorStand, marker);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean showArms;
    public void setShowArms(boolean showArms) {
        this.showArms = showArms;
        
        try {
            nmsOtherUtil.EntityArmorStand_setShowArms().invoke(entityArmorStand, showArms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Getter private boolean showBasePlate;
    public void setHideBasePlate(boolean hideBasePlate) {
        this.showBasePlate = hideBasePlate;
        
        try {
            nmsOtherUtil.EntityArmorStand_setNoBasePlate().invoke(entityArmorStand, hideBasePlate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /* HeadPose
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public Vector3fWrapper getHeadPose() {
        try {
            return new Vector3fWrapper(nmsOtherUtil.EntityArmorStand_headPose().get(entityArmorStand));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setHeadPose(Vector3fWrapper headPose) {
        try {
            nmsOtherUtil.EntityArmorStand_setHeadPose().invoke(entityArmorStand, headPose.getAsVector3f());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void resetHeadPose() {
        setHeadPose(new Vector3fWrapper(defaultHeadPose));
    }

    float headYaw;
    public void setHeadYaw(float yaw) {
        headYaw = yaw;
        try {
            nmsOtherUtil.EntityArmorStand_setHeadYaw().invoke(entityArmorStand, yaw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /* Arms
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public void setLeftArmsPose(Vector3fWrapper headPose) {
        try {
            nmsOtherUtil.EntityArmorStand_setLeftArmPose().invoke(entityArmorStand, headPose.getAsVector3f());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setRightArmPose(Vector3fWrapper headPose) {
        try {
            nmsOtherUtil.EntityArmorStand_setRightArmPose().invoke(entityArmorStand, headPose.getAsVector3f());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector3fWrapper getLeftArmPose() {
        try {
            return new Vector3fWrapper(nmsOtherUtil.EntityArmorStand_leftArmPose().get(entityArmorStand));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Vector3fWrapper getRightArmPose() {
        try {
            return new Vector3fWrapper(nmsOtherUtil.EntityArmorStand_rightArmPose().get(entityArmorStand));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /* Legs
     ─────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public void setLeftLegPose(Vector3fWrapper headPose) {
        try {
            nmsOtherUtil.EntityArmorStand_setLeftLegPose().invoke(entityArmorStand, headPose.getAsVector3f());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setRightLegPose(Vector3fWrapper headPose) {
        try {
            nmsOtherUtil.EntityArmorStand_setRightLegPose().invoke(entityArmorStand, headPose.getAsVector3f());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector3fWrapper getLeftLegPose() {
        try {
            return new Vector3fWrapper(nmsOtherUtil.EntityArmorStand_leftLegPose().get(entityArmorStand));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Vector3fWrapper getRightLegPose() {
        try {
            return new Vector3fWrapper(nmsOtherUtil.EntityArmorStand_rightLegPose().get(entityArmorStand));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /* Equipment
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    @Setter private @Nullable ItemStack itemInMainHand;
    @Setter private @Nullable ItemStack itemInOffHand;
    @Setter private @Nullable ItemStack helmet;
    @Setter private @Nullable ItemStack chestplate;
    @Setter private @Nullable ItemStack leggings;
    @Setter private @Nullable ItemStack boots;

    public void applyItemInMainHand(Player target) {
        if (itemInMainHand == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_MAINHAND();
            Object[] args = createEquipmentArgs(enumItemSlot, itemInMainHand);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyItemInOffHand(Player target) {
        if (itemInOffHand == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_OFFHAND();
            Object[] args = createEquipmentArgs(enumItemSlot, itemInOffHand);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyHelmet(Player target) {
        if (helmet == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_HEAD();
            Object[] args = createEquipmentArgs(enumItemSlot, helmet);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyChestplate(Player target) {
        if (chestplate == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_CHEST();
            Object[] args = createEquipmentArgs(enumItemSlot, chestplate);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyLeggings(Player target) {
        if (leggings == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_LEGS();
            Object[] args = createEquipmentArgs(enumItemSlot, leggings);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyBoots(Player target) {
        if (boots == null) return;
        try {
            Object enumItemSlot = nmsOtherUtil.EnumItemSlot_FEET();
            Object[] args = createEquipmentArgs(enumItemSlot, boots);

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityEquipment_Constructor(), args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /* Others
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public void spawn(Player target) {
        spawn(target, location.toLocation());
    }

    public void spawn(Player target, Location location) {
        try {
            nmsOtherUtil.Entity_setLocation().invoke(entityArmorStand, location.getX(), location.getY(), location.getZ());
            nmsOtherUtil.Entity_setYawPitch().invoke(entityArmorStand, location.getYaw(), location.getPitch());

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutSpawnEntity_Constructor(), entityArmorStand);
            applyMeta(target);
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
            nmsOtherUtil.Entity_setLocation().invoke(entityArmorStand, location.getX(), location.getY(), location.getZ());
            nmsOtherUtil.Entity_setYawPitch().invoke(entityArmorStand, location.getYaw(), location.getPitch());

            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityTeleport_Constructor(), entityArmorStand);
            if (savePose) defaultHeadPose = nmsOtherUtil.EntityArmorStand_getHeadPose().invoke(entityArmorStand);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void applyMeta(Player target) {
        applyItemInMainHand(target);
        applyItemInOffHand(target);
        applyHelmet(target);
        applyChestplate(target);
        applyLeggings(target);
        applyBoots(target);

        sendMetaDataPacket(target);
    }


    /* Util
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    private Object[] createEquipmentArgs(Object enumItemSlot, ItemStack itemStack) {
        try {
            Version version = VersionController.getInstance().getVersion();
            return version.isHighVersion() ?
                    new Object[]{id, Arrays.asList(nmsOtherUtil.Pair().newInstance(enumItemSlot, NmsItemStackUtil.getInstance().asNMSCopy(itemStack).getNmsItemStack()))} :
                    new Object[]{id, enumItemSlot, NmsItemStackUtil.getInstance().asNMSCopy(itemStack).getNmsItemStack()};
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void sendMetaDataPacket(Player target) {
        try {
            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityMetadata_Constructor(),
                    id,
                    nmsOtherUtil.Entity_getDataWatcher().invoke(entityArmorStand),
                    true
            );
        } catch (Exception ignored) {
            try {
                nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityMetadata_Constructor(),
                        id,
                        nmsOtherUtil.DataWatcher_getNonDefaultValues().invoke(nmsOtherUtil.Entity_getDataWatcher().invoke(entityArmorStand))
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendHeadRotationPacket(Player target) {
        try {
            nmsOtherUtil.sendPacket(target, nmsOtherUtil.PacketPlayOutEntityHeadRotation_Constructor(),
                    entityArmorStand, (byte) headYaw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}