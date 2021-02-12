package com.chaosDog.Chaosinc.world.biome.gen;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.world.biome.BiomeGlacier;
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

public class GlacierGen implements IWorldGenerator {
	public static int height;
	public static boolean webs = false;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		height=255;
		if (dim == 0) {
			height = 100;
			webs = true;
		}
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = height; y > 0; y--) {
					BlockPos pos = new BlockPos(x, y ,z);

					// biome of current block
					Biome blockBiome = chunk.getBiome(pos ,world.getBiomeProvider());

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(pos).getBlock();
					
					//overworld generation
					//generate blue ice going 20 blocks above the surface
					if ((blockToReplace == Blocks.DIRT) && blockBiome instanceof BiomeGlacier) {	
						if (dim == 0) {
							for (int i = 1; i<21;i++) 
								chunk.setBlockState(pos.up(i), Misc.blueIce.getDefaultState());
							chunk.setBlockState(pos.up(20), Blocks.SNOW_LAYER.getDefaultState());
						}
						break;
					}
					if ((blockToReplace == Blocks.WATER||blockToReplace == Blocks.STONE) && blockBiome instanceof BiomeGlacier) {	
						if (dim == 0) {
							for (int i = 1; i<21;i++) {
								if (y+i<=62)
									chunk.setBlockState(pos.up(i), Blocks.WATER.getDefaultState());
								else
									chunk.setBlockState(pos.up(i), Blocks.ICE.getDefaultState());
							}
						}
						break;
					}
					
					//deep underground generation
					if ((blockToReplace==Blocks.STONE||blockToReplace==Blocks.DIRT||blockToReplace==Blocks.GRAVEL||blockToReplace==Blocks.SAND||blockToReplace==Blocks.CLAY)&&dim == ModConfig.dimensions.DeepUndergroundDim && blockBiome instanceof BiomeGlacier)
						chunk.setBlockState(pos, Misc.blueIce.getDefaultState());
				}
			}
		}
	}
}