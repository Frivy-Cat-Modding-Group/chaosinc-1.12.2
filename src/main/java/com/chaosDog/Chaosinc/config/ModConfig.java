
package com.chaosDog.Chaosinc.config;

import com.chaosDog.Chaosinc.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid= Reference.MOD_ID,name="ChaosDog Inc. Mod",category="chaosinc")
public class ModConfig {
    
    @Config.LangKey("config.worldgen")
    public static WorldGen worldgen=new WorldGen();
    
    @Config.LangKey("config.dimensions")
    public static Dimensions dimensions=new Dimensions();
    
    public static class WorldGen{

        @Config.LangKey("config.structures")
        public Structures structures=new Structures();
    	public class Structures{
        	@Config.Comment("Generate Brick Pyramids")
            @Config.LangKey("config.brickpyramids")
        	public Boolean BrickPyramids=true;
        	
            @Config.LangKey("config.chance")
            public int BrickPyramidsChance=1000;
        	
        	@Config.Comment("Generate Rusty Iron Buoys in oceans")
            @Config.LangKey("config.buoys")
        	public Boolean Buoys=true;
        	
            @Config.LangKey("config.chance")
            public int BuoysChance=600;
            
            @Config.Comment("I wanted this a while ago")
            @Config.LangKey("config.grassblocklarge")
            public Boolean GiantGrassBlock=true;
            
            @Config.LangKey("config.chance")
            public int GiantGrassBlockChance=100;
            
        	@Config.Comment("Generate Starter Homes")
            @Config.LangKey("config.homes")
        	public Boolean Homes=true;
        	
            @Config.LangKey("config.chance")
            public int HomesChance=60;
            
        	@Config.Comment("Generate Research Bases")
            @Config.LangKey("config.bases")
        	public Boolean Bases=true;
        	
            @Config.LangKey("config.chance")
            public int BaseChance=100;

        	@Config.Comment("Generate Landmines")
            @Config.LangKey("config.mines")
        	public Boolean Landmines=true;
        	
            @Config.LangKey("config.chance")
            public int LandminesChance=30;

        	@Config.Comment("Generate Temples in Mesa-type biomes")
            @Config.LangKey("config.mesatemples")
        	public Boolean MesaTemple=true;
        	
            @Config.LangKey("config.chance")
            public int MesaTempleChance=500;

        	@Config.Comment("Generate Abandoned Miner Bases")
            @Config.LangKey("config.minerbase")
        	public Boolean MinerBase=true;
        	
            @Config.LangKey("config.chance")
            public int MinerBaseChance=1000;

        	@Config.Comment("Generate Mini Strongholds")
            @Config.LangKey("config.ministronghold")
        	public Boolean MiniStronghold=true;
        	
            @Config.LangKey("config.chance")
            public int MiniStrongholdChance=1000;

        	@Config.Comment("Generate Shipwrecks in the oceans")
            @Config.LangKey("config.shipwreck")
        	public Boolean Shipwreck=true;
        	
            @Config.LangKey("config.chance")
            public int ShipwreckChance=800;

        	@Config.Comment("Generate Underwater Villages")
            @Config.LangKey("config.oceanvillages")
        	public Boolean OceanVillage=true;
        	
            @Config.LangKey("config.chance")
            public int OceanVillageChance=600;
            
            @Config.Comment("I requested this from the original mod creator")
            @Config.LangKey("config.playertemples")
            public Boolean PlayerTemples=true;
            
            @Config.LangKey("config.chance")
            public int PlayerTemplesChance=100;
            
        	@Config.Comment("Generate Rogue Nether Portals")
            @Config.LangKey("config.portal")
        	public Boolean RoguePortal=true;
        	
            @Config.LangKey("config.chance")
            public int RoguePortalChance=700;

        	@Config.Comment("Generate Elytra Runways")
            @Config.LangKey("config.runways")
        	public Boolean Runway=true;
        	
            @Config.LangKey("config.chance")
            public int RunwayChance=800;

        	@Config.Comment("Generate Wheels in the Sky")
            @Config.LangKey("config.skywheels")
        	public Boolean SkyWheel=true;
        	
            @Config.LangKey("config.chance")
            public int SkyWheelChance=1000;

        	@Config.Comment("Generate Treasure Chests")
            @Config.LangKey("config.treasure")
        	public Boolean TreasureChest=true;
        	
            @Config.LangKey("config.chance")
            public int TreasureChestChance=1000;

        	@Config.Comment("Generate Void Dungeons in the Deep Void")
            @Config.LangKey("config.voiddungeons")
        	public Boolean VoidDungeon=true;
        	
            @Config.LangKey("config.chance")
            public int VoidDungeonChance=60;
        	
        	@Config.Comment("Generate Large Void Dungeons in the Deep Void")
            @Config.LangKey("config.voiddungeonslarge")
        	public Boolean VoidDungeonLarge=true;
        	
