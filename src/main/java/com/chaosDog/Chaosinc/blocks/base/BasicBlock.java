package com.chaosDog.Chaosinc.blocks.base;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
public class BasicBlock extends Block{
	public final boolean isBeacon;
	public BasicBlock(Material material, SoundType sound, boolean beaconBase) {
		super(material);
		setSoundType(sound);
		isBeacon=beaconBase;
	}

	@Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon)
    {
        return isBeacon;
    }
}