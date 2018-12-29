package space.rph.goodchatbox;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraft.item.ItemStack;

import space.rph.goodchatbox.items.*;
import space.rph.goodchatbox.blocks.*;
import space.rph.goodchatbox.tileentity.*;


public class RegisterContent {
    public static Item t1MicrochipArray;
    public static Item t2MicrochipArray;
    public static Item t3MicrochipArray;
    public static Item t1MicrochipCard;
    public static Item t2MicrochipCard;
    public static Item t3MicrochipCard;
    public static Item combinedDiamond;
    public static Item combinedObsidian;
    public static Item combinedEPearl;
    public static Item denseCombinedDiamond;
    public static Item denseCombinedObsidian;
    public static Item denseCombinedEPearl;

    public static Block creativeChatbox;
    public static Block t1Chatbox;
    public static Block t2Chatbox;
    public static Block t3Chatbox;
    public static Block t4Chatbox;
    public static Block t5Chatbox;
    public static Block t6Chatbox;
    public static Block t7Chatbox;
    public static Block t8Chatbox;
    public static Block t9Chatbox;
    public static Block t10Chatbox;
    public static Block t11Chatbox;
    public static Block t12Chatbox;
    public static Block t13Chatbox;

    public static CreativeTabs creativeTab;

    public static void preInit() {
        registerTabs();
        registerBlocks();
        registerItems();
        registerEvents();
        registerRecipies();
    }

    private static void registerTabs() {
        creativeTab = new CreativeTab("GoodChatbox");
    }

    private static void registerBlocks() {
        GameRegistry.registerTileEntity(TileEntityChatbox.class, "GoodChatBox");
        GameRegistry.registerTileEntity(TileChatboxT1.class, "GoodChatBoxT1");
        GameRegistry.registerTileEntity(TileChatboxT2.class, "GoodChatBoxT2");
        GameRegistry.registerTileEntity(TileChatboxT3.class, "GoodChatBoxT3");
        GameRegistry.registerTileEntity(TileChatboxT4.class, "GoodChatBoxT4");
        GameRegistry.registerTileEntity(TileChatboxT5.class, "GoodChatBoxT5");
        GameRegistry.registerTileEntity(TileChatboxT6.class, "GoodChatBoxT6");
        GameRegistry.registerTileEntity(TileChatboxT7.class, "GoodChatBoxT7");
        GameRegistry.registerTileEntity(TileChatboxT8.class, "GoodChatBoxT8");
        GameRegistry.registerTileEntity(TileChatboxT9.class, "GoodChatBoxT9");
        GameRegistry.registerTileEntity(TileChatboxT10.class, "GoodChatBoxT10");
        GameRegistry.registerTileEntity(TileChatboxT11.class, "GoodChatBoxT11");
        GameRegistry.registerTileEntity(TileChatboxT12.class, "GoodChatBoxT12");
        GameRegistry.registerTileEntity(TileChatboxT13.class, "GoodChatBoxT13");
        GameRegistry.registerTileEntity(TileChatboxCreative.class, "GoodChatBoxCreative");


        creativeChatbox = new BlockCreativeChatbox();
        GameRegistry.registerBlock(creativeChatbox, "chat_box.creative");
        creativeChatbox.setCreativeTab(creativeTab);

        t1Chatbox = new BlockTier1Chatbox();
        GameRegistry.registerBlock(t1Chatbox, "chat_box.tier1");
        t1Chatbox.setCreativeTab(creativeTab);

        t2Chatbox = new BlockTier2Chatbox();
        GameRegistry.registerBlock(t2Chatbox, "chat_box.tier2");
        t2Chatbox.setCreativeTab(creativeTab);

        t3Chatbox = new BlockTier3Chatbox();
        GameRegistry.registerBlock(t3Chatbox, "chat_box.tier3");
        t3Chatbox.setCreativeTab(creativeTab);

        t4Chatbox = new BlockTier4Chatbox();
        GameRegistry.registerBlock(t4Chatbox, "chat_box.tier4");
        t4Chatbox.setCreativeTab(creativeTab);

        t5Chatbox = new BlockTier5Chatbox();
        GameRegistry.registerBlock(t5Chatbox, "chat_box.tier5");
        t5Chatbox.setCreativeTab(creativeTab);

        t6Chatbox = new BlockTier6Chatbox();
        GameRegistry.registerBlock(t6Chatbox, "chat_box.tier6");
        t6Chatbox.setCreativeTab(creativeTab);

        t7Chatbox = new BlockTier7Chatbox();
        GameRegistry.registerBlock(t7Chatbox, "chat_box.tier7");
        t7Chatbox.setCreativeTab(creativeTab);

        t8Chatbox = new BlockTier8Chatbox();
        GameRegistry.registerBlock(t8Chatbox, "chat_box.tier8");
        t8Chatbox.setCreativeTab(creativeTab);

        t9Chatbox = new BlockTier9Chatbox();
        GameRegistry.registerBlock(t9Chatbox, "chat_box.tier9");
        t9Chatbox.setCreativeTab(creativeTab);

        t10Chatbox = new BlockTier10Chatbox();
        GameRegistry.registerBlock(t10Chatbox, "chat_box.tier10");
        t10Chatbox.setCreativeTab(creativeTab);

        t11Chatbox = new BlockTier11Chatbox();
        GameRegistry.registerBlock(t11Chatbox, "chat_box.tier11");
        t11Chatbox.setCreativeTab(creativeTab);

        t12Chatbox = new BlockTier12Chatbox();
        GameRegistry.registerBlock(t12Chatbox, "chat_box.tier12");
        t12Chatbox.setCreativeTab(creativeTab);

        t13Chatbox = new BlockTier13Chatbox();
        GameRegistry.registerBlock(t13Chatbox, "chat_box.tier13");
        t13Chatbox.setCreativeTab(creativeTab);
    }

