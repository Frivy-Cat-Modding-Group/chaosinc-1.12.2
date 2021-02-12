package com.chaosDog.Chaosinc.blocks;

import com.chaosDog.Chaosinc.blocks.base.CustomWall;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RedstoneFenceGate extends BlockHorizontal
{
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");
    protected static final AxisAlignedBB COLLIDE_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB COLLIDE_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB COLLIDE_ZAXIS_INWALL = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
    protected static final AxisAlignedBB COLLIDE_XAXIS_INWALL = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB CLOSED_SELECTED_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
    protected static final AxisAlignedBB CLOSED_SELECTED_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);
    public RedstoneFenceGate()
    {
        super(Material.WOOD, MapColor.RED);
        setDefaultState(blockState.getBaseState().withProperty(OPEN, Boolean.valueOf(false)).withProperty(IN_WALL, Boolean.valueOf(false)));
        setCreativeTab(CreativeTabs.REDSTONE);
		setUnlocalizedName("RedstoneFenceGate");
		setRegistryName("redstone_fence_gate");
		setHardness(5F);
		setResistance(10F);
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
    }
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis enumfacing$axis = ((EnumFacing)state.getValue(FACING)).getAxis();

        if (enumfacing$axis == EnumFacing.Axis.Z && (worldIn.getBlockState(pos.west()).getBlock() instanceof BlockWall || worldIn.getBlockState(pos.east()).getBlock() instanceof BlockWall) || enumfacing$axis == EnumFacing.Axis.X && (worldIn.getBlockState(pos.north()).getBlock() instanceof BlockWall || worldIn.getBlockState(pos.south()).getBlock() instanceof BlockWall))
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        else if (enumfacing$axis == EnumFacing.Axis.Z && (worldIn.getBlockState(pos.west()).getBlock() instanceof CustomWall || worldIn.getBlockState(pos.east()).getBlock() instanceof CustomWall) || enumfacing$axis == EnumFacing.Axis.X && (worldIn.getBlockState(pos.north()).getBlock() instanceof CustomWall || worldIn.getBlockState(pos.south()).getBlock() instanceof CustomWall))
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        return state;
    }
	// this fence post is a rs power block!
	@Override
	public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return 15;
	}
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockFenceGate &&
            state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.MIDDLE_POLE)
        {
            Block connector = world.getBlockState(pos.offset(facing)).getBlock();
            return connector instanceof BlockFence || connector instanceof BlockWall || connector instanceof CustomWall;
        }
        return false;
    }
    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = getActualState(state, source, pos);
        return state.getValue(IN_WALL).booleanValue() ? (state.getValue(FACING).getAxis() == EnumFacing.Axis.X ? COLLIDE_XAXIS_INWALL : COLLIDE_ZAXIS_INWALL) : (state.getValue(FACING).getAxis() == EnumFacing.Axis.X ? COLLIDE_XAXIS : COLLIDE_ZAXIS);
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
    }
    @Override
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return blockState.getValue(OPEN).booleanValue() ? NULL_AABB : (blockState.getValue(FACING).getAxis() == EnumFacing.Axis.Z ? CLOSED_SELECTED_ZAXIS : CLOSED_SELECTED_XAXIS);
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
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(OPEN).booleanValue();
    }
    @Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        boolean flag = worldIn.isBlockPowered(pos);
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, Boolean.valueOf(flag)).withProperty(IN_WALL, Boolean.valueOf(false));
    }
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (state.getValue(OPEN).booleanValue())
        {
            state = state.withProperty(OPEN, Boolean.valueOf(false));
            worldIn.setBlockState(pos, state, 10);
        }
        else
        {
            EnumFacing enumfacing = EnumFacing.fromAngle(playerIn.rotationYaw);
            if (state.getValue(FACING) == enumfacing.getOpposite())
                state = state.withProperty(FACING, enumfacing);
            state = state.withProperty(OPEN, Boolean.valueOf(true));
            worldIn.setBlockState(pos, state, 10);
        }
        worldIn.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? 1008 : 1014, pos, 0);
        return true;
    }
    /*
    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta)).withProperty(OPEN, Boolean.valueOf((meta & 4) != 0));
    }
     */
    @Override
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();
        if (state.getValue(OPEN).booleanValue())
            i |= 4;
        return i;
    }
    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING,OPEN,IN_WALL});
    }
    @SuppressWarnings("unused")
	private boolean canFenceGateConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block.canBeConnectedTo(world, pos.offset(facing), facing.getOpposite());
    }
}