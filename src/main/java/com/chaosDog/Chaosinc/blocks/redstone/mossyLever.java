package com.chaosDog.Chaosinc.blocks.redstone;

import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;

public class mossyLever extends BlockLever{
	public mossyLever()
	{
		setUnlocalizedName("mossyLever");
		setRegistryName("mossy_lever");
		setSoundType(SoundType.WOOD);
    }
}