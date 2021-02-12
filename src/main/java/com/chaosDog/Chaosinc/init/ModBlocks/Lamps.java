package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.blocks.misc.SaltLamp;
import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.BlackRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.BlueRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.BrownRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.CyanRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.GrayRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.GreenRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.LightBlueRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.LimeRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.MagentaRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.OrangeRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.PinkRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.PurpleRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.RedRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.SilverRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.WhiteRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.YellowRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitBlackRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitBlueRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitBrownRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitCyanRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitGrayRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitGreenRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitLightBlueRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitLimeRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitMagentaRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitOrangeRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitPinkRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitPurpleRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitRedRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitSilverRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitWhiteRSLamp;
import com.chaosDog.Chaosinc.blocks.redstone.lamps.lit.LitYellowRSLamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Lamps {
	public static Block SaltLamp;

	//lit lamps
	public static BlockRedstoneLight LitBlackLamp = new LitBlackRSLamp();
	public static BlockRedstoneLight LitBlueLamp = new LitBlueRSLamp();
	public static BlockRedstoneLight LitBrownLamp = new LitBrownRSLamp();
	public static BlockRedstoneLight LitCyanLamp = new LitCyanRSLamp();
	public static BlockRedstoneLight LitGrayLamp = new LitGrayRSLamp();
	public static BlockRedstoneLight LitGreenLamp  = new LitGreenRSLamp();
	public static BlockRedstoneLight LitLightBlueLamp  = new LitLightBlueRSLamp();
	public static BlockRedstoneLight LitLimeLamp = new LitLimeRSLamp();
	public static BlockRedstoneLight LitMagentaLamp = new LitMagentaRSLamp();
	public static BlockRedstoneLight LitOrangeLamp = new LitOrangeRSLamp();
	public static BlockRedstoneLight LitPinkLamp = new LitPinkRSLamp();
	public static BlockRedstoneLight LitPurpleLamp = new LitPurpleRSLamp();
	public static BlockRedstoneLight LitRedLamp = new LitRedRSLamp();
	public static BlockRedstoneLight LitSilverLamp = new LitSilverRSLamp();
	public static BlockRedstoneLight LitWhiteLamp = new LitWhiteRSLamp();
	public static BlockRedstoneLight LitYellowLamp = new LitYellowRSLamp();

	//unlit lamps
	public static BlockRedstoneLight BlackLamp = new BlackRSLamp();
	public static BlockRedstoneLight BlueLamp = new BlueRSLamp();
	public static BlockRedstoneLight BrownLamp = new BrownRSLamp();
	public static BlockRedstoneLight CyanLamp = new CyanRSLamp();
	public static BlockRedstoneLight GrayLamp = new GrayRSLamp();
	public static BlockRedstoneLight GreenLamp = new GreenRSLamp();
	public static BlockRedstoneLight LightBlueLamp = new LightBlueRSLamp();
	public static BlockRedstoneLight LimeLamp = new LimeRSLamp();
	public static BlockRedstoneLight MagentaLamp = new MagentaRSLamp();
	public static BlockRedstoneLight OrangeLamp = new OrangeRSLamp();
	public static BlockRedstoneLight PinkLamp = new PinkRSLamp();
	public static BlockRedstoneLight PurpleLamp = new PurpleRSLamp();
	public static BlockRedstoneLight RedLamp = new RedRSLamp();
	public static BlockRedstoneLight SilverLamp = new SilverRSLamp();
	public static BlockRedstoneLight WhiteLamp = new WhiteRSLamp();
	public static BlockRedstoneLight YellowLamp = new YellowRSLamp();


	public static void initAndRegister() {
		Utils.regBlock(SaltLamp = new SaltLamp());

		//lit lamps
		ForgeRegistries.BLOCKS.register(LitBlackLamp);
		ForgeRegistries.BLOCKS.register(LitBlueLamp);
		ForgeRegistries.BLOCKS.register(LitBrownLamp);
		ForgeRegistries.BLOCKS.register(LitCyanLamp);
		ForgeRegistries.BLOCKS.register(LitGrayLamp);
		ForgeRegistries.BLOCKS.register(LitGreenLamp);
		ForgeRegistries.BLOCKS.register(LitLightBlueLamp);
		ForgeRegistries.BLOCKS.register(LitLimeLamp);
		ForgeRegistries.BLOCKS.register(LitMagentaLamp);
		ForgeRegistries.BLOCKS.register(LitOrangeLamp);
		ForgeRegistries.BLOCKS.register(LitPinkLamp);
		ForgeRegistries.BLOCKS.register(LitPurpleLamp);
		ForgeRegistries.BLOCKS.register(LitRedLamp);
		ForgeRegistries.BLOCKS.register(LitSilverLamp);
		ForgeRegistries.BLOCKS.register(LitWhiteLamp);
		ForgeRegistries.BLOCKS.register(LitYellowLamp);

		//unlit lamps
		Utils.regBlock(BlackLamp);
		Utils.regBlock(BlueLamp);
		Utils.regBlock(BrownLamp);
		Utils.regBlock(CyanLamp);
		Utils.regBlock(GrayLamp);
		Utils.regBlock(GreenLamp);
		Utils.regBlock(LightBlueLamp);
		Utils.regBlock(LimeLamp);
		Utils.regBlock(MagentaLamp);
		Utils.regBlock(OrangeLamp);
		Utils.regBlock(PinkLamp);
		Utils.regBlock(PurpleLamp);
		Utils.regBlock(RedLamp);
		Utils.regBlock(SilverLamp);
		Utils.regBlock(WhiteLamp);
		Utils.regBlock(YellowLamp);
	}

	public static void regRenders() {
		Utils.regRender(BlackLamp);
		Utils.regRender(BlueLamp);
		Utils.regRender(BrownLamp);
		Utils.regRender(CyanLamp);
		Utils.regRender(GrayLamp);
		Utils.regRender(GreenLamp);
		Utils.regRender(LightBlueLamp);
		Utils.regRender(LimeLamp);
		Utils.regRender(MagentaLamp);
		Utils.regRender(OrangeLamp);
		Utils.regRender(PinkLamp);
		Utils.regRender(PurpleLamp);
		Utils.regRender(RedLamp);
		Utils.regRender(SaltLamp);
		Utils.regRender(SilverLamp);
		Utils.regRender(WhiteLamp);
		Utils.regRender(YellowLamp);
	}
}