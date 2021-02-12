package com.chaosDog.Chaosinc.blocks.chests;

import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCactusChest;

import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CactusChest extends BlockChest {
	public CactusChest(){
		super(BlockChest.Type.BASIC);
		setUnlocalizedName("CactusChest");
		setRegistryName("cactus_chest");
		setHardness(.5F);
		setResistance(1F);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.CLOTH);
	}
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCactusChest();
    }
}