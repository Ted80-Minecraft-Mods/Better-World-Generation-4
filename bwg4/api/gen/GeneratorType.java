package bwg4.api.gen;

public class GeneratorType 
{
	public static GeneratorType currentGenerator;
	public static String biomestring;
	public static int[] currentSettings;
	
	public static final GeneratorType[] generatortypes = new GeneratorType[40];
	public static final GeneratorType DEFAULT = new GeneratorType(1, 0, "BETTERDEFAULT", "Better Default", true, true);
	public static final GeneratorType FLAT = new GeneratorType(2, 0, "EXTRFLAT", "Extremeflat", false, true);
	public static final GeneratorType BETA173 = new GeneratorType(3, 1, "BETA173", "Beta 1.7.3", true, true);
	public static final GeneratorType ALPHA12 = new GeneratorType(4, 1, "ALPHA12", "Alpha 1.2.0", true, false);
	public static final GeneratorType ALPHA11 = new GeneratorType(5, 1, "ALPHA11", "Alpha 1.1.0", true, true);
	public static final GeneratorType INFDEV = new GeneratorType(6, 1, "INFDEV", "Infdev", true, true);
	public static final GeneratorType INDEV = new GeneratorType(7, 1, "INDEV", "Indev", true, true);
	public static final GeneratorType WASTELAND = new GeneratorType(8, 2, "WASTELAND", "Wasteland", true, false);
	public static final GeneratorType ISLAND = new GeneratorType(9, 2, "ISLAND", "Survival Island", true, true);
	public static final GeneratorType SKYISLAND = new GeneratorType(10, 2, "SKYISLAND", "Survival Skyland", true, true);
	public static final GeneratorType CAVESURV = new GeneratorType(11, 2, "CAVESURV", "Cave Survival", true, false);
	public static final GeneratorType SKYBLOCK = new GeneratorType(12, 2, "SKYBLOCK", "Skyblock", true, true);
	public static final GeneratorType SKYLANDS = new GeneratorType(13, 3, "SKYLANDS", "Skylands", true, true);
	public static final GeneratorType CAVE = new GeneratorType(14, 3, "CAVE", "Cave world", true, false);
	public static final GeneratorType CITY = new GeneratorType(15, 3, "CITY", "Endless City", false, false);
	public static final GeneratorType DEADLYDESERT = new GeneratorType(16, 5, "DEADLYDESERT", "Deadly Desert", false, false);
	public static final GeneratorType EXTREMEJUNGLE = new GeneratorType(17, 5, "EXTREMEJUNGLE", "Extreme Jungle", false, false);
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	private final boolean hasSettings;
	private final int category;
	
	public GeneratorType(int id, int cat, String name, String screen, boolean c, boolean s)
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
