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

public class VoidDungeon implements IWorldGenerator{
	public static final  String[] dungeons= {
			Reference.MOD_ID+":dungeons/void_dungeon_small_zombie",
			Reference.MOD_ID+":dungeons/void_dungeon_small_skeleton",
			Reference.MOD_ID+":dungeons/void_dungeon_small_spider",
			Reference.MOD_ID+":dungeons/dungeon_medium"
	};
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (ModConfig.worldgen.structures.VoidDungeon==false)
			return;
		if (world.provider.getDimension() != ModConfig.dimensions.DeepVoidDim)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template=templatemanager.getTemplate(minecraftserver,new ResourceLocation(dungeons[rand.nextInt(4)]));

		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int y = modWorld.getGroundFromAbove(world, 31, 120, blockX, blockZ,Misc.VoidStone);
		if (y<=31)
			return;
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		
		//generate the structure
		if(rand.nextInt(ModConfig.worldgen.structures.VoidDungeonChance) == 0){
			IBlockState iblockstate = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
					.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
					.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
			template.getDataBlocks(pos, placementsettings);
			template.addBlocksToWorld(world, pos.down(17), placementsettings);
		}
	}
}