            @Config.LangKey("config.chance")
            public int VoidDungeonLargeChance=1000;

        	@Config.Comment("Generate Void Towers in the Deep Void")
            @Config.LangKey("config.voidtowers")
        	public Boolean VoidTower=true;
        	
            @Config.LangKey("config.chance")
            public int VoidTowerChance=1000;

        	@Config.Comment("Generate Underground Villages")
            @Config.LangKey("config.undergroundvillages")
        	public Boolean UndergroundVillage=true;
        	
            @Config.LangKey("config.chance")
            public int UndergroundVillageChance=600;

        	@Config.Comment("Generate a special surprise!")
            @Config.LangKey("config.easteregg")
        	public Boolean EasterEgg=false;
        	
            @Config.LangKey("config.chance")
            public int EasterEggChance=1000;
            
            @Config.LangKey("config.chaos")
        	public Chaos chaos=new Chaos();
        	public class Chaos{
        		
        		@Config.Comment("Generate Jeff the Creeper's tank in the nether")
                @Config.LangKey("config.jefftank")
        		public Boolean JeffTank=true;
            	
                @Config.LangKey("config.chance")
                public int JeffTankChance=30;
    		
        		@Config.Comment("Generate Bill the Enderman & John the Shulker's shrines in the end")
                @Config.LangKey("config.shrines")
        		public Boolean Shrines=true;
            	
                @Config.LangKey("config.chance")
                public int ShrinesChance=400;
        	}
        }

    	@Config.LangKey("config.caves")
        public Caves caves=new Caves();
        public class Caves{
        	@Config.Comment("Beautify Caves in the Overworld and other mods' cave dimensions (like Cavern")
        	@Config.LangKey("config.bettercaves")
        	public Boolean betterCaves=true;

        	@Config.Comment("Chances of crystals generating in caves")
        	@Config.LangKey("config.crystalchance")
        	public int CrystalChance=150;

            @Config.Comment("Don't generate cave pools in these dimensions")
            @Config.LangKey("config.poolblacklist")
        	public int[] poolBlacklist = {-1,1,-51,-60,-53};
        	@Config.LangKey("config.cavetypes")
        	public Types types=new Types();
        	public class Types{
                @Config.Comment("Caves in Jungles and Swamps")
        		public Boolean Wet=true;
                @Config.Comment("Caves in dry biomes (ex: Deserts and Salt Flats)")
        		public Boolean Dry=true;
                @Config.Comment("Water filled caves in oceans")
        		public Boolean Ocean=true;
                @Config.Comment("Caves with ice in cold biomes (ex: Ice Plains/Mountains)")
        		public Boolean Icy=true;
                @Config.Comment("Mushroom Island Caves")
        		public Boolean Mushroom=true;
                @Config.Comment("Clay caves in mesa biomes")
        		public Boolean Mesa=true;
        	}
        	
        }
        @Config.Comment("Generate roses")
        @Config.LangKey("config.rosegen")
    	public Boolean GenerateRoses=true;
        
        @Config.Comment("Generate this mod's ores in the overworld")
        @Config.LangKey("config.owores")
    	public Boolean GenerateOverworldOres=true;
        
        @Config.Comment("Generate this mod's ores in the nether")
        @Config.LangKey("config.nores")
    	public Boolean GenerateNetherOres=true;
        
        @Config.Comment("Generate this mod's ores in the end")
        @Config.LangKey("config.eores")
    	public Boolean GenerateEndOres=true;
        
        @Config.LangKey("config.oceanfloors")
        @Config.Comment("Generate sand, clay, and dirt on the ocean floors instead of boring gravel")
    	public Boolean BeautifyOceanFloors=true;
        
        @Config.Comment("Generate a special surprise in the End!")
        @Config.LangKey("config.endsurprise")
    	public Boolean endSurprise=false;

    }
    
    public static class Dimensions{
    	
        @Config.RequiresMcRestart
        @Config.LangKey("config.dunum")
        @Config.Comment("Dimension ID for the Deep Underground")
    	public int DeepUndergroundDim=-2;
        
        @Config.RequiresMcRestart
        @Config.LangKey("config.dvnum")
        @Config.Comment("Dimension ID for the Deep Void")
    	public int DeepVoidDim=-3;

        @Config.LangKey("config.duenable")
        @Config.Comment("Enable/Disable the Deep Underground Dimension")
        public Boolean DUEnabled=true;

        @Config.LangKey("config.dvenable")
        @Config.Comment("Enable/Disable the Deep Void Dimension")
        public Boolean DVEnabled=true;
    }

    @Config.RequiresMcRestart
    @Config.Comment("Hide Christmas Features")
    @Config.LangKey("config.xmas")
    public static Boolean HideXmasFeatures = false;
    @Mod.EventBusSubscriber
    private static class ConfigHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(Reference.MOD_ID))
                ConfigManager.load(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }
}