package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class CustomStairs extends BlockStairs {
	public CustomStairs(Block stairType) {
		super(stairType.getDefaultState());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		useNeighborBrightness = true;
	}
}