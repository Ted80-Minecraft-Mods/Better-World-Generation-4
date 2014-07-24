package bwg4.biomes.realistic;

import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.biomes.RealisticBiome;
import bwg4.deco.DecoBigTree;
import bwg4.deco.DecoGold1;
import bwg4.deco.DecoGrass;
import bwg4.deco.DecoPineTree;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;
import bwg4.util.SnowheightCalculator;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeSnowTaiga extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeSnowTaiga(int id)
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		//boulders
		for (int l = 0; l < 2; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 > 62 && k1 < 95)
			{
		    	(new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}

		//trees
		boolean bush = false;
		float l = perlin.noise2(chunkX / 50f, chunkY / 50f) * 15f + 4f;
		if(l < 0f) { l = 2; bush = true; }
		for (int b1 = 0; b1 < l; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 100)
			{
				WorldGenerator worldgenerator;
				worldgenerator = bush ? new WorldGenShrub(0, 0) : new DecoPineTree(5);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
			else if(z52 < 130)
			{
				WorldGenerator worldgenerator;
				worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : new DecoPineTree(3);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 180f, y / 180f) * 135f;
		h *= h / 40f;
		
		float sh = perlin.noise2(x / 10f, y / 10f) * 4f;
		sh *= h / 20f > 3.75f ? 3.75f : h / 20f;
		h += sh;
		//h -= 20f;
		
		float s = perlin.noise2(x / 180f, y / 180f) * 70;
		s += perlin.noise2(x / 25f, y / 25f) * 9;
		s += perlin.noise2(x / 10f, y / 10f) * 6;
		
		h = h > s ? h : s;
		
		return h + 70f;
	}
	
	@Override
	public void rReplace(int x, int y, Block[] blocks, byte[] metadata, int depth, Random rand, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.1f ? true : false;
		boolean clay = c > 1.9f ? true : false;
		
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
            			if(k > 61)
            			{
                			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
                			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
            			}
            			else
            			{
                			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            			}
	        		}
	        		else if(depth > 0 && depth < 8)
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
		return 0f;
	}
}
