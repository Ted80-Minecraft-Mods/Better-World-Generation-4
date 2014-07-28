package bwg4.biomes.realistic;

import java.util.Random;

import bwg4.biomes.RealisticBiome;
import bwg4.deco.DecoCacti;
import bwg4.deco.DecoFlowers;
import bwg4.deco.DecoGrass;
import bwg4.deco.DecoSavannah;
import bwg4.deco.old.OldGenReed;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeRedDesert extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeRedDesert(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		if(rand.nextInt(2) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 71)
		    {
		    	(new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
		    }
		}
		
		for(int b33 = 0; b33 < 5; b33++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

		    if(z52 < 66)
		    {
				WorldGenerator worldgenerator = rand.nextInt(5) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(4) == 0 ? new DecoSavannah(0): new DecoSavannah(1);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
		    }
		    else if(z52 < 72 && rand.nextInt(3) == 0)
		    {
				WorldGenerator worldgenerator = rand.nextInt(5) != 0 ? new WorldGenShrub(0, 0) : new DecoSavannah(1);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
		    }
		}
		
		if(rand.nextInt(2) == 0) 
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new OldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		for(int f23 = 0; f23 < 5; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{11,11,9,3,3,3,2,1,1})).generate(world, rand, j15, j17, j20);
		}
		
		for(int k18 = 0; k18 < 12; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int l14 = 0; l14 < 15; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 35f, y / 35f) * 6;
		h += perlin.noise2(x / 23f, y / 23f) * 5;
		h += perlin.noise2(x / 12f, y / 12f) * 2;
		
		float m = perlin.noise2(x / 120f, y / 120f) * 70f;
		m *= m / 40f;
		
		float l = perlin.noise2(x / 160f, y / 160f) * 45f;
		l *= l / 25f;
		l = l < -15f ? -15f : l;
		
		return 67f + h + m - l;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 0.8f ? true : false;
		boolean dirt = false;
		
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
            		if(depth > -1 && depth < 5)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
            			metadata[(y * 16 + x) * 256 + k] = 14;
            		}
            	}
            	else
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			if(perlin.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        				dirt = true;
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        				metadata[(y * 16 + x) * 256 + k] = 1;
	        			}
	        		}
	        		else if(depth < 6 && k > 61)
	        		{
	        			if(dirt)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        			else
	        			{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	            			metadata[(y * 16 + x) * 256 + k] = 14;
	        			}
	        		}
	        		else if(depth < 4)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
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
