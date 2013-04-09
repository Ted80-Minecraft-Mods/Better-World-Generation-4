package ted80.api;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;

public class DefaultBiomeList 
{
	public static ArrayList<BiomeGenBase> allowedBiomes = new ArrayList<BiomeGenBase>();
	public static String[] biomeList = new String[256];
	public static int[] biomeType = new int[256];
	public static int generatorversion = 1;
	
	public static void addBiome(String name, BiomeGenBase biome, int biometype)
	{
		allowedBiomes.add(biome);
		
		for(int i = 0; i < biomeList.length; i++)
		{
			if(biomeList[i] == null)
			{
				biomeList[i] = name;
				biomeType[i] = biometype;
				break;
			}
		}
	}
	
	public static ArrayList<BiomeGenBase> getBiomeList()
	{
		return allowedBiomes;
	}
	
	public static String[] getStringList()
	{
		String[] newbiomelist = biomeList;
		for(int i = 0; i < newbiomelist.length; i++)
		{
			if(newbiomelist[i] != null)
			{
				newbiomelist[i] += "=true";
			}
			else
			{
				break;
			}
		}
		return newbiomelist;
	}
	
	public static String getDefaultString()
	{
		String genstring = generatorversion + "&";
		
		for(int i = 0; i < biomeList.length; i++)
		{
			if(biomeList[i] != null)
			{
				if(i != 0)
				{
					genstring += ";" + biomeList[i] + "=true";
				}
				else
				{
					genstring += biomeList[i] + "=true";
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
