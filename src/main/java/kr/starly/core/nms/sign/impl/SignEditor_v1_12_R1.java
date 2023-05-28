package kr.starly.core.nms.sign.impl;

import kr.starly.core.nms.sign.SignEditor;
import kr.starly.core.nms.version.Version;
import kr.starly.core.nms.version.VersionController;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SignEditor_v1_12_R1 implements SignEditor {

    private static final String NMS_PACKAGE = "net.minecraft.server";
    private static final String CRAFTBUKKIT_PACKAGE = "org.bukkit.craftbukkit";

    private static final Class<?> CLASS_TILE_ENTITY_SIGN;
    private static final Class<?> CLASS_PACKET_PLAY_OUT_OPEN_SIGN_EDITOR;
    private static final Class<?> CLASS_CHAT_COMPONENT_TEXT;
    private static final Class<?> CLASS_PACKET;
    private static final Class<?> CLASS_TILE_ENTITY;
    private static final Class<?> CLASS_PLAYER_CONNECTION;
    private static final Class<?> CLASS_I_CHAT_BASE_COMPONENT;
    private static final Class<?> CLASS_WORLD;
    private static final Class<?> CLASS_BLOCK_POSITION;

    private static final Method METHOD_GET_HANDLE;
    private static final Method METHOD_GET_TILE_ENTITY;
    private static final Method METHOD_SET_LINE;
    private static final Method METHOD_UPDATE;

    private static final Field FIELD_PLAYER_CONNECTION;

    static {
        try {
            Version version = VersionController.getInstance().getVersion();

            String nmsPackage = NMS_PACKAGE + "." + version.name();
            String craftbukkitPackage = CRAFTBUKKIT_PACKAGE + "." + version.name();

            CLASS_TILE_ENTITY_SIGN = Class.forName(nmsPackage + ".TileEntitySign");
            CLASS_PACKET_PLAY_OUT_OPEN_SIGN_EDITOR = Class.forName(nmsPackage + ".PacketPlayOutOpenSignEditor");
            CLASS_CHAT_COMPONENT_TEXT = Class.forName(nmsPackage + ".ChatComponentText");
            CLASS_PACKET = Class.forName(nmsPackage + ".Packet");
            CLASS_TILE_ENTITY = Class.forName(nmsPackage + ".TileEntity");
            CLASS_PLAYER_CONNECTION = Class.forName(nmsPackage + ".PlayerConnection");
            CLASS_I_CHAT_BASE_COMPONENT = Class.forName(nmsPackage + ".IChatBaseComponent");
            CLASS_WORLD = Class.forName(nmsPackage + ".World");
            CLASS_BLOCK_POSITION = Class.forName(nmsPackage + ".BlockPosition");

            Class<?> craftPlayerClass = Class.forName(craftbukkitPackage + ".entity.CraftPlayer");
            METHOD_GET_HANDLE = craftPlayerClass.getMethod("getHandle");
            Class<?> entityPlayerClass = Class.forName(nmsPackage + ".EntityPlayer");
            METHOD_GET_TILE_ENTITY = CLASS_WORLD.getMethod("getTileEntity", int.class, int.class, int.class);
            METHOD_SET_LINE = CLASS_TILE_ENTITY_SIGN.getMethod("a", int.class, CLASS_I_CHAT_BASE_COMPONENT);
            METHOD_UPDATE = CLASS_TILE_ENTITY.getMethod("update");

            FIELD_PLAYER_CONNECTION = entityPlayerClass.getDeclaredField("playerConnection");
            FIELD_PLAYER_CONNECTION.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException exception) {
            throw new RuntimeException("표지판이 등록되지 않았습니다.", exception);
        }
    }

    public void open(Player player, String... lines) {
        try {
            Object craftPlayer = CraftPlayer.class.cast(player);
            Object entityPlayer = METHOD_GET_HANDLE.invoke(craftPlayer);
            Object playerConnection = FIELD_PLAYER_CONNECTION.get(entityPlayer);

            Location location = player.getLocation();
            Block block = location.getBlock();

            Object tileEntity = METHOD_GET_TILE_ENTITY.invoke(entityPlayer, block.getX(), block.getY(), block.getZ());

            if (tileEntity != null && CLASS_TILE_ENTITY_SIGN.isInstance(tileEntity)) {
                Object tileSign = CLASS_TILE_ENTITY_SIGN.cast(tileEntity);

                for (int i = 0; i < lines.length; i++) {
                    Object chatComponentText = CLASS_CHAT_COMPONENT_TEXT.getConstructor(String.class).newInstance(lines[i]);
                    METHOD_SET_LINE.invoke(tileSign, i, chatComponentText);
                }

                METHOD_UPDATE.invoke(tileSign);

                Object packetOpenSignEditor = CLASS_PACKET_PLAY_OUT_OPEN_SIGN_EDITOR.getConstructor(CLASS_TILE_ENTITY).newInstance(tileSign);
                playerConnection.getClass().getMethod("sendPacket", CLASS_PACKET).invoke(playerConnection, packetOpenSignEditor);
            } else {
                throw new IllegalArgumentException("표지판을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

