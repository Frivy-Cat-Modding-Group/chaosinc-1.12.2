package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;

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

public class UndergroundVillage implements IWorldGenerator{
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(ModConfig.worldgen.structures.UndergroundVillage==false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":underground_village"));
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		BlockPos pos = new BlockPos(blockX, 20, blockZ);
		if (world.provider.getDimension() == 0) {
			if(rand.nextInt(ModConfig.worldgen.structures.UndergroundVillageChance) == 0){
				IBlockState iblockstate = world.getBlockState(pos);
				world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
				for (int x=0; x<32;x++) {
					for (int y=0; y<12;y++) {
						for (int z=0; z<32;z++) {
							world.setBlockState(pos.add(x,y,z), Misc.undergroundAir.getDefaultState());
						}
					}
				}

				template.getDataBlocks(pos, placementsettings);
				template.addBlocksToWorld(world, pos, placementsettings,0);
			}
		}
	}
}