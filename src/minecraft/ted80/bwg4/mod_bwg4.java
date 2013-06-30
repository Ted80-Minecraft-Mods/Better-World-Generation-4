package ted80.bwg4;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import ted80.api.DefaultBiomeList;
import ted80.bwg4.biomes.BWG4Biomes;
import ted80.bwg4.biomes.BWG4BiomesAlpha;
import ted80.bwg4.biomes.BWG4BiomesBeta;
import ted80.bwg4.biomes.BWG4BiomesDefault;
import ted80.bwg4.biomes.BWG4BiomesIndev;
import ted80.bwg4.biomes.BWG4BiomesInfdev;
import ted80.bwg4.biomes.BWG4BiomesSurvival;
import ted80.bwg4.config.BWG4Config;
import ted80.bwg4.gen.BWG4Provider;
import ted80.bwg4.gen.BWG4ProviderHell;
import ted80.bwg4.gen.BWG4WorldType;
import ted80.bwg4.gen.BWG4WorldTypeArray;
import ted80.bwg4.gui.BWG4BiomeInfo;
import ted80.bwg4.network.BWG4Connection;
import ted80.bwg4.network.BWG4Packet;
import net.minecraft.block.Block;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.0.8")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"BWG4channel"}, packetHandler = BWG4Packet.class)
public class mod_bwg4
{	
	@Instance("BWG4")
	public static mod_bwg4 instance;
	
	public static final BWG4WorldType BWG4DEFAULT = (new BWG4WorldType(12, "BWG4"));  
	
	public mod_bwg4()
	{
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		BWG4Config.init(event.getSuggestedConfigurationFile());
		
		BWG4Biomes.init();
		
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, BWG4Provider.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, BWG4ProviderHell.class, true);
		
		//village biomes
		BiomeManager.addVillageBiome(BiomeGenBase.plains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.desert, true);
		BiomeManager.addVillageBiome(BWG4Biomes.BDsnowplains, true);
		BiomeManager.addVillageBiome(BWG4Biomes.BDplains, true);
		BiomeManager.addVillageBiome(BWG4Biomes.BDsavanna, true);
		BiomeManager.addVillageBiome(BWG4Biomes.BDshrubland, true);
		
		//biome dictionary
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDswampland, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDrainforest, Type.JUNGLE, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDgrassland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDtaiga, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDpines, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDforestlakes, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDforesthills, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsnowhills, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsnowplains, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsnowtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsnowforest, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDsnowpines, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDbeachDunes, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDbeach, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDmushroomisland, Type.MUSHROOM);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDjungleisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDtropicalisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BDocean, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.BETAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ALPHAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INFDEVdefault, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INFDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INDEVnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INDEVhell, Type.NETHER);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INDEVparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INDEVwoods, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4Biomes.INDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ISLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.ISLANDparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4Biomes.SKYLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4Biomes.SKYLANDice, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4Biomes.SKYLANDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4Biomes.SURVIVALnether, Type.NETHER);
		
        //bwg4 api
		DefaultBiomeList.addBiome("Shrubland", BWG4Biomes.BDshrubland, 4);
		DefaultBiomeList.addBiome("Savanna", BWG4Biomes.BDsavanna, 4);
		DefaultBiomeList.addBiome("Desert", BWG4Biomes.BDdesert, 4);
		DefaultBiomeList.addBiome("Swampland", BWG4Biomes.BDswampland, 3);
		DefaultBiomeList.addBiome("Jungle", BWG4Biomes.BDjungle, 3);
		DefaultBiomeList.addBiome("RainForest", BWG4Biomes.BDrainforest, 3);
		DefaultBiomeList.addBiome("Grassland", BWG4Biomes.BDgrassland, 2);
		DefaultBiomeList.addBiome("Taiga", BWG4Biomes.BDtaiga, 2);
		DefaultBiomeList.addBiome("Pines", BWG4Biomes.BDpines, 2);
		DefaultBiomeList.addBiome("Forest Lakes", BWG4Biomes.BDforestlakes, 2);
		DefaultBiomeList.addBiome("Forest Hills", BWG4Biomes.BDforesthills, 2);
		DefaultBiomeList.addBiome("Forest", BWG4Biomes.BDforest, 2);
		DefaultBiomeList.addBiome("Plains", BWG4Biomes.BDplains, 2);
		DefaultBiomeList.addBiome("Snow Hills", BWG4Biomes.BDsnowhills, 1);
		DefaultBiomeList.addBiome("Snow Plains", BWG4Biomes.BDsnowplains, 1);
		DefaultBiomeList.addBiome("Snow Taiga", BWG4Biomes.BDsnowtaiga, 1);
		DefaultBiomeList.addBiome("Snow Forest", BWG4Biomes.BDsnowforest, 1);
		DefaultBiomeList.addBiome("Snow Pines", BWG4Biomes.BDsnowpines, 1);
		DefaultBiomeList.addBiome("Beach Dunes", BWG4Biomes.BDbeachDunes, 0);
		DefaultBiomeList.addBiome("Beach", BWG4Biomes.BDbeach, 0);
		DefaultBiomeList.addBiome("Mushroom Island", BWG4Biomes.BDmushroomisland, 5);
		DefaultBiomeList.addBiome("Jungle Island", BWG4Biomes.BDjungleisland, 5);
		DefaultBiomeList.addBiome("Tropical Island", BWG4Biomes.BDtropicalisland, 5);
		DefaultBiomeList.addBiome("Ocean", BWG4Biomes.BDocean, 0);
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		NetworkRegistry.instance().registerConnectionHandler(new BWG4Connection());
		LanguageRegistry.instance().addStringLocalization("generator.BWG4", "BWG4");
	}
}