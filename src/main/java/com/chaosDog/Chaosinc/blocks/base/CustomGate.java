package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class CustomGate extends BlockFenceGate {
	public CustomGate(SoundType sound) {
		super(BlockPlanks.EnumType.DARK_OAK);
		setCreativeTab(CreativeTabs.REDSTONE);
		setSoundType(sound);
	}

	@Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof CustomGate &&
            state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.MIDDLE_POLE)
        {
            Block connector = world.getBlockState(pos.offset(facing)).getBlock();
            return connector instanceof BlockFence || connector instanceof BlockWall || connector instanceof CustomWall;
        }
        return false;
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis enumfacing$axis = ((EnumFacing)state.getValue(FACING)).getAxis();
        Block northBlock = worldIn.getBlockState(pos.north()).getBlock();
        Block southBlock = worldIn.getBlockState(pos.south()).getBlock();
        Block eastBlock = worldIn.getBlockState(pos.east()).getBlock();
        Block westBlock = worldIn.getBlockState(pos.west()).getBlock();

        if (enumfacing$axis == EnumFacing.Axis.Z && (westBlock instanceof BlockWall || eastBlock instanceof BlockWall || westBlock instanceof CustomWall || eastBlock instanceof CustomWall) || enumfacing$axis == EnumFacing.Axis.X && (northBlock instanceof BlockWall || southBlock instanceof BlockWall || northBlock instanceof CustomWall || southBlock instanceof CustomWall))
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        return state;
    }
}