package com.chaosDog.Chaosinc;

import com.chaosDog.Chaosinc.blocks.base.CustomSlab;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Utils {
	//check if an int array has a specific value
	public static boolean containsVal(int[] array, int checkValue){
		for (int element : array) {
			if (element == checkValue)
				return true;
		}
		return false;
	}

	//Registers a block with associated item
	public static void regBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
	}

	//Registers a slab with its double and item
	public static void regSlab(CustomSlab slab,CustomSlab doubleSlab) {
		ForgeRegistries.BLOCKS.register(doubleSlab);
		ForgeRegistries.BLOCKS.register(slab);
		doubleSlab.setSingleSlab(slab);
		ItemBlock item = new ItemSlab(slab, slab, doubleSlab);
		ForgeRegistries.ITEMS.register(item.setRegistryName(slab.getRegistryName()));
	}

	//Registers an item
	public static void regItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}

	//Registers a block model
	public static void regRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(),"inventory"));
	}

	//Registers an item model
	public static void regRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	//Gets the maximum value of an integer array
	public static int getMaxValue(int[] numbers){
		int maxValue = numbers[0];
		for(int i=1;i < numbers.length;i++){
			if(numbers[i] > maxValue && numbers[i]!=0){
				maxValue = numbers[i];
			}
		}
		return maxValue;
	}

	//Gets the minimum value of an integer array
	public static int getMinValue(int[] numbers){
		int minValue = numbers[0];
		for(int i=1;i<numbers.length;i++){
			if(numbers[i] < minValue && numbers[i]!=0){
				minValue = numbers[i];
			}
		}
		return minValue;
	}
}