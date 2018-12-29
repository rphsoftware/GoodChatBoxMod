package space.rph.goodchatbox.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;

import space.rph.goodchatbox.util.*;

import java.util.ArrayList;
import java.util.List;

public class TileEntityChatbox extends TileEntity implements Environment {

    public double getRange() { return  64;}
    public boolean getInterdimensional() { return false;}
    public boolean getAllowLowLevel() { return false;}
    public boolean getAllowExtended() { return false;}

    public ComponentConnector node = Network.newNode(this, Visibility.Network)
            .withComponent(getComponentName())
            .withConnector(32)
            .create();

    public TileEntityChatbox() {
        super();
    }
    public String getComponentName() {
        return "good_chatbox";
    }

    @SuppressWarnings("Duplicates")
    private void log(String eventName, String content, String target) {
        StringBuilder builder = new StringBuilder();
        builder.append(eventName);
        builder.append("@");
        builder.append("X:");
        builder.append(this.xCoord);
        builder.append("Y:");
        builder.append(this.yCoord);
        builder.append("Z:");
        builder.append(this.zCoord);
        builder.append(" Dimension: ");
        builder.append(this.getWorldObj().provider.dimensionId);
        builder.append(" whispering to: ");
        builder.append(target);
        builder.append(" with content: ");
        builder.append(content);
        System.out.println(builder.toString());
    }


