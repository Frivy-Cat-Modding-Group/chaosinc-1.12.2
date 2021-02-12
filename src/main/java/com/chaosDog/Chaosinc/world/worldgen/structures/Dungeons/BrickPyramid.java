package com.chaosDog.Chaosinc.world.worldgen.structures.Dungeons;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.config.ModConfig;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class BrickPyramid implements IWorldGenerator{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider.getDimension() != 0 || rand.nextInt(ModConfig.worldgen.structures.BrickPyramidsChance)!=0||ModConfig.worldgen.structures.BrickPyramids==false)
				return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template part1 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/brick_pyramid_1"));
		Template part2 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/brick_pyramid_2"));
		Template part3 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/brick_pyramid_3"));
		Template part4 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/brick_pyramid_4"));
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		
		//generate the structure
		int y = getLowestBlock(world, blockX, blockZ);
		if (y<31)
			return;
		System.out.println(y);
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		IBlockState iblockstate = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
		PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
				.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
				.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
		part1.getDataBlocks(pos, placementsettings);
		part1.addBlocksToWorld(world, pos, placementsettings);			
		part2.getDataBlocks(pos, placementsettings);
		part2.addBlocksToWorld(world, pos.add(26, 0, 0), placementsettings);			
		part3.getDataBlocks(pos, placementsettings);
		part3.addBlocksToWorld(world, pos.add(0, 0, 27), placementsettings);			
		part4.getDataBlocks(pos, placementsettings);
		part4.addBlocksToWorld(world, pos.add(26, 0, 27), placementsettings);
	
		//remove foliage from the area
		for (int i=0;i<=53;i++) {
			for (int j=0;j<=53;j++) {
				for (int k=0;k<=53;k++) {
					Block blockToReplace = world.getBlockState(pos.add(i,j,k)).getBlock();
					Block blockAbove = world.getBlockState(pos.add(i,j,k)).getBlock();
					if (blockToReplace==Blocks.LEAVES
							||blockToReplace==Blocks.LEAVES2
							||blockToReplace==Blocks.LOG
							||blockToReplace==Blocks.LOG2
							||blockToReplace==Blocks.BROWN_MUSHROOM_BLOCK
							||blockToReplace==Blocks.RED_MUSHROOM_BLOCK) {
						world.setBlockState(pos.add(i,j,k), Blocks.AIR.getDefaultState());
					if (blockAbove == Blocks.AIR && blockToReplace == Blocks.DIRT)
						world.setBlockState(pos.add(i,j,k), Blocks.GRASS.getDefaultState());
					}
				}
			}		
		}
	}

	//get the lowest block in the area
	public static int getLowestBlock(World world, int blockX,int blockZ) {
		int[] heights = new int[4];
		heights[0]= modWorld.getGroundFromAbove(world, 31, 127, blockX, blockZ, modWorld.ground);
		heights[1]=modWorld.getGroundFromAbove(world, 31, 127, blockX+53, blockZ, modWorld.ground);
		heights[2]=modWorld.getGroundFromAbove(world, 31, 127, blockX, blockZ+53, modWorld.ground);
		heights[3]=modWorld.getGroundFromAbove(world, 31, 127, blockX+53, blockZ+53, modWorld.ground);
		
		return Utils.getMinValue(heights);
	}
}