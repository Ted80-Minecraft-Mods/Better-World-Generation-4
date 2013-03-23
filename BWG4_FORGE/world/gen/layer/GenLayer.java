package net.minecraft.world.gen.layer;

import net.minecraft.world.WorldType;

import net.minecraftforge.common.*;
import net.minecraftforge.event.terraingen.*;

public abstract class GenLayer
{
    private long worldGenSeed;
    protected GenLayer parent;
    private long chunkSeed;
    private long baseSeed;
	private static String[] generatorSettings;
	private static boolean isRemote = false;

    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType, String genstring, boolean remote)
    {
		if(!isRemote) { isRemote = remote; }
	
		if(par2WorldType == WorldType.BWG4GOLD)
		{
			GenLayer obj = new BWG4LayerCreate(1L);
			obj = new GenLayerZoom(2000L, (GenLayer)(obj));
			obj = new GenLayerZoom(2001L, (GenLayer)(obj));
			obj = new GenLayerZoom(2002L, (GenLayer)(obj));
			obj = new BWG4LayerRegion(1L, (GenLayer)(obj));
			obj = new BWG4LayerPack(2L, (GenLayer)(obj), 1);
			obj = new GenLayerZoom(2003L, (GenLayer)(obj));
			byte size = 4;
			
			/* GenLayer obj1 = obj;
			obj1 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj1)), 0);
			obj1 = new GenLayerRiverInit(100L, ((GenLayer)(obj1)));
			obj1 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj1)), size + 2);
			obj1 = new GenLayerRiver(1L, ((GenLayer)(obj1)));
			obj1 = new GenLayerSmooth(1000L, ((GenLayer)(obj1))); */
			GenLayer obj2 = obj;
			obj2 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj2)), 0);
			obj2 = new BWG4LayerBiome(200L, ((GenLayer)(obj2)), par2WorldType, generatorSettings, 1, false);
			obj2 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj2)), 2);
			obj2 = new BWG4LayerSmall(1000L, ((GenLayer)(obj2)));
			obj2 = new BWG4LayerBorder(1000L, ((GenLayer)(obj2)));
			obj2 = new GenLayerZoom(1000, ((GenLayer)(obj2)));
			//obj2 = new BWG4LayerShore(1000L, ((GenLayer)(obj2)));

			for (int i = 0 + 1; i < size; i++)
			{
				obj2 = new GenLayerZoom(1000 + i, ((GenLayer)(obj2)));
			}

			obj2 = new GenLayerSmooth(1000L, ((GenLayer)(obj2)));
			// obj2 = new BWG3LayerRiverMix(100L, ((GenLayer)(obj2)), ((GenLayer)(obj1)));
			// BWG3LayerRiverMix bwg3layerrivermix = ((BWG3LayerRiverMix)(obj2));
			GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, ((GenLayer)(obj2)));
			((GenLayer)(obj2)).initWorldGenSeed(par0);
			genlayervoronoizoom.initWorldGenSeed(par0);
			return (new GenLayer[]
					{
						obj2, genlayervoronoizoom//, bwg3layerrivermix
					});		
		}
		else if(par2WorldType == WorldType.BWG4DEFAULT || par2WorldType == WorldType.BWG4LARGE)
		{
			if(genstring.length() > 3)
			{
				String[] splitstring = genstring.split("&");
				//System.out.println(splitstring[1]);
				generatorSettings = splitstring[1].split(";");
			}
			
			GenLayer obj = new BWG4LayerCreate(1L, generatorSettings, isRemote);
			obj = new GenLayerFuzzyZoom(2000L, (GenLayer)(obj));
			obj = new GenLayerAddIsland(1L, (GenLayer)(obj));
			obj = new GenLayerZoom(2001L, (GenLayer)(obj));
			obj = new GenLayerAddIsland(2L, (GenLayer)(obj));
			obj = new GenLayerZoom(2002L, (GenLayer)(obj));
			obj = new GenLayerAddIsland(3L, (GenLayer)(obj));
			obj = new GenLayerZoom(2003L, (GenLayer)(obj));
			obj = new GenLayerAddIsland(4L, (GenLayer)(obj));
			byte size = 4;

			if (par2WorldType == WorldType.BWG4LARGE)
			{
				size = 6;
			}
			
			GenLayer obj1 = obj;
			obj1 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj1)), 0);
			obj1 = new GenLayerRiverInit(100L, ((GenLayer)(obj1)));
			obj1 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj1)), size + 2);
			obj1 = new GenLayerRiver(1L, ((GenLayer)(obj1)));
			obj1 = new GenLayerSmooth(1000L, ((GenLayer)(obj1)));
			GenLayer obj2 = obj;
			obj2 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj2)), 0);
			obj2 = new BWG4LayerBiome(200L, ((GenLayer)(obj2)), par2WorldType, generatorSettings, 0, isRemote);
			obj2 = GenLayerZoom.func_75915_a(1000L, ((GenLayer)(obj2)), 2);
			obj2 = new BWG4LayerHills(1000L, ((GenLayer)(obj2)), generatorSettings, isRemote);
			obj2 = new GenLayerZoom(1000, ((GenLayer)(obj2)));
			obj2 = new BWG4LayerShore(1000L, ((GenLayer)(obj2)), 1, generatorSettings, isRemote);
			obj2 = new BWG4LayerShore(1000L, ((GenLayer)(obj2)), 2, generatorSettings, isRemote);

			for (int i = 0 + 1; i < size; i++)
			{
				obj2 = new GenLayerZoom(1000 + i, ((GenLayer)(obj2)));
			}

			obj2 = new GenLayerSmooth(1000L, ((GenLayer)(obj2)));
			obj2 = new BWG4LayerRiverMix(100L, ((GenLayer)(obj2)), ((GenLayer)(obj1)));
			BWG4LayerRiverMix bwg4layerrivermix = ((BWG4LayerRiverMix)(obj2));
			GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, ((GenLayer)(obj2)));
			((GenLayer)(obj2)).initWorldGenSeed(par0);
			genlayervoronoizoom.initWorldGenSeed(par0);
			return (new GenLayer[]
					{
						obj2, genlayervoronoizoom, bwg4layerrivermix
					});		
		
			// GenLayerIsland var3 = new GenLayerIsland(1L);
			// GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
			// GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
			// GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
			// var10 = new GenLayerAddIsland(2L, var11);
			// GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
			// var11 = new GenLayerZoom(2002L, var12);
			// var10 = new GenLayerAddIsland(3L, var11);
			// var11 = new GenLayerZoom(2003L, var10);
			// var10 = new GenLayerAddIsland(4L, var11);
			// GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
			// byte var4 = 4;

			// if (par2WorldType == WorldType.BWG4LARGE)
			// {
				// var4 = 6;
			// }

			// GenLayer var5 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			// GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
			// var5 = GenLayerZoom.func_75915_a(1000L, var13, var4 + 2);
			// GenLayerRiver var14 = new GenLayerRiver(1L, var5);
			// GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
			// GenLayer var6 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			// BWG4LayerBiome var17 = new BWG4LayerBiome(200L, var6, par2WorldType, generatorSettings, 0, isRemote);
			// var6 = GenLayerZoom.func_75915_a(1000L, var17, 2);
			// Object var18 = new BWG4LayerHills(1000L, var6);
			// var18 = new BWG4LayerShore(1000L, (GenLayer)var18);

			// for (int var7 = 0; var7 < var4; ++var7)
			// {
				// var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

				// if (var7 == 0)
				// {
					// var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
				// }

				// if (var7 == 1)
				// {
					// var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
				// }
			// }

			// GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
			// BWG4LayerRiverMix var20 = new BWG4LayerRiverMix(100L, var19, var15);
			// GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
			// var20.initWorldGenSeed(par0);
			// var8.initWorldGenSeed(par0);
			// return new GenLayer[] {var20, var8, var20};
		}
		else
		{
			GenLayerIsland var3 = new GenLayerIsland(1L);
			GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
			GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
			GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
			var10 = new GenLayerAddIsland(2L, var11);
			GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
			var11 = new GenLayerZoom(2002L, var12);
			var10 = new GenLayerAddIsland(3L, var11);
			var11 = new GenLayerZoom(2003L, var10);
			var10 = new GenLayerAddIsland(4L, var11);
			GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
			byte var4 = 4;

			if (par2WorldType == WorldType.LARGE_BIOMES)
			{
				var4 = 6;
			}

			GenLayer var5 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
			var5 = GenLayerZoom.func_75915_a(1000L, var13, var4 + 2);
			GenLayerRiver var14 = new GenLayerRiver(1L, var5);
			GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
			GenLayer var6 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			GenLayerBiome var17 = new GenLayerBiome(200L, var6, par2WorldType);
			var6 = GenLayerZoom.func_75915_a(1000L, var17, 2);
			Object var18 = new GenLayerHills(1000L, var6);

			for (int var7 = 0; var7 < var4; ++var7)
			{
				var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);

				if (var7 == 0)
				{
					var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
				}

				if (var7 == 1)
				{
					var18 = new GenLayerShore(1000L, (GenLayer)var18);
				}

				if (var7 == 1)
				{
					var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
				}
			}

			GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
			GenLayerRiverMix var20 = new GenLayerRiverMix(100L, var19, var15);
			GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
			var20.initWorldGenSeed(par0);
			var8.initWorldGenSeed(par0);
			return new GenLayer[] {var20, var8, var20};
		}	
    }

    public GenLayer(long par1)
    {
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        this.worldGenSeed = par1;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and the (x,z) chunk coordinates.
     */
    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    protected int nextInt(int par1)
    {
        int j = (int)((this.chunkSeed >> 24) % (long)par1);

        if (j < 0)
        {
            j += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return j;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public abstract int[] getInts(int i, int j, int k, int l);

    public static byte getModdedBiomeSize(WorldType worldType, byte original)
    {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newSize;
    }
}
