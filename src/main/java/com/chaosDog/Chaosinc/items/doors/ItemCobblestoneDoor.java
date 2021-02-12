package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemCobblestoneDoor extends ItemDoor {
	public ItemCobblestoneDoor() {
		super(Doors.CobblestoneDoor);
		setUnlocalizedName("ItemCobblestoneDoor");
		setRegistryName("cobblestone_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}