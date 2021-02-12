package com.chaosDog.Chaosinc.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeRegistry {
	public static final Biome glacier = new BiomeGlacier(new BiomeProperties("Glacier").setTemperature(-1f).setRainfall(0f).setSnowEnabled());
	public static final Biome iceShelf = new BiomeGlacier(new BiomeProperties("Ice Shelf").setTemperature(-1f).setRainfall(0f).setSnowEnabled());

	public static final Biome rockyDesert = new BiomeRockyDesert(new BiomeProperties("Rocky Desert").setTemperature(2f).setRainfall(0f).setRainDisabled());

	public static final Biome saltFlat= new BiomeSaltFlat(new BiomeProperties("Salt Flat").setTemperature(2f).setRainfall(0f).setHeightVariation(-.01f).setRainDisabled());
	public static final Biome temperateJungle = new BiomeTemperateJungle(new BiomeProperties("Temperate Rainforest").setTemperature(0.5f).setRainfall(1f).setHeightVariation(0f));

	public static final Biome volcanicField = new BiomeVolcanic(new BiomeProperties("Volcanic Field").setTemperature(4f).setRainfall(0f).setHeightVariation(0f).setRainDisabled());
	public static final Biome volcanicMountains = new BiomeVolcanic(new BiomeProperties("Volcanic Mountains").setTemperature(4f).setRainfall(0f).setHeightVariation(2f).setRainDisabled());
	
	public static void regBiomes() {
		initBiome(glacier, "glacier", BiomeType.ICY, Type.COLD);
		initBiome(iceShelf, "ice_shelf", BiomeType.ICY, Type.COLD,Type.BEACH);

		initBiome(rockyDesert, "rocky_desert", BiomeType.DESERT,Type.DRY,Type.HOT);

		initBiome(saltFlat, "salt_flat", BiomeType.DESERT,Type.DRY,Type.HOT);

		initBiome(volcanicField, "volcanic_field", BiomeType.DESERT,Type.DRY, Type.HOT);
		initBiome(volcanicMountains, "volcanic_mountains", BiomeType.DESERT, Type.DRY, Type.HOT);

		initBiome(temperateJungle, "temperate_jungle", BiomeType.COOL, Type.COLD,Type.JUNGLE,Type.FOREST);
	}
	
	public static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
