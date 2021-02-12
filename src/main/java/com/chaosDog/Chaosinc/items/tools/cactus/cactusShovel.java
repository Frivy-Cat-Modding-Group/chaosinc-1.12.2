package com.chaosDog.Chaosinc.items.tools.cactus;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class cactusShovel extends ItemSpade {
	public cactusShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}