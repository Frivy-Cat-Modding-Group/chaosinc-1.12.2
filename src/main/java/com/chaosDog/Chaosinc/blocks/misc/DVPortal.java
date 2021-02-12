package com.chaosDog.Chaosinc.blocks.misc;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.chaosDog.Chaosinc.config.ModConfig;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.world.dimension.DimTeleporter;
import com.chaosDog.Chaosinc.world.dimension.DimensionRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//class for the Deep Underground void block
//same structure as the DUPortal class
public class DVPortal extends Block{
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis> create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[]{EnumFacing.Axis.X, EnumFacing.Axis.Z});
	protected static final AxisAlignedBB X = new AxisAlignedBB(0.0d, 0.0d, 0.375d, 1.0d, 1.0d, 0.625d);
	protected static final AxisAlignedBB Y = new AxisAlignedBB(0.375d, 0.0d, 0.0d, 0.625d, 1.0d, 1.0d);
	protected static final AxisAlignedBB Z = new AxisAlignedBB(0.375d, 0.0d, 0.375d, 0.625d, 1.0d, 0.625d);
	public DVPortal() {
		super(Material.PORTAL);
		setUnlocalizedName("DVPortal");
		setRegistryName("void_portal");
		setDefaultState(blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Z));
		setTickRandomly(true);
		setHardness(0.1F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabs.DECORATIONS);
	}
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();
		if (this == Misc.DVPortal)
		{
			if (blockState != iblockstate)
			{
				return true;
			}
			if (block == this)
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos) {
		return NULL_AABB;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
	@Nullable
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Misc.DVPortal);
	}
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		return FULL_BLOCK_AABB;
	}
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		if(worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getDifficultyId()) {
			BlockPos blockpos;
			for(blockpos = pos; !worldIn.getBlockState(blockpos).isFullCube() && blockpos.getY() > 0; blockpos = blockpos.down()) {
				;
			}
		}
	}
	public static int getMetaForAxis(EnumFacing.Axis axis) {
		return axis == EnumFacing.Axis.X ? 1 : (axis == EnumFacing.Axis.Z ? 2 : 0);
	}
	@Override
	public int getMetaFromState(IBlockState state) {
		return getMetaForAxis(state.getValue(AXIS));
	}
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
	}
	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		DVPortal.Size blockportal$size = new DVPortal.Size(worldIn, pos, EnumFacing.Axis.X);
		if(blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			blockportal$size.placePortalBlocks();
			return true;
		}
		else {
			DVPortal.Size blockportal$size1 = new DVPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			if(blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
				blockportal$size1.placePortalBlocks();
				return true;
			}
			else {
				return false;
			}
		}
	}
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
	{
	}
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		EnumFacing.Axis enumfacing$axis = state.getValue(AXIS);
		if(enumfacing$axis == EnumFacing.Axis.X) {
			DVPortal.Size blockportal$size = new DVPortal.Size(worldIn, pos, EnumFacing.Axis.X);
			if(!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
		else if (enumfacing$axis == EnumFacing.Axis.Z) {
			DVPortal.Size blockportal$size = new DVPortal.Size(worldIn, pos, EnumFacing.Axis.Z);
			if(!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && entityIn instanceof EntityPlayerMP) {
			EntityPlayerMP thePlayer = (EntityPlayerMP)entityIn;
			if(thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			}
			else if (thePlayer.dimension != DimensionRegistry.DeepVoid) {
				thePlayer.timeUntilPortal = 10;
				if (ModConfig.dimensions.DVEnabled)
					thePlayer.changeDimension(DimensionRegistry.DeepVoid,new DimTeleporter(thePlayer.mcServer.getWorld(DimensionRegistry.DeepVoid),false));
				else
					thePlayer.sendMessage(new TextComponentString("You disabled the dimension!"));
			}
			else {
				thePlayer.timeUntilPortal = 10;
				thePlayer.changeDimension(0,new DimTeleporter(thePlayer.mcServer.getWorld(0),false));
			}
		}
	}
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		if (rand.nextInt(100) == 0)
		{
			worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		double d0 = (double)((float)pos.getX() + rand.nextFloat());
		double d1 = (double)((float)pos.getY() + 0.8F);
		double d2 = (double)((float)pos.getZ() + rand.nextFloat());
		double d3 = 0.0D;
		double d4 = 0.0D;
		double d5 = 0.0D;
		worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.getValue(AXIS)) {
					case X:
						return state.withProperty(AXIS, EnumFacing.Axis.Z);
					case Z:
						return state.withProperty(AXIS, EnumFacing.Axis.X);
					default:
						return state;
				}
			default:
				return state;
		}
	}
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {AXIS});
	}
	public static class Size
	{
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;
		public Size(World worldIn, BlockPos position, EnumFacing.Axis p_i45694_3_)
		{
			world = worldIn;
			axis = p_i45694_3_;
			if (p_i45694_3_ == EnumFacing.Axis.X)
			{
				leftDir = EnumFacing.EAST;
				rightDir = EnumFacing.WEST;
			}
			else
			{
				leftDir = EnumFacing.NORTH;
				rightDir = EnumFacing.SOUTH;
			}
			for (BlockPos blockpos = position; position.getY() > blockpos.getY() - 21 && position.getY() > 0 && isEmptyBlock(worldIn.getBlockState(position.down()).getBlock()); position = position.down())
			{
				;
			}
			int i = getDistanceUntilEdge(position, leftDir) - 1;
			if (i >= 0)
			{
				bottomLeft = position.offset(leftDir, i);
				width = getDistanceUntilEdge(bottomLeft, rightDir);
				if (width < 2 || width > 21)
				{
					bottomLeft = null;
					width = 0;
				}
			}
			if (bottomLeft != null)
			{
				height = calculatePortalHeight();
			}
		}
		protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_)
		{
			int i;

			for (i = 0; i < 22; ++i)
			{
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

				if (!isEmptyBlock(world.getBlockState(blockpos).getBlock()) || world.getBlockState(blockpos.down()).getBlock() != Blocks.BOOKSHELF)
				{
					break;
				}
			}
			Block block = world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == Ores.blazeBlock ? i : 0;
		}
		public int getHeight()
		{
			return height;
		}

		public int getWidth()
		{
			return width;
		}
		protected int calculatePortalHeight()
		{
			label24:
			for (height = 0; height < 21; ++height)
			{
				for (int i = 0; i < width; ++i)
				{
					BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
					Block block = world.getBlockState(blockpos).getBlock();
					if (!isEmptyBlock(block))
					{
						break label24;
					}
					if (block == Misc.DVPortal)
					{
						++portalBlockCount;
					}
					if (i == 0)
					{
						block = world.getBlockState(blockpos.offset(leftDir)).getBlock();
						if (block != Ores.voidPearlBlock)
						{
							break label24;
						}
					}
					else if (i == width - 1)
					{
						block = world.getBlockState(blockpos.offset(rightDir)).getBlock();
						if (block != Ores.voidPearlBlock)
						{
							break label24;
						}
					}
				}
			}
			for (int j = 0; j < width; ++j)
			{
				if (world.getBlockState(bottomLeft.offset(rightDir, j).up(height)).getBlock() != Ores.voidPearlBlock)
				{
					height = 0;
					break;
				}
			}
			if (height <= 21 && height >= 3)
			{
				return height;
			}
			else
			{
				bottomLeft = null;
				width = 0;
				height = 0;
				return 0;
			}
		}
		@SuppressWarnings("deprecation")
		protected boolean isEmptyBlock(Block blockIn)
		{
			return blockIn.getMaterial(blockIn.getDefaultState()) == Material.AIR || blockIn == Blocks.FIRE || blockIn == Misc.DVPortal;
		}
		public boolean isValid()
		{
			return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
		}
		public void placePortalBlocks()
		{
			for (int i = 0; i < width; ++i)
			{
				BlockPos blockpos = bottomLeft.offset(rightDir, i);
				for (int j = 0; j < height; ++j)
				{
					world.setBlockState(blockpos.up(j), Misc.DVPortal.getDefaultState().withProperty(BlockPortal.AXIS, axis), 2);
				}
			}
		}
	}




}