package com.chaosDog.Chaosinc.world.worldgen.caveGen;

import com.chaosDog.Chaosinc.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class CaveGenMesa implements IWorldGenerator {
	protected static final IBlockState TERRACOTTA = Blocks.HARDENED_CLAY.getDefaultState();
	protected static final IBlockState ORANGE_TERRACOTTA = Blocks.STAINED_HARDENED_CLAY.getDefaultState()
			.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.ORANGE);
	protected static final IBlockState WHITE_TERRACOTTA = Blocks.STAINED_HARDENED_CLAY.getDefaultState()
			.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.WHITE);
	protected static final IBlockState BROWN_TERRACOTTA = Blocks.STAINED_HARDENED_CLAY.getDefaultState()
			.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.BROWN);
	protected static final IBlockState YELLOW_TERRACOTTA = Blocks.STAINED_HARDENED_CLAY.getDefaultState()
			.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.YELLOW);
	protected static final IBlockState RED_TERRACOTTA = Blocks.STAINED_HARDENED_CLAY.getDefaultState()
			.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.RED);
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

		// don't generate if the config says not to
		// generate cave features
		if (!ModConfig.worldgen.caves.betterCaves)
			return;
		if (!ModConfig.worldgen.caves.types.Mesa)
			return;

		// the height is 256 if the features are being generated in a cave dimension,
		// else, it's 61
		height=255;
		if (world.provider.getDimension() == 0) {
			height = 61;
		}

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < height; y++) {
					BlockPos pos = new BlockPos(x,y,z);

					// biome of current block
					Biome blockBiome = chunk.getBiome(pos, world.getBiomeProvider());

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(x, y, z).getBlock();

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();

					boolean mesa = blockBiome instanceof BiomeMesa;

					// replace exposed stone
					if (mesa&&(blockToReplace == Blocks.STONE || blockToReplace == Blocks.COBBLESTONE)
							&& (chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x, y + 1, z).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x, y, z + 1).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x - 1, y, z).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x, y - 1, z).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x, y, z - 1).getBlock() == Blocks.AIR
									|| chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x, y + 1, z).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x, y, z + 1).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x - 1, y, z).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x, y - 1, z).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x, y, z - 1).getBlock() == Blocks.WATER
									|| chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.LAVA
									|| chunk.getBlockState(x, y + 1, z).getBlock() == Blocks.LAVA
									|| chunk.getBlockState(x, y, z + 1).getBlock() == Blocks.LAVA
									|| chunk.getBlockState(x - 1, y, z).getBlock() == Blocks.LAVA
									|| chunk.getBlockState(x, y - 1, z).getBlock() == Blocks.LAVA
									|| chunk.getBlockState(x, y, z - 1).getBlock() == Blocks.LAVA)) {

							//terracotta
							switch (y) {
							case 74:
							case 45:
								chunk.setBlockState(pos, ORANGE_TERRACOTTA);
								break;

							case 79:
							case 50:
								chunk.setBlockState(pos, WHITE_TERRACOTTA);
								break;

							case 83:
							case 55:
								chunk.setBlockState(pos, BROWN_TERRACOTTA);
								break;

							case 85:
							case 64:
								chunk.setBlockState(pos, YELLOW_TERRACOTTA);
								break;

							case 40:
							case 56:
								chunk.setBlockState(pos, RED_TERRACOTTA);
								break;

							default:
								chunk.setBlockState(pos, TERRACOTTA);

							}
						}

						// shrubs
						if (mesa && (blockToReplace == TERRACOTTA || blockToReplace == TERRACOTTA
								|| blockToReplace == Blocks.SANDSTONE) && blockAbove == Blocks.AIR && rand.nextInt(5) == 0) {
							chunk.setBlockState(pos.up(), Blocks.DEADBUSH.getDefaultState());

					}
				}
			}
		}
	}
}