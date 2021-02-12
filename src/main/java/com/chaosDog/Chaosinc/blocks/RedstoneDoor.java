package com.chaosDog.Chaosinc.blocks;

import com.chaosDog.Chaosinc.init.ModItems.DoorItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class RedstoneDoor extends Block{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum<BlockDoor.EnumHingePosition> HINGE = PropertyEnum.<BlockDoor.EnumHingePosition>create("hinge", BlockDoor.EnumHingePosition.class);
    public static final PropertyEnum<BlockDoor.EnumDoorHalf> HALF = PropertyEnum.<BlockDoor.EnumDoorHalf>create("half", BlockDoor.EnumDoorHalf.class);
    protected static final AxisAlignedBB SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
    protected static final AxisAlignedBB NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB WEST = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    
    public RedstoneDoor() {
		super(Material.ROCK);
		setUnlocalizedName("RedstoneDoor");
		setRegistryName("redstone_door_block");
		setHardness(5F);
		setResistance(10F);
		setHarvestLevel("pickaxe", 2);
		setSoundType(SoundType.METAL);
	}
	//the item is the door itself
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
      return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : DoorItems.RedstoneDoor;
    }
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
	//this door is a rs power block!
	@Override
	public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos,  EnumFacing side){
	    return 15;
	}
	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(DoorItems.RedstoneDoor);
    }
    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = state.getActualState(source, pos);
        EnumFacing enumfacing = state.getValue(FACING);
        boolean flag = !state.getValue(OPEN).booleanValue();
        boolean flag1 = state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT;
        switch (enumfacing)
        {
            case EAST:
            default:
                return flag ? EAST : (flag1 ? NORTH : SOUTH);
            case SOUTH:
                return flag ? SOUTH : (flag1 ? EAST : WEST);
            case WEST:
                return flag ? WEST : (flag1 ? SOUTH : NORTH);
            case NORTH:
                return flag ? NORTH : (flag1 ? WEST : EAST);
        }
    }
    @Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    @Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return isOpen(combineMetadata(worldIn, pos));
    }
    @Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	public int getCloseSound()
    {
        return 1011;
    }
    private int getOpenSound()
    {
        return 1005;
    }
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
        IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

        if (iblockstate.getBlock() != this)
        	return false;
        else
        {
        	state = iblockstate.cycleProperty(OPEN);
            worldIn.setBlockState(blockpos, state, 10);
            worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
            worldIn.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? getOpenSound() : getCloseSound(), pos, 0);
            return true;
        }
    }
    public void toggleDoor(World worldIn, BlockPos pos, boolean open){
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            BlockPos blockpos = iblockstate.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
            IBlockState iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);

            if (iblockstate1.getBlock() == this && iblockstate1.getValue(OPEN).booleanValue() != open)
            {
                worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, Boolean.valueOf(open)), 10);
                worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
                worldIn.playEvent((EntityPlayer)null, open ? getOpenSound() : getCloseSound(), pos, 0);
            }
        }
    }
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos){
        return pos.getY() >= worldIn.getHeight() - 1 ? false : worldIn.getBlockState(pos.down()).isSideSolid(worldIn,  pos.down(), EnumFacing.UP) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
    }
	public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }
    public static int combineMetadata(IBlockAccess worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        int i = iblockstate.getBlock().getMetaFromState(iblockstate);
        boolean flag = isTop(i);
        IBlockState iblockstate1 = worldIn.getBlockState(pos.down());
        int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
        int k = flag ? j : i;
        IBlockState iblockstate2 = worldIn.getBlockState(pos.up());
        int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
        int i1 = flag ? i : l;
        boolean flag1 = (i1 & 1) != 0;
        boolean flag2 = (i1 & 2) != 0;
        return removeHalfBit(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
    }
    @Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(DoorItems.RedstoneDoor);
    }
    @Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        BlockPos blockpos = pos.down();
        BlockPos blockpos1 = pos.up();
        if (player.capabilities.isCreativeMode && state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && worldIn.getBlockState(blockpos).getBlock() == this)
            worldIn.setBlockToAir(blockpos);
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this)
        {
            if (player.capabilities.isCreativeMode)
                worldIn.setBlockToAir(pos);
            worldIn.setBlockToAir(blockpos1);
        }
    }
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.up());

            if (iblockstate.getBlock() == this)
                state = state.withProperty(HINGE, iblockstate.getValue(HINGE));
        }
        else
        {
            IBlockState iblockstate1 = worldIn.getBlockState(pos.down());

            if (iblockstate1.getBlock() == this)
                state = state.withProperty(FACING, iblockstate1.getValue(FACING)).withProperty(OPEN, iblockstate1.getValue(OPEN));
        }

        return state;
    }
    @Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.getValue(HALF) != BlockDoor.EnumDoorHalf.LOWER ? state : state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }
    @Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation(state.getValue(FACING))).cycleProperty(HINGE);
    }
    /*
    @Override
	public IBlockState getStateFromMeta(int meta)
    {
        return (meta & 8) > 0 ? getDefaultState().withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(HINGE, (meta & 1) > 0 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT): getDefaultState().withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(FACING, EnumFacing.getFront(meta & 3).rotateYCCW()).withProperty(OPEN, Boolean.valueOf((meta & 4) > 0));
    }
     */
    @Override
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
        {
            i = i | 8;
            if (state.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT)
                i |= 1;
        }
        else
        {
            i = i | state.getValue(FACING).rotateY().getHorizontalIndex();
            if (state.getValue(OPEN).booleanValue())
                i |= 4;
        }
        return i;
    }
    protected static int removeHalfBit(int meta)
    {
        return meta & 7;
    }
    public static boolean isOpen(IBlockAccess worldIn, BlockPos pos)
    {
        return isOpen(combineMetadata(worldIn, pos));
    }
    public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos)
    {
        return getFacing(combineMetadata(worldIn, pos));
    }
    public static EnumFacing getFacing(int combinedMeta)
    {
        return EnumFacing.getFront(combinedMeta & 3).rotateYCCW();
    }
    protected static boolean isOpen(int combinedMeta)
    {
        return (combinedMeta & 4) != 0;
    }
    protected static boolean isTop(int meta)
    {
        return (meta & 8) != 0;
    }
    @Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {HALF,FACING,OPEN,HINGE,});
    }
}