package com.chaosDog.Chaosinc.items.tools.emerald;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class emeraldShovel extends ItemSpade {
	public emeraldShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}