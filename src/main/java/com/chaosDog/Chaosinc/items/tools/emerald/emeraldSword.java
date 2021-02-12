package com.chaosDog.Chaosinc.items.tools.emerald;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class emeraldSword extends ItemSword {
	public emeraldSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}