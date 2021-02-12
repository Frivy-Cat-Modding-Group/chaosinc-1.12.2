package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomSlab;

import net.minecraft.block.material.Material;

public class Slabs {	
	public static CustomSlab bedrock_slab = new CustomSlab(Material.BARRIER, false);
	public static CustomSlab bedrock_slab_double = new CustomSlab(Material.BARRIER, true);

	public static CustomSlab cactus_slab = new CustomSlab(Material.WOOD, false);
	public static CustomSlab cactus_slab_double = new CustomSlab(Material.WOOD, true);

	public static CustomSlab end_slab = new CustomSlab(Material.ROCK, false);
	public static CustomSlab end_slab_double = new CustomSlab(Material.ROCK, true);
	
	public static CustomSlab glowing_obsidian_slab = new CustomSlab(Material.BARRIER, false);
	public static CustomSlab glowing_obsidian_slab_double = new CustomSlab(Material.BARRIER, true);
	
	public static CustomSlab obsidian_slab = new CustomSlab(Material.BARRIER, false);
	public static CustomSlab obsidian_slab_double = new CustomSlab(Material.BARRIER, true);
	
	public static CustomSlab void_bricks_slab = new CustomSlab(Material.ROCK, false);
	public static CustomSlab void_bricks_slab_double = new CustomSlab(Material.ROCK, true);

	public static CustomSlab void_slab = new CustomSlab(Material.ROCK, false);
	public static CustomSlab void_slab_double = new CustomSlab(Material.ROCK, true);

	public static void initAndRegister() {
		bedrock_slab.setUnlocalizedName("bedrock_slab").setRegistryName("bedrock_slab").setHardness(-1F).setResistance(6000000F).setBlockUnbreakable();
		bedrock_slab_double.setRegistryName("double_bedrock_slab").setHardness(-1F).setResistance(6000000F).setBlockUnbreakable();

		cactus_slab.setUnlocalizedName("cactus_slab").setRegistryName("cactus_slab").setHardness(2.5F).setResistance(5F);
		cactus_slab_double.setRegistryName("double_cactus_slab").setHardness(2.5F).setResistance(5F);

		end_slab.setUnlocalizedName("end_slab").setRegistryName("end_slab").setHardness(7.5F).setResistance(15F);
		end_slab_double.setRegistryName("double_end_slab").setHardness(7.5F).setResistance(15F);

		glowing_obsidian_slab.setUnlocalizedName("glowing_obsidian_slab").setRegistryName("glowing_obsidian_slab").setHardness(51F).setResistance(2000F).setLightLevel(.46666667F).setHarvestLevel("pickaxe", 3);
		glowing_obsidian_slab_double.setRegistryName("glowing_obsidian_slab_double").setHardness(51F).setResistance(2000F).setLightLevel(.46666667F).setHarvestLevel("pickaxe", 3);

		obsidian_slab.setUnlocalizedName("obsidian_slab").setRegistryName("obsidian_slab").setHardness(51F).setResistance(2000F).setHarvestLevel("pickaxe", 3);
		obsidian_slab_double.setRegistryName("double_obsidian_slab").setHardness(51F).setResistance(2000F).setHarvestLevel("pickaxe", 3);

		void_slab.setUnlocalizedName("void_slab").setRegistryName("void_slab").setHardness(5F).setResistance(10F);
		void_slab_double.setRegistryName("double_void_slab").setHardness(5F).setResistance(10F);

		void_bricks_slab.setUnlocalizedName("void_bricks_slab").setRegistryName("void_bricks_slab").setHardness(5F).setResistance(10F);
		void_bricks_slab_double.setRegistryName("double_void_bricks_slab").setHardness(5F).setResistance(10F);

		Utils.regSlab(bedrock_slab,bedrock_slab_double);
		Utils.regSlab(end_slab,end_slab_double);
		Utils.regSlab(cactus_slab,cactus_slab_double);
		Utils.regSlab(glowing_obsidian_slab,glowing_obsidian_slab_double);
		Utils.regSlab(obsidian_slab,obsidian_slab_double);
		Utils.regSlab(void_slab,void_slab_double);
		Utils.regSlab(void_bricks_slab,void_bricks_slab_double);
	}
	
	public static void regRenders() {
		Utils.regRender(bedrock_slab);
		Utils.regRender(cactus_slab);
		Utils.regRender(end_slab);
		Utils.regRender(glowing_obsidian_slab);
		Utils.regRender(obsidian_slab);
		Utils.regRender(void_slab);
		Utils.regRender(void_bricks_slab);
	}

}
