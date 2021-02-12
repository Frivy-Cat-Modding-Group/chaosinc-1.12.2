package com.chaosDog.Chaosinc.world.dimension;

import com.chaosDog.Chaosinc.init.ModBlocks.Crystals;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.mapgen.MapGenCustomCaves;
import com.chaosDog.Chaosinc.world.mapgen.MapGenCustomRavines;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

public class DVChunkGenerator implements IChunkGenerator
{
    private final Random rand;
    protected static final IBlockState STONE = Misc.VoidStone.getDefaultState();
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    private final World world;
    private NoiseGeneratorSimplex islandNoise;
    private double[] buffer;
    
    double[] pnr;
    double[] ar;
    double[] br;
    @SuppressWarnings("unused")
	private int chunkX = 0;
    
    @SuppressWarnings("unused")
	private int chunkZ = 0;
    
    //crystals
	private final WorldGenBush quartzGen = new WorldGenBush(Crystals.quartzCrystal);
	private final WorldGenBush diamondGen = new WorldGenBush(Crystals.diamondCrystal);
	private final WorldGenBush emeraldGen = new WorldGenBush(Crystals.emeraldCrystal);
	private final WorldGenBush redstoneGen = new WorldGenBush(Crystals.redstoneCrystal);
	private final WorldGenBush rubyGen = new WorldGenBush(Crystals.rubyCrystal);
	
