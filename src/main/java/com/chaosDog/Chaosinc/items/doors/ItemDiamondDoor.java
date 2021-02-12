package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemDiamondDoor extends ItemDoor {
	public ItemDiamondDoor() {
		super(Doors.DiamondDoor);
		setUnlocalizedName("ItemDiamondDoor");
		setRegistryName("diamond_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}
