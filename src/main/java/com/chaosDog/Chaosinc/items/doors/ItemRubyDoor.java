package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemRubyDoor extends ItemDoor {
	public ItemRubyDoor() {
		super(Doors.RubyDoor);
		setUnlocalizedName("ItemRubyDoor");
		setRegistryName("ruby_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}