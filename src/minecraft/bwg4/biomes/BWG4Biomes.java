package bwg4.biomes;

import bwg4.api.DefaultBiomeList;
import bwg4.config.BWG4Config;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.registry.GameRegistry;

public class BWG4Biomes 
{
	public static BiomeGenBase BETArainforest;
	public static BiomeGenBase BETAswampland;
	public static BiomeGenBase BETAseasonalForest;
	public static BiomeGenBase BETAforest;
	public static BiomeGenBase BETAsavanna;
	public static BiomeGenBase BETAshrubland;
	public static BiomeGenBase BETAtaiga;
	public static BiomeGenBase BETAdesert;
	public static BiomeGenBase BETAplains;
	public static BiomeGenBase BETAtundra;
	
	public static BiomeGenBase CLASSICnormal;
	public static BiomeGenBase CLASSICsnow;
	
	public static BiomeGenBase SURVIVALnormal1;
	public static BiomeGenBase SURVIVALnormal2;
	public static BiomeGenBase SURVIVALsnow;
	public static BiomeGenBase SURVIVALnether;
	
	public static BiomeGenBase ADVENTUREdesert;
	
	public static BiomeGenBase BDocean;
	public static BiomeGenBase BDtropicalisland;
	public static BiomeGenBase BDjungleisland;
	public static BiomeGenBase BDmushroomisland;
	public static BiomeGenBase BDbeach; 
	public static BiomeGenBase BDbeachDunes; 
	public static BiomeGenBase BDsnowpines;
	public static BiomeGenBase BDsnowforest;
	public static BiomeGenBase BDsnowtaiga;
	public static BiomeGenBase BDsnowplains;
	public static BiomeGenBase BDsnowhills;
	public static BiomeGenBase BDplains;
	public static BiomeGenBase BDforest;
	public static BiomeGenBase BDforesthills;
	public static BiomeGenBase BDforestlakes;
	public static BiomeGenBase BDpines;
	public static BiomeGenBase BDtaiga;
	public static BiomeGenBase BDgrassland;
	public static BiomeGenBase BDrainforest;
	public static BiomeGenBase BDjungle;
	public static BiomeGenBase BDswampland;
	public static BiomeGenBase BDdesert;
	public static BiomeGenBase BDsavanna;
	public static BiomeGenBase BDsavannaforest;
	public static BiomeGenBase BDshrubland;
	public static BiomeGenBase BDiceriver;
	public static BiomeGenBase BDriver;
	public static BiomeGenBase BDgreenriver;
	public static BiomeGenBase BDsandriver;
	public static BiomeGenBase BDjungle_nocolor;
	public static BiomeGenBase BDswampland_nocolor;
	
	public static BiomeGenBase WASTELANDriver;
	public static BiomeGenBase WASTELANDpines;
	public static BiomeGenBase WASTELANDplains;
	public static BiomeGenBase WASTELANDforest;
	public static BiomeGenBase WASTELANDdesert;
	public static BiomeGenBase WASTELANDmountains;
	
