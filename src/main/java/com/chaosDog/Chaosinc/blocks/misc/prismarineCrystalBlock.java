package com.chaosDog.Chaosinc.blocks.misc;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class prismarineCrystalBlock extends BlockBreakable {
	public prismarineCrystalBlock() {
		super(Material.GLASS, true);
		setUnlocalizedName("prismarineCrystalBlock");
		setRegistryName("prismarine_crystal_block");
		setHardness(.5F);
		setResistance(1F);
		setLightLevel(1F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();
        
			if (this == Misc.prismarineCrystalBlock)
	        {
	            if (blockState != iblockstate)
	            {
	                return true;
	            }
	            if (block == this)
	            {
	                return false;
	            }
	        }
			return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	//get item to drop
    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.PRISMARINE_CRYSTALS;
    }

    //get amount of items to drop
    @Override
	public int quantityDropped(Random random)
    {
		return 4;
    }
}