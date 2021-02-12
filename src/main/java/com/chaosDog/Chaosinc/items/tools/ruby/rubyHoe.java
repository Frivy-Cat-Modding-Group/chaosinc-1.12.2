package com.chaosDog.Chaosinc.items.tools.ruby;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class rubyHoe extends ItemHoe {
	public rubyHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}