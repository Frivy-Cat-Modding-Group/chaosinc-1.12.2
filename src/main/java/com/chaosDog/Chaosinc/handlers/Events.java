package com.chaosDog.Chaosinc.handlers;

import com.chaosDog.Chaosinc.init.ModBlocks.Misc;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.ArrayList;
import java.util.Random;

public class Events {

	@SubscribeEvent
	public static void deleteRecipes(RegistryEvent.Register<IRecipe> event) {
		ResourceLocation woodenPressurePlate = new ResourceLocation("minecraft:wooden_pressure_plate");
		ResourceLocation oakTable = new ResourceLocation("minecraft:crafting_table");
		ResourceLocation woodenButton = new ResourceLocation("minecraft:wooden_button");
		ResourceLocation furnace = new ResourceLocation("minecraft:furnace");
		@SuppressWarnings("rawtypes")
		IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
		modRegistry.remove(woodenPressurePlate);
		modRegistry.remove(woodenButton);
		modRegistry.remove(oakTable);
		modRegistry.remove(furnace);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void growEndPlants(BonemealEvent event) {
		World world = event.getWorld();
		Random rand = world.rand;
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		Block[] plants = {Misc.EnderRose, Misc.EndGrass, Misc.TallEndGrass};
		if (state == Misc.EndGrass.getDefaultState()) {
			world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Misc.TallEndGrass.getDefaultState());
			return;
		}

		if (state != Misc.OvergrownEndStone.getDefaultState())
			return;

		if (world.isRemote)
			return;

		int x = pos.getX() - 7;
		int y = pos.getY() - 7;
		int z = pos.getZ() - 7;
		world.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), plants[rand.nextInt(3)].getDefaultState());
		if (y < 0)
			y = 0;
		for (int bx = x; bx < x + 14; bx++) {
			for (int bz = z; bz < z + 14; bz++) {
				for (int by = y; by < y + 14; by++) {
					if (world.getBlockState(new BlockPos(bx, by, bz)) == Misc.OvergrownEndStone.getDefaultState() &&
							world.getBlockState(new BlockPos(bx, by + 1, bz)) == Blocks.AIR.getDefaultState() &&
							rand.nextInt(15) == 0)
						world.setBlockState(new BlockPos(bx, by + 1, bz), plants[rand.nextInt(3)].getDefaultState());
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void growRoses(BonemealEvent event) {
		World world = event.getWorld();
		Random rand = world.rand;
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);

		if (state != Blocks.GRASS.getDefaultState())
			return;
		if (world.isRemote)
			return;
		int x = pos.getX() - 7;
		int y = pos.getY() - 7;
		int z = pos.getZ() - 7;

		if (y < 0)
			y = 0;
		Block[] roses = {Misc.BlueRose, Misc.Rose};
		for (int bx = x; bx < x + 14; bx++) {
			for (int bz = z; bz < z + 14; bz++) {
				for (int by = y; by < y + 14; by++) {
					if (world.getBlockState(new BlockPos(bx, by, bz)) == Blocks.GRASS.getDefaultState() &&
							world.getBlockState(new BlockPos(bx, by + 1, bz)) == Blocks.AIR.getDefaultState() &&
							rand.nextInt(16) == 0)
						world.setBlockState(new BlockPos(bx, by + 1, bz), roses[rand.nextInt(2)].getDefaultState());
				}
			}
		}
	}

	//unlocks all recipes if Quark is not present
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			ArrayList<IRecipe> recipes = Lists.newArrayList(CraftingManager.REGISTRY);
			((EntityPlayerMP) event.player).unlockRecipes(recipes);
		}
	}
}
