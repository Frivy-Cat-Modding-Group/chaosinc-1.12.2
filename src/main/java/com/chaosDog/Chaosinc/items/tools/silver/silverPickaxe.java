package com.chaosDog.Chaosinc.items.tools.silver;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class silverPickaxe extends ItemPickaxe {
	public silverPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}