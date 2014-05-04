package bwg4.data;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Planets 
{	
	public static PlanetData[] data = new PlanetData[]{
		new PlanetData(Blocks.leaves, Blocks.log, 20, 5, 30),
		new PlanetData(Blocks.dirt, Blocks.dirt, 20, 5, 30, Blocks.grass, false),
		new PlanetData(Blocks.dirt, Blocks.dirt, 15, 5, 10, Blocks.grass, true),
		new PlanetData(Blocks.sand, Blocks.sand, 20, 5, 10),
		
		new PlanetData(Blocks.stone, Blocks.coal_ore, 20, 15, 10),
		new PlanetData(Blocks.stone, Blocks.iron_ore, 20, 12, 10),
		new PlanetData(Blocks.stone, Blocks.gravel, 17, 9, 5),
		new PlanetData(Blocks.stone, Blocks.gold_ore, 17, 9, 5),
		new PlanetData(Blocks.stone, Blocks.redstone_ore, 15, 9, 5),
		new PlanetData(Blocks.stone, Blocks.diamond_ore, 15, 9, 5),

		new PlanetData(Blocks.glowstone, Blocks.glowstone, 18, 5, 10),
		new PlanetData(Blocks.glass, Blocks.water, 15, 10, 5),
		new PlanetData(Blocks.glass, Blocks.lava, 15, 10, 3)
	};
	
	private static int[] rarityData;
	private static int rarityLenght;
	
	public static void init()
	{
		int length = 0;
		for(int i = data.length - 1; i > -1; i--)
		{
			length += data[i].rarity;
		}
		
		rarityLenght = length;
		rarityData = new int[length];
		int count = 0;
		for(int j = data.length - 1; j > -1; j--)
		{
			for(int k = 0; k < data[j].rarity; k++)
			{
				rarityData[count] = j;
				count++;
			}
		}
	}

	public static PlanetData getRandomPlanet(Random rand)
	{
		return data[rarityData[rand.nextInt(rarityLenght)]];
	}
}
