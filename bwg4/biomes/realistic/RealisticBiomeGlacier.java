package bwg4.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import bwg4.biomes.RealisticBiome;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;
import bwg4.util.SnowheightCalculator;

public class RealisticBiomeGlacier extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeGlacier(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float lh = perlin.noise2(x / 180f, y / 180f) * 80f;
		lh *= lh / 15f;
		
		float sh = perlin.noise2(x / 10f, y / 10f) * 4f;
		sh *= lh / 10f > 3.75f ? 3.75f : lh / 10f;
		lh += sh;
		
		float gh = 0f;
		if(lh < 20f)
		{
			float lht = lh;
			if(lht < 10f) { lht = 10f; }
			gh = perlin.noise2(x / 5f, y / 5f) * (15f - lht - 1f) * 1.5f - 1f;
		}
		
		float h = lh > 10f + gh ? lh : 10f + gh;
		
		return h + 55f;
	}

	@Override
	public void rReplace(int x, int y, Block[] blocks, byte[] metadata, int depth, Random rand, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.2f ? true : false;
		boolean clay = c > 2.1f ? true : false;
		boolean ice = false;
		
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

            	if(cliff && k > 69)
            	{
	        		if(depth > -1 && depth < 6)
	        		{
        				if(clay)
        				{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
            				metadata[(y * 16 + x) * 256 + k] = 9; 
        				}
        				else
        				{
        					blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
        				}
	        		}
            	}
            	else
            	{
            		if(depth == 0)
	        		{
            			if(k > 69)
            			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            				SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
            			}
            			else
            			{
            				ice = true;
            				blocks[(y * 16 + x) * 256 + k] = Blocks.packed_ice;
            			}
	        		}
	        		else if(depth > 0 && depth < 8)
	        		{
	        			if(ice)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.packed_ice;
	        			}
	        			else if(k < 70)
	        			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
	        			}
	        			else
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
	        			}
	        		}
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
