package com.chaosDog.Chaosinc.world.biome.gen;

import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.biome.BiomeSaltFlat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SaltFlatGen implements IWorldGenerator {
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		height=255;
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 255; y > 58; y--) {
					BlockPos pos = new BlockPos(x, y ,z);
					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(pos).getBlock();

					// biome of current block
					Biome blockBiome = chunk.getBiome(pos.add(x,y,z) ,world.getBiomeProvider());

					if (!(blockBiome instanceof BiomeSaltFlat))
						break;

					//overworld generation
					if (dim == 0) {
						if (!(blockToReplace instanceof BlockAir||blockToReplace == Blocks.WATER))
							chunk.setBlockState(pos, Ores.saltBlock2.getDefaultState());
					}
				}
			}
		}
	}
}