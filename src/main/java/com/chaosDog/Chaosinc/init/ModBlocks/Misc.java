package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.BasicBlock;
import com.chaosDog.Chaosinc.blocks.base.CaveFormation;
import com.chaosDog.Chaosinc.blocks.base.CustomEndPlant;
import com.chaosDog.Chaosinc.blocks.base.CustomGlass;
import com.chaosDog.Chaosinc.blocks.base.CustomPane;
import com.chaosDog.Chaosinc.blocks.base.CustomPlant;
import com.chaosDog.Chaosinc.blocks.base.MossyFurnaceBase;
import com.chaosDog.Chaosinc.blocks.base.VoidFurnaceBase;
import com.chaosDog.Chaosinc.blocks.base.spike;
import com.chaosDog.Chaosinc.blocks.misc.CactusBookshelf;
import com.chaosDog.Chaosinc.blocks.misc.DUPortal;
import com.chaosDog.Chaosinc.blocks.misc.DVPortal;
import com.chaosDog.Chaosinc.blocks.misc.OvergrownEndStone;
import com.chaosDog.Chaosinc.blocks.misc.RegenerationStone;
import com.chaosDog.Chaosinc.blocks.misc.emeraldRail;
import com.chaosDog.Chaosinc.blocks.misc.prismarineCrystalBlock;
import com.chaosDog.Chaosinc.blocks.misc.undergroundAir;
import com.chaosDog.Chaosinc.blocks.redstone.GoldHopper;
import com.chaosDog.Chaosinc.blocks.redstone.SilverHopper;
import com.chaosDog.Chaosinc.blocks.redstone.emeraldRailPowered;
import com.chaosDog.Chaosinc.blocks.redstone.mossyLever;
import com.chaosDog.Chaosinc.blocks.xmas.ChristmasLeaves;
import com.chaosDog.Chaosinc.blocks.xmas.ChristmasSapling;
import com.chaosDog.Chaosinc.blocks.xmas.ChristmasTopper;
import com.chaosDog.Chaosinc.blocks.xmas.Present;
import com.chaosDog.Chaosinc.blocks.xmas.SantaHat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Misc {
	//cave formations
	public static CaveFormation icicle = new CaveFormation(SoundType.GLASS);
	public static CaveFormation mossyStoneFormation = new CaveFormation(SoundType.STONE);
	public static CaveFormation netherrackFormation = new CaveFormation(SoundType.STONE);
	public static CaveFormation sandstoneFormation = new CaveFormation(SoundType.STONE);
	public static CaveFormation stoneFormation = new CaveFormation(SoundType.STONE);
	public static CaveFormation voidFormation = new CaveFormation(SoundType.STONE);

	//christmas stuff
	public static ChristmasSapling ChristmasSapling = new ChristmasSapling();
	public static BlockOldLeaf ChristmasLeaves = new ChristmasLeaves();
	public static Block ChristmasTopper = new ChristmasTopper();
	public static Block Present = new Present();
	public static BlockPumpkin SantaHat = new SantaHat();

	//deep void blocks
	public static Block PolishedVoidStone = new Block(Material.ROCK);
	public static Block RegenerationStone = new RegenerationStone();
	public static Block VoidBricks = new Block(Material.ROCK);
	public static Block VoidStone = new Block(Material.ROCK);

	//end improvements
	public static Block PolishedEndStone = new Block(Material.ROCK);
	public static Block OvergrownEndStone = new OvergrownEndStone();
	public static CustomEndPlant EndGrass = new CustomEndPlant(true);
	public static CustomEndPlant TallEndGrass = new CustomEndPlant(true);

	//flowers
	public static CustomPlant BlueRose = new CustomPlant(false);
	public static CustomPlant Rose = new CustomPlant(false);
	public static CustomEndPlant EnderRose = new CustomEndPlant(false);

	//furnaces
	public static MossyFurnaceBase MossyFurnace = new MossyFurnaceBase(false);
	public static MossyFurnaceBase MossyFurnaceLit = new MossyFurnaceBase(true);
	public static VoidFurnaceBase VoidFurnace = new VoidFurnaceBase(false);
	public static VoidFurnaceBase VoidFurnaceLit = new VoidFurnaceBase(true);

	//glass
	public static CustomGlass bulletproofGlass = new CustomGlass();
	public static CustomPane bulletproofGlassPane = new CustomPane(Material.GLASS, true, SoundType.GLASS,2000F);
	public static CustomGlass soulGlass = new CustomGlass();
	public static CustomPane soulGlassPane = new CustomPane(Material.GLASS, false, SoundType.GLASS, 0.5F);

	//hoppers
	public static BlockHopper GoldHopper = new GoldHopper();
	public static BlockHopper SilverHopper = new SilverHopper();

	//metal bars
	public static CustomPane GoldBars = new CustomPane(Material.IRON, true, SoundType.METAL, 10F);
	public static CustomPane SilverBars = new CustomPane(Material.IRON, true, SoundType.METAL, 10F);

	//others
	public static Block CactusBookshelf = new CactusBookshelf();
	public static BasicBlock CompressedCactus = new BasicBlock(Material.WOOD, SoundType.WOOD, false);
	public static CustomGlass glowingIce = new CustomGlass();
	public static CustomGlass blueIce = new CustomGlass();
	public static BasicBlock glowingObsidian = new BasicBlock(Material.ROCK,SoundType.STONE,true);
	public static BlockLever mossyLever = new mossyLever();
	public static BlockBreakable prismarineCrystalBlock = new prismarineCrystalBlock();
	public static Block Stonecutter = new Block(Material.ROCK);
	public static Block spike = new spike();
	public static BlockAir undergroundAir = new undergroundAir();

	//portal blocks
	public static DUPortal DUPortal = new DUPortal();
	public static DVPortal DVPortal = new DVPortal();

	//rails
	public static BlockRail emeraldRail = new emeraldRail();
	public static BlockRailPowered emeraldRailPowered = new emeraldRailPowered();

	public static void initAndRegister() {
		//cave formations
		Utils.regBlock(icicle.setUnlocalizedName("Icicle").setRegistryName("icicle"));
		Utils.regBlock(mossyStoneFormation.setUnlocalizedName("mossyStoneFormation").setRegistryName("mossy_stone_formation"));
		Utils.regBlock(netherrackFormation.setUnlocalizedName("netherrackFormation").setRegistryName("netherrack_formation"));
		Utils.regBlock(sandstoneFormation.setUnlocalizedName("sandstoneFormation").setRegistryName("sandstone_formation"));
		Utils.regBlock(voidFormation.setUnlocalizedName("voidStoneFormation").setRegistryName("void_stone_formation"));
		Utils.regBlock(stoneFormation.setUnlocalizedName("stoneFormation").setRegistryName("stone_formation"));

		//christmas stuff
		Utils.regBlock(ChristmasLeaves);
		Utils.regBlock(ChristmasSapling);
		Utils.regBlock(ChristmasTopper);
		Utils.regBlock(Present);
		Utils.regBlock(SantaHat);

		//deep void blocks
		Utils.regBlock(PolishedVoidStone.setUnlocalizedName("PolishedVoidStone").setRegistryName("polished_void_stone").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(RegenerationStone);
		Utils.regBlock(VoidBricks.setUnlocalizedName("VoidBricks").setRegistryName("void_bricks").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(VoidStone.setUnlocalizedName("VoidStone").setRegistryName("void_stone").setHardness(5F).setResistance(15F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));

		//end improvements
		Utils.regBlock(OvergrownEndStone);
		Utils.regBlock(PolishedEndStone.setUnlocalizedName("PolishedEndStone").setRegistryName("polished_end_stone").setHardness(5F).setResistance(15F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(EndGrass.setUnlocalizedName("EndGrass").setRegistryName("end_grass"));
		Utils.regBlock(TallEndGrass.setUnlocalizedName("TallEndGrass").setRegistryName("tall_end_grass"));

		//flowers
		Utils.regBlock(BlueRose.setUnlocalizedName("BlueRose").setRegistryName("blue_rose"));
		Utils.regBlock(Rose.setUnlocalizedName("Rose").setRegistryName("rose"));
		Utils.regBlock(EnderRose.setUnlocalizedName("EnderRose").setRegistryName("ender_rose"));

		//furnaces
		Utils.regBlock(MossyFurnace.setUnlocalizedName("MossyFurnace").setRegistryName("mossy_furnace").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.DECORATIONS));
		ForgeRegistries.BLOCKS.register(MossyFurnaceLit.setUnlocalizedName("MossyFurnaceLit").setRegistryName("lit_mossy_furnace").setHardness(5F).setResistance(10F));
		Utils.regBlock(VoidFurnace.setUnlocalizedName("VoidFurnace").setRegistryName("void_furnace").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.DECORATIONS));
		ForgeRegistries.BLOCKS.register(VoidFurnaceLit.setUnlocalizedName("VoidFurnaceLit").setRegistryName("lit_void_furnace").setHardness(5F).setResistance(10F));

		//glass
		soulGlassPane.setDefaultSlipperiness(1F);
		soulGlass.setDefaultSlipperiness(1F);

		Utils.regBlock(soulGlassPane.setUnlocalizedName("soulGlassPane").setRegistryName("soul_glass_pane").setHardness(.5F).setResistance(1F).setLightLevel(1F));
		Utils.regBlock(soulGlass.setUnlocalizedName("soulGlass").setRegistryName("soul_glass").setHardness(.5F).setResistance(1F).setLightLevel(1F));
		Utils.regBlock(bulletproofGlass.setUnlocalizedName("bulletProofGlass").setRegistryName("bulletproof_glass").setHardness(.5F).setResistance(2000F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(bulletproofGlassPane.setUnlocalizedName("bulletproofGlassPane").setRegistryName("bulletproof_glass_pane").setHardness(.5F).setResistance(2000F).setCreativeTab(CreativeTabs.DECORATIONS));

		//hoppers
		Utils.regBlock(SilverHopper);
		Utils.regBlock(GoldHopper);

		//metal bars
		GoldBars.setHarvestLevel("pickaxe", 2);
		SilverBars.setHarvestLevel("pickaxe", 2);

		Utils.regBlock(GoldBars.setUnlocalizedName("GoldBars").setRegistryName("gold_bars").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.DECORATIONS));
		Utils.regBlock(SilverBars.setUnlocalizedName("SilverBars").setRegistryName("silver_bars").setHardness(5F).setResistance(10F).setCreativeTab(CreativeTabs.DECORATIONS));

		//others
		glowingIce.setDefaultSlipperiness(1F);
		blueIce.setDefaultSlipperiness(1F);
		glowingObsidian.setHarvestLevel("pickaxe",3);

		Utils.regBlock(CactusBookshelf);
		Utils.regBlock(CompressedCactus.setUnlocalizedName("CompressedCactus").setRegistryName("compressed_cactus").setHardness(2.5F).setResistance(5F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(glowingIce.setUnlocalizedName("glowingIce").setRegistryName("glowing_ice").setHardness(.2F).setResistance(2F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightLevel(.46666667F));
		Utils.regBlock(blueIce.setUnlocalizedName("blueIce").setRegistryName("blue_ice").setHardness(.2F).setResistance(2F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
		Utils.regBlock(glowingObsidian.setUnlocalizedName("glowingObsidian").setRegistryName("glowing_obsidian").setHardness(51F).setResistance(2000F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setLightLevel(.46666667F));
		Utils.regBlock(mossyLever);
		Utils.regBlock(prismarineCrystalBlock);
		Utils.regBlock(spike);
		Utils.regBlock(Stonecutter.setUnlocalizedName("Stonecutter").setRegistryName("stonecutter").setHardness(5F).setResistance(5.8333333333F).setCreativeTab(CreativeTabs.DECORATIONS));
		ForgeRegistries.BLOCKS.register(undergroundAir);

		//portal blocks
		Utils.regBlock(DUPortal);
		Utils.regBlock(DVPortal);

		//rails
		Utils.regBlock(emeraldRail);
		Utils.regBlock(emeraldRailPowered);
	}

	public static void regRenders() {
		Utils.regRender(EndGrass);
		Utils.regRender(TallEndGrass);
		Utils.regRender(EnderRose);
		Utils.regRender(BlueRose);
		Utils.regRender(bulletproofGlass);
		Utils.regRender(bulletproofGlassPane);
		Utils.regRender(CactusBookshelf);
		Utils.regRender(ChristmasLeaves);
		Utils.regRender(ChristmasTopper);
		Utils.regRender(ChristmasSapling);
		Utils.regRender(CompressedCactus);
		Utils.regRender(DUPortal);
		Utils.regRender(DVPortal);
		Utils.regRender(emeraldRailPowered);
		Utils.regRender(emeraldRail);
		Utils.regRender(glowingIce);
		Utils.regRender(blueIce);
		Utils.regRender(glowingObsidian);
		Utils.regRender(GoldBars);
		Utils.regRender(GoldHopper);
		Utils.regRender(icicle);
		Utils.regRender(MossyFurnace);
		Utils.regRender(mossyLever);
		Utils.regRender(mossyStoneFormation);
		Utils.regRender(netherrackFormation);
		Utils.regRender(PolishedVoidStone);
		Utils.regRender(PolishedEndStone);
		Utils.regRender(OvergrownEndStone);
		Utils.regRender(Present);
		Utils.regRender(prismarineCrystalBlock);
		Utils.regRender(RegenerationStone);
		Utils.regRender(Rose);
		Utils.regRender(sandstoneFormation);
		Utils.regRender(SantaHat);
		Utils.regRender(SilverBars);
		Utils.regRender(SilverHopper);
		Utils.regRender(soulGlass);
		Utils.regRender(soulGlassPane);
		Utils.regRender(Stonecutter);
		Utils.regRender(stoneFormation);
		Utils.regRender(VoidBricks);
		Utils.regRender(voidFormation);
		Utils.regRender(VoidFurnace);
		Utils.regRender(VoidStone);
		Utils.regRender(spike);
	}
}