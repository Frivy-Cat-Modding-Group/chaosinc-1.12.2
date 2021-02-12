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

public class GlowingObsidianDoor extends BlockDoor {
	public GlowingObsidianDoor() {
		super(Material.IRON);
		setUnlocalizedName("GlowingObsidianDoor");
		setRegistryName("glowing_obsidian_door_block");
		setHardness(51F);
		setResistance(2000F);
		setHarvestLevel("pickaxe", 3);
		setLightLevel(.46666667F);
	}
	// the item is the door itself
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : DoorItems.GlowingObsidianDoor;
	}
	
	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(DoorItems.GlowingObsidianDoor);
    }
}