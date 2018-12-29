package space.rph.goodchatbox.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import space.rph.goodchatbox.tileentity.*;
import space.rph.goodchatbox.tileentity.TileEntityChatbox;

public class BlockTier2Chatbox extends BlockContainer {
    public BlockTier2Chatbox() {
        super(Material.iron);
        setHardness(2F);
        setResistance(10F);
        setBlockName("chatboxt2");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileChatboxT2();
    }

    @SideOnly(Side.CLIENT)
    public static IIcon topIcon;

    @SideOnly(Side.CLIENT)
    public static IIcon sideIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        topIcon = icon.registerIcon("GoodChatBox:chatbox_basic");
        sideIcon = icon.registerIcon("GoodChatBox:chatbox_side");
    }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side == 1) {
            return this.topIcon;
        }
        return this.sideIcon;
    }
}
