package ted80.bwg4.generatordata;

import net.minecraft.world.WorldType;

public class GeneratorType 
{
	public static GeneratorType Current;
	public static String generatorinfo = "";
	
	public static final GeneratorType[] generatortypes = new GeneratorType[16];
	
	public static final GeneratorType BWG4SMALL = new GeneratorType(0, "BetterDefault", "Better Default", true);
	public static final GeneratorType BWG4LARGE = new GeneratorType(1, "BetterDefaultLarge", "", false);  
	public static final GeneratorType BWG4BETA1 = new GeneratorType(2, "Beta_Beta", "Beta", true);  
	public static final GeneratorType BWG4BETA2 = new GeneratorType(3, "Beta_Default", "", false);  
	public static final GeneratorType BWG4ALPHA = new GeneratorType(4, "Alpha", "Alpha", true);  
	public static final GeneratorType BWG4INFDEV = new GeneratorType(5, "Infdev", "Infdev", true);  
	public static final GeneratorType BWG4INDEV1 = new GeneratorType(6, "Indev_inland", "Indev", true);  
	public static final GeneratorType BWG4INDEV2 = new GeneratorType(7, "Indev_floating", "", false);  
    public static final GeneratorType BWG4ISLAND = new GeneratorType(8, "Survival_Island", "Survival Island", true);  
    public static final GeneratorType BWG4SKYLAND = new GeneratorType(9, "Survival_Skyland", "Survival Skyland", true);  
    public static final GeneratorType BWG4SKYBLOCK = new GeneratorType(10, "Skyblock", "SkyBlock Survival", true);  
    public static final GeneratorType BWG4SKY1 = new GeneratorType(11, "Sky_Default", "Sky Dimension", true);  
    public static final GeneratorType BWG4SKY2 = new GeneratorType(12, "Sky_Beta", "", false);
    public static final GeneratorType BWG4CAVE = new GeneratorType(13, "CaveDimension", "", false);  
    public static final GeneratorType BWG4HARD = new GeneratorType(14, "Hardcore", "", false);  
    public static final GeneratorType BWG4WASTE = new GeneratorType(15, "Wasteland", "", false);  	
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	private final String ScreenName;
	private final boolean Creatable;
	
	public GeneratorType(int id, String name, String screen, boolean c)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
		ScreenName = screen;
		Creatable = c;
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
}
