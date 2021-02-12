package com.chaosDog.Chaosinc.blocks.redstone;

import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.SoundType;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class emeraldRailPowered extends BlockRailPowered {

	public emeraldRailPowered() {
		setUnlocalizedName("emeraldRailPowered");
		setRegistryName("powered_emerald_rail");
		setSoundType(SoundType.METAL);
	}
	@Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
        return super.getRailMaxSpeed(world, cart, pos) * 3;
    }
    @Override
    public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos) {
        if (!world.getBlockState(pos).getValue(BlockRailPowered.POWERED))
            return;

        double d15 = Math.sqrt(cart.motionX * cart.motionX + cart.motionZ * cart.motionZ);

        if (d15 > 0.01D) {
            cart.motionX += cart.motionX / d15 * 0.06D;
            cart.motionZ += cart.motionZ / d15 * 0.06D;
        }
    }
}