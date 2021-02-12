package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.blocks.base.CustomButton;

public class Buttons {	
	//buttons
	//dirt and grass
	public static CustomButton DirtButton = new CustomButton(20,4);
	public static CustomButton GrassButton = new CustomButton(20,3);

	//special buttons
	public static CustomButton DiamondButton = new CustomButton(14,2);
	public static CustomButton EmeraldButton = new CustomButton(10,2);
	public static CustomButton GlassButton = new CustomButton(20,5);
	public static CustomButton GoldButton = new CustomButton(2,2);
	public static CustomButton IronButton = new CustomButton(6,2);
	public static CustomButton RubyButton = new CustomButton(4,2);
	public static CustomButton SilverButton = new CustomButton(2,2);

	//stone variants
	public static CustomButton AndesiteButton = new CustomButton(20,0);
	public static CustomButton CobblestoneButton = new CustomButton(20,0);
	public static CustomButton DioriteButton = new CustomButton(20,0);
	public static CustomButton EndButton = new CustomButton(20,0);
	public static CustomButton GraniteButton = new CustomButton(20,0);
	public static CustomButton MossyButton = new CustomButton(20,0);
	public static CustomButton RedSandstoneButton = new CustomButton(20,0);
	public static CustomButton SandstoneButton = new CustomButton(20,0);
	public static CustomButton VoidButton = new CustomButton(20,0);

	//unbreakable buttons
	public static CustomButton GlowingObsidianButton = new CustomButton(20,2);
	public static CustomButton BedrockButton = new CustomButton(20,2);
	public static CustomButton ObsidianButton = new CustomButton(20,2);

	//wooden buttons
	public static CustomButton AcaciaButton = new CustomButton(30,1);
	public static CustomButton BirchButton = new CustomButton(30,1);
	public static CustomButton DarkOakButton = new CustomButton(30,1);
	public static CustomButton JungleButton = new CustomButton(30,1);
	public static CustomButton SpruceButton = new CustomButton(30,1);
	public static CustomButton CactusButton = new CustomButton(30,1);

	public static void initAndRegister() {
		//dirt and grass
		Utils.regBlock(DirtButton.setUnlocalizedName("DirtButton").setRegistryName("dirt_button"));
		Utils.regBlock(GrassButton.setUnlocalizedName("GrassButton").setRegistryName("grass_button"));

		//special buttons
		Utils.regBlock(DiamondButton.setUnlocalizedName("DiamondButton").setRegistryName("diamond_button").setHardness(0.5F));
		Utils.regBlock(EmeraldButton.setUnlocalizedName("EmeraldButton").setRegistryName("emerald_button").setHardness(0.5F));
		Utils.regBlock(GlassButton.setUnlocalizedName("GlassButton").setRegistryName("glass_button").setHardness(0.5F));
		Utils.regBlock(GoldButton.setUnlocalizedName("GoldButton").setRegistryName("gold_button").setHardness(0.5F));
		Utils.regBlock(IronButton.setUnlocalizedName("IronButton").setRegistryName("iron_button").setHardness(0.5F));
		Utils.regBlock(RubyButton.setUnlocalizedName("RubyButton").setRegistryName("ruby_button").setHardness(0.5F));
		Utils.regBlock(SilverButton.setUnlocalizedName("SilverButton").setRegistryName("silver_button").setHardness(0.5F));

		//stone variant buttons
		Utils.regBlock(AndesiteButton.setUnlocalizedName("AndesiteButton").setRegistryName("andesite_button").setHardness(0.5F));
		Utils.regBlock(CobblestoneButton.setUnlocalizedName("CobblestoneButton").setRegistryName("cobblestone_button").setHardness(0.5F));
		Utils.regBlock(DioriteButton.setUnlocalizedName("DioriteButton").setRegistryName("diorite_button").setHardness(0.5F));
		Utils.regBlock(EndButton.setUnlocalizedName("EndButton").setRegistryName("end_button").setHardness(0.5F));
		Utils.regBlock(GraniteButton.setUnlocalizedName("GraniteButton").setRegistryName("granite_button").setHardness(0.5F));
		Utils.regBlock(MossyButton.setUnlocalizedName("MossyButton").setRegistryName("mossy_cobblestone_button").setHardness(0.5F));
		Utils.regBlock(RedSandstoneButton.setUnlocalizedName("RedSandstoneButton").setRegistryName("red_sandstone_button").setHardness(0.5F));
		Utils.regBlock(SandstoneButton.setUnlocalizedName("SandstoneButton").setRegistryName("sandstone_button").setHardness(0.5F));
		Utils.regBlock(VoidButton.setUnlocalizedName("VoidButton").setRegistryName("void_button").setHardness(0.5F));

		//unbreakable buttons
		Utils.regBlock(BedrockButton.setUnlocalizedName("BedrockButton").setRegistryName("bedrock_button").setHardness(-1F).setResistance(18000000F).setBlockUnbreakable());
		Utils.regBlock(GlowingObsidianButton.setUnlocalizedName("GlowingObsidianButton").setRegistryName("glowing_obsidian_button").setHardness(51F).setResistance(2000F).setLightLevel(.46666667F));
		Utils.regBlock(ObsidianButton.setUnlocalizedName("ObsidianButton").setRegistryName("obsidian_button").setHardness(51F).setResistance(6000F));

		//wooden buttons
		Utils.regBlock(AcaciaButton.setUnlocalizedName("AcaciaButton").setRegistryName("acacia_button").setHardness(0.5F));
		Utils.regBlock(BirchButton.setUnlocalizedName("BirchButton").setRegistryName("birch_button").setHardness(0.5F));
		Utils.regBlock(CactusButton.setUnlocalizedName("CactusButton").setRegistryName("cactus_button").setHardness(0.5F));
		Utils.regBlock(DarkOakButton.setUnlocalizedName("DarkOakButton").setRegistryName("dark_oak_button").setHardness(0.5F));
		Utils.regBlock(JungleButton.setUnlocalizedName("JungleButton").setRegistryName("jungle_button").setHardness(0.5F));
		Utils.regBlock(SpruceButton.setUnlocalizedName("SpruceButton").setRegistryName("spruce_button").setHardness(0.5F));	
	}
	public static void regRenders() {
		Utils.regRender(AcaciaButton);
		Utils.regRender(AndesiteButton);
		Utils.regRender(BedrockButton);
		Utils.regRender(BirchButton);
		Utils.regRender(CactusButton);
		Utils.regRender(CobblestoneButton);
		Utils.regRender(DarkOakButton);
		Utils.regRender(DiamondButton);
		Utils.regRender(DioriteButton);
		Utils.regRender(DirtButton);
		Utils.regRender(EmeraldButton);
		Utils.regRender(EndButton);
		Utils.regRender(GlassButton);
		Utils.regRender(GoldButton);
		Utils.regRender(GlowingObsidianButton);
		Utils.regRender(GraniteButton);
		Utils.regRender(GrassButton);
		Utils.regRender(IronButton);
		Utils.regRender(JungleButton);
		Utils.regRender(MossyButton);
		Utils.regRender(ObsidianButton);
		Utils.regRender(RedSandstoneButton);
		Utils.regRender(RubyButton);
		Utils.regRender(SandstoneButton);
		Utils.regRender(SilverButton);
		Utils.regRender(SpruceButton);
		Utils.regRender(VoidButton);
	}
}