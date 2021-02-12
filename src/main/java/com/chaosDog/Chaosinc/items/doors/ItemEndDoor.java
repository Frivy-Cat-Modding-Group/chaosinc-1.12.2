package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemEndDoor extends ItemDoor {
	public ItemEndDoor() {
		super(Doors.EndDoor);
		setUnlocalizedName("ItemEndDoor");
		setRegistryName("end_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
