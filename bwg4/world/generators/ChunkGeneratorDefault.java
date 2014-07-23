package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.biomes.DefaultBiome;
import bwg4.deco.DecoDungeons;
import bwg4.deco.old.OldGenClay;
import bwg4.deco.old.OldGenLakes;
import bwg4.deco.old.OldGenMinable;
import bwg4.deco.old.OldGenReed;
import bwg4.map.MapGenBWG4;
import bwg4.map.MapGenBWG4Caves;
import bwg4.noise.NoiseOctavesBeta;
import bwg4.util.PerlinNoise;
import bwg4.util.TerrainMath;
import bwg4.world.ChunkManagerOld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkGeneratorDefault implements IChunkProvider
{
    private Random rand;
	
    private World worldObj;
    private MapGenBWG4 field_902_u;
    private final boolean mapFeaturesEnabled;
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    
    private BiomeGenBase biomesForGeneration[];
    private final int biomesForGenLength;
    
    private PerlinNoise perlin;
    
    private final int parabolicSize = 16;
    private final int parabolicJump = 1; //not working right now
    private final int parabolicArraySize;
    private final float[] parabolicField;
    private float parabolicFieldTotal;
	
    public ChunkGeneratorDefault(World world, long l, boolean m)
    {
        field_902_u = new MapGenBWG4Caves();
        worldObj = world;
        rand = new Random(l);
        perlin = new PerlinNoise(l);
        mapFeaturesEnabled = m;

        parabolicArraySize = parabolicSize * 2 + 1;
        biomesForGenLength = parabolicArraySize + 15;
        parabolicField = new float[parabolicArraySize * parabolicArraySize];
        for (int j = -parabolicSize; j <= parabolicSize; ++j)
        {
            for (int k = -parabolicSize; k <= parabolicSize; ++k)
            {
                float f = 0.445f / MathHelper.sqrt_float((float)((j * parabolicJump) * (j * parabolicJump) + (k * parabolicJump) * (k * parabolicJump)) + 0.2F);
                parabolicField[j + parabolicSize + (k + parabolicSize) * parabolicArraySize] = f;
                parabolicFieldTotal += f;
            }
        }
    }

    public Chunk provideChunk(int cx, int cy)
    {
    	rand.setSeed((long)cx * 0x4f9939f508L + (long)cy * 0x1ef1565bd5L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        float[] noise = new float[256];
        WorldChunkManager wcm = worldObj.getWorldChunkManager();
        biomesForGeneration = wcm.loadBlockGeneratorData(biomesForGeneration, cx * 16 - parabolicSize, cy * 16 - parabolicSize, biomesForGenLength, biomesForGenLength);
    	
        generateTerrain(wcm, cx, cy, blocks, metadata, biomesForGeneration, noise);
        replaceBlocksForBiome(cx, cy, blocks, metadata, biomesForGeneration, noise);
        //field_902_u.generate(this, worldObj, cx, cy, blocks);
        
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
        }
        
        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.biomesForGeneration[((k >> 4) + parabolicSize) * biomesForGenLength + ((k & 0xf) + parabolicSize)].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }
    
    public void generateTerrain(WorldChunkManager wcm, int cx, int cy, Block[] blocks, byte[] metadata, BiomeGenBase biomes[], float[] n)
    {	
    	float noise3Dstength = 10f;
    	
    	int p;
    	float noise;
    	float noise3;
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			noise = getNoise(wcm, i, j, cx * 16 + j, cy * 16 + i, biomes);
    			for(int k = 0; k < 256; k++)
    			{
    				p = (j * 16 + i) * 256 + k;
    				if(k > noise + noise3Dstength)
    				{
    					if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else if(k <= noise + noise3Dstength && k >= noise - noise3Dstength)
    				{
        				noise3 = perlin.noise3((cx * 16 + j) / 20f, (cy * 16 + i) / 20f, k / 20f) + (noise - k) / (noise3Dstength * 2);
        				if(noise3 > 0f)
        				{
        					blocks[p] = Blocks.stone;
        				}
        				else if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else if(k < noise - noise3Dstength)
    				{
    					blocks[p] = Blocks.gold_block;
    				}
    			}
    			n[j * 16 + i] = noise;
    		}
    	}
    }
    
    public float getNoise(WorldChunkManager wcm, int a, int b, int x, int y, BiomeGenBase biomes[])
    {
    	float[] noiseArray = new float[256];
    	for(int i = -parabolicSize; i <= parabolicSize; i++)
    	{
        	for(int j = -parabolicSize; j <= parabolicSize; j++)
        	{
        		noiseArray[biomes[(parabolicSize + a + i) * biomesForGenLength + (parabolicSize + b + j)].biomeID] += parabolicField[i + parabolicSize + (j + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
        	}
    	}
    	
    	float noise = 0f;
    	for(int k = 0; k < 256; k++)
    	{
    		if(noiseArray[k] > 0.0f)
    		{
    			noise += ((DefaultBiome)BiomeGenBase.getBiome(k)).getNoise(perlin, x, y) * noiseArray[k];
    		}
    	}
    	
    	return noise;
    }

    public void replaceBlocksForBiome(int cx, int cy, Block[] blocks, byte[] metadata, BiomeGenBase biomes[], float[] n)
    {
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			BiomeGenBase biome = biomes[(parabolicSize + i) * biomesForGenLength + (parabolicSize + j)];
    			int depth = -1;
                
    			((DefaultBiome)biome).replaceBlocksForBiome(i, j, blocks, metadata, depth, rand, n);
    			
    			/*
    			for(int k = 255; k > -1; k--)
    			{
    				Block b = blocks[(j * 16 + i) * 256 + k];
                    if(b == Blocks.air)
                    {
                    	depth = -1;
                    }
                    else if(b == Blocks.stone)
                    {
                    	depth++;
                    	blocks[(j * 16 + i) * 256 + k] = ((DefaultBiome)biome).replaceBlocksForBiome(i, k, j, depth, rand, n);
                    	
                    	depth++;
                    	if(depth == 0)
                    	{
                    		blocks[(j * 16 + i) * 256 + k] = top;
                    	}
                    	else if(depth < 4)
                    	{
                    		blocks[(j * 16 + i) * 256 + k] = filler;
                    	}
                    }
    			}*/
    		}
    	}
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
    	return null;
    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockFalling.fallInstantly = true;
        int x = i * 16;
        int y = j * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(x + 16, y + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
        boolean flag = false;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));

        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            this.strongholdGenerator.generateStructuresInChunk(worldObj, rand, i, j);
        }
        
        ((DefaultBiome)biomegenbase).bwg4Decorate(this.worldObj, this.rand, x, y, perlin);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, y + 8, 16, 16, this.rand);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));

        BlockFalling.fallInstantly = false;
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
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }
	
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(par1World, par3, par4, par5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }
    
    public void saveExtraData() {}

    public void recreateStructures(int par1, int par2)
    {
		if (mapFeaturesEnabled)
        {
			strongholdGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
			mineshaftGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
		}	
	}
}
