package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomWall;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Walls {
	//walls
	public static CustomWall BedrockWall = new CustomWall(Material.BARRIER);
	public static CustomWall GlowingObsidianWall = new CustomWall(Material.ROCK);
	public static CustomWall ObsidianWall = new CustomWall(Material.ROCK);
	public static CustomWall EndWall = new CustomWall(Material.ROCK);
	public static CustomWall VoidWall = new CustomWall(Material.ROCK);

	public static void initAndRegister() {
		
		//walls
		Utils.regBlock(BedrockWall.setUnlocalizedName("BedrockWall").setRegistryName("bedrock_wall").setHardness(-1F).setResistance(6000000F).setBlockUnbreakable());
		Utils.regBlock(EndWall.setRegistryName("end_wall").setUnlocalizedName("EndWall").setHardness(5F).setResistance(15F));
		Utils.regBlock(GlowingObsidianWall.setRegistryName("glowing_obsidian_wall").setUnlocalizedName("GlowingObsidianWall").setCreativeTab(CreativeTabs.DECORATIONS).setHardness(51F).setResistance(2000F).setLightLevel(.46666667F));
		Utils.regBlock(ObsidianWall.setRegistryName("obsidian_wall").setUnlocalizedName("ObsidianWall").setHardness(51F).setResistance(2000F));
		Utils.regBlock(VoidWall.setUnlocalizedName("VoidWall").setRegistryName("void_wall").setHardness(5F).setResistance(10F));
	}
	public static void regRenders() {
		Utils.regRender(VoidWall);
		Utils.regRender(GlowingObsidianWall);
		Utils.regRender(EndWall);
		Utils.regRender(BedrockWall);
		Utils.regRender(ObsidianWall);
	}
}
