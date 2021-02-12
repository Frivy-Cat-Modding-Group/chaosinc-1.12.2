package com.chaosDog.Chaosinc.items.tools.silver;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class silverHoe extends ItemHoe {
	public silverHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}