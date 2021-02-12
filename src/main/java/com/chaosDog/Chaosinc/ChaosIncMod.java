package com.chaosDog.Chaosinc;

import com.chaosDog.Chaosinc.blocks.fluids.EnderAcid;
import com.chaosDog.Chaosinc.blocks.fluids.GlowingWater;
import com.chaosDog.Chaosinc.blocks.fluids.LiquidRedstone;
import com.chaosDog.Chaosinc.blocks.fluids.fluidRenders;
import com.chaosDog.Chaosinc.creativeTabs.ChristmasTab;
import com.chaosDog.Chaosinc.handlers.CustomCraftBenchGuiHandler;
import com.chaosDog.Chaosinc.handlers.Events;
import com.chaosDog.Chaosinc.handlers.OreDictHandler;
import com.chaosDog.Chaosinc.init.ModSmelting;
import com.chaosDog.Chaosinc.init.modBlocks;
import com.chaosDog.Chaosinc.init.modItems;
import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.proxy.CommonProxy;
import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCactusChest;
import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCompressedCactusChest;
import com.chaosDog.Chaosinc.tileentity.furnace.TEMossyFurnace;
import com.chaosDog.Chaosinc.tileentity.furnace.TEVoidFurnace;
import com.chaosDog.Chaosinc.tileentity.hopper.TileEntityGoldHopper;
import com.chaosDog.Chaosinc.tileentity.hopper.TileEntitySilverHopper;
import com.chaosDog.Chaosinc.world.biome.BiomeRegistry;
import com.chaosDog.Chaosinc.world.dimension.DimensionRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class ChaosIncMod {

	public static final CreativeTabs xmas = new ChristmasTab();
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	@Instance
	public static ChaosIncMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//loot tables
		LootTableList.register(new ResourceLocation("chaosinc","starter_chest"));
		LootTableList.register(new ResourceLocation("chaosinc","mining_chest"));
		LootTableList.register(new ResourceLocation("chaosinc","treasure_chest"));
        
        //events
        MinecraftForge.EVENT_BUS.register(Events.class);

        //some modifications to vanilla blocks
        //This is NOT a coremod!
		Blocks.REDSTONE_BLOCK.setResistance(2000F);
		Blocks.WOODEN_BUTTON.setUnlocalizedName("oak_button");
		Blocks.STRUCTURE_BLOCK.setCreativeTab(CreativeTabs.REDSTONE);
		Blocks.STRUCTURE_VOID.setCreativeTab(CreativeTabs.REDSTONE);
		Blocks.COMMAND_BLOCK.setCreativeTab(CreativeTabs.REDSTONE);
		Blocks.REPEATING_COMMAND_BLOCK.setCreativeTab(CreativeTabs.REDSTONE);
		Blocks.CHAIN_COMMAND_BLOCK.setCreativeTab(CreativeTabs.REDSTONE);
		Blocks.BARRIER.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		Blocks.MOB_SPAWNER.setCreativeTab(CreativeTabs.DECORATIONS);
		Items.COMMAND_BLOCK_MINECART.setCreativeTab(CreativeTabs.TRANSPORTATION);

		//liquids
		LiquidRedstone.register();
		EnderAcid.register();
		GlowingWater.register();

		//this will fail to run in a server environment
		try {
			fluidRenders.register();
		}
		catch(NoClassDefFoundError ex) {
		}
		FluidRegistry.addBucketForFluid(LiquidRedstone.FluidLiquidRedstone.instance);
		FluidRegistry.addBucketForFluid(EnderAcid.FluidEnderAcid.instance);
		FluidRegistry.addBucketForFluid(GlowingWater.FluidGlowingWater.instance);
		
		//tile entities
		GameRegistry.registerTileEntity(TEMossyFurnace.class,"mossy_furnace");
		GameRegistry.registerTileEntity(TEVoidFurnace.class,"void_furnace");
		GameRegistry.registerTileEntity(TileEntityCactusChest.class,"cactus_chest");
		GameRegistry.registerTileEntity(TileEntityCompressedCactusChest.class,"compressed_cactus_chest");
		GameRegistry.registerTileEntity(TileEntityGoldHopper.class,"gold_hopper");
		GameRegistry.registerTileEntity(TileEntitySilverHopper.class,"silver_hopper");

		//world generators
		modWorld.register();

		//biomes
		BiomeRegistry.regBiomes();
		
		//block categories
		modBlocks.register();
		
		//item categories
		modItems.register();
	}
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		OreDictHandler.registerOreDict();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new CustomCraftBenchGuiHandler());
		proxy.init();

		ModSmelting.register();

		DimensionRegistry.registerDimensions();

		//spawn mobs in the deep void dimension
		EntityRegistry.addSpawn(EntityZombie.class, 10, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityCreeper.class, 10, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntitySpider.class, 10, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntitySkeleton.class, 10, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityWitherSkeleton.class, 1, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityEnderman.class, 1, 0, 1, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityIllusionIllager.class, 10, 0, 10, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityGiantZombie.class, 10, 0, 1, EnumCreatureType.MONSTER, Biomes.VOID);
		EntityRegistry.addSpawn(EntityRabbit.class, 10, 0, 1, EnumCreatureType.CREATURE, Biomes.VOID);
	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		if (Reference.DEV_BUILD)
			System.out.println("This is a development build, do NOT open your favorite worlds with this build!");
	}
}