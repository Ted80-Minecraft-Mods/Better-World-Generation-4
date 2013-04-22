package ted80.bwg4.layer;

import ted80.bwg4.layer.old.*;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.*;
import net.minecraftforge.event.terraingen.*;

public abstract class BWG4Layer
{
    private long worldGenSeed;
    protected BWG4Layer parent;
    private long chunkSeed;
    private long baseSeed;
	private static String[] generatorSettings;
	private static boolean isRemote = false;

    public static BWG4Layer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType, String genstring, boolean remote)
    {
		if(!isRemote) { isRemote = remote; }
	
		if(par2WorldType == WorldType.BWG4DEFAULT || par2WorldType == WorldType.BWG4LARGE)
		{
			System.out.println("GENSTRING: " + genstring);
			if(genstring.length() > 3)
			{
				String[] splitstring = genstring.split("&");
				generatorSettings = splitstring[1].split(";");
			}
			
			BWG4Layer obj = new BWG4LayerCreate(1L, generatorSettings, isRemote);
			obj = new BWG4LayerFuzzyZoom(2000L, (BWG4Layer)(obj));
			obj = new BWG4LayerAddIsland(1L, (BWG4Layer)(obj));
			obj = new BWG4LayerZoom(2001L, (BWG4Layer)(obj));
			obj = new BWG4LayerAddIsland(2L, (BWG4Layer)(obj));
			obj = new BWG4LayerZoom(2002L, (BWG4Layer)(obj));
			obj = new BWG4LayerAddIsland(3L, (BWG4Layer)(obj));
			obj = new BWG4LayerZoom(2003L, (BWG4Layer)(obj));
			obj = new BWG4LayerAddIsland(4L, (BWG4Layer)(obj));
			byte size = 4;

			if (par2WorldType == WorldType.BWG4LARGE)
			{
				size = 6;
			}
			
			BWG4Layer obj1 = obj;
			obj1 = BWG4LayerZoom.func_75915_a(1000L, ((BWG4Layer)(obj1)), 0);
			obj1 = new BWG4LayerRiverInit(100L, ((BWG4Layer)(obj1)));
			obj1 = BWG4LayerZoom.func_75915_a(1000L, ((BWG4Layer)(obj1)), size + 2);
			obj1 = new BWG4LayerRiver(1L, ((BWG4Layer)(obj1)));
			obj1 = new BWG4LayerSmooth(1000L, ((BWG4Layer)(obj1)));
			BWG4Layer obj2 = obj;
			obj2 = BWG4LayerZoom.func_75915_a(1000L, ((BWG4Layer)(obj2)), 0);
			obj2 = new BWG4LayerBiome(200L, ((BWG4Layer)(obj2)), par2WorldType, generatorSettings, 0, isRemote);
			obj2 = BWG4LayerZoom.func_75915_a(1000L, ((BWG4Layer)(obj2)), 2);
			obj2 = new BWG4LayerHills(1000L, ((BWG4Layer)(obj2)), generatorSettings, isRemote);
			obj2 = new BWG4LayerZoom(1000, ((BWG4Layer)(obj2)));
			obj2 = new BWG4LayerShore(1000L, ((BWG4Layer)(obj2)), 1, generatorSettings, isRemote);
			obj2 = new BWG4LayerShore(1000L, ((BWG4Layer)(obj2)), 2, generatorSettings, isRemote);

			for (int i = 0 + 1; i < size; i++)
			{
				obj2 = new BWG4LayerZoom(1000 + i, ((BWG4Layer)(obj2)));
			}

			obj2 = new BWG4LayerSmooth(1000L, ((BWG4Layer)(obj2)));
			obj2 = new BWG4LayerRiverMix(100L, ((BWG4Layer)(obj2)), ((BWG4Layer)(obj1)));
			BWG4LayerRiverMix bwg4layerrivermix = ((BWG4LayerRiverMix)(obj2));
			BWG4LayerVoronoiZoom genlayervoronoizoom = new BWG4LayerVoronoiZoom(10L, ((BWG4Layer)(obj2)));
			((BWG4Layer)(obj2)).initWorldGenSeed(par0);
			genlayervoronoizoom.initWorldGenSeed(par0);
			return (new BWG4Layer[]
					{
						obj2, genlayervoronoizoom, bwg4layerrivermix
					});		
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

			BWG4Layer var5 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
			var5 = GenLayerZoom.func_75915_a(1000L, var13, var4 + 2);
			GenLayerRiver var14 = new GenLayerRiver(1L, var5);
			GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
			BWG4Layer var6 = GenLayerZoom.func_75915_a(1000L, var16, 0);
			GenLayerBiome var17 = new GenLayerBiome(200L, var6, par2WorldType);
			var6 = GenLayerZoom.func_75915_a(1000L, var17, 2);
			Object var18 = new GenLayerHills(1000L, var6);

			for (int var7 = 0; var7 < var4; ++var7)
			{
				var18 = new GenLayerZoom((long)(1000 + var7), (BWG4Layer)var18);

				if (var7 == 0)
				{
					var18 = new GenLayerAddIsland(3L, (BWG4Layer)var18);
				}

				if (var7 == 1)
				{
					var18 = new GenLayerShore(1000L, (BWG4Layer)var18);
				}

				if (var7 == 1)
				{
					var18 = new GenLayerSwampRivers(1000L, (BWG4Layer)var18);
				}
			}

			GenLayerSmooth var19 = new GenLayerSmooth(1000L, (BWG4Layer)var18);
			GenLayerRiverMix var20 = new GenLayerRiverMix(100L, var19, var15);
			GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
			var20.initWorldGenSeed(par0);
			var8.initWorldGenSeed(par0);
			return new BWG4Layer[] {var20, var8, var20};
		}
    }

    public BWG4Layer(long par1)
    {
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

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

    public abstract int[] getInts(int i, int j, int k, int l);

    public static byte getModdedBiomeSize(WorldType worldType, byte original)
    {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newSize;
    }
}
