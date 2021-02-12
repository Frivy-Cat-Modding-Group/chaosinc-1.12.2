package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemGraniteDoor extends ItemDoor {
	public ItemGraniteDoor() {
		super(Doors.GraniteDoor);
		setUnlocalizedName("ItemGraniteDoor");
		setRegistryName("granite_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
