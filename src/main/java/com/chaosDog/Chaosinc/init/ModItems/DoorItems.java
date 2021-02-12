package com.chaosDog.Chaosinc.init.ModItems;

import com.chaosDog.Chaosinc.Utils;
import com.chaosDog.Chaosinc.items.doors.ItemAndesiteDoor;
import com.chaosDog.Chaosinc.items.doors.ItemBedrockDoor;
import com.chaosDog.Chaosinc.items.doors.ItemCactusDoor;
import com.chaosDog.Chaosinc.items.doors.ItemCobblestoneDoor;
import com.chaosDog.Chaosinc.items.doors.ItemDiamondDoor;
import com.chaosDog.Chaosinc.items.doors.ItemDioriteDoor;
import com.chaosDog.Chaosinc.items.doors.ItemEmeraldDoor;
import com.chaosDog.Chaosinc.items.doors.ItemEndDoor;
import com.chaosDog.Chaosinc.items.doors.ItemGlassDoor;
import com.chaosDog.Chaosinc.items.doors.ItemGlowingObsidianDoor;
import com.chaosDog.Chaosinc.items.doors.ItemGoldDoor;
import com.chaosDog.Chaosinc.items.doors.ItemGraniteDoor;
import com.chaosDog.Chaosinc.items.doors.ItemMossStoneDoor;
import com.chaosDog.Chaosinc.items.doors.ItemObsidianDoor;
import com.chaosDog.Chaosinc.items.doors.ItemRedstoneDoor;
import com.chaosDog.Chaosinc.items.doors.ItemRubyDoor;
import com.chaosDog.Chaosinc.items.doors.ItemSilverDoor;
import com.chaosDog.Chaosinc.items.doors.ItemStoneDoor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;

public class DoorItems {
	// doors
	public static ItemDoor AndesiteDoor;
	public static ItemDoor BedrockDoor;
	public static ItemDoor CactusDoor;
	public static ItemDoor CobblestoneDoor;
	public static ItemDoor DiamondDoor;
	public static ItemDoor DioriteDoor;
	public static ItemDoor EmeraldDoor;
	public static ItemDoor EndDoor;
	public static ItemDoor GlassDoor;
	public static ItemDoor GlowingObsidianDoor;
	public static ItemDoor GoldDoor;
	public static ItemDoor GraniteDoor;
	public static ItemDoor MossStoneDoor;
	public static ItemDoor ObsidianDoor;
	public static Item RedstoneDoor;
	public static ItemDoor RubyDoor;
	public static ItemDoor SilverDoor;
	public static ItemDoor StoneDoor;
	
	public static void initAndRegister() {
		Utils.regItem(AndesiteDoor = new ItemAndesiteDoor());
		Utils.regItem(BedrockDoor = new ItemBedrockDoor());
		Utils.regItem(CactusDoor = new ItemCactusDoor());
		Utils.regItem(CobblestoneDoor = new ItemCobblestoneDoor());
		Utils.regItem(DiamondDoor = new ItemDiamondDoor());
		Utils.regItem(DioriteDoor = new ItemDioriteDoor());
		Utils.regItem(EmeraldDoor = new ItemEmeraldDoor());
		Utils.regItem(EndDoor = new ItemEndDoor());
		Utils.regItem(GlassDoor = new ItemGlassDoor());
		Utils.regItem(GlowingObsidianDoor = new ItemGlowingObsidianDoor());
		Utils.regItem(GoldDoor = new ItemGoldDoor());
		Utils.regItem(GraniteDoor = new ItemGraniteDoor());
		Utils.regItem(MossStoneDoor = new ItemMossStoneDoor());
		Utils.regItem(ObsidianDoor = new ItemObsidianDoor());
		Utils.regItem(RedstoneDoor = new ItemRedstoneDoor());
		Utils.regItem(RubyDoor = new ItemRubyDoor());
		Utils.regItem(SilverDoor = new ItemSilverDoor());
		Utils.regItem(StoneDoor = new ItemStoneDoor());
	}
	
	public static void regRenders() {
		Utils.regRender(GlowingObsidianDoor);
		Utils.regRender(EndDoor);
		Utils.regRender(CactusDoor);
		Utils.regRender(SilverDoor);
		Utils.regRender(GlassDoor);
		Utils.regRender(GoldDoor);
		Utils.regRender(DiamondDoor);
		Utils.regRender(EmeraldDoor);
		Utils.regRender(AndesiteDoor);
		Utils.regRender(DioriteDoor);
		Utils.regRender(GraniteDoor);
		Utils.regRender(StoneDoor);
		Utils.regRender(CobblestoneDoor);
		Utils.regRender(MossStoneDoor);
		Utils.regRender(ObsidianDoor);
		Utils.regRender(BedrockDoor);
		Utils.regRender(RubyDoor);
		Utils.regRender(RedstoneDoor);
	}
}
