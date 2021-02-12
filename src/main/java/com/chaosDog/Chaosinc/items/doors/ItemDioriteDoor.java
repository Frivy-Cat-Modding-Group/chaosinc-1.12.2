package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemDioriteDoor extends ItemDoor {
	public ItemDioriteDoor() {
		super(Doors.DioriteDoor);
		setUnlocalizedName("ItemDioriteDoor");
		setRegistryName("diorite_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
