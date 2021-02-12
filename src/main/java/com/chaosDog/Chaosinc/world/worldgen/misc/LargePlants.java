package com.chaosDog.Chaosinc.world.worldgen.misc;

import java.util.Random;

import com.chaosDog.Chaosinc.world.biome.BiomeRockyDesert;
import com.chaosDog.Chaosinc.world.dimension.DimensionRegistry;

import com.chaosDog.Chaosinc.init.modWorld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraft.world.biome.BiomeSavanna;
import net.minecraft.world.biome.BiomeSavannaMutated;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class LargePlants implements IWorldGenerator{
	public static final IBlockState AIR = Blocks.AIR.getDefaultState();
	public static final IBlockState DIRT = Blocks.DIRT.getDefaultState();
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		if (world.provider.getDimension()!=DimensionRegistry.DeepUnderground)
			return;
		int[] heights = {127,80,60};
		for (int i=0;i<10;i++) {
			int blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
			int blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
			int y = modWorld.getGroundFromAbove(world, 31, heights[rand.nextInt(3)], blockX, blockZ, Blocks.GRASS);
			BlockPos position = new BlockPos(blockX, y+1, blockZ);
			if (y>31) {
				Biome biome = world.getBiomeForCoordsBody(position);

				//oak or birch tree (forest biomes)
				if (biome== Biomes.FOREST||biome== Biomes.FOREST_HILLS||biome== Biomes.MUTATED_FOREST) {
					if (rand.nextInt(10)==0)
						genClassicTree(world, rand, position,1,false);
					else
						genClassicTree(world, rand, position,0,false);
				}
					
				//swamp oak tree
				if (biome instanceof BiomeSwamp)
					genClassicTree(world, rand, position,0,true);
					
				//birch tree
				if(biome == Biomes.BIRCH_FOREST||biome == Biomes.BIRCH_FOREST_HILLS||biome == Biomes.MUTATED_BIRCH_FOREST||biome == Biomes.MUTATED_BIRCH_FOREST_HILLS)
					genClassicTree(world, rand, position,1,false);
					
				//jungle tree
				if(biome instanceof BiomeJungle) {
					genClassicTree(world, rand, position,2,true);
					//generate some bushes
					for (int j=0;j<5;j++) {
						blockX = (chunkX * 16)+ 3 + rand.nextInt(10);
						blockZ = (chunkZ * 16)+ 3 + rand.nextInt(10);
						y = modWorld.getGroundFromAbove(world, 31, heights[rand.nextInt(3)], blockX, blockZ, Blocks.GRASS);
						position = new BlockPos(blockX, y+1, blockZ);
						if (y>30)
							genJungleBush(world, rand, position);
					}
				}

				//spruce tree
				if(biome instanceof BiomeTaiga)
					genSpruceTree(world, rand, position);
					
				//dark oak tree
				if(biome == Biomes.ROOFED_FOREST||biome == Biomes.MUTATED_ROOFED_FOREST)
					genBigOakTree(world, position,rand);
				
				//acacia tree
				if(biome instanceof BiomeSavanna|| biome instanceof BiomeSavannaMutated)
					genAcaciaTree(world, position);

				//cactus
				if(biome instanceof BiomeDesert||biome instanceof BiomeRockyDesert) {
					blockX = (chunkX * 16)+ rand.nextInt(16);
					blockZ = (chunkZ * 16)+ rand.nextInt(16);
					y = modWorld.getGroundFromAbove(world, 31, heights[rand.nextInt(3)], blockX, blockZ, new Block[] {Blocks.SAND, Blocks.SANDSTONE});
					position = new BlockPos(blockX, y+1, blockZ);
					genCactus(world, position, rand, 0);
				}
				if (biome == Biomes.MESA||biome == Biomes.MESA_CLEAR_ROCK||biome == Biomes.MUTATED_MESA||biome == Biomes.MUTATED_MESA_CLEAR_ROCK||biome == Biomes.MUTATED_MESA_ROCK) {
					blockX = (chunkX * 16)+ rand.nextInt(16);
					blockZ = (chunkZ * 16)+ rand.nextInt(16);
					y = modWorld.getGroundFromAbove(world, 31, heights[rand.nextInt(3)], blockX, blockZ, Blocks.SAND);
					position = new BlockPos(blockX, y+1, blockZ);
					genCactus(world, position, rand, 1);
				}
			}
		}
	}

	//cactus
	public static void genCactus(World world, BlockPos pos, Random rand, int sandType) {
		IBlockState[] sand = {
				Blocks.SAND.getDefaultState(),
				Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND)
		};
		world.setBlockState(pos.add(0,-1,0), sand[sandType]);
		int height = rand.nextInt(3);
		
		//blocks next to the cactus 
		Block n1 = world.getBlockState(pos.add(1,0,0)).getBlock();
		Block n2 = world.getBlockState(pos.add(-1,0,0)).getBlock();
		Block n3 = world.getBlockState(pos.add(0,0,1)).getBlock();
		Block n4 = world.getBlockState(pos.add(0,0,-1)).getBlock();
		
		if (n1 instanceof BlockAir && n2 instanceof BlockAir && n3 instanceof BlockAir && n4 instanceof BlockAir) {
			for (int y=0;y<=height;y++)
				world.setBlockState(pos.add(0,y,0), Blocks.CACTUS.getDefaultState());
		}
	}

	//acacia trees
	public static void genAcaciaTree(World world, BlockPos pos) {
		IBlockState log = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
		IBlockState leaves = Blocks.LEAVES2.getDefaultState()
				.withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA)
				.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
				.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		Block blockToReplace = world.getBlockState(pos).getBlock();

		//check if the area can fit the tree
		for (int i =0; i<7; i++) {
			blockToReplace = world.getBlockState(pos.add(0,i,0)).getBlock();
			if (!(blockToReplace instanceof BlockAir))
				return;
		}

		//generate dirt on the bottom
		world.setBlockState(pos.add(0,-1,0), DIRT);
	
		//generate the trunk
		int x = 1;
		for (int y=0;y<=7;y++) {
			if (y>3)
				world.setBlockState(pos.add(-1,y,0), log);
			else
				world.setBlockState(pos.add(0,y,0), log);
			
			if (y>2 && y<=5) {
				world.setBlockState(pos.add(x,y,0), log);
				x++;
			}
		}
		
		//generate the leaves
		BlockPos[] tops = {pos.add(3,5,0),pos.add(-1,7,0)};
		for (x=-2;x<=2;x++) {
			for (int z=-2;z<=2;z++) {
				blockToReplace = world.getBlockState(tops[0].add(x,0,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(tops[0].add(x,0,z), leaves);
				
			}
		}
		for (x=-1;x<=1;x++) {
			for (int z=-1;z<=1;z++) {
				blockToReplace = world.getBlockState(tops[0].add(x,1,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(tops[0].add(x,1,z), leaves);
				
			}
		}
		

		for (x=-2;x<=2;x++) {
			for (int z=-2;z<=2;z++) {
				blockToReplace = world.getBlockState(tops[1].add(x,0,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(tops[1].add(x,0,z), leaves);
				
			}
		}
		for (x=-1;x<=1;x++) {
			for (int z=-1;z<=1;z++) {
				blockToReplace = world.getBlockState(tops[1].add(x,1,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(tops[1].add(x,1,z), leaves);
				
			}
		}
		
		for (int i=0;i<2;i++) {
			world.setBlockState(tops[i].add(-2,0,-2), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(2,0,2), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(2,0,-2), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(-2,0,2), Blocks.AIR.getDefaultState());

			world.setBlockState(tops[i].add(-1,1,-1), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(1,1,1), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(1,1,-1), Blocks.AIR.getDefaultState());
			world.setBlockState(tops[i].add(-1,1,1), Blocks.AIR.getDefaultState());
			
		}
	}
	
	//dark oak trees
	public static void genBigOakTree(World world, BlockPos pos, Random rand) {
		IBlockState log = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
		IBlockState leaves = Blocks.LEAVES2.getDefaultState()
				.withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK)
				.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
				.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		Block blockToReplace = world.getBlockState(pos).getBlock();
		int height = 5+rand.nextInt(4);

		//check if the area can fit the tree
		for (int i =0; i<height; i++) {
			blockToReplace = world.getBlockState(pos.add(0,i,0)).getBlock();
			if (!(blockToReplace instanceof BlockAir))
				return;
		}
		
		//generate dirt on the bottom
		for (int x=0;x<=1;x++) {
			for (int z=0;z<=1;z++) {
				blockToReplace = world.getBlockState(pos.add(x,0,z)).getBlock();
				if (blockToReplace==Blocks.GRASS)
					world.setBlockState(pos.add(x,-1,z), DIRT);
			}
		}

		//generate the trunk
		for (int x = 0; x<=1; x++) {
			for (int z = 0; z<=1; z++) {
				for (int y=0; y<=height; y++) {
					world.setBlockState(pos.add(x,y,z), log);
					if (y>=height-1 &&z==0&&x==0) 
						world.setBlockState(pos.add(x,y,z-1), log);
					if (y>=height-1 &&z==1&&x==1) 
						world.setBlockState(pos.add(x,y,z+1), log);
				}	
			}
		}
			
		//generate the leaves
		for (int x = -3; x<=4; x++) {
			for (int z = -3; z<=4; z++) {
				for (int y=height-1; y<=height; y++) {
					blockToReplace = world.getBlockState(pos.add(x,y,z)).getBlock();
					if (blockToReplace instanceof BlockAir)
						world.setBlockState(pos.add(x,y,z), leaves);
				}
			}
		}
		int y=0;
		BlockPos[] corners = {pos.add(-3,0,-3),pos.add(4,0,4),pos.add(-3,0,4),pos.add(4,0,-3)};
		for (int i=0;i<4;i++) {
			for (int j=0;j<2;j++) {
				y=height-1+rand.nextInt(2);
				blockToReplace = world.getBlockState(corners[i].add(0,y,0)).getBlock();
				if (blockToReplace==Blocks.LEAVES2)
					world.setBlockState(corners[i].add(0,y,0), AIR);
			}
		}

		for (int x = -2; x<=3; x++) {
			for (int z = -2; z<=3; z++) {
				blockToReplace = world.getBlockState(pos.add(x,height+1,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(pos.add(x,height+1,z), leaves);
			}
		}
		corners = new BlockPos[] {pos.add(-2,0,-2),pos.add(3,0,3),pos.add(-2,0,3),pos.add(3,0,-2)};
		for (int i=0;i<4;i++) {
			blockToReplace = world.getBlockState(corners[i].add(0,height+1,0)).getBlock();
			if (blockToReplace==Blocks.LEAVES2 && rand.nextBoolean())
				world.setBlockState(corners[i].add(0,height+1,0), AIR);
		}

		for (int x = 0; x<=1; x++) {
			for (int z = 0; z<=1; z++) {
				blockToReplace = world.getBlockState(pos.add(x,height+2,z)).getBlock();
				if (blockToReplace instanceof BlockAir)
					world.setBlockState(pos.add(x,height+2,z), leaves);
			}
		}
	}

	//jungle bushes
	public static void genJungleBush(World world, Random rand, BlockPos pos) {
		IBlockState log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
		IBlockState leaves = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
			.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
		Block blockToReplace = world.getBlockState(pos).getBlock();
	
		//generate dirt
		world.setBlockState(pos.add(0,-1,0), DIRT);
	
		//generate the trunk
		world.setBlockState(pos, log);
	
		//generate the leaves
		BlockPos[] cornersLower = {pos.add(-2,0,-2),pos.add(2,0,2),pos.add(-2,0,2),pos.add(2,0,-2)};
		BlockPos[] cornersUpper = {pos.add(-1,1,-1),pos.add(1,1,1),pos.add(-1,1,1),pos.add(1,1,-1)};
		for (int x=-2; x<=2; x++) {
			for (int z=-2; z<=2; z++) {
				blockToReplace = world.getBlockState(pos.add(x,0,z)).getBlock();
				if (blockToReplace instanceof BlockAir||blockToReplace instanceof BlockVine)
					world.setBlockState(pos.add(x,0,z), leaves);
			}
		}
	
		for (int x=-1; x<=1; x++) {
			for (int z=-1; z<=1; z++) {
				blockToReplace = world.getBlockState(pos.add(x,1,z)).getBlock();
				if (blockToReplace==Blocks.AIR||blockToReplace instanceof BlockVine)
					world.setBlockState(pos.add(x,1,z), leaves);
			}
		}
		BlockPos corner = pos;
		for (int i=0;i<4;i++) {
			corner = cornersLower[i];
			blockToReplace = world.getBlockState(corner).getBlock();
			if (blockToReplace==Blocks.LEAVES||blockToReplace instanceof BlockAir)
				world.setBlockState(cornersLower[i], AIR);
			corner = cornersUpper[i];
			blockToReplace = world.getBlockState(corner).getBlock();
			if (blockToReplace==Blocks.LEAVES||blockToReplace instanceof BlockAir)
				world.setBlockState(cornersUpper[i], AIR);
		}
	}

	//oak, birch, and jungle trees
	public static void genClassicTree(World world, Random rand, BlockPos pos, int type, boolean genVines) {
		int height = 5 + rand.nextInt(3);
		if (type == 2)
			height = 6 + rand.nextInt(4);
		IBlockState[] logs = {
				Blocks.LOG.getDefaultState(),
				Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH),
				Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE)};
		IBlockState leaves[] = {
				Blocks.LEAVES.getDefaultState()
					.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
					.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)),
				Blocks.LEAVES.getDefaultState()
					.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH)
					.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
					.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)),
				Blocks.LEAVES.getDefaultState()
					.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
					.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false))
					.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true))};
		Block blockToReplace = world.getBlockState(pos).getBlock();

		//check if the area can fit the tree
		for (int i =0; i<height; i++) {
			blockToReplace = world.getBlockState(pos.add(0,i,0)).getBlock();
			if (!(blockToReplace instanceof BlockAir))
				return;
		}
		
		//generate dirt
		world.setBlockState(pos.add(0,-1,0), DIRT);
		
		//generate the trunk
		for (int i =0; i<height; i++)
			world.setBlockState(pos.add(0,i,0), logs[type]);
		
		BlockPos[] cornersLower = {pos.add(-2,0,-2),pos.add(2,0,2),pos.add(-2,0,2),pos.add(2,0,-2)};
		BlockPos[] cornersUpper = {pos.add(-1,0,-1),pos.add(1,0,1),pos.add(-1,0,1),pos.add(1,0,-1)};
		
		//generate the leaves
		for (int x=-2; x<=2; x++) {
			for (int z=-2; z<=2; z++) {
				for (int y=height-3; y<=height-2; y++) {
					blockToReplace = world.getBlockState(pos.add(x,y,z)).getBlock();
					if (blockToReplace instanceof BlockAir||blockToReplace instanceof BlockVine)
						world.setBlockState(pos.add(x,y,z), leaves[type]);
				}
			}
		}
		
		for (int x=-1; x<=1; x++) {
			for (int z=-1; z<=1; z++) {
				for (int y=height-1; y<=height; y++) {
					blockToReplace = world.getBlockState(pos.add(x,y,z)).getBlock();
					if (blockToReplace==Blocks.AIR||blockToReplace instanceof BlockVine)
						world.setBlockState(pos.add(x,y,z), leaves[type]);
				}
			}
		}

		BlockPos corner = pos.add(0,0,0);
		for (int i=0;i<4;i++) {
			for (int j=0;j<2;j++) {
				corner = cornersLower[i].add(0,height-3+rand.nextInt(2),0);
				blockToReplace = world.getBlockState(corner).getBlock();
				if (blockToReplace==Blocks.LEAVES||blockToReplace instanceof BlockAir)
					world.setBlockState(cornersLower[i].add(0,height-3+rand.nextInt(2),0), AIR);
				corner = cornersUpper[i].add(0,height+rand.nextInt(2),0);
				blockToReplace = world.getBlockState(corner).getBlock();
				if (blockToReplace==Blocks.LEAVES||blockToReplace instanceof BlockAir)
					world.setBlockState(cornersUpper[i].add(0,height+rand.nextInt(2),0), AIR);
			}
		}

		//generate vines
		if (!genVines)
			return;
		int a = 0;
		for (int i = 0; i < 4; i++) {
			for (int k=0; k<2; k++) {
				a = -1 +rand.nextInt(3);
				for (int j = height-2;j>0;j--) {
					blockToReplace = world.getBlockState(pos.add(3,j,a)).getBlock();
					if (blockToReplace==Blocks.AIR)
						world.setBlockState(pos.add(3,j,a), Blocks.VINE.getDefaultState().withProperty(BlockVine.WEST, true));
				}
				a = -1 +rand.nextInt(3);
				for (int j = height-2;j>0;j--) {
					blockToReplace = world.getBlockState(pos.add(-3,j,a)).getBlock();
					if (blockToReplace==Blocks.AIR)
						world.setBlockState(pos.add(-3,j,a), Blocks.VINE.getDefaultState().withProperty(BlockVine.EAST, true));
				}
				a = -1 +rand.nextInt(3);
				for (int j = height-2;j>0;j--) {
					blockToReplace = world.getBlockState(pos.add(a,j,-3)).getBlock();
					if (blockToReplace==Blocks.AIR)
						world.setBlockState(pos.add(a,j,-3), Blocks.VINE.getDefaultState().withProperty(BlockVine.SOUTH, true));
				}
				a = -1 +rand.nextInt(3);
				for (int j = height-2;j>0;j--) {
					blockToReplace = world.getBlockState(pos.add(a,j,3)).getBlock();
					if (blockToReplace==Blocks.AIR)
						world.setBlockState(pos.add(a,j,3), Blocks.VINE.getDefaultState().withProperty(BlockVine.NORTH, true));
				}
			}
		}
	}
	
	//spruce trees
	public static void genSpruceTree(World world, Random rand, BlockPos pos) {
		int height = 5 + rand.nextInt(3);
		IBlockState log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
		IBlockState leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
			Block blockToReplace = world.getBlockState(pos).getBlock();

		//check if the area can fit the tree
		for (int i =0; i<height; i++) {
			blockToReplace = world.getBlockState(pos.add(0,i,0)).getBlock();
			if (!(blockToReplace instanceof BlockAir||blockToReplace instanceof BlockSnow))
				return;
		}

		//generate dirt
		world.setBlockState(pos.add(0,-1,0), DIRT);
			
		//generate the trunk
		for (int i =0; i<=height; i++)
			world.setBlockState(pos.add(0,i,0), log);
			
		//generate the leaves
		for (int i=-1; i<4;i++) {
			int min=0;
			int max=0;
			int y= height - i;
			switch (i) {
				case 3:
				case 1:
				case -1:
					min=-1;
					max=1;
					break;
				case 2:
					min=-2;
					max=2;
				case 0:
					break;
			}
			if (i!=0) {
				for (int x = min;x<=max;x++) {
					for (int z = min;z<=max;z++) {
						blockToReplace = world.getBlockState(pos.add(x,y,z)).getBlock();
						if (blockToReplace==Blocks.AIR||blockToReplace instanceof BlockVine||blockToReplace instanceof BlockSnow)
							world.setBlockState(pos.add(x,y,z), leaves);
					}
				}
				BlockPos[] corners = {pos.add(min,y,min),pos.add(max,y,max),pos.add(min,y,max),pos.add(max,y,min)};
				if (world.getBlockState(corners[0]).getBlock()==Blocks.LEAVES)
					world.setBlockState(corners[0], AIR);
				if (world.getBlockState(corners[1]).getBlock()==Blocks.LEAVES)
					world.setBlockState(corners[1], AIR);
				if (world.getBlockState(corners[2]).getBlock()==Blocks.LEAVES)
					world.setBlockState(corners[2], AIR);
				if (world.getBlockState(corners[3]).getBlock()==Blocks.LEAVES)
					world.setBlockState(corners[3], AIR);
			}
		}
	}
}