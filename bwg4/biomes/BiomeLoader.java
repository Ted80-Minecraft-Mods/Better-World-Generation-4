package bwg4.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import bwg4.api.BiomeList;
import bwg4.api.BiomeManager;
import bwg4.biomes.realistic.*;
import bwg4.config.ConfigBWG4;

public class BiomeLoader 
{
	public static void load()
	{
		BiomeList.REALISTICpole = (new RealisticBiomePole(200)).setColor(16777215).setBiomeName("Polar").setTemperatureRainfall(0.0F, 0.1F);
		BiomeList.REALISTICglacier = (new RealisticBiomeGlacier(201)).setColor(16777215).setBiomeName("Glacier").setTemperatureRainfall(0.0F, 0.1F);
		BiomeList.REALISTICsnowtaiga = (new RealisticBiomeSnowTaiga(202)).setColor(16777215).setBiomeName("Taiga").setTemperatureRainfall(0.0F, 0.1F);
		BiomeList.REALISTICtaiga = (new RealisticBiomeTaiga(203)).setColor(16777215).setBiomeName("SnowTaiga").setTemperatureRainfall(0.7F, 0.5F);
		
		BiomeList.REALISTICcanyon = (new RealisticBiomeCanyon(204)).setColor(16421912).setBiomeName("Canyon").setTemperatureRainfall(2.0f, 0.0f);
		BiomeList.REALSITICmesa = (new RealisticBiomeMesa(205)).setColor(16421912).setBiomeName("Mesa").setTemperatureRainfall(2.0f, 0.0f);
		BiomeList.REALISTICsavannah = (new RealisticBiomeSavannah(206)).setColor(16421912).setBiomeName("Savannah").setTemperatureRainfall(0.9f, 0.1f);
		BiomeList.REALISTICdesert = (new RealisticBiomeDesert(208)).setColor(16421912).setBiomeName("Desert").setTemperatureRainfall(2.0f, 0.0f);
		BiomeList.REALISTICredDesert = (new RealisticBiomeRedDesert(209)).setColor(16421912).setBiomeName("Red Desert").setTemperatureRainfall(1.0f, 0.4f); 
		
		BiomeList.REALISTICjungleRivers = (new RealisticBiomeJungleRivers(210)).setColor(353825).setBiomeName("Jungle Rivers").setTemperatureRainfall(2.0f, 0.0f);
		BiomeList.REALISTICjungleHills = (new RealisticBiomeJungleHills(211)).setColor(353825).setBiomeName("Jungle Hills").setTemperatureRainfall(1.0f, 1.0f);
		
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
		
		BiomeManager.addBiome("Taiga", BiomeList.REALISTICtaiga, 0f);
		
		//TODO Biomedictonairy
	}
}
