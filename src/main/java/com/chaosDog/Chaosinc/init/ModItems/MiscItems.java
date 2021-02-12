package com.chaosDog.Chaosinc.init.ModItems;

import com.chaosDog.Chaosinc.Utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFood;

public class MiscItems {
	public static Item BlackDye = new Item();
	public static Item BlueDye = new Item();
	public static Item BrownDye = new Item();
	public static Item CactusStick = new Item();
	public static ItemFood CookedEgg = new ItemFood(12, true);
	public static Item Ruby = new Item();
	public static Item salt = new Item();
	public static Item SilverIngot = new Item();
	public static Item SilverNugget = new Item();
	public static Item VoidPearl=new ItemEnderPearl();
	
	public static void initAndRegister() {
		Utils.regItem(BlackDye.setUnlocalizedName("DyeBlack").setRegistryName("black_dye").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(BlueDye.setUnlocalizedName("DyeBlue").setRegistryName("blue_dye").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(BrownDye.setUnlocalizedName("DyeBrown").setRegistryName("brown_dye").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(CactusStick.setUnlocalizedName("CactusStick").setRegistryName("cactus_stick").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(CookedEgg.setUnlocalizedName("EggCooked").setRegistryName("cooked_egg"));
		Utils.regItem(Ruby.setUnlocalizedName("Ruby").setRegistryName("ruby").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(salt.setUnlocalizedName("salt").setRegistryName("salt").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(SilverIngot.setUnlocalizedName("SilverIngot").setRegistryName("silver_ingot").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(SilverNugget.setUnlocalizedName("SilverNugget").setRegistryName("silver_nugget").setCreativeTab(CreativeTabs.MISC));
		Utils.regItem(VoidPearl.setUnlocalizedName("VoidPearl").setRegistryName("void_pearl").setCreativeTab(CreativeTabs.MISC));
	}
	
	public static void regRenders() {
		Utils.regRender(BlackDye);
		Utils.regRender(BlueDye);
		Utils.regRender(BrownDye);
		Utils.regRender(CactusStick);
		Utils.regRender(CookedEgg);
		Utils.regRender(Ruby);
		Utils.regRender(salt);
		Utils.regRender(SilverIngot);
		Utils.regRender(SilverNugget);
		Utils.regRender(VoidPearl);
	}
}