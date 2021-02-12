package com.chaosDog.Chaosinc.blocks.base;

import javax.annotation.Nullable;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomTrapDoor extends BlockTrapDoor {
	/**
	 * 
	 * @param material The block's material
	 * @param sound The sound the block makes when placed or removed
	 * @param creakSound What sound should it make when it opens or closes?
	 * 0: Iron
	 * 1: Wood
	 */
	private final int[] openSounds = {1037,1007};
	private final int[] closeSounds = {1036,1013};
	private final int creak;
	public CustomTrapDoor(Material material, SoundType sound, int creakSound) {
		super(material);
		setCreativeTab(CreativeTabs.REDSTONE);
		setSoundType(sound);
		creak=creakSound;
	}
	@Override
	protected void playSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos, boolean isOpen)
    {
        if (isOpen)
        {
            worldIn.playEvent(player, openSounds[creak], pos, 0);
        }
        else
        {
            worldIn.playEvent(player, closeSounds[creak], pos, 0);
        }
    }
}