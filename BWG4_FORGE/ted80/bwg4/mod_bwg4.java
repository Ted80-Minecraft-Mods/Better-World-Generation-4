package ted80.bwg4;

import java.lang.reflect.Array;

import ted80.api.DefaultBiomeList;
import ted80.bwg4.gen.BWG4Provider;
import ted80.bwg4.gen.BWG4ProviderHell;
import net.minecraft.client.gui.BWG4BiomeInfo;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
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
		BiomeManager.addVillageBiome(BiomeGenBase.BDsnowplains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDplains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDsavanna, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDshrubland, true);
		
		//biome dictionary
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDswampland, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDrainforest, Type.JUNGLE, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDgrassland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDtaiga, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDpines, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDforestlakes, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDforesthills, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsnowhills, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsnowplains, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsnowtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsnowforest, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDsnowpines, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDbeachDunes, Type.BEACH);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDbeach, Type.BEACH);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDmushroomisland, Type.MUSHROOM);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDjungleisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDtropicalisland, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BDocean, Type.WATER);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.BETAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHArainforest, Type.JUNGLE, Type.SWAMP);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAswampland, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAseasonalForest, Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAforest, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAsavanna, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAshrubland, Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAtaiga, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ALPHAtundra, Type.WATER);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INFDEVdefault, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INFDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INDEVnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INDEVhell, Type.NETHER);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INDEVparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INDEVwoods, Type.FOREST);
		BiomeDictionary.registerBiomeType(BiomeGenBase.INDEVsnow, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ISLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.ISLANDparadise, Type.FOREST, Type.JUNGLE, Type.BEACH);
		BiomeDictionary.registerBiomeType(BiomeGenBase.SKYLANDnormal, Type.FOREST, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(BiomeGenBase.SKYLANDice, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BiomeGenBase.SKYLANDjungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BiomeGenBase.SURVIVALnether, Type.NETHER);
		
        //bwg4 api
		DefaultBiomeList.addBiome("Shrubland", BiomeGenBase.BDshrubland, 4);
		DefaultBiomeList.addBiome("Savanna", BiomeGenBase.BDsavanna, 4);
		DefaultBiomeList.addBiome("Desert", BiomeGenBase.BDdesert, 4);
		DefaultBiomeList.addBiome("Swampland", BiomeGenBase.BDswampland, 3);
		DefaultBiomeList.addBiome("Jungle", BiomeGenBase.BDjungle, 3);
		DefaultBiomeList.addBiome("RainForest", BiomeGenBase.BDrainforest, 3);
		DefaultBiomeList.addBiome("Grassland", BiomeGenBase.BDgrassland, 2);
		DefaultBiomeList.addBiome("Taiga", BiomeGenBase.BDtaiga, 2);
		DefaultBiomeList.addBiome("Pines", BiomeGenBase.BDpines, 2);
		DefaultBiomeList.addBiome("Forest Lakes", BiomeGenBase.BDforestlakes, 2);
		DefaultBiomeList.addBiome("Forest Hills", BiomeGenBase.BDforesthills, 2);
		DefaultBiomeList.addBiome("Forest", BiomeGenBase.BDforest, 2);
		DefaultBiomeList.addBiome("Plains", BiomeGenBase.BDplains, 2);
		DefaultBiomeList.addBiome("Snow Hills", BiomeGenBase.BDsnowhills, 1);
		DefaultBiomeList.addBiome("Snow Plains", BiomeGenBase.BDsnowplains, 1);
		DefaultBiomeList.addBiome("Snow Taiga", BiomeGenBase.BDsnowtaiga, 1);
		DefaultBiomeList.addBiome("Snow Forest", BiomeGenBase.BDsnowforest, 1);
		DefaultBiomeList.addBiome("Snow Pines", BiomeGenBase.BDsnowpines, 1);
		DefaultBiomeList.addBiome("Beach Dunes", BiomeGenBase.BDbeachDunes, 0);
		DefaultBiomeList.addBiome("Beach", BiomeGenBase.BDbeach, 0);
		DefaultBiomeList.addBiome("Mushroom Island", BiomeGenBase.BDmushroomisland, 5);
		DefaultBiomeList.addBiome("Jungle Island", BiomeGenBase.BDjungleisland, 5);
		DefaultBiomeList.addBiome("Tropical Island", BiomeGenBase.BDtropicalisland, 5);
		DefaultBiomeList.addBiome("Ocean", BiomeGenBase.BDocean, 0);
	}
}