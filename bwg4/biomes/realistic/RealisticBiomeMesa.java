package bwg4.biomes.realistic;

import java.util.Random;

import bwg4.biomes.RealisticBiome;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeMesa extends BiomeGenBase implements RealisticBiome
{
	private int[] claycolor = new int[100];
	
	public RealisticBiomeMesa(int id) 
	{
		super(id);
		
		int[] c = new int[]{1, 8, 0};
		PerlinNoise perlin = new PerlinNoise(3L);
		
		float n;
		for(int i = 0; i < 100; i++)
		{
			n = perlin.noise1(i / 3f) * 4f + perlin.noise1(i / 1f) * 0.6f + 1.5f;
			n = n >= 3f ? 2.9f : n < 0f ? 0f : n;
			claycolor[i] = c[(int)n];
		}
		
		waterColorMultiplier = 0x00FF62;
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin)
	{
		for (int l = 0; l < 1; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 83)
			{
				(new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		for (int b1 = 0; b1 < 3; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 90)
			{
				WorldGenerator worldgenerator;
				worldgenerator = new WorldGenShrub(0, 0);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int i15 = 0; i15 < 3; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = 60 + rand.nextInt(40);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
		
		for(int k18 = 0; k18 < 18; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = 60 + rand.nextInt(40);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(world, rand, k21, j23, k24);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{		
		float b = perlin.noise2(x / 160f, y / 160f) * 40f;
		b *= b / 40f;
		
		float sb = 0f;
		if(b > 2f)
		{
			sb = (b - 2f) / 2f;
			sb = sb < 0f ? 0f : sb > 5.5f ? 5.5f : sb;
			sb = perlin.noise2(x / 12f, y / 12f) * sb;
		}
		b += sb;
		
		b = b < 0.1f ? 0.1f : b;
	
		float c1 = 0f;
		if(b > 2f)
		{
			c1 = b > 5.5f ? 3.5f : b - 2f;
			c1 *= 5;
		}
	
		float c2 = 0f;
		if(b > 5.5f)
		{
			c2 = b > 6f ? 0.5f : b - 5.5f;
			c2 *= 35;
		}
		
		float bn = 0f;
		if(b < 7f)
		{
			float bnh = 5f - b;
			bn += perlin.noise2(x / 70f, y / 70f) * (bnh * 0.4f);
			bn += perlin.noise2(x / 20f, y / 20f) * (bnh * 0.3f);
		}
		
		float w = perlin.noise2(x / 80f, y / 80f) * 25f;
		w *= w / 25f;
		
		b += c1 + c2 + bn - w;
		
		return 74f + b;
	}
	
	public byte getClayColorForHeight(int k)
	{
		k -= 60;
		k = k < 0 ? 0 : k > 99 ? 99 : k;
		return (byte)claycolor[k];
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

        		if(depth > -1 && depth < 12)
	        	{
	            	if(cliff)
	            	{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	    				metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	            	}
	            	else
	            	{
	        			if(depth > 4)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
		        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	        			}
	        			else if(k > 77)
	        			{
	        				if(rand.nextInt(5) == 0)
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        				metadata[(y * 16 + x) * 256 + k] = 1;
	        				}
	        			}
	        			else if(k < 69)
						{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
						}
	        			else if(k < 78)
	        			{
	        				if(depth == 0)
	        				{
	        					if(k < 72 && rand.nextInt(k - 69 + 1) == 0)
	        					{
			        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        					}
		        				else if(rand.nextInt(5) == 0)
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
		        				}
		        				else
		        				{
			        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
			        				metadata[(y * 16 + x) * 256 + k] = 1;
		        				}
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        				metadata[(y * 16 + x) * 256 + k] = 1;
	        				}
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        				metadata[(y * 16 + x) * 256 + k] = 1;
	        			}
	            	}
        		}
        		else if(k > 70)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
        		}
            }
		}
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}
}
