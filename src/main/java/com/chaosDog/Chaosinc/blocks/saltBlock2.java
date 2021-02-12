package com.chaosDog.Chaosinc.blocks;

import com.chaosDog.Chaosinc.init.ModItems.MiscItems;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class saltBlock2 extends BlockFalling{
	public saltBlock2() {
		super(Material.SAND);
		setUnlocalizedName("saltBlock2");
		setRegistryName("salt_block2");
		setHardness(.5F);
		setResistance(2.5F);
		setSoundType(SoundType.SAND);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return MiscItems.salt;
    }

    //get amount of items to drop
    @Override
	public int quantityDropped(Random random)
    {
		return 4;
    }
}