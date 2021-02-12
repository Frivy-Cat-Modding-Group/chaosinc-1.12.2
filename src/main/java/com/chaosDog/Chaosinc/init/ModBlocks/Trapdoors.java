package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.RedstoneTrapdoor;
import com.chaosDog.Chaosinc.blocks.base.CustomTrapDoor;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Trapdoors {
	//trapdoors
	public static CustomTrapDoor AndesiteTrapDoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor BedrockTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor CactusTrapDoor = new CustomTrapDoor(Material.WOOD, SoundType.WOOD, 1);
	public static CustomTrapDoor CobblestoneTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor DiamondTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.METAL, 0);
	public static CustomTrapDoor DioriteTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor EmeraldTrapdoor = new CustomTrapDoor(Material.WOOD, SoundType.METAL, 0);
	public static CustomTrapDoor EndTrapDoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor GlassTrapDoor = new CustomTrapDoor(Material.WOOD, SoundType.GLASS, 1);
	public static CustomTrapDoor GlowingObsidianTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor GoldTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.METAL, 0);
	public static CustomTrapDoor GraniteTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor MossStoneTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static CustomTrapDoor ObsidianTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);
	public static Block RedstoneTrapdoor;
	public static CustomTrapDoor RubyTrapdoor = new CustomTrapDoor(Material.WOOD, SoundType.METAL, 0);
	public static CustomTrapDoor SilverTrapDoor = new CustomTrapDoor(Material.IRON, SoundType.METAL, 0);
	public static CustomTrapDoor StoneTrapdoor = new CustomTrapDoor(Material.IRON, SoundType.STONE, 0);

	public static void initAndRegister() {
		//trapdoors
		AndesiteTrapDoor.setHarvestLevel("pickaxe", 0);
		CobblestoneTrapdoor.setHarvestLevel("pickaxe", 0);
		DiamondTrapdoor.setHarvestLevel("pickaxe", 2);
		DioriteTrapdoor.setHarvestLevel("pickaxe", 0);
		EmeraldTrapdoor.setHarvestLevel("pickaxe", 2);
		EndTrapDoor.setHarvestLevel("pickaxe", 0);
		GlowingObsidianTrapdoor.setHarvestLevel("pickaxe", 3);
		GoldTrapdoor.setHarvestLevel("pickaxe", 2);
		GraniteTrapdoor.setHarvestLevel("pickaxe", 0);
		MossStoneTrapdoor.setHarvestLevel("pickaxe", 0);
		ObsidianTrapdoor.setHarvestLevel("pickaxe", 3);
		RubyTrapdoor.setHarvestLevel("pickaxe", 2);
		SilverTrapDoor.setHarvestLevel("pickaxe", 2);
		StoneTrapdoor.setHarvestLevel("pickaxe", 0);

		Utils.regBlock(AndesiteTrapDoor.setUnlocalizedName("AndesiteTrapdoor").setRegistryName("andesite_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(BedrockTrapdoor.setUnlocalizedName("BedrockTrapdoor").setRegistryName("bedrock_trapdoor").setHardness(-1F).setResistance(6000000F).setBlockUnbreakable());
		Utils.regBlock(CactusTrapDoor.setUnlocalizedName("CactusTrapdoor").setRegistryName("cactus_trapdoor").setHardness(2F).setResistance(5F));
		Utils.regBlock(CobblestoneTrapdoor.setUnlocalizedName("CobblestoneTrapdoor").setRegistryName("cobblestone_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(DiamondTrapdoor.setUnlocalizedName("DiamondTrapdoor").setRegistryName("diamond_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(DioriteTrapdoor.setUnlocalizedName("DioriteTrapdoor").setRegistryName("diorite_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(EmeraldTrapdoor.setUnlocalizedName("EmeraldTrapdoor").setRegistryName("emerald_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(EndTrapDoor.setUnlocalizedName("EndTrapdoor").setRegistryName("end_trapdoor").setHardness(5F).setResistance(15F));
		Utils.regBlock(GlassTrapDoor.setUnlocalizedName("GlassTrapdoor").setRegistryName("glass_trapdoor").setHardness(.5F).setResistance(1F));
		Utils.regBlock(GlowingObsidianTrapdoor.setUnlocalizedName("GlowingObsidianTrapdoor").setRegistryName("glowing_obsidian_trapdoor").setHardness(51F).setResistance(2000F).setLightLevel(.46666667F));
		Utils.regBlock(GoldTrapdoor.setUnlocalizedName("GoldTrapdoor").setRegistryName("gold_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(GraniteTrapdoor.setUnlocalizedName("GraniteTrapdoor").setRegistryName("granite_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(MossStoneTrapdoor.setUnlocalizedName("MossStoneTrapdoor").setRegistryName("moss_stone_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(ObsidianTrapdoor.setUnlocalizedName("ObsidianTrapdoor").setRegistryName("obsidian_trapdoor").setHardness(51F).setResistance(2000F));
		Utils.regBlock(RedstoneTrapdoor = new RedstoneTrapdoor());
		Utils.regBlock(RubyTrapdoor.setUnlocalizedName("RubyTrapdoor").setRegistryName("ruby_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(SilverTrapDoor.setUnlocalizedName("SilverTrapdoor").setRegistryName("silver_trapdoor").setHardness(5F).setResistance(10F));
		Utils.regBlock(StoneTrapdoor.setUnlocalizedName("StoneTrapdoor").setRegistryName("stone_trapdoor").setHardness(5F).setResistance(10F));
	}

	public static void regRenders() {
		Utils.regRender(CactusTrapDoor);
		Utils.regRender(GlowingObsidianTrapdoor);
		Utils.regRender(EndTrapDoor);
		Utils.regRender(SilverTrapDoor);
		Utils.regRender(GlassTrapDoor);
		Utils.regRender(GoldTrapdoor);
		Utils.regRender(DiamondTrapdoor);
		Utils.regRender(EmeraldTrapdoor);
		Utils.regRender(AndesiteTrapDoor);
		Utils.regRender(DioriteTrapdoor);
		Utils.regRender(GraniteTrapdoor);
		Utils.regRender(StoneTrapdoor);
		Utils.regRender(CobblestoneTrapdoor);
		Utils.regRender(MossStoneTrapdoor);
		Utils.regRender(ObsidianTrapdoor);
		Utils.regRender(BedrockTrapdoor);
		Utils.regRender(RubyTrapdoor);
		Utils.regRender(RedstoneTrapdoor);
	}
}