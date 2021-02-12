package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSnow;
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
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.IWorldGenerator;

public class EasterEgg implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (ModConfig.worldgen.structures.EasterEgg == false)
			return;
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver,
				new ResourceLocation(Reference.MOD_ID + ":easter_egg_1"));
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int y = modWorld.getGroundFromAbove(world, 63, 255, blockX, blockZ,
				new Block[] { Blocks.GRAVEL, Blocks.GRASS, Blocks.SAND, Blocks.DIRT, Blocks.WATER });
		boolean isUnderground = rand.nextBoolean();
		if (y <= 63 && isUnderground == false)
			return;
		if (isUnderground == true) {
			template = templatemanager.getTemplate(minecraftserver,
					new ResourceLocation(Reference.MOD_ID + ":easter_egg_2"));
		}
		BlockPos pos = new BlockPos(blockX, y, blockZ);
		if (isUnderground == true)
			pos = new BlockPos(blockX, 30, blockZ);
		if (world.provider.getDimension() == 0) {
			if (rand.nextInt(ModConfig.worldgen.structures.EasterEggChance) == 0) {
				IBlockState iblockstate = world.getBlockState(pos);
				world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
				template.getDataBlocks(pos, placementsettings);
				template.addBlocksToWorld(world, pos, placementsettings);

				if (isUnderground)
					return;

				// generate supports if needed
				int a = pos.getX();
				int b = pos.getY();
				int c = pos.getZ();
				Block blockToReplace = world.getBlockState(new BlockPos(a, b - 1, c)).getBlock();

				// (0,0)
				while ((blockToReplace == Blocks.WATER || blockToReplace == Blocks.COBBLESTONE
						|| blockToReplace == Blocks.FLOWING_WATER || blockToReplace instanceof BlockAir
						|| blockToReplace instanceof BlockBush || blockToReplace instanceof BlockSnow
						|| blockToReplace instanceof BlockFluidClassic || blockToReplace instanceof BlockLeaves)
						&& b > 0) {
					world.setBlockState(new BlockPos(a, b, c), modWorld.getRandomBrick(rand));
					blockToReplace = world.getBlockState(new BlockPos(a, b - 1, c)).getBlock();
					b--;
				}
				b = pos.getY();

				// (7,0)
				blockToReplace = world.getBlockState(new BlockPos(a + 6, b - 1, c)).getBlock();
				while ((blockToReplace == Blocks.WATER || blockToReplace == Blocks.COBBLESTONE
						|| blockToReplace == Blocks.FLOWING_WATER || blockToReplace instanceof BlockAir
						|| blockToReplace instanceof BlockBush || blockToReplace instanceof BlockSnow
						|| blockToReplace instanceof BlockFluidClassic || blockToReplace instanceof BlockLeaves)
						&& b > 0) {
					world.setBlockState(new BlockPos(a + 6, b, c), modWorld.getRandomBrick(rand));
					blockToReplace = world.getBlockState(new BlockPos(a + 6, b - 1, c)).getBlock();
					b--;
				}
				b = pos.getY();

				// (0,17)
				blockToReplace = world.getBlockState(new BlockPos(a, b - 1, c + 16)).getBlock();
				while ((blockToReplace == Blocks.WATER || blockToReplace == Blocks.COBBLESTONE
						|| blockToReplace == Blocks.FLOWING_WATER || blockToReplace instanceof BlockAir
						|| blockToReplace instanceof BlockBush || blockToReplace instanceof BlockSnow
						|| blockToReplace instanceof BlockFluidClassic || blockToReplace instanceof BlockLeaves)
						&& b > 0) {
					world.setBlockState(new BlockPos(a, b, c + 16), modWorld.getRandomBrick(rand));
					blockToReplace = world.getBlockState(new BlockPos(a, b - 1, c + 16)).getBlock();
					b--;
				}
				b = pos.getY();

				// (7,17)
				blockToReplace = world.getBlockState(new BlockPos(a + 6, b - 1, c + 16)).getBlock();
				while ((blockToReplace == Blocks.WATER || blockToReplace == Blocks.COBBLESTONE
						|| blockToReplace == Blocks.FLOWING_WATER || blockToReplace instanceof BlockAir
						|| blockToReplace instanceof BlockBush || blockToReplace instanceof BlockSnow
						|| blockToReplace instanceof BlockFluidClassic || blockToReplace instanceof BlockLeaves)
						&& b > 0) {
					world.setBlockState(new BlockPos(a + 6, b, c + 16), modWorld.getRandomBrick(rand));
					blockToReplace = world.getBlockState(new BlockPos(a + 6, b - 1, c + 16)).getBlock();
					b--;
				}
			}
		}
	}
}