package com.chaosDog.Chaosinc.world.worldgen.betterEnd;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import net.minecraft.block.*;
import net.minecraft.block.BlockTallGrass.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class EndSurprise implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (!ModConfig.worldgen.endSurprise)
			return;
		if (world.provider.getDimension()!=1)
			return;
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		IBlockState[] plants= {Misc.Rose.getDefaultState(),Misc.BlueRose.getDefaultState(),Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, EnumType.GRASS)};

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 255; y++) {

					BlockPos pos = new BlockPos(x,y,z);

					// the block to be replaced
					Block blockToReplace = chunk.getBlockState(x, y, z).getBlock();
					IBlockState blockstateToReplace = chunk.getBlockState(x, y, z);

					// the block above it
					Block blockAbove = chunk.getBlockState(x, y + 1, z).getBlock();

					// replace exposed end stone with grass
					if ((blockToReplace == Blocks.END_STONE || blockToReplace == Ores.EndRubyOre || blockToReplace == Ores.VoidPearlOre) && blockAbove == Blocks.AIR)
						chunk.setBlockState(pos, Blocks.GRASS.getDefaultState());
					else {
						if (blockToReplace == Blocks.END_STONE)
							chunk.setBlockState(pos, Blocks.STONE.getDefaultState());
					}
					if (rand.nextInt(15)==0 && (blockToReplace == Blocks.END_STONE||blockToReplace==Misc.OvergrownEndStone) && blockAbove==Blocks.AIR)
						chunk.setBlockState(pos.up(), plants[rand.nextInt(3)]);
					
					//glowstone
					if (rand.nextInt(15)==0 && (blockToReplace == Blocks.END_STONE||blockToReplace==Misc.OvergrownEndStone) && blockAbove==Blocks.AIR)
						chunk.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
					
					//place dark oak trees
					if (rand.nextInt(30)==0 && (blockToReplace == Blocks.END_STONE||blockToReplace==Misc.OvergrownEndStone) && blockAbove==Blocks.AIR) {
						LargePlants.genBigOakTree(world,pos,rand);
					}
					

					//replace end bricks with stone bricks
					if (blockToReplace==Blocks.END_BRICKS)
						chunk.setBlockState(pos, Blocks.STONEBRICK.getDefaultState());
					if (blockToReplace==Blocks.PURPUR_BLOCK)
						chunk.setBlockState(pos, Blocks.PLANKS.getDefaultState());
					if (blockToReplace==Blocks.END_ROD)
						chunk.setBlockState(pos, Blocks.TORCH.getDefaultState());
					if (blockToReplace==Blocks.PURPUR_STAIRS)
						chunk.setBlockState(pos, Blocks.OAK_STAIRS.getDefaultState()
								.withProperty(BlockStairs.FACING, blockstateToReplace.getValue(BlockStairs.FACING))
								.withProperty(BlockStairs.HALF, blockstateToReplace.getValue(BlockStairs.HALF))
								.withProperty(BlockStairs.SHAPE, blockstateToReplace.getValue(BlockStairs.SHAPE))
								);
					if (blockToReplace==Blocks.STAINED_GLASS)
						chunk.setBlockState(pos, Blocks.GLASS.getDefaultState());
				}
			}
		}
	}
}
