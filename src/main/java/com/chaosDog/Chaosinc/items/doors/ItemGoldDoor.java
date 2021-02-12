package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemGoldDoor extends ItemDoor {
	public ItemGoldDoor() {
		super(Doors.GoldDoor);
		setUnlocalizedName("ItemGoldDoor");
		setRegistryName("gold_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
