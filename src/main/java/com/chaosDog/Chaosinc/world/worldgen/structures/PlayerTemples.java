package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
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

public class PlayerTemples implements IWorldGenerator{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(ModConfig.worldgen.structures.PlayerTemples==false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = null;
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;	
		int y = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ, modWorld.ground);
		if (y<=31)
			return;
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		switch (rand.nextInt(2)) {
			case 0:
				template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":player_temples/nn777_temple"));
				break;
			case 1:
				template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":player_temples/chaos_head"));
				break;
		}
		Biome biome = world.getBiomeForCoordsBody(pos);
		if (biome==Biomes.DESERT||biome==Biomes.SAVANNA||biome==Biomes.TAIGA||biome==Biomes.PLAINS) {
			if(rand.nextInt(ModConfig.worldgen.structures.PlayerTemplesChance) == 0){
				IBlockState iblockstate = world.getBlockState(pos);
				world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
				template.getDataBlocks(pos, placementsettings);
				template.addBlocksToWorld(world, pos, placementsettings);
			}
		}
	}
}