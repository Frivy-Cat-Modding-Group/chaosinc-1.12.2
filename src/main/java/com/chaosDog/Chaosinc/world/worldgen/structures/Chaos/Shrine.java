package com.chaosDog.Chaosinc.world.worldgen.structures.Chaos;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;

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

public class Shrine implements IWorldGenerator{
	public static final String[] templateString = {Reference.MOD_ID+":chaos/bill_shrine",Reference.MOD_ID+":chaos/john_shrine"};
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {			
		if(ModConfig.worldgen.structures.chaos.Shrines)
			return;

		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		String[] templateString = {Reference.MOD_ID+":chaos/bill_shrine",Reference.MOD_ID+":chaos/john_shrine"};
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(templateString[rand.nextInt(2)]));;
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		
		//generate the structure
		if (world.provider.getDimension() == 1) {	
			int y = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ, modWorld.endGround);
			if (y<=31)
				return;
			BlockPos pos = new BlockPos(blockX, y, blockZ);
			
			if(rand.nextInt(ModConfig.worldgen.structures.chaos.ShrinesChance) == 0){
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