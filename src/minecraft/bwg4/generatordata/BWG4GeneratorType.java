package bwg4.generatordata;

import net.minecraft.world.WorldType;

public class BWG4GeneratorType 
{
	public static BWG4GeneratorType currentGenerator;
	public static String biomestring;
	public static int[] currentSettings;
	
	public static final BWG4GeneratorType[] generatortypes = new BWG4GeneratorType[40];
	public static final BWG4GeneratorType DEFAULT = new BWG4GeneratorType(1, 0, "BETTERDEFAULT", "Better Default", true, true);
	public static final BWG4GeneratorType FLAT = new BWG4GeneratorType(2, 0, "EXTRFLAT", "Extremeflat", false, true);
	public static final BWG4GeneratorType BETA = new BWG4GeneratorType(3, 1, "BETA", "Beta 1.7.3", true, true);
	public static final BWG4GeneratorType ALPHA = new BWG4GeneratorType(4, 1, "ALPHA", "Alpha 1.2.0", true, false);
	public static final BWG4GeneratorType INFDEV = new BWG4GeneratorType(5, 1, "INFDEV", "Infdev", true, true);
	public static final BWG4GeneratorType INDEV = new BWG4GeneratorType(6, 1, "INDEV", "Indev", true, true);
	public static final BWG4GeneratorType ISLAND = new BWG4GeneratorType(7, 2, "ISLAND", "Survival Island", true, true);
	public static final BWG4GeneratorType SKYISLAND = new BWG4GeneratorType(8, 2, "SKYISLAND", "Survival Skyland", true, true);
	public static final BWG4GeneratorType SKYBLOCK = new BWG4GeneratorType(9, 2, "SKYBLOCK", "Skyblock", true, false);
	public static final BWG4GeneratorType SKYLANDS = new BWG4GeneratorType(10, 3, "SKYLANDS", "Skylands", true, true);
	public static final BWG4GeneratorType CAVE = new BWG4GeneratorType(11, 3, "CAVE", "Cave world", true, true);
	public static final BWG4GeneratorType WASTELAND = new BWG4GeneratorType(12, 3, "WASTELAND", "Wasteland", true, false);
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	private final boolean hasSettings;
	private final int category;
	
	public BWG4GeneratorType(int id, int cat, String name, String screen, boolean c, boolean s)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		ScreenName = screen;
		Creatable = c;
		hasSettings = s;
		category = cat;
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
}
