package com.chaosDog.Chaosinc.items.tools.obsidian;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class obsidianSword extends ItemSword {
	public obsidianSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}