package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.config.ModConfig;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class Ocean implements IWorldGenerator{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = null;

		if (world.provider.getDimension() == 0) {
			int structureType=rand.nextInt(2);
			int y = modWorld.getGroundFromAbove(world, 20, 255, blockX, blockZ, modWorld.ground, true);
			if (y<=20)
				return;
			if (structureType==0)
				y=getLowestBlock(world, blockX, blockZ);
			BlockPos pos = new BlockPos(blockX, y, blockZ);
			int rarity=ModConfig.worldgen.structures.ShipwreckChance;

			switch(structureType) {
				case 0:
					if (ModConfig.worldgen.structures.OceanVillage==true) {
						rarity=ModConfig.worldgen.structures.OceanVillageChance;
						template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":ocean_village"));
					}
					break;
				case 1:
					if (ModConfig.worldgen.structures.Shipwreck==true)
						template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":ship"));
					break;
			}

			if(template == null)
				return;
			
			Biome biome = world.getBiomeForCoordsBody(pos);
			if(biome == Biomes.OCEAN||biome == Biomes.DEEP_OCEAN) {
				if(rand.nextInt(rarity) == 0){
					IBlockState iblockstate = world.getBlockState(pos);
					world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
					PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
							.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
							.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
					template.getDataBlocks(pos, placementsettings);
					template.addBlocksToWorld(world, pos.add(0, 0, 0), placementsettings);
				}
			}
		}
	}

	//get the lowest block in the area
	public static int getLowestBlock(World world, int blockX,int blockZ) {
		int[] heights = new int[136];
		int index = 0;
		for (int i=0;i<=33;i++) {
			heights[index]=modWorld.getGroundFromAbove(world, 20, 255, blockX, blockZ, modWorld.ground, true);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 20, 255, blockX+i+1, blockZ, modWorld.ground, true);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 20, 255, blockX+i+1, blockZ+i+1, modWorld.ground, true);
			index++;
			heights[index]=modWorld.getGroundFromAbove(world, 20, 255, blockX+i+1, blockZ+i+1, modWorld.ground, true);
			index++;
		}
			
		return Utils.getMinValue(heights);
	}
}