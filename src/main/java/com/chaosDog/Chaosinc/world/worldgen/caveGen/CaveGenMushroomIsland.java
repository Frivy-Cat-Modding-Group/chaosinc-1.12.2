package com.chaosDog.Chaosinc.world.worldgen.caveGen;

import com.chaosDog.Chaosinc.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class CaveGenMushroomIsland implements IWorldGenerator{
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		// don't generate if the config says not to
		// generate cave features
		if (ModConfig.worldgen.caves.betterCaves == false)
			return;
		if (ModConfig.worldgen.caves.types.Mushroom == false)
			return;

		// the height is 256 if the features are being generated in a cave dimension,
		// else, it's 61
		height=255;
		if (world.provider.getDimension() == 0) {
			height = 61;
		}

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < height; y++) {
					BlockPos pos = new BlockPos(x,y,z);

					// biome of current block
					Biome blockBiome = chunk.getBiome(pos, world.getBiomeProvider());

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(x, y, z).getBlock();

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();

					// the block below it
					Block blockBelow = chunk.getBlockState(x, y - 1, z).getBlock();

					if (blockBiome instanceof BiomeMushroomIsland) {
						// replace ground stone with mycelium
						if ((blockToReplace==Blocks.STONE||blockToReplace==Blocks.COBBLESTONE)&& blockAbove == Blocks.AIR)
							chunk.setBlockState(pos, Blocks.MYCELIUM.getDefaultState());

						// little mushrooms
						if(chunk.getBlockState(x, y, z).getBlock() == Blocks.MYCELIUM){
							switch (rand.nextInt(10)) {
								//red
								case 1:
									chunk.setBlockState(pos.up(), Blocks.RED_MUSHROOM.getDefaultState());
									break;
								//brown
								case 9:
									chunk.setBlockState(pos.up(), Blocks.BROWN_MUSHROOM.getDefaultState());
									break;
							}
						}

						// the cave is a giant mushroom!
						if ((blockToReplace==Blocks.STONE||blockToReplace==Blocks.COBBLESTONE) && blockBelow == Blocks.AIR) {
							int a = 1;
							chunk.setBlockState(pos, Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.CENTER));
							if (rand.nextInt(30) == 0) {
								Block blockToReplace2 = chunk.getBlockState(x, y - 1, z).getBlock();
								while (blockToReplace2 == Blocks.AIR
										|| blockToReplace2 == Blocks.WATER
										|| blockToReplace2 == Blocks.LAVA
										|| blockToReplace2 == Blocks.FLOWING_LAVA
										|| blockToReplace2 == Blocks.FLOWING_WATER) {
									chunk.setBlockState(pos.add(0,-a,0),Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.STEM));
									a++;
									blockToReplace2 = chunk.getBlockState(x, y - a, z).getBlock();
								}
							}
						}
					}
				}
			}
		}
	}
}