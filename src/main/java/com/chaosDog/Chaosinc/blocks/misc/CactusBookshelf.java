package com.chaosDog.Chaosinc.blocks.misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class CactusBookshelf extends Block{
	public CactusBookshelf() {
		super(Material.WOOD);
		setUnlocalizedName("CactusBookshelf");
		setRegistryName("cactus_bookshelf");
		setHardness(2.5F);
		setResistance(5F);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos)
    {
        return this == (Misc.CactusBookshelf)? 1 : 0;
    }
}