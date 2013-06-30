package ted80.bwg4.generatordata;

import net.minecraft.world.WorldType;

public class GeneratorType 
{
	public static GeneratorType Current;
	public static String generatorinfo = "";
	public static int GeneratorTheme = 0;
	
	public static final GeneratorType[] generatortypes = new GeneratorType[16];
	
	public static final GeneratorType BWG4SMALL = new GeneratorType(0, "BetterDefault", "Better Default", true, true);
	public static final GeneratorType BWG4LARGE = new GeneratorType(1, "BetterDefaultLarge");  
	public static final GeneratorType BWG4BETA1 = new GeneratorType(2, "Beta_Beta", "Beta", true, true);  
	public static final GeneratorType BWG4BETA2 = new GeneratorType(3, "Beta_Default");  
	public static final GeneratorType BWG4ALPHA = new GeneratorType(4, "Alpha", "Alpha", true, false);  
	public static final GeneratorType BWG4INFDEV = new GeneratorType(5, "Infdev", "Infdev", true, true);  
	public static final GeneratorType BWG4INDEV1 = new GeneratorType(6, "Indev_inland", "Indev", true, true);  
	public static final GeneratorType BWG4INDEV2 = new GeneratorType(7, "Indev_floating");  
    public static final GeneratorType BWG4ISLAND = new GeneratorType(8, "Survival_Island", "Survival Island", true, true);  
    public static final GeneratorType BWG4SKYLAND = new GeneratorType(9, "Survival_Skyland", "Survival Skyland", true, true);  
    public static final GeneratorType BWG4SKYBLOCK = new GeneratorType(10, "Skyblock", "SkyBlock Survival", true, true);  
    public static final GeneratorType BWG4SKY1 = new GeneratorType(11, "Sky_Default", "Sky Dimension", true, true);  
    public static final GeneratorType BWG4SKY2 = new GeneratorType(12, "Sky_Beta");
    public static final GeneratorType BWG4CAVE = new GeneratorType(13, "CaveDimension");  
    public static final GeneratorType BWG4HARD = new GeneratorType(14, "Hardcore");  
    public static final GeneratorType BWG4WASTE = new GeneratorType(15, "Wasteland");  	
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	private final boolean hasSettings;
	
	public GeneratorType(int id, String name, String screen, boolean c, boolean s)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		ScreenName = screen;
		Creatable = c;
		hasSettings = s;
	}

	public GeneratorType(int id, String name)
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
