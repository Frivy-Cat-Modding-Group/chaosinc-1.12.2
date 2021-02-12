package com.chaosDog.Chaosinc.world.worldgen.misc;

import java.util.Random;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class Rose implements IWorldGenerator{

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		for (int i=0;i<5;i++) {
			int blockX = (chunkX * 16)+rand.nextInt(16);
			int blockZ = (chunkZ * 16)+rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ,Blocks.GRASS);
			BlockPos pos = new BlockPos(blockX, y+1, blockZ);
			if (y<40) return;
			world.setBlockState(pos, Misc.BlueRose.getDefaultState());
			if (rand.nextBoolean())
				world.setBlockState(pos, Misc.Rose.getDefaultState());
		}
	}
}