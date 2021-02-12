package com.chaosDog.Chaosinc.world.worldgen.misc;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SeaFloorGen implements IWorldGenerator {
	private static final IBlockState[] earth= {Blocks.DIRT.getDefaultState(),Blocks.SAND.getDefaultState(),Blocks.CLAY.getDefaultState()};
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0) {
			int x = chunkX * 16;
			int z = chunkZ * 16;
			IBlockState ore = earth[random.nextInt(3)];
			for (int i = 0; i < 6; i++) {
				BlockPos pos = new BlockPos(x + random.nextInt(8), 16 + random.nextInt(48), z + random.nextInt(8));
				WorldGenMinable generator = new WorldGenMinable(ore, 66,BlockMatcher.forBlock(Blocks.GRAVEL));
				generator.generate(world, random, pos);
			}
		}
	}
}