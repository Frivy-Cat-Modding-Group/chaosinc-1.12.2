package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.Crystal;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;

import net.minecraft.init.Items;

public class Crystals {
	public static Crystal diamondCrystal = new Crystal(Items.DIAMOND);
	public static Crystal emeraldCrystal = new Crystal(Items.EMERALD);
	public static Crystal quartzCrystal = new Crystal(Items.QUARTZ);
	public static Crystal redstoneCrystal = new Crystal(Items.REDSTONE);
	public static Crystal rubyCrystal = new Crystal(MiscItems.Ruby);

	public static void initAndRegister() {
		Utils.regBlock(diamondCrystal.setUnlocalizedName("diamondCrystal").setRegistryName("diamond_crystal"));
		Utils.regBlock(emeraldCrystal.setUnlocalizedName("emeraldCrystal").setRegistryName("emerald_crystal"));
		Utils.regBlock(quartzCrystal.setUnlocalizedName("quartzCrystal").setRegistryName("quartz_crystal"));
		Utils.regBlock(redstoneCrystal.setUnlocalizedName("redstoneCrystal").setRegistryName("redstone_crystal"));
		Utils.regBlock(rubyCrystal.setUnlocalizedName("rubyCrystal").setRegistryName("ruby_crystal"));

	}
	public static void regRenders() {
		Utils.regRender(diamondCrystal);
		Utils.regRender(emeraldCrystal);
		Utils.regRender(quartzCrystal);
		Utils.regRender(redstoneCrystal);
		Utils.regRender(rubyCrystal);
		
	}
}