    private static void registerItems() {
        t1MicrochipArray = new ItemT1MicrochipArray();
        GameRegistry.registerItem(t1MicrochipArray, "t1_microchip_array");
        t1MicrochipArray.setCreativeTab(creativeTab);

        t2MicrochipArray = new ItemT2MicrochipArray();
        GameRegistry.registerItem(t2MicrochipArray, "t2_microchip_array");
        t2MicrochipArray.setCreativeTab(creativeTab);

        t3MicrochipArray = new ItemT3MicrochipArray();
        GameRegistry.registerItem(t3MicrochipArray, "t3_microchip_array");
        t3MicrochipArray.setCreativeTab(creativeTab);

        t1MicrochipCard = new ItemT1MicrochipCard();
        GameRegistry.registerItem(t1MicrochipCard, "t1_microchip_card");
        t1MicrochipCard.setCreativeTab(creativeTab);

        t2MicrochipCard = new ItemT2MicrochipCard();
        GameRegistry.registerItem(t2MicrochipCard, "t2_microchip_card");
        t2MicrochipCard.setCreativeTab(creativeTab);

        t3MicrochipCard = new ItemT3MicrochipCard();
        GameRegistry.registerItem(t3MicrochipCard, "t3_microchip_card");
        t3MicrochipCard.setCreativeTab(creativeTab);

        combinedDiamond = new ItemCombinedDiamond();
        GameRegistry.registerItem(combinedDiamond, "combined_diamond");
        combinedDiamond.setCreativeTab(creativeTab);

        combinedObsidian = new ItemCombinedObsidian();
        GameRegistry.registerItem(combinedObsidian, "combined_obsidian");
        combinedObsidian.setCreativeTab(creativeTab);

        combinedEPearl = new ItemCombinedEPearl();
        GameRegistry.registerItem(combinedEPearl, "combined_ender_pearl");
        combinedEPearl.setCreativeTab(creativeTab);

        denseCombinedDiamond = new ItemDenselyCombinedDiamond();
        GameRegistry.registerItem(denseCombinedDiamond, "dense_combined_diamond");
        denseCombinedDiamond.setCreativeTab(creativeTab);

        denseCombinedObsidian = new ItemDenselyCombinedObsidian();
        GameRegistry.registerItem(denseCombinedObsidian, "dense_combined_obsidian");
        denseCombinedObsidian.setCreativeTab(creativeTab);

        denseCombinedEPearl = new ItemDenselyCombinedEPearl();
        GameRegistry.registerItem(denseCombinedEPearl, "dense_combined_ender_pearl");
        denseCombinedEPearl.setCreativeTab(creativeTab);
    }

    private static void registerEvents() {

    }

