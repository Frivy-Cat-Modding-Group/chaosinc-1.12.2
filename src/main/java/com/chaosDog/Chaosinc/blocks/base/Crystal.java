package com.chaosDog.Chaosinc.blocks.base;

import com.chaosDog.Chaosinc.blocks.fluids.LiquidRedstone;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class Crystal extends BlockBush {
    public final Item dropItem;
    public Crystal(Item drop) {
        dropItem=drop;
        setHardness(.5F);
        setResistance(1F);
        setLightLevel(1F);
        setSoundType(SoundType.GLASS);
        setTickRandomly(false);
    }

    @Override
    public boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() != Blocks.AIR&&state.getBlock() != Blocks.WATER&&state.getBlock() != Blocks.FLOWING_WATER&&state.getBlock() != Blocks.LAVA&&state.getBlock() != Blocks.FLOWING_LAVA&&state.getBlock() != LiquidRedstone.BlockLiquidRedstone.instance;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    //get item to drop
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return dropItem;
    }

    //get amount of items to drop
    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(4) + 1;
    }

    //is the pick enchanted with fortune?
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    //spawn the item in the world
    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    //spawn xp orbs
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;
            i = MathHelper.getInt(rand, 0, 5);
            return i;
        }
        return 0;
    }
    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    //spawn the actual item
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(dropItem);
    }
}