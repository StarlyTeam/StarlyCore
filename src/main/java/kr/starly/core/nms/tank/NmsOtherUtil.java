package kr.starly.core.nms.tank;

import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;

import java.lang.reflect.Method;

public class NmsOtherUtil {

    private static final Version version = VersionController.getInstance().getVersion();
    private static final String nmsPackage = "net.minecraft.server." + version.name();

    public NmsOtherUtil() {}

    private static Class<?> WorldClass;
    private static Class<?> CraftWorldClass;
    private static Method getHandleAtWorld;
    public static Class<?> getWorldClass() throws ClassNotFoundException {
        try {
            WorldClass = Class.forName(nmsPackage + ".World");
        } catch (ClassNotFoundException ignored) {
            try {
                WorldClass = Class.forName("net.minecraft.world.level.World");
            } catch (ClassNotFoundException ignored2) {
                throw new ClassNotFoundException("WorldClass not found");
            }
        }
        return WorldClass;
    }

    private static Class<?> EntityHumanClass;
    public static Class<?> getEntityHumanClass() throws ClassNotFoundException {
        try {
            EntityHumanClass = Class.forName(nmsPackage + ".EntityHuman");
        } catch (ClassNotFoundException ignored) {
            try {
                EntityHumanClass = Class.forName("net.minecraft.world.entity.player.EntityHuman");
            } catch (ClassNotFoundException ignored2) {
                throw new ClassNotFoundException("EntityHumanClass not found");
            }
        }
        return EntityHumanClass;
    }

    private static Class<?> EnumHandClass;
    public static Class<?> getEnumHandClass() throws ClassNotFoundException {
        try {
            EnumHandClass = Class.forName(nmsPackage + ".EnumHand");
        } catch (ClassNotFoundException ignored) {
            try {
                EnumHandClass = Class.forName("net.minecraft.world.EnumHand");
            } catch (ClassNotFoundException ignored2) {
                throw new ClassNotFoundException("EnumHandClass not found");
            }
        }
        return EnumHandClass;
    }

    private static Method aMethod;
    public static Method getAMethod() throws ClassNotFoundException, NoSuchMethodException {
        try {
            Class<?> NmsItemClass = Class.forName(nmsPackage + ".NmsItem");
            Class<?> worldClass = getWorldClass();
            Class<?> entityHumanClass = getEntityHumanClass();
            Class<?> enumHandClass = getEnumHandClass();

            aMethod = NmsItemClass.getMethod("a", worldClass, entityHumanClass, enumHandClass);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw e;
        }
        return aMethod;
    }

    private static Class<?> CraftPlayerClass;
    public static Class<?> getCraftPlayerClass() throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + version.name() + ".entity.CraftPlayer");
    }

    public static Method getHandleAtPlayer() throws NoSuchMethodException {
        return CraftPlayerClass.getMethod("getHandle");
    }
}
