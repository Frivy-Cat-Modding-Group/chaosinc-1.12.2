package com.chaosDog.Chaosinc.world.mapgen;

import java.util.Random;

import com.google.common.base.MoreObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public class MapGenCustomCaves extends MapGenBase{
	private final boolean fillWater;
	public MapGenCustomCaves(boolean fill) {
		fillWater=fill;
	}
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState WATER = Blocks.WATER.getDefaultState();

    protected void addRoom(long seed, int originalX, int originalZ, ChunkPrimer primer, double d0, double d1, double d2){
        addTunnel(seed, originalX, originalZ, primer, d0, d1, d2, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void addTunnel(long seed, int chunkX, int chunkZ, ChunkPrimer primer, double p_180702_6_, double p_180702_8_, double p_180702_10_, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_){
        double d0 = (double)(chunkX * 16 + 8);
        double d1 = (double)(chunkZ * 16 + 8);
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(seed);

        if (p_180702_16_ <= 0){
            int i = range * 16 - 16;
            p_180702_16_ = i - random.nextInt(i / 4);
        }

        boolean flag2 = false;

        if (p_180702_15_ == -1){
            p_180702_15_ = p_180702_16_ / 2;
            flag2 = true;
        }

        int j = random.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;

        for (boolean flag = random.nextInt(6) == 0; p_180702_15_ < p_180702_16_; ++p_180702_15_){
            double d2 = 1.5D + (double)(MathHelper.sin((float)p_180702_15_ * (float)Math.PI / (float)p_180702_16_) * p_180702_12_);
            double d3 = d2 * p_180702_17_;
            float f2 = MathHelper.cos(p_180702_14_);
            float f3 = MathHelper.sin(p_180702_14_);
            p_180702_6_ += (double)(MathHelper.cos(p_180702_13_) * f2);
            p_180702_8_ += (double)f3;
            p_180702_10_ += (double)(MathHelper.sin(p_180702_13_) * f2);

            if (flag)
                p_180702_14_ = p_180702_14_ * 0.92F;
            else
                p_180702_14_ = p_180702_14_ * 0.7F;

            p_180702_14_ = p_180702_14_ + f1 * 0.1F;
            p_180702_13_ += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && p_180702_15_ == j && p_180702_12_ > 1.0F && p_180702_16_ > 0){
                addTunnel(random.nextLong(), chunkX, chunkZ, primer, p_180702_6_, p_180702_8_, p_180702_10_, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ - ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                addTunnel(random.nextLong(), chunkX, chunkZ, primer, p_180702_6_, p_180702_8_, p_180702_10_, random.nextFloat() * 0.5F + 0.5F, p_180702_13_ + ((float)Math.PI / 2F), p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0){
                double d4 = p_180702_6_ - d0;
                double d5 = p_180702_10_ - d1;
                double d6 = (double)(p_180702_16_ - p_180702_15_);
                double d7 = (double)(p_180702_12_ + 2.0F + 16.0F);

                if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7)
                    return;

                if (p_180702_6_ >= d0 - 16.0D - d2 * 2.0D && p_180702_10_ >= d1 - 16.0D - d2 * 2.0D && p_180702_6_ <= d0 + 16.0D + d2 * 2.0D && p_180702_10_ <= d1 + 16.0D + d2 * 2.0D){
                    int k2 = MathHelper.floor(p_180702_6_ - d2) - chunkX * 16 - 1;
                    int k = MathHelper.floor(p_180702_6_ + d2) - chunkX * 16 + 1;
                    int l2 = MathHelper.floor(p_180702_8_ - d3) - 1;
                    int l = MathHelper.floor(p_180702_8_ + d3) + 1;
                    int i3 = MathHelper.floor(p_180702_10_ - d2) - chunkZ * 16 - 1;
                    int i1 = MathHelper.floor(p_180702_10_ + d2) - chunkZ * 16 + 1;

                    if (k2 < 0) k2 = 0;
                    if (k > 16) k = 16;
                    if (l2 < 1) l2 = 1;
                    if (l > 248) l = 248;
                    if (i3 < 0) i3 = 0;
                    if (i1 > 16) i1 = 16;

                    for (int j1 = k2; j1 < k; ++j1){
                        for (int k1 = i3; k1 < i1; ++k1){
                            for (int l1 = l + 1; l1 >= l2 - 1; --l1){
                                if (l1 >= 0 && l1 < 256){
                                    if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1) l1 = l2;
                                }
                            }
                        }
                    }

                    for (int j3 = k2; j3 < k; ++j3){
                    	double d10 = ((double)(j3 + chunkX * 16) + 0.5D - p_180702_6_) / d2;

                        for (int i2 = i3; i2 < i1; ++i2){
                        	double d8 = ((double)(i2 + chunkZ * 16) + 0.5D - p_180702_10_) / d2;
                            boolean flag1 = false;

                            if (d10 * d10 + d8 * d8 < 1.0D){
                            	for (int j2 = l; j2 > l2; --j2){
                            		double d9 = ((double)(j2 - 1) + 0.5D - p_180702_8_) / d3;

                                    if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D){
                                    	IBlockState state = primer.getBlockState(j3, j2, i2);
                                        IBlockState up = (IBlockState)MoreObjects.firstNonNull(primer.getBlockState(j3, j2 + 1, i2), AIR);
                                        flag1 = isTopBlock(primer, j3, j2, i2, chunkX, chunkZ);

                                        digBlock(primer, j3, j2, i2, chunkX, chunkZ, flag1, state, up);
                                    }
                            	}
                            }
                        }

                        if (flag2)
                        	break;
                    }
                }
            }
        }
    }

    protected boolean canReplaceBlock(IBlockState block1, IBlockState block2){
        if (block1.getBlock()!=Blocks.BEDROCK && block1.getBlock()!=Blocks.AIR) return true;
        return false;
    }

    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn){
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
        if (this.rand.nextInt(7) != 0)
            i = 0;

        for (int j = 0; j < i; ++j){
            double d0 = (double)(chunkX * 16 + this.rand.nextInt(16));
            double d1 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
            double d2 = (double)(chunkZ * 16 + this.rand.nextInt(16));
            int k = 1;

            if (this.rand.nextInt(4) == 0){
                this.addRoom(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2);
                k += this.rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l){
                float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0)
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;

                this.addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
    
    private boolean isTopBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ){
        return (primer.getBlockState(x, y, z).getBlock() == Blocks.GRASS);
    }

    protected void digBlock(ChunkPrimer primer, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up)
    {
        Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;
        if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock())
        {
        	//water generates below level 31
        	if (y<=31 && fillWater) {
                primer.setBlockState(x, y, z, WATER);
                return;
        	}
        	
        	//if this is an ocean biome, fill it completely with water instead
        	if (biome==Biomes.OCEAN||biome==Biomes.DEEP_OCEAN&&y-1>=10 && fillWater) {
        		primer.setBlockState(x, y, z, WATER);
                return;
        	}
        	
        	primer.setBlockState(x, y, z, AIR);
            if (foundTop && primer.getBlockState(x, y - 1, z).getBlock() == filler.getBlock())
            	primer.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
        }
    }
}
