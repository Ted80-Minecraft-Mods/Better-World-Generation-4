package bwg4.api.gen;

import bwg4.api.biome.BiomeList;
import bwg4.world.ProviderBWG4;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class GeneratorType 
{
	public static GeneratorType currentGenerator;
	public static String biomestring;
	public static int[] currentSettings;
	
	public static long seed;
	public static String genString;
	
	public static final GeneratorType[] generatortypes = new GeneratorType[40];
	public static final GeneratorType DEFAULT = new GeneratorTypeDefault(1, 0, "BETTERDEFAULT", "Better Default", true, true);
	public static final GeneratorType FLAT = new GeneratorType(2, 0, "EXTRFLAT", "Extremeflat", false, true);
	public static final GeneratorType BETA173 = new GeneratorTypeBeta(3, 1, "BETA173", "Beta 1.7.3", true, true);
	public static final GeneratorType ALPHA12 = new GeneratorTypeAlpha12(4, 1, "ALPHA12", "Alpha 1.2.0", true, false);
	public static final GeneratorType ALPHA11 = new GeneratorTypeAlpha11(5, 1, "ALPHA11", "Alpha 1.1.0", true, true);
	public static final GeneratorType INFDEV = new GeneratorTypeInfdev(6, 1, "INFDEV", "Infdev", true, true);
	public static final GeneratorType INDEV = new GeneratorTypeIndev(7, 1, "INDEV", "Indev", true, true);
	public static final GeneratorType WASTELAND = new GeneratorTypeWasteland(8, 2, "WASTELAND", "Wasteland (WIP)", false, false);
	public static final GeneratorType ISLAND = new GeneratorTypeIsland(9, 2, "ISLAND", "Survival Island", true, true);
	public static final GeneratorType SKYISLAND = new GeneratorTypeSkyIsland(10, 2, "SKYISLAND", "Survival Skyland", true, true);
	public static final GeneratorType CAVESURV = new GeneratorTypeCaveSurvival(11, 2, "CAVESURV", "Cave Survival", true, false);
	public static final GeneratorType SKYBLOCK = new GeneratorTypeSkyblock(12, 2, "SKYBLOCK", "Skyblock", true, true);
	public static final GeneratorType SKYLANDS = new GeneratorTypeSkyland(13, 3, "SKYLANDS", "Skylands", true, true);
	public static final GeneratorType CAVE = new GeneratorTypeCave(14, 3, "CAVE", "Cave world (WIP)", true, false);
	public static final GeneratorType CITY = new GeneratorType(15, 3, "CITY", "Endless City", false, false);
	public static final GeneratorType DEADLYDESERT = new GeneratorType(16, 5, "DEADLYDESERT", "Deadly Desert", false, false);
	public static final GeneratorType EXTREMEJUNGLE = new GeneratorType(17, 5, "EXTREMEJUNGLE", "Extreme Jungle", false, false);
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	private final boolean hasSettings;
	private final int category;
	
	public GeneratorType(int id, int cat, String name, String screen, boolean c, boolean s, boolean fog, boolean angle)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		ScreenName = screen;
		Creatable = c;
		hasSettings = s;
		category = cat;
		
		customFogColor = fog;
		customCelestialAngle = angle;
	}
	
	public GeneratorType(int id, int cat, String name, String screen, boolean c, boolean s)
	{
		this(id, cat, name, screen, c, s, false, false);
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
	
	public String GetScreenName()
	{
		return ScreenName;
	}
	
	public boolean CanBeCreated()
	{
		return Creatable;
	}
	
	public boolean HasSettings()
	{
		return hasSettings;
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
}
