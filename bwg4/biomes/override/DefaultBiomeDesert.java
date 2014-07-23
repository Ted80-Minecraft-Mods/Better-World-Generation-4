package bwg4.biomes.override;

import java.util.Random;

import bwg4.biomes.DefaultBiome;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class DefaultBiomeDesert extends BiomeGenDesert implements DefaultBiome
{
	public DefaultBiomeDesert(int id)
	{
		super(id);
	}

	@Override
	public void bwg4Decorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
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
	public float getNoise(PerlinNoise perlin, int x, int y) 
	{
		return (perlin.noise2(x / 60f, y / 60f) * 20) + (perlin.noise2(x / 20f, y / 20f) * 8) + 80;
	}

	@Override
	public void replaceBlocksForBiome(int x, int y, Block[] blocks, byte[] metadata, int depth, Random rand, float[] noise) 
	{
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
