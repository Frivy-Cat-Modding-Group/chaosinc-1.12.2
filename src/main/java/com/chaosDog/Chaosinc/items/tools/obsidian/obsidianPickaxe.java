package com.chaosDog.Chaosinc.items.tools.obsidian;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class obsidianPickaxe extends ItemPickaxe {
	public obsidianPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}