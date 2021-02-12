package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomPressurePlate;

import net.minecraft.block.material.Material;

public class Plates {
	//pressure plates
	//grass and dirt
	public static CustomPressurePlate DirtPlate = new CustomPressurePlate(1,20,4, Material.GROUND);
	public static CustomPressurePlate GrassPlate = new CustomPressurePlate(1,20,3, Material.GROUND);

	//others
	public static CustomPressurePlate DiamondPlate = new CustomPressurePlate(0,14,2,Material.IRON);
	public static CustomPressurePlate EmeraldPlate = new CustomPressurePlate(0,10,2,Material.IRON);
	public static CustomPressurePlate glassPlate  = new CustomPressurePlate(0,20,5, Material.GLASS);
	public static CustomPressurePlate RubyPlate = new CustomPressurePlate(0,4,2,Material.IRON);
	public static CustomPressurePlate SilverPlate = new CustomPressurePlate(0,20,2,Material.IRON);

	//stone variants
	public static CustomPressurePlate CobblestonePlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate MossyPlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate AndesitePlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate DioritePlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate GranitePlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate EndPlate = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate redSandstonePlate  = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate sandstonePlate  = new CustomPressurePlate(1,20,0, Material.ROCK);
	public static CustomPressurePlate VoidPlate = new CustomPressurePlate(1,20,0, Material.ROCK);

	//unbreakable
	public static CustomPressurePlate GlowingObsidianPlate = new CustomPressurePlate(2,20,0,Material.ROCK);
	public static CustomPressurePlate BedrockPlate = new CustomPressurePlate(2,20,0,Material.BARRIER);

	//wooden
	public static CustomPressurePlate AcaciaPlate = new CustomPressurePlate(0, 30, 1, Material.WOOD);
	public static CustomPressurePlate BirchPlate = new CustomPressurePlate(0, 30, 1, Material.WOOD);
	public static CustomPressurePlate CactusPlate = new CustomPressurePlate(0, 30, 1, Material.WOOD);
	public static CustomPressurePlate DarkOakPlate = new CustomPressurePlate(0, 30, 1, Material.WOOD);
	public static CustomPressurePlate JunglePlate = new CustomPressurePlate(0, 30, 1, Material.WOOD);
	public static CustomPressurePlate SprucePlate = new CustomPressurePlate(0, 30, 1,Material.WOOD);

