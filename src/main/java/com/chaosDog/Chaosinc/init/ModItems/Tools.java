package com.chaosDog.Chaosinc.init.ModItems;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.items.tools.emerald.emeraldAxe;
import com.chaosDog.Chaosinc.items.tools.emerald.emeraldHoe;
import com.chaosDog.Chaosinc.items.tools.emerald.emeraldPickaxe;
import com.chaosDog.Chaosinc.items.tools.emerald.emeraldShovel;
import com.chaosDog.Chaosinc.items.tools.emerald.emeraldSword;
import com.chaosDog.Chaosinc.items.tools.obsidian.obsidianAxe;
import com.chaosDog.Chaosinc.items.tools.obsidian.obsidianHoe;
import com.chaosDog.Chaosinc.items.tools.obsidian.obsidianPickaxe;
import com.chaosDog.Chaosinc.items.tools.obsidian.obsidianShovel;
import com.chaosDog.Chaosinc.items.tools.obsidian.obsidianSword;
import com.chaosDog.Chaosinc.items.tools.redstone.redstoneAxe;
import com.chaosDog.Chaosinc.items.tools.redstone.redstoneHoe;
import com.chaosDog.Chaosinc.items.tools.redstone.redstonePickaxe;
import com.chaosDog.Chaosinc.items.tools.redstone.redstoneShovel;
import com.chaosDog.Chaosinc.items.tools.redstone.redstoneSword;
import com.chaosDog.Chaosinc.items.tools.ruby.rubyAxe;
import com.chaosDog.Chaosinc.items.tools.ruby.rubyHoe;
import com.chaosDog.Chaosinc.items.tools.ruby.rubyPickaxe;
import com.chaosDog.Chaosinc.items.tools.ruby.rubyShovel;
import com.chaosDog.Chaosinc.items.tools.ruby.rubySword;
import com.chaosDog.Chaosinc.items.tools.silver.silverAxe;
import com.chaosDog.Chaosinc.items.tools.silver.silverHoe;
import com.chaosDog.Chaosinc.items.tools.silver.silverPickaxe;
import com.chaosDog.Chaosinc.items.tools.silver.silverShovel;
import com.chaosDog.Chaosinc.items.tools.silver.silverSword;
import com.chaosDog.Chaosinc.items.tools.stoneBanana;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class Tools {
	//cactus
	public static ItemSword cactusSword;
	public static ItemTool cactusAxe;
	public static ItemTool cactusPickaxe;
	public static ItemHoe cactusHoe;
	public static ItemTool cactusShovel;
	// emerald
	public static ItemSword emeraldSword;
	public static ItemTool emeraldAxe;
	public static ItemTool emeraldPickaxe;
	public static ItemHoe emeraldHoe;
	public static ItemTool emeraldShovel;
	// ruby
	public static ItemSword rubySword;
	public static ItemTool rubyAxe;
	public static ItemTool rubyPickaxe;
	public static ItemHoe rubyHoe;
	public static ItemTool rubyShovel;
	// obsidian
	public static ItemSword obsidianSword;
	public static ItemTool obsidianAxe;
	public static ItemTool obsidianPickaxe;
	public static ItemHoe obsidianHoe;
	public static ItemTool obsidianShovel;
	// redstone
	public static ItemSword redstoneSword;
	public static ItemTool redstoneAxe;
	public static ItemTool redstonePickaxe;
	public static ItemHoe redstoneHoe;
	public static ItemTool redstoneShovel;
	//silver
	public static ItemSword silverSword;
	public static ItemTool silverAxe;
	public static ItemTool silverPickaxe;
	public static ItemHoe silverHoe;
	public static ItemTool silverShovel;
	//stone banana
	public static ItemTool stoneBanana;
	
	//tool material
	private static final ToolMaterial silverMaterial = EnumHelper.addToolMaterial(Reference.MOD_ID + ":silver", 2, 64, 12.0F, 2F, 22);
	private static final ToolMaterial emeraldMaterial = EnumHelper.addToolMaterial(Reference.MOD_ID + ":emerald", 4,1700, 9F, 4F, 30);
	private static final ToolMaterial rubyMaterial = EnumHelper.addToolMaterial(Reference.MOD_ID + ":ruby", 4, 1700, 9F,4F, 30);
	private static final ToolMaterial obsidianMaterial = EnumHelper.addToolMaterial(Reference.MOD_ID + ":obsidian", 5,3122, 10F, 5F, 30);
	private static final ToolMaterial redstoneMaterial = EnumHelper.addToolMaterial(Reference.MOD_ID + ":redstone", 6,16, 100F, 10F, 45);
	private static final ToolMaterial Cactus = EnumHelper.addToolMaterial(Reference.MOD_ID + ":cactus", 0, 59, 2F, 0F, 15);
	private static final ToolMaterial stone = EnumHelper.addToolMaterial(Reference.MOD_ID + ":stone", 1, 1, 1f, 1f, 1);
	
	public static void initAndRegister() {
		Utils.regItem(emeraldPickaxe = new emeraldPickaxe(emeraldMaterial, "emerald_pickaxe"));
		Utils.regItem(emeraldAxe = new emeraldAxe(emeraldMaterial, "emerald_axe"));
		Utils.regItem(emeraldShovel = new emeraldShovel(emeraldMaterial, "emerald_shovel"));
		Utils.regItem(emeraldHoe = new emeraldHoe(emeraldMaterial, "emerald_hoe"));
		Utils.regItem(emeraldSword = new emeraldSword(emeraldMaterial, "emerald_sword"));
		
		Utils.regItem(rubyPickaxe = new rubyPickaxe(rubyMaterial, "ruby_pickaxe"));
		Utils.regItem(rubyAxe = new rubyAxe(rubyMaterial, "ruby_axe"));
		Utils.regItem(rubyShovel = new rubyShovel(rubyMaterial, "ruby_shovel"));
		Utils.regItem(rubyHoe = new rubyHoe(rubyMaterial, "ruby_hoe"));
		Utils.regItem(rubySword = new rubySword(rubyMaterial, "ruby_sword"));
		
		Utils.regItem(silverPickaxe = new silverPickaxe(silverMaterial, "silver_pickaxe"));
		Utils.regItem(silverAxe = new silverAxe(silverMaterial, "silver_axe"));
		Utils.regItem(silverShovel = new silverShovel(silverMaterial, "silver_shovel"));
		Utils.regItem(silverHoe = new silverHoe(silverMaterial, "silver_hoe"));
		Utils.regItem(silverSword = new silverSword(silverMaterial, "silver_sword"));
		
		Utils.regItem(obsidianPickaxe = new obsidianPickaxe(obsidianMaterial, "obsidian_pickaxe"));
		Utils.regItem(obsidianAxe = new obsidianAxe(obsidianMaterial, "obsidian_axe"));
		Utils.regItem(obsidianShovel = new obsidianShovel(obsidianMaterial, "obsidian_shovel"));
		Utils.regItem(obsidianHoe = new obsidianHoe(obsidianMaterial, "obsidian_hoe"));
		Utils.regItem(obsidianSword = new obsidianSword(obsidianMaterial, "obsidian_sword"));

		Utils.regItem(cactusPickaxe = new redstonePickaxe(Cactus, "cactus_pickaxe"));
		Utils.regItem(cactusAxe = new redstoneAxe(Cactus, "cactus_axe"));
		Utils.regItem(cactusShovel = new redstoneShovel(Cactus, "cactus_shovel"));
		Utils.regItem(cactusHoe = new redstoneHoe(Cactus, "cactus_hoe"));
		Utils.regItem(cactusSword = new redstoneSword(Cactus, "cactus_sword"));
		
		Utils.regItem(redstonePickaxe = new redstonePickaxe(redstoneMaterial, "redstone_pickaxe"));
		Utils.regItem(redstoneAxe = new redstoneAxe(redstoneMaterial, "redstone_axe"));
		Utils.regItem(redstoneShovel = new redstoneShovel(redstoneMaterial, "redstone_shovel"));
		Utils.regItem(redstoneHoe = new redstoneHoe(redstoneMaterial, "redstone_hoe"));
		Utils.regItem(redstoneSword = new redstoneSword(redstoneMaterial, "redstone_sword"));

		Utils.regItem(stoneBanana = new stoneBanana(stone, "stone_banana"));
	}
	
	public static void regRenders() {

		//cactus
		Utils.regRender(cactusPickaxe);
		Utils.regRender(cactusAxe);
		Utils.regRender(cactusShovel);
		Utils.regRender(cactusHoe);
		Utils.regRender(cactusSword);

		//emerald
		Utils.regRender(emeraldPickaxe);
		Utils.regRender(emeraldAxe);
		Utils.regRender(emeraldShovel);
		Utils.regRender(emeraldHoe);
		Utils.regRender(emeraldSword);

		//ruby
		Utils.regRender(rubyPickaxe);
		Utils.regRender(rubyAxe);
		Utils.regRender(rubyShovel);
		Utils.regRender(rubyHoe);
		Utils.regRender(rubySword);

		//obsidian
		Utils.regRender(obsidianPickaxe);
		Utils.regRender(obsidianAxe);
		Utils.regRender(obsidianShovel);
		Utils.regRender(obsidianHoe);
		Utils.regRender(obsidianSword);

		//silver
		Utils.regRender(silverPickaxe);
		Utils.regRender(silverAxe);
		Utils.regRender(silverShovel);
		Utils.regRender(silverHoe);
		Utils.regRender(silverSword);

		//redstone
		Utils.regRender(redstonePickaxe);
		Utils.regRender(redstoneAxe);
		Utils.regRender(redstoneShovel);
		Utils.regRender(redstoneHoe);
		Utils.regRender(redstoneSword);

		//stone banana
		Utils.regRender(stoneBanana);
	}
}
