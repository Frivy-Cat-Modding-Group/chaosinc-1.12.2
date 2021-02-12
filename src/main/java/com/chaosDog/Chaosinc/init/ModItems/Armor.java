package com.chaosDog.Chaosinc.init.ModItems;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.items.armor.ArmorEmerald;
import com.chaosDog.Chaosinc.items.armor.ArmorObsidian;
import com.chaosDog.Chaosinc.items.armor.ArmorRedstone;
import com.chaosDog.Chaosinc.items.armor.ArmorRuby;
import com.chaosDog.Chaosinc.items.armor.ArmorSilver;
import com.chaosDog.Chaosinc.items.armor.SantaSuit;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Armor {
	public static ItemArmor santaJacket;
	public static ItemArmor santaPants;
	public static ItemArmor santaBoots;

	//silver
	public static ItemArmor silverHelmet;
	public static ItemArmor silverChestplate;
	public static ItemArmor silverLeggings;
	public static ItemArmor silverBoots;

	// emerald
	public static ItemArmor emeraldHelmet;
	public static ItemArmor emeraldChestplate;
	public static ItemArmor emeraldLeggings;
	public static ItemArmor emeraldBoots;

	// ruby
	public static ItemArmor rubyHelmet;
	public static ItemArmor rubyChestplate;
	public static ItemArmor rubyLeggings;
	public static ItemArmor rubyBoots;

	// redstone
	public static ItemArmor redstoneHelmet;
	public static ItemArmor redstoneChestplate;
	public static ItemArmor redstoneLeggings;
	public static ItemArmor redstoneBoots;

	// obsidian
	public static ItemArmor obsidianHelmet;
	public static ItemArmor obsidianChestplate;
	public static ItemArmor obsidianLeggings;
	public static ItemArmor obsidianBoots;
	
	//armor material
	public static ArmorMaterial santaSuit = EnumHelper.addArmorMaterial("santa", Reference.MOD_ID + ":santa", -1,new int[] { 0, 0, 0, 0 },0,SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F);
	public static ArmorMaterial emeraldMaterial = EnumHelper.addArmorMaterial("emerald", Reference.MOD_ID + ":emerald",35, new int[] { 4, 6, 8, 4 }, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
	public static ArmorMaterial rubyMaterial = EnumHelper.addArmorMaterial("ruby", Reference.MOD_ID + ":ruby", 35,new int[] { 4, 6, 8, 4 }, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);
	public static ArmorMaterial obsidianMaterial = EnumHelper.addArmorMaterial("obsidian",Reference.MOD_ID + ":obsidian",40,new int[] {5,7,9,4},35,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,4.0F);
	public static ArmorMaterial redstoneMaterial = EnumHelper.addArmorMaterial("redstone",Reference.MOD_ID + ":redstone",3,new int[]{8,10,12,7},40,SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,5.0F);
	public static ArmorMaterial silverMaterial = EnumHelper.addArmorMaterial("silver",Reference.MOD_ID + ":silver", 7, new int[] {2,5,6,2},25,SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	
	public static void initAndRegister() {
		Utils.regItem(santaJacket = new SantaSuit(santaSuit, 1, EntityEquipmentSlot.CHEST, "santa_jacket"));
		Utils.regItem(santaPants = new SantaSuit(santaSuit, 2, EntityEquipmentSlot.LEGS, "santa_pants"));
		Utils.regItem(santaBoots = new SantaSuit(santaSuit, 1, EntityEquipmentSlot.FEET, "santa_boots"));
		
		Utils.regItem(emeraldHelmet = new ArmorEmerald(emeraldMaterial, 1, EntityEquipmentSlot.HEAD, "emerald_helmet"));
		Utils.regItem(emeraldChestplate = new ArmorEmerald(emeraldMaterial, 1, EntityEquipmentSlot.CHEST,"emerald_chestplate"));
		Utils.regItem(emeraldLeggings = new ArmorEmerald(emeraldMaterial, 2, EntityEquipmentSlot.LEGS, "emerald_leggings"));
		Utils.regItem(emeraldBoots = new ArmorEmerald(emeraldMaterial, 1, EntityEquipmentSlot.FEET, "emerald_boots"));
		
		Utils.regItem(silverHelmet = new ArmorSilver(silverMaterial, 1, EntityEquipmentSlot.HEAD, "silver_helmet"));
		Utils.regItem(silverChestplate = new ArmorSilver(silverMaterial, 1, EntityEquipmentSlot.CHEST,"silver_chestplate"));
		Utils.regItem(silverLeggings = new ArmorSilver(silverMaterial, 2, EntityEquipmentSlot.LEGS, "silver_leggings"));
		Utils.regItem(silverBoots = new ArmorSilver(silverMaterial, 1, EntityEquipmentSlot.FEET, "silver_boots"));
		
		Utils.regItem(rubyHelmet = new ArmorRuby(rubyMaterial, 1, EntityEquipmentSlot.HEAD, "ruby_helmet"));
		Utils.regItem(rubyChestplate = new ArmorRuby(rubyMaterial, 1, EntityEquipmentSlot.CHEST, "ruby_chestplate"));
		Utils.regItem(rubyLeggings = new ArmorRuby(rubyMaterial, 2, EntityEquipmentSlot.LEGS, "ruby_leggings"));
		Utils.regItem(rubyBoots = new ArmorRuby(rubyMaterial, 1, EntityEquipmentSlot.FEET, "ruby_boots"));
		
		Utils.regItem(redstoneHelmet = new ArmorRedstone(redstoneMaterial, 1, EntityEquipmentSlot.HEAD, "redstone_helmet"));
		Utils.regItem(redstoneChestplate = new ArmorRedstone(redstoneMaterial, 1, EntityEquipmentSlot.CHEST,"redstone_chestplate"));
		Utils.regItem(redstoneLeggings = new ArmorRedstone(redstoneMaterial, 2, EntityEquipmentSlot.LEGS,"redstone_leggings"));
		Utils.regItem(redstoneBoots = new ArmorRedstone(redstoneMaterial, 1, EntityEquipmentSlot.FEET, "redstone_boots"));

		Utils.regItem(obsidianHelmet = new ArmorObsidian(obsidianMaterial, 1, EntityEquipmentSlot.HEAD, "obsidian_helmet"));
		Utils.regItem(obsidianChestplate = new ArmorObsidian(obsidianMaterial, 1, EntityEquipmentSlot.CHEST,"obsidian_chestplate"));
		Utils.regItem(obsidianLeggings = new ArmorObsidian(obsidianMaterial, 2, EntityEquipmentSlot.LEGS,"obsidian_leggings"));
		Utils.regItem(obsidianBoots = new ArmorObsidian(obsidianMaterial, 1, EntityEquipmentSlot.FEET, "obsidian_boots"));
	}
	
	public static void regRenders() {
		Utils.regRender(santaJacket);
		Utils.regRender(santaPants);
		Utils.regRender(santaBoots);

		//emerald
		Utils.regRender(emeraldHelmet);
		Utils.regRender(emeraldChestplate);
		Utils.regRender(emeraldLeggings);
		Utils.regRender(emeraldBoots);

		//ruby
		Utils.regRender(rubyHelmet);
		Utils.regRender(rubyChestplate);
		Utils.regRender(rubyLeggings);
		Utils.regRender(rubyBoots);

		//redstone
		Utils.regRender(redstoneHelmet);
		Utils.regRender(redstoneChestplate);
		Utils.regRender(redstoneLeggings);
		Utils.regRender(redstoneBoots);

		//obsidian
		Utils.regRender(obsidianHelmet);
		Utils.regRender(obsidianChestplate);
		Utils.regRender(obsidianLeggings);
		Utils.regRender(obsidianBoots);

		//silver
		Utils.regRender(silverHelmet);
		Utils.regRender(silverChestplate);
		Utils.regRender(silverLeggings);
		Utils.regRender(silverBoots);
	}
}
