package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.RedstoneFence;
import com.chaosDog.Chaosinc.blocks.base.CustomFence;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class Fences {
	//fences
	public static CustomFence BrickFence = new CustomFence(Material.ROCK, MapColor.ADOBE);
	public static CustomFence CactusFence = new CustomFence(Material.WOOD, MapColor.GREEN);
	public static CustomFence DiamondFence = new CustomFence(Material.IRON, MapColor.DIAMOND);
	public static CustomFence EmeraldFence = new CustomFence(Material.IRON, MapColor.EMERALD);
	public static CustomFence GoldFence = new CustomFence(Material.IRON, MapColor.GOLD);
	public static CustomFence IronFence = new CustomFence(Material.IRON, MapColor.IRON);
	public static RedstoneFence RedstoneFence = new RedstoneFence();
	public static CustomFence RubyFence = new CustomFence(Material.IRON, MapColor.MAGENTA);
	public static CustomFence SilverFence = new CustomFence(Material.IRON, MapColor.SILVER);

	public static void initAndRegister() {
		//fences
		BrickFence.setHarvestLevel("pickaxe", 0);
		DiamondFence.setHarvestLevel("pickaxe", 2);
		EmeraldFence.setHarvestLevel("pickaxe", 2);
		GoldFence.setHarvestLevel("pickaxe", 2);
		IronFence.setHarvestLevel("pickaxe", 1);
		RubyFence.setHarvestLevel("pickaxe", 2);
		SilverFence.setHarvestLevel("pickaxe", 2);
		
		Utils.regBlock(BrickFence.setUnlocalizedName("BrickFence").setRegistryName("brick_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(CactusFence.setUnlocalizedName("CactusFence").setRegistryName("cactus_fence").setHardness(2F).setResistance(5F));
		Utils.regBlock(DiamondFence.setUnlocalizedName("DiamondFence").setRegistryName("diamond_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(EmeraldFence.setUnlocalizedName("EmeraldFence").setRegistryName("emerald_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(GoldFence.setUnlocalizedName("GoldFence").setRegistryName("gold_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(IronFence.setUnlocalizedName("IronFence").setRegistryName("iron_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(RedstoneFence);
		Utils.regBlock(RubyFence.setUnlocalizedName("RubyFence").setRegistryName("ruby_fence").setHardness(5F).setResistance(10F));
		Utils.regBlock(SilverFence.setUnlocalizedName("SilverFence").setRegistryName("silver_fence").setHardness(5F).setResistance(10F));
	}

	public static void regRenders() {
		Utils.regRender(BrickFence);
		Utils.regRender(CactusFence);
		Utils.regRender(DiamondFence);
		Utils.regRender(EmeraldFence);
		Utils.regRender(GoldFence);
		Utils.regRender(IronFence);
		Utils.regRender(RedstoneFence);
		Utils.regRender(RubyFence);
		Utils.regRender(SilverFence);
	}
}