package bwg4.api;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeItem 
{
	public String name;
	public BiomeGenBase biome;
	public float temp;
	
	public BiomeItem(String n, BiomeGenBase b, float t)
	{
		name = n;
		biome = b;
		temp = t;
	}
}
