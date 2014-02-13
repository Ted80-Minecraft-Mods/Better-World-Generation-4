package bwg4.biomes;

import bwg4.api.biome.BiomeList;
import bwg4.api.biome.BiomeManager;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeLoader 
{
	public static void load()
	{
		BiomeList.OLDdesert = (new BiomeBeta(60, 7)).setColor(16421912).setBiomeName("desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
		BiomeList.OLDforest = (new BiomeBeta(61, 3)).setColor(353825).setBiomeName("forest").setTemperatureRainfall(0.8F, 0.6F);
		BiomeList.OLDplains = (new BiomeBeta(62, 8)).setColor(353825).setBiomeName("plains").setTemperatureRainfall(0.95F, 0.35F);
		BiomeList.OLDrainforest = (new BiomeBeta(63, 0)).setColor(353825).setBiomeName("rainforest").setTemperatureRainfall(0.95F, 0.95F);
		BiomeList.OLDsavanna = (new BiomeBeta(64, 4)).setColor(16421912).setBiomeName("savanna").setTemperatureRainfall(0.7F, 0.1F);
		BiomeList.OLDseasonalForest = (new BiomeBeta(65, 2)).setColor(353825).setBiomeName("seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
		BiomeList.OLDshrubland = (new BiomeBeta(66, 5)).setColor(353825).setBiomeName("shrubland").setTemperatureRainfall(0.7F, 0.3F);
		BiomeList.OLDswampland = (new BiomeBeta(67, 1)).setColor(353825).setBiomeName("swampland").setTemperatureRainfall(0.55F, 0.65F);
		BiomeList.OLDtaiga = (new BiomeBeta(68, 6)).setColor(16777215).setBiomeName("taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
		BiomeList.OLDtundra = (new BiomeBeta(69, 9)).setColor(16777215).setBiomeName("tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();
		
		BiomeList.CLASSICnormal = (new BiomeClassic(70)).setColor(353825).setBiomeName("Classic");
		BiomeList.CLASSICsnow = (new BiomeClassic(71)).setColor(353825).setBiomeName("Classic").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
		
		BiomeList.COMMONnormal1 = (new BiomeCommon(72, 0)).setColor(353825).setBiomeName("BWG4 com_nom1").setTemperatureRainfall(0.9F, 0.8F);
		BiomeList.COMMONnormal2 = (new BiomeCommon(73, 0)).setColor(353825).setBiomeName("BWG4 com_nom2").setTemperatureRainfall(0.9F, 0.8F);
		BiomeList.COMMONsnow = (new BiomeCommon(74, 0)).setColor(353825).setBiomeName("BWG4 com_snow").setTemperatureRainfall(0.0F, 0.5F);
		BiomeList.COMMONnether = (new BiomeCommon(75, 0)).setColor(353825).setBiomeName("BWG4 Hell").setTemperatureRainfall(0.8F, 0.6F);
		
		BiomeManager.addBiome("test", BiomeList.OLDdesert, 1);
	}
}
