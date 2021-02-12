package com.chaosDog.Chaosinc.blocks.base;

import com.chaosDog.Chaosinc.ChaosIncMod;
import com.chaosDog.Chaosinc.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class CustomWorkBench extends Block
{
    public CustomWorkBench()
    {
        super(Material.WOOD);
		setHardness(2.5F);
		setResistance(5F);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.WOOD);
    }

    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if (worldIn.isRemote)
    		return true;
    	else
    	{
    		playerIn.openGui(ChaosIncMod.instance, Reference.GUI_CUSTOM_WORKBENCH, worldIn, 0, 0, 0);
    		playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
    		return true;
    	}
    }

    public static class InterfaceCraftingTable implements IInteractionObject {
    	private final World world;
        private final BlockPos position;
        public InterfaceCraftingTable(World worldIn, BlockPos pos)
        {
        	world = worldIn;
            position = pos;
        }

        @Override
		public String getName()
        {
        	return "crafting_table";
        }

        @Override
		public boolean hasCustomName()
        {
        	return false;
        }

        @Override
		public ITextComponent getDisplayName()
        {
        	return new TextComponentTranslation(Blocks.CRAFTING_TABLE.getUnlocalizedName() + ".name", new Object[0]);
        }

        @Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
        {
        	return new ContainerWorkbench(playerInventory, world, position);
        }

        @Override
		public String getGuiID()
        {
        	return "minecraft:crafting_table";
        }
    }
}