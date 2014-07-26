package bwg4.biomes.realistic;

import java.util.Random;

import bwg4.biomes.RealisticBiome;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class RealisticBiomeDesert extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeDesert(int id)
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		for(int i15 = 0; i15 < 2; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(128);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
		
		for(int k18 = 0; k18 < 10; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(128);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(world, rand, k21, j23, k24);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float b = perlin.noise2(x / 80f, y / 80f) * 16f;
		b *= b / 10f;
		b = b < 0.1f ? 0.1f : b;
		b += perlin.noise2(x / 20f, y / 20f) * 3.5f;
		
		float h = perlin.noise2(x / 350f, y / 350f) * 80f;
		float hd = h / 40f > 1.25f ? 1.25f : h / 40f;
		h *= hd;
		
		float sh = perlin.noise2(x / 12f, y / 12f) * 2f;
		sh *= h / 20f > 3.75f ? 3.75f : h / 20f;
		h += sh;
		
		float c = 0f;
		float cs = 13f + perlin.noise2(x / 150f, y / 150f) * 20f;
		if(h > cs)
		{
			float ch = 4f + perlin.noise2(x / 130f, y / 130f) * 4;
			c = h > cs + ch ? ch : h - cs;
			c *= 3.5f + perlin.noise2(x / 110f, y / 110f) * 6f;
		}

		b += h + c;
		
		return b + 75f;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.1f ? true : false;
		
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
	        		if(depth > -1 && depth < 8)
	        		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
	        		}
            	}
            	else
            	{
	        		if(depth > -1 && depth < 9)
	        		{
	        			if(depth > 4)
	        			{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        			}
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        		}
            	}
            }
		}
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0f;
	}
}
