package bwg4.generatordata;

public class BWG4WorldSettings 
{
	public double DECO_ORES;
	public double DECO_PLANTS;
	public double DECO_TREES;
	public double DECO_PONDS;
	public double DECO_DUNGEONS;
	public double DECO_CLAY;
	
	public double TERRAIN_HEIGHT;
	public double TERRAIN_CAVES;
	public double TERRAIN_RAVINES;
	
	public double STRUCTURES_VILLAGES;
	public double STRUCTURES_SCATTERED;
	
	public int BIOME_SIZE;
	
	public BWG4WorldSettings(float ores, float plants, float trees, float ponds, float dungeons, float clay, float height, float caves, float ravines, float villages, float scattered, float size)
	{
		DECO_ORES = (double) ores;
		DECO_PLANTS = (double) plants;
		DECO_TREES = (double) trees;
		DECO_PONDS = (double) ponds;
		DECO_DUNGEONS = (double) dungeons;
		DECO_CLAY = (double) clay;
		
		TERRAIN_HEIGHT = (double) height;
		TERRAIN_CAVES = (double) caves;
		TERRAIN_RAVINES = (double) ravines;
		
		STRUCTURES_VILLAGES = (double) villages;
		STRUCTURES_SCATTERED = (double) scattered;
		
		BIOME_SIZE = ((int) Math.floor(size * 9) + 1);
	}
	
	public BWG4WorldSettings()
	{
		this(0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, 0.25F, (1F / 9F) * 4F);
	}
	
	public String getSettingsString()
	{
		return "";
	}
}
