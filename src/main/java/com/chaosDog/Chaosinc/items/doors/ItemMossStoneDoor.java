package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemMossStoneDoor extends ItemDoor {
	public ItemMossStoneDoor() {
		super(Doors.MossStoneDoor);
		setUnlocalizedName("ItemMossStoneDoor");
		setRegistryName("moss_stone_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}