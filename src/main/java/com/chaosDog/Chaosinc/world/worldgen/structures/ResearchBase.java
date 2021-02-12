package com.chaosDog.Chaosinc.world.worldgen.structures;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.biome.BiomeGlacier;
import com.chaosDog.Chaosinc.world.biome.BiomeSaltFlat;
import com.chaosDog.Chaosinc.world.biome.BiomeVolcanic;
import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ResearchBase implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		if(!ModConfig.worldgen.structures.Bases ||rand.nextInt(ModConfig.worldgen.structures.BaseChance) != 0)
			return;
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int level = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ, new Block[] {Misc.blueIce,Blocks.ICE,Ores.saltBlock2,Blocks.OBSIDIAN,Blocks.MAGMA,Blocks.STONE});
		if (level<=31)
			return;
		BlockPos pos = new BlockPos(blockX, level, blockZ);
		Biome biome = world.getBiome(pos);
		if (!(biome instanceof BiomeGlacier||biome instanceof BiomeVolcanic||biome instanceof BiomeSaltFlat))
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();

		//the direction the base will be facing
		int direction = rand.nextInt(4);

		//the corners of the base (relative to struct position)
		BlockPos[] corners = null;

		//coordiates of the front (relative to struct position)
		BlockPos[] front = null;

		BlockPos balconyCoords = null;

		Template base = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/research_base_"+ modWorld.dirStrings[direction]));
		Template balcony = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/base_patio_"+modWorld.dirStrings[direction]));
		Block support = Blocks.PACKED_ICE;
		if (biome instanceof BiomeVolcanic) {
			support = Blocks.OBSIDIAN;
			base = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/research_base_volcanic_"+modWorld.dirStrings[direction]));
			balcony = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/base_patio_volcanic_"+modWorld.dirStrings[direction]));
		}
		if (biome instanceof BiomeSaltFlat) {
			support = Ores.SaltBlock;
			base = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/research_base_salt_"+modWorld.dirStrings[direction]));
			balcony = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":research_base/base_patio_salt_"+modWorld.dirStrings[direction]));
		}

		//get the corner and front values for the facing
		switch (direction) {
			case 0: //east
				corners = new BlockPos[] {pos.add(6,0,0),pos.add(6,0,5),pos,pos.add(0,0,5)};
				front = new BlockPos[] {pos.add(7,0,3),pos.add(7,0,4)};
				balconyCoords = pos.add(7,0,0);
				break;

			case 1: //west
				corners = new BlockPos[] {pos,pos.add(0,0,5),pos.add(6,0,5),pos.add(6,0,0)};
				front = new BlockPos[] {pos.add(-1,0,3),pos.add(-1,0,4)};
				balconyCoords = pos.add(-3,0,0);
				break;

			case 2: //north
				corners = new BlockPos[] {pos.add(5,0,0),pos,pos.add(0,0,6),pos.add(5,0,6)};
				front = new BlockPos[] {pos.add(2,0,-1),pos.add(3,0,-1)};
				balconyCoords = pos.add(0,0,-3);
				break;
				
			case 3: //south
				corners = new BlockPos[] {pos.add(5,0,6),pos.add(5,0,0),pos,pos.add(0,0,6)};
				front = new BlockPos[] {pos.add(2,0,7),pos.add(3,0,7)};
				balconyCoords = pos.add(0,0,7);
				break;
		}

		//if the structure spawns at an invalid position, don't generate it
		if (!isFrontAir(world, new BlockPos[] {front[0].add(0,1,0),front[1].add(0,1,0)}))
			return;

		//generate the structure
		IBlockState iblockstate = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
		PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
				.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
				.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
		base.getDataBlocks(pos, placementsettings);
		base.addBlocksToWorld(world, pos, placementsettings);

		//add a balcony if needed
		boolean needsBalcony = isFrontAir(world, front);
		if (needsBalcony) {
			balcony.addBlocksToWorld(world, balconyCoords, placementsettings);
			Vec3i[] bfront = {new Vec3i(3, 0, 0),new Vec3i(-3,0,0),new Vec3i(0,0,-3),new Vec3i(0,0,3)};
			corners[0]=corners[0].add(bfront[direction]);
			corners[1]=corners[1].add(bfront[direction]);
		}

		//generate supports if needed
		int y=-1;
		Block blockToReplace = world.getBlockState(corners[0].add(0,-2,0)).getBlock();
		for (int i=0; i<4; i++) 
			world.setBlockState(corners[i].add(0,y,0), support.getDefaultState());
		for (int i=0; i<4; i++) {
			do {
				world.setBlockState(corners[i].add(0,y,0), support.getDefaultState());
				blockToReplace = world.getBlockState(corners[i].add(0,y-1,0)).getBlock();
				y--;
			} while ((blockToReplace==Blocks.LAVA
					||blockToReplace==Blocks.FLOWING_LAVA
					||blockToReplace==Blocks.WATER
					||blockToReplace==Blocks.FLOWING_WATER
					||blockToReplace instanceof BlockAir
					||blockToReplace instanceof BlockBush
					||blockToReplace instanceof BlockSnow
					||blockToReplace instanceof BlockFluidClassic
					||blockToReplace instanceof BlockLeaves)
					&&(level+y>1));
			y=-1;
		}
	}
	
	//check if the 2 blocks in front of the doors are a transparent block
	public boolean isFrontAir(World world, BlockPos[] positions) {
		Block block1 = world.getBlockState(positions[0]).getBlock();
		Block block2 = world.getBlockState(positions[1]).getBlock();
		if (
				block1==Blocks.LAVA
				||block1==Blocks.FLOWING_LAVA
				||block1==Blocks.WATER
				||block1==Blocks.FLOWING_WATER
				||block1 instanceof BlockAir
				||block1 instanceof BlockBush
				||block1 instanceof BlockSnow
				||block1 instanceof BlockFluidClassic
				||block1 instanceof BlockLeaves

				||block2==Blocks.LAVA
				||block2==Blocks.FLOWING_LAVA
				||block2==Blocks.WATER
				||block2==Blocks.FLOWING_WATER
				||block2 instanceof BlockAir
				||block2 instanceof BlockBush
				||block2 instanceof BlockSnow
				||block2 instanceof BlockFluidClassic
				||block2 instanceof BlockLeaves)
			return true;
		return false;
	}

}
