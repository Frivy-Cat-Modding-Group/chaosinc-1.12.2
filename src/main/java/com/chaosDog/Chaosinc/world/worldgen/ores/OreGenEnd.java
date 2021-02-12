package com.chaosDog.Chaosinc.world.worldgen.ores;

import java.util.Random;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGenEnd implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if ((world.provider.getDimension() == 1)&&ModConfig.worldgen.GenerateEndOres){
			IBlockState[]endOres= {Ores.EndRubyOre.getDefaultState(),Ores.EnderPearlOre.getDefaultState(),Ores.VoidPearlOre.getDefaultState()};
			modWorld.generateOre(endOres[random.nextInt(3)], world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(15), 6,Blocks.END_STONE);
		}
	}
}