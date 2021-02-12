package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class CustomPressurePlate extends BlockBasePressurePlate
{
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    private final int sensitivity;
    private final int tickRate;
    private final int sound;
    public final Material type;

    private final SoundType[] sounds = {
    		SoundType.STONE,
    		SoundType.WOOD,
    		SoundType.METAL,
    		SoundType.PLANT,
    		SoundType.GROUND,
    		SoundType.GLASS
    };
    /**
     * @param sensitivityIn
     * The sensitivity of the pressure plate
     * 0: All entities
     * 1: Mobs and players only
     * 2: Players only
     * @param ticks
     * The amount of time (in game ticks) before the pressure plate becomes unpowered
     * @param soundType
     * The type of sound made by the pressure plate
     * 0: Stone
     * 1: Wood
     * 2: Metal
     * 3: Plant
     * 4: Dirt
     * 5: Glass
     */
    public CustomPressurePlate(int sensitivityIn, int ticks,int soundType, Material material)
    {
        super(material);
        tickRate=ticks;
    	sensitivity=sensitivityIn;
    	sound=soundType;
    	type=material;
    	setSoundType(sounds[soundType]);
        setDefaultState(blockState.getBaseState().withProperty(POWERED, Boolean.valueOf(false)));
    }

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
    	if (type==Material.GLASS)
    		return BlockRenderLayer.TRANSLUCENT;
		return BlockRenderLayer.SOLID;
	}

    @Override
	public int tickRate(World worldIn)
    {
        return tickRate;
    }

    @Override
	protected int getRedstoneStrength(IBlockState state)
    {
        return state.getValue(POWERED).booleanValue() ? 15 : 0;
    }

    @Override
	protected IBlockState setRedstoneStrength(IBlockState state, int strength)
    {
        return state.withProperty(POWERED, Boolean.valueOf(strength > 0));
    }

	protected void playClickOnSound(World worldIn, BlockPos pos)
    {
    	if (sound==0||sound==3||sound==4)
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    	else if (sound==2)
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
    	else
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
    }

	protected void playClickOffSound(World worldIn, BlockPos pos)
    {
    	if (sound==0||sound==3||sound==4)
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
    	else if(sound==2)
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
    	else
    		worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
    }

	@Override
	protected int computeRedstoneStrength(World worldIn, BlockPos pos)
    {
        AxisAlignedBB axisalignedbb = PRESSURE_AABB.offset(pos);
        List <? extends Entity > list;
        
        switch (sensitivity) {
        case 1:
        	//mobs and players only
        	list = worldIn.<Entity>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
        	break;
        case 2:
        	//player only
        	list = worldIn.<Entity>getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
        	break;
        default:
        case 0:
        	//everything
        	list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);
        	break;
        }

        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (!entity.doesEntityNotTriggerPressurePlate())
                    return 15;
            }
        }
        return 0;
    }
    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
    }
    @Override
	public int getMetaFromState(IBlockState state)
    {
        return state.getValue(POWERED).booleanValue() ? 1 : 0;
    }
    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWERED});
    }
}