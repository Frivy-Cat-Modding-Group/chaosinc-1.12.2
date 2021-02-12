package com.chaosDog.Chaosinc.items.tools.obsidian;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class obsidianHoe extends ItemHoe {

	public obsidianHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}