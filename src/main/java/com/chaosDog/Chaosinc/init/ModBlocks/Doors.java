package com.chaosDog.Chaosinc.init.ModBlocks;

import com.chaosDog.Chaosinc.blocks.RedstoneDoor;
import com.chaosDog.Chaosinc.blocks.doors.AndesiteDoor;
import com.chaosDog.Chaosinc.blocks.doors.BedrockDoor;
import com.chaosDog.Chaosinc.blocks.doors.CactusDoor;
import com.chaosDog.Chaosinc.blocks.doors.CobblestoneDoor;
import com.chaosDog.Chaosinc.blocks.doors.DiamondDoor;
import com.chaosDog.Chaosinc.blocks.doors.DioriteDoor;
import com.chaosDog.Chaosinc.blocks.doors.EmeraldDoor;
import com.chaosDog.Chaosinc.blocks.doors.EndDoor;
import com.chaosDog.Chaosinc.blocks.doors.GlassDoor;
import com.chaosDog.Chaosinc.blocks.doors.GlowingObsidianDoor;
import com.chaosDog.Chaosinc.blocks.doors.GoldDoor;
import com.chaosDog.Chaosinc.blocks.doors.GraniteDoor;
import com.chaosDog.Chaosinc.blocks.doors.MossStoneDoor;
import com.chaosDog.Chaosinc.blocks.doors.ObsidianDoor;
import com.chaosDog.Chaosinc.blocks.doors.RubyDoor;
import com.chaosDog.Chaosinc.blocks.doors.SilverDoor;
import com.chaosDog.Chaosinc.blocks.doors.StoneDoor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Doors {
	//doors
	public static BlockDoor CactusDoor = new CactusDoor();
	public static BlockDoor EndDoor = new EndDoor();
	public static BlockDoor SilverDoor = new SilverDoor();
	public static BlockDoor GlassDoor = new GlassDoor();
	public static BlockDoor GoldDoor = new GoldDoor();
	public static BlockDoor DiamondDoor = new DiamondDoor();
	public static BlockDoor EmeraldDoor = new EmeraldDoor();
	public static BlockDoor AndesiteDoor = new AndesiteDoor();
	public static BlockDoor DioriteDoor = new DioriteDoor();
	public static BlockDoor GraniteDoor = new GraniteDoor();
	public static BlockDoor StoneDoor = new StoneDoor();
	public static BlockDoor CobblestoneDoor = new CobblestoneDoor();
	public static BlockDoor MossStoneDoor = new MossStoneDoor();
	public static BlockDoor GlowingObsidianDoor = new GlowingObsidianDoor();
	public static BlockDoor ObsidianDoor = new ObsidianDoor();
	public static BlockDoor BedrockDoor = new BedrockDoor();
	public static BlockDoor RubyDoor = new RubyDoor();
	public static Block RedstoneDoor = new RedstoneDoor();

	public static void initAndRegister() {
		
		// door blocks
		// they don't need their own items
		ForgeRegistries.BLOCKS.register(CactusDoor);
		ForgeRegistries.BLOCKS.register(GlowingObsidianDoor);
		ForgeRegistries.BLOCKS.register(EndDoor);
		ForgeRegistries.BLOCKS.register(SilverDoor);
		ForgeRegistries.BLOCKS.register(GlassDoor);
		ForgeRegistries.BLOCKS.register(GoldDoor);
		ForgeRegistries.BLOCKS.register(DiamondDoor);
		ForgeRegistries.BLOCKS.register(EmeraldDoor);
		ForgeRegistries.BLOCKS.register(AndesiteDoor);
		ForgeRegistries.BLOCKS.register(DioriteDoor);
		ForgeRegistries.BLOCKS.register(GraniteDoor);
		ForgeRegistries.BLOCKS.register(StoneDoor);
		ForgeRegistries.BLOCKS.register(CobblestoneDoor);
		ForgeRegistries.BLOCKS.register(MossStoneDoor);
		ForgeRegistries.BLOCKS.register(ObsidianDoor);
		ForgeRegistries.BLOCKS.register(BedrockDoor);
		ForgeRegistries.BLOCKS.register(RubyDoor);
		ForgeRegistries.BLOCKS.register(RedstoneDoor);
	}
}
