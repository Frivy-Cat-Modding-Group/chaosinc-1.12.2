package com.chaosDog.Chaosinc.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeTemperateJungle  extends Biome{

	public BiomeTemperateJungle(BiomeProperties properties) {
		super(properties);
		//tree will be generated from somewhere else
		decorator.treesPerChunk = 0;
		decorator.grassPerChunk = 25;
		decorator.flowersPerChunk = 5;
		decorator.cactiPerChunk = 0;
		decorator.clayPerChunk = 1;
		decorator.deadBushPerChunk = 0;
		decorator.mushroomsPerChunk = 3;
		topBlock = Blocks.GRASS.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
	}

}
