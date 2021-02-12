package com.chaosDog.Chaosinc.blocks.misc;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class RegenerationStone extends Block{
	public RegenerationStone() { 
		super(Material.ROCK);
		setUnlocalizedName("RegenerationStone");
		setRegistryName("regeneration_stone");
		setHardness(5F);
		setResistance(10F);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setSoundType(SoundType.STONE);
		setLightLevel(.46666667F);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		EntityLivingBase entityLiving= (EntityLivingBase) entityIn;
		entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(10),100));
    }
}