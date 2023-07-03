package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;

import java.lang.reflect.Method;

public class NmsOtherUtil {

    private static NmsOtherUtil instance;

    public static NmsOtherUtil getInstance() {
        if (instance == null) instance = new NmsOtherUtil();
        return instance;
    }

    private NmsOtherUtil() {}

    private final Version version = VersionController.getInstance().getVersion();
    private final String nmsPackage = "net.minecraft.server." + version.name();

    private Class<?> WorldClass;
    public Class<?> getWorldClass() {
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

    private Class<?> EntityHumanClass;
    public Class<?> getEntityHumanClass() {
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

    private Class<?> EnumHandClass;
    public Class<?> getEnumHandClass() {
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

    private Class<?> CraftPlayerClass;
    public Class<?> getCraftPlayerClass() {
        if (CraftPlayerClass == null) {
            try {
                CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version.name() + ".entity.CraftPlayer");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return CraftPlayerClass;
    }

    private Method getHandleAtCraftPlayer;
    public Method getHandleAtPlayer() {
        if (getHandleAtCraftPlayer == null) {
            try {
                getHandleAtCraftPlayer = CraftPlayerClass.getMethod("getHandle");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        return getHandleAtCraftPlayer;
    }
}
