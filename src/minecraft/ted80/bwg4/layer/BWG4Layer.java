package ted80.bwg4.layer;

import ted80.bwg4.mod_bwg4;
import ted80.bwg4.generatordata.GeneratorType;
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

    public static BWG4Layer[] initializeAllBiomeGenerators(long par0, String genstring)
    {
		if(genstring.length() > 4)
		{
			String[] splitstring = genstring.split("&");
			generatorSettings = splitstring[1].split(";");
		}
		
		BWG4Layer obj = new BWG4LayerCreate(1L, generatorSettings);
		obj = new BWG4LayerFuzzyZoom(2000L, (BWG4Layer)(obj));
		obj = new BWG4LayerAddIsland(1L, (BWG4Layer)(obj));
		obj = new BWG4LayerZoom(2001L, (BWG4Layer)(obj));
		obj = new BWG4LayerAddIsland(2L, (BWG4Layer)(obj));
		obj = new BWG4LayerZoom(2002L, (BWG4Layer)(obj));
		obj = new BWG4LayerAddIsland(3L, (BWG4Layer)(obj));
		obj = new BWG4LayerZoom(2003L, (BWG4Layer)(obj));
		obj = new BWG4LayerAddIsland(4L, (BWG4Layer)(obj));
		byte size = 4;

		if (GeneratorType.Current == GeneratorType.BWG4LARGE)
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
		obj2 = new BWG4LayerBiome(200L, ((BWG4Layer)(obj2)), generatorSettings, 0);
		obj2 = BWG4LayerZoom.func_75915_a(1000L, ((BWG4Layer)(obj2)), 2);
		obj2 = new BWG4LayerHills(1000L, ((BWG4Layer)(obj2)), generatorSettings);
		obj2 = new BWG4LayerZoom(1000, ((BWG4Layer)(obj2)));
		obj2 = new BWG4LayerShore(1000L, ((BWG4Layer)(obj2)), 1, generatorSettings);
		obj2 = new BWG4LayerShore(1000L, ((BWG4Layer)(obj2)), 2, generatorSettings);

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
