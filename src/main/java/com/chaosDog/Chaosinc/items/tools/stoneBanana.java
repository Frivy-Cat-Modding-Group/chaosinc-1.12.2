package com.chaosDog.Chaosinc.items.tools;

import com.chaosDog.Chaosinc.Reference;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class stoneBanana extends ItemTool {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE});
	@SuppressWarnings("unused")
    private static final float[] ATTACK_DAMAGES = new float[] {5.0F, 5.0F, 5.0F, 5.0F, 5.0F};
	@SuppressWarnings("unused")
    private static final float[] ATTACK_SPEEDS = new float[] { -1.6F, -1.6F, -1.6F, -1.6F, -1.6F};

	public stoneBanana(ToolMaterial material, String unlocalizedName) {
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : efficiency;
    }
}

/*
public class stoneBanana extends ItemSpade {
	public stoneBanana(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}

public class stoneBanana extends ItemHoe {
	public stoneBanana(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}

public class stoneBanana extends ItemPickaxe {
	public stoneBanana(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}

public class stoneBanana extends ItemSword {
	public stoneBanana(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, unlocalizedName));
	}
}
		*/