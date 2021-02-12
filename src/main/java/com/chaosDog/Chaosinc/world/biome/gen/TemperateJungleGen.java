package com.chaosDog.Chaosinc.world.biome.gen;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.world.biome.BiomeTemperateJungle;
import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class TemperateJungleGen implements IWorldGenerator {
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();
		int blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
		int blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
		int[] heights = {127,80,60};
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 255; y > 1; y--) {
					BlockPos pos = new BlockPos(x, y, z);
					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(pos).getBlock();
					// biome of current block
					Biome blockBiome = chunk.getBiome(pos ,world.getBiomeProvider());
					if (!(blockBiome instanceof BiomeTemperateJungle))
						break;

					//overworld generation
					if (dim == 0) {
						if (blockToReplace==Blocks.GRASS) {
							for (int i=1;i<4;i++) {
								if (y-i>50)
									chunk.setBlockState(pos.down(i), Blocks.DIRT.getDefaultState());
							}
							break;
						}
					}
				}
			}
		}
		
		for (int i=0;i<14;i++) {
			blockX = (chunkX * 16)+ rand.nextInt(16);
			blockZ = (chunkZ * 16)+ rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 2, 255, blockX, blockZ, Blocks.GRASS);
			if (dim == ModConfig.dimensions.DeepUndergroundDim)
				y = modWorld.getGroundFromAbove(world, 31, heights[rand.nextInt(3)], blockX, blockZ, Blocks.GRASS);
			BlockPos pos2 = new BlockPos(blockX, y+1, blockZ);
			if (!(world.getBiome(pos2) instanceof BiomeTemperateJungle))
				return;
			if (y>0) {
				if (rand.nextBoolean())
					LargePlants.genClassicTree(world, rand, pos2, rand.nextInt(3), true);
				else
					LargePlants.genSpruceTree(world, rand, pos2);
			}
		}
	}
}