package com.chaosDog.Chaosinc.blocks.misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
public class OvergrownEndStone extends Block implements IGrowable{
	public OvergrownEndStone() {
		super(Material.WOOD);
		setUnlocalizedName("OverGrownEndStone");
		setRegistryName("overgrown_end_stone");
		setHardness(5F);
		setResistance(15F);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setSoundType(SoundType.STONE);
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Blocks.END_STONE.getItemDropped(Blocks.END_STONE.getDefaultState(), rand, fortune);
    }

	@Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
	}
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			if (worldIn.getLightFromNeighbors(pos.up()) >= 1 && worldIn.getBlockState(pos.up()).getLightOpacity() >= 1)
			{
				worldIn.setBlockState(pos, Blocks.END_STONE.getDefaultState());
			}
			else
			{
				if (worldIn.getLightFromNeighbors(pos.up()) == 0)
				{
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

						if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
							return;
						}

						IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
						IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

						if (iblockstate1.getBlock() == Blocks.END_STONE) {
							worldIn.setBlockState(blockpos, Misc.OvergrownEndStone.getDefaultState());
						}
					}
				}
			}
		}
	}
}