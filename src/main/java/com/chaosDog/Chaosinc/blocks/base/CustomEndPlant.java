package com.chaosDog.Chaosinc.blocks.base;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class CustomEndPlant extends BlockBush implements IGrowable {
	private final boolean big;
	public CustomEndPlant(boolean isBig) {
		big=isBig;
		setSoundType(SoundType.PLANT);
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		if (big)
			return FULL_BLOCK_AABB;
		return BUSH_AABB;
    }

	@Override
	protected boolean canSustainBush(IBlockState state)
    {
		return state.getBlock()== Misc.OvergrownEndStone||state.getBlock()==Blocks.END_STONE;
    }

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

	@Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }
	
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
    }

	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
}