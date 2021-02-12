package com.chaosDog.Chaosinc.blocks.chests;

import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCompressedCactusChest;

import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CompressedCactusChestTrapped extends BlockChest {
	public CompressedCactusChestTrapped(){
		super(BlockChest.Type.TRAP);
		setUnlocalizedName("TrapppedCompressedCactusChest");
		setRegistryName("compressed_cactus_chest_trapped");
		setHardness(.5F);
		setResistance(1F);
		setCreativeTab(CreativeTabs.REDSTONE);
		setSoundType(SoundType.WOOD);
	}
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCompressedCactusChest();
    }
}