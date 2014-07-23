package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.deco.DecoSurvival;
import bwg4.map.MapGenPlanets;
import bwg4.util.TerrainMath;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkGeneratorPlanetoids implements IChunkProvider
{
    private Random endRNG;
    private World world;
    private double[] densities;
    private BiomeGenBase[] biomesForGeneration;
    int[][] field_73203_h = new int[32][32];
	private boolean water = false;
	private MapGenPlanets mapgenplanets;
	private int dimension;
	
    public ChunkGeneratorPlanetoids(World par1World, long par2, boolean w, int d)
    {
        world = par1World;
        this.endRNG = new Random(par2);
        mapgenplanets = new MapGenPlanets(d);
        water = w;
        dimension = d;
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.endRNG.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);

        Block[] var3 = new Block[32768];
        
        if(water)
        {
			for(int i = 0; i < 16; i++)
			{
				for(int j = 0; j < 16; j++)
				{
					for(int k = 0; k < 5; k++)
					{
						if(k == 0)
						{
							var3[i * 16 * 128 + j * 128 + k] = Blocks.bedrock;
						}
						else
						{
							if(dimension == 0)
							{
								var3[i * 16 * 128 + j * 128 + k] = Blocks.water;
							}
							else
							{
								if(k == 4)
								{
									var3[i * 16 * 128 + j * 128 + k] = Blocks.flowing_lava;
								}
								else
								{
									var3[i * 16 * 128 + j * 128 + k] = Blocks.lava;
								}
							}
						}
					}
				}
			}
        }
        
        mapgenplanets.generate(this, world, par1, par2, var3);
        
        Chunk var4 = new Chunk(world, var3, par1, par2);
        byte[] var5 = var4.getBiomeArray();
		this.biomesForGeneration = world.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }
		
        var4.generateSkylightMap();
        return var4;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        BiomeGenBase var6 = world.getBiomeGenForCoords(var4 + 16, var5 + 16);
        
        SpawnerAnimals.performWorldGenSpawning(this.world, var6, var4 + 8, var5 + 8, 16, 16, this.world.rand); 
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }
	
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.world.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2) {}
}
