package com.chaosDog.Chaosinc.items.tools.redstone;

import com.chaosDog.Chaosinc.Reference;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class redstoneShovel extends ItemSpade {
	public redstoneShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}