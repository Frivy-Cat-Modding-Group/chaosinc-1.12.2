package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemCactusDoor extends ItemDoor {
	public ItemCactusDoor() {
		super(Doors.CactusDoor);
		setUnlocalizedName("ItemCactusDoor");
		setRegistryName("cactus_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}