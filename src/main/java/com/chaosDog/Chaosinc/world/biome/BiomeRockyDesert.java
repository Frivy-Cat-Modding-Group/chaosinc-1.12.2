package com.chaosDog.Chaosinc.world.biome;

import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeRockyDesert  extends Biome{

	public BiomeRockyDesert(BiomeProperties properties) {
		super(properties);
		decorator.treesPerChunk = 1;
		decorator.grassPerChunk = 3;
		decorator.flowersPerChunk = 0;
		decorator.cactiPerChunk = 0;
		decorator.clayPerChunk = 1;
		decorator.deadBushPerChunk = 5;
		decorator.mushroomsPerChunk = 0;
		topBlock = Blocks.DIRT.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 95, 4, 4));
	}
}