package bwg4.biomes;

import bwg4.api.biome.BiomeList;
import bwg4.api.biome.BiomeManager;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;

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
		
		BiomeManager.addBiome("Plains", BiomeGenBase.plains, 1);
		BiomeManager.addBiome("Desert", BiomeGenBase.desert, 1);
		BiomeManager.addBiome("Extreme Hills", BiomeGenBase.extremeHills, 1);
		BiomeManager.addBiome("Forest", BiomeGenBase.forest, 1);
		BiomeManager.addBiome("Taiga", BiomeGenBase.taiga, 1);
		BiomeManager.addBiome("Swampland", BiomeGenBase.swampland, 1);
/*
BiomeGenBase.icePlains "Ice Plains"
BiomeGenBase.iceMountains "Ice Mountains"
BiomeGenBase.mushroomIsland "MushroomIsland"
BiomeGenBase.mushroomIslandShore "MushroomIslandShore"
BiomeGenBase.beach "Beach"
BiomeGenBase.desertHills "DesertHills"
BiomeGenBase.forestHills "ForestHills"
BiomeGenBase.taigaHills "TaigaHills"
BiomeGenBase.extremeHillsEdge "Extreme Hills Edge"
BiomeGenBase.jungle "Jungle"
BiomeGenBase.jungleHills "JungleHills"
BiomeGenBase.jungleEdge "JungleEdge"
BiomeGenBase.deepOcean "Deep Ocean"
BiomeGenBase.stoneBeach "Stone Beach"
BiomeGenBase.coldBeach "Cold Beach"
BiomeGenBase.birchForest "Birch Forest"
BiomeGenBase.birchForestHills "Birch Forest Hills"
BiomeGenBase.roofedForest "Roofed Forest"
BiomeGenBase.coldTaiga "Cold Taiga"
BiomeGenBase.coldTaigaHills "Cold Taiga Hills"
BiomeGenBase.megaTaiga "Mega Taiga"
BiomeGenBase.megaTaigaHills "Mega Taiga Hills"
BiomeGenBase.extremeHillsPlus "Extreme Hills+"
BiomeGenBase.savanna "Savanna"
BiomeGenBase.savannaPlateau "Savanna Plateau"
BiomeGenBase.mesa = "Mesa"
BiomeGenBase.mesaPlateau_F "Mesa Plateau F"
BiomeGenBase.mesaPlateau "Mesa Plateau"
*/
	}
}
