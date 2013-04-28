package ted80.bwg4;

import java.lang.reflect.Array;

import ted80.api.DefaultBiomeList;
import ted80.bwg4.biomes.BWG4BiomeGenBase;
import ted80.bwg4.gen.BWG4Provider;
import ted80.bwg4.gen.BWG4ProviderHell;
import net.minecraft.client.gui.BWG4BiomeInfo;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.0.3")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class mod_bwg4
{	
	@Instance("BWG4")
	public static mod_bwg4 instance;
	
	public mod_bwg4()
	{
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, BWG4Provider.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, BWG4ProviderHell.class, true);
		
		//village biomes
		BiomeManager.addVillageBiome(BiomeGenBase.plains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.desert, true);
		BiomeManager.addVillageBiome(BWG4BiomeGenBase.BDsnowplains, true);
		BiomeManager.addVillageBiome(BWG4BiomeGenBase.BDplains, true);
		BiomeManager.addVillageBiome(BWG4BiomeGenBase.BDsavanna, true);
		BiomeManager.addVillageBiome(BWG4BiomeGenBase.BDshrubland, true);
		
		//biome dictionary
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDswampland, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDrainforest, Type.JUNGLE, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDgrassland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDtaiga, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDpines, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDforestlakes, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDforesthills, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsnowhills, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsnowplains, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsnowtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsnowforest, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDsnowpines, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDbeachDunes, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDbeach, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDmushroomisland, Type.MUSHROOM);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDjungleisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDtropicalisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BDocean, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.BETAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ALPHAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INFDEVdefault, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INFDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INDEVnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INDEVhell, Type.NETHER);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INDEVparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INDEVwoods, Type.FOREST);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.INDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ISLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.ISLANDparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.SKYLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.SKYLANDice, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.SKYLANDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BWG4BiomeGenBase.SURVIVALnether, Type.NETHER);
		
        //bwg4 api
		DefaultBiomeList.addBiome("Shrubland", BWG4BiomeGenBase.BDshrubland, 4);
		DefaultBiomeList.addBiome("Savanna", BWG4BiomeGenBase.BDsavanna, 4);
		DefaultBiomeList.addBiome("Desert", BWG4BiomeGenBase.BDdesert, 4);
		DefaultBiomeList.addBiome("Swampland", BWG4BiomeGenBase.BDswampland, 3);
		DefaultBiomeList.addBiome("Jungle", BWG4BiomeGenBase.BDjungle, 3);
		DefaultBiomeList.addBiome("RainForest", BWG4BiomeGenBase.BDrainforest, 3);
		DefaultBiomeList.addBiome("Grassland", BWG4BiomeGenBase.BDgrassland, 2);
		DefaultBiomeList.addBiome("Taiga", BWG4BiomeGenBase.BDtaiga, 2);
		DefaultBiomeList.addBiome("Pines", BWG4BiomeGenBase.BDpines, 2);
		DefaultBiomeList.addBiome("Forest Lakes", BWG4BiomeGenBase.BDforestlakes, 2);
		DefaultBiomeList.addBiome("Forest Hills", BWG4BiomeGenBase.BDforesthills, 2);
		DefaultBiomeList.addBiome("Forest", BWG4BiomeGenBase.BDforest, 2);
		DefaultBiomeList.addBiome("Plains", BWG4BiomeGenBase.BDplains, 2);
		DefaultBiomeList.addBiome("Snow Hills", BWG4BiomeGenBase.BDsnowhills, 1);
		DefaultBiomeList.addBiome("Snow Plains", BWG4BiomeGenBase.BDsnowplains, 1);
		DefaultBiomeList.addBiome("Snow Taiga", BWG4BiomeGenBase.BDsnowtaiga, 1);
		DefaultBiomeList.addBiome("Snow Forest", BWG4BiomeGenBase.BDsnowforest, 1);
		DefaultBiomeList.addBiome("Snow Pines", BWG4BiomeGenBase.BDsnowpines, 1);
		DefaultBiomeList.addBiome("Beach Dunes", BWG4BiomeGenBase.BDbeachDunes, 0);
		DefaultBiomeList.addBiome("Beach", BWG4BiomeGenBase.BDbeach, 0);
		DefaultBiomeList.addBiome("Mushroom Island", BWG4BiomeGenBase.BDmushroomisland, 5);
		DefaultBiomeList.addBiome("Jungle Island", BWG4BiomeGenBase.BDjungleisland, 5);
		DefaultBiomeList.addBiome("Tropical Island", BWG4BiomeGenBase.BDtropicalisland, 5);
		DefaultBiomeList.addBiome("Ocean", BWG4BiomeGenBase.BDocean, 0);
	}
}