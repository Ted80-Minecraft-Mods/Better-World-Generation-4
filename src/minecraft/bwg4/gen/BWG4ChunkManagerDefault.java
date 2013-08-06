package bwg4.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bwg4.mod_bwg4;
import bwg4.api.DefaultBiomeList;
import bwg4.biomes.BWG4Biomes;
import bwg4.generatordata.BWG4DecodeGeneratorString;
import bwg4.generatordata.BWG4GeneratorType;
import bwg4.layer.BWG4Layer;
import bwg4.noise.BWG4oldNoiseGeneratorOctaves2;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;

public class BWG4ChunkManagerDefault extends WorldChunkManager
{
    private BWG4Layer genBiomes;
    private BWG4Layer biomeIndexLayer;
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;
	
	protected BWG4ChunkManagerDefault()
	{
        this.biomeCache = new BiomeCache(this);
		
		//spawn list
        this.biomesToSpawnIn = new ArrayList();
		
		//default spawn biomes
        this.biomesToSpawnIn.add(BiomeGenBase.forest);
        this.biomesToSpawnIn.add(BiomeGenBase.plains);
        this.biomesToSpawnIn.add(BiomeGenBase.beach);
		
		//bwg4 spawn biomes
        this.biomesToSpawnIn.add(BWG4Biomes.BDtropicalisland);
        this.biomesToSpawnIn.add(BWG4Biomes.BDjungleisland);
        this.biomesToSpawnIn.add(BWG4Biomes.BDmushroomisland);
        this.biomesToSpawnIn.add(BWG4Biomes.BDbeach);
        this.biomesToSpawnIn.add(BWG4Biomes.BDbeachDunes); 
        this.biomesToSpawnIn.add(BWG4Biomes.BDsnowpines);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsnowforest);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsnowtaiga);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsnowplains);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsnowhills);
        this.biomesToSpawnIn.add(BWG4Biomes.BDplains);
        this.biomesToSpawnIn.add(BWG4Biomes.BDforest);
        this.biomesToSpawnIn.add(BWG4Biomes.BDforesthills);
        this.biomesToSpawnIn.add(BWG4Biomes.BDforestlakes);
        this.biomesToSpawnIn.add(BWG4Biomes.BDpines);
        this.biomesToSpawnIn.add(BWG4Biomes.BDtaiga);
        this.biomesToSpawnIn.add(BWG4Biomes.BDgrassland);
        this.biomesToSpawnIn.add(BWG4Biomes.BDrainforest);
        this.biomesToSpawnIn.add(BWG4Biomes.BDjungle);
        this.biomesToSpawnIn.add(BWG4Biomes.BDswampland);
        this.biomesToSpawnIn.add(BWG4Biomes.BDdesert);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsavanna);
        this.biomesToSpawnIn.add(BWG4Biomes.BDsavannaforest);
        this.biomesToSpawnIn.add(BWG4Biomes.BDshrubland);
	}

    public BWG4ChunkManagerDefault(World par1World, boolean remote)
    {
        this();
        long seed = par1World.getSeed();

        if(BWG4GeneratorType.generatorinfo == null)
        {
        	BWG4GeneratorType.generatorinfo = DefaultBiomeList.getDefaultString();
        }
        BWG4Layer[] var4 = BWG4Layer.initializeAllBiomeGenerators(seed, BWG4GeneratorType.generatorinfo);
        this.genBiomes = (BWG4Layer) var4[0];
        this.biomeIndexLayer = (BWG4Layer) var4[1];
    }    

    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomeCache.getBiomeGenAt(par1, par2);
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }

    /**
     * Return an adjusted version of a given temperature based on the y height
     */
    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    /**
     * Returns a list of temperatures to use for the specified blocks.  Args: listToReuse, x, y, width, length
     */
    public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

		int var6[] = genBiomes.getInts(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            par1ArrayOfBiomeGenBase[var7] = BiomeGenBase.biomeList[var6[var7]];
        }

        return par1ArrayOfBiomeGenBase;
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
     * WorldChunkManager Args: oldBiomeList, x, z, width, depth
     */
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
			int var7[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

            for (int var8 = 0; var8 < par4 * par5; ++var8)
            {
                par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.biomeList[var7[var8]];
            }

            return par1ArrayOfBiomeGenBase;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        IntCache.resetIntCache();
        int var5 = par1 - par3 >> 2;
        int var6 = par2 - par3 >> 2;
        int var7 = par1 + par3 >> 2;
        int var8 = par2 + par3 >> 2;
        int var9 = var7 - var5 + 1;
        int var10 = var8 - var6 + 1;
		
		int var11[] = genBiomes.getInts(var5, var6, var9, var10);

        for (int var12 = 0; var12 < var9 * var10; ++var12)
        {
            BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];

            if (!par4List.contains(var13))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
     * Strongly favors positive y positions.
     */
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        IntCache.resetIntCache();
        int var6 = par1 - par3 >> 2;
        int var7 = par2 - par3 >> 2;
        int var8 = par1 + par3 >> 2;
        int var9 = par2 + par3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
		
		int var12[] = genBiomes.getInts(var6, var7, var10, var11);
		
        ChunkPosition var13 = null;
        int var14 = 0;

        for (int var15 = 0; var15 < var10 * var11; ++var15)
        {
            int var16 = var6 + var15 % var10 << 2;
            int var17 = var7 + var15 / var10 << 2;
            BiomeGenBase var18 = BiomeGenBase.biomeList[var12[var15]];

            if (par4List.contains(var18) && (var13 == null || par5Random.nextInt(var14 + 1) == 0))
            {
                var13 = new ChunkPosition(var16, 0, var17);
                ++var14;
            }
        }

        return var13;
    }

    /**
     * Calls the WorldChunkManager's biomeCache.cleanupCache()
     */
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
