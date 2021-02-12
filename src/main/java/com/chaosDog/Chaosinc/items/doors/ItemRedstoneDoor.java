package com.chaosDog.Chaosinc.items.doors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRedstoneDoor extends Item
{
    public ItemRedstoneDoor()
    {
        setCreativeTab(CreativeTabs.REDSTONE);
		setUnlocalizedName("ItemRedstoneDoor");
		setRegistryName("redstone_door");
    }
}