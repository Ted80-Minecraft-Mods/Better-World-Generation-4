package bwg4.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import bwg4.api.BiomeList;
import bwg4.api.BiomeManager;
import bwg4.biomes.override.*;
import bwg4.config.ConfigBWG4;

public class BiomeLoader 
{
	public static void load()
	{
		BiomeList.DEFAULTplains = (new DefaultBiomePlains(1)).setColor(9286496).setBiomeName("Plains");
		BiomeList.DEFAULTdesert = (new DefaultBiomeDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(new BiomeGenBase.Height(0.125F, 0.05F));
		BiomeList.DEFAULTjungle = (new DefaultBiomeJungle(21)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(0.95F, 0.9F);
		
		BiomeList.OLDdesert = (new BiomeBeta(ConfigBWG4.biomeIDs[0], 7)).setColor(16421912).setBiomeName("desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
		BiomeList.OLDforest = (new BiomeBeta(ConfigBWG4.biomeIDs[1], 3)).setColor(353825).setBiomeName("forest").setTemperatureRainfall(0.8F, 0.6F);
		BiomeList.OLDplains = (new BiomeBeta(ConfigBWG4.biomeIDs[2], 8)).setColor(353825).setBiomeName("plains").setTemperatureRainfall(0.95F, 0.35F);
		BiomeList.OLDrainforest = (new BiomeBeta(ConfigBWG4.biomeIDs[3], 0)).setColor(353825).setBiomeName("rainforest").setTemperatureRainfall(0.95F, 0.95F);
		BiomeList.OLDsavanna = (new BiomeBeta(ConfigBWG4.biomeIDs[4], 4)).setColor(16421912).setBiomeName("savanna").setTemperatureRainfall(0.7F, 0.1F);
		BiomeList.OLDseasonalForest = (new BiomeBeta(ConfigBWG4.biomeIDs[5], 2)).setColor(353825).setBiomeName("seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
		BiomeList.OLDshrubland = (new BiomeBeta(ConfigBWG4.biomeIDs[6], 5)).setColor(353825).setBiomeName("shrubland").setTemperatureRainfall(0.7F, 0.3F);
		BiomeList.OLDswampland = (new BiomeBeta(ConfigBWG4.biomeIDs[7], 1)).setColor(353825).setBiomeName("swampland").setTemperatureRainfall(0.55F, 0.65F);
		BiomeList.OLDtaiga = (new BiomeBeta(ConfigBWG4.biomeIDs[8], 6)).setColor(16777215).setBiomeName("taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
		BiomeList.OLDtundra = (new BiomeBeta(ConfigBWG4.biomeIDs[9], 9)).setColor(16777215).setBiomeName("tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();
		
		BiomeList.CLASSICnormal = (new BiomeClassic(ConfigBWG4.biomeIDs[10])).setColor(353825).setBiomeName("Classic");
		BiomeList.CLASSICsnow = (new BiomeClassic(ConfigBWG4.biomeIDs[11])).setColor(353825).setBiomeName("Classic").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
		
		BiomeList.COMMONnormal1 = (new BiomeCommon(ConfigBWG4.biomeIDs[12], 0)).setColor(353825).setBiomeName("BWG4_com1").setTemperatureRainfall(0.9F, 0.8F);
		BiomeList.COMMONnormal2 = (new BiomeCommon(ConfigBWG4.biomeIDs[13], 1)).setColor(353825).setBiomeName("BWG4_com2").setTemperatureRainfall(0.9F, 0.8F);
		BiomeList.COMMONsnow = (new BiomeCommon(ConfigBWG4.biomeIDs[14], 2)).setColor(353825).setBiomeName("BWG4_com3").setTemperatureRainfall(0.0F, 0.5F);
		BiomeList.COMMONnether = (new BiomeCommon(ConfigBWG4.biomeIDs[15], 3)).setColor(353825).setBiomeName("BWG4_nether").setTemperatureRainfall(0.8F, 0.6F);
		
		BiomeManager.addBiome("Plains", BiomeList.DEFAULTplains, 0f);
		//BiomeManager.addBiome("Desert", BiomeList.DEFAULTdesert, 0.5f);
		//BiomeManager.addBiome("Jungle", BiomeList.DEFAULTjungle, 1f);
		
		//TODO Biomedictonairy
	}
}
