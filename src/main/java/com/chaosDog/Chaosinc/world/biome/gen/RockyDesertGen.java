package com.chaosDog.Chaosinc.world.biome.gen;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.modWorld;
import com.chaosDog.Chaosinc.world.biome.BiomeRockyDesert;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class RockyDesertGen implements IWorldGenerator {
	public static int height;
	public static final IBlockState[] topBlocks = {
			Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT),
			Blocks.DIRT.getDefaultState(),
			Blocks.GRAVEL.getDefaultState()
	};

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int dim = world.provider.getDimension();
		Chunk chunk=world.getChunkFromChunkCoords(chunkX, chunkZ);
		height=255;
		int blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
		int blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = height; y > 0; y--) {
					BlockPos pos = new BlockPos(x, y ,z);
					// biome of current block
					Biome blockBiome = chunk.getBiome(pos ,world.getBiomeProvider());

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(pos).getBlock();

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();
					if (!(blockBiome instanceof BiomeRockyDesert))
						break;

					//overworld generation
					if (blockToReplace==Blocks.DIRT && blockBiome instanceof BiomeRockyDesert) {
						if (dim == 0) {
							for (int i = 0; i<4;i++) {
								if (y-i>50)
									chunk.setBlockState(pos.down(i), topBlocks[rand.nextInt(3)]);
							}
						}
						break;
					}
					if ((blockToReplace==Blocks.DIRT||blockToReplace==Blocks.GRAVEL)&& blockAbove instanceof BlockAir && rand.nextBoolean()&& blockBiome instanceof BiomeRockyDesert)
						chunk.setBlockState(pos.up(1), Blocks.DEADBUSH.getDefaultState());
					
					//deep underground generation
					if ((blockToReplace==Blocks.DIRT||blockToReplace==Blocks.GRAVEL)&& blockAbove instanceof BlockAir && dim==ModConfig.dimensions.DeepUndergroundDim&& blockBiome instanceof BiomeRockyDesert)
						chunk.setBlockState(pos.up(1),  topBlocks[rand.nextInt(3)]);
				}
			}
		}
		
		for (int i=0;i<3;i++) {
			blockX = (chunkX * 16)+ rand.nextInt(16);
			blockZ = (chunkZ * 16)+ rand.nextInt(16);
			int y = modWorld.getGroundFromAbove(world, 2, 255, blockX, blockZ, new Block[] {Blocks.GRAVEL, Blocks.DIRT});
			BlockPos pos2 = new BlockPos(blockX, y+1, blockZ);
			if (!(world.getBiome(pos2) instanceof BiomeRockyDesert))
				return;
			if (y>0)
				LargePlants.genCactus(world, pos2, rand, 0);
		}
	}
}