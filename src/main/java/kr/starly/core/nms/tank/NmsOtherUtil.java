package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class NmsOtherUtil {

    private static NmsOtherUtil instance;

    public static NmsOtherUtil getInstance() {
        if (instance == null) instance = new NmsOtherUtil();
        return instance;
    }

    private NmsOtherUtil() {}

    private final Version version = VersionController.getInstance().getVersion();
    private final String nmsPackage = "net.minecraft.server." + version.name();


    /* PACKET
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * Packet
     */
    private Class<?> PacketClass;
    public Class<?> Packet() {
        if (PacketClass == null) {
            try {
                PacketClass = Class.forName(nmsPackage + ".Packet");
            } catch (ClassNotFoundException ignored) {
                try {
                    PacketClass = Class.forName("net.minecraft.network.protocol.Packet");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return PacketClass;
    }


    /* PLAYER
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * EntityHuman
     */
    private Class<?> EntityHumanClass;
    public Class<?> EntityHuman() {
        if (EntityHumanClass == null) {
            try {
                EntityHumanClass = Class.forName(nmsPackage + ".EntityHuman");
            } catch (ClassNotFoundException ignored) {
                try {
                    EntityHumanClass = Class.forName("net.minecraft.world.entity.player.EntityHuman");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EntityHumanClass;
    }

    /**
     * EntityPlayer
     */
    private Class<?> EntityPlayerClass;
    public Class<?> EntityPlayer() {
        if (EntityPlayerClass == null) {
            try {
                EntityPlayerClass = Class.forName(nmsPackage + ".EntityPlayer");
            } catch (ClassNotFoundException ignored) {
                try {
                    EntityPlayerClass = Class.forName("net.minecraft.server.level.EntityPlayer");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EntityPlayerClass;
    }

    /**
     * EntityPlayer#playerConnection
     */
    private Field playerConnectionAtEntityPlayer;
    public Field getEntityPlayer_playerConnection() {
        if (playerConnectionAtEntityPlayer == null) {
            try {
                playerConnectionAtEntityPlayer = EntityPlayer().getField("playerConnection");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "a");
                    fieldNameMap.put("v1_13_R1", "a");
                    fieldNameMap.put("v1_13_R2", "a");
                    fieldNameMap.put("v1_14_R1", "b");
                    fieldNameMap.put("v1_15_R1", "b");
                    fieldNameMap.put("v1_16_R1", "b");
                    fieldNameMap.put("v1_16_R2", "b");
                    fieldNameMap.put("v1_16_R3", "b");
                    fieldNameMap.put("v1_17_R1", "b");
                    fieldNameMap.put("v1_18_R1", "b");
                    fieldNameMap.put("v1_18_R2", "b");
                    fieldNameMap.put("v1_19_R1", "b");
                    fieldNameMap.put("v1_19_R2", "b");
                    fieldNameMap.put("v1_19_R3", "b");
                    fieldNameMap.put("v1_20_R1", "c");

                    playerConnectionAtEntityPlayer = EntityPlayer().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException e) { e.printStackTrace(); }
            }
        }
        return playerConnectionAtEntityPlayer;
    }

    /**
     * PlayerConnection
     */
    private Class<?> PlayerConnectionClass;
    public Class<?> PlayerConnection() {
        if (PlayerConnectionClass == null) {
            try {
                PlayerConnectionClass = Class.forName(nmsPackage + ".PlayerConnection");
            } catch (ClassNotFoundException ignored) {
                try {
                    PlayerConnectionClass = Class.forName("net.minecraft.server.network.PlayerConnection");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return PlayerConnectionClass;
    }

    /**
     * PlayerConnection#sendPacket(Packet)
     */
    private Method sendPacketAtPlayerConnection;
    public Method PlayerConnection_sendPacket() {
        if (sendPacketAtPlayerConnection == null) {
            try {
                sendPacketAtPlayerConnection = PlayerConnection().getMethod("sendPacket", Packet());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "a");
                    fieldNameMap.put("v1_13_R1", "a");
                    fieldNameMap.put("v1_13_R2", "a");
                    fieldNameMap.put("v1_14_R1", "a");
                    fieldNameMap.put("v1_15_R1", "a");
                    fieldNameMap.put("v1_16_R1", "a");
                    fieldNameMap.put("v1_16_R2", "a");
                    fieldNameMap.put("v1_16_R3", "a");
                    fieldNameMap.put("v1_17_R1", "a");
                    fieldNameMap.put("v1_18_R1", "a");
                    fieldNameMap.put("v1_18_R2", "a");
                    fieldNameMap.put("v1_19_R1", "a");
                    fieldNameMap.put("v1_19_R2", "a");
                    fieldNameMap.put("v1_19_R3", "a");
                    fieldNameMap.put("v1_20_R1", "a");

                    sendPacketAtPlayerConnection = PlayerConnection().getMethod(fieldNameMap.get(version.getVersion()), Packet());
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return sendPacketAtPlayerConnection;
    }

    /**
     * CraftPlayer
     */
    private Class<?> CraftPlayerClass;
    public Class<?> CraftPlayer() {
        if (CraftPlayerClass == null) {
            try {
                CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version.name() + ".entity.CraftPlayer");
            } catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
        return CraftPlayerClass;
    }

    /**
     * CraftPlayer#getHandle()
     */
    private Method getHandleAtCraftPlayer;
    public Method CraftPlayer_getHandle() {
        if (getHandleAtCraftPlayer == null) {
            try {
                getHandleAtCraftPlayer = CraftPlayer().getMethod("getHandle");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return getHandleAtCraftPlayer;
    }


    /* ENTITY
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * Entity
     */
    private Class<?> EntityClass;
    public Class<?> Entity() {
        if (EntityClass == null) {
            try {
                EntityClass = Class.forName(nmsPackage + ".Entity");
            } catch (ClassNotFoundException ignored) {
                try {
                    EntityClass = Class.forName("net.minecraft.world.entity.Entity");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EntityClass;
    }

    /**
     * Entity#getDataWatcher()
     */
    private Method getDataWatcherAtEntity;
    public Method Entity_getDataWatcher() {
        if (getDataWatcherAtEntity == null) {
            try {
                getDataWatcherAtEntity = Entity().getMethod("getDataWatcher");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "V");
                    methodNameMap.put("v1_13_R1", "T");
                    methodNameMap.put("v1_13_R2", "T");
                    methodNameMap.put("v1_14_R1", "W");
                    methodNameMap.put("v1_15_R1", "V");
                    methodNameMap.put("v1_16_R1", "Y");
                    methodNameMap.put("v1_16_R2", "aa");
                    methodNameMap.put("v1_16_R3", "ab");
                    methodNameMap.put("v1_17_R1", "ad");
                    methodNameMap.put("v1_18_R1", "ai");
                    methodNameMap.put("v1_18_R2", "ai");
                    methodNameMap.put("v1_19_R1", "ai");
                    methodNameMap.put("v1_19_R2", "al");
                    methodNameMap.put("v1_19_R3", "aj");
                    methodNameMap.put("v1_20_R1", "aj");

                    getDataWatcherAtEntity = Entity().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return getDataWatcherAtEntity;
    }

    /**
     * EntityLiving
     */
    private Class<?> EntityLivingClass;
    public Class<?> EntityLiving() {
        if (EntityLivingClass == null) {
            try {
                EntityLivingClass = Class.forName(nmsPackage + ".EntityLiving");
            } catch (ClassNotFoundException ignored) {
                try {
                    EntityLivingClass = Class.forName("net.minecraft.world.entity.EntityLiving");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EntityLivingClass;
    }

    /**
     * DataWatcher
     */
    private Class<?> DataWatcherClass;
    public Class<?> DataWatcher() {
        if (DataWatcherClass == null) {
            try {
                DataWatcherClass = Class.forName(nmsPackage + ".DataWatcher");
            } catch (ClassNotFoundException ignored) {
                try {
                    DataWatcherClass = Class.forName("net.minecraft.network.syncher.DataWatcher");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return DataWatcherClass;
    }

    /**
     * DataWatcher#getNonDefaultValues() (Support: 1.19+)
     */
    private Method getNonDefaultValuesAtDataWatcher;
    public Method DataWatcher_getNonDefaultValues() {
        if (getNonDefaultValuesAtDataWatcher == null) {
            try {
                getNonDefaultValuesAtDataWatcher = DataWatcher().getMethod("getNonDefaultValues");
            } catch (NoSuchMethodException ignored) {
                try {
                    getNonDefaultValuesAtDataWatcher = DataWatcher().getMethod("c");
                } catch (NoSuchMethodException ignored1) {}
            }
        }
        return getNonDefaultValuesAtDataWatcher;
    }


    /* ENTITY - ArmorStand
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * EntityArmorStand
     */
    private Class<?> EntityArmorStandClass;
    public Class<?> EntityArmorStand() {
        if (EntityArmorStandClass == null) {
            try {
                EntityArmorStandClass = Class.forName(nmsPackage + ".EntityArmorStand");
            } catch (ClassNotFoundException ignored) {
                try {
                    EntityArmorStandClass = Class.forName("net.minecraft.world.entity.decoration.EntityArmorStand");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EntityArmorStandClass;
    }

    /**
     * EntityArmorStand#setInvisible(Boolean)
     */
    private Method setInvisibleAtEntityArmorStand;
    public Method EntityArmorStand_setInvisible() {
        if (setInvisibleAtEntityArmorStand == null) {
            try {
                setInvisibleAtEntityArmorStand = EntityArmorStand().getMethod("setInvisible", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "j");
                    methodNameMap.put("v1_13_R1", "j");
                    methodNameMap.put("v1_13_R2", "j");
                    methodNameMap.put("v1_14_R1", "j");
                    methodNameMap.put("v1_15_R1", "j");
                    methodNameMap.put("v1_16_R1", "j");
                    methodNameMap.put("v1_16_R2", "j");
                    methodNameMap.put("v1_16_R3", "j");
                    methodNameMap.put("v1_17_R1", "j");
                    methodNameMap.put("v1_18_R1", "j");
                    methodNameMap.put("v1_18_R2", "j");
                    methodNameMap.put("v1_19_R1", "j");
                    methodNameMap.put("v1_19_R2", "j");
                    methodNameMap.put("v1_19_R3", "j");
                    methodNameMap.put("v1_20_R1", "j");

                    setInvisibleAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setInvisibleAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setCustomName(IChatBaseComponent)
     */
    private Method setCustomNameAtEntityArmorStand;
    public Method EntityArmorStand_setCustomName() {
        if (setCustomNameAtEntityArmorStand == null) {
            try {
                setCustomNameAtEntityArmorStand = EntityArmorStand().getMethod("setCustomName", IChatBaseComponentClass);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "b");
                    methodNameMap.put("v1_19_R2", "b");
                    methodNameMap.put("v1_19_R3", "b");
                    methodNameMap.put("v1_20_R1", "b");

                    setCustomNameAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), IChatBaseComponentClass);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setCustomNameAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setCustomNameVisible(Boolean)
     */
    private Method setCustomNameVisibleAtEntityArmorStand;
    public Method EntityArmorStand_setCustomNameVisible() {
        if (setCustomNameVisibleAtEntityArmorStand == null) {
            try {
                setCustomNameVisibleAtEntityArmorStand = EntityArmorStand().getMethod("setCustomNameVisible", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "n");
                    methodNameMap.put("v1_13_R1", "n");
                    methodNameMap.put("v1_13_R2", "n");
                    methodNameMap.put("v1_14_R1", "n");
                    methodNameMap.put("v1_15_R1", "n");
                    methodNameMap.put("v1_16_R1", "n");
                    methodNameMap.put("v1_16_R2", "n");
                    methodNameMap.put("v1_16_R3", "n");
                    methodNameMap.put("v1_17_R1", "n");
                    methodNameMap.put("v1_18_R1", "n");
                    methodNameMap.put("v1_18_R2", "n");
                    methodNameMap.put("v1_19_R1", "n");
                    methodNameMap.put("v1_19_R2", "n");
                    methodNameMap.put("v1_19_R3", "n");
                    methodNameMap.put("v1_20_R1", "n");

                    setCustomNameVisibleAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setCustomNameVisibleAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setSmall(Boolean)
     */
    private Method setSmallAtEntityArmorStand;
    public Method EntityArmorStand_setSmall() {
        if (setSmallAtEntityArmorStand == null) {
            try {
                setSmallAtEntityArmorStand = EntityArmorStand().getMethod("setSmall", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "t");
                    methodNameMap.put("v1_20_R1", "t");

                    setSmallAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setSmallAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setLocation(Double, Double, Double, Float, Float)
     */
    private Method setLocationAtEntityArmorStand;
    public Method EntityArmorStand_setLocation() {
        if (setLocationAtEntityArmorStand == null) {
            try {
                setLocationAtEntityArmorStand = EntityArmorStand().getMethod("setLocation", Double.class, Double.class, Double.class, Float.class, Float.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");

                    setLocationAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Double.class, Double.class, Double.class, Float.class, Float.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setLocationAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setShowArms(Boolean)
     */
    private Method setShowArmsAtEntityArmorStand;
    public Method EntityArmorStand_setShowArms() {
        if (setShowArmsAtEntityArmorStand == null) {
            try {
                setShowArmsAtEntityArmorStand = EntityArmorStand().getMethod("setShowArms", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "n");
                    methodNameMap.put("v1_13_R1", "q");
                    methodNameMap.put("v1_13_R2", "q");
                    methodNameMap.put("v1_14_R1", "o");
                    methodNameMap.put("v1_15_R1", "o");
                    methodNameMap.put("v1_16_R1", "p");
                    methodNameMap.put("v1_16_R2", "p");
                    methodNameMap.put("v1_16_R3", "p");
                    methodNameMap.put("v1_17_R1", "r");
                    methodNameMap.put("v1_18_R1", "r");
                    methodNameMap.put("v1_18_R2", "r");
                    methodNameMap.put("v1_19_R1", "r");
                    methodNameMap.put("v1_19_R2", "r");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");

                    setShowArmsAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setShowArmsAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setBasePlate(Boolean)
     */
    private Method setBasePlateAtEntityArmorStand;
    public Method EntityArmorStand_setBasePlate() {
        if (setBasePlateAtEntityArmorStand == null) {
            try {
                setBasePlateAtEntityArmorStand = EntityArmorStand().getMethod("setBasePlate", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "o");
                    methodNameMap.put("v1_13_R1", "r");
                    methodNameMap.put("v1_13_R2", "r");
                    methodNameMap.put("v1_14_R1", "p");
                    methodNameMap.put("v1_15_R1", "p");
                    methodNameMap.put("v1_16_R1", "q");
                    methodNameMap.put("v1_16_R2", "q");
                    methodNameMap.put("v1_16_R3", "q");
                    methodNameMap.put("v1_17_R1", "s");
                    methodNameMap.put("v1_18_R1", "s");
                    methodNameMap.put("v1_18_R2", "s");
                    methodNameMap.put("v1_19_R1", "s");
                    methodNameMap.put("v1_19_R2", "s");
                    methodNameMap.put("v1_19_R3", "s");
                    methodNameMap.put("v1_20_R1", "s");

                    setBasePlateAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setBasePlateAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setMarker(Boolean)
     */
    private Method setMarkerAtEntityArmorStand;
    public Method EntityArmorStand_setMarker() {
        if (setMarkerAtEntityArmorStand == null) {
            try {
                setMarkerAtEntityArmorStand = EntityArmorStand().getMethod("setMarker", Boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "p");
                    methodNameMap.put("v1_13_R1", "s");
                    methodNameMap.put("v1_13_R2", "s");
                    methodNameMap.put("v1_14_R1", "q");
                    methodNameMap.put("v1_15_R1", "q");
                    methodNameMap.put("v1_16_R1", "r");
                    methodNameMap.put("v1_16_R2", "r");
                    methodNameMap.put("v1_16_R3", "r");
                    methodNameMap.put("v1_17_R1", "t");
                    methodNameMap.put("v1_18_R1", "t");
                    methodNameMap.put("v1_18_R2", "t");
                    methodNameMap.put("v1_19_R1", "t");
                    methodNameMap.put("v1_19_R2", "t");
                    methodNameMap.put("v1_19_R3", "u");
                    methodNameMap.put("v1_20_R1", "u");

                    setMarkerAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Boolean.class);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setMarkerAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setHeadPose(Vector3f)
     */
    private Method setHeadPoseAtEntityArmorStand;
    public Method EntityArmorStand_setHeadPose() {
        if (setHeadPoseAtEntityArmorStand == null) {
            try {
                setHeadPoseAtEntityArmorStand = EntityArmorStand().getMethod("setHeadPose", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");

                    setHeadPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Vector3f());
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setHeadPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#headPose
     */
    private Field headPoseAtEntityArmorStand;
    public Field EntityArmorStand_headPose() {
        if (headPoseAtEntityArmorStand == null) {
            try {
                headPoseAtEntityArmorStand = EntityArmorStand().getField("headPose");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "bD");
                    fieldNameMap.put("v1_13_R1", "bJ");
                    fieldNameMap.put("v1_13_R2", "bJ");
                    fieldNameMap.put("v1_14_R1", "bF");
                    fieldNameMap.put("v1_15_R1", "bC");
                    fieldNameMap.put("v1_16_R1", "bB");
                    fieldNameMap.put("v1_16_R2", "bw");
                    fieldNameMap.put("v1_16_R3", "bw");
                    fieldNameMap.put("v1_17_R1", "cg");
                    fieldNameMap.put("v1_18_R1", "ch");
                    fieldNameMap.put("v1_18_R2", "cg");
                    fieldNameMap.put("v1_19_R1", "cg");
                    fieldNameMap.put("v1_19_R2", "cg");
                    fieldNameMap.put("v1_19_R3", "cb");
                    fieldNameMap.put("v1_20_R1", "cc");

                    headPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.name()));
                } catch (NoSuchFieldException e) { e.printStackTrace(); }
            }
        }
        return headPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#getHeadPose() (Support: 1.14+)
     */
    private Method getHeadPoseAtEntityArmorStand;
    public Method EntityArmorStand_getHeadPose() {
        if (getHeadPoseAtEntityArmorStand == null) {
            try {
                getHeadPoseAtEntityArmorStand = EntityArmorStand().getMethod("getHeadPose");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", null);
                    methodNameMap.put("v1_13_R1", null);
                    methodNameMap.put("v1_13_R2", null);
                    methodNameMap.put("v1_14_R1", "r");
                    methodNameMap.put("v1_15_R1", "r");
                    methodNameMap.put("v1_16_R1", "r");
                    methodNameMap.put("v1_16_R2", "r");
                    methodNameMap.put("v1_16_R3", "r");
                    methodNameMap.put("v1_17_R1", "v");
                    methodNameMap.put("v1_18_R1", "u");
                    methodNameMap.put("v1_18_R2", "u");
                    methodNameMap.put("v1_19_R1", "u");
                    methodNameMap.put("v1_19_R2", "u");
                    methodNameMap.put("v1_19_R3", "x");
                    methodNameMap.put("v1_20_R1", "x");

                    String methodName = methodNameMap.get(version.name());
                    if (methodName != null) getHeadPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return getHeadPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setLeftArmPose(Vector3f)
     */
    private Method setLeftArmPoseAtEntityArmorStand;
    public Method EntityArmorStand_setLeftArmPose() {
        if (setLeftArmPoseAtEntityArmorStand == null) {
            try {
                setLeftArmPoseAtEntityArmorStand = EntityArmorStand().getMethod("setLeftArmPose", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "c");
                    methodNameMap.put("v1_13_R1", "c");
                    methodNameMap.put("v1_13_R2", "c");
                    methodNameMap.put("v1_14_R1", "c");
                    methodNameMap.put("v1_15_R1", "c");
                    methodNameMap.put("v1_16_R1", "c");
                    methodNameMap.put("v1_16_R2", "c");
                    methodNameMap.put("v1_16_R3", "c");
                    methodNameMap.put("v1_17_R1", "c");
                    methodNameMap.put("v1_18_R1", "c");
                    methodNameMap.put("v1_18_R2", "c");
                    methodNameMap.put("v1_19_R1", "c");
                    methodNameMap.put("v1_19_R2", "c");
                    methodNameMap.put("v1_19_R3", "c");
                    methodNameMap.put("v1_20_R1", "c");

                    setLeftArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.name()), Vector3f());
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setLeftArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#leftArmPose
     */
    private Field leftArmPoseAtEntityArmorStand;
    public Field EntityArmorStand_leftArmPose() {
        if (leftArmPoseAtEntityArmorStand == null) {
            try {
                leftArmPoseAtEntityArmorStand = EntityArmorStand().getField("leftArmPose");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "bF");
                    fieldNameMap.put("v1_13_R1", "bL");
                    fieldNameMap.put("v1_13_R2", "bL");
                    fieldNameMap.put("v1_14_R1", "bH");
                    fieldNameMap.put("v1_15_R1", "bE");
                    fieldNameMap.put("v1_16_R1", "bD");
                    fieldNameMap.put("v1_16_R2", "by");
                    fieldNameMap.put("v1_16_R3", "by");
                    fieldNameMap.put("v1_17_R1", "ci");
                    fieldNameMap.put("v1_18_R1", "cj");
                    fieldNameMap.put("v1_18_R2", "ci");
                    fieldNameMap.put("v1_19_R1", "ci");
                    fieldNameMap.put("v1_19_R2", "ci");
                    fieldNameMap.put("v1_19_R3", "cd");
                    fieldNameMap.put("v1_20_R1", "ce");

                    leftArmPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.name()));
                } catch (NoSuchFieldException e) { e.printStackTrace(); }
            }
        }
        return leftArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#getLeftArmPose() (Support: 1.17+)
     */
    private Method getLeftArmPoseAtEntityArmorStand;
    public Method EntityArmorStand_getLeftArmPose() {
        if (getLeftArmPoseAtEntityArmorStand == null) {
            try {
                getLeftArmPoseAtEntityArmorStand = EntityArmorStand().getMethod("getLeftArmPose");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", null);
                    methodNameMap.put("v1_13_R1", null);
                    methodNameMap.put("v1_13_R2", null);
                    methodNameMap.put("v1_14_R1", null);
                    methodNameMap.put("v1_15_R1", null);
                    methodNameMap.put("v1_16_R1", null);
                    methodNameMap.put("v1_16_R2", null);
                    methodNameMap.put("v1_16_R3", null);
                    methodNameMap.put("v1_17_R1", "x");
                    methodNameMap.put("v1_18_R1", "x");
                    methodNameMap.put("v1_18_R2", "x");
                    methodNameMap.put("v1_19_R1", "x");
                    methodNameMap.put("v1_19_R2", "y");
                    methodNameMap.put("v1_19_R3", "z");
                    methodNameMap.put("v1_20_R1", "z");

                    String methodName = methodNameMap.get(version.name());
                    if (methodName != null) getLeftArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return getLeftArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#rightArmPose
     */
    private Field rightArmPoseAtEntityArmorStand;
    public Field EntityArmorStand_rightArmPose() {
        if (rightArmPoseAtEntityArmorStand == null) {
            try {
                rightArmPoseAtEntityArmorStand = EntityArmorStand().getField("rightArmPose");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "bG");
                    fieldNameMap.put("v1_13_R1", "bM");
                    fieldNameMap.put("v1_13_R2", "bM");
                    fieldNameMap.put("v1_14_R1", "bI");
                    fieldNameMap.put("v1_15_R1", "bF");
                    fieldNameMap.put("v1_16_R1", "bE");
                    fieldNameMap.put("v1_16_R2", "bz");
                    fieldNameMap.put("v1_16_R3", "bz");
                    fieldNameMap.put("v1_17_R1", "cj");
                    fieldNameMap.put("v1_18_R1", "ck");
                    fieldNameMap.put("v1_18_R2", "cj");
                    fieldNameMap.put("v1_19_R1", "cj");
                    fieldNameMap.put("v1_19_R2", "cj");
                    fieldNameMap.put("v1_19_R3", "ce");
                    fieldNameMap.put("v1_20_R1", "cf");

                    rightArmPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.name()));
                } catch (NoSuchFieldException e) { e.printStackTrace(); }
            }
        }
        return rightArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setRightArmPose(Vector3f)
     */
    private Method setRightArmPoseAtEntityArmorStand;
    public Method EntityArmorStand_setRightArmPose() {
        if (setRightArmPoseAtEntityArmorStand == null) {
            try {
                setRightArmPoseAtEntityArmorStand = EntityArmorStand().getMethod("setRightArmPose", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "d");
                    methodNameMap.put("v1_13_R1", "d");
                    methodNameMap.put("v1_13_R2", "d");
                    methodNameMap.put("v1_14_R1", "d");
                    methodNameMap.put("v1_15_R1", "d");
                    methodNameMap.put("v1_16_R1", "d");
                    methodNameMap.put("v1_16_R2", "d");
                    methodNameMap.put("v1_16_R3", "d");
                    methodNameMap.put("v1_17_R1", "d");
                    methodNameMap.put("v1_18_R1", "d");
                    methodNameMap.put("v1_18_R2", "d");
                    methodNameMap.put("v1_19_R1", "d");
                    methodNameMap.put("v1_19_R2", "d");
                    methodNameMap.put("v1_19_R3", "d");
                    methodNameMap.put("v1_20_R1", "d");

                    String methodName = methodNameMap.get(version.name());
                    if (methodName != null) setRightArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName, Vector3f());
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return setRightArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#getRightArmPose() (Support: 1.17+)
     */
    private Method getRightArmPoseAtEntityArmorStand;
    public Method EntityArmorStand_getRightArmPose() {
        if (getRightArmPoseAtEntityArmorStand == null) {
            try {
                getRightArmPoseAtEntityArmorStand = EntityArmorStand().getMethod("getRightArmPose");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", null);
                    methodNameMap.put("v1_13_R1", null);
                    methodNameMap.put("v1_13_R2", null);
                    methodNameMap.put("v1_14_R1", null);
                    methodNameMap.put("v1_15_R1", null);
                    methodNameMap.put("v1_16_R1", null);
                    methodNameMap.put("v1_16_R2", null);
                    methodNameMap.put("v1_16_R3", null);
                    methodNameMap.put("v1_17_R1", "z");
                    methodNameMap.put("v1_18_R1", "y");
                    methodNameMap.put("v1_18_R2", "y");
                    methodNameMap.put("v1_19_R1", "y");
                    methodNameMap.put("v1_19_R2", "z");
                    methodNameMap.put("v1_19_R3", "A");
                    methodNameMap.put("v1_20_R1", "A");

                    String methodName = methodNameMap.get(version.name());
                    if (methodName != null) getRightArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName);
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return getRightArmPoseAtEntityArmorStand;
    }


    /* WORLD
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * World
     */
    private Class<?> WorldClass;
    public Class<?> World() {
        if (WorldClass == null) {
            try {
                WorldClass = Class.forName(nmsPackage + ".World");
            } catch (ClassNotFoundException ignored) {
                try {
                    WorldClass = Class.forName("net.minecraft.world.level.World");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return WorldClass;
    }


    /* METADATA
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * IChatBaseComponent
     */
    private Class<?> IChatBaseComponentClass;
    public Class<?> IChatBaseComponent() {
        if (IChatBaseComponentClass == null) {
            try {
                IChatBaseComponentClass = Class.forName(nmsPackage + ".IChatBaseComponent");
            } catch (Exception ignored) {
                try {
                    IChatBaseComponentClass = Class.forName("net.minecraft.network.chat.IChatBaseComponent");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return IChatBaseComponentClass;
    }

    /**
     * ChatSerializer
     */
    private Class<?> ChatSerializerClass;
    public Class<?> ChatSerializer() {
        if (ChatSerializerClass == null) {
            try {
                ChatSerializerClass = Class.forName(nmsPackage + ".IChatBaseComponent$ChatSerializer");
            } catch (Exception ignored) {
                try {
                    ChatSerializerClass = Class.forName("net.minecraft.network.chat.IChatBaseComponent$ChatSerializer");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return ChatSerializerClass;
    }

    /**
     * Vector3f
     */
    private Class<?> Vector3fClass;
    public Class<?> Vector3f() {
        if (Vector3fClass == null) {
            try {
                Vector3fClass = Class.forName(nmsPackage + ".Vector3f");
            } catch (Exception ignored) {
                try {
                    Vector3fClass = Class.forName("net.minecraft.world.phys.Vec3D");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return Vector3fClass;
    }

    /**
     * Pair (Support: 1.13+)
     */
    private Constructor<?> PairClass;
    public Constructor<?> Pair() {
        if (PairClass == null) {
            try {
                PairClass = Class.forName("com.mojang.datafixers.util.Pair").getConstructor(Object.class, Object.class);
            } catch (Exception ignored) {
                PairClass = null;
            }
        }
        return PairClass;
    }

    /**
     * ChatSerializer#serialize(String)
     */
    private Method serializeAtChatSerializer;
    public Method ChatSerializer_serialize() {
        if (serializeAtChatSerializer == null && version.isHighVersion()) {
            try {
                serializeAtChatSerializer = ChatSerializerClass.getMethod("a", String.class);
            } catch (NoSuchMethodException ignored) {}
        }
        return serializeAtChatSerializer;
    }

    /**
     * EnumHand
     */
    private Class<?> EnumHandClass;
    public Class<?> EnumHand() {
        if (EnumHandClass == null) {
            try {
                EnumHandClass = Class.forName(nmsPackage + ".EnumHand");
            } catch (ClassNotFoundException ignored) {
                try {
                    EnumHandClass = Class.forName("net.minecraft.world.EnumHand");
                } catch (ClassNotFoundException e) { e.printStackTrace(); }
            }
        }
        return EnumHandClass;
    }

    /**
     * EnumHand : MAIN_HAND
     */
    private Object EnumHand_MAIN_HAND;
    public Object EnumHand_MAIN_HAND() {
        if (EnumHand_MAIN_HAND == null) {
            try {
                EnumHand_MAIN_HAND = EnumHand().getField("MAIN_HAND").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");

                    EnumHand_MAIN_HAND = EnumHand().getField(methodNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumHand_MAIN_HAND;
    }

    /**
     * EnumHand : OFF_HAND
     */
    private Object EnumHand_OFF_HAND;
    public Object EnumHand_OFF_HAND() {
        if (EnumHand_OFF_HAND == null) {
            try {
                EnumHand_OFF_HAND = EnumHand().getField("OFF_HAND").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "b");
                    methodNameMap.put("v1_13_R1", "b");
                    methodNameMap.put("v1_13_R2", "b");
                    methodNameMap.put("v1_14_R1", "b");
                    methodNameMap.put("v1_15_R1", "b");
                    methodNameMap.put("v1_16_R1", "b");
                    methodNameMap.put("v1_16_R2", "b");
                    methodNameMap.put("v1_16_R3", "b");
                    methodNameMap.put("v1_17_R1", "b");
                    methodNameMap.put("v1_18_R1", "b");
                    methodNameMap.put("v1_18_R2", "b");
                    methodNameMap.put("v1_19_R1", "b");
                    methodNameMap.put("v1_19_R2", "b");
                    methodNameMap.put("v1_19_R3", "b");
                    methodNameMap.put("v1_20_R1", "b");

                    EnumHand_OFF_HAND = EnumHand().getField(methodNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumHand_OFF_HAND;
    }

    /**
     * EnumItemSlot
     */
    private Class<?> EnumItemSlotClass;
    public Class<?> EnumItemSlot() {
        if (EnumItemSlotClass == null) {
            try {
                EnumItemSlotClass = Class.forName(nmsPackage + ".EnumItemSlot");
            } catch (Exception ignored) {
                try {
                    EnumItemSlotClass = Class.forName("net.minecraft.world.entity.EnumItemSlot");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlotClass;
    }

    /**
     * EnumItemSlot : MAINHAND
     */
    private Object EnumItemSlot_MAINHAND;
    public Object EnumItemSlot_MAINHAND() {
        if (EnumItemSlot_MAINHAND == null) {
            try {
                EnumItemSlot_MAINHAND = EnumHand().getField("MAINHAND").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "a");
                    fieldNameMap.put("v1_13_R1", "a");
                    fieldNameMap.put("v1_13_R2", "a");
                    fieldNameMap.put("v1_14_R1", "a");
                    fieldNameMap.put("v1_15_R1", "a");
                    fieldNameMap.put("v1_16_R1", "a");
                    fieldNameMap.put("v1_16_R2", "a");
                    fieldNameMap.put("v1_16_R3", "a");
                    fieldNameMap.put("v1_17_R1", "a");
                    fieldNameMap.put("v1_18_R1", "a");
                    fieldNameMap.put("v1_18_R2", "a");
                    fieldNameMap.put("v1_19_R1", "a");
                    fieldNameMap.put("v1_19_R2", "a");
                    fieldNameMap.put("v1_19_R3", "a");
                    fieldNameMap.put("v1_20_R1", "a");

                    EnumItemSlot_MAINHAND = EnumHand().getField(fieldNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlot_MAINHAND;
    }

    /**
     * EnumItemSlot : CHEST
     */
    private Object EnumItemSlot_CHEST;
    public Object EnumItemSlot_OFFHAND() {
        if (EnumItemSlot_CHEST == null) {
            try {
                EnumItemSlot_CHEST = EnumHand().getField("CHEST").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "b");
                    fieldNameMap.put("v1_13_R1", "b");
                    fieldNameMap.put("v1_13_R2", "b");
                    fieldNameMap.put("v1_14_R1", "b");
                    fieldNameMap.put("v1_15_R1", "b");
                    fieldNameMap.put("v1_16_R1", "b");
                    fieldNameMap.put("v1_16_R2", "b");
                    fieldNameMap.put("v1_16_R3", "b");
                    fieldNameMap.put("v1_17_R1", "b");
                    fieldNameMap.put("v1_18_R1", "b");
                    fieldNameMap.put("v1_18_R2", "b");
                    fieldNameMap.put("v1_19_R1", "b");
                    fieldNameMap.put("v1_19_R2", "b");
                    fieldNameMap.put("v1_19_R3", "b");
                    fieldNameMap.put("v1_20_R1", "b");

                    EnumItemSlot_CHEST = EnumHand().getField(fieldNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlot_CHEST;
    }

    /**
     * EnumItemSlot : FEET
     */
    private Object EnumItemSlot_FEET;
    public Object EnumItemSlot_FEET() {
        if (EnumItemSlot_FEET == null) {
            try {
                EnumItemSlot_FEET = EnumHand().getField("FEET").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "c");
                    fieldNameMap.put("v1_13_R1", "c");
                    fieldNameMap.put("v1_13_R2", "c");
                    fieldNameMap.put("v1_14_R1", "c");
                    fieldNameMap.put("v1_15_R1", "c");
                    fieldNameMap.put("v1_16_R1", "c");
                    fieldNameMap.put("v1_16_R2", "c");
                    fieldNameMap.put("v1_16_R3", "c");
                    fieldNameMap.put("v1_17_R1", "c");
                    fieldNameMap.put("v1_18_R1", "c");
                    fieldNameMap.put("v1_18_R2", "c");
                    fieldNameMap.put("v1_19_R1", "c");
                    fieldNameMap.put("v1_19_R2", "c");
                    fieldNameMap.put("v1_19_R3", "c");
                    fieldNameMap.put("v1_20_R1", "c");

                    EnumItemSlot_FEET = EnumHand().getField(fieldNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlot_FEET;
    }

    /**
     * EnumItemSlot : LEGS
     */
    private Object EnumItemSlot_LEGS;
    public Object EnumItemSlot_LEGS() {
        if (EnumItemSlot_LEGS == null) {
            try {
                EnumItemSlot_LEGS = EnumHand().getField("LEGS").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "d");
                    fieldNameMap.put("v1_13_R1", "d");
                    fieldNameMap.put("v1_13_R2", "d");
                    fieldNameMap.put("v1_14_R1", "d");
                    fieldNameMap.put("v1_15_R1", "d");
                    fieldNameMap.put("v1_16_R1", "d");
                    fieldNameMap.put("v1_16_R2", "d");
                    fieldNameMap.put("v1_16_R3", "d");
                    fieldNameMap.put("v1_17_R1", "d");
                    fieldNameMap.put("v1_18_R1", "d");
                    fieldNameMap.put("v1_18_R2", "d");
                    fieldNameMap.put("v1_19_R1", "d");
                    fieldNameMap.put("v1_19_R2", "d");
                    fieldNameMap.put("v1_19_R3", "d");
                    fieldNameMap.put("v1_20_R1", "d");

                    EnumItemSlot_LEGS = EnumHand().getField(fieldNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlot_LEGS;
    }

    /**
     * EnumItemSlot : HEAD
     */
    private Object EnumItemSlot_HEAD;
    public Object EnumItemSlot_HEAD() {
        if (EnumItemSlot_HEAD == null) {
            try {
                EnumItemSlot_HEAD = EnumHand().getField("HEAD").get(null);
            } catch (Exception ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "f");
                    fieldNameMap.put("v1_13_R1", "f");
                    fieldNameMap.put("v1_13_R2", "f");
                    fieldNameMap.put("v1_14_R1", "f");
                    fieldNameMap.put("v1_15_R1", "f");
                    fieldNameMap.put("v1_16_R1", "f");
                    fieldNameMap.put("v1_16_R2", "f");
                    fieldNameMap.put("v1_16_R3", "f");
                    fieldNameMap.put("v1_17_R1", "f");
                    fieldNameMap.put("v1_18_R1", "f");
                    fieldNameMap.put("v1_18_R2", "f");
                    fieldNameMap.put("v1_19_R1", "f");
                    fieldNameMap.put("v1_19_R2", "f");
                    fieldNameMap.put("v1_19_R3", "f");
                    fieldNameMap.put("v1_20_R1", "f");

                    EnumItemSlot_HEAD = EnumHand().getField(fieldNameMap.get(version.name())).get(null);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return EnumItemSlot_HEAD;
    }


    /* ITEM
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * ItemStack
     */
    private Class<?> ItemStackClass;
    public Class<?> ItemStack() {
        if (ItemStackClass == null) {
            try {
                ItemStackClass = Class.forName(nmsPackage + ".ItemStack");
            } catch (Exception ignored) {
                try {
                    ItemStackClass = Class.forName("net.minecraft.world.item.ItemStack");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return ItemStackClass;
    }

    /**
     * ItemStack#setTag(NBTTagCompound)
     */
    private Method ItemStackSetTagMethod;
    public Method ItemStack_setTag() {
        if (ItemStackSetTagMethod == null) {
            try {
                ItemStackSetTagMethod = ItemStack().getMethod("setTag", NBTTagCompound());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "c");
                    methodNameMap.put("v1_13_R1", "c");
                    methodNameMap.put("v1_13_R2", "c");
                    methodNameMap.put("v1_14_R1", "c");
                    methodNameMap.put("v1_15_R1", "c");
                    methodNameMap.put("v1_16_R1", "c");
                    methodNameMap.put("v1_16_R2", "c");
                    methodNameMap.put("v1_16_R3", "c");
                    methodNameMap.put("v1_17_R1", "c");
                    methodNameMap.put("v1_18_R1", "c");
                    methodNameMap.put("v1_18_R2", "c");
                    methodNameMap.put("v1_19_R1", "c");
                    methodNameMap.put("v1_19_R2", "c");
                    methodNameMap.put("v1_19_R3", "c");
                    methodNameMap.put("v1_20_R1", "c");

                    ItemStackSetTagMethod = ItemStack().getDeclaredMethod(methodNameMap.get(version.name()), NBTTagCompound());
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return ItemStackSetTagMethod;
    }

    /**
     * ItemStack#getTag()
     */
    private Method ItemStackGetTagMethod;
    public Method ItemStack_getTag() {
        if (ItemStackGetTagMethod == null) {
            try {
                ItemStackGetTagMethod = ItemStack().getMethod("getTag");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "u");
                    methodNameMap.put("v1_13_R1", "u");
                    methodNameMap.put("v1_13_R2", "u");
                    methodNameMap.put("v1_14_R1", "u");
                    methodNameMap.put("v1_15_R1", "u");
                    methodNameMap.put("v1_16_R1", "u");
                    methodNameMap.put("v1_16_R2", "u");
                    methodNameMap.put("v1_16_R3", "u");
                    methodNameMap.put("v1_17_R1", "u");
                    methodNameMap.put("v1_18_R1", "u");
                    methodNameMap.put("v1_18_R2", "u");
                    methodNameMap.put("v1_19_R1", "u");
                    methodNameMap.put("v1_19_R2", "u");
                    methodNameMap.put("v1_19_R3", "u");
                    methodNameMap.put("v1_20_R1", "u");

                    ItemStackGetTagMethod = ItemStack().getDeclaredMethod(methodNameMap.get(version.name()));
                } catch (NoSuchMethodException e) { e.printStackTrace(); }
            }
        }
        return ItemStackGetTagMethod;
    }

    /**
     * CraftItemStack
     */
    private Class<?> CraftItemStackClass;
    public Class<?> CraftItemStack() {
        if (CraftItemStackClass == null) {
            try {
                CraftItemStackClass = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".inventory.CraftItemStack");
            } catch (ClassNotFoundException e) { e.printStackTrace(); }
        }
        return CraftItemStackClass;
    }

    /**
     * NBTTagCompound
     */
    private Class<?> NBTTagCompoundClass;
    public Class<?> NBTTagCompound() {
        if (NBTTagCompoundClass == null) {
            try {
                NBTTagCompoundClass = Class.forName(nmsPackage + ".NBTTagCompound");
            } catch (Exception ignored) {
                try {
                    NBTTagCompoundClass = Class.forName("net.minecraft.nbt.NBTTagCompound");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return NBTTagCompoundClass;
    }

    /**
     * NBTTagCompound#getString(String)
     */
    private Method getStringAtNBTTagCompound;
    public Method NBTTagCompound_getString() {
        if (getStringAtNBTTagCompound == null) {
            try {
                getStringAtNBTTagCompound = NBTTagCompound().getMethod("getString", String.class);
            } catch (Exception ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "l");
                    methodNameMap.put("v1_13_R1", "l");
                    methodNameMap.put("v1_13_R2", "l");
                    methodNameMap.put("v1_14_R1", "l");
                    methodNameMap.put("v1_15_R1", "l");
                    methodNameMap.put("v1_16_R1", "l");
                    methodNameMap.put("v1_16_R2", "l");
                    methodNameMap.put("v1_16_R3", "l");
                    methodNameMap.put("v1_17_R1", "l");
                    methodNameMap.put("v1_18_R1", "l");
                    methodNameMap.put("v1_18_R2", "l");
                    methodNameMap.put("v1_19_R1", "l");
                    methodNameMap.put("v1_19_R2", "l");
                    methodNameMap.put("v1_19_R3", "l");
                    methodNameMap.put("v1_20_R1", "l");

                    getStringAtNBTTagCompound = NBTTagCompoundClass.getDeclaredMethod(methodNameMap.get(version.name()), String.class);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return getStringAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#setString(String, String)
     */
    private Method setStringAtNBTTagCompound;
    public Method NBTTagCompound_setString() {
        if (setStringAtNBTTagCompound == null) {
            try {
                setStringAtNBTTagCompound = NBTTagCompound().getMethod("setString", String.class, String.class);
            } catch (Exception ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "a");
                    methodNameMap.put("v1_13_R2", "a");
                    methodNameMap.put("v1_14_R1", "a");
                    methodNameMap.put("v1_15_R1", "a");
                    methodNameMap.put("v1_16_R1", "a");
                    methodNameMap.put("v1_16_R2", "a");
                    methodNameMap.put("v1_16_R3", "a");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");

                    setStringAtNBTTagCompound = NBTTagCompoundClass.getDeclaredMethod(methodNameMap.get(version.name()), String.class, String.class);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return setStringAtNBTTagCompound;
    }

    /**
     * Item
     */
    private Class<?> ItemClass;
    public Class<?> Item() {
        if (ItemClass == null) {
            try {
                ItemClass = Class.forName(nmsPackage + ".Item");
            } catch (Exception ignored) {
                try {
                    ItemClass = Class.forName("net.minecraft.world.item.Item");
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return ItemClass;
    }

    /**
     * Item#getDescriptionId(ItemStack)
     */
    private Method getDescriptionIdAtItem;
    public Method Item_getDescriptionId() {
        try {
            getDescriptionIdAtItem = ItemClass.getMethod("getDescriptionId", ItemStack());
        } catch (NoSuchMethodException ignored) {
            try {
                Map<String, String> methodNameMap = new HashMap<>();
                methodNameMap.put("v1_12_R1", "j");
                methodNameMap.put("v1_13_R2", "h");
                methodNameMap.put("v1_14_R1", "f");
                methodNameMap.put("v1_15_R1", "f");
                methodNameMap.put("v1_16_R1", "f");
                methodNameMap.put("v1_16_R2", "f");
                methodNameMap.put("v1_16_R3", "f");
                methodNameMap.put("v1_17_R1", "j");
                methodNameMap.put("v1_18_R1", "j");
                methodNameMap.put("v1_18_R2", "j");
                methodNameMap.put("v1_19_R1", "j");
                methodNameMap.put("v1_19_R2", "j");
                methodNameMap.put("v1_19_R3", "j");
                methodNameMap.put("v1_20_R1", "j");

                getDescriptionIdAtItem = ItemClass.getMethod(methodNameMap.get(version.name()), ItemStack());
            } catch (NoSuchMethodException e) { e.printStackTrace(); }
        }
        return getDescriptionIdAtItem;
    }

    /**
     * Item#use(World, EntityHuman, EnumHand)
     */
    private Method useAtItem;
    public Method Item_use() {
        try {
            useAtItem = ItemClass.getMethod("use", World(), EntityHuman(), EnumHand());
        } catch (NoSuchMethodException ignored) {
            try {
                Map<String, String> methodNameMap = new HashMap<>();
                methodNameMap.put("v1_12_R1", "a");
                methodNameMap.put("v1_13_R1", "a");
                methodNameMap.put("v1_13_R2", "a");
                methodNameMap.put("v1_14_R1", "a");
                methodNameMap.put("v1_15_R1", "a");
                methodNameMap.put("v1_16_R1", "a");
                methodNameMap.put("v1_16_R2", "a");
                methodNameMap.put("v1_16_R3", "a");
                methodNameMap.put("v1_17_R1", "a");
                methodNameMap.put("v1_18_R1", "a");
                methodNameMap.put("v1_18_R2", "a");
                methodNameMap.put("v1_19_R1", "a");
                methodNameMap.put("v1_19_R2", "a");
                methodNameMap.put("v1_19_R3", "a");
                methodNameMap.put("v1_20_R1", "a");

                useAtItem = ItemClass.getMethod(methodNameMap.get(version.name()), World(), EntityHuman(), EnumHand());
            } catch (NoSuchMethodException e) { e.printStackTrace(); }
        }
        return useAtItem;
    }


    /* PACKET - IMPL
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    /**
     * PacketPlayOutSpawnEntity
     */
    private Constructor<?> PacketPlayOutSpawnEntityConstructor;
    public Constructor<?> PacketPlayOutSpawnEntity_Constructor() {
        if (PacketPlayOutSpawnEntityConstructor == null) {
            try {
                PacketPlayOutSpawnEntityConstructor = Class.forName(nmsPackage + ".PacketPlayOutSpawnEntity").getConstructor(EntityLiving());
            } catch (Exception ignored) {
                try {
                    PacketPlayOutSpawnEntityConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity").getConstructor(Entity());
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return PacketPlayOutSpawnEntityConstructor;
    }

    /**
     * PacketPlayOutEntityDestroy
     */
    private Constructor<?> PacketPlayOutEntityDestroyConstructor;
    public Constructor<?> PacketPlayOutEntityDestroy_Constructor() {
        if (PacketPlayOutEntityDestroyConstructor == null) {
            try {
                PacketPlayOutEntityDestroyConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityDestroy").getConstructor(int[].class);
            } catch (Exception ignored) {
                try {
                    PacketPlayOutEntityDestroyConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy").getConstructor(int[].class);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return PacketPlayOutEntityDestroyConstructor;
    }

    /**
     * PacketPlayOutEntityEquipment
     */
    private Constructor<?> PacketPlayOutEntityEquipmentConstructor;
    public Constructor<?> PacketPlayOutEntityEquipment_Constructor() {
        if (PacketPlayOutEntityEquipmentConstructor == null) {
            try {
                PacketPlayOutEntityEquipmentConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityEquipment").getConstructor(int.class, EnumItemSlot(), ItemStack());
            } catch (Exception ignored) {
                try {
                    PacketPlayOutEntityEquipmentConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityEquipment").getConstructor(int.class, List.class);
                } catch (Exception ignored1) {
                    try {
                        PacketPlayOutEntityEquipmentConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment").getConstructor(int.class, List.class);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
        return PacketPlayOutEntityEquipmentConstructor;
    }

    /**
     * PacketPlayOutEntityTeleport
     */
    private Constructor<?> PacketPlayOutEntityTeleportConstructor;
    public Constructor<?> PacketPlayOutEntityTeleport_Constructor() {
        if (PacketPlayOutEntityTeleportConstructor == null) {
            try {
                PacketPlayOutEntityTeleportConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityTeleport").getConstructor(Entity());
            } catch (Exception ignored) {
                try {
                    PacketPlayOutEntityTeleportConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport").getConstructor(Entity());
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
        return PacketPlayOutEntityTeleportConstructor;
    }

    /**
     * PacketPlayOutEntityMetadata
     */
    private Constructor<?> PacketPlayOutEntityMetadataConstructor;
    public Constructor<?> PacketPlayOutEntityMetadata_Constructor() {
        if (PacketPlayOutEntityMetadataConstructor == null) {
            try {
                PacketPlayOutEntityMetadataConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityMetadata").getConstructor(int.class, DataWatcher(), boolean.class);
            } catch (Exception ignored) {
                try {
                    PacketPlayOutEntityMetadataConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata").getConstructor(int.class, DataWatcher(), boolean.class);
                } catch (Exception ignored1) {
                    try {
                        PacketPlayOutEntityMetadataConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata").getConstructor(int.class, List.class);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
        return PacketPlayOutEntityMetadataConstructor;
    }
}
