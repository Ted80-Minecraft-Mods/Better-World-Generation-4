package bwg4.api;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeManager 
{
	public static ArrayList<BiomeItem> list = new ArrayList<BiomeItem>();
	
	/**
	 * Biome must implement DefaultBiome
	 * 
	 * @param name = name of the biome in biome selection screen
	 * @param biome = the biome class
	 * @param temp = temperator 0 to 1
	 */
	public static void addBiome(String name, BiomeGenBase biome, float temp)
	{
		int s = list.size();
		if(s == 0)
		{
			list.add(new BiomeItem(name, biome, temp));
			return;
		}
		
		for(int i = 0; i < s; i++)
		{
			if(temp <= list.get(i).temp)
			{
				list.add(i, new BiomeItem(name, biome, temp));
				return;
			}
		}
		
		list.add(new BiomeItem(name, biome, temp));
	}
	
	public static String[] getBiomeNames()
	{
		int s = list.size();
		String[] names = new String[s];
		for(int i = 0; i < s; i++)
		{
			names[i] = list.get(i).name;
		}
		return names;
	}
	
	public static String getDefaultString()
	{
		String s = "";
		int size = list.size();
		for(int i = 0; i < size; i++)
		{
			s += i == 0 ? list.get(i).name : ";" + list.get(i).name;
		}
		return s;
	}
	
	public static BiomeGenBase[] getBiomesByNames(String[] biomeNames)
	{
		ArrayList<BiomeGenBase> b = new ArrayList<BiomeGenBase>();

		int size = list.size();
		for(int i = 0; i < size; i++)
		{
			for(int j = biomeNames.length - 1; j > -1; j--)
			{
				if(biomeNames[j].equals(list.get(i).name))
				{
					b.add(list.get(i).biome);
					break;
				}
				else if(j == 0)
				{
					System.out.println("[BWG4]: Warning biome name not found!");
				}
			}
		}

		int bl = b.size();
		BiomeGenBase[] biomes = new BiomeGenBase[bl];
		for(int a = 0; a < bl; a++)
		{
			biomes[a] = b.get(a);
		}
		return biomes;
	}
}
