package com.chaosDog.Chaosinc.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeVolcanic extends Biome {
	public BiomeVolcanic(BiomeProperties properties) {
		super(properties);
		decorator.treesPerChunk = 0;
		decorator.grassPerChunk = 0;
		decorator.flowersPerChunk = 0;
		decorator.cactiPerChunk = 0;
		decorator.clayPerChunk = 1;
		decorator.deadBushPerChunk = 0;
		decorator.mushroomsPerChunk = 0;
		topBlock = Blocks.STONE.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
	}
}