	//special ores
	private final WorldGenerator coalGen = new WorldGenMinable(Ores.CoalOreVoid.getDefaultState(), 28,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator ironGen = new WorldGenMinable(Ores.IronOreVoid.getDefaultState(), 14,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator goldGen = new WorldGenMinable(Ores.GoldOreVoid.getDefaultState(), 7,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator silverGen = new WorldGenMinable(Ores.SilverOreVoid.getDefaultState(), 7,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator RStoneGen = new WorldGenMinable(Misc.RegenerationStone.getDefaultState(), 7,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator GrassGen = new WorldGenMinable(Blocks.GRASS.getDefaultState(), 42,BlockMatcher.forBlock(Misc.VoidStone));
	private final WorldGenerator LightGen = new WorldGenMinable(Blocks.GLOWSTONE.getDefaultState(),4,BlockMatcher.forBlock(Blocks.GRASS));
	
	//natural structures
	private MapGenBase genDVCaves = new MapGenCustomCaves(false);
	private MapGenBase genDVRavines = new MapGenCustomRavines(false);

    public DVChunkGenerator(World worldIn){
        world = worldIn;
        rand = worldIn.rand;
        lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        islandNoise = new NoiseGeneratorSimplex(this.rand);

        InitNoiseGensEvent.ContextEnd ctx = new InitNoiseGensEvent.ContextEnd(lperlinNoise1, lperlinNoise2, perlinNoise1, noiseGen5, noiseGen6, islandNoise);
        ctx = TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        lperlinNoise1 = ctx.getLPerlin1();
        lperlinNoise2 = ctx.getLPerlin2();
        perlinNoise1 = ctx.getPerlin();
        noiseGen5 = ctx.getDepth();
        noiseGen6 = ctx.getScale();
        islandNoise = ctx.getIsland();
        
		genDVCaves=TerrainGen.getModdedMapGen(genDVCaves, InitMapGenEvent.EventType.CAVE);
		genDVRavines=TerrainGen.getModdedMapGen(genDVRavines, InitMapGenEvent.EventType.RAVINE);

    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer){
        
    	this.buffer = this.getHeights(this.buffer, x * 2, 0, z * 2, 3, 33, 3);

        for (int i1 = 0; i1 < 2; ++i1){
            for (int j1 = 0; j1 < 2; ++j1){
                for (int k1 = 0; k1 < 32; ++k1){
                    double d1 = buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 0];
                    double d2 = buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 0];
                    double d3 = buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 0];
                    double d4 = buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 0];
                    double d5 = (buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 1] - d1) * 0.25D;
                    double d6 = (buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25D;
                    double d7 = (buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 1] - d3) * 0.25D;
                    double d8 = (buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25D;

                    for (int l1 = 0; l1 < 4; ++l1){
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.125D;
                        double d13 = (d4 - d2) * 0.125D;

                        for (int i2 = 0; i2 < 8; ++i2){
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.125D;

                            for (int j2 = 0; j2 < 8; ++j2){
                                IBlockState iblockstate = AIR;

                                if (d15 > 0.0D)
                                    iblockstate = STONE;

                                int k2 = i2 + i1 * 8;
                                int l2 = l1 + k1 * 4;
                                int i3 = j2 + j1 * 8;
                                primer.setBlockState(k2, l2, i3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void buildSurfaces(ChunkPrimer primer)
    {
        for (int i = 0; i < 16; ++i){
            for (int j = 0; j < 16; ++j){
                int l = -1;
                IBlockState iblockstate = STONE;
                IBlockState iblockstate1 = STONE;

                for (int i1 = 127; i1 >= 0; --i1){
                    IBlockState iblockstate2 = primer.getBlockState(i, i1, j);

                    if (iblockstate2.getMaterial() == Material.AIR)
                        l = -1;
                    
                    else if (iblockstate2.getBlock() == Blocks.STONE){
                        if (l == -1){
                            l = 1;

                            if (i1 >= 0)
                                primer.setBlockState(i, i1, j, iblockstate);
                            else
                                primer.setBlockState(i, i1, j, iblockstate1);
                        }
                        else if (l > 0)
                        {
                            --l;
                            primer.setBlockState(i, i1, j, iblockstate1);
                        }
                    }
                }
            }
        }
    }

    @Override
	public Chunk generateChunk(int x, int z)
    {
        chunkX = x;
        chunkZ = z;
        ChunkPrimer chunkprimer = new ChunkPrimer();
        setBlocksInChunk(x, z, chunkprimer);
        buildSurfaces(chunkprimer);

		genDVRavines.generate(world, x, z, chunkprimer);
		genDVCaves.generate(world, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        
        chunk.generateSkylightMap();
        return chunk;
    }

    private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_)
    {
        float f = p_185960_1_ * 2 + p_185960_3_;
        float f1 = p_185960_2_ * 2 + p_185960_4_;
        float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;

        if (f2 > 80.0F)
            f2 = 80.0F;

        if (f2 < -100.0F)
            f2 = -100.0F;

        for (int i = -12; i <= 12; ++i)
        {
            for (int j = -12; j <= 12; ++j)
            {
                long k = p_185960_1_ + i;
                long l = p_185960_2_ + j;

                if (k * k + l * l > 4096L && this.islandNoise.getValue(k, l) < -0.8999999761581421D)
                {
                    float f3 = (MathHelper.abs(k) * 3439.0F + MathHelper.abs(l) * 147.0F) % 13.0F + 9.0F;
                    f = p_185960_3_ - i * 2;
                    f1 = p_185960_4_ - j * 2;
                    float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;

                    if (f4 > 80.0F)
                        f4 = 80.0F;

                    if (f4 < -100.0F)
                        f4 = -100.0F;

                    if (f4 > f2)
                        f2 = f4;
                }
            }
        }

        return f2;
    }

    private double[] getHeights(double[] p_185963_1_, int p_185963_2_, int p_185963_3_, int p_185963_4_, int p_185963_5_, int p_185963_6_, int p_185963_7_)
    {
        ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(this, p_185963_1_, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return event.getNoisefield();

        if (p_185963_1_ == null)
            p_185963_1_ = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];

        double d0 = 684.412D;
        d0 = d0 * 2.0D;
        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
        int i = p_185963_2_ / 2;
        int j = p_185963_4_ / 2;
        int k = 0;

        for (int l = 0; l < p_185963_5_; ++l)
        {
            for (int i1 = 0; i1 < p_185963_7_; ++i1)
            {
                float f = this.getIslandHeightValue(i, j, l, i1);

                for (int j1 = 0; j1 < p_185963_6_; ++j1)
                {
                    double d2 = this.ar[k] / 512.0D;
                    double d3 = this.br[k] / 512.0D;
                    double d5 = (this.pnr[k] / 10.0D + 1.0D) / 2.0D;
                    double d4;

                    if (d5 < 0.0D)
                        d4 = d2;
                    else if (d5 > 1.0D)
                        d4 = d3;
                    else
                        d4 = d2 + (d3 - d2) * d5;

                    d4 = d4 - 8.0D;
                    d4 = d4 + f;
                    int k1 = 2;

                    if (j1 > p_185963_6_ / 2 - k1)
                    {
                        double d6 = (j1 - (p_185963_6_ / 2 - k1)) / 64.0F;
                        d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
                        d4 = d4 * (1.0D - d6) + -3000.0D * d6;
                    }

                    k1 = 8;

                    if (j1 < k1)
                    {
                        double d7 = (k1 - j1) / (k1 - 1.0F);
                        d4 = d4 * (1.0D - d7) + -30.0D * d7;
                    }

                    p_185963_1_[k] = d4;
                    ++k;
                }
            }
        }

        return p_185963_1_;
    }

    @SuppressWarnings("deprecation")
	@Override
	public void populate(int x, int z)
    {
		ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, false);
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
    	if (TerrainGen.decorate(world, rand, blockpos,DecorateBiomeEvent.Decorate.EventType.SHROOM)) {
			if (rand.nextBoolean()) {
				//crystals
				quartzGen.generate(world, rand,	blockpos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
				diamondGen.generate(world, rand,blockpos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
				emeraldGen.generate(world, rand,blockpos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
				redstoneGen.generate(world, rand,blockpos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
				rubyGen.generate(world, rand,blockpos.add(rand.nextInt(16) + 8, rand.nextInt(256), rand.nextInt(16) + 8));
			}
    	}

		if (TerrainGen.generateOre(world, rand, coalGen, blockpos,OreGenEvent.GenerateMinable.EventType.QUARTZ))
		for (int l1 = 0; l1 < 32; ++l1) {
			//ores
			coalGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
			ironGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
			goldGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
			silverGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
			RStoneGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
            GrassGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
            LightGen.generate(world, rand,blockpos.add(rand.nextInt(16), rand.nextInt(216) + 20, rand.nextInt(16)));
		}
    }

    @Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        return world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    }

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@SubscribeEvent
    public static void rabbitsInVoid(EntityJoinWorldEvent event) {
		if (!(event.getEntity() instanceof EntityRabbit)) {
			return;
		}
		EntityRabbit rabbit = (EntityRabbit) event.getEntity();

		rabbit.setRabbitType(99);
	}
}