    @SuppressWarnings("Duplicates")
    private void log(String eventName, String content) {
        StringBuilder builder = new StringBuilder();
        builder.append(eventName);
        builder.append("@");
        builder.append("X:");
        builder.append(this.xCoord);
        builder.append("Y:");
        builder.append(this.yCoord);
        builder.append("Z:");
        builder.append(this.zCoord);
        builder.append(" Dimension: ");
        builder.append(this.getWorldObj().provider.dimensionId);
        builder.append(" with content: ");
        builder.append(content);
        System.out.println(builder.toString());
    }
    @SuppressWarnings("Duplicates")
    private void log(String eventName) {
        StringBuilder builder = new StringBuilder();
        builder.append(eventName);
        builder.append("@");
        builder.append("X:");
        builder.append(this.xCoord);
        builder.append("Y:");
        builder.append(this.yCoord);
        builder.append("Z:");
        builder.append(this.zCoord);
        builder.append(" Dimension: ");
        builder.append(this.getWorldObj().provider.dimensionId);
        System.out.println(builder.toString());
    }
    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 2, doc = "function(prefix:string, message:string):boolean, ?number, ?string -- Say something to the global chat with a prefix")
    public Object[] say(Context context, Arguments args) {
        if (args.count() >= 2 && args.isString(0) && args.isString(1)) {
            StringBuilder builder = new StringBuilder();
            builder.append(EnumChatFormatting.ITALIC);
            builder.append(EnumChatFormatting.DARK_GRAY);
            builder.append("[");
            builder.append(EnumChatFormatting.RESET);
            builder.append(EnumChatFormatting.GRAY);
            builder.append("Chatbox ");
            builder.append(args.checkString(0));
            builder.append(EnumChatFormatting.ITALIC);
            builder.append(EnumChatFormatting.DARK_GRAY);
            builder.append("] ");
            builder.append(EnumChatFormatting.RESET);
            builder.append(args.checkString(1));
            builder.append(EnumChatFormatting.RESET);
            String message = builder.toString();
            if (message.length() > 2048) {
                return new Object[] { false, 1, "Message too long!" };
            }
            ChatboxUtils.say(this, getRange(), message, getAllowLowLevel(), getInterdimensional());
            log("Say", args.checkString(1));
            return new Object[] { true };
        }
        return new Object[] { false, 0, "Not enough arguments or wrong types" };
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 8, doc = "function(message:string):boolean, ?number, ?string  -- CREATIVE ONLY! Say something without formatting limitation")
    public Object[] rawsay(Context context, Arguments args) {
        if (getAllowLowLevel()) {
            if (args.count() >= 1 && args.isString(0)) {
                if (args.checkString(0).length() > 2048) {
                    return new Object[]{false,2,"Message too long!"};
                }
                ChatboxUtils.say(this, getRange(), args.checkString(0), getAllowLowLevel(), getInterdimensional());
                log("RawSay");
                return new Object[] { true };
            }
            return new Object[]{false, 0, "Not enough arguments or wrong types"};
        } else {
            return new Object[]{false, 1, "Not a creative chatbox."};
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 4, doc = "function():number, boolean, boolean -- Dump range, interdimensional capability and creativity of chatbox")
    public Object[] debug(Context context, Arguments args) {
        return new Object[] { getRange(), getInterdimensional(), getAllowLowLevel() };
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 1, doc = "function(target:string, prefix:string, message:string):boolean, ?number, ?string -- Whisper something to a player (target is the username)")
    public Object[] tell(Context context, Arguments args) {
        if (args.count() >= 3 && args.isString(0) && args.isString(1) && args.isString(2)) {
            EntityPlayer resolved = null;
            for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (!(o instanceof EntityPlayer)) {
                    continue;
                }
                EntityPlayer player = (EntityPlayer) o;

                if (player.getDisplayName().equals(args.checkString(0))) {
                    resolved = player;
                    break;
                }
            }
            if (resolved == null) {
                return new Object[] { false, 1, "Invalid player" };
            }

            StringBuilder pmBuilder = new StringBuilder();
            pmBuilder.append(EnumChatFormatting.BOLD);
            pmBuilder.append(EnumChatFormatting.GRAY);
            pmBuilder.append("[");
            pmBuilder.append(EnumChatFormatting.RESET);
            pmBuilder.append(EnumChatFormatting.GOLD);
            pmBuilder.append("Private Message");
            pmBuilder.append(EnumChatFormatting.BOLD);
            pmBuilder.append(EnumChatFormatting.GRAY);
            pmBuilder.append("] ");
            pmBuilder.append(EnumChatFormatting.ITALIC);
            pmBuilder.append(EnumChatFormatting.DARK_GRAY);
            pmBuilder.append("[");
            pmBuilder.append(EnumChatFormatting.RESET);
            pmBuilder.append(EnumChatFormatting.GRAY);
            pmBuilder.append("Chatbox ");
            pmBuilder.append(args.checkString(1));
            pmBuilder.append(EnumChatFormatting.ITALIC);
            pmBuilder.append(EnumChatFormatting.DARK_GRAY);
            pmBuilder.append("] ");
            pmBuilder.append(EnumChatFormatting.RESET);
            pmBuilder.append(args.checkString(2));
            pmBuilder.append(EnumChatFormatting.RESET);
            String message = pmBuilder.toString();

            if (message.length() > 2048) {
                return new Object[] { false, 1, "Message too long!" };
            }
            ChatboxUtils.tell(this, resolved, getRange(), message, getAllowLowLevel(), getInterdimensional());
            log("Tell", args.checkString(2), args.checkString(0));
            return new Object[] { true };
        }
        return new Object[] { false, 0, "Not enough arguments or wrong types" };
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 8, doc = "function(target:string, message:string):boolean, ?number, ?string  -- CREATIVE ONLY! Whisper something to target without formatting limitation")
    public Object[] rawtell(Context context, Arguments args) {
        if (getAllowLowLevel()) {
            if (args.count() >= 2 && args.isString(0) && args.isString(1)) {
                EntityPlayer resolved = null;
                for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                    if (!(o instanceof EntityPlayer)) {
                        continue;
                    }
                    EntityPlayer player = (EntityPlayer) o;

                    if (player.getDisplayName().equals(args.checkString(0))) {
                        resolved = player;
                        break;
                    }
                }
                if (resolved == null) {
                    return new Object[] { false, 2, "Invalid player" };
                }
                if (args.checkString(1).length() > 2048) {
                    return new Object[] { false, 1, "Message too long!" };
                }
                ChatboxUtils.tell(this, resolved, getRange(), args.checkString(1), getAllowLowLevel(), getInterdimensional());
                log("RawTell");
                return new Object[] { true };
            }
            return new Object[]{false, 0, "Not enough arguments or wrong types"};
        } else {
            return new Object[]{false, 1, "Not a creative chatbox."};
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 1, doc = "function(prefix:string, message:table, ...):boolean, ?number, ?string  -- Requires elite capability; Reffer to documentatoin for content format info.")
    public Object[] advsay(Context context, Arguments args) {
        if (getAllowExtended()) {
            if (args.isString(0) && args.isTable(1)) {
                AdvsayUtils utils = new AdvsayUtils(args.checkTable(1));
                try {
                    utils.buildFromMap();
                } catch(AdvsayParsingException e) {
                    return new Object[] { false, 2, e.reason };
                }
                StringBuilder builder = new StringBuilder();
                if (!getAllowLowLevel()) {
                    builder.append(EnumChatFormatting.ITALIC);
                    builder.append(EnumChatFormatting.DARK_GRAY);
                    builder.append("[");
                    builder.append(EnumChatFormatting.RESET);
                    builder.append(EnumChatFormatting.GRAY);
                    builder.append("Chatbox ");
                    builder.append(args.checkString(0));
                    builder.append(EnumChatFormatting.ITALIC);
                    builder.append(EnumChatFormatting.DARK_GRAY);
                    builder.append("] ");
                    builder.append(EnumChatFormatting.RESET);
                }
                String message = builder.toString();

                ChatComponentText toSend = new ChatComponentText(message);
                toSend.appendSibling(utils.full);

                for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                    if (!(o instanceof EntityPlayer)) {
                        continue;
                    }
                    EntityPlayer player = (EntityPlayer) o;

                    if (ChatboxUtils.complexChecks(player, this, getRange(), getAllowLowLevel(), getInterdimensional())) {
                        player.addChatMessage(toSend.createCopy());
                    }
                }
                log("AdvSay");
                return new Object[] { true };
            } else {
                return new Object[] { false, 1, "Bad argument" };
            }
        }

        return new Object[] { false, 0, "Chatbox doesn't support this feature" };
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 1, doc = "function(target:string, prefix:string, message:table, ...):boolean, ?number, ?string  -- Requires elite capability; Reffer to documentatoin for content format info.")
    public Object[] advtell(Context context, Arguments args) {
        if (getAllowExtended()) {
            if (args.isString(0) && args.isString(1) && args.isTable(2)) {
                AdvsayUtils utils = new AdvsayUtils(args.checkTable(2));
                try {
                    utils.buildFromMap();
                } catch(AdvsayParsingException e) {
                    return new Object[] { false, 2, e.reason };
                }
                StringBuilder builder = new StringBuilder();
                if (!getAllowLowLevel()) {
                    builder.append(EnumChatFormatting.BOLD);
                    builder.append(EnumChatFormatting.GRAY);
                    builder.append("[");
                    builder.append(EnumChatFormatting.RESET);
                    builder.append(EnumChatFormatting.GOLD);
                    builder.append("Private Message");
                    builder.append(EnumChatFormatting.BOLD);
                    builder.append(EnumChatFormatting.GRAY);
                    builder.append("] ");
                    builder.append(EnumChatFormatting.ITALIC);
                    builder.append(EnumChatFormatting.DARK_GRAY);
                    builder.append("[");
                    builder.append(EnumChatFormatting.RESET);
                    builder.append(EnumChatFormatting.GRAY);
                    builder.append("Chatbox ");
                    builder.append(args.checkString(1));
                    builder.append(EnumChatFormatting.ITALIC);
                    builder.append(EnumChatFormatting.DARK_GRAY);
                    builder.append("] ");
                    builder.append(EnumChatFormatting.RESET);
                }
                String message = builder.toString();

                ChatComponentText toSend = new ChatComponentText(message);
                toSend.appendSibling(utils.full);

                EntityPlayer resolved = null;
                for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                    if (!(o instanceof EntityPlayer)) {
                        continue;
                    }
                    EntityPlayer player = (EntityPlayer) o;

                    if (player.getDisplayName().equals(args.checkString(0))) {
                        resolved = player;
                        if (ChatboxUtils.complexChecks(player, this, getRange(), getAllowLowLevel(), getInterdimensional())) {
                            break;
                        }
                    }
                }
                if (resolved == null) {
                    return new Object[] { false, 3, "Invalid player" };
                }
                log("Advtell");
                resolved.addChatComponentMessage(toSend.createCopy());
                return new Object[] { true };
            } else {
                return new Object[] { false, 1, "Bad argument" };
            }
        }

        return new Object[] { false, 0, "Chatbox doesn't support this feature" };
    }

    @SuppressWarnings({"unused", "Duplicates"})
    @Callback(limit = 2, doc = "function():boolean, ... -- Get all online players | Requires Advanced capabilities")
    public Object[] playerlist(Context context, Arguments args) {
        if (getInterdimensional()) {
            List<Object> players = new ArrayList<Object>();
            players.add( true );
            for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (!(o instanceof EntityPlayer)) {
                    continue;
                }
                EntityPlayer player = (EntityPlayer) o;

                players.add(player.getDisplayName());
            }
            Object[] retval = new Object[ players.size() ];
            players.toArray(retval);
            return retval;
        }
        return new Object[]{ false };
    }
    @SubscribeEvent
    public void chatMessage(ServerChatEvent event) {
        if (node() != null && (event.player.getDistance(this.xCoord, this.yCoord, this.zCoord) <= getRange())) {
            node().sendToReachable(
                    "computer.signal",
                    "goodchatbox_message",
                    event.username,
                    event.player.getDistance(this.xCoord, this.yCoord, this.zCoord),
                    event.message
            );
        }
    }

    @SubscribeEvent
    public void playerJoin(PlayerLoggedInEvent event) {
        if (node() != null && getInterdimensional()) {
            node().sendToReachable(
                    "computer.signal",
                    "goodchatbox_playerjoin",
                    event.player.getDisplayName()
            );
        }
    }
    @SubscribeEvent
    public void playerLeave(PlayerLoggedOutEvent event) {
        if (node() != null && getInterdimensional()) {
            node().sendToReachable(
                    "computer.signal",
                    "goodchatbox_playerleave",
                    event.player.getDisplayName()
            );
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (node != null) {
            node.remove();
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (node != null && node.network() == null) {
            Network.joinOrCreateNetwork(this);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (node != null && node.host() == this) {
            node.load(tag.getCompoundTag("oc:node"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (node != null && node.host() == this) {
            final NBTTagCompound nodeNbt = new NBTTagCompound();
            node.save(nodeNbt);
            tag.setTag("oc:node", nodeNbt);
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        if (node != null) {
            node.remove();
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
        }
    }

    public Node node() {
        return node;
    }
    // Blank stubs
    @Override
    public void onConnect(final Node node) {
        if(node == this.node()) {
            MinecraftForge.EVENT_BUS.register(this);
            FMLCommonHandler.instance().bus().register(this);
        }
    }

    @Override
    public void onDisconnect(Node node) {
        if(node == this.node()) {
            MinecraftForge.EVENT_BUS.unregister(this);
            FMLCommonHandler.instance().bus().unregister(this);
        }
    }

    @Override
    public void onMessage(Message message) { }
}
