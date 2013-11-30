package bwg4.deco;

import java.util.Random;

import bwg4.util.Coords;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoMites extends WorldGenerator
{
	public int size;
	public int type;
	public int max;
	
	public BWG4decoMites(int t, int s, int m)
	{
		size = s;
		type = t;
		max = m;
	}
	
	public boolean generate(World world, Random random, int i, int j, int k) 
	{
		Material mat1 = Material.rock;
		Material mat2 = Material.rock;
		if(type == 2)
		{
			mat1 = Material.grass;
		}
		
		int j2 = j;
		for(int h1 = j; h1 > 0; h1--)
		{
			if(world.getBlockMaterial(i, h1, k) == mat1)
			{
				j2 = h1 + size;
				generateStalagmite(size, i, h1 - 4, k, world, random);
				break;
			}
			if(world.getBlockMaterial(i, h1, k) == Material.water)
			{
				break;
			}
		}
		for(int h2 = j2; h2 < max; h2++)
		{
			if(world.getBlockMaterial(i, h2, k) == mat2)
			{
				generateStalactite(size, i, h2 + 6, k, world, random);
				break;
			}
		}
		return true;
	}

	public void generateStalactite(int size, int px, int py, int pz, World w, Random rand)
	{
		for(int x = -5; x < 6; x++)
		{
			for(int z = -5; z < 6; z++)
			{
				double d = 1 + Coords.getDistance(x, z, 0, 0);
				int h = (int) Math.floor((size) / (d / 1.5D)) - 1 + rand.nextInt(3);
				for(int y = 0; y > -size; y--)
				{
					if(y > -h)
					{
						if (w.isAirBlock(px + x, py + y, pz + z))
						{
							if(type == 1 || type == 3)
							{
								if(rand.nextInt(2) == 0)
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.cobblestone.blockID, 0);
								}
								else
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.stone.blockID, 0);
								}
							}
							else
							{
								setBlockAndMetadata(w, px + x, py + y, pz + z, Block.sandStone.blockID, 0);
							}
						}
					}
				}
			}
		}
	}
	
	public void generateStalagmite(int size, int px, int py, int pz, World w, Random rand)
	{
		for(int x = -5; x < 6; x++)
		{
			for(int z = -5; z < 6; z++)
			{
				double d = 1 + Coords.getDistance(x, z, 0, 0);
				int h = (int) Math.floor((size) / (d / 1.5D)) - 1 + rand.nextInt(3);
				for(int y = 0; y < size; y++)
				{
					if(y < h && d < 5)
					{
						if (w.getBlockMaterial(px + x, py + y, pz + z) != Material.rock)
						{
							if(type == 3)
							{
								if(rand.nextInt((int) Math.floor(y / 5) + 1) == 0)
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.dirt.blockID, 0);
								}
								else
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.cobblestoneMossy.blockID, 0);
								}
							}
							else if(type == 1)
							{
								if(rand.nextInt(2) == 0)
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.cobblestone.blockID, 0);
								}
								else
								{
									setBlockAndMetadata(w, px + x, py + y, pz + z, Block.stone.blockID, 0);
								}
							}
							else
							{
								setBlockAndMetadata(w, px + x, py + y, pz + z, Block.sandStone.blockID, 0);
							}
						}
					}
				}
			}
		}
	}
}
