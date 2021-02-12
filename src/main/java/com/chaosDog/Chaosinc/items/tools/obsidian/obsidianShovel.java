package com.chaosDog.Chaosinc.items.tools.obsidian;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class obsidianShovel extends ItemSpade {
	public obsidianShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}