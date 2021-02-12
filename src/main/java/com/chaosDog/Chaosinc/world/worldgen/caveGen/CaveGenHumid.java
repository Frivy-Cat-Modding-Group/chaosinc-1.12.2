package com.chaosDog.Chaosinc.world.worldgen.caveGen;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.world.biome.BiomeTemperateJungle;
import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class CaveGenHumid implements IWorldGenerator{
	protected static final IBlockState WATER = Blocks.WATER.getDefaultState();
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		int dim = world.provider.getDimension();
		boolean noGenPools = Utils.containsVal(ModConfig.worldgen.caves.poolBlacklist, dim);

		// don't generate if the config says not to
		// generate cave features
		if (!ModConfig.worldgen.caves.betterCaves)
			return;
		if (!ModConfig.worldgen.caves.types.Wet)
			return;

		// the height is 256 if the features are being generated in a cave dimension,
		// else, it's 61
		height=255;
		if (dim == 0) {
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

					// the block below it
					Block blockBelow = chunk.getBlockState(x, y - 1, z).getBlock();

					// biome types
					boolean swamp = blockBiome instanceof BiomeSwamp;//== Biomes.SWAMPLAND|| blockBiome == Biomes.MUTATED_SWAMPLAND;
					boolean jungle = blockBiome instanceof BiomeJungle||blockBiome instanceof BiomeTemperateJungle;// == Biomes.JUNGLE || blockBiome == Biomes.JUNGLE_EDGE|| blockBiome == Biomes.JUNGLE_HILLS || blockBiome == Biomes.MUTATED_JUNGLE|| blockBiome == Biomes.MUTATED_JUNGLE_EDGE||blockBiome instanceof BiomeTemperateJungle;
					
	
					//shorthands for checking for stone and grass
					boolean stoneCheck=blockToReplace==Blocks.STONE||blockToReplace==Blocks.COBBLESTONE||blockToReplace==Blocks.GRAVEL;
					boolean grassCheck=blockToReplace==Blocks.STONE||blockToReplace==Blocks.COBBLESTONE||blockToReplace==Blocks.GRAVEL;

					// jungle and swamp features
					if (jungle || swamp) {

						// mossy cobblestone
						if (rand.nextInt(10) == 0&&stoneCheck)
							chunk.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState());

						// shallow water pools
						if(!noGenPools) {
							if (blockToReplace == Blocks.AIR && y < 20) {
								chunk.setBlockState(pos, WATER);
								if (blockBelow == Blocks.LAVA) {
									chunk.setBlockState(pos.add(0, -1, 0), Blocks.OBSIDIAN.getDefaultState());
									if (rand.nextBoolean())
										chunk.setBlockState(pos.add(0, -1, 0), Blocks.MAGMA.getDefaultState());
								}
							}
						}

						// replace ground stone with grass
						if (stoneCheck&&blockAbove==Blocks.AIR && y >= 19)
							chunk.setBlockState(pos, Blocks.GRASS.getDefaultState());
						if (stoneCheck&&blockAbove==Blocks.AIR && noGenPools)
							chunk.setBlockState(pos, Blocks.GRASS.getDefaultState());


						if (grassCheck&&blockAbove==Blocks.AIR && y >= 19 && rand.nextInt(5)==0)
							chunk.setBlockState(pos.up(), modWorld.plants[rand.nextInt(5)]);
						if (grassCheck&&blockAbove==Blocks.AIR && noGenPools && rand.nextInt(5)==0)
							chunk.setBlockState(pos.up(), modWorld.plants[rand.nextInt(5)]);

						//mossy stone formations
						if (stoneCheck&&blockAbove==Blocks.AIR&&rand.nextInt(10)==0){
							chunk.setBlockState(pos.up(), Misc.mossyStoneFormation.getDefaultState());
							chunk.setBlockState(pos, Blocks.STONE.getDefaultState());
						}
						if (stoneCheck&&blockBelow==Blocks.AIR&&rand.nextInt(10)==0&&y>0)
							chunk.setBlockState(pos.add(0,-1,0), Misc.mossyStoneFormation.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.DOWN));

						//vines
						if (stoneCheck&&y>=19) {
							// east
							if (chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.AIR&& rand.nextInt(10) == 0&&x!=15)
								chunk.setBlockState(pos.add(1,0,0),Blocks.VINE.getDefaultState().withProperty(BlockVine.WEST, true));

							// west
							if (chunk.getBlockState(x - 1, y, z).getBlock() == Blocks.AIR && rand.nextInt(10) == 0&&x!=0)
								chunk.setBlockState(pos.add(-1,0,0),Blocks.VINE.getDefaultState().withProperty(BlockVine.EAST, true));

							// south
							if (chunk.getBlockState(x, y, z + 1).getBlock() == Blocks.AIR && rand.nextInt(10) == 0&&z!=15)
								chunk.setBlockState(pos.add(0,0,1),Blocks.VINE.getDefaultState().withProperty(BlockVine.NORTH, true));

							// north
							if (chunk.getBlockState(x, y, z - 1).getBlock() == Blocks.AIR && rand.nextInt(10) == 0&&z!=0)
								chunk.setBlockState(pos.add(0,0,-1),Blocks.VINE.getDefaultState().withProperty(BlockVine.SOUTH, true));
						}
						blockToReplace = chunk.getBlockState(x, y, z).getBlock();

					}

					// swamp only features
					if (swamp&&blockToReplace==Blocks.GRASS) {
						switch (rand.nextInt(5)) {
							//slime blocks
							case 1:
								chunk.setBlockState(pos, Blocks.SLIME_BLOCK.getDefaultState());
								break;

							//tall grass
							case 4:
								chunk.setBlockState(pos.up(), Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS));
								break;
						}
					}
				}
			}
		}
		
		//underground trees
		for (int i=0;i<14;i++) {
			int blockX = (chunkX * 16)+ rand.nextInt(16);
			int blockZ = (chunkZ * 16)+ rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 2, 61, blockX, blockZ, Blocks.GRASS);
			BlockPos pos2 = new BlockPos(blockX, y+1, blockZ);
			if (world.getBiome(pos2) instanceof BiomeJungle && y>0)
				LargePlants.genClassicTree(world, rand, pos2, 2, true);
		}
	}
}