	public static void init()
	{
		//beta,alpha
		BETAdesert = (new BWG4BiomesBeta(BWG4Config.biomeIDs[0], 7)).setColor(16421912).setBiomeName("desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
		BETAforest = (new BWG4BiomesBeta(BWG4Config.biomeIDs[1], 3)).setColor(353825).setBiomeName("forest").setTemperatureRainfall(0.8F, 0.6F);
		BETAplains = (new BWG4BiomesBeta(BWG4Config.biomeIDs[2], 8)).setColor(353825).setBiomeName("plains").setTemperatureRainfall(0.95F, 0.35F);
		BETArainforest = (new BWG4BiomesBeta(BWG4Config.biomeIDs[3], 0)).setColor(353825).setBiomeName("rainforest").setTemperatureRainfall(0.95F, 0.95F);
		BETAsavanna = (new BWG4BiomesBeta(BWG4Config.biomeIDs[4], 4)).setColor(16421912).setBiomeName("savanna").setTemperatureRainfall(0.7F, 0.1F);
		BETAseasonalForest = (new BWG4BiomesBeta(BWG4Config.biomeIDs[5], 2)).setColor(353825).setBiomeName("seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
		BETAshrubland = (new BWG4BiomesBeta(BWG4Config.biomeIDs[6], 5)).setColor(353825).setBiomeName("shrubland").setTemperatureRainfall(0.7F, 0.3F);
		BETAswampland = (new BWG4BiomesBeta(BWG4Config.biomeIDs[7], 1)).setColor(353825).setBiomeName("swampland").setTemperatureRainfall(0.55F, 0.65F);
		BETAtaiga = (new BWG4BiomesBeta(BWG4Config.biomeIDs[8], 6)).setColor(16777215).setBiomeName("taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
		BETAtundra = (new BWG4BiomesBeta(BWG4Config.biomeIDs[9], 9)).setColor(16777215).setBiomeName("tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();
		
		//infdev,indev
		CLASSICnormal = (new BWG4BiomesClassic(BWG4Config.biomeIDs[10])).setColor(353825).setBiomeName("Classic");
		CLASSICsnow = (new BWG4BiomesClassic(BWG4Config.biomeIDs[11])).setColor(353825).setBiomeName("Classic").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
		
		//COMMON
		//island,skyland,skyblock
		SURVIVALnormal1 = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[12], 0)).setColor(353825).setBiomeName("Extreme Survival").setTemperatureRainfall(0.9F, 0.8F);
		SURVIVALnormal2 = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[13], 1)).setColor(353825).setBiomeName("Extreme Survival").setTemperatureRainfall(0.9F, 0.8F);
		SURVIVALsnow = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[14], 2)).setColor(353825).setBiomeName("Extreme Survival").setTemperatureRainfall(0.0F, 0.5F);
		SURVIVALnether = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[15], 3)).setColor(353825).setBiomeName("Hell").setTemperatureRainfall(0.8F, 0.6F);
		
		//adventure
		ADVENTUREdesert = (new BWG4BiomesAdventure(BWG4Config.biomeIDs[16], 0)).setColor(353825).setBiomeName("Deadly Desert").setTemperatureRainfall(0.8F, 0.6F); 
		
		//ISLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[27], 0)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
		//ISLANDtropical = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[28], 1)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
		//ISLANDiceberg = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[29], 2)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.0F, 0.5F);
		//ISLANDparadise = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[30], 3)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
		//ISLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[31], 4)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
		//ISLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[32], 5)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
		//SKYLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[33], 6)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
		//SKYLANDhell = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[34], 7)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
		//SKYLANDice = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[35], 8)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.0F, 0.5F);
		//SKYLANDjungle = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[36], 9)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
		//SKYLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[37], 10)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
		//SKYLANDnormal = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[38], 11)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
		//SURVIVALnether = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[39], 12)).setColor(353825).setBiomeName("Hell").setTemperatureRainfall(0.8F, 0.6F);
		//SKYBLOCKworld = (new BWG4BiomesSurvival(BWG4Config.biomeIDs[40], 13)).setColor(353825).setBiomeName("Survival Skyblock").setTemperatureRainfall(0.9F, 0.8F);
		
		//better default
		BDocean = (new BWG4BiomesDefault(BWG4Config.biomeIDs[41], 1, 1)).setColor(353825).setBiomeName("Ocean").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-1.1F, 0.3F);
		BDtropicalisland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[42], 1, 2)).setColor(353825).setBiomeName("Tropical Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F);
		BDjungleisland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[43], 1, 3)).setColor(353825).setBiomeName("Jungle Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F);
		BDmushroomisland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[44], 1, 4)).setColor(353825).setBiomeName("Mushroom Island").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.3F);
		BDbeach = (new BWG4BiomesDefault(BWG4Config.biomeIDs[45], 1, 5)).setColor(353825).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.0F, 0.1F); 
		BDbeachDunes = (new BWG4BiomesDefault(BWG4Config.biomeIDs[46], 1, 6)).setColor(353825).setBiomeName("Beach Dunes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 0.2F); 
		BDsnowpines = (new BWG4BiomesDefault(BWG4Config.biomeIDs[47], 2, 1)).setColor(353825).setBiomeName("Snow Pines").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow();
		BDsnowforest = (new BWG4BiomesDefault(BWG4Config.biomeIDs[48], 2, 2)).setColor(353825).setBiomeName("Snow Forest").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F).setEnableSnow();
		BDsnowtaiga = (new BWG4BiomesDefault(BWG4Config.biomeIDs[49], 2, 3)).setColor(353825).setBiomeName("Snow Taiga").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow();
		BDsnowplains = (new BWG4BiomesDefault(BWG4Config.biomeIDs[50], 2, 4)).setColor(353825).setBiomeName("Snow Plains").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 0.3F).setEnableSnow();
		BDsnowhills = (new BWG4BiomesDefault(BWG4Config.biomeIDs[51], 2, 5)).setColor(353825).setBiomeName("Snow Hills").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 1.3F).setEnableSnow();
		BDplains = (new BWG4BiomesDefault(BWG4Config.biomeIDs[52], 3, 1)).setColor(353825).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 0.3F);
		BDforest = (new BWG4BiomesDefault(BWG4Config.biomeIDs[53], 3, 2)).setColor(353825).setBiomeName("Forest").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 1.0F);
		BDforesthills = (new BWG4BiomesDefault(BWG4Config.biomeIDs[54], 3, 3)).setColor(353825).setBiomeName("Forest Hills").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 1.5F);
		BDforestlakes = (new BWG4BiomesDefault(BWG4Config.biomeIDs[55], 3, 4)).setColor(353825).setBiomeName("Forest Lakes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.2F, 0.9F);
		BDpines = (new BWG4BiomesDefault(BWG4Config.biomeIDs[56], 3, 5)).setColor(353825).setBiomeName("Pines").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 0.9F);
		BDtaiga = (new BWG4BiomesDefault(BWG4Config.biomeIDs[57], 3, 6)).setColor(353825).setBiomeName("Taiga").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.4F, 0.9F);
		BDgrassland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[58], 3, 7)).setColor(353825).setBiomeName("Grassland").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.5F, 1.2F);
		BDrainforest = (new BWG4BiomesDefault(BWG4Config.biomeIDs[59], 4, 1)).setColor(353825).setBiomeName("Rainforest").setMinMaxHeight(0.3F, 1.2F).setTemperatureRainfall(0.8F, 1.0F);
		BDjungle = (new BWG4BiomesDefault(BWG4Config.biomeIDs[60], 4, 2)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 1.2F).setTemperatureRainfall(1.0F, 1.0F);
		BDswampland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[61], 4, 3)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F);
		BDdesert = (new BWG4BiomesDefault(BWG4Config.biomeIDs[62], 5, 1)).setColor(353825).setBiomeName("Desert").setMinMaxHeight(0.3F, 0.8F).setTemperatureRainfall(1.0F, 0.0F);
		BDsavanna = (new BWG4BiomesDefault(BWG4Config.biomeIDs[63], 5, 2)).setColor(353825).setBiomeName("Savanna").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(1.0F, 0.2F);
		BDsavannaforest = (new BWG4BiomesDefault(BWG4Config.biomeIDs[64], 5, 3)).setColor(353825).setBiomeName("Savanna Forest").setMinMaxHeight(0.3F, 0.6F).setTemperatureRainfall(1.0F, 0.2F);
		BDshrubland = (new BWG4BiomesDefault(BWG4Config.biomeIDs[65], 5, 4)).setColor(353825).setBiomeName("Shrubland").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(0.9F, 0.0F);
		BDiceriver = (new BWG4BiomesDefault(BWG4Config.biomeIDs[66], 6, 1)).setColor(353825).setBiomeName("River_ice").setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(-0.8F, 0.0F).setEnableSnow();
		BDriver = (new BWG4BiomesDefault(BWG4Config.biomeIDs[67], 6, 2)).setColor(353825).setBiomeName("River_normal").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.8F, 0.0F);
		BDgreenriver = (new BWG4BiomesDefault(BWG4Config.biomeIDs[68], 6, 3)).setColor(353825).setBiomeName("River_green").setTemperatureRainfall(0.8F, 1.0F).setMinMaxHeight(-0.8F, 0.0F);
		BDsandriver = (new BWG4BiomesDefault(BWG4Config.biomeIDs[69], 6, 4)).setColor(353825).setBiomeName("River_sand").setTemperatureRainfall(0.9F, 0.1F).setMinMaxHeight(-0.8F, 0.0F);
		BDjungle_nocolor = (new BWG4BiomesDefault(BWG4Config.biomeIDs[70], 4, 4)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(1.0F, 1.0F);
		BDswampland_nocolor = (new BWG4BiomesDefault(BWG4Config.biomeIDs[71], 4, 5)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F);

		WASTELANDriver = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[72], 1)).setColor(353825).setBiomeName("Dry River").setMinMaxHeight(0.0F, 0.0F);
		WASTELANDpines = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[73], 2)).setColor(353825).setBiomeName("Dead Pine Forest").setMinMaxHeight(0.8F, 0.2F);
		WASTELANDplains = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[74], 3)).setColor(353825).setBiomeName("Wasteland").setMinMaxHeight(0.8F, 0.1F);
		WASTELANDforest = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[75], 4)).setColor(353825).setBiomeName("Forest").setMinMaxHeight(0.8F, 0.3F);
		WASTELANDdesert = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[76], 5)).setColor(353825).setBiomeName("Desert").setMinMaxHeight(0.8F, 0.7F);
		WASTELANDmountains = (new BWG4BiomesWasteland(BWG4Config.biomeIDs[77], 6)).setColor(353825).setBiomeName("Cold Mountains").setMinMaxHeight(0.9F, 1.4F);
		
		//village biomes
		BiomeManager.addVillageBiome(BiomeGenBase.plains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.desert, true);
		//BiomeManager.addVillageBiome(BWG4Biomes.BDsnowplains, true);
		//BiomeManager.addVillageBiome(BWG4Biomes.BDplains, true);
		//BiomeManager.addVillageBiome(BWG4Biomes.BDsavanna, true);
		//BiomeManager.addVillageBiome(BWG4Biomes.BDshrubland, true);
		
		//biome dictionary
		BiomeDictionary.registerBiomeType(BETAdesert, Type.DESERT);
		BiomeDictionary.registerBiomeType(BETAforest, Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BETAplains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BETArainforest, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(BETAsavanna, Type.DESERT, Type.FOREST);
		BiomeDictionary.registerBiomeType(BETAseasonalForest, Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BETAshrubland, Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(BETAswampland, Type.FOREST);
		BiomeDictionary.registerBiomeType(BETAtaiga, Type.FOREST, Type.FROZEN);
		BiomeDictionary.registerBiomeType(BETAtundra, Type.FROZEN, Type.PLAINS);
		BiomeDictionary.registerBiomeType(CLASSICnormal, Type.PLAINS);
		BiomeDictionary.registerBiomeType(CLASSICsnow, Type.PLAINS, Type.FROZEN);
		BiomeDictionary.registerBiomeType(SURVIVALnormal1, Type.PLAINS);
		BiomeDictionary.registerBiomeType(SURVIVALnormal2, Type.PLAINS);
		BiomeDictionary.registerBiomeType(SURVIVALsnow, Type.PLAINS, Type.FROZEN);
		BiomeDictionary.registerBiomeType(SURVIVALnether, Type.NETHER);
		BiomeDictionary.registerBiomeType(ADVENTUREdesert, Type.WASTELAND, Type.DESERT);
		
		//add biomes to bwg4 api
		//DefaultBiomeList.addBiome("Ocean", BWG4Biomes.BDocean, 0);
		//DefaultBiomeList.addBiome("Tropical Island", BWG4Biomes.BDtropicalisland, 5);
		//DefaultBiomeList.addBiome("Jungle Island", BWG4Biomes.BDjungleisland, 5);
		//DefaultBiomeList.addBiome("Mushroom Island", BWG4Biomes.BDmushroomisland, 5);
		//DefaultBiomeList.addBiome("River", BWG4Biomes.BDriver, 0);
		//DefaultBiomeList.addBiome("Beach", BWG4Biomes.BDbeach, 0);
		//DefaultBiomeList.addBiome("Beach Dunes", BWG4Biomes.BDbeachDunes, 0);
		//DefaultBiomeList.addBiome("Snow Pines", BWG4Biomes.BDsnowpines, 1);
		//DefaultBiomeList.addBiome("Snow Forest", BWG4Biomes.BDsnowforest, 1);
		//DefaultBiomeList.addBiome("Snow Taiga", BWG4Biomes.BDsnowtaiga, 1);
		//DefaultBiomeList.addBiome("Snow Plains", BWG4Biomes.BDsnowplains, 1);
		//DefaultBiomeList.addBiome("Snow Hills", BWG4Biomes.BDsnowhills, 1);
		DefaultBiomeList.addBiome("Plains", BWG4Biomes.BDplains, 2);
		//DefaultBiomeList.addBiome("Forest", BWG4Biomes.BDforest, 2);
		//DefaultBiomeList.addBiome("Forest Hills", BWG4Biomes.BDforesthills, 2);
		//DefaultBiomeList.addBiome("Forest Lakes", BWG4Biomes.BDforestlakes, 2);
		//DefaultBiomeList.addBiome("Pines", BWG4Biomes.BDpines, 2);
		//DefaultBiomeList.addBiome("Taiga", BWG4Biomes.BDtaiga, 2);
		//DefaultBiomeList.addBiome("Grassland", BWG4Biomes.BDgrassland, 2);
		//DefaultBiomeList.addBiome("RainForest", BWG4Biomes.BDrainforest, 3);
		//DefaultBiomeList.addBiome("Jungle", BWG4Biomes.BDjungle, 3);
		//DefaultBiomeList.addBiome("Swampland", BWG4Biomes.BDswampland, 3);
		//DefaultBiomeList.addBiome("Desert", BWG4Biomes.BDdesert, 4);
		//DefaultBiomeList.addBiome("Savanna", BWG4Biomes.BDsavanna, 4);
		//DefaultBiomeList.addBiome("Shrubland", BWG4Biomes.BDshrubland, 4);
	}
}
