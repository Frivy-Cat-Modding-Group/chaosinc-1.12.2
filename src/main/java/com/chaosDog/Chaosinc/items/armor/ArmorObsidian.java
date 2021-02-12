package com.chaosDog.Chaosinc.items.armor;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.init.ModItems.Armor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Iterator;

public class ArmorObsidian extends ItemArmor {
	public ArmorObsidian(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn,
			String unlocalizedName) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
		setCreativeTab(CreativeTabs.COMBAT);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int ArmorObsidianPieces = 0;
		if (player.getArmorInventoryList() != null) {
			Iterator<ItemStack> iterator = player.getArmorInventoryList().iterator();
			while (iterator.hasNext()) {
				ItemStack stack = iterator.next();
				if (stack != null) {
					if (stack.getItem() instanceof ArmorObsidian) {
						ArmorObsidian item = (ArmorObsidian) stack.getItem();
						if (item.getArmorMaterial() == Armor.obsidianMaterial)
							ArmorObsidianPieces++;
					}
				}
			}
		}
		if (ArmorObsidianPieces == 4)
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(12)));
	}
}