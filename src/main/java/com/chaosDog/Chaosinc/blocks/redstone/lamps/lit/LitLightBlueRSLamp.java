package com.chaosDog.Chaosinc.blocks.redstone.lamps.lit;

import com.chaosDog.Chaosinc.init.ModBlocks.Lamps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

;

public class LitLightBlueRSLamp extends BlockRedstoneLight {
	public LitLightBlueRSLamp() {
		super(true);
		setUnlocalizedName("LightBlueRSLamp");
		setRegistryName("lit_light_blue_redstone_lamp");
		setSoundType(SoundType.GLASS);
		setLightLevel(1F);
	}
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (!worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, Lamps.LightBlueLamp.getDefaultState(), 2);
			}
		}
	}
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
        	if (!worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, Lamps.LightBlueLamp.getDefaultState(), 2);
            }
        }
    }
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, Lamps.LightBlueLamp.getDefaultState(), 2);
            }
        }
    }
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Lamps.LightBlueLamp);
    }
}