package com.chaosDog.Chaosinc.world.worldgen.misc;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CustomLakes implements IWorldGenerator
{
	private final int dimension;
	private final Block liquid;
	public CustomLakes(int dim, Block block) {
		liquid = block;
		dimension=dim;
	}
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension()!=dimension)
			return;
		genLake(world,rand,chunkX,chunkZ,liquid);
    }
	public static void genlake(World world, Random rand, int x, int y, int z, Block block) {
		BlockPos pos1 = new BlockPos(x,y,z);
		BlockPos pos2 = new BlockPos(x+16,y,z);
		BlockPos pos3 = new BlockPos(x,y,z+16);
		BlockPos pos4 = new BlockPos(x+16,y,z+16);

		if (world.getBlockState(pos1).getBlock()==Blocks.AIR || world.getBlockState(pos2).getBlock()==Blocks.AIR || world.getBlockState(pos3).getBlock()==Blocks.AIR || world.getBlockState(pos4).getBlock()==Blocks.AIR)
			return;

		for (pos1 = pos1.add(-8, 0, -8); pos1.getY() > 5 && world.isAirBlock(pos1); pos1 = pos1.down());

		if (pos1.getY() <= 4)
			return;
		else {
			pos1 = pos1.down(4);
			boolean[] aboolean = new boolean[2048];
			int i = rand.nextInt(4) + 4;

			for (int j = 0; j < i; ++j) {
				double d0 = rand.nextDouble() * 6.0D + 3.0D;
				double d1 = rand.nextDouble() * 4.0D + 2.0D;
				double d2 = rand.nextDouble() * 6.0D + 3.0D;
				double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
				double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
				double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

				for (int l = 1; l < 15; ++l) {
					for (int i1 = 1; i1 < 15; ++i1) {
						for (int j1 = 1; j1 < 7; ++j1) {
							double d6 = ((double)l - d3) / (d0 / 2.0D);
							double d7 = ((double)j1 - d4) / (d1 / 2.0D);
							double d8 = ((double)i1 - d5) / (d2 / 2.0D);
							double d9 = d6 * d6 + d7 * d7 + d8 * d8;

							if (d9 < 1.0D)
								aboolean[(l * 16 + i1) * 8 + j1] = true;
						}
					}
				}
			}
			for (int l1 = 0; l1 < 16; ++l1) {
				for (int i3 = 0; i3 < 16; ++i3) {
					for (int i4 = 0; i4 < 8; ++i4) {
						if (aboolean[(l1 * 16 + i3) * 8 + i4]) {
							if (pos1.getY()>=38)
								world.setBlockState(pos1.add(l1, i4, i3), i4 >= 4 ? Blocks.AIR.getDefaultState() : block.getDefaultState(), 2);     
						}
					}
				}
			}
		}
	}
	public static void genLake(World world, Random rand, int chunkX, int chunkZ, Block block) {
		int x = chunkX*16 + rand.nextInt(16);
		int y = rand.nextInt(128);
		int z = chunkZ*16 + rand.nextInt(16);
		genlake(world, rand, x, y, z, block);
	}
}