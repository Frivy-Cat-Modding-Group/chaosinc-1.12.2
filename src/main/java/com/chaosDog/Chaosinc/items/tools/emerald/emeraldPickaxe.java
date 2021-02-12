package com.chaosDog.Chaosinc.items.tools.emerald;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class emeraldPickaxe extends ItemPickaxe {
	public emeraldPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}