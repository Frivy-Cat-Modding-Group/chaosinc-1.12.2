package com.chaosDog.Chaosinc.blocks.fluids;

import com.chaosDog.Chaosinc.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Random;
public class GlowingWater {
	public static void register() {
		FluidRegistry.registerFluid(FluidGlowingWater.instance);
		ForgeRegistries.BLOCKS.register(BlockGlowingWater.instance);
	}
	public static final class FluidGlowingWater extends Fluid {
		public static final String name = "glowing_water";
		public static final FluidGlowingWater instance = new FluidGlowingWater();
		public FluidGlowingWater() {
			super(name, new ResourceLocation(Reference.MOD_ID + ":" + "blocks/" + name + "_still"),new ResourceLocation(Reference.MOD_ID + ":" + "blocks/" + name + "_flow"));
			setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY);
			setFillSound(SoundEvents.ITEM_BUCKET_FILL);
			setLuminosity(15);
			setDensity(1000);
			setTemperature(300);
			setViscosity(1000);
			setLuminosity(15);
		}
	}
	public static final class BlockGlowingWater extends BlockFluidClassic {
		public static final BlockGlowingWater instance = new BlockGlowingWater();
		public static final String name = "glowing_water";
		public BlockGlowingWater() {
			super(FluidGlowingWater.instance, Material.WATER);
			setUnlocalizedName(name);
			setRegistryName(name);
			setResistance(500F);
			setLightLevel(1F);
		}
		@Override
		public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
			double d0 = pos.getX();
	        double d1 = pos.getY();
	        double d2 = pos.getZ();
            double d8 = d0 + rand.nextFloat();
            double d4 = d1 + stateIn.getBoundingBox(worldIn, pos).maxY;
            double d6 = d2 + rand.nextFloat();
			if(rand.nextInt(100) == 0) 
                worldIn.playSound(d8, d4, d6, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            if(rand.nextInt(25)==0)
                worldIn.spawnParticle(EnumParticleTypes.END_ROD, d8, d4, d6, 0.0D, 0.0D, 0.0D, new int[0]);
		}
		@Override
		public EnumBlockRenderType getRenderType(IBlockState state) {
			return EnumBlockRenderType.MODEL;
		}

	    @Override
	    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
	    {
	        if (!isSourceBlock(world, pos))
	        {
	            int adjacentSourceBlocks =
	                    (isSourceBlock(world, pos.north()) ? 1 : 0) +
	                    (isSourceBlock(world, pos.south()) ? 1 : 0) +
	                    (isSourceBlock(world, pos.east()) ? 1 : 0) +
	                    (isSourceBlock(world, pos.west()) ? 1 : 0);
	            if (adjacentSourceBlocks >= 2 && (world.getBlockState(pos.up(densityDir)).getMaterial().isSolid() || isSourceBlock(world, pos.up(densityDir))))
	                world.setBlockState(pos, state.withProperty(LEVEL, 0));
	        }

	        int quantaRemaining = quantaPerBlock - state.getValue(LEVEL);
	        int expQuanta = -101;

	        // check adjacent block levels if non-source
	        if (quantaRemaining < quantaPerBlock)
	        {
	            if (world.getBlockState(pos.add( 0, -densityDir,  0)).getBlock() == this ||
	                world.getBlockState(pos.add(-1, -densityDir,  0)).getBlock() == this ||
	                world.getBlockState(pos.add( 1, -densityDir,  0)).getBlock() == this ||
	                world.getBlockState(pos.add( 0, -densityDir, -1)).getBlock() == this ||
	                world.getBlockState(pos.add( 0, -densityDir,  1)).getBlock() == this)
	            {
	                expQuanta = quantaPerBlock - 1;
	            }
	            else
	            {
	                int maxQuanta = -100;
	                maxQuanta = getLargerQuanta(world, pos.add(-1, 0,  0), maxQuanta);
	                maxQuanta = getLargerQuanta(world, pos.add( 1, 0,  0), maxQuanta);
	                maxQuanta = getLargerQuanta(world, pos.add( 0, 0, -1), maxQuanta);
	                maxQuanta = getLargerQuanta(world, pos.add( 0, 0,  1), maxQuanta);

	                expQuanta = maxQuanta - 1;
	            }

	            // decay calculation
	            if (expQuanta != quantaRemaining)
	            {
	                quantaRemaining = expQuanta;

	                if (expQuanta <= 0)
	                {
	                    world.setBlockToAir(pos);
	                }
	                else
	                {
	                    world.setBlockState(pos, state.withProperty(LEVEL, quantaPerBlock - expQuanta), 2);
	                    world.scheduleUpdate(pos, this, tickRate);
	                    world.notifyNeighborsOfStateChange(pos, this, false);
	                }
	            }
	        }
	        // This is a "source" block, set meta to zero, and send a server only update
	        else if (quantaRemaining >= quantaPerBlock)
	        {
	            world.setBlockState(pos, this.getDefaultState(), 2);
	        }

	        // Flow vertically if possible
	        if (canDisplace(world, pos.up(densityDir)))
	        {
	            flowIntoBlock(world, pos.up(densityDir), 1);
	            return;
	        }

	        // Flow outward if possible
	        int flowMeta = quantaPerBlock - quantaRemaining + 1;
	        if (flowMeta >= quantaPerBlock)
	        {
	            return;
	        }

	        if (isSourceBlock(world, pos) || !isFlowingVertically(world, pos))
	        {
	            if (world.getBlockState(pos.down(densityDir)).getBlock() == this)
	            {
	                flowMeta = 1;
	            }
	            boolean flowTo[] = getOptimalFlowDirections(world, pos);

	            if (flowTo[0]) flowIntoBlock(world, pos.add(-1, 0,  0), flowMeta);
	            if (flowTo[1]) flowIntoBlock(world, pos.add( 1, 0,  0), flowMeta);
	            if (flowTo[2]) flowIntoBlock(world, pos.add( 0, 0, -1), flowMeta);
	            if (flowTo[3]) flowIntoBlock(world, pos.add( 0, 0,  1), flowMeta);
	        }
	    }
	}
}
	