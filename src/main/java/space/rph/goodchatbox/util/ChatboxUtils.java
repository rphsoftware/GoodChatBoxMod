package space.rph.goodchatbox.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ChatboxUtils {
    private static boolean checkDistance(EntityPlayer player, double x, double y, double z, double range) {
        return (player.getDistance(x, y, z) <= range);
    }

    private static boolean checkDimension(EntityPlayer player, TileEntity te) {
        return (player.worldObj.provider.dimensionId == te.getWorldObj().provider.dimensionId);
    }

    public static boolean complexChecks(EntityPlayer player, TileEntity te, double range, boolean creative, boolean dimensional) {
        if (creative || (range == Double.POSITIVE_INFINITY)) {
            return true;
        }
        if (!dimensional) {
            if (!checkDimension(player, te)) {
                return false;
            }
        }
        double x = te.xCoord;
        double y = te.yCoord;
        double z = te.zCoord;
        return checkDistance(player, x, y, z, range);

    }

    public static void say(TileEntity source, double range, String message, boolean creative, boolean dimensional) {
        message = message + EnumChatFormatting.RESET;
        StringBuilder builder = new StringBuilder();
        builder.append("X: ");
        builder.append(source.xCoord);
        builder.append(" Y: ");
        builder.append(source.yCoord);
        builder.append(" Z: ");
        builder.append(source.zCoord);
        ChatStyle style = new ChatStyle();
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(builder.toString()));

        style.setChatHoverEvent(event);
        ChatComponentText component = new ChatComponentText(message);
        component.setChatStyle(style);
        try {
            for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (!(o instanceof EntityPlayer)) {
                    continue;
                }
                EntityPlayer player = (EntityPlayer) o;

                if (complexChecks(player, source, range, creative, dimensional)) {
                    player.addChatMessage(component.createCopy());
                }
            }
        } catch(Exception e) {
            // Oof ouch oowie
        }
    }

    public static void tell(TileEntity source, EntityPlayer player, double range, String message, boolean creative, boolean dimensional) {
        message = message + EnumChatFormatting.RESET;
        ChatComponentText component = new ChatComponentText(message);
        if (complexChecks(player, source, range, creative, dimensional)) {
            player.addChatMessage(component.createCopy());
        }
    }
}
