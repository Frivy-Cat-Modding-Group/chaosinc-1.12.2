package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemAndesiteDoor extends ItemDoor {
	public ItemAndesiteDoor() {
		super(Doors.AndesiteDoor);
		setUnlocalizedName("ItemAndesiteDoor");
		setRegistryName("andesite_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}