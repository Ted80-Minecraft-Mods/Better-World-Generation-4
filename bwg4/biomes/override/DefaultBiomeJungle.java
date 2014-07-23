package bwg4.biomes.override;

import java.util.Random;

import bwg4.biomes.DefaultBiome;
import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

public class DefaultBiomeJungle extends BiomeGenJungle implements DefaultBiome
{
	public DefaultBiomeJungle(int id)
	{
		super(id, false);
	}

	@Override
	public void bwg4Decorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		for(int l14 = 0; l14 < 10; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenTallGrass(Blocks.tallgrass, 2)).generate(world, rand, l19, k22, j24);
		}
	}

	@Override
	public float getNoise(PerlinNoise perlin, int x, int y) 
	{
		return 75f;
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

        		if(depth == 0)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
        		}
        		else if(depth < 4)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        		}
            }
		}
	}
}
