package com.chaosDog.Chaosinc.items.tools.ruby;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class rubyPickaxe extends ItemPickaxe {
	public rubyPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}