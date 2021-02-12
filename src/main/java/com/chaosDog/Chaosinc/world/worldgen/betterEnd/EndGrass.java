package com.chaosDog.Chaosinc.world.worldgen.betterEnd;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class EndGrass implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider.getDimension()!=1)
			return;
		if (ModConfig.worldgen.endSurprise)
			return;
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		Block[] plants= {Misc.EnderRose,Misc.EndGrass,Misc.TallEndGrass};

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 80; y++) {
					BlockPos pos = new BlockPos(x,y,z);

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(x, y, z).getBlock();

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();

					// replace exposed end stone
					if ((blockToReplace == Blocks.END_STONE||blockToReplace == Ores.EnderPearlOre||blockToReplace == Ores.EndRubyOre||blockToReplace == Ores.VoidPearlOre) && blockAbove==Blocks.AIR)
						chunk.setBlockState(pos, Misc.OvergrownEndStone.getDefaultState());
					
					// ender roses and grass
					if (rand.nextInt(15)==0 && (blockToReplace == Blocks.END_STONE||blockToReplace==Misc.OvergrownEndStone) && blockAbove==Blocks.AIR)
						chunk.setBlockState(pos.up(), plants[rand.nextInt(3)].getDefaultState());
				}
			}
		}
	}
}