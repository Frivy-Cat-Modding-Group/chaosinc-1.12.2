package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Lamps;

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

public class Buoy implements IWorldGenerator{
	private static final Block[] lamps = {
			Lamps.LitBlackLamp,
			Lamps.LitBlueLamp,
			Lamps.LitBrownLamp,
			Lamps.LitCyanLamp,
			Lamps.LitGrayLamp,
			Lamps.LitGreenLamp,
			Lamps.LitLightBlueLamp,
			Lamps.LitLimeLamp,
			Lamps.LitMagentaLamp,
			Lamps.LitOrangeLamp,
			Lamps.LitPinkLamp,
			Lamps.LitPurpleLamp,
			Lamps.LitRedLamp,
			Lamps.LitSilverLamp,
			Lamps.LitWhiteLamp,
			Lamps.LitYellowLamp,
			Blocks.LIT_REDSTONE_LAMP
	};
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(ModConfig.worldgen.structures.Buoys==false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":buoy"));
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int y = modWorld.getGroundFromAbove(world, 60, 255, blockX, blockZ, new Block[] {Blocks.WATER, Blocks.ICE});
		if (y<=60)
			return;
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		if(rand.nextInt(ModConfig.worldgen.structures.BuoysChance) == 0){
			IBlockState iblockstate = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
					.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
					.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
			template.getDataBlocks(pos, placementsettings);
			template.addBlocksToWorld(world, pos.down(), placementsettings);
			Block lamp = lamps[rand.nextInt(17)];
			world.setBlockState(pos.add(2,1,2), lamp.getDefaultState());
			world.setBlockState(pos.add(3,1,2), lamp.getDefaultState());
			world.setBlockState(pos.add(2,2,2), lamp.getDefaultState());
			world.setBlockState(pos.add(3,2,2), lamp.getDefaultState());
			world.setBlockState(pos.add(2,1,3), lamp.getDefaultState());
			world.setBlockState(pos.add(3,1,3), lamp.getDefaultState());
			world.setBlockState(pos.add(2,2,3), lamp.getDefaultState());
			world.setBlockState(pos.add(3,2,3), lamp.getDefaultState());
		}
	}
}