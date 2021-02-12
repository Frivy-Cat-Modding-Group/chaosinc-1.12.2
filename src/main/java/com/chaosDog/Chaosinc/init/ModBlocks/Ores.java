package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.saltBlock2;
import com.chaosDog.Chaosinc.blocks.sugarBlock;
import com.chaosDog.Chaosinc.blocks.base.BasicBlock;
import com.chaosDog.Chaosinc.blocks.base.CustomOre;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;

public class Ores {
	//ores
	public static CustomOre CoalOreVoid = new CustomOre(Items.COAL, 4, 0);
	public static CustomOre EnderPearlOre = new CustomOre(Items.ENDER_PEARL, 1, 2);
	public static CustomOre EndRubyOre = new CustomOre(MiscItems.Ruby, 1, 2);
	public static Block GoldOreNether = new Block(Material.ROCK);
	public static Block GoldOreVoid = new Block(Material.ROCK);
	public static Block IronOreVoid = new Block(Material.ROCK);
	public static CustomOre RubyOre = new CustomOre(MiscItems.Ruby, 1, 2);
	public static CustomOre SaltOre = new CustomOre(MiscItems.salt, 6, 0);
	public static Block SilverOre = new Block(Material.ROCK);
	public static Block SilverOreNether = new Block(Material.ROCK);
	public static Block SilverOreVoid = new Block(Material.ROCK);
	public static CustomOre VoidPearlOre = new CustomOre(MiscItems.VoidPearl, 1, 2);

	//resource blocks
	public static BasicBlock blazeBlock = new BasicBlock(Material.IRON, SoundType.METAL, true);
	public static BasicBlock endPearlBlock = new BasicBlock(Material.IRON, SoundType.METAL, true);
	public static BasicBlock RubyBlock = new BasicBlock(Material.IRON, SoundType.METAL, true);
	public static BasicBlock SaltBlock = new BasicBlock(Material.ROCK, SoundType.STONE, true);
	public static BasicBlock SilverBlock = new BasicBlock(Material.IRON, SoundType.METAL, true);
	public static BlockFalling sugarBlock=new sugarBlock();
	public static BlockFalling saltBlock2=new saltBlock2();
	public static BasicBlock voidPearlBlock = new BasicBlock(Material.IRON, SoundType.METAL, true);

	public static void initAndRegister() {
		//ores
		GoldOreNether.setHarvestLevel("pickaxe", 2);
		GoldOreVoid.setHarvestLevel("pickaxe", 2);
		IronOreVoid.setHarvestLevel("pickaxe", 1);
		SilverOre.setHarvestLevel("pickaxe", 2);
		SilverOreNether.setHarvestLevel("pickaxe", 2);
		SilverOreVoid.setHarvestLevel("pickaxe", 2);

		Utils.regBlock(CoalOreVoid.setUnlocalizedName("VoidCoalOre").setRegistryName("void_coal_ore").setHardness(5F).setResistance(10F));
		Utils.regBlock(EnderPearlOre.setUnlocalizedName("EndPearlOre").setRegistryName("ender_pearl_ore").setHardness(5F).setResistance(10F));
		Utils.regBlock(EndRubyOre.setUnlocalizedName("EndRubyOre").setRegistryName("ender_ruby_ore").setHardness(5F).setResistance(10F));
		Utils.regBlock(GoldOreNether.setUnlocalizedName("NetherGoldOre").setRegistryName("nether_gold_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(GoldOreVoid.setUnlocalizedName("VoidGoldOre").setRegistryName("void_gold_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(IronOreVoid.setUnlocalizedName("VoidIronOre").setRegistryName("void_iron_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(RubyOre.setUnlocalizedName("RubyOre").setRegistryName("ruby_ore").setHardness(5F).setResistance(10F));
		Utils.regBlock(SaltOre.setUnlocalizedName("SaltOre").setRegistryName("salt_ore").setHardness(5F).setResistance(10F));
		Utils.regBlock(SilverOre.setUnlocalizedName("SilverOre").setRegistryName("silver_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(SilverOreNether.setUnlocalizedName("NetherSilverOre").setRegistryName("nether_silver_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(SilverOreVoid.setUnlocalizedName("VoidSilverOre").setRegistryName("void_silver_ore").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(VoidPearlOre.setUnlocalizedName("VoidPearlOre").setRegistryName("void_pearl_ore").setHardness(5F).setResistance(10F));

		//resource blocks
		blazeBlock.setHarvestLevel("pickaxe", 0);
		endPearlBlock.setHarvestLevel("pickaxe", 0);
		RubyBlock.setHarvestLevel("pickaxe", 2);
		SaltBlock.setHarvestLevel("pickaxe", 0);
		SilverBlock.setHarvestLevel("pickaxe", 2);
		voidPearlBlock.setHarvestLevel("pickaxe",0);

		Utils.regBlock(blazeBlock.setUnlocalizedName("blazeBlock").setRegistryName("blaze_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightLevel(1F));
		Utils.regBlock(endPearlBlock.setUnlocalizedName("endPearlBlock").setRegistryName("ender_pearl_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(RubyBlock.setUnlocalizedName("BlockRuby").setRegistryName("ruby_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(SaltBlock.setUnlocalizedName("SaltBlock").setRegistryName("salt_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(SilverBlock.setUnlocalizedName("SilverBlock").setRegistryName("silver_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(sugarBlock);
		Utils.regBlock(saltBlock2);
		Utils.regBlock(voidPearlBlock.setUnlocalizedName("voidPearlBlock").setRegistryName("void_pearl_block").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
	}

	public static void regRenders() {
		Utils.regRender(blazeBlock);
		Utils.regRender(CoalOreVoid);
		Utils.regRender(endPearlBlock);
		Utils.regRender(EnderPearlOre);
		Utils.regRender(EndRubyOre);
		Utils.regRender(GoldOreNether);
		Utils.regRender(GoldOreVoid);
		Utils.regRender(IronOreVoid);
		Utils.regRender(RubyBlock);
		Utils.regRender(RubyOre);
		Utils.regRender(SaltBlock);
		Utils.regRender(SaltOre);
		Utils.regRender(SilverBlock);
		Utils.regRender(SilverOre);
		Utils.regRender(SilverOreNether);
		Utils.regRender(SilverOreVoid);
		Utils.regRender(sugarBlock);
		Utils.regRender(saltBlock2);
		Utils.regRender(voidPearlBlock);
		Utils.regRender(VoidPearlOre);
	}
}