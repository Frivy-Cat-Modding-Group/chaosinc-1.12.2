package com.chaosDog.Chaosinc.blocks.base;

import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomPane extends BlockPane {
	@SuppressWarnings("unused")
	private final boolean metal;
	private final Material blockMaterial;
	public CustomPane(Material material, boolean isMetal,SoundType sound, float resistance) {
		super(material, isMetal);
		blockMaterial=material;
		metal=isMetal;
		setSoundType(sound);
		setResistance(resistance);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		if (blockMaterial!=Material.GLASS)
			return BlockRenderLayer.SOLID;
		else
			return BlockRenderLayer.TRANSLUCENT;
	}
}