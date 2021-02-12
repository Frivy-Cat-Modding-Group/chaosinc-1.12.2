package com.chaosDog.Chaosinc.items.tools.emerald;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class emeraldHoe extends ItemHoe {
	public emeraldHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}