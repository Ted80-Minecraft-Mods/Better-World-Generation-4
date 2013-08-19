package bwg4.deco;

import java.util.Random;

import bwg4.util.Coords;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoWasteland extends WorldGenerator
{
	public int id;
	public int var1;
	public int var2;
	
	public BWG4decoWasteland(int i)
	{
		id = i;
		var1 = 0;
		var2 = 0;
	}
	
	public BWG4decoWasteland(int i, int v1, int v2)
	{
		id = i;
		var1 = v1;
		var2 = v2;
	}

	public boolean generate(World world, Random random, int i, int j, int k) 
	{
		if(id == 1)
		{
			int j2 = j;
			for(int h1 = j - 3; h1 > 0; h1--)
			{
				if(world.getBlockMaterial(i, h1, k) == Material.rock)
				{
					int size = 14 + random.nextInt(6);
					j2 = h1 + size;
					generateStalagmite(size, i, h1 - 3, k, world, random);
					break;
				}
			}
			for(int h2 = j2; h2 < 60; h2++)
			{
				if(world.getBlockMaterial(i, h2, k) == Material.rock)
				{
					generateStalactite(14 + random.nextInt(6), i, h2 + 3, k, world, random);
					break;
				}
			}
		}
		if(id == 2)
		{
			if(world.getBlockMaterial(i, j, k) == Material.lava)
			{
				for(int h = j; h < 55; h++)
				{
					int c = 0;
					if(world.getBlockMaterial(i - 1, h, k) == Material.rock) { c++; }
					if(world.getBlockMaterial(i + 1, h, k) == Material.rock) { c++; }
					if(world.getBlockMaterial(i, h, k - 1) == Material.rock) { c++; }
					if(world.getBlockMaterial(i, h, k + 1) == Material.rock) { c++; }
					
					if(c == 4)
					{
						world.setBlock(i, h, k, Block.lavaMoving.blockID, 0, 2);
						for(int b = h - 1; b > 35; b--)
						{
							if(!world.isAirBlock(i, b, k))
							{
								world.setBlock(i, h, k, 0, 0, 2);
							}
							else
							{
								break;
							}
						}

						//world.scheduledUpdatesAreImmediate = true;
		                //Block.blocksList[Block.lavaMoving.blockID].updateTick(world, i, h, k, random);
		                //world.scheduledUpdatesAreImmediate = false;
						
						break;
					}
				}
			}
		}
		if(id == 3)
		{
			float f = random.nextFloat() * 3.141593F;
			double d = (float)(i + 8) + (MathHelper.sin(f) * (float)var1) / 8F;
			double d1 = (float)(i + 8) - (MathHelper.sin(f) * (float)var1) / 8F;
			double d2 = (float)(k + 8) + (MathHelper.cos(f) * (float)var1) / 8F;
			double d3 = (float)(k + 8) - (MathHelper.cos(f) * (float)var1) / 8F;
			double d4 = j + random.nextInt(3) + 2;
			double d5 = j + random.nextInt(3) + 2;
			for(int l = 0; l <= var1; l++)
			{
				double d6 = d + ((d1 - d) * (double)l) / (double)var1;
				double d7 = d4 + ((d5 - d4) * (double)l) / (double)var1;
				double d8 = d2 + ((d3 - d2) * (double)l) / (double)var1;
				double d9 = (random.nextDouble() * (double)var1) / 16D;
				double d10 = (double)(MathHelper.sin(((float)l * 3.141593F) / (float)var1) + 1.0F) * d9 + 1.0D;
				double d11 = (double)(MathHelper.sin(((float)l * 3.141593F) / (float)var1) + 1.0F) * d9 + 1.0D;
				int i1 = MathHelper.floor_double(d6 - d10 / 2D);
				int j1 = MathHelper.floor_double(d7 - d11 / 2D);
				int k1 = MathHelper.floor_double(d8 - d10 / 2D);
				int l1 = MathHelper.floor_double(d6 + d10 / 2D);
				int i2 = MathHelper.floor_double(d7 + d11 / 2D);
				int j2 = MathHelper.floor_double(d8 + d10 / 2D);
				for(int k2 = i1; k2 <= l1; k2++)
				{
					double d12 = (((double)k2 + 0.5D) - d6) / (d10 / 2D);
					if(d12 * d12 >= 1.0D)
					{
						continue;
					}
					for(int l2 = j1; l2 <= i2; l2++)
					{
						double d13 = (((double)l2 + 0.5D) - d7) / (d11 / 2D);
						if(d12 * d12 + d13 * d13 >= 1.0D)
						{
							continue;
						}
						for(int i3 = k1; i3 <= j2; i3++)
						{
							double d14 = (((double)i3 + 0.5D) - d8) / (d10 / 2D);
							if(d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && (world.getBlockId(k2, l2, i3) == Block.stone.blockID || world.getBlockId(k2, l2, i3) == Block.cobblestone.blockID))
							{
								world.setBlock(k2, l2, i3, var2);
							}
						}

					}

				}

			}
		}
		if(id == 4)
		{
			int g = world.getBlockId(i, j - 1, k);
			if(g == Block.dirt.blockID || g == Block.gravel.blockID || g == Block.cobblestone.blockID )
			{
				int s = 6 + random.nextInt(5);
				for(int t = 0 + j; t < s + j; t++)
				{
					if(world.isAirBlock(i, t, k))
					{
						world.setBlock(i, t, k, Block.wood.blockID, 1, 2);
					}
				}
			}
		}
		return true;
	}
	
	public void generateStalactite(int size, int px, int py, int pz, World w, Random rand)
	{
		for(int x = -3; x < 4; x++)
		{
			for(int z = -3; z < 4; z++)
			{
				double d = 1 + Coords.getDistance(x, z, 0, 0);
				int h = (int) Math.floor((size) / (d / 1.5D)) - 1 + rand.nextInt(3);
				for(int y = 0; y > -size; y--)
				{
					if(y > -h)
					{
						if (w.isAirBlock(px + x, py + y, pz + z))
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
					}
				}
			}
		}
	}
	
	public void generateStalagmite(int size, int px, int py, int pz, World w, Random rand)
	{
		for(int x = -3; x < 4; x++)
		{
			for(int z = -3; z < 4; z++)
			{
				double d = 1 + Coords.getDistance(x, z, 0, 0);
				int h = (int) Math.floor((size) / (d / 1.5D)) - 1 + rand.nextInt(3);
				for(int y = 0; y < size; y++)
				{
					if(y < h)
					{
						if (w.getBlockMaterial(px + x, py + y, pz + z) != Material.rock)
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
					}
				}
			}
		}
	}
}
