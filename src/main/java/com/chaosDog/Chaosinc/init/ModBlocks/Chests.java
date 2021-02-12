package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.chests.CactusChest;
import com.chaosDog.Chaosinc.blocks.chests.CactusChestTrapped;
import com.chaosDog.Chaosinc.blocks.chests.CompressedCactusChest;
import com.chaosDog.Chaosinc.blocks.chests.CompressedCactusChestTrapped;
import net.minecraft.block.Block;

public class Chests {
	//chests
	public static Block CactusChest = new CactusChest();
	public static Block CactusChestTrapped = new CactusChestTrapped();
	public static Block CompressedCactusChest = new CompressedCactusChest();
	public static Block CompressedCactusChestTrapped = new CompressedCactusChestTrapped();

	public static void initAndRegister() {
		//chests
		Utils.regBlock(CactusChest);
		Utils.regBlock(CactusChestTrapped);
		Utils.regBlock(CompressedCactusChest);
		Utils.regBlock(CompressedCactusChestTrapped);
	}
	public static void regRenders() {
		Utils.regRender(CactusChest);
		Utils.regRender(CactusChestTrapped);
		Utils.regRender(CompressedCactusChest);
		Utils.regRender(CompressedCactusChestTrapped);
	}
}
