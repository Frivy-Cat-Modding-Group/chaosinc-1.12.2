package com.chaosDog.Chaosinc.items.tools.redstone;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class redstoneSword extends ItemSword {
	public redstoneSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}