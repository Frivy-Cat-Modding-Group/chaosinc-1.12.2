package com.chaosDog.Chaosinc.blocks.xmas;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.config.ModConfig;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.SoundType;

public class SantaHat extends BlockPumpkin {
	public SantaHat() {
		setUnlocalizedName("SantaHat");
		setRegistryName("santa_hat");
		setHardness(.01F);
		setResistance(.01F);
		if (!ModConfig.HideXmasFeatures)
			setCreativeTab(ChaosIncMod.xmas);
		setSoundType(SoundType.CLOTH);
	}
}