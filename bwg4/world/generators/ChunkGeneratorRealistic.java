package bwg4.world.generators;

import java.util.List;
import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.biomes.RealisticBiome;
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
import net.minecraft.world.gen.MapGenCaves;
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

public class ChunkGeneratorRealistic implements IChunkProvider
{
    private Random rand;
	
    private World worldObj;
    private MapGenCaves caves;
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
	
    public ChunkGeneratorRealistic(World world, long l, boolean m)
    {
    	caves = new MapGenCaves();
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
        //caves.func_151539_a(this, worldObj, cx, cy, blocks);
        
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
    	int p;
    	float[] noise;
    	float noise3;
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			noise = getNoise(wcm, i, j, cx * 16 + j, cy * 16 + i, biomes);
    			for(int k = 0; k < 256; k++)
    			{
    				p = (j * 16 + i) * 256 + k;
    				if(k > noise[0] + noise[1])
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
    				else if(k <= noise[0] + noise[1] * 6 && k >= noise[0] - noise[1] * 6)
    				{
        				noise3 = perlin.noise3((cx * 16 + j) / 20f, (cy * 16 + i) / 20f, k / 20f) + (noise[0] - k) / (noise[1] * 2);
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
    				else if(k < noise[0] - noise[1])
    				{
    					blocks[p] = Blocks.stone;
    				}
    			}
    			n[j * 16 + i] = noise[0];
    		}
    	}
    }
    
    public float[] getNoise(WorldChunkManager wcm, int a, int b, int x, int y, BiomeGenBase biomes[])
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
    	float noise3D = 0f;
    	
    	int k;
    	for(k = 0; k < 256; k++)
    	{
    		if(noiseArray[k] > 0.0f)
    		{
    			noise += ((RealisticBiome)BiomeGenBase.getBiome(k)).rNoise(perlin, x, y) * noiseArray[k];
    		}
    	}
    	
    	for(k = 0; k < 256; k++)
    	{
    		if(noiseArray[k] > 0.0f)
    		{
    			noise3D += ((RealisticBiome)BiomeGenBase.getBiome(k)).r3Dnoise(noise) * noiseArray[k];
    		}
    	}
    	
    	return new float[]{noise, noise3D};
    }

    public void replaceBlocksForBiome(int cx, int cy, Block[] blocks, byte[] metadata, BiomeGenBase biomes[], float[] n)
    {
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			BiomeGenBase biome = biomes[(parabolicSize + i) * biomesForGenLength + (parabolicSize + j)];
    			int depth = -1;
                
    			((RealisticBiome)biome).rReplace(blocks, metadata, cx * 16 + j, cy * 16 + i, i, j, depth, rand, perlin, n);
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
        
		if(rand.nextInt(10) == 0)
		{
			int i2 = x + rand.nextInt(16) + 8;
			int l4 = rand.nextInt(50);
			int i8 = y + rand.nextInt(16) + 8;
			(new OldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
		}
		
		if(rand.nextInt(18) == 0) 
		{
			int j2 = x + rand.nextInt(16) + 8;
			int i5 = rand.nextInt(rand.nextInt(45) + 8);
			int j8 = y + rand.nextInt(16) + 8;
			if(i5 < 64 || rand.nextInt(10) == 0)
			{
				(new OldGenLakes(Blocks.lava)).generate(worldObj, rand, j2, i5, j8);
			}
		} 
		
		for(int k1 = 0; k1 < 8; k1++)
		{
			int j5 = x + rand.nextInt(16) + 8;
			int k8 = rand.nextInt(128);
			int j11 = y + rand.nextInt(16) + 8;
			(new DecoDungeons(0)).generate(worldObj, rand, j5, k8, j11);
		}
		
		for(int j2 = 0; j2 < 10; j2++)
		{
			int l5 = x + rand.nextInt(16);
			int i9 = rand.nextInt(64);
			int l11 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.dirt, 32, 2)).generate(worldObj, rand, l5, i9, l11);
		}

		for(int k2 = 0; k2 < 5; k2++)
		{
			int i6 = x + rand.nextInt(16);
			int j9 = rand.nextInt(64);
			int i12 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.gravel, 32, 2)).generate(worldObj, rand, i6, j9, i12);
		}

		for(int i3 = 0; i3 < 20; i3++)
		{
			int j6 = x + rand.nextInt(16);
			int k9 = rand.nextInt(128);
			int j12 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.coal_ore, 16, 2)).generate(worldObj, rand, j6, k9, j12);
		}

		for(int j3 = 0; j3 < 20; j3++)
		{
			int k6 = x + rand.nextInt(16);
			int l9 = rand.nextInt(64);
			int k12 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.iron_ore, 8, 2)).generate(worldObj, rand, k6, l9, k12);
		}

		for(int k3 = 0; k3 < 2; k3++)
		{
			int l6 = x + rand.nextInt(16);
			int i10 = rand.nextInt(32);
			int l12 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.gold_ore, 8, 2)).generate(worldObj, rand, l6, i10, l12);
		}

		for(int l3 = 0; l3 < 8; l3++)
		{
			int i7 = x + rand.nextInt(16);
			int j10 = rand.nextInt(16);
			int i13 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.redstone_ore, 7, 2)).generate(worldObj, rand, i7, j10, i13);
		}

		for(int i4 = 0; i4 < 1; i4++)
		{
			int j7 = x + rand.nextInt(16);
			int k10 = rand.nextInt(16);
			int j13 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.diamond_ore, 7, 2)).generate(worldObj, rand, j7, k10, j13);
		}

		for(int j4 = 0; j4 < 1; j4++)
		{
			int k7 = x + rand.nextInt(16);
			int l10 = rand.nextInt(16) + rand.nextInt(16);
			int k13 = y + rand.nextInt(16);
			(new OldGenMinable(Blocks.lapis_ore, 6, 2)).generate(worldObj, rand, k7, l10, k13);
		}
		
        for (int g12 = 0; g12 < 4; ++g12)
        {
            int n1 = x + rand.nextInt(16);
            int m1 = rand.nextInt(28) + 4;
            int p1 = y + rand.nextInt(16);

            if (worldObj.getBlock(n1, m1, p1).isReplaceableOreGen(worldObj, n1, m1, p1, Blocks.stone))
            {
            	worldObj.setBlock(n1, m1, p1, Blocks.emerald_ore, 0, 2);
            }
        }
        
        ((RealisticBiome)biomegenbase).rDecorate(this.worldObj, this.rand, x, y, perlin);
        
		for(int l18 = 0; l18 < 50; l18++)
		{
			int l21 = x + rand.nextInt(16) + 8;
			int k23 = rand.nextInt(rand.nextInt(120) + 8);
			int l24 = y + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, l21, k23, l24);
		}

		for(int i19 = 0; i19 < 20; i19++)
		{
			int i22 = x + rand.nextInt(16) + 8;
			int l23 = rand.nextInt(rand.nextInt(rand.nextInt(112) + 8) + 8);
			int i25 = y + rand.nextInt(16) + 8;
			(new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, i22, l23, i25);
		}
        
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, x + 8, y + 8, 16, 16, this.rand);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));

        x += 8;
        y += 8;
		
        for (int sn1 = 0; sn1 < 16; ++sn1)
        {
            for (int sn2 = 0; sn2 < 16; ++sn2)
            {
                int sn3 = worldObj.getPrecipitationHeight(x + sn1, y + sn2);

                if (worldObj.isBlockFreezable(sn1 + x, sn3 - 1, sn2 + y))
                {
                	worldObj.setBlock(sn1 + x, sn3 - 1, sn2 + y, Blocks.ice, 0, 2);
                }

                Block b = worldObj.getBlock(sn1 + x, sn3 - 1, sn2 + y);
                if (worldObj.func_147478_e(sn1 + x, sn3, sn2 + y, false) && b != Blocks.ice && b != Blocks.water && sn3 > 63)
                {
                	if(worldObj.getBlock(sn1 + x, sn3, sn2 + y) != Blocks.snow_layer && b != Blocks.packed_ice)
                	{
                		worldObj.setBlock(sn1 + x, sn3, sn2 + y, Blocks.snow_layer, 0, 2);
                	}
                }
            }
        }
		

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
