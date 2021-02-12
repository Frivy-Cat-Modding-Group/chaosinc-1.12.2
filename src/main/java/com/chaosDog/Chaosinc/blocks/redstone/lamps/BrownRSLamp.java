package com.chaosDog.Chaosinc.blocks.redstone.lamps;

import com.chaosDog.Chaosinc.init.ModBlocks.Lamps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

;

public class BrownRSLamp extends BlockRedstoneLight {
	@SuppressWarnings("static-access")
	public BrownRSLamp() {
		super(false);
		setUnlocalizedName("BrownRSLamp");
		setRegistryName("brown_redstone_lamp");
		setSoundType(blockSoundType.GLASS);
		setCreativeTab(CreativeTabs.REDSTONE);
	}
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, Lamps.LitBrownLamp.getDefaultState(), 2);
            }
        }
    }
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, Lamps.LitBrownLamp.getDefaultState(), 2);
            }
        }
    }
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Lamps.BrownLamp);
    }
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Lamps.BrownLamp);
    }
}