package space.rph.goodchatbox;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = GoodChatboxMod.MODID, version = GoodChatboxMod.VERSION)
public class GoodChatboxMod {
    public static final String MODID = "GoodChatBox";
    public static final String VERSION = "1.0.2";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Loaded Rph's Good chat box mod");
    }

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        RegisterContent.preInit();
    }
}
