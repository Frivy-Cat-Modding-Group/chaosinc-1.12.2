package com.chaosDog.Chaosinc.world.worldgen.structures.Dungeons;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.world.dimension.DimensionRegistry;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

public class VoidDungeonLarge implements IWorldGenerator{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (ModConfig.worldgen.structures.VoidDungeonLarge==false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template part1 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/void_dungeon_large_piece_1"));
		Template part2 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/void_dungeon_large_piece_2"));
		Template part3 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/void_dungeon_large_piece_3"));
		Template part4 = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":dungeons/void_dungeon_large_piece_4"));

		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		if (world.provider.getDimension() == DimensionRegistry.DeepVoid && rand.nextInt(ModConfig.worldgen.structures.VoidDungeonLargeChance) == 0) {
			int y = getHighestBlock(world, blockX, blockZ);
			BlockPos pos = new BlockPos(blockX, y, blockZ);

			//generate the structure
			if(isAreaValid(worldserver, pos)) {
				IBlockState iblockstate = world.getBlockState(pos);
				world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
				part1.getDataBlocks(pos, placementsettings);
				part1.addBlocksToWorld(world, pos.add(0, -10, 0), placementsettings);			
				part2.getDataBlocks(pos, placementsettings);
				part2.addBlocksToWorld(world, pos.add(0, -10, 32), placementsettings);			
				part3.getDataBlocks(pos, placementsettings);
				part3.addBlocksToWorld(world, pos.add(32, -10, 0), placementsettings);			
				part4.getDataBlocks(pos, placementsettings);
				part4.addBlocksToWorld(world, pos.add(32, -10, 32), placementsettings);
			}
		}
	}

	//get the highest block in the area
	public static int getHighestBlock(World world, int blockX,int blockZ) {
		int[] heights = new int[264];
		int index = 0;
		for (int i=0;i<=65;i++) {
			heights[index]= modWorld.getGroundFromAbove(world, 31, 120, blockX, blockZ, Misc.VoidStone);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 31, 120, blockX+i+1, blockZ, Misc.VoidStone);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 31, 120, blockX+i+1, blockZ+i+1, Misc.VoidStone);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 31, 120, blockX+i+1, blockZ+i+1, Misc.VoidStone);
			index++;
		}
		
		return Utils.getMaxValue(heights);
	}

	//check to see if the area is valid for spawning the structure
	public static boolean isAreaValid(World world, BlockPos postion)
	{
		int x = postion.getX();
		int y = postion.getY();
		int z =  postion.getZ();
		return postion.getY() > 31 
		&& world.getBlockState(new BlockPos(x,y-11,z)).getBlock()==Misc.VoidStone
		&& world.getBlockState(new BlockPos(x+64,y-11,z)).getBlock()==Misc.VoidStone
		&& world.getBlockState(new BlockPos(x,y-11,z+64)).getBlock()==Misc.VoidStone
		&& world.getBlockState(new BlockPos(x+64,y-11,z+64)).getBlock()==Misc.VoidStone;
		
	}
}