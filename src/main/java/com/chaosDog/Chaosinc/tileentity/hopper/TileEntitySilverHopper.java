//this was derived from the vanilla code
package com.chaosDog.Chaosinc.tileentity.hopper;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.NonNullList;

public class TileEntitySilverHopper extends TileEntityHopper {
    @SuppressWarnings("unused")
	private int transferCooldown = -1;
    @SuppressWarnings("unused")
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
    
    @Override
    public void setTransferCooldown(int ticks)
    {
        this.transferCooldown = ticks/2;
    }
}