	public static void initAndRegister() {
		//pressure plates
		//I have to set these motherf_ckers separately since java complains 
		//about them being set in the lines below!
		AcaciaPlate.setHarvestLevel("axe", 0);
		BirchPlate.setHarvestLevel("axe", 0);
		CactusPlate.setHarvestLevel("axe", 0);
		DarkOakPlate.setHarvestLevel("axe", 0);
		DiamondPlate.setHarvestLevel("pickaxe", 2);
		EmeraldPlate.setHarvestLevel("pickaxe", 2);
		GlowingObsidianPlate.setHarvestLevel("pickaxe", 3);
		JunglePlate.setHarvestLevel("axe", 0);
		SilverPlate.setHarvestLevel("pickaxe", 2);
		RubyPlate.setHarvestLevel("pickaxe", 2);
		SprucePlate.setHarvestLevel("axe", 0);

		//grass and dirt
		Utils.regBlock(DirtPlate.setUnlocalizedName("DirtPlate").setRegistryName("dirt_pressure_plate"));
		Utils.regBlock(GrassPlate.setUnlocalizedName("GrassPlate").setRegistryName("grass_pressure_plate"));
		
		//special
		Utils.regBlock(DiamondPlate.setUnlocalizedName("DiamondPlate").setRegistryName("diamond_pressure_plate").setHardness(0.5F));
		Utils.regBlock(EmeraldPlate.setUnlocalizedName("EmeraldPlate").setRegistryName("emerald_pressure_plate").setHardness(0.5F));
		Utils.regBlock(glassPlate.setUnlocalizedName("GlassPlate").setRegistryName("glass_pressure_plate").setHardness(0.1F));
		Utils.regBlock(SilverPlate.setUnlocalizedName("SilverPlate").setRegistryName("silver_pressure_plate").setHardness(0.5F));
		Utils.regBlock(RubyPlate.setUnlocalizedName("RubyPlate").setRegistryName("ruby_pressure_plate").setHardness(0.5F));

		//stone variants
		Utils.regBlock(MossyPlate.setUnlocalizedName("MossyPlate").setRegistryName("mossy_cobblestone_pressure_plate").setHardness(0.5F));
		Utils.regBlock(CobblestonePlate.setUnlocalizedName("CobblestonePlate").setRegistryName("cobblestone_pressure_plate").setHardness(0.5F));
		Utils.regBlock(AndesitePlate.setUnlocalizedName("AndesitePlate").setRegistryName("andesite_pressure_plate").setHardness(0.5F));
		Utils.regBlock(DioritePlate.setUnlocalizedName("DioritePlate").setRegistryName("diorite_pressure_plate").setHardness(0.5F));
		Utils.regBlock(GranitePlate.setUnlocalizedName("GranitePlate").setRegistryName("granite_pressure_plate").setHardness(0.5F));
		Utils.regBlock(EndPlate.setUnlocalizedName("EndPlate").setRegistryName("end_pressure_plate").setHardness(0.5F));
		Utils.regBlock(sandstonePlate.setUnlocalizedName("SandstonePlate").setRegistryName("sandstone_pressure_plate").setHardness(0.5F));
		Utils.regBlock(redSandstonePlate.setUnlocalizedName("RedSandstonePlate").setRegistryName("red_sandstone_pressure_plate").setHardness(0.5F));
		Utils.regBlock(VoidPlate.setUnlocalizedName("VoidPlate").setRegistryName("void_pressure_plate").setHardness(0.5F));

		//unbreakable
		Utils.regBlock(BedrockPlate.setUnlocalizedName("BedrockPlate").setRegistryName("bedrock_pressure_plate").setHardness(-1F).setResistance(6000000F).setBlockUnbreakable());
		Utils.regBlock(GlowingObsidianPlate.setUnlocalizedName("GlowingObsidianPlate").setRegistryName("glowing_obsidian_pressure_plate").setHardness(51F).setResistance(2000F).setLightLevel(.46666667F));

		//wooden
		Utils.regBlock(AcaciaPlate.setUnlocalizedName("AcaciaPlate").setRegistryName("acacia_pressure_plate").setHardness(0.5F));
		Utils.regBlock(BirchPlate.setUnlocalizedName("BirchPlate").setRegistryName("birch_pressure_plate").setHardness(0.5F));
		Utils.regBlock(CactusPlate.setUnlocalizedName("CactusPlate").setRegistryName("cactus_pressure_plate").setHardness(0.5F));
		Utils.regBlock(DarkOakPlate.setUnlocalizedName("DarkOakPlate").setRegistryName("dark_oak_pressure_plate").setHardness(0.5F));
		Utils.regBlock(JunglePlate.setUnlocalizedName("JunglePlate").setRegistryName("jungle_pressure_plate").setHardness(0.5F));
		Utils.regBlock(SprucePlate.setUnlocalizedName("SprucePlate").setRegistryName("spruce_pressure_plate").setHardness(0.5F));
	}

	
	public static void regRenders() {		
		Utils.regRender(VoidPlate);
		Utils.regRender(DirtPlate);
		Utils.regRender(glassPlate);
		Utils.regRender(GrassPlate);
		Utils.regRender(CactusPlate);
		Utils.regRender(GlowingObsidianPlate);
		Utils.regRender(EndPlate);
		Utils.regRender(SilverPlate);
		Utils.regRender(DiamondPlate);
		Utils.regRender(EmeraldPlate);
		Utils.regRender(sandstonePlate);
		Utils.regRender(redSandstonePlate);
		Utils.regRender(RubyPlate);
		Utils.regRender(BedrockPlate);
		Utils.regRender(MossyPlate);
		Utils.regRender(CobblestonePlate);
		Utils.regRender(AndesitePlate);
		Utils.regRender(DioritePlate);
		Utils.regRender(GranitePlate);
		Utils.regRender(AcaciaPlate);
		Utils.regRender(BirchPlate);
		Utils.regRender(DarkOakPlate);
		Utils.regRender(JunglePlate);
		Utils.regRender(SprucePlate);
	}
}