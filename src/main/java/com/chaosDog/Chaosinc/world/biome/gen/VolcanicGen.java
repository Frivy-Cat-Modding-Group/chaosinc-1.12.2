package com.chaosDog.Chaosinc.world.biome.gen;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.world.biome.BiomeVolcanic;
import com.chaosDog.Chaosinc.world.worldgen.misc.CustomLakes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class VolcanicGen implements IWorldGenerator {
	public static int height;
	public static final IBlockState[] topBlocks = {
			Blocks.STONE.getDefaultState(),
			Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE),
			Blocks.MAGMA.getDefaultState(),
			Blocks.OBSIDIAN.getDefaultState()
	};

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		height=255;
		int blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
		int blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = height; y > 0; y--) {
					BlockPos pos = new BlockPos(x, y ,z);
					// biome of current block
					Biome blockBiome = chunk.getBiome(pos, world.getBiomeProvider());

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(pos).getBlock();

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();
					if (!(blockBiome instanceof BiomeVolcanic))
						break;

					//overworld generation
					if (dim == 0) {
						if (blockToReplace==Blocks.STONE && blockBiome instanceof BiomeVolcanic) {
							for (int i = 0; i<4;i++) {
								if (y-i>50) {
									if (rand.nextInt(10)==0)
										chunk.setBlockState(pos.down(i), Blocks.LAVA.getDefaultState());
									else
										chunk.setBlockState(pos.down(i), topBlocks[rand.nextInt(4)]);
								}
							}
							break;
						}
					}
					
					//deep underground generation
					if (dim==ModConfig.dimensions.DeepUndergroundDim) {
						if ((blockToReplace==Blocks.COBBLESTONE)&& blockAbove instanceof BlockAir && blockBiome instanceof BiomeVolcanic) {
							if (rand.nextInt(10)==0)
								chunk.setBlockState(pos, Blocks.LAVA.getDefaultState());
							else
								chunk.setBlockState(pos,  topBlocks[rand.nextInt(4)]);
						}
					}
				}
			}
		}
		
		for (int i=0;i<2;i++) {
			blockX = (chunkX * 16)+ rand.nextInt(16);
			blockZ = (chunkZ * 16)+ rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ, new Block[] {Blocks.STONE, Blocks.OBSIDIAN,Blocks.MAGMA});
			BlockPos pos2 = new BlockPos(blockX, y+1, blockZ);
			if (!(world.getBiome(pos2) instanceof BiomeVolcanic))
				return;
			if (y>29)
				CustomLakes.genlake(world, rand, blockX, y, blockZ, Blocks.LAVA);
		}
	}
}