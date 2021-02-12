package com.chaosDog.Chaosinc.world.worldgen.caveGen;

import java.util.Random;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Lamps;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.biome.BiomeRockyDesert;
import com.chaosDog.Chaosinc.world.biome.BiomeSaltFlat;
import com.chaosDog.Chaosinc.world.biome.BiomeVolcanic;
import com.chaosDog.Chaosinc.world.biome.gen.RockyDesertGen;
import com.chaosDog.Chaosinc.world.biome.gen.VolcanicGen;

import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CaveGenDesert implements IWorldGenerator{
	protected static final IBlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
	protected static final IBlockState[] rocks = {Blocks.DIRT.getDefaultState(),Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT),Blocks.GRAVEL.getDefaultState()};
	public static int height;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		int blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
		int blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
		// don't generate if the config says not to
		// generate cave features
		if (!ModConfig.worldgen.caves.betterCaves)
			return;
		if(!ModConfig.worldgen.caves.types.Dry)
			return;
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);

		height=255;
		if (world.provider.getDimension() == 0) {
			height = 100;
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

					//vanilla desert
					if (blockBiome instanceof BiomeDesert){
						if ((blockToReplace == Blocks.STONE||blockToReplace == Blocks.SANDSTONE)&&(chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.AIR
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
							// lava
							if (rand.nextInt(20) == 0 && blockAbove == Blocks.AIR)
								chunk.setBlockState(pos, Blocks.LAVA.getDefaultState());
							// sandstone
							if (rand.nextBoolean())
								chunk.setBlockState(pos, SANDSTONE);
							
							//sandstone formations
							if (blockAbove==Blocks.AIR&&rand.nextInt(10)==0)
								chunk.setBlockState(pos.add(0,1,0), Misc.sandstoneFormation.getDefaultState());
							if (blockBelow==Blocks.AIR&&rand.nextInt(10)==0&&y>0)
								chunk.setBlockState(pos.add(0,-1,0), Misc.sandstoneFormation.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.DOWN));
						}

						//this needs to be reset
						blockToReplace = chunk.getBlockState(x, y, z).getBlock();

						// shrubs
						if (blockToReplace == SANDSTONE	&& blockAbove == Blocks.AIR && rand.nextInt(5) == 0 ) {
							chunk.setBlockState(pos.add(0, 1, 0), Blocks.DEADBUSH.getDefaultState());
							chunk.setBlockState(pos, Blocks.SAND.getDefaultState());
						}
					}
					
					//salt flat
					if (blockBiome instanceof BiomeSaltFlat){
						if ((blockToReplace == Blocks.STONE||blockToReplace == Blocks.GRASS||blockToReplace == Blocks.DIRT)&&(chunk.getBlockState(x + 1, y, z).getBlock() == Blocks.AIR
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
							// salt lamp
							if (rand.nextInt(20) == 0 && blockAbove == Blocks.AIR)
								chunk.setBlockState(pos, Lamps.SaltLamp.getDefaultState());

							
							//salt block
							if (rand.nextBoolean())
								chunk.setBlockState(pos, Ores.SaltBlock.getDefaultState());
						}
						// compact salt block
						if (blockToReplace == Blocks.STONE && blockAbove==Blocks.AIR&&rand.nextBoolean())
							chunk.setBlockState(pos, Ores.saltBlock2.getDefaultState());
					}

					//rocky desert
					if (blockBiome instanceof BiomeRockyDesert) {
						if ((blockToReplace == Blocks.STONE||blockToReplace == Blocks.GRASS||blockToReplace == Blocks.DIRT) && blockAbove==Blocks.AIR)
							chunk.setBlockState(pos, RockyDesertGen.topBlocks[rand.nextInt(3)]);
					}
					
					//volcanic
					if (blockBiome instanceof BiomeVolcanic) {
						if (blockToReplace == Blocks.STONE && blockAbove==Blocks.AIR) {
							if (rand.nextInt(5)==0)
								chunk.setBlockState(pos, Blocks.LAVA.getDefaultState());
							else
								chunk.setBlockState(pos, VolcanicGen.topBlocks[rand.nextInt(4)]);
						}
					}
				}
			}
		}
		for (int i=0;i<3;i++) {
			blockX = (chunkX * 16)+ rand.nextInt(16);
			blockZ = (chunkZ * 16)+ rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 2, 255, blockX, blockZ, new Block[] {Blocks.STONE,Blocks.SANDSTONE});
			BlockPos pos2 = new BlockPos(blockX, y+1, blockZ);
			if (!(world.getBiome(pos2) instanceof BiomeDesert))
				return;
			if (y>0)
				LargePlants.genCactus(world, pos2, rand, 0);
		}
	}
}

