package com.chaosDog.Chaosinc.init;

import com.chaosDog.Chaosinc.blocks.fluids.EnderAcid;
import com.chaosDog.Chaosinc.blocks.fluids.GlowingWater;
import com.chaosDog.Chaosinc.blocks.fluids.LiquidRedstone;
import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.worldgen.betterEnd.EndGrass;
import com.chaosDog.Chaosinc.world.worldgen.betterEnd.EndSurprise;
import com.chaosDog.Chaosinc.world.worldgen.misc.CustomLakes;
import com.chaosDog.Chaosinc.world.worldgen.misc.LargePlants;
import com.chaosDog.Chaosinc.world.worldgen.misc.Rose;
import com.chaosDog.Chaosinc.world.worldgen.misc.SeaFloorGen;
import com.chaosDog.Chaosinc.world.worldgen.ores.OreGenEnd;
import com.chaosDog.Chaosinc.world.worldgen.ores.OreGenNether;
import com.chaosDog.Chaosinc.world.worldgen.ores.OreGenOverworld;
import com.chaosDog.Chaosinc.world.worldgen.structures.Chaos.JeffTank;
import com.chaosDog.Chaosinc.world.worldgen.structures.Chaos.Shrine;
import com.chaosDog.Chaosinc.world.biome.gen.*;
import com.chaosDog.Chaosinc.world.worldgen.caveGen.*;
import com.chaosDog.Chaosinc.world.worldgen.structures.*;
import com.chaosDog.Chaosinc.world.worldgen.structures.Dungeons.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;
import java.util.Random;

