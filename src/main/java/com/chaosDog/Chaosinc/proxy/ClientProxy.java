package com.chaosDog.Chaosinc.proxy;

import javax.annotation.Nullable;

import com.chaosDog.Chaosinc.init.ModBlocks.Buttons;
import com.chaosDog.Chaosinc.init.ModBlocks.Chests;
import com.chaosDog.Chaosinc.init.ModBlocks.Crystals;
import com.chaosDog.Chaosinc.init.ModBlocks.Fences;
import com.chaosDog.Chaosinc.init.ModBlocks.Gates;
import com.chaosDog.Chaosinc.init.ModBlocks.Lamps;
import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.chaosDog.Chaosinc.init.ModBlocks.Ores;
import com.chaosDog.Chaosinc.init.ModBlocks.Plates;
import com.chaosDog.Chaosinc.init.ModBlocks.Slabs;
import com.chaosDog.Chaosinc.init.ModBlocks.Stairs;
import com.chaosDog.Chaosinc.init.ModBlocks.Tables;
import com.chaosDog.Chaosinc.init.ModBlocks.Trapdoors;
import com.chaosDog.Chaosinc.init.ModBlocks.Walls;
import com.chaosDog.Chaosinc.init.ModItems.Armor;
import com.chaosDog.Chaosinc.init.ModItems.DoorItems;
import com.chaosDog.Chaosinc.init.ModItems.MiscItems;
import com.chaosDog.Chaosinc.init.ModItems.Tools;
import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCactusChest;
import com.chaosDog.Chaosinc.tileentity.chest.TileEntityCompressedCactusChest;
import com.chaosDog.Chaosinc.tileentity.render.TileEntityCactusChestRender;
import com.chaosDog.Chaosinc.tileentity.render.TileEntityCompressedCactusChestRender;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements CommonProxy{
	@Override
	public void init() {
		//block groups
		Lamps.regRenders();
		Ores.regRenders();
		Misc.regRenders();
		Crystals.regRenders();
		Walls.regRenders();
		Stairs.regRenders();
		Buttons.regRenders();
		Plates.regRenders();
		Tables.regRenders();
		Chests.regRenders();
		Trapdoors.regRenders();
		Fences.regRenders();
		Gates.regRenders();
		Slabs.regRenders();
		
		//item groups
		Tools.regRenders();
		Armor.regRenders();
		DoorItems.regRenders();
		MiscItems.regRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCactusChest.class, new TileEntityCactusChestRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompressedCactusChest.class, new TileEntityCompressedCactusChestRender());
		
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
        {
            @Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, Plates.GrassPlate);		
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
        {
            @Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
            }
        }, Buttons.GrassButton);
	}
}