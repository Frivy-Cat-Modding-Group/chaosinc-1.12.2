package com.chaosDog.Chaosinc.world.worldgen.structures;

import java.util.Random;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Chests;
import com.chaosDog.Chaosinc.init.ModBlocks.Doors;
import com.chaosDog.Chaosinc.init.ModBlocks.Fences;
import com.chaosDog.Chaosinc.init.ModBlocks.Gates;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Plates;
import com.chaosDog.Chaosinc.init.ModBlocks.Tables;
import com.chaosDog.Chaosinc.init.ModBlocks.Trapdoors;
import com.chaosDog.Chaosinc.world.biome.BiomeRockyDesert;
import com.chaosDog.Chaosinc.world.biome.BiomeTemperateJungle;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSavannaMutated;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.IWorldGenerator;

public class Home implements IWorldGenerator {
	public static final IBlockState[] logs = {
			Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
			Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE),
			Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH),
			Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE),
			Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA),
			Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK),
			Misc.CompressedCactus.getDefaultState()
	};

	public static final IBlockState[] planks = {
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.OAK),
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE),
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.BIRCH),
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE),
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA),
			Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK),
			Misc.CompressedCactus.getDefaultState()
	};
	
	public static final IBlockState[] doors = {
			Blocks.OAK_DOOR.getDefaultState(),
			Blocks.SPRUCE_DOOR.getDefaultState(),
			Blocks.BIRCH_DOOR.getDefaultState(),
			Blocks.JUNGLE_DOOR.getDefaultState(),
			Blocks.ACACIA_DOOR.getDefaultState(),
			Blocks.DARK_OAK_DOOR.getDefaultState(),
			Doors.CactusDoor.getDefaultState()
	};
	
	public static final IBlockState[] plates = {
			Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(),
			Plates.SprucePlate.getDefaultState(),
			Plates.BirchPlate.getDefaultState(),
			Plates.JunglePlate.getDefaultState(),
			Plates.AcaciaPlate.getDefaultState(),
			Plates.DarkOakPlate.getDefaultState(),
			Plates.CactusPlate.getDefaultState()
	};
	
	public static final IBlockState[] tables = {
			Blocks.CRAFTING_TABLE.getDefaultState(),
			Tables.SpruceWorkbench.getDefaultState(),
			Tables.BirchWorkbench.getDefaultState(),
			Tables.JungleWorkbench.getDefaultState(),
			Tables.AcaciaWorkbench.getDefaultState(),
			Tables.DarkOakWorkbench.getDefaultState(),
			Tables.CactusWorkbench.getDefaultState()
	};
	
	public static final IBlockState[] fences = {
			Blocks.OAK_FENCE.getDefaultState(),
			Blocks.SPRUCE_FENCE.getDefaultState(),
			Blocks.BIRCH_FENCE.getDefaultState(),
			Blocks.JUNGLE_FENCE.getDefaultState(),
			Blocks.ACACIA_FENCE.getDefaultState(),
			Blocks.DARK_OAK_FENCE.getDefaultState(),
			Fences.CactusFence.getDefaultState()
	};
	
	public static final IBlockState[] gates = {
			Blocks.OAK_FENCE_GATE.getDefaultState(),
			Blocks.SPRUCE_FENCE_GATE.getDefaultState(),
			Blocks.BIRCH_FENCE_GATE.getDefaultState(),
			Blocks.JUNGLE_FENCE_GATE.getDefaultState(),
			Blocks.ACACIA_FENCE_GATE.getDefaultState(),
			Blocks.DARK_OAK_FENCE_GATE.getDefaultState(),
			Gates.CactusFenceGate.getDefaultState()
	};
	public static final EnumFacing[] doorDirs = {EnumFacing.WEST,EnumFacing.EAST,EnumFacing.SOUTH,EnumFacing.NORTH};

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		if(ModConfig.worldgen.structures.Homes==false||rand.nextInt(ModConfig.worldgen.structures.HomesChance) != 0)
			return;
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int level = modWorld.getGroundFromAbove(world, 31, 255, blockX, blockZ, modWorld.ground);
		if (level<=31)
			return;
		BlockPos pos = new BlockPos(blockX, level, blockZ);
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();

		//the direction the house will be facing
		int direction = rand.nextInt(4);

		//the corners of the house (relative to struct position)
		BlockPos[] corners = null;

		//coordiates of the front (relative to struct position)
		BlockPos[] front = null;

		BlockPos balconyCoords = null;

		Template house = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":homes/starter_house_"+modWorld.dirStrings[direction]));
		Template balcony = templatemanager.getTemplate(minecraftserver, new ResourceLocation(Reference.MOD_ID+":homes/patio_"+modWorld.dirStrings[direction]));

		//get the corner and front values for the facing
		switch (direction) {
			case 0:
				corners = new BlockPos[] {pos.add(5,0,1),pos.add(5,0,7),pos.add(0,0,1),pos.add(0,0,7)};
				front = new BlockPos[] {pos.add(6,0,3),pos.add(6,0,4)};
				balconyCoords = pos.add(6,0,1);
				break;

			case 1:
				corners = new BlockPos[] {pos.add(0,0,0),pos.add(0,0,6),pos.add(5,0,0),pos.add(5,0,6)};
				front = new BlockPos[] {pos.add(-1,0,4),pos.add(-1,0,5)};
				balconyCoords = pos.add(-3,0,0);
				break;

			case 2:
				corners = new BlockPos[] {pos.add(1,0,0),pos.add(7,0,0),pos.add(1,0,5),pos.add(7,0,5)};
				front = new BlockPos[] {pos.add(3,0,-1),pos.add(4,0,-1)};
				balconyCoords = pos.add(1,0,-3);
				break;
				
			case 3:
				corners = new BlockPos[] {pos.add(0,0,5),pos.add(6,0,5),pos.add(0,0,0),pos.add(6,0,0)};
				front = new BlockPos[] {pos.add(4,0,6),pos.add(5,0,6)};
				balconyCoords = pos.add(0,0,6);
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
		house.getDataBlocks(pos, placementsettings);
		house.addBlocksToWorld(world, pos, placementsettings);

		//add a balcony if needed
		boolean needsBalcony = isFrontAir(world, front);
		if (needsBalcony) {
			balcony.addBlocksToWorld(world, balconyCoords, placementsettings);
			Vec3i[] bfront = {new Vec3i(3, 0, 0),new Vec3i(-3,0,0),new Vec3i(0,0,-3),new Vec3i(0,0,3)};
			corners[0]=corners[0].add(bfront[direction]);
			corners[1]=corners[1].add(bfront[direction]);
		}

		int biomeIndex=replaceBiomeBlocks(world, pos);

		//add in the doors
		BlockPos[] doorLoc = null;
		switch (direction) {
			case 0:
				doorLoc = new BlockPos[] {pos.add(5,1,3),pos.add(5,1,4)};
				break;
			case 1:
				doorLoc = new BlockPos[] {pos.add(0,1,4),pos.add(0,1,3)};
				break;
			case 2:
				doorLoc = new BlockPos[] {pos.add(3,1,0),pos.add(4,1,0)};
				break;
			case 3:
				doorLoc = new BlockPos[] {pos.add(4,1,5),pos.add(3,1,5)};
				break;
		}
		//left
		world.setBlockState(doorLoc[1], doors[biomeIndex]
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER)
				.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT)
				.withProperty(BlockDoor.FACING, doorDirs[direction]));
		world.setBlockState(doorLoc[1].add(0,1,0), doors[biomeIndex]
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER)
				.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT)
				.withProperty(BlockDoor.FACING, doorDirs[direction]));
		//right
		world.setBlockState(doorLoc[0], doors[biomeIndex]
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER)
				.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT)
				.withProperty(BlockDoor.FACING, doorDirs[direction]));
		world.setBlockState(doorLoc[0].add(0,1,0), doors[biomeIndex]
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER)
				.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT)
				.withProperty(BlockDoor.FACING, doorDirs[direction]));

		//generate supports if needed
		int y=-1;
		Block blockToReplace = world.getBlockState(corners[0].add(0,-2,0)).getBlock();
		for (int i=0; i<4; i++) 
			world.setBlockState(corners[i].add(0,y,0), logs[biomeIndex]);
		for (int i=0; i<4; i++) {
			do {
				world.setBlockState(corners[i].add(0,y,0), logs[biomeIndex]);
				blockToReplace = world.getBlockState(corners[i].add(0,y-1,0)).getBlock();
				y--;
			} while ((blockToReplace==Blocks.WATER
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

	//replace the default blocks with biome-specific blocks
	public int replaceBiomeBlocks(World world, BlockPos pos) {
		Random rand = world.rand;
		int biomeIndex=0;
		Biome biome = world.getBiomeForCoordsBody(pos);
		IBlockState blockstateToReplace = world.getBlockState(pos);
		Block blockToReplace = blockstateToReplace.getBlock();
		EnumDyeColor bedColor = EnumDyeColor.RED;
		for(int x = -3; x<=8;x++) {
			for(int y = 0; y<=5;y++) {
				for(int z = -3; z<=8;z++) {
					blockstateToReplace = world.getBlockState(pos.add(x,y,z));
					blockToReplace = blockstateToReplace.getBlock();

					//spruce home
					if(biome instanceof BiomeTaiga) {
						biomeIndex=1;
						bedColor = EnumDyeColor.LIGHT_BLUE;
					}

					//birch forest home
					if(biome == Biomes.BIRCH_FOREST_HILLS|| biome == Biomes.BIRCH_FOREST_HILLS||biome == Biomes.BIRCH_FOREST||biome == Biomes.MUTATED_BIRCH_FOREST||biome == Biomes.MUTATED_BIRCH_FOREST_HILLS) {	
						biomeIndex=2;
						bedColor = EnumDyeColor.CYAN;
					}

					//jungle home
					if(biome instanceof BiomeJungle) { 
						biomeIndex=3;
						bedColor = EnumDyeColor.LIME;
					}
					
					//savanna home
					if(biome instanceof BiomeSavanna || biome instanceof BiomeSavannaMutated) {
						biomeIndex=4;
						bedColor = EnumDyeColor.ORANGE;
					}

					//roofed forest home
					if(biome == Biomes.ROOFED_FOREST||biome == Biomes.MUTATED_ROOFED_FOREST) {
						biomeIndex=5;
						bedColor = EnumDyeColor.BLUE;
					}

					//desert home
					if(biome instanceof BiomeDesert) {
						biomeIndex=6;
						bedColor = EnumDyeColor.YELLOW;
					}

					//mesa and rocky desert home
					if(biome instanceof BiomeMesa||biome instanceof BiomeRockyDesert)
						bedColor = EnumDyeColor.BROWN;

					//swamp and temperate rainforest home
					if (biome instanceof BiomeSwamp||biome instanceof BiomeTemperateJungle)
						bedColor = EnumDyeColor.GREEN;

					if (blockToReplace == Blocks.CHEST) {
						if (biomeIndex==6 ) {
							EnumFacing chestDir = blockstateToReplace.getValue(BlockChest.FACING);
							world.setBlockState(pos.add(x,y,z), Chests.CompressedCactusChest.getDefaultState().withProperty(BlockChest.FACING, chestDir));
						}
						TileEntity chestTE = world.getTileEntity(pos.add(x,y,z));
						((TileEntityChest) chestTE).setLootTable(new ResourceLocation("nnparadisemod:starter_chest"),rand.nextLong());
					}

					//set bed color
					if (blockToReplace == Blocks.BED) {
						TileEntity bedTE = world.getTileEntity(pos.add(x,y,z));
						((TileEntityBed) bedTE).setColor(bedColor);
					}

					if (blockToReplace==Blocks.OAK_FENCE_GATE) {
						EnumFacing gateDir = blockstateToReplace.getValue(BlockFenceGate.FACING);
						world.setBlockState(pos.add(x,y,z), gates[biomeIndex].withProperty(BlockFenceGate.FACING, gateDir));
					}
					if (blockToReplace==Blocks.TRAPDOOR && biomeIndex==6) {
						EnumFacing hatchDir = blockstateToReplace.getValue(BlockTrapDoor.FACING);
						world.setBlockState(pos.add(x,y,z), Trapdoors.CactusTrapDoor.getDefaultState().withProperty(BlockTrapDoor.FACING, hatchDir));
					}
					if (blockToReplace==Blocks.PLANKS)
						world.setBlockState(pos.add(x,y,z), planks[biomeIndex]);
					if (blockToReplace==Blocks.OAK_FENCE)
						world.setBlockState(pos.add(x,y,z), fences[biomeIndex]);
					if (blockToReplace==Blocks.WOODEN_PRESSURE_PLATE)
						world.setBlockState(pos.add(x,y,z), plates[biomeIndex]);
					if (blockToReplace==Blocks.CRAFTING_TABLE)
						world.setBlockState(pos.add(x,y,z), tables[biomeIndex]);
				}
			}			
		}
		return biomeIndex;
	}

	//check if the 2 blocks in front of the doors are a transparent block
	public boolean isFrontAir(World world, BlockPos[] positions) {
		Block block1 = world.getBlockState(positions[0]).getBlock();
		Block block2 = world.getBlockState(positions[1]).getBlock();
		if (block1==Blocks.WATER
				||block1==Blocks.FLOWING_WATER
				||block1 instanceof BlockAir
				||block1 instanceof BlockBush
				||block1 instanceof BlockSnow
				||block1 instanceof BlockFluidClassic
				||block1 instanceof BlockLeaves

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