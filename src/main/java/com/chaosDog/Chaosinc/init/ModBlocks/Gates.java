package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.RedstoneFenceGate;
import com.chaosDog.Chaosinc.blocks.base.CustomGate;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;

public class Gates {
	//fence gates
	public static CustomGate CactusFenceGate = new CustomGate(SoundType.WOOD);
	public static CustomGate SilverFenceGate = new CustomGate(SoundType.METAL);
	public static CustomGate BrickFenceGate = new CustomGate(SoundType.STONE);
	public static CustomGate DiamondFenceGate = new CustomGate(SoundType.METAL);
	public static CustomGate EmeraldFenceGate = new CustomGate(SoundType.METAL);
	public static CustomGate GoldFenceGate = new CustomGate(SoundType.METAL);
	public static CustomGate IronFenceGate = new CustomGate(SoundType.METAL);
	public static CustomGate RubyFenceGate = new CustomGate(SoundType.METAL);
	public static BlockHorizontal RedstoneFenceGate = new RedstoneFenceGate();

	public static void initAndRegister() {
		//fence gates
		BrickFenceGate.setHarvestLevel("pickaxe", 0);
		DiamondFenceGate.setHarvestLevel("pickaxe", 2);
		EmeraldFenceGate.setHarvestLevel("pickaxe", 2);
		GoldFenceGate.setHarvestLevel("pickaxe", 2);
		IronFenceGate.setHarvestLevel("pickaxe", 1);
		RubyFenceGate.setHarvestLevel("pickaxe", 2);
		SilverFenceGate.setHarvestLevel("pickaxe", 2);

		Utils.regBlock(BrickFenceGate.setUnlocalizedName("BrickFenceGate").setRegistryName("brick_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(CactusFenceGate.setUnlocalizedName("CactusFenceGate").setRegistryName("cactus_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(DiamondFenceGate.setUnlocalizedName("DiamondFenceGate").setRegistryName("diamond_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(EmeraldFenceGate.setUnlocalizedName("EmeraldFenceGate").setRegistryName("emerald_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(GoldFenceGate.setUnlocalizedName("GoldFenceGate").setRegistryName("gold_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(IronFenceGate.setUnlocalizedName("IronFenceGate").setRegistryName("iron_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(RedstoneFenceGate);
		Utils.regBlock(RubyFenceGate.setUnlocalizedName("RubyFenceGate").setRegistryName("ruby_fence_gate").setHardness(5F).setResistance(10F));
		Utils.regBlock(SilverFenceGate.setUnlocalizedName("SilverFenceGate").setRegistryName("silver_fence_gate").setHardness(5F).setResistance(10F));
	}

	public static void regRenders() {	
		Utils.regRender(BrickFenceGate);
		Utils.regRender(CactusFenceGate);
		Utils.regRender(DiamondFenceGate);
		Utils.regRender(EmeraldFenceGate);
		Utils.regRender(GoldFenceGate);
		Utils.regRender(IronFenceGate);
		Utils.regRender(RedstoneFenceGate);
		Utils.regRender(RubyFenceGate);
		Utils.regRender(SilverFenceGate);
	}
}