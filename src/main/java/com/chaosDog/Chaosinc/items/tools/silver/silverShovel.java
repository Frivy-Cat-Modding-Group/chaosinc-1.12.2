package com.chaosDog.Chaosinc.items.tools.silver;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class silverShovel extends ItemSpade {
	public silverShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}