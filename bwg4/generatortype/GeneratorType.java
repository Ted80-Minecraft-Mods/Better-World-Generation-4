package bwg4.generatortype;

import bwg4.api.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.support.Support;
import bwg4.support.SupportBopHell;
import bwg4.world.ProviderBWG4;
import bwg4.world.ProviderBWG4Hell;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class GeneratorType 
{
	public static GeneratorType currentGenerator;
	public static String biomestring;
	public static int[] currentSettings;
	
	public static long seed;
	public static String genString;
	
	public static final GeneratorType[] generatortypes = new GeneratorType[40];
	public static final GeneratorType DEFAULT = new GeneratorTypeDefault(0, 0, "DEFAULT", false);
	public static final GeneratorType FLAT = new GeneratorType(1, 0, "EXTRFLAT", false);
	public static final GeneratorType BETA173 = new GeneratorTypeBeta(2, 1, "BETA173", true);
	public static final GeneratorType ALPHA12 = new GeneratorTypeAlpha12(3, 1, "ALPHA12", true);
	public static final GeneratorType ALPHA11 = new GeneratorTypeAlpha11(4, 1, "ALPHA11", true);
	public static final GeneratorType INFDEV = new GeneratorTypeInfdev(5, 1, "INFDEV", true);
	public static final GeneratorType INDEV = new GeneratorTypeIndev(6, 1, "INDEV", true);
	public static final GeneratorType WASTELAND = new GeneratorTypeWasteland(7, 2, "WASTELAND", false);
	public static final GeneratorType ISLAND = new GeneratorTypeIsland(8, 2, "ISLAND", true);
	public static final GeneratorType SKYISLAND = new GeneratorTypeSkyIsland(9, 2, "SKYISLAND", true);
	public static final GeneratorType CAVESURV = new GeneratorTypeCaveSurvival(10, 2, "CAVESURV", true);
	public static final GeneratorType SKYBLOCK = new GeneratorTypeSkyblock(11, 2, "SKYBLOCK", true);
	public static final GeneratorType SKYLANDS = new GeneratorTypeSkyland(12, 3, "SKYLANDS", true);
	public static final GeneratorType CAVE = new GeneratorTypeCave(13, 3, "CAVE", true);
	public static final GeneratorType PLANET = new GeneratorTypePlanetoids(14, 3, "PLANET", true);
	public static final GeneratorType CITY = new GeneratorType(15, 3, "CITY", false);
	public static final GeneratorType DEADLYDESERT = new GeneratorType(16, 5, "DEADLYDESERT", false);
	public static final GeneratorType EXTREMEJUNGLE = new GeneratorType(17, 5, "EXTREMEJUNGLE", false);
	public static final GeneratorType REALISTIC = new GeneratorTypeRealistic(18, 0, "REALISTIC", true);
	//20 == realistic Biomes 'o plenty
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final boolean Creatable;
	private final int category;
	
	public GeneratorType(int id, int cat, String name, boolean c, boolean fog, boolean angle)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		Creatable = c;
		category = cat;
		
		customFogColor = fog;
		customCelestialAngle = angle;
	}
	
	public GeneratorType(int id, int cat, String name, boolean c)
	{
		this(id, cat, name, c, false, false);
	}
	
	public int GetID()
	{
		return GeneratorTypeId;
	}
	
	public int GetCategory()
	{
		return category;
	}
	
	public String GetName()
	{
		return GeneratorName;
	}
	
	public boolean CanBeCreated()
	{
		return Creatable;
	}
	
	//======================================= GENERATOR MENU SETTINGS ================================================
	
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		return false;
	}

	//======================================= GENERATOR TYPE SETTINGS ================================================
	
	public final boolean customFogColor;
	public final boolean customCelestialAngle;
	
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		return new WorldChunkManagerHell(BiomeList.OLDplains, 0.5F);
    }
	
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.OLDplains, 0.5F);
    }
	
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
    	return null;
    }
    
    public boolean getRandSpawn(ProviderBWG4 provider)
    {
    	return true;
    }
    
    public float getCalculateCelestialAngle(ProviderBWG4 provider, long par1, float par3)
    {
    	return 0F;
    }
    
    public boolean isSurfaceWorld(ProviderBWG4 provider)
    {
    	return true;
    }
    
    public Vec3 getFogColor(ProviderBWG4 provider, World worldObj, float par1, float par2)
    {
    	return null;
    }
    
    public float getCloudHeight(ProviderBWG4 provider)
    {
    	return 128F;
    }
    
    public int getAverageGroundLevel(ProviderBWG4 provider)
    {
    	return 64;
    }
    
    public double getHorizon(ProviderBWG4 provider)
    {
    	return 64D;
    }
    
    public boolean getWorldHasVoidParticles(ProviderBWG4 provider)
    {
    	return true;
    }
    
	//======================================= GENERATOR HELL SETTINGS ================================================

    public WorldChunkManager getHellChunkManager(ProviderBWG4Hell provider)
    {
		if(Support.biomesoplenty)
		{
			WorldChunkManager hell;
			
			try
			{
				hell = SupportBopHell.getBopHellManager(provider);
			}
			catch(Exception e)
			{
				System.out.println("[BWG4] Failed to load BOP HELL");
				hell = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
			}
			
			return hell;
		}
		else
		{
			return new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
		}
    }
    
    public IChunkProvider getHellChunkProvider(ProviderBWG4Hell provider)
    {
		if(Support.biomesoplenty)
		{
			IChunkProvider hell;
			
			try
			{
				hell = SupportBopHell.getBopHellProvider(provider);
			}
			catch(Exception e)
			{
				System.out.println("[BWG4] Failed to load BOP HELL");
				hell = new ChunkProviderHell(provider.worldObj, provider.worldObj.getSeed());
			}
			
			return hell;
		}
		else
		{
			return new ChunkProviderHell(provider.worldObj, provider.worldObj.getSeed());
		}
    }
}