public class modWorld {
    public static final Block[] ground = {Blocks.GRASS, Blocks.DIRT, Blocks.MYCELIUM, Blocks.SAND, Blocks.GRAVEL};
    public static final Block[] endGround = {Blocks.END_STONE, Misc.OvergrownEndStone};
    public static final Block[] mesaGround = {Blocks.STAINED_HARDENED_CLAY, Blocks.RED_SANDSTONE};
    public static final String[] dirStrings= {"east","west","north","south"};
    public static final IBlockState[] plants = {Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE,BlockTallGrass.EnumType.GRASS),Blocks.YELLOW_FLOWER.getDefaultState(),Blocks.RED_FLOWER.getDefaultState(),Misc.Rose.getDefaultState(),Misc.BlueRose.getDefaultState()};

    public static void register(){
        //world generators
        //cave gen
        IWorldGenerator[] cavegen= {
                new CaveGenBase(),
                new CaveGenDesert(),
                new CaveGenHumid(),
                new CaveGenIcy(),
                new CaveGenOcean(),
                new CaveGenMesa(),
                new CaveGenMushroomIsland(),
                new CaveGenCrystal()
        };

        //ores
        IWorldGenerator[] ores= {
                //overworld ore gen
                new OreGenOverworld(),

                //nether ore gen
                new OreGenNether(),

                //end ore gen
                new OreGenEnd()
        };

        //structures
        IWorldGenerator[] structures= {
                new Home(),
                new ResearchBase(),
                new Ocean(),
                new BrickPyramid(),
                new Minerbase(),
                new RoguePortal(),
                new MesaTemple(),
                new Runway(),
                new TreasureChest(),
                new SkyWheel(),
                new MiniStronghold(),
                new LandMine(),
                new Buoy(),
                new TreasureChest(),
                new SkyWheel(),
                new MiniStronghold(),
                new VoidDungeonLarge(),
                new VoidDungeon(),
                new VoidTower(),
                new PlayerTemples(),
                new GiantGrassBlock(),

                //created by ChaosDog
                new Shrine(),
                new JeffTank(),

                //easter egg
                new EasterEgg(),

                //underground villages
                new UndergroundVillage()
        };

        //miscellaneous
        IWorldGenerator[] misc= {
                //dirt, gravel, sand, and clay on the ocean floors
                new SeaFloorGen(),

                //flowers
                new Rose(),

                //trees in the Deep Underground (also the custom tree generator)
                new LargePlants(),

                new EndSurprise()
        };

        //biome decorators
        IWorldGenerator [] decorators = {
                new GlacierGen(),
                new RockyDesertGen(),
                new SaltFlatGen(),
                new TemperateJungleGen(),
                new VolcanicGen()
        };

        for(int i=0;i<cavegen.length;i++)
            GameRegistry.registerWorldGenerator(cavegen[i],0);
        for(int i=0;i<ores.length;i++)
            GameRegistry.registerWorldGenerator(ores[i],3);
        for(int i=0;i<structures.length;i++)
            GameRegistry.registerWorldGenerator(structures[i],i);
        for(int i=0;i<misc.length;i++)
            GameRegistry.registerWorldGenerator(misc[i],5);
        for(int i=0;i<decorators.length;i++)
            GameRegistry.registerWorldGenerator(decorators[i],0);

        GameRegistry.registerWorldGenerator(new EndGrass(),0);

        //lakes
        GameRegistry.registerWorldGenerator(new CustomLakes(1, EnderAcid.BlockEnderAcid.instance),0);
        GameRegistry.registerWorldGenerator(new CustomLakes(ModConfig.dimensions.DeepVoidDim, GlowingWater.BlockGlowingWater.instance),0);
        GameRegistry.registerWorldGenerator(new CustomLakes(ModConfig.dimensions.DeepVoidDim, Blocks.LAVA),0);

        GameRegistry.registerWorldGenerator(new CustomLakes(ModConfig.dimensions.DeepUndergroundDim, LiquidRedstone.BlockLiquidRedstone.instance),0);
        GameRegistry.registerWorldGenerator(new CustomLakes(ModConfig.dimensions.DeepUndergroundDim, Blocks.LAVA),0);

    }

    //generate an ore cluster in a specific block layer(like stone)
    public static void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances, Block layer) {
        int deltaY = maxY - minY;
        int rarity = 16;
        if (ore== Ores.RubyOre.getDefaultState())
            rarity=8;
        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(rarity), minY + random.nextInt(deltaY), z + random.nextInt(8));
            WorldGenMinable generator = new WorldGenMinable(ore, size, BlockMatcher.forBlock(layer));
            generator.generate(world, random, pos);
        }
    }

    //Returns a random Stone brick block state object
    public static IBlockState getRandomBrick( Random rand) {
        return getRandomBrick(rand, false);
    }
    public static IBlockState getRandomBrick(Random rand, Boolean isVoid) {
        if (isVoid)
            return Misc.VoidBricks.getDefaultState();
        IBlockState[] bricks = {
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED),
                Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY)
        };
        return bricks[rand.nextInt(3)];
    }

    //find ground to generate a structure or place a block
    public static int getGroundFromAbove(World world, int minY, int maxY, int x, int z, Block[] groundBlocks, boolean isUnderwater)
    {
        int y = maxY;
        boolean foundGround = false;
        while(!foundGround && y-->= minY)
        {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            Block blockAbove = world.getBlockState(new BlockPos(x,y+1,z)).getBlock();
            foundGround=(Arrays.asList(groundBlocks).contains(blockAt)  && blockAbove== Blocks.AIR) || (Arrays.asList(groundBlocks).contains(blockAt)  && blockAbove==Blocks.SNOW_LAYER);
            if (isUnderwater)
                foundGround=(Arrays.asList(groundBlocks).contains(blockAt)  && blockAbove==Blocks.WATER);
        }
        return y;
    }
    public static int getGroundFromAbove(World world, int minY, int maxY, int x, int z, Block groundBlock) {
        return getGroundFromAbove(world, minY, maxY, x, z, new Block [] {groundBlock},false);
    }
    public static int getGroundFromAbove(World world, int minY, int maxY, int x, int z, Block[] groundBlocks) {
        return getGroundFromAbove(world, minY, maxY, x, z, groundBlocks, false);
    }
}
