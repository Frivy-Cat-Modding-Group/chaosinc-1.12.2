package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class spike extends Block {
	protected static final AxisAlignedBB SPIKE = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

	public spike() {
		super(Material.CIRCUITS);
		setResistance(10F);
		setHardness(5F);
		setCreativeTab(CreativeTabs.REDSTONE);
		setUnlocalizedName("spike");
		setRegistryName("spike");
		setSoundType(SoundType.METAL);
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return SPIKE;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (!(entityIn instanceof EntityLivingBase))
			return;
		if (!(entityIn instanceof EntityLivingBase))
			return;
		EntityLivingBase creature= (EntityLivingBase) entityIn;
		creature.attackEntityFrom(DamageSource.CACTUS, 4F);
    }
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!(entityIn instanceof EntityLivingBase))
			return;
		if (!(entityIn instanceof EntityLivingBase))
			return;
		EntityLivingBase creature= (EntityLivingBase) entityIn;
		creature.attackEntityFrom(DamageSource.CACTUS, 4F);
	}
}
