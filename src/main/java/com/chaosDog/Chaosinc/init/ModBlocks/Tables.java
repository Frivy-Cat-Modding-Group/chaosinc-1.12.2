package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomWorkBench;

public class Tables {
	//crafting tables
	public static CustomWorkBench AcaciaWorkbench = new CustomWorkBench();
	public static CustomWorkBench BirchWorkbench = new CustomWorkBench();
	public static CustomWorkBench CactusWorkbench = new CustomWorkBench();
	public static CustomWorkBench DarkOakWorkbench = new CustomWorkBench();
	public static CustomWorkBench JungleWorkbench = new CustomWorkBench();
	public static CustomWorkBench SpruceWorkbench = new CustomWorkBench();

	public static void initAndRegister() {
		//crafting tables
		Utils.regBlock(AcaciaWorkbench.setUnlocalizedName("CustomWorkbenchAcacia").setRegistryName("acacia_crafting_table"));
		Utils.regBlock(BirchWorkbench.setUnlocalizedName("CustomWorkbenchBirch").setRegistryName("birch_crafting_table"));
		Utils.regBlock(CactusWorkbench.setUnlocalizedName("CustomWorkbenchCactus").setRegistryName("cactus_crafting_table"));
		Utils.regBlock(DarkOakWorkbench.setUnlocalizedName("CustomWorkbenchDarkOak").setRegistryName("dark_oak_crafting_table"));
		Utils.regBlock(JungleWorkbench.setUnlocalizedName("CustomWorkbenchJungle").setRegistryName("jungle_crafting_table"));
		Utils.regBlock(SpruceWorkbench.setUnlocalizedName("CustomWorkbenchSpruce").setRegistryName("spruce_crafting_table"));
	}
	public static void regRenders() {
		Utils.regRender(CactusWorkbench);
		Utils.regRender(AcaciaWorkbench);
		Utils.regRender(BirchWorkbench);
		Utils.regRender(DarkOakWorkbench);
		Utils.regRender(JungleWorkbench);
		Utils.regRender(SpruceWorkbench);
	}
}
