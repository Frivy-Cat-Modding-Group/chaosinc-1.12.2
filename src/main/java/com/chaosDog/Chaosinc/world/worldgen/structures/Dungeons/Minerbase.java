package com.chaosDog.Chaosinc.world.worldgen.structures.Dungeons;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;

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

public class Minerbase implements IWorldGenerator {
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (ModConfig.worldgen.structures.MinerBase == false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver,
				new ResourceLocation(Reference.MOD_ID + ":dungeons/miner_base"));

		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		if (world.provider.getDimension() == 0) {
			BlockPos pos = new BlockPos(blockX, 30, blockZ);
			if (rand.nextInt(ModConfig.worldgen.structures.MinerBaseChance) == 0) {
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