package bwg4.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bwg4.api.BiomeList;
import bwg4.api.BiomeManager;
import bwg4.biomes.RealisticBiome;
import bwg4.generatortype.GeneratorType;
import bwg4.noise.OldNoiseGeneratorOctaves2;
import bwg4.util.PerlinNoise;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;

public class ChunkManagerRealistic extends WorldChunkManager
{
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    private PerlinNoise perlin;
    private int biomeLength;
    private boolean remote = false;
    
    private static int biomeLookupTable[] = new int[4096];	
	
	protected ChunkManagerRealistic()
	{
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
	}

    public ChunkManagerRealistic(World par1World, boolean r)
    {
        this();
        long seed = par1World.getSeed();
    	perlin = new PerlinNoise(seed);
        
        if(r)
        {
			for(int i = 0; i < 64; i++)
			{
				for(int j = 0; j < 64; j++)
				{
					biomeLookupTable[i + j * 64] = getBiomeByTempHum((float)i / 63F, (float)j / 63F);
				}
			}
        	remote = true;
        }
    }    
    
    public static int getBiomeFromLookup(float d, float d1)
    {
        int i = (int)(d * 63D);
        int j = (int)(d1 * 63D);
        return biomeLookupTable[i + j * 64];
    }
    
    public int getBiomeByTempHum(float t, float h) 
    {
    	return BiomeList.REALISTICsavannahField.biomeID;
    }
	
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {	
    	int[] d = new int[par3 * par4];
    	if(remote)
    	{	
    		for(int i = 0; i < par3; i++)
    		{
    			for(int j = 0; j < par4; j++)
	    		{
	    			d[j * par3 + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
	    		}
    		}
    	}
    	else
    	{
	    	for(int j = 0; j < par3 * par4; j++)
	    	{
	    		//TODO wtf?
	    		d[j] = 1;
	    	}
    	}
    	return d;
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
    	float temp = 0.5f + perlin.noise2(par1 / 500f, par2 / 500f);
    	float hum = 0.5f + perlin.noise2(par1 / 500f, par2 / 500f);
    	
    	temp = temp > 1f ? 1f : temp < 0f ? 0f : temp;
    	hum = hum > 1f ? 1f : hum < 0f ? 0f : hum;
    	
        return BiomeGenBase.getBiome(getBiomeFromLookup(temp, hum));
    }
    
    public float getNoiseAt(int x, int y)
    {
    	return ((RealisticBiome)getBiomeGenAt(x, y)).rNoise(perlin, x, y);
    }
    
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.getBiome(var6[var7]).getIntRainfall() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }

    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
    	return false;
    }

    public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_)
    {
    	return null;
    }

    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
