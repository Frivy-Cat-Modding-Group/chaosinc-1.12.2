package com.chaosDog.Chaosinc.world.biome;

import com.chaosDog.Chaosinc.init.ModBlocks.Ores;

import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeSaltFlat  extends Biome{

	public BiomeSaltFlat(BiomeProperties properties) {
		super(properties);
		decorator.treesPerChunk = 0;
		decorator.grassPerChunk = 0;
		decorator.flowersPerChunk = 0;
		decorator.cactiPerChunk = 0;
		decorator.clayPerChunk = 0;
		decorator.deadBushPerChunk = 0;
		decorator.mushroomsPerChunk = 0;
		topBlock = Ores.saltBlock2.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();
		spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 95, 4, 4));
	}

}
