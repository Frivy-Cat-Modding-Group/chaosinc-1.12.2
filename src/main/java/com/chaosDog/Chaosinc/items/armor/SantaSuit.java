package com.chaosDog.Chaosinc.items.armor;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

public class SantaSuit extends ItemArmor {
	public SantaSuit(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn,
			String unlocalizedName) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
		if (!ModConfig.HideXmasFeatures)
			setCreativeTab(ChaosIncMod.xmas);
	}
}
