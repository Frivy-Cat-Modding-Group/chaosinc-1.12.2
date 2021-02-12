package com.chaosDog.Chaosinc.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class RedstoneTrapdoor extends Block{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum<BlockTrapDoor.DoorHalf> HALF = PropertyEnum.<BlockTrapDoor.DoorHalf>create("half", BlockTrapDoor.DoorHalf.class);
    protected static final AxisAlignedBB EAST_OPEN_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST_OPEN_AABB = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB SOUTH_OPEN_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
    protected static final AxisAlignedBB NORTH_OPEN_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB BOTTOM_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
    protected static final AxisAlignedBB TOP_AABB = new AxisAlignedBB(0.0D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D);
    public RedstoneTrapdoor() {
		super(Material.ROCK);
		setUnlocalizedName("RedstoneTrapdoor");
		setRegistryName("redstone_trapdoor");
		setHardness(5F);
		setResistance(10F);
		setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.REDSTONE);
		setHarvestLevel("pickaxe", 2);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HALF, BlockTrapDoor.DoorHalf.BOTTOM));
	}
    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        AxisAlignedBB axisalignedbb;
        if (state.getValue(OPEN).booleanValue())
        {
            switch (state.getValue(FACING))
            {
                case NORTH:
                default:
                    axisalignedbb = NORTH_OPEN_AABB;
                    break;
                case SOUTH:
                    axisalignedbb = SOUTH_OPEN_AABB;
                    break;
                case WEST:
                    axisalignedbb = WEST_OPEN_AABB;
                    break;
                case EAST:
                    axisalignedbb = EAST_OPEN_AABB;
            }
        }
        else if (state.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP)
            axisalignedbb = TOP_AABB;
        else
            axisalignedbb = BOTTOM_AABB;
        return axisalignedbb;
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
        return !worldIn.getBlockState(pos).getValue(OPEN).booleanValue();
    }
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	state = state.cycleProperty(OPEN);
        worldIn.setBlockState(pos, state, 2);
        playSound(playerIn, worldIn, pos, state.getValue(OPEN).booleanValue());
        return true;
    }
	protected void playSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos, boolean isOpen)
    {

    }
    @Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = getDefaultState();
        if (facing.getAxis().isHorizontal())
        {
            iblockstate = iblockstate.withProperty(FACING, facing).withProperty(OPEN, Boolean.valueOf(false));
            iblockstate = iblockstate.withProperty(HALF, hitY > 0.5F ? BlockTrapDoor.DoorHalf.TOP : BlockTrapDoor.DoorHalf.BOTTOM);
        }
        else
        {
            iblockstate = iblockstate.withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(OPEN, Boolean.valueOf(false));
            iblockstate = iblockstate.withProperty(HALF, facing == EnumFacing.UP ? BlockTrapDoor.DoorHalf.BOTTOM : BlockTrapDoor.DoorHalf.TOP);
        }
        return iblockstate;
    }
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
    @Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }
	// this door is a rs power block!
	@Override
	public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return 15;
	}
    protected static EnumFacing getFacing(int meta)
    {
        switch (meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }
    protected static int getMetaForFacing(EnumFacing facing)
    {
        switch (facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }
    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, Boolean.valueOf((meta & 4) != 0)).withProperty(HALF, (meta & 8) == 0 ? BlockTrapDoor.DoorHalf.BOTTOM : BlockTrapDoor.DoorHalf.TOP);
    }
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));
        if (state.getValue(OPEN).booleanValue())
            i |= 4;
        if (state.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP)
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
        return new BlockStateContainer(this, new IProperty[] {FACING, OPEN, HALF});
    }
    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity)
    {
        if (state.getValue(OPEN))
        {
            IBlockState down = world.getBlockState(pos.down());
            if (down.getBlock() == Blocks.LADDER)
                return down.getValue(BlockLadder.FACING) == state.getValue(FACING);
        }
        return false;
    }
    
    /*
    public static enum DoorHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom");
        private final String name;
        private DoorHalf(String name)
        {
            this.name = name;
        }
        @Override
		public String toString()
        {
            return name;
        }
        @Override
		public String getName()
        {
            return name;
        }
    }
    */
}