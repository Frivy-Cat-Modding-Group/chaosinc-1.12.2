package com.chaosDog.Chaosinc.blocks.xmas;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModItems.Armor;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;
import com.chaosDog.Chaosinc.init.ModItems.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class Present extends Block {
	public Present() {
		super(Material.CLOTH);
		setUnlocalizedName("Present");
		setRegistryName("present");
		setHardness(.01F);
		setResistance(.01F);
		if (!ModConfig.HideXmasFeatures)
			setCreativeTab(ChaosIncMod.xmas);
		setSoundType(SoundType.PLANT);
	}
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		int chance = rand.nextInt(58);
		if (chance == 0) {
			return Tools.emeraldPickaxe;
		}
		if (chance == 1) {
			return Tools.obsidianPickaxe;
		}
		if (chance == 2) {
			return Tools.redstonePickaxe;
		}
		if (chance == 3) {
			return Tools.rubyPickaxe;
		}
		if (chance == 4) {
			return Tools.emeraldAxe;
		}
		if (chance == 5) {
			return Tools.obsidianAxe;
		}
		if (chance == 6) {
			return Tools.redstoneAxe;
		}
		if (chance == 7) {
			return Tools.rubyAxe;
		}
		if (chance == 8) {
			return Tools.emeraldSword;
		}
		if (chance == 9) {
			return Tools.obsidianSword;
		}
		if (chance == 10) {
			return Tools.rubySword;
		}
		if (chance == 11) {
			return Tools.emeraldShovel;
		}
		if (chance == 12) {
			return Tools.obsidianShovel;
		}
		if (chance == 13) {
			return Tools.redstoneShovel;
		}
		if (chance == 14) {
			return Tools.rubyShovel;
		}
		if (chance == 15) {
			return Tools.emeraldHoe;
		}
		if (chance == 16) {
			return Tools.obsidianHoe;
		}
		if (chance == 17) {
			return Tools.redstoneHoe;
		}
		if (chance == 18) {
			return Tools.rubyHoe;
		}
		if (chance == 19) {
			return Items.DIAMOND_AXE;
		}
		if (chance == 20) {
			return Items.DIAMOND_HOE;
		}
		if (chance == 21) {
			return Items.DIAMOND_SHOVEL;
		}
		if (chance == 22) {
			return Items.DIAMOND_PICKAXE;
		}
		if (chance == 23) {
			return Items.DIAMOND_SWORD;
		}
		if (chance == 24) {
			return Items.IRON_AXE;
		}
		if (chance == 25) {
			return Items.IRON_HOE;
		}
		if (chance == 26) {
			return Items.IRON_SHOVEL;
		}
		if (chance == 27) {
			return Items.IRON_PICKAXE;
		}
		if (chance == 28) {
			return Items.IRON_SWORD;
		}
		if (chance == 29) {
			return Armor.emeraldHelmet;
		}
		if (chance == 30) {
			return Armor.emeraldChestplate;
		}
		if (chance == 31) {
			return Armor.emeraldLeggings;
		}
		if (chance == 32) {
			return Armor.emeraldBoots;
		}
		if (chance == 33) {
			return Armor.rubyHelmet;
		}
		if (chance == 34) {
			return Armor.rubyChestplate;
		}
		if (chance == 35) {
			return Armor.rubyLeggings;
		}
		if (chance == 36) {
			return Armor.rubyBoots;
		}
		if (chance == 37) {
			return Armor.obsidianHelmet;
		}
		if (chance == 38) {
			return Armor.obsidianChestplate;
		}
		if (chance == 39) {
			return Armor.obsidianLeggings;
		}
		if (chance == 40) {
			return Armor.obsidianBoots;
		}
		if (chance == 41) {
			return Armor.redstoneHelmet;
		}
		if (chance == 42) {
			return Armor.redstoneChestplate;
		}
		if (chance == 43) {
			return Armor.redstoneLeggings;
		}
		if (chance == 44) {
			return Armor.redstoneBoots;
		}
		if (chance == 45) {
			return Items.IRON_HELMET;
		}
		if (chance == 46) {
			return Items.IRON_CHESTPLATE;
		}
		if (chance == 47) {
			return Items.IRON_LEGGINGS;
		}
		if (chance == 48) {
			return Items.IRON_BOOTS;
		}
		if (chance == 49) {
			return Items.DIAMOND_HELMET;
		}
		if (chance == 50) {
			return Items.DIAMOND_CHESTPLATE;
		}
		if (chance == 51) {
			return Items.DIAMOND_LEGGINGS;
		}
		if (chance == 52) {
			return Items.DIAMOND_BOOTS;
		}
		if (chance == 53) {
			return Items.DIAMOND;
		}
		if (chance == 54) {
			return Items.EMERALD;
		}
		if (chance == 55) {
			return Items.GOLD_INGOT;
		}
		if (chance == 56) {
			return Items.IRON_INGOT;
		}
		if (chance == 57) {
			return MiscItems.Ruby;
		}
		return Items.REDSTONE;
	}
}