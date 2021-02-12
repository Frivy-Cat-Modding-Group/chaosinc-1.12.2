package com.chaosDog.Chaosinc.creativeTabs;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ChristmasTab extends CreativeTabs {

public ChristmasTab() {
		super("christmas_stuff");
	}
	public ItemStack getTabIconItem() {
		return new ItemStack(Misc.ChristmasSapling);
	}
}