package com.chaosDog.Chaosinc.blocks.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SaltLamp extends Block{
	public SaltLamp() {
		super(Material.GLASS);
		setUnlocalizedName("SaltLamp");
		setRegistryName("salt_lamp");
		setHardness(.5F);
		setResistance(1F);
		setLightLevel(1F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
}