package bwg4.biomes;

import java.util.Random;

import bwg4.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface DefaultBiome
{
    public void bwg4Decorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin);
    public float getNoise(PerlinNoise perlin, int x, int y);
    public void replaceBlocksForBiome(int x, int y, Block[] blocks, byte[] metadata, int depth, Random rand, float[] noise);
}
