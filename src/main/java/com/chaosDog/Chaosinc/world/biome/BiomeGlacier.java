package com.chaosDog.Chaosinc.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeGlacier  extends Biome{

	public BiomeGlacier(BiomeProperties properties) {
		super(properties);
		decorator.treesPerChunk = 0;
		decorator.grassPerChunk = 0;
		decorator.flowersPerChunk = 0;
		decorator.cactiPerChunk = 0;
		decorator.clayPerChunk = 1;
		decorator.deadBushPerChunk = 0;
		decorator.mushroomsPerChunk = 0;
		topBlock = Blocks.DIRT.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
	}

}
