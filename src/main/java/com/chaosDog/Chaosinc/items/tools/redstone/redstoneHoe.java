package com.chaosDog.Chaosinc.items.tools.redstone;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class redstoneHoe extends ItemHoe {
	public redstoneHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}