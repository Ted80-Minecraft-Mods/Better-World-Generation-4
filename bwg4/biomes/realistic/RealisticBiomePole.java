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

public class RealisticBiomePole extends BiomeGenBase implements RealisticBiome
{

	public RealisticBiomePole(int id) 
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
		float lh = perlin.noise2(x / 180f, y / 180f) * 60f;
		lh *= lh / 10f;
		
		float lsh = perlin.noise2(x / 10f, y / 10f) * 4f;
		lsh *= lh / 5f > 3.75f ? 3.75f : lh / 10f;
		lh += lsh;
		
		float h = perlin.noise2(x / 130f, y / 130f) * 25f;
		h *= h / 20f;
		if(h < 0.1f) { h = 0.1f; }
		
		float sh = perlin.noise2(x / 10f, y / 10f) * 6f;
		sh *= h / 20f;
		if(sh < 0.1f) { sh = 0.1f; }
		h += sh;
		
		h = h > lh - 50 ? h : lh - 50;
		return h + 70f;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.6f ? true : false;
		boolean clay = c > 2.1f ? true : false;
		
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
            	if(cliff)
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
                    		if(depth > -1 && depth < 2)
                    		{
                    			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
                    		}
                    		else
                    		{
                    			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
                    		}
        				}
	        		}
            	}
            	else
            	{
	        		if(depth > -1 && depth < 9)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
	            		if(depth == 0 && k < 254)
	            		{
	            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
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
