package com.chaosDog.Chaosinc.blocks.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CustomOre extends Block {
	private final int max;
	private final Item itemDrop;
	/**
	 * Custom ore class for ores that drop items other than their own block
	 * @param drop
	 * Item to drop upon mining the ore
	 * @param maxAmount
	 * Maximum amount of items to drop
	 * @param harvestLevel
	 * Harvest level of the pickaxe needed to mine the ore
	 * Vanilla harvest levels:
	 * 0: Wood and Gold
	 * 1: Stone
	 * 2: Iron
	 * 3: Diamond
	 */
	public CustomOre(Item drop,int maxAmount,int harvestLevel) {
		super(Material.ROCK);
		itemDrop=drop;
		max=maxAmount;
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHarvestLevel("pickaxe", harvestLevel);
	}

	// get item to drop
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return itemDrop;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

	// get amount of items to drop
	@Override
	public int quantityDropped(Random random) {
		return 1+random.nextInt(max);
	}

	// is the pick enchanted with fortune?
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this
				.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
			int i = random.nextInt(fortune + 2) - 1;

			if (i < 0) {
				i = 0;
			}
			return this.quantityDropped(random) * (i + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	// spawn xp orbs
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
			int i = 0;
			i = MathHelper.getInt(rand, 0, 5);
			return i;
		}
		return 0;
	}

	// spawn the actual item
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(itemDrop);
	}
}