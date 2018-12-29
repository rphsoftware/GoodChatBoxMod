package space.rph.goodchatbox;

import akka.io.Tcp;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import space.rph.goodchatbox.RegisterContent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {
    public CreativeTab(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Item.getItemFromBlock(RegisterContent.creativeChatbox);
    }
}