package com.chaosDog.Chaosinc.blocks.xmas;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.config.ModConfig;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChristmasLeaves extends BlockOldLeaf {
	public ChristmasLeaves() {
		// these leaf blocks emit light
		setLightLevel(1F);
		setUnlocalizedName("ChristmasLeaves");
		setRegistryName("christmas_leaves");
		if (!ModConfig.HideXmasFeatures)
			setCreativeTab(ChaosIncMod.xmas);
	}
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.OAK.getMetadata()));
    }
}