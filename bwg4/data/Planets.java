package bwg4.data;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Planets 
{	
	public static PlanetData[] data_overworld = new PlanetData[]{
		new PlanetData(Blocks.leaves, Blocks.log, 15, 5, 30),
		new PlanetData(Blocks.dirt, Blocks.dirt, 15, 5, 30, Blocks.grass, false),
		new PlanetData(Blocks.dirt, Blocks.dirt, 15, 5, 10, Blocks.grass, true),
		new PlanetData(Blocks.sand, Blocks.sand, 15, 5, 10),
		new PlanetData(Blocks.clay, Blocks.clay, 12, 4, 6),
		new PlanetData(Blocks.hardened_clay, Blocks.hardened_clay, 12, 4, 5),
		new PlanetData(Blocks.dirt, Blocks.dirt, 12, 4, 4, Blocks.mycelium, false),
		
		new PlanetData(Blocks.stone, Blocks.coal_ore, 15, 9, 10),
		new PlanetData(Blocks.stone, Blocks.iron_ore, 15, 9, 10),
		new PlanetData(Blocks.stone, Blocks.gravel, 15, 8, 5),
		new PlanetData(Blocks.stone, Blocks.gold_ore, 15, 8, 5),
		new PlanetData(Blocks.stone, Blocks.redstone_ore, 15, 7, 5),
		new PlanetData(Blocks.stone, Blocks.diamond_ore, 15, 7, 5),

		new PlanetData(Blocks.glowstone, Blocks.glowstone, 15, 5, 10),
		new PlanetData(Blocks.glass, Blocks.water, 15, 8, 5),
		new PlanetData(Blocks.glass, Blocks.lava, 15, 8, 3)
	};
	
	public static PlanetData[] data_hell = new PlanetData[]{
		new PlanetData(Blocks.netherrack, Blocks.netherrack, 15, 8, 20),
		new PlanetData(Blocks.netherrack, Blocks.quartz_ore, 15, 8, 10),
		new PlanetData(Blocks.glowstone, Blocks.glowstone, 15, 5, 10),
		new PlanetData(Blocks.soul_sand, Blocks.soul_sand, 10, 5, 8)
	};
	
	private static int[] rarityData_overworld;
	private static int rarityLenght_overworld;
	private static int[] rarityData_hell;
	private static int rarityLenght_hell;
	
	public static void init()
	{
		int length = 0;
		for(int i = data_overworld.length - 1; i > -1; i--)
		{
			length += data_overworld[i].rarity;
		}
		
		rarityLenght_overworld = length;
		rarityData_overworld = new int[length];
		int count = 0;
		for(int j = data_overworld.length - 1; j > -1; j--)
		{
			for(int k = 0; k < data_overworld[j].rarity; k++)
			{
				rarityData_overworld[count] = j;
				count++;
			}
		}
		
		length = 0;
		for(int l = data_hell.length - 1; l > -1; l--)
		{
			length += data_hell[l].rarity;
		}
		
		rarityLenght_hell = length;
		rarityData_hell = new int[length];
		count = 0;
		for(int m = data_hell.length - 1; m > -1; m--)
		{
			for(int n = 0; n < data_hell[m].rarity; n++)
			{
				rarityData_hell[count] = m;
				count++;
			}
		}
	}

	public static PlanetData getRandomPlanet(Random rand, int dimension)
	{
		if(dimension == 0)
		{
			return data_overworld[rarityData_overworld[rand.nextInt(rarityLenght_overworld)]];
		}
		else
		{
			return data_hell[rarityData_hell[rand.nextInt(rarityLenght_hell)]];
		}
	}
}
