package com.chaosDog.Chaosinc.items.doors;

import com.chaosDog.Chaosinc.init.ModBlocks.Doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDoor;

public class ItemGlowingObsidianDoor extends ItemDoor {
	public ItemGlowingObsidianDoor() {
		super(Doors.GlowingObsidianDoor);
		setUnlocalizedName("ItemGlowingObsidianDoor");
		setRegistryName("glowing_obsidian_door");
		setCreativeTab(CreativeTabs.REDSTONE);
	}
}