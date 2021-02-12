package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomStairs;

import net.minecraft.init.Blocks;

public class Stairs {
	//stairs
	public static CustomStairs BedrockStairs = new CustomStairs(Blocks.BARRIER);
	public static CustomStairs CactusStairs = new CustomStairs(Blocks.PLANKS);
	public static CustomStairs EndStairs = new CustomStairs(Blocks.END_STONE);
	public static CustomStairs GlowingObsidianStairs = new CustomStairs(Blocks.BARRIER);
	public static CustomStairs ObsidianStairs = new CustomStairs(Blocks.BARRIER);
	public static CustomStairs VoidStairs = new CustomStairs(Blocks.STONE);
	public static CustomStairs VoidBrickStairs = new CustomStairs(Blocks.STONE);

	public static void initAndRegister() {
		//stairs
		EndStairs.setHarvestLevel("pickaxe", 0);
		GlowingObsidianStairs.setHarvestLevel("pickaxe", 3);
		ObsidianStairs.setHarvestLevel("pickaxe", 3);
		
		Utils.regBlock(BedrockStairs.setUnlocalizedName("BedrockStairs").setRegistryName("bedrock_stairs").setHardness(-1F).setResistance(6000000F));
		Utils.regBlock(CactusStairs.setUnlocalizedName("CactusStairs").setRegistryName("cactus_stairs").setHardness(2F).setResistance(5F));
		Utils.regBlock(EndStairs.setUnlocalizedName("EndStairs").setRegistryName("end_stairs").setHardness(5F).setResistance(15F));
		Utils.regBlock(GlowingObsidianStairs.setUnlocalizedName("GlowingObsidianStairs").setRegistryName("glowing_obsidian_stairs").setHardness(51F).setResistance(2000F));
		Utils.regBlock(ObsidianStairs.setUnlocalizedName("ObsidianStairs").setRegistryName("obsidian_stairs").setHardness(51F).setResistance(2000F));
		Utils.regBlock(VoidStairs.setUnlocalizedName("VoidStairs").setRegistryName("void_stairs").setHardness(5F).setResistance(10F));
		Utils.regBlock(VoidBrickStairs.setUnlocalizedName("VoidBrickStairs").setRegistryName("void_brick_stairs").setHardness(5F).setResistance(10F));
	}
	public static void regRenders() {
		Utils.regRender(VoidStairs);
		Utils.regRender(CactusStairs);
		Utils.regRender(EndStairs);
		Utils.regRender(ObsidianStairs);
		Utils.regRender(GlowingObsidianStairs);
		Utils.regRender(BedrockStairs);
		Utils.regRender(VoidBrickStairs);
	}
}
