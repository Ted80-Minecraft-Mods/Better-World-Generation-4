package bwg4.generatordata;

import net.minecraft.world.WorldType;

public class BWG4GeneratorType 
{
	public static BWG4GeneratorType Current;
	public static BWG4WorldSettings Settings;
	public static String generatorinfo = "";
	public static int GeneratorTheme = 0;
	
	public static final BWG4GeneratorType[] generatortypes = new BWG4GeneratorType[16];
	
	public static final BWG4GeneratorType BWG4SMALL = new BWG4GeneratorType(0, "BetterDefault", "Better Default", true, true);
	public static final BWG4GeneratorType BWG4LARGE = new BWG4GeneratorType(1, "BetterDefaultLarge");  
	public static final BWG4GeneratorType BWG4BETA1 = new BWG4GeneratorType(2, "Beta_Beta", "Beta", true, true);  
	public static final BWG4GeneratorType BWG4BETA2 = new BWG4GeneratorType(3, "Beta_Default");  
	public static final BWG4GeneratorType BWG4ALPHA = new BWG4GeneratorType(4, "Alpha", "Alpha", true, false);  
	public static final BWG4GeneratorType BWG4INFDEV = new BWG4GeneratorType(5, "Infdev", "Infdev", true, true);  
	public static final BWG4GeneratorType BWG4INDEV1 = new BWG4GeneratorType(6, "Indev_inland", "Indev", true, true);  
	public static final BWG4GeneratorType BWG4INDEV2 = new BWG4GeneratorType(7, "Indev_floating");  
    public static final BWG4GeneratorType BWG4ISLAND = new BWG4GeneratorType(8, "Survival_Island", "Survival Island", true, true);  
    public static final BWG4GeneratorType BWG4SKYLAND = new BWG4GeneratorType(9, "Survival_Skyland", "Survival Skyland", true, true);  
    public static final BWG4GeneratorType BWG4SKYBLOCK = new BWG4GeneratorType(10, "Skyblock", "SkyBlock Survival", true, false);  
    public static final BWG4GeneratorType BWG4SKY1 = new BWG4GeneratorType(11, "Sky_Default", "Sky Dimension", true, true);  
    public static final BWG4GeneratorType BWG4SKY2 = new BWG4GeneratorType(12, "Sky_Beta");
    public static final BWG4GeneratorType BWG4CAVE = new BWG4GeneratorType(13, "CaveDimension");  
    public static final BWG4GeneratorType BWG4HARD = new BWG4GeneratorType(14, "Hardcore");  
    public static final BWG4GeneratorType BWG4WASTE = new BWG4GeneratorType(15, "Wasteland");  	
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	private final boolean hasSettings;
	
	public BWG4GeneratorType(int id, String name, String screen, boolean c, boolean s)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		ScreenName = screen;
		Creatable = c;
		hasSettings = s;
	}

	public BWG4GeneratorType(int id, String name)
	{
		this(id, name, "", false, false);
	}
	
	public int GetID()
	{
		return GeneratorTypeId;
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
