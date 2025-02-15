package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import kr.starly.core.nms.wrapper.ArmorStandWrapper;
import kr.starly.core.nms.wrapper.EntityItemWrapper;
import kr.starly.core.nms.wrapper.WorldWrapper;
import kr.starly.core.util.FeatherLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("all")
public class NmsOtherUtil {

    private static NmsOtherUtil instance;

    public static NmsOtherUtil getInstance() {
        if (instance == null) instance = new NmsOtherUtil();
        return instance;
    }

    private NmsOtherUtil() {}

    private final Version version = VersionController.getInstance().getVersion();
    private final String nmsPackage = "net.minecraft.server." + version.getVersion();


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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EntityPlayerClass;
    }

    /**
     * EntityPlayer#playerConnection // EntityPlayer#connection
     */
    private Field playerConnectionAtEntityPlayer;
    public Field EntityPlayer_playerConnection() {
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
                    fieldNameMap.put("v1_20_R2", "c");
                    fieldNameMap.put("v1_20_R3", "c");

                    playerConnectionAtEntityPlayer = EntityPlayer().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return PlayerConnectionClass;
    }

    /**
     * PlayerConnection#sendPacket(Packet) // ServerCommonPacketListenerImpl#send(Packet<?>)
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
                    fieldNameMap.put("v1_20_R2", "b");
                    fieldNameMap.put("v1_20_R3", "b");

                    sendPacketAtPlayerConnection = PlayerConnection().getMethod(fieldNameMap.get(version.getVersion()), Packet());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
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
                CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".entity.CraftPlayer");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EntityClass;
    }

    private Method BukkitEntityClass;
    public Method BukkitEntity() {
        if (BukkitEntityClass == null) {
            try {
                BukkitEntityClass = Entity().getDeclaredMethod("getBukkitEntity");
                BukkitEntityClass.setAccessible(true);
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return BukkitEntityClass;
    }

    /**
     * EntityArmorStand#setLocation(Double, Double, Double) // Entity#teleport(Double, Double, Double)
     */
    private Method setLocationAtEntity;
    public Method Entity_setLocation() {
        if (setLocationAtEntity == null) {
            try {
                setLocationAtEntity = Entity().getMethod("teleport", double.class, double.class, double.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "b");
                    methodNameMap.put("v1_13_R1", "b");
                    methodNameMap.put("v1_13_R2", "b");
                    methodNameMap.put("v1_14_R1", "j");
                    methodNameMap.put("v1_15_R1", "l");
                    methodNameMap.put("v1_16_R1", "l");
                    methodNameMap.put("v1_16_R2", "m");
                    methodNameMap.put("v1_16_R3", "m");
                    methodNameMap.put("v1_17_R1", "m");
                    methodNameMap.put("v1_18_R1", "m");
                    methodNameMap.put("v1_18_R2", "m");
                    methodNameMap.put("v1_19_R1", "m");
                    methodNameMap.put("v1_19_R2", "n");
                    methodNameMap.put("v1_19_R3", "n");
                    methodNameMap.put("v1_20_R1", "n");
                    methodNameMap.put("v1_20_R2", "n");
                    methodNameMap.put("v1_20_R3", "n");

                    setLocationAtEntity = Entity().getMethod(methodNameMap.get(version.getVersion()), double.class, double.class, double.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setLocationAtEntity;
    }

    /**
     * Entity#setYawPitch(Float, Float) // Entity#setRotation(Float, Float)
     */
    private Method setYawPitchAtEntity;
    public Method Entity_setYawPitch() {
        if (setYawPitchAtEntity == null) {
            try {
                setYawPitchAtEntity = Entity().getMethod("setYawPitch", float.class, float.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "f");
                    methodNameMap.put("v1_13_R1", "f");
                    methodNameMap.put("v1_13_R2", "f");
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
                    methodNameMap.put("v1_20_R2", "a");
                    methodNameMap.put("v1_20_R3", "a");

                    setYawPitchAtEntity = Entity().getMethod(methodNameMap.get(version.getVersion()), float.class, float.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setYawPitchAtEntity;
    }

    /**
     * Entity#setCustomName(IChatBaseComponent) // Entity#setCustomName(Component)
     */
    private Method setCustomNameAtEntity;
    public Method Entity_setCustomName() {
        if (setCustomNameAtEntity == null) {
            try {
                setCustomNameAtEntity = Entity().getMethod("setCustomName", IChatBaseComponent());
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
                    methodNameMap.put("v1_20_R2", "b");
                    methodNameMap.put("v1_20_R3", "b");

                    setCustomNameAtEntity = Entity().getMethod(methodNameMap.get(version.getVersion()), IChatBaseComponent());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setCustomNameAtEntity;
    }

    /**
     * Entity#getId()
     */
    private Method getIdAtEntity;
    public Method Entity_getId() {
        if (getIdAtEntity == null) {
            try {
                getIdAtEntity = Entity().getMethod("getId");
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "S");
                    methodNameMap.put("v1_13_R1", "Q");
                    methodNameMap.put("v1_13_R2", "Q");
                    methodNameMap.put("v1_14_R1", "T");
                    methodNameMap.put("v1_15_R1", "S");
                    methodNameMap.put("v1_16_R1", "V");
                    methodNameMap.put("v1_16_R2", "X");
                    methodNameMap.put("v1_16_R3", "Y");
                    methodNameMap.put("v1_17_R1", "Z");
                    methodNameMap.put("v1_18_R1", "ae");
                    methodNameMap.put("v1_18_R2", "ae");
                    methodNameMap.put("v1_19_R1", "ae");
                    methodNameMap.put("v1_19_R2", "ah");
                    methodNameMap.put("v1_19_R3", "af");
                    methodNameMap.put("v1_20_R1", "af");
                    methodNameMap.put("v1_20_R2", "ah");
                    methodNameMap.put("v1_20_R3", "aj");

                    getIdAtEntity = Entity().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getIdAtEntity;
    }

    /**
     * Entity#getDataWatcher() // Entity#getDataTracker()
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
                    methodNameMap.put("v1_20_R2", "al");
                    methodNameMap.put("v1_20_R3", "an");

                    getDataWatcherAtEntity = Entity().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getDataWatcherAtEntity;
    }

    /**
     * Entity#setNoGravity(Boolean)
     */
    private Method setNoGravityAtEntity;
    public Method Entity_setNoGravity() {
        if (setNoGravityAtEntity == null) {
            try {
                setNoGravityAtEntity = Entity().getMethod("setNoGravity", boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    setNoGravityAtEntity = Entity().getMethod("e", boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setNoGravityAtEntity;
    }

    /**
     * Entity#setInvisible(Boolean)
     */
    private Method setInvisibleAtEntity;
    public Method Entity_setInvisible() {
        if (setInvisibleAtEntity == null) {
            try {
                setInvisibleAtEntity = Entity().getMethod("setInvisible", boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    setInvisibleAtEntity = Entity().getMethod("j", boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setInvisibleAtEntity;
    }

    /**
     * Entity#setCustomNameVisible(Boolean)
     */
    private Method setCustomNameVisibleAtEntity;
    public Method Entity_setCustomNameVisible() {
        if (setCustomNameVisibleAtEntity == null) {
            try {
                setCustomNameVisibleAtEntity = Entity().getMethod("setCustomNameVisible", boolean.class);
            } catch (NoSuchMethodException ignored) {
                try {
                    setCustomNameVisibleAtEntity = Entity().getMethod("n", boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setCustomNameVisibleAtEntity;
    }

    /**
     * Entity#save(NBTTagCompound)
     */
    private Method saveAtEntity;
    public Method Entity_save() {
        if (saveAtEntity == null) {
            try {
                saveAtEntity = Entity().getMethod("setCustomNameVisible", NBTTagCompound());
            } catch (NoSuchMethodException ignored) {
                try {
                    saveAtEntity = Entity().getMethod("e", NBTTagCompound());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return saveAtEntity;
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EntityLivingClass;
    }

    /**
     * DataWatcher // DataTracker
     */
    private Class<?> DataWatcherClass;
    public Class<?> DataWatcher() {
        if (DataWatcherClass == null) {
            try {
                DataWatcherClass = Class.forName(nmsPackage + ".DataWatcher");
            } catch (ClassNotFoundException ignored) {
                try {
                    DataWatcherClass = Class.forName("net.minecraft.network.syncher.DataWatcher");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return DataWatcherClass;
    }

    /**
     * DataWatcher#getNonDefaultValues() // DataTracker#getNonDefaultValues() // (Support: 1.19+)
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
            } catch (Exception ignored) {
                try {
                    EntityArmorStandClass = Class.forName("net.minecraft.world.entity.decoration.EntityArmorStand");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EntityArmorStandClass;
    }

    /**
     * EntityArmorStand#EntityArmorStand(World, double, double, double)
     */
    private Constructor<?> EntityArmorStandConstructor;
    public Constructor<?> EntityArmorStand_Constructor() {
        if (EntityArmorStandConstructor == null) {
            try {
                EntityArmorStandConstructor = EntityArmorStand().getConstructor(World(), double.class, double.class, double.class);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return EntityArmorStandConstructor;
    }

    /**
     * EntityArmorStand#setSmall(Boolean)
     */
    private Method setSmallAtEntityArmorStand;
    public Method EntityArmorStand_setSmall() {
        if (setSmallAtEntityArmorStand == null) {
            try {
                setSmallAtEntityArmorStand = EntityArmorStand().getMethod("setSmall", boolean.class);
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
                    methodNameMap.put("v1_20_R2", "t");
                    methodNameMap.put("v1_20_R3", "t");

                    setSmallAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setSmallAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setShowArms(Boolean)
     */
    private Method setShowArmsAtEntityArmorStand;
    public Method EntityArmorStand_setShowArms() {
        if (setShowArmsAtEntityArmorStand == null) {
            try {
                setShowArmsAtEntityArmorStand = EntityArmorStand().getMethod("setShowArms", boolean.class);
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
                    methodNameMap.put("v1_20_R2", "a");
                    methodNameMap.put("v1_20_R3", "a");

                    setShowArmsAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setShowArmsAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setNoBasePlate(!Boolean)
     */
    private Method setNoBasePlateAtEntityArmorStand;
    public Method EntityArmorStand_setNoBasePlate() {
        if (setNoBasePlateAtEntityArmorStand == null) {
            try {
                setNoBasePlateAtEntityArmorStand = EntityArmorStand().getMethod("setNoBasePlate", boolean.class);
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
                    methodNameMap.put("v1_20_R2", "s");
                    methodNameMap.put("v1_20_R3", "s");

                    setNoBasePlateAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setNoBasePlateAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setMarker(Boolean)
     */
    private Method setMarkerAtEntityArmorStand;
    public Method EntityArmorStand_setMarker() {
        if (setMarkerAtEntityArmorStand == null) {
            try {
                setMarkerAtEntityArmorStand = EntityArmorStand().getMethod("setMarker", boolean.class);
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
                    methodNameMap.put("v1_20_R2", "u");
                    methodNameMap.put("v1_20_R3", "u");

                    setMarkerAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), boolean.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setMarkerAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setHeadYaw(Float) // EntityArmorStand#setYHeadRot(Float)
     */
    private Method setHeadYawAtEntityArmorStand;
    public Method EntityArmorStand_setHeadYaw() {
        if (setHeadYawAtEntityArmorStand == null) {
            try {
                setHeadYawAtEntityArmorStand = EntityArmorStand().getMethod("setYHeadRot", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "g");
                    methodNameMap.put("v1_13_R1", "j");
                    methodNameMap.put("v1_13_R2", "j");
                    methodNameMap.put("v1_14_R1", "k");
                    methodNameMap.put("v1_15_R1", "k");
                    methodNameMap.put("v1_16_R1", "k");
                    methodNameMap.put("v1_16_R2", "k");
                    methodNameMap.put("v1_16_R3", "m");
                    methodNameMap.put("v1_17_R1", "l");
                    methodNameMap.put("v1_18_R1", "l");
                    methodNameMap.put("v1_18_R2", "l");
                    methodNameMap.put("v1_19_R1", "l");
                    methodNameMap.put("v1_19_R2", "l");
                    methodNameMap.put("v1_19_R3", "r");
                    methodNameMap.put("v1_20_R1", "n");
                    methodNameMap.put("v1_20_R2", "n");
                    methodNameMap.put("v1_20_R3", "n");

                    setHeadYawAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), float.class);
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setHeadYawAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setHeadRotation(Vector3f) // EntityArmorStand#setHeadPose(Vector3f)
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
                    methodNameMap.put("v1_20_R2", "a");
                    methodNameMap.put("v1_20_R3", "a");

                    setHeadPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), Vector3f());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
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
                    fieldNameMap.put("v1_20_R2", "cc");
                    fieldNameMap.put("v1_20_R3", "cc");

                    headPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return headPoseAtEntityArmorStand;
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
                    methodNameMap.put("v1_20_R2", "c");
                    methodNameMap.put("v1_20_R3", "c");

                    setLeftArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodNameMap.get(version.getVersion()), Vector3f());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setLeftArmPoseAtEntityArmorStand;
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
                    methodNameMap.put("v1_20_R2", "d");
                    methodNameMap.put("v1_20_R3", "d");

                    String methodName = methodNameMap.get(version.getVersion());
                    if (methodName != null) setRightArmPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName, Vector3f());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setRightArmPoseAtEntityArmorStand;
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
                    fieldNameMap.put("v1_20_R2", "ce");
                    fieldNameMap.put("v1_20_R3", "ce");

                    leftArmPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return leftArmPoseAtEntityArmorStand;
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
                    fieldNameMap.put("v1_20_R2", "cf");
                    fieldNameMap.put("v1_20_R3", "cf");

                    rightArmPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return rightArmPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setLeftLegPose(Vector3f)
     */
    private Method setLeftLegPoseAtEntityArmorStand;
    public Method EntityArmorStand_setLeftLegPose() {
        if (setLeftLegPoseAtEntityArmorStand == null) {
            try {
                setLeftLegPoseAtEntityArmorStand = EntityArmorStand().getMethod("setLeftLegPose", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "e");
                    methodNameMap.put("v1_13_R1", "e");
                    methodNameMap.put("v1_13_R2", "e");
                    methodNameMap.put("v1_14_R1", "e");
                    methodNameMap.put("v1_15_R1", "e");
                    methodNameMap.put("v1_16_R1", "e");
                    methodNameMap.put("v1_16_R2", "e");
                    methodNameMap.put("v1_16_R3", "e");
                    methodNameMap.put("v1_17_R1", "e");
                    methodNameMap.put("v1_18_R1", "e");
                    methodNameMap.put("v1_18_R2", "e");
                    methodNameMap.put("v1_19_R1", "e");
                    methodNameMap.put("v1_19_R2", "e");
                    methodNameMap.put("v1_19_R3", "e");
                    methodNameMap.put("v1_20_R1", "e");
                    methodNameMap.put("v1_20_R2", "e");
                    methodNameMap.put("v1_20_R3", "e");

                    String methodName = methodNameMap.get(version.getVersion());
                    if (methodName != null) setLeftLegPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName, Vector3f());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setLeftLegPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#setRightLegPose(Vector3f)
     */
    private Method setRightLegPoseAtEntityArmorStand;
    public Method EntityArmorStand_setRightLegPose() {
        if (setRightLegPoseAtEntityArmorStand == null) {
            try {
                setRightLegPoseAtEntityArmorStand = EntityArmorStand().getMethod("setRightLegPose", Vector3f());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "f");
                    methodNameMap.put("v1_13_R1", "f");
                    methodNameMap.put("v1_13_R2", "f");
                    methodNameMap.put("v1_14_R1", "f");
                    methodNameMap.put("v1_15_R1", "f");
                    methodNameMap.put("v1_16_R1", "f");
                    methodNameMap.put("v1_16_R2", "f");
                    methodNameMap.put("v1_16_R3", "f");
                    methodNameMap.put("v1_17_R1", "f");
                    methodNameMap.put("v1_18_R1", "f");
                    methodNameMap.put("v1_18_R2", "f");
                    methodNameMap.put("v1_19_R1", "f");
                    methodNameMap.put("v1_19_R2", "f");
                    methodNameMap.put("v1_19_R3", "f");
                    methodNameMap.put("v1_20_R1", "f");
                    methodNameMap.put("v1_20_R2", "f");
                    methodNameMap.put("v1_20_R3", "f");

                    String methodName = methodNameMap.get(version.getVersion());
                    if (methodName != null) setRightLegPoseAtEntityArmorStand = EntityArmorStand().getMethod(methodName, Vector3f());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setRightLegPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#leftLegPose
     */
    private Field leftLegPoseAtEntityArmorStand;
public Field EntityArmorStand_leftLegPose() {
        if (leftLegPoseAtEntityArmorStand == null) {
            try {
                leftLegPoseAtEntityArmorStand = EntityArmorStand().getField("leftLegPose");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "bH");
                    fieldNameMap.put("v1_13_R1", "bN");
                    fieldNameMap.put("v1_13_R2", "bN");
                    fieldNameMap.put("v1_14_R1", "bJ");
                    fieldNameMap.put("v1_15_R1", "bG");
                    fieldNameMap.put("v1_16_R1", "bF");
                    fieldNameMap.put("v1_16_R2", "bA");
                    fieldNameMap.put("v1_16_R3", "bA");
                    fieldNameMap.put("v1_17_R1", "ck");
                    fieldNameMap.put("v1_18_R1", "ck");
                    fieldNameMap.put("v1_18_R2", "ck");
                    fieldNameMap.put("v1_19_R1", "ck");
                    fieldNameMap.put("v1_19_R2", "ck");
                    fieldNameMap.put("v1_19_R3", "cf");
                    fieldNameMap.put("v1_20_R1", "cg");
                    fieldNameMap.put("v1_20_R2", "cg");
                    fieldNameMap.put("v1_20_R3", "cg");

                    leftLegPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return leftLegPoseAtEntityArmorStand;
    }

    /**
     * EntityArmorStand#rightLegPose
     */
    private Field rightLegPoseAtEntityArmorStand;
public Field EntityArmorStand_rightLegPose() {
        if (rightLegPoseAtEntityArmorStand == null) {
            try {
                rightLegPoseAtEntityArmorStand = EntityArmorStand().getField("rightLegPose");
            } catch (NoSuchFieldException ignored) {
                try {
                    Map<String, String> fieldNameMap = new HashMap<>();
                    fieldNameMap.put("v1_12_R1", "bI");
                    fieldNameMap.put("v1_13_R1", "bO");
                    fieldNameMap.put("v1_13_R2", "bO");
                    fieldNameMap.put("v1_14_R1", "bK");
                    fieldNameMap.put("v1_15_R1", "bH");
                    fieldNameMap.put("v1_16_R1", "bG");
                    fieldNameMap.put("v1_16_R2", "bB");
                    fieldNameMap.put("v1_16_R3", "bB");
                    fieldNameMap.put("v1_17_R1", "cl");
                    fieldNameMap.put("v1_18_R1", "cl");
                    fieldNameMap.put("v1_18_R2", "cl");
                    fieldNameMap.put("v1_19_R1", "cl");
                    fieldNameMap.put("v1_19_R2", "cl");
                    fieldNameMap.put("v1_19_R3", "cg");
                    fieldNameMap.put("v1_20_R1", "ch");
                    fieldNameMap.put("v1_20_R2", "ch");
                    fieldNameMap.put("v1_20_R3", "ch");

                    rightLegPoseAtEntityArmorStand = EntityArmorStand().getField(fieldNameMap.get(version.getVersion()));
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return rightLegPoseAtEntityArmorStand;
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return WorldClass;
    }

    /**
     * CraftWorld
     */
    private Class<?> CraftWorldClass;
    public Class<?> CraftWorld() {
        if (CraftWorldClass == null) {
            try {
                CraftWorldClass = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".CraftWorld");
            } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
        }
        return CraftWorldClass;
    }

    /**
     * CraftWorld#getHandle()
     */
    private Method getHandleAtCraftWorld;
    public Method CraftWorld_getHandle() {
        if (getHandleAtCraftWorld == null) {
            try {
                getHandleAtCraftWorld = CraftWorld().getMethod("getHandle");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return getHandleAtCraftWorld;
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                    Vector3fClass = Class.forName("net.minecraft.core.Vector3f");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return Vector3fClass;
    }

    /**
     * Vector3f#Vector3f(float, float, float) / Vector3f#Vector3f(double, double, double)
     */
    private Constructor<?> Vector3fConstructor;
    public Constructor<?> Vector3f_Constructor() {
        if (Vector3fConstructor == null) {
            try {
                Vector3fConstructor = Vector3f().getConstructor(float.class, float.class, float.class);
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return Vector3fConstructor;
    }

    /**
     * Vector3f#getX()
     */
    private Method getXAtVector3f;
    public Method Vector3f_getX() {
        if (getXAtVector3f == null) {
            try {
                getXAtVector3f = Vector3f().getMethod("getX");
            } catch (NoSuchMethodException ignored) {
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
                    methodNameMap.put("v1_20_R2", "b");
                    methodNameMap.put("v1_20_R3", "b");

                    getXAtVector3f = Vector3f().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getXAtVector3f;
    }

    /**
     * Vector3f#getY()
     */
    private Method getYAtVector3f;
    public Method Vector3f_getY() {
        if (getYAtVector3f == null) {
            try {
                getYAtVector3f = Vector3f().getMethod("getY");
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
                    methodNameMap.put("v1_20_R2", "c");
                    methodNameMap.put("v1_20_R3", "c");

                    getYAtVector3f = Vector3f().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getYAtVector3f;
    }

    /**
     * Vector3f#getZ()
     */
    private Method getZAtVector3f;
    public Method Vector3f_getZ() {
        if (getZAtVector3f == null) {
            try {
                getZAtVector3f = Vector3f().getMethod("getZ");
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
                    methodNameMap.put("v1_20_R2", "d");
                    methodNameMap.put("v1_20_R3", "d");

                    getZAtVector3f = Vector3f().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getZAtVector3f;
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
                serializeAtChatSerializer = ChatSerializer().getMethod("a", String.class);
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
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
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
                    EnumHand_MAIN_HAND = EnumHand().getField("a").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                    EnumHand_OFF_HAND = EnumHand().getField("b").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EnumItemSlotClass;
    }

    /**
     * EnumItemSlot : MAINHAND
     */
    private Object MAINHANDAtEnumItemSlot;
    public Object EnumItemSlot_MAINHAND() {
        if (MAINHANDAtEnumItemSlot == null) {
            try {
                MAINHANDAtEnumItemSlot = EnumItemSlot().getField("MAINHAND").get(null);
            } catch (Exception ignored) {
                try {
                    MAINHANDAtEnumItemSlot = EnumItemSlot().getField("a").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return MAINHANDAtEnumItemSlot;
    }

    /**
     * EnumItemSlot : CHEST
     */
    private Object OFFHANDAtEnumItemSlot;
    public Object EnumItemSlot_OFFHAND() {
        if (OFFHANDAtEnumItemSlot == null) {
            try {
                OFFHANDAtEnumItemSlot = EnumItemSlot().getField("CHEST").get(null);
            } catch (Exception ignored) {
                try {
                    OFFHANDAtEnumItemSlot = EnumItemSlot().getField("b").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return OFFHANDAtEnumItemSlot;
    }

    /**
     * EnumItemSlot : FEET
     */
    private Object FEETAtEnumItemSlot;
    public Object EnumItemSlot_FEET() {
        if (FEETAtEnumItemSlot == null) {
            try {
                FEETAtEnumItemSlot = EnumItemSlot().getField("FEET").get(null);
            } catch (Exception ignored) {
                try {
                    FEETAtEnumItemSlot = EnumItemSlot().getField("c").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return FEETAtEnumItemSlot;
    }

    /**
     * EnumItemSlot : LEGS
     */
    private Object LEGSAtEnumItemSlot;
    public Object EnumItemSlot_LEGS() {
        if (LEGSAtEnumItemSlot == null) {
            try {
                LEGSAtEnumItemSlot = EnumItemSlot().getField("LEGS").get(null);
            } catch (Exception ignored) {
                try {
                    LEGSAtEnumItemSlot = EnumItemSlot().getField("d").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return LEGSAtEnumItemSlot;
    }

    /**
     * EnumItemSlot : CHEST
     */
    private Object CHESTAtEnumItemSlot;
    public Object EnumItemSlot_CHEST() {
        if (CHESTAtEnumItemSlot == null) {
            try {
                CHESTAtEnumItemSlot = EnumItemSlot().getField("CHEST").get(null);
            } catch (Exception ignored) {
                try {
                    CHESTAtEnumItemSlot = EnumItemSlot().getField("e").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return CHESTAtEnumItemSlot;
    }

    /**
     * EnumItemSlot : HEAD
     */
    private Object EnumItemSlot_HEAD;
    public Object EnumItemSlot_HEAD() {
        if (EnumItemSlot_HEAD == null) {
            try {
                EnumItemSlot_HEAD = EnumItemSlot().getField("HEAD").get(null);
            } catch (Exception ignored) {
                try {
                    EnumItemSlot_HEAD = EnumItemSlot().getField("f").get(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ItemStackClass;
    }

    /**
     * ItemStack#getItem()
     */
    private Method getItemInItemStack;
    public Method ItemStack_getItem() {
        if (getItemInItemStack == null) {
            try {
                getItemInItemStack = ItemStack().getMethod("getItem");
            } catch (NoSuchMethodException e) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "c");
                    methodNameMap.put("v1_13_R1", "b");
                    methodNameMap.put("v1_13_R2", "b");
                    methodNameMap.put("v1_14_R1", "b");
                    methodNameMap.put("v1_15_R1", "b");
                    methodNameMap.put("v1_16_R2", "b");
                    methodNameMap.put("v1_16_R3", "b");
                    methodNameMap.put("v1_17_R1", "c");
                    methodNameMap.put("v1_18_R1", "c");
                    methodNameMap.put("v1_18_R2", "c");
                    methodNameMap.put("v1_19_R1", "c");
                    methodNameMap.put("v1_19_R2", "c");
                    methodNameMap.put("v1_19_R3", "c");
                    methodNameMap.put("v1_20_R1", "d");
                    methodNameMap.put("v1_20_R2", "d");
                    methodNameMap.put("v1_20_R3", "d");

                    getItemInItemStack = ItemStack().getMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getItemInItemStack;
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
                    methodNameMap.put("v1_20_R1", "v");
                    methodNameMap.put("v1_20_R2", "v");
                    methodNameMap.put("v1_20_R3", "v");

                    ItemStackGetTagMethod = ItemStack().getDeclaredMethod(methodNameMap.get(version.getVersion()));
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ItemStackGetTagMethod;
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
                    methodNameMap.put("v1_20_R2", "c");
                    methodNameMap.put("v1_20_R3", "c");

                    ItemStackSetTagMethod = ItemStack().getDeclaredMethod(methodNameMap.get(version.getVersion()), NBTTagCompound());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ItemStackSetTagMethod;
    }

    /**
     * CraftItemStack
     */
    private Class<?> CraftItemStackClass;
    public Class<?> CraftItemStack() {
        if (CraftItemStackClass == null) {
            try {
                CraftItemStackClass = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".inventory.CraftItemStack");
            } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
        }
        return CraftItemStackClass;
    }

    /**
     * Tag
     */
    private Class<?> TagClass;
    public Class<?> Tag() {
        if (TagClass == null) {
            try {
                TagClass = Class.forName(nmsPackage + ".NBTBase");
            } catch (Exception ignored) {
                try {
                    TagClass = Class.forName("net.minecraft.nbt.NBTBase");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return TagClass;
    }

    /**
     * NBTTagCompound
     */
    private Class<?> NBTTagCompoundClass;
    public Class<?> NBTTagCompound() {
        if (TagClass == null) {
            try {
                TagClass = Class.forName(nmsPackage + ".NBTTagCompound");
            } catch (Exception ignored) {
                try {
                    TagClass = Class.forName("net.minecraft.nbt.NBTTagCompound");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return TagClass;
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
                    methodNameMap.put("v1_20_R2", "l");
                    methodNameMap.put("v1_20_R3", "l");

                    getStringAtNBTTagCompound = NBTTagCompound().getDeclaredMethod(methodNameMap.get(version.getVersion()), String.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getStringAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#putString(String, String)
     */
    private Method putStringAtNBTTagCompound;
    public Method NBTTagCompound_putString() {
        if (putStringAtNBTTagCompound == null) {
            try {
                putStringAtNBTTagCompound = NBTTagCompound().getMethod("putString", String.class, String.class);
            } catch (Exception ignored) {
                try {
                    putStringAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, String.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return putStringAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#putUUID(String, UUID)
     */
    private Method putUUIDAtNBTTagCompound;
    public Method NBTTagCompound_putUUID() {
        if (putUUIDAtNBTTagCompound == null) {
            try {
                putUUIDAtNBTTagCompound = NBTTagCompound().getMethod("putUUID", String.class, UUID.class);
            } catch (Exception ignored) {
                try {
                    putUUIDAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, UUID.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return putUUIDAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#putInt(String, int)
     */
    private Method putIntAtNBTTagCompound;
    public Method NBTTagCompound_putInt() {
        if (putIntAtNBTTagCompound == null) {
            try {
                putIntAtNBTTagCompound = NBTTagCompound().getMethod("putInt", String.class, int.class);
            } catch (Exception ignored) {
                try {
                    putIntAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, int.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return putIntAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#putByte(String, byte)
     */
    private Method putByteAtNBTTagCompound;
    public Method NBTTagCompound_putByte() {
        if (putByteAtNBTTagCompound == null) {
            try {
                putByteAtNBTTagCompound = NBTTagCompound().getMethod("putByte", String.class, byte.class);
            } catch (Exception ignored) {
                try {
                    putByteAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, byte.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return putByteAtNBTTagCompound;
    }

    /**
     * NBTTagCompound#put(String, Tag)
     */
    private Method putAtNBTTagCompound;
    public Method NBTTagCompound_put() {
        if (putAtNBTTagCompound == null) {
            try {
                putAtNBTTagCompound = NBTTagCompound().getMethod("put", String.class, Tag());
            } catch (Exception ignored) {
                try {
//                    putAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, Tag());
                    putAtNBTTagCompound = NBTTagCompound().getDeclaredMethod("a", String.class, Class.forName("net.minecraft.nbt.NBTBase"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return putAtNBTTagCompound;
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ItemClass;
    }

    /**
     * Item#getDescriptionId(ItemStack)
     */
    private Method getDescriptionIdAtItem;
    public Method Item_getDescriptionId() {
        if (getDescriptionIdAtItem == null) {
            try {
                getDescriptionIdAtItem = Item().getMethod("getDescriptionId", ItemStack());
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
                    methodNameMap.put("v1_20_R2", "j");
                    methodNameMap.put("v1_20_R3", "j");

                    getDescriptionIdAtItem = Item().getMethod(methodNameMap.get(version.getVersion()), ItemStack());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return getDescriptionIdAtItem;
    }

    /**
     * Item#use(World, EntityHuman, EnumHand)
     */
    private Method useAtItem;
    public Method Item_use() {
        if (useAtItem == null) {
            try {
                useAtItem = Item().getMethod("use", World(), EntityHuman(), EnumHand());
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
                    methodNameMap.put("v1_20_R2", "a");
                    methodNameMap.put("v1_20_R3", "a");

                    useAtItem = Item().getMethod(methodNameMap.get(version.getVersion()), World(), EntityHuman(), EnumHand());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return useAtItem;
    }

    /**
     * EntityItem
     */
    private Class<?> EntityItemClass;
    public Class<?> EntityItem() {
        if (EntityItemClass == null) {
            try {
                EntityItemClass = Class.forName(nmsPackage + ".EntityItem");
            } catch (Exception ignored) {
                try {
                    EntityItemClass = Class.forName("net.minecraft.world.entity.item.EntityItem");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return EntityItemClass;
    }

    /**
     * EntityItem#EntityItem(World, double, double, ItemStack)
     */
    private Constructor EntityItemConstructor;
    public Constructor EntityItem_Constructor() {
        if (EntityItemConstructor == null) {
            try {
                EntityItemConstructor = EntityItem().getConstructor(World(), double.class, double.class, double.class, ItemStack());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return EntityItemConstructor;
    }

    /**
     * EntityItem#setItem(ItemStack)
     */
    private Method setItemAtEntityItem;
    public Method EntityItem_setItem() {
        if (setItemAtEntityItem == null) {
            try {
                setItemAtEntityItem = EntityItem().getMethod("setItem", ItemStack());
            } catch (NoSuchMethodException ignored) {
                try {
                    Map<String, String> methodNameMap = new HashMap<>();
                    methodNameMap.put("v1_12_R1", "a");
                    methodNameMap.put("v1_13_R1", "b");
                    methodNameMap.put("v1_13_R2", "b");
                    methodNameMap.put("v1_14_R1", "b");
                    methodNameMap.put("v1_15_R1", "b");
                    methodNameMap.put("v1_16_R1", "b");
                    methodNameMap.put("v1_16_R2", "b");
                    methodNameMap.put("v1_16_R3", "b");
                    methodNameMap.put("v1_17_R1", "a");
                    methodNameMap.put("v1_18_R1", "a");
                    methodNameMap.put("v1_18_R2", "a");
                    methodNameMap.put("v1_19_R1", "a");
                    methodNameMap.put("v1_19_R2", "a");
                    methodNameMap.put("v1_19_R3", "a");
                    methodNameMap.put("v1_20_R1", "a");
                    methodNameMap.put("v1_20_R2", "a");
                    methodNameMap.put("v1_20_R3", "a");

                    setItemAtEntityItem = EntityItem().getMethod(methodNameMap.get(version.getVersion()), ItemStack());
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return setItemAtEntityItem;
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
                PacketPlayOutSpawnEntityConstructor = Class.forName(nmsPackage + ".PacketPlayOutSpawnEntity").getConstructor(Entity());
            } catch (Exception ignored) {
                try {
                    PacketPlayOutSpawnEntityConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity").getConstructor(Entity());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return PacketPlayOutEntityEquipmentConstructor;
    }

    /**
     * PacketPlayOutEntityHeadRotation
     */
    private Constructor<?> PacketPlayOutEntityHeadRotationConstructor;
    public Constructor<?> PacketPlayOutEntityHeadRotation_Constructor() {
        if (PacketPlayOutEntityHeadRotationConstructor == null) {
            try {
                PacketPlayOutEntityHeadRotationConstructor = Class.forName(nmsPackage + ".PacketPlayOutEntityHeadRotation").getConstructor(Entity(), byte.class);
            } catch (Exception ignored) {
                try {
                    PacketPlayOutEntityHeadRotationConstructor = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation").getConstructor(Entity(), byte.class);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return PacketPlayOutEntityHeadRotationConstructor;
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return PacketPlayOutEntityMetadataConstructor;
    }

    /**
     * PacketPlayOutAttachEntity
     */
    private Constructor<?> PacketPlayOutAttachEntityConstructor;
    public Constructor<?> PacketPlayOutAttachEntity_Constructor() {
        if (PacketPlayOutAttachEntityConstructor == null) {
            try {
                PacketPlayOutAttachEntityConstructor = Class.forName(nmsPackage + ".PacketPlayOutAttachEntity").getConstructor(Entity(), Entity());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return PacketPlayOutAttachEntityConstructor;
    }

    /**
     * PacketPlayOutMount
     */
    private Constructor<?> PacketPlayOutMountConstructor;
    public Constructor<?> PacketPlayOutMount_Constructor() {
        if (PacketPlayOutMountConstructor == null) {
            try {
                PacketPlayOutMountConstructor = Class.forName(nmsPackage + ".PacketPlayOutMount").getConstructor(Entity());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return PacketPlayOutMountConstructor;
    }

    /* MOB
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    private Class<?> CraftMobClass;
    public Class<?> CraftMob() {
        if (CraftMobClass == null) {
            try {
                CraftMobClass = Class.forName("org.bukkit.craftbukkit.entity.CraftMob");
            } catch (Exception ignored) {
                try {
                    CraftMobClass = Class.forName("org.bukkit.craftbukkit." + version.getVersion() + ".entity.CraftMob");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return CraftMobClass;
    }

    private Method getHandleAtCraftMob;
    public Method CraftMob_getHandle() {
        if (getHandleAtCraftMob == null) {
            try {
                getHandleAtCraftMob = CraftMob().getMethod("getHandle");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return getHandleAtCraftMob;
    }

    private Class<?> MobClass;
    public Class<?> Mob() {
        if (MobClass == null) {
            try {
                MobClass = Class.forName(nmsPackage + ".EntityInsentient");
            } catch (Exception ignored) {
                try {
                    MobClass = Class.forName("net.minecraft.world.entity.EntityInsentient");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return MobClass;
    }

    /* UTIL
     ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    public ArmorStandWrapper createArmorStandWrapper(Location location) {
        try {
            FeatherLocation featherLocation = toFeatherLocation(location);
            Object entityArmorStand = EntityArmorStand_Constructor()
                    .newInstance(
                            featherLocation.getWorld().getWorld(),
                            location.getX(), location.getY(), location.getZ()
                    );
            Object entityId = Entity_getId().invoke(entityArmorStand);
            return new ArmorStandWrapper(entityId == null ? 0 : (int) entityId, featherLocation, entityArmorStand);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public EntityItemWrapper createEntityItemWrapper(Location location, ItemStack itemStack) {
        try {
            FeatherLocation featherLocation = toFeatherLocation(location);
            Object entityItem = EntityItem_Constructor()
                    .newInstance(
                            featherLocation.getWorld().getWorld(),
                            location.getX(), location.getY(), location.getZ(),
                            NmsItemStackUtil.getInstance().asNMSCopy(itemStack).getNmsItemStack()
                    );
            Object entityId = Entity_getId().invoke(entityItem);

            return new EntityItemWrapper(entityId == null ? 0 : (int) entityId, featherLocation, entityItem);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public WorldWrapper toNMSWorld(World world) {
        try {
            return new WorldWrapper(world, CraftWorld_getHandle().invoke(CraftWorld().cast(world)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public FeatherLocation toFeatherLocation(Location location) {
        try {
            WorldWrapper worldWrapper = toNMSWorld(location.getWorld());
            return new FeatherLocation(worldWrapper, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Object toVersionString(String string) {
        try {
            return ChatSerializer_serialize().invoke(null, "{\"text\":\"" + string + "\"}");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void sendPacket(List<Player> targets, Constructor<?> packetConstructor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object packet = args.length == 0 ? packetConstructor.newInstance() : packetConstructor.newInstance(args);
        for (Player target : targets) {
            PlayerConnection_sendPacket().invoke(
                    EntityPlayer_playerConnection().get(CraftPlayer_getHandle().invoke(target)),
                    packet
            );
        }
    }

    public void sendPacket(Player target, Constructor<?> packetConstructor, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object packet = args.length == 0 ? packetConstructor.newInstance() : packetConstructor.newInstance(args);
        PlayerConnection_sendPacket().invoke(
                EntityPlayer_playerConnection().get(CraftPlayer_getHandle().invoke(CraftPlayer().cast(target))),
                packet
        );
    }
}