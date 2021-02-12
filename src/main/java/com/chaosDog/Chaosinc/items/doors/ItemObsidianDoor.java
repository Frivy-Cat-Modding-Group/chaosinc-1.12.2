package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemObsidianDoor extends ItemDoor {
	public ItemObsidianDoor() {
		super(Doors.ObsidianDoor);
		setUnlocalizedName("ItemObsidianDoor");
		setRegistryName("obsidian_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}