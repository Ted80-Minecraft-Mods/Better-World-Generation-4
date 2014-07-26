package bwg4.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import bwg4.biomes.RealisticBiome;
import bwg4.util.PerlinNoise;

public class RealisticBiomeJungleRivers extends BiomeGenBase implements RealisticBiome
{

	public RealisticBiomeJungleRivers(int p_i1971_1_) {
		super(p_i1971_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY,
			PerlinNoise perlin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float r3Dnoise(float z) {
		// TODO Auto-generated method stub
		return 0;
	}

}
