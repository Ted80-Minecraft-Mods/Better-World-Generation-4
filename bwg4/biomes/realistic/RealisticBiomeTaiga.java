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

public class RealisticBiomeTaiga extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeTaiga(int id)
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
			if(k1 < 95)
			{
		    	(new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
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
		
		//pumpkin
		if(rand.nextInt(28) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		//flowers
		if(rand.nextInt(2) == 0)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenFlowers(Blocks.red_flower)).generate(world, rand, j15, j17, j20);
		}
		
		//mushroom
		if(rand.nextInt(4) == 0)
		{
			int k15 = chunkX + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(128);
			int k20 = chunkY + rand.nextInt(16) + 8;
			
			if(rand.nextBoolean())
			{
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(world, rand, k15, k17, k20);
			}
			else
			{
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(world, rand, k15, k17, k20);
			}
		}

		//grass
		for(int l14 = 0; l14 < 15; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(k22 < 78 && rand.nextInt(2) == 0)
			{
				(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
			}
			else
			{
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
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
		
		if(h < 30f)
		{
			if(h < 0f)
			{
				h = 0f;
			}
			
			h += perlin.noise2(x / 100f, y / 100f) * (30f - h) * 1f + perlin.noise2(x / 15f, y / 15f) * (30f - h) * 0.1f;
		}
		
		return h + 64f;
	}
	
	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.1f ? true : false;
		boolean clay = c > 1.9f ? true : false;
		boolean snow = false;
		boolean gravel = false;
		
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            	snow = false;
            	gravel = false;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

            	if(!cliff)
            	{
            		if(!snow && depth == 0 && k > 130f)
            		{
            			if((k < 136 && rand.nextInt(135 - k + 1) == 0) || k > 135)
            			{
            				snow = true;
    	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
    	        			if(k < 254)
    	        			{
    	        				blocks[(y * 16 + x) * 256 + k + 1] = Blocks.snow_layer;
    	        			}
            			}
            		}
            		else if(snow && depth > 0 && depth < 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else if(depth == 0)
	        		{
            			if(k > 63)
            			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
            			}
            			else if(rand.nextInt(20) == 0)
            			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.mossy_cobblestone;
            			}
            			else if(k < 63)
            			{
            				gravel = true;
            				blocks[(y * 16 + x) * 256 + k] = Blocks.water;
            			}
            			else
            			{
            				gravel = true;
            				blocks[(y * 16 + x) * 256 + k] = Blocks.air;
            			}
	        		}
	        		else if(depth > 0 && depth < 4)
	        		{
	        			if(k < 64 && gravel)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
	        			}
	        			else
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        		}
            	}
            	else
            	{
	        		if(depth > -1 && depth < 6)
	        		{
            			if(depth == 0 && k > 130f && ((k < 136 && rand.nextInt(135 - k + 1) == 0) || k > 135))
            			{
            				snow = true;    	        			
            				if(k < 254)
    	        			{
    	        				blocks[(y * 16 + x) * 256 + k + 1] = Blocks.snow_layer;
    	        			}
            			}

            			if(snow && k > 160f && ((k < 166 && rand.nextInt(165 - k + 1) == 0) || k > 165))
            			{
    	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            			}
            			else
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
            	}
            }
		}
	}

	@Override
	public float r3Dnoise(float z) 
	{
		float n = z > 120f ? 0f : (120f - z) / 6f;
		return n > 7f ? 7f : n;
	}
}
