package bwg4.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import bwg4.biomes.RealisticBiome;
import bwg4.deco.DecoJungleTree;
import bwg4.deco.DecoPineTree;
import bwg4.util.CliffCalculator;
import bwg4.util.PerlinNoise;

public class RealisticBiomeJungleHills extends BiomeGenBase implements RealisticBiome
{

	public RealisticBiomeJungleHills(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		for (int b1 = 0; b1 < 1; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			WorldGenerator worldgenerator;
			worldgenerator = new DecoJungleTree();
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
	}
	
    public WorldGenAbstractTree randomTree(Random rand)
    {
    	if(rand.nextInt(10) == 0)
    	{
    		return worldGeneratorBigTree;
    	}
    	else if(rand.nextInt(2) == 0)
    	{
    		return new WorldGenShrub(3, 0);
    	}
    	else if(rand.nextInt(3) == 0)
    	{
    		return new WorldGenMegaJungle(false, 10, 20, 3, 3);
    	}
    	return new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
    }

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 160f, y / 160f) * 115f;
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
		
		return 69 + h;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.7f ? true : false;
		
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

            	if(depth > -1 && depth < 6)
            	{
            		if(cliff)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else
            		{
            			if(depth == 0)
            			{
                			blocks[(y * 16 + x) * 256 + k] = Blocks.grass; 
            			}
            			else
            			{
                			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt; 
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
