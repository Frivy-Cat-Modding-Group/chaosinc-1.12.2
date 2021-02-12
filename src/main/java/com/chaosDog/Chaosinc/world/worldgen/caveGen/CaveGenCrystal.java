package com.chaosDog.Chaosinc.world.worldgen.caveGen;

import java.util.Random;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Crystals;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CaveGenCrystal implements IWorldGenerator {
	public static final Block[] crystals = { Crystals.diamondCrystal, Crystals.emeraldCrystal, Crystals.quartzCrystal,
			Crystals.redstoneCrystal, Crystals.rubyCrystal };

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (rand.nextInt(ModConfig.worldgen.caves.CrystalChance) == 0) {
			for (int i = 0; i < 256; i++) {
				int blockX = (chunkX * 16) + rand.nextInt(32);
				int blockZ = (chunkZ * 16) + rand.nextInt(32);
				int y = modWorld.getGroundFromAbove(world, 10, 40, blockX, blockZ,
						new Block[] { Blocks.STONE, Blocks.GRASS, Blocks.MYCELIUM });
				BlockPos pos = new BlockPos(blockX, y + 1, blockZ);
				if (y > 9) 
					world.setBlockState(pos, crystals[rand.nextInt(5)].getDefaultState(), 1);
			}
		}
	}
}