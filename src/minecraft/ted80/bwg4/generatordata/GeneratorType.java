package ted80.bwg4.generatordata;

import net.minecraft.world.WorldType;

public class GeneratorType 
{
	public static GeneratorType Current;
	public static String generatorinfo = "";
	
	public static final GeneratorType[] generatortypes = new GeneratorType[16];
	
	public static final GeneratorType BWG4SMALL = new GeneratorType(0, "BetterDefault");
	public static final GeneratorType BWG4LARGE = new GeneratorType(1, "BetterDefaultLarge");  
	public static final GeneratorType BWG4BETA1 = new GeneratorType(2, "Beta_Beta");  
	public static final GeneratorType BWG4BETA2 = new GeneratorType(3, "Beta_Default");  
	public static final GeneratorType BWG4ALPHA = new GeneratorType(4, "Alpha");  
	public static final GeneratorType BWG4INFDEV = new GeneratorType(5, "Infdev");  
	public static final GeneratorType BWG4INDEV1 = new GeneratorType(6, "Indev_inland");  
	public static final GeneratorType BWG4INDEV2 = new GeneratorType(7, "Indev_floating");  
    public static final GeneratorType BWG4ISLAND = new GeneratorType(8, "Survival_Island");  
    public static final GeneratorType BWG4SKYLAND = new GeneratorType(9, "Survival_Skyland");  
    public static final GeneratorType BWG4SKYBLOCK = new GeneratorType(10, "Skyblock");  
    public static final GeneratorType BWG4SKY1 = new GeneratorType(11, "Sky_Default");  
    public static final GeneratorType BWG4SKY2 = new GeneratorType(12, "Sky_Beta");
    public static final GeneratorType BWG4CAVE = new GeneratorType(13, "CaveDimension");  
    public static final GeneratorType BWG4HARD = new GeneratorType(14, "Hardcore");  
    public static final GeneratorType BWG4WASTE = new GeneratorType(15, "Wasteland");  	
	
	private final int GeneratorTypeId;
	private final String GeneratorName;
	
	public GeneratorType(int id, String name)
	{
		generatortypes[id] = this;
		GeneratorTypeId = id;
		GeneratorName = name;
	}
	
	public String GetName()
	{
		return GeneratorName;
	}
}
