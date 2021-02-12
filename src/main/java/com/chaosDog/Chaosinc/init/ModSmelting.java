package com.chaosDog.Chaosinc.init;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.init.ModItems.Armor;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;
import com.chaosDog.Chaosinc.init.ModItems.Tools;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmelting {
	//smelting recipes
	public static void register() {
		//cooked egg recipe
		GameRegistry.addSmelting(Items.EGG, new ItemStack(MiscItems.CookedEgg), .01F);

		//rubies and silver ingots can obtained by smelting their ores
		GameRegistry.addSmelting(Ores.RubyOre, new ItemStack(MiscItems.Ruby), 5F);
		GameRegistry.addSmelting(Ores.SilverOre, new ItemStack(MiscItems.SilverIngot), 5F);
		
		//smelting recipes for end ores
		GameRegistry.addSmelting(Ores.EndRubyOre, new ItemStack(MiscItems.Ruby), 5F);
		GameRegistry.addSmelting(Ores.EnderPearlOre, new ItemStack(Items.ENDER_PEARL), 5F);
		GameRegistry.addSmelting(Ores.VoidPearlOre, new ItemStack(MiscItems.VoidPearl), 5F);
		
		//smelting recipes for nether ores
		GameRegistry.addSmelting(Ores.SilverOreNether, new ItemStack(MiscItems.SilverIngot), 5F);
		GameRegistry.addSmelting(Ores.GoldOreNether, new ItemStack(Items.GOLD_INGOT), 5F);
		
		//smelting recipes for void ores
		GameRegistry.addSmelting(Ores.GoldOreVoid, new ItemStack(Items.GOLD_INGOT), 5F);
		GameRegistry.addSmelting(Ores.IronOreVoid, new ItemStack(Items.IRON_INGOT), 5F);
		GameRegistry.addSmelting(Ores.CoalOreVoid, new ItemStack(Items.COAL), 5F);
		GameRegistry.addSmelting(Ores.SilverOreVoid, new ItemStack(MiscItems.SilverIngot), 5F);
		
		//silver nuggets can be obtained by smelting stuff made of silver
		GameRegistry.addSmelting(Armor.silverHelmet, new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Armor.silverChestplate,new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Armor.silverLeggings,new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Armor.silverBoots,new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Tools.silverAxe, new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Tools.silverShovel, new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Tools.silverPickaxe, new ItemStack(MiscItems.SilverNugget),5F);
		GameRegistry.addSmelting(Tools.silverHoe, new ItemStack(MiscItems.SilverNugget),5F);
		
		//soul glass
		GameRegistry.addSmelting(Blocks.SOUL_SAND, new ItemStack(Misc.soulGlass), .01F);
	}
}