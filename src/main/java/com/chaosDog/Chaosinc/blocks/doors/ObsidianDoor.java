package com.chaosDog.Chaosinc.blocks.doors;

import com.chaosDog.Chaosinc.init.ModItems.DoorItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class ObsidianDoor extends BlockDoor {
	@SuppressWarnings("unused")
	private static Item item= DoorItems.ObsidianDoor;
	public ObsidianDoor() {
		super(Material.IRON);
		setUnlocalizedName("ObsidianDoor");
		setRegistryName("obsidian_door_block");
		setHardness(51F);
		setResistance(2000F);
		setHarvestLevel("pickaxe", 3);
	}
	// the item is the door itself
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR :DoorItems.ObsidianDoor;
	}
	
	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(DoorItems.ObsidianDoor);
    }
}