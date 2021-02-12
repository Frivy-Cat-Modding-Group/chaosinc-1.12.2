package com.chaosDog.Chaosinc.items.tools.cactus;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class cactusPickaxe extends ItemPickaxe {
	public cactusPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}