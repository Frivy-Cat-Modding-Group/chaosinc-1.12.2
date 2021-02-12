package com.chaosDog.Chaosinc.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class sugarBlock extends BlockFalling{
	public sugarBlock() {
		super(Material.SAND);
		setUnlocalizedName("sugarBlock");
		setRegistryName("sugar_block");
		setHardness(.5F);
		setResistance(2.5F);
		setSoundType(SoundType.SAND);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	@Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
        return true;
    }
}