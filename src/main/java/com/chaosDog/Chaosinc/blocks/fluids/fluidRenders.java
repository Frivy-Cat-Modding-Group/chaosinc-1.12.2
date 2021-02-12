package com.chaosDog.Chaosinc.blocks.fluids;

import com.chaosDog.Chaosinc.Reference;
import com.chaosDog.Chaosinc.blocks.fluids.GlowingWater.BlockGlowingWater;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraftforge.client.model.ModelLoader;

public class fluidRenders {
	public static void register() {

		//liquid redstone
		ModelLoader.setCustomStateMapper(LiquidRedstone.BlockLiquidRedstone.instance, new net.minecraft.client.renderer.block.statemap.StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(Reference.MOD_ID + ":" + LiquidRedstone.BlockLiquidRedstone.name, "fluid");
			}
		});

		//ender acid
		ModelLoader.setCustomStateMapper(EnderAcid.BlockEnderAcid.instance, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(Reference.MOD_ID + ":" + EnderAcid.BlockEnderAcid.name, "fluid");
			}
		});

		//glowing water
		ModelLoader.setCustomStateMapper(BlockGlowingWater.instance, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(Reference.MOD_ID + ":" + BlockGlowingWater.name, "fluid");
			}
		});
		
		
	}
}
