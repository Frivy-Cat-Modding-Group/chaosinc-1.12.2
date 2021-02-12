package com.chaosDog.Chaosinc.world.dimension;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DUWorldProvider extends WorldProvider{
	@Override
	public void init() {
		biomeProvider = new BiomeProvider(world.getWorldInfo());
		hasSkyLight = false;
		nether=true;
	}
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionRegistry.DU;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.6D, 0.0D, 0.79D);
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new DUChunkGenerator(world);
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}
	
	@Override
    public boolean shouldMapSpin(String entity, double x, double z, double rotation)
    {
        return false;
    }
	
	@Override
    public float getSunBrightnessFactor(float par1)
    {
        return 0.0f;
    }

	@Override
    public boolean isDaytime()
    {
        return false;
    }
}