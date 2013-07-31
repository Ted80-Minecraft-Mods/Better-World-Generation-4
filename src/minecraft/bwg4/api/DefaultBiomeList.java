package bwg4.api;

import java.util.ArrayList;
import net.minecraft.world.biome.BiomeGenBase;

public class DefaultBiomeList 
{
	public static BiomeGenBase[] biomeList = new BiomeGenBase[256];
	public static String[] biomeNames = new String[256];
	public static int[] biomeType = new int[256];
	public static int generatorversion = 2;

	/**
	 * Adds a new biome to the biome list.
	 * 
	 * @param name = name in the biome list gui, example: "YOURMODNAME: biome name"
	 * @param biome = the biome
	 * @param biometype 1 = cold, 2 = normal, 3 = wet, 4 = hot, 5 = island
	 */
	public static void addBiome(String name, BiomeGenBase biome, int biometype)
	{
		if(biomeNames[biome.biomeID] == null)
		{
			biomeList[biome.biomeID] = biome;
			biomeNames[biome.biomeID] = name;
			biomeType[biome.biomeID] = biometype;
		}
	}
	
	/**
	 * Get the biome array.
	 * 
	 * @return a ArrayList with all biomes
	 */
	public static BiomeGenBase[] getBiomeList()
	{
		return biomeList;
	}

	/**
	 * Get the biome type array.
	 * 
	 * @return a Int array with all biome type ids
	 */
	public static int[] getTypesList()
	{
		return biomeType;
	}
	
	/**
	 * Get all biome names.
	 * 
	 * @return a Sting array with all biome names
	 */
	public static String[] getStringList()
	{
		String[] newbiomelist = new String[biomeNames.length];
		int i = 0; 
		for(i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] != null)
			{
				newbiomelist[i] = biomeNames[i];
			}
		}
		
		return newbiomelist;
	}
	

	public static String[] getOldStringList()
	{
		String[] newbiomelist = new String[biomeNames.length];
		int i = 0; 
		for(i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] != null)
			{
				newbiomelist[i] = biomeNames[i] + "=true";
			}
		}
		
		return newbiomelist;
	}

	/**
	 * Get all biome names.
	 * 
	 * @return a Sting with all biome names
	 */
	public static String getDefaultString()
	{
		String genstring = generatorversion + "&";
		
		boolean first = true;
		for(int i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] != null)
			{
				if(!first)
				{
					genstring += ";" + biomeNames[i];
				}
				else
				{
					genstring += biomeNames[i];
					first = false;
				}
			}
		}
		return genstring;
	}
}