    @SuppressWarnings("Duplicates")
    private static void registerRecipies() {
        // Vanilla items
        String redstoneBlock = "blockRedstone";
        String obsidian = "obsidian";
        String diamond = "gemDiamond";
        String diamondBlock = "blockDiamond";
        String ironBlock = "blockIron";
        Item epearl = Items.ender_pearl;
        Item redstone = Items.redstone;
        Block glowstone = Blocks.glowstone;
        // OC items
        String t1microchip = "oc:circuitChip1";
        String t2microchip = "oc:circuitChip2";
        String t3microchip = "oc:circuitChip3";
        String cardBase = "oc:materialCard";

        /*
            COMBINATIONS
         */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(combinedDiamond, 1),
                "DDD",
                "DOD",
                "DDD",
                'D', diamond, 'O', obsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(combinedObsidian, 1),
                "OOO",
                "ODO",
                "OOO",
                'O', obsidian, 'D', diamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(combinedEPearl, 1),
                "OOO",
                "ODO",
                "OOO",
                'O', epearl, 'D', combinedDiamond));

        /*
            MICROCHIP ARRAYS
         */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t1MicrochipArray, 1),
                "MMM",
                "MRM",
                "MMM",
                'M', t1microchip, 'R', redstoneBlock));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t2MicrochipArray, 1),
                "MMM",
                "AOA",
                "MMM",
                'M', t2microchip, 'A', t1MicrochipArray, 'O', combinedObsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t3MicrochipArray, 1),
                "MMM",
                "ADA",
                "MMM",
                'M', t3microchip, 'A', t2MicrochipArray, 'D', combinedDiamond));

        /*
            DENSE COMBINATIONS
         */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(denseCombinedObsidian, 1),
                "CCC",
                "BOB",
                "CCC",
                'C', combinedObsidian, 'B', combinedDiamond, 'O', obsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(denseCombinedDiamond, 1),
                "DDD",
                "BOB",
                "DDD",
                'D', combinedDiamond, 'B', diamondBlock, 'O', denseCombinedObsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(denseCombinedEPearl, 1),
                "DDD",
                "BOB",
                "DDD",
                'D', combinedEPearl, 'B', denseCombinedDiamond, 'O', denseCombinedObsidian));

        /*
            MICROCHIP CARDS
         */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t1MicrochipCard, 1),
                "MMM",
                "RDC",
                "MMM",
                'M', t1MicrochipArray, 'R', redstone, 'D', diamond, 'C', cardBase));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t2MicrochipCard, 1),
                "MMM",
                "RDC",
                "MMM",
                'M', t2MicrochipArray, 'R', redstoneBlock, 'D', combinedObsidian, 'C', t1MicrochipCard));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t3MicrochipCard, 1),
                "MMM",
                "RDC",
                "MMM",
                'M', t3MicrochipArray, 'R', glowstone, 'D', combinedDiamond, 'C', t2MicrochipCard));

        /*
            CHATBOXES
         */
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t1Chatbox, 1),
                "CAC",
                "PBR",
                "CAC",
                'C', t1microchip, 'A', t1MicrochipArray, 'P', epearl, 'B', ironBlock, 'R', redstoneBlock));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t2Chatbox, 1),
                "BAE",
                "AOB",
                "BAR",
                'B', t1Chatbox, 'A', t1MicrochipArray, 'O', combinedObsidian, 'E', epearl, 'R', redstoneBlock));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t3Chatbox, 1),
                "BAE",
                "AOB",
                "BAR",
                'B', t2Chatbox, 'A', t2MicrochipArray, 'O', combinedObsidian, 'E', epearl, 'R', combinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t4Chatbox, 1),
                "BAE",
                "ARB",
                "BAR",
                'B', t3Chatbox, 'A', t2MicrochipArray, 'E', epearl, 'R', combinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t5Chatbox, 1),
                "BAE",
                "ARB",
                "BAO",
                'B', t4Chatbox, 'A', t3MicrochipArray, 'E', combinedEPearl, 'R', combinedDiamond, 'O', denseCombinedObsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t6Chatbox, 1),
                "BAE",
                "CRB",
                "BAO",
                'B', t5Chatbox, 'A', t3MicrochipArray, 'C', t1MicrochipCard, 'E', combinedEPearl, 'R', combinedDiamond, 'O', denseCombinedObsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t7Chatbox, 1),
                "BAE",
                "CRB",
                "BAO",
                'B', t6Chatbox, 'A', t3MicrochipArray, 'C', t1MicrochipCard, 'E', combinedEPearl, 'R', denseCombinedDiamond, 'O', denseCombinedObsidian));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t8Chatbox, 1),
                "BCE",
                "ARB",
                "BCE",
                'B', t7Chatbox, 'A', t3MicrochipArray, 'C', t2MicrochipCard, 'E', combinedEPearl, 'R', denseCombinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t9Chatbox, 1),
                "BCE",
                "CRB",
                "BCE",
                'B', t8Chatbox, 'C', t3MicrochipCard, 'E', combinedEPearl, 'R', denseCombinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t10Chatbox, 1),
                "BCR",
                "CEB",
                "BCR",
                'B', t9Chatbox, 'C', t3MicrochipCard, 'E', denseCombinedEPearl, 'R', denseCombinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t11Chatbox, 1),
                "BCB",
                "RER",
                "BCB",
                'B', t10Chatbox, 'C', t3MicrochipCard, 'E', denseCombinedEPearl, 'R', denseCombinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t12Chatbox, 1),
                "BCB",
                "ERE",
                "BCB",
                'B', t11Chatbox, 'C', t3MicrochipCard, 'E', denseCombinedEPearl, 'R', denseCombinedDiamond));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(t13Chatbox, 1),
                "BCB",
                "EBE",
                "BCB",
                'B', t12Chatbox, 'C', t3MicrochipCard, 'E', denseCombinedEPearl, 'R', denseCombinedDiamond));

    }
}
