package bwg4.support;

import net.minecraft.world.biome.BiomeGenBase;
import bwg4.api.DefaultBiomeList;
import highlands.api.HighlandsBiomes;

public class SupportHighLands 
{
	public static void init()
	{
		if(HighlandsBiomes.alps != null) { DefaultBiomeList.addBiome("HL: Alps", HighlandsBiomes.alps, 1); }
		if(HighlandsBiomes.autumnForest != null) { DefaultBiomeList.addBiome("HL: Autumn Forest", HighlandsBiomes.autumnForest, 2); }
		if(HighlandsBiomes.badlands != null) { DefaultBiomeList.addBiome("HL: Badlands", HighlandsBiomes.badlands, 4); }
		if(HighlandsBiomes.birchHills != null) { DefaultBiomeList.addBiome("HL: Birch Hills", HighlandsBiomes.birchHills, 2); }
		if(HighlandsBiomes.bog != null) { DefaultBiomeList.addBiome("HL: Bog", HighlandsBiomes.bog, 3); }
		if(HighlandsBiomes.cliffs != null) { DefaultBiomeList.addBiome("HL: Cliffs", HighlandsBiomes.cliffs, 2); }
		if(HighlandsBiomes.desertMountains != null) { DefaultBiomeList.addBiome("HL: Desert Mountains", HighlandsBiomes.desertMountains, 4); }
		if(HighlandsBiomes.dunes != null) { DefaultBiomeList.addBiome("HL: Dunes", HighlandsBiomes.dunes, 4); }
		if(HighlandsBiomes.estuary != null) { DefaultBiomeList.addBiome("HL: Estuary", HighlandsBiomes.estuary, 4); }
		if(HighlandsBiomes.flyingMountains != null) { DefaultBiomeList.addBiome("HL: Flying Mountains", HighlandsBiomes.flyingMountains, 3); }
		if(HighlandsBiomes.glacier != null) { DefaultBiomeList.addBiome("HL: Glacier", HighlandsBiomes.glacier, 1); }
		if(HighlandsBiomes.highlandsb != null) { DefaultBiomeList.addBiome("HL: Highlands", HighlandsBiomes.highlandsb, 2); }
		if(HighlandsBiomes.lowlands != null) { DefaultBiomeList.addBiome("HL: Lowlands", HighlandsBiomes.lowlands, 2); }
		if(HighlandsBiomes.meadow != null) { DefaultBiomeList.addBiome("HL: Meadow", HighlandsBiomes.meadow, 2); }
		if(HighlandsBiomes.outback != null) { DefaultBiomeList.addBiome("HL: Outback", HighlandsBiomes.outback, 4); }
		if(HighlandsBiomes.pinelands != null) { DefaultBiomeList.addBiome("HL: Pinelands", HighlandsBiomes.pinelands, 2); }
		if(HighlandsBiomes.rainforest != null) { DefaultBiomeList.addBiome("HL: Rainforest", HighlandsBiomes.rainforest, 3); }
		if(HighlandsBiomes.redwoodForest != null) { DefaultBiomeList.addBiome("HL: Redwood Forest", HighlandsBiomes.redwoodForest, 2); }
		if(HighlandsBiomes.rockMountains != null) { DefaultBiomeList.addBiome("HL: Rock Mountains", HighlandsBiomes.rockMountains, 4); }
		if(HighlandsBiomes.sahel != null) { DefaultBiomeList.addBiome("HL: Sahel", HighlandsBiomes.sahel, 4); }
		if(HighlandsBiomes.savannah != null) { DefaultBiomeList.addBiome("HL: Savannah", HighlandsBiomes.savannah, 4); }
		if(HighlandsBiomes.snowMountains != null) { DefaultBiomeList.addBiome("HL: Snow Mountains", HighlandsBiomes.snowMountains, 1); }
		if(HighlandsBiomes.steppe != null) { DefaultBiomeList.addBiome("HL: Steppe", HighlandsBiomes.steppe, 4); }
		if(HighlandsBiomes.tallPineForest != null) { DefaultBiomeList.addBiome("HL: Tall Pine Forest", HighlandsBiomes.tallPineForest, 1); }
		if(HighlandsBiomes.tropicalIslands != null) { DefaultBiomeList.addBiome("HL: Tropical Islands", HighlandsBiomes.tropicalIslands, 3); }
		if(HighlandsBiomes.tropics != null) { DefaultBiomeList.addBiome("HL: Tropics", HighlandsBiomes.tropics, 3); }
		if(HighlandsBiomes.tundra != null) { DefaultBiomeList.addBiome("HL: Tundra", HighlandsBiomes.tundra, 1); }
		if(HighlandsBiomes.woodlands != null) { DefaultBiomeList.addBiome("HL: Woodlands", HighlandsBiomes.woodlands, 2); }
		if(HighlandsBiomes.woodsMountains != null) { DefaultBiomeList.addBiome("HL: Woodland Mountains", HighlandsBiomes.woodsMountains, 2); }
		
		Support.highlands = true;
	}
}
