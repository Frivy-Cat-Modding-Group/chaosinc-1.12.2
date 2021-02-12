package com.chaosDog.Chaosinc.blocks.xmas;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.config.ModConfig;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.Random;

public class ChristmasTopper extends BlockGlass {
	public ChristmasTopper() {
		super(Material.PLANTS, false);
		setUnlocalizedName("ChristmasTopper");
		setRegistryName("tree_topper");
		setLightLevel(1F);
		setSoundType(SoundType.METAL);
		if (!ModConfig.HideXmasFeatures)
			setCreativeTab(ChaosIncMod.xmas);
	}
	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}