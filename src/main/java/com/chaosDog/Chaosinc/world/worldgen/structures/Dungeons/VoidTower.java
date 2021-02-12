package com.chaosDog.Chaosinc.world.worldgen.structures.Dungeons;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;

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

public class VoidTower implements IWorldGenerator{
	public int topper;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (ModConfig.worldgen.structures.VoidTower==false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template bottom = templatemanager.getTemplate(minecraftserver,new ResourceLocation(Reference.MOD_ID+":dungeons/void_tower_bottom"));
		Template section = templatemanager.getTemplate(minecraftserver,new ResourceLocation(Reference.MOD_ID+":dungeons/void_tower_section"));
		Template top = templatemanager.getTemplate(minecraftserver,new ResourceLocation(Reference.MOD_ID+":dungeons/void_tower_top"));
		int sections=rand.nextInt(16);
		sections++;
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int y = modWorld.getGroundFromAbove(world, 31, 120, blockX, blockZ, Misc.VoidStone);
		if (y<=31)
			return;
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		
		//generate the structure
		if(canSpawnHere(bottom, worldserver, pos)) {
			if(rand.nextInt(ModConfig.worldgen.structures.VoidTowerChance) == 0){
				IBlockState iblockstate = world.getBlockState(pos);
				world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
				bottom.getDataBlocks(pos, placementsettings);
				bottom.addBlocksToWorld(world, pos, placementsettings);
				if (sections>1) {
					for (int i=1;i<sections;i++) {
						section.getDataBlocks(pos, placementsettings);
						section.addBlocksToWorld(world, pos.up((i*12)+2), placementsettings);
						topper=i;
					}
					top.getDataBlocks(pos, placementsettings);
					top.addBlocksToWorld(world, pos.up((topper*12)+14), placementsettings);
				}
				else 
				{
					section.getDataBlocks(pos, placementsettings);
					top.getDataBlocks(pos, placementsettings);
					section.addBlocksToWorld(world, pos, placementsettings);
					top.addBlocksToWorld(world, pos.up(13), placementsettings);
				}
			}
		}
	}

	public static boolean canSpawnHere(Template template, World world, BlockPos posAboveGround)
	{
		int zwidth = template.getSize().getZ();
		int xwidth = template.getSize().getX();
		boolean corner1 = isCornerValid(world, posAboveGround);
		boolean corner2 = isCornerValid(world, posAboveGround.add(xwidth, 0, zwidth));
		return posAboveGround.getY() > 31 && corner1 && corner2;
	}

	public static boolean isCornerValid(World world, BlockPos pos)
	{
		int variation = 3;
		int highestBlock = modWorld.getGroundFromAbove(world, 31, 120, pos.getX(), pos.getZ(), Misc.VoidStone);
		if (highestBlock > pos.getY() - variation && highestBlock < pos.getY() + variation)
			return true;
		return false;
	}
}