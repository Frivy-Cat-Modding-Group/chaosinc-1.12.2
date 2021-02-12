package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemSilverDoor extends ItemDoor {
	public ItemSilverDoor() {
		super(Doors.SilverDoor);
		setUnlocalizedName("ItemSilverDoor");
		setRegistryName("silver_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
