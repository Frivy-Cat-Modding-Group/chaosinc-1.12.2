package com.chaosDog.Chaosinc.items.tools.cactus;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class cactusSword extends ItemSword {
	public cactusSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}