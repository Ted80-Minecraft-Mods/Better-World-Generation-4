package bwg4.api;

import java.util.ArrayList;
import net.minecraft.world.biome.BiomeGenBase;

public class DefaultBiomeList 
{
	public static ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
	public static String[] biomeNames = new String[256];
	public static int[] biomeType = new int[256];
	public static int generatorversion = 1;

	/**
	 * Adds a new biome to the biome list.
	 * 
	 * @param name = name in the biome list gui, example: "YOURMODNAME: biome name"
	 * @param biome = the biome
	 * @param biometype 1 = cold, 2 = normal, 3 = wet, 4 = hot, 5 = island
	 */
	public static void addBiome(String name, BiomeGenBase biome, int biometype)
	{
		biomeList.add(biome);
		
		for(int i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] == null)
			{
				biomeNames[i] = name;
				biomeType[i] = biometype;
				break;
			}
		}
	}
	
	/**
	 * Get the biome array.
	 * 
	 * @return a ArrayList with all biomes
	 */
	public static ArrayList<BiomeGenBase> getBiomeList()
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
		for(int i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] != null)
			{
				newbiomelist[i] = biomeNames[i] + "=true";
			}
			else
			{
				break;
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
		
		for(int i = 0; i < biomeNames.length; i++)
		{
			if(biomeNames[i] != null)
			{
				if(i != 0)
				{
					genstring += ";" + biomeNames[i] + "=true";
				}
				else
				{
					genstring += biomeNames[i] + "=true";
				}
			}
			else
			{
				break;
			}
		}
		return genstring;
	}
}
