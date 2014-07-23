package bwg4.biomes.override;

import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.biomes.DefaultBiome;
import bwg4.deco.DecoBigTree;
import bwg4.deco.DecoGold1;
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
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DefaultBiomePlains extends BiomeGenTaiga implements DefaultBiome
{
	public DefaultBiomePlains(int id)
	{
		super(id, 0);
	}

	@Override
	public void bwg4Decorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		float l = perlin.noise2(chunkX / 50f, chunkY / 50f) * 12f + 4f;
		for (int b1 = 0; b1 < l; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			
			if(z52 < 125)
			{
				WorldGenerator worldgenerator = randomTree(rand, z52);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int l14 = 0; l14 < 10; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenTallGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
	}
	
	public WorldGenerator randomTree(Random par1Random, int height)
	{
		return new DecoPineTree(4); 
	}

	@Override
	public float getNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 180f, y / 180f) * 135f;// + perlin.noise2(x / 12f, y / 12f) * 2f;
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
			
			h += perlin.noise2(x / 50f, y / 50f) * (30f - h) * 0.7f + perlin.noise2(x / 15f, y / 15f) * (30f - h) * 0.2f;
		}
		
		return h + 64f;
	}
	
	@Override
	public void replaceBlocksForBiome(int x, int y, Block[] blocks, byte[] metadata, int depth, Random rand, float[] noise) 
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
            				blocks[(y * 16 + x) * 256 + k] = Blocks.water;
            			}
            			else
            			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.air;
            			}
	        		}
	        		else if(depth > 0 && depth < 4)
	        		{
	        			if(k < 64 && gravel) //GRAVELLLLLLLLLLLLLLLLLLLLLLLLLLLLL
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
            					blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            				}
            			}
	        		}
            	}
            }
		}
	}
}
