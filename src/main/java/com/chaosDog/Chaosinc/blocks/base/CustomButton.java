package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class CustomButton extends BlockDirectional
{
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    protected static final AxisAlignedBB DOWN_OFF = new AxisAlignedBB(0.3125D, 0.875D, 0.375D, 0.6875D, 1.0D, 0.625D);
    protected static final AxisAlignedBB UP_OFF = new AxisAlignedBB(0.3125D, 0.0D, 0.375D, 0.6875D, 0.125D, 0.625D);
    protected static final AxisAlignedBB NORTH_OFF = new AxisAlignedBB(0.3125D, 0.375D, 0.875D, 0.6875D, 0.625D, 1.0D);
    protected static final AxisAlignedBB SOUTH_OFF = new AxisAlignedBB(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.125D);
    protected static final AxisAlignedBB WEST_OFF = new AxisAlignedBB(0.875D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
    protected static final AxisAlignedBB EAST_OFF = new AxisAlignedBB(0.0D, 0.375D, 0.3125D, 0.125D, 0.625D, 0.6875D);
    protected static final AxisAlignedBB DOWN_ON = new AxisAlignedBB(0.3125D, 0.9375D, 0.375D, 0.6875D, 1.0D, 0.625D);
    protected static final AxisAlignedBB UP_ON = new AxisAlignedBB(0.3125D, 0.0D, 0.375D, 0.6875D, 0.0625D, 0.625D);
    protected static final AxisAlignedBB NORTH_ON = new AxisAlignedBB(0.3125D, 0.375D, 0.9375D, 0.6875D, 0.625D, 1.0D);
    protected static final AxisAlignedBB SOUTH_ON = new AxisAlignedBB(0.3125D, 0.375D, 0.0D, 0.6875D, 0.625D, 0.0625D);
    protected static final AxisAlignedBB WEST_ON = new AxisAlignedBB(0.9375D, 0.375D, 0.3125D, 1.0D, 0.625D, 0.6875D);
    protected static final AxisAlignedBB EAST_ON = new AxisAlignedBB(0.0D, 0.375D, 0.3125D, 0.0625D, 0.625D, 0.6875D);
    private boolean wooden;
    private final int tickRate;
    private final int sound;
    private final SoundType[] sounds = {
    		SoundType.STONE,
    		SoundType.WOOD,
    		SoundType.METAL,
    		SoundType.PLANT,
    		SoundType.GROUND,
    		SoundType.GLASS
    };

    /**
     * @param soundType
     * The type of sound made by the button
     * 0: Stone
     * 1: Wood
     * 2: Metal
     * 3: Plant
     * 4: Dirt
     * 5: Glass
     * @param ticks
     * The amount of time (in game ticks) before the button becomes unpowered
     */
    public CustomButton(int ticks, int soundType)
    {
        super(Material.CIRCUITS);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.valueOf(false)));
        setTickRandomly(true);
        sound=soundType;
        tickRate=ticks;
        this.setSoundType(sounds[soundType]);
        setCreativeTab(CreativeTabs.REDSTONE);
    }

    @Override
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
	public int tickRate(World worldIn)
    {
        return tickRate;
    }

    @Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return canPlaceBlock(worldIn, pos, side.getOpposite());
    }

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
    	if (sound==5)
    		return BlockRenderLayer.TRANSLUCENT;
		return BlockRenderLayer.SOLID;
	}

    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (canPlaceBlock(worldIn, pos, enumfacing))
                return true;
        }
        return false;
    }

    protected static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction);
        return worldIn.getBlockState(blockpos).isSideSolid(worldIn, blockpos, direction.getOpposite());
    }

    @Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return canPlaceBlock(worldIn, pos, facing.getOpposite()) ? getDefaultState().withProperty(FACING, facing).withProperty(POWERED, Boolean.valueOf(false)) : getDefaultState().withProperty(FACING, EnumFacing.DOWN).withProperty(POWERED, Boolean.valueOf(false));
    }

    @Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (checkForDrop(worldIn, pos, state) && !canPlaceBlock(worldIn, pos, state.getValue(FACING).getOpposite()))
        {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (canPlaceBlockAt(worldIn, pos))
            return true;
        else
        {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return false;
        }
    }

    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = state.getValue(FACING);
        boolean flag = state.getValue(POWERED).booleanValue();
        switch (enumfacing)
        {
            case EAST:
                return flag ? EAST_ON : EAST_OFF;
            case WEST:
                return flag ? WEST_ON : WEST_OFF;
            case SOUTH:
                return flag ? SOUTH_ON : SOUTH_OFF;
            case NORTH:
            default:
                return flag ? NORTH_ON : NORTH_OFF;
            case UP:
                return flag ? UP_ON : UP_OFF;
            case DOWN:
                return flag ? DOWN_ON : DOWN_OFF;
        }
    }

    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (state.getValue(POWERED).booleanValue())
            return true;
        else
        {
            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(true)), 3);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
            playClickSound(playerIn, worldIn, pos);
            notifyNeighbors(worldIn, pos, state.getValue(FACING));
            worldIn.scheduleUpdate(pos, this, tickRate);//(worldIn)
            return true;
        }
    }

    protected void playClickSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos) {

    	if (sound==0||sound==3||sound==4)
    		worldIn.playSound(player, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    	else if (sound==2)
    		worldIn.playSound(player, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    	else
    		worldIn.playSound(player, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    protected void playReleaseSound(World worldIn, BlockPos pos) {
    	if (sound==0||sound==3||sound==4)
    		worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F,0.5F);
    	else if(sound==2)
    		worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F,0.5F);
    	else
    		worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F,0.5F);
    }

    @Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getValue(POWERED).booleanValue())
            notifyNeighbors(worldIn, pos, state.getValue(FACING));
        super.breakBlock(worldIn, pos, state);
    }

    @Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return blockState.getValue(POWERED).booleanValue() ? 15 : 0;
    }

    @Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return !blockState.getValue(POWERED).booleanValue() ? 0 : (blockState.getValue(FACING) == side ? 15 : 0);
    }

    @Override
	public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (state.getValue(POWERED).booleanValue())
            {
                if (wooden)
                    checkPressed(state, worldIn, pos);
                else
                {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(false)));
                    notifyNeighbors(worldIn, pos, state.getValue(FACING));
                    playReleaseSound(worldIn, pos);
                    worldIn.markBlockRangeForRenderUpdate(pos, pos);
                }
            }
        }
    }

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (!worldIn.isRemote)
        {
            if (wooden)
            {
                if (!state.getValue(POWERED).booleanValue())
                    checkPressed(state, worldIn, pos);
            }
        }
    }

    private void checkPressed(IBlockState state, World world, BlockPos position)
    {
        List <? extends Entity > list = world.<Entity>getEntitiesWithinAABB(EntityArrow.class, state.getBoundingBox(world, position).offset(position));
        boolean flag = !list.isEmpty();
        boolean flag1 = state.getValue(POWERED).booleanValue();
        if (flag && !flag1)
        {
        	world.setBlockState(position, state.withProperty(POWERED, Boolean.valueOf(true)));
            notifyNeighbors(world, position, state.getValue(FACING));
            world.markBlockRangeForRenderUpdate(position, position);
            playClickSound((EntityPlayer)null, world, position);
        }
        if (!flag && flag1)
        {
        	world.setBlockState(position, state.withProperty(POWERED, Boolean.valueOf(false)));
            notifyNeighbors(world, position, state.getValue(FACING));
            world.markBlockRangeForRenderUpdate(position, position);
            playReleaseSound(world, position);
        }
        if (flag)
        	world.scheduleUpdate(new BlockPos(position), this, tickRate);//(world)
    }

    private void notifyNeighbors(World worldIn, BlockPos pos, EnumFacing facing)
    {
        worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this, false);
    }

    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;
        switch (meta & 7)
        {
            case 0:
                enumfacing = EnumFacing.DOWN;
                break;
            case 1:
                enumfacing = EnumFacing.EAST;
                break;
            case 2:
                enumfacing = EnumFacing.WEST;
                break;
            case 3:
                enumfacing = EnumFacing.SOUTH;
                break;
            case 4:
                enumfacing = EnumFacing.NORTH;
                break;
            case 5:
            default:
                enumfacing = EnumFacing.UP;
        }
        return getDefaultState().withProperty(FACING, enumfacing).withProperty(POWERED, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
	public int getMetaFromState(IBlockState state)
    {
        int i;
        switch (state.getValue(FACING))
        {
            case EAST:
                i = 1;
                break;
            case WEST:
                i = 2;
                break;
            case SOUTH:
                i = 3;
                break;
            case NORTH:
                i = 4;
                break;
            case UP:
            default:
                i = 5;
                break;
            case DOWN:
                i = 0;
        }
        if (state.getValue(POWERED).booleanValue())
            i |= 8;
        return i;
    }

    @Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, POWERED});
    }
}