package bwg4.support;

import net.minecraft.world.biome.BiomeGenBase;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import extrabiomes.api.BiomeManager;
import bwg4.api.DefaultBiomeList;
import bwg4.biomes.BWG4Biomes;

public class SupportExtraBiomesXL 
{
	public static void init()
	{
		if(BiomeManager.alpine.isPresent()) { DefaultBiomeList.addBiome("EBXL: Alpine", BiomeManager.alpine.get(), 1); }
		if(BiomeManager.autumnwoods.isPresent()) { DefaultBiomeList.addBiome("EBXL: Autumn Woods", BiomeManager.autumnwoods.get(), 2); }
		if(BiomeManager.birchforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Birch Forest", BiomeManager.birchforest.get(), 2); }
		if(BiomeManager.extremejungle.isPresent()) { DefaultBiomeList.addBiome("EBXL: Extreme Jungle", BiomeManager.extremejungle.get(), 3); }
		if(BiomeManager.forestedisland.isPresent()) { DefaultBiomeList.addBiome("EBXL: Forested Hills", BiomeManager.forestedisland.get(), 2); }
		if(BiomeManager.forestedhills.isPresent()) { DefaultBiomeList.addBiome("EBXL: Forested Island", BiomeManager.forestedhills.get(), 2); }
		if(BiomeManager.glacier.isPresent()) { DefaultBiomeList.addBiome("EBXL: Glacier", BiomeManager.glacier.get(), 1); }
		if(BiomeManager.greenhills.isPresent()) { DefaultBiomeList.addBiome("EBXL: Green Hills", BiomeManager.greenhills.get(), 1); }
		if(BiomeManager.icewasteland.isPresent()) { DefaultBiomeList.addBiome("EBXL: Ice Wasteland", BiomeManager.icewasteland.get(), 1); }
		if(BiomeManager.greenswamp.isPresent()) { DefaultBiomeList.addBiome("EBXL: Green Swamplands", BiomeManager.greenswamp.get(), 3); }
		if(BiomeManager.marsh.isPresent()) { DefaultBiomeList.addBiome("EBXL: Marsh", BiomeManager.marsh.get(), 3); }
		if(BiomeManager.meadow.isPresent()) { DefaultBiomeList.addBiome("EBXL: Meadow", BiomeManager.meadow.get(), 2); }
		if(BiomeManager.minijungle.isPresent()) { DefaultBiomeList.addBiome("EBXL: Mini Jungle", BiomeManager.minijungle.get(), 3); }
		if(BiomeManager.mountaindesert.isPresent()) { DefaultBiomeList.addBiome("EBXL: Mountain Desert", BiomeManager.mountaindesert.get(), 4); }
		if(BiomeManager.mountainridge.isPresent()) { DefaultBiomeList.addBiome("EBXL: Mountain Ridge", BiomeManager.mountainridge.get(), 4); }
		if(BiomeManager.mountaintaiga.isPresent()) { DefaultBiomeList.addBiome("EBXL: Mountain Taiga", BiomeManager.mountaintaiga.get(), 1); }
		if(BiomeManager.pineforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Pine Forest", BiomeManager.pineforest.get(), 2); }
		if(BiomeManager.rainforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Rainforest", BiomeManager.rainforest.get(), 3); }
		if(BiomeManager.redwoodforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Redwood Forest", BiomeManager.redwoodforest.get(), 2); }
		if(BiomeManager.redwoodlush.isPresent()) { DefaultBiomeList.addBiome("EBXL: Redwood Lush", BiomeManager.redwoodlush.get(), 2); }
		if(BiomeManager.savanna.isPresent()) { DefaultBiomeList.addBiome("EBXL: Savanna", BiomeManager.savanna.get(), 4); }
		if(BiomeManager.shrubland.isPresent()) { DefaultBiomeList.addBiome("EBXL: Shrubland", BiomeManager.shrubland.get(), 2); }
		if(BiomeManager.snowforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Snow Forest", BiomeManager.snowforest.get(), 1); }
		if(BiomeManager.snowyrainforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Snowy Rainforest", BiomeManager.snowyrainforest.get(), 1); }
		if(BiomeManager.temperaterainforest.isPresent()) { DefaultBiomeList.addBiome("EBXL: Temperate Rainforest", BiomeManager.temperaterainforest.get(), 2); }
		if(BiomeManager.tundra.isPresent()) { DefaultBiomeList.addBiome("EBXL: Tundra", BiomeManager.tundra.get(), 1); }
		if(BiomeManager.wasteland.isPresent()) { DefaultBiomeList.addBiome("EBXL: Wasteland", BiomeManager.wasteland.get(), 4); }
		if(BiomeManager.woodlands.isPresent()) { DefaultBiomeList.addBiome("EBXL: Woodlands", BiomeManager.woodlands.get(), 2); }
		
		Support.extraBiomesXL = true;
	}
}
