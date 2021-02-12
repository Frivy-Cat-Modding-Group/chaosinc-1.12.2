//based off of the source code from the Portable Craft Bench mod by RenEvo
//https://github.com/renevo/pcb
package com.chaosDog.Chaosinc.handlers;
import com.chaosDog.Chaosinc.GUI.ContainerCustomCraftBench;
import com.chaosDog.Chaosinc.GUI.CustomCraftBenchGui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CustomCraftBenchGuiHandler implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	id=0;
        return new CustomCraftBenchGui(player.inventory, world);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    	id=0;
        return new ContainerCustomCraftBench(player.inventory, world, new BlockPos(x, y, z));
    }
}