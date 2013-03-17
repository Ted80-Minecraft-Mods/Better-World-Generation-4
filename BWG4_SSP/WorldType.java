package net.minecraft.src;

public class WorldType
{
	//WORLDTYPES LIST
    public static final WorldType[] worldTypes = new WorldType[32];
	
	//VANILLA WORLDTYPES
    public static final WorldType DEFAULT = (new WorldType(0, "default", 1)).setVersioned();
    public static final WorldType FLAT = new WorldType(1, "flat");
    public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
    public static final WorldType DEFAULT_1_1 = (new WorldType(8, "default_1_1", 0)).setCanBeCreated(false);

	//BWG4 BIG WORLDTYPES
	public static final WorldType BWG4DEFAULT = (new WorldType(10, "BetterDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4LARGE = (new WorldType(11, "LargeDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4BETA1 = new WorldType(12, "Beta"); 
	public static final WorldType BWG4BETA2 = (new WorldType(13, "BetaDefault")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4BETA3 = (new WorldType(14, "BetaGold")).setCanBeCreated(false); 
	public static final WorldType BWG4ALPHA = new WorldType(15, "Alpha"); 
	public static final WorldType BWG4INFDEV = new WorldType(16, "Infdev"); 
	public static final WorldType BWG4INDEV1 = new WorldType(17, "Indev");
	public static final WorldType BWG4INDEV2 = (new WorldType(18, "Indev_floating")).setCanBeCreated(false); //SMP
	public static final WorldType BWG4GOLD = new WorldType(19, "Gold"); 
	
	//BWG4 SURVIVAL WORLDTYPES	
    public static final WorldType BWG4ISLAND = new WorldType(21, "Survival Island");
    public static final WorldType BWG4SKYLAND = new WorldType(22, "Survival Skyland");
    public static final WorldType BWG4SKYBLOCK = new WorldType(23, "Skyblock");
	
	//BWG4 FUN/DIMENSION WORLDTYPES
    public static final WorldType BWG4SKY1 = new WorldType(25, "Sky Dimension"); 
    public static final WorldType BWG4SKY2 = (new WorldType(26, "SkyDimensionBeta")).setCanBeCreated(false); //SMP
    public static final WorldType BWG4SKY3 = (new WorldType(27, "SkyDimensionGold")).setCanBeCreated(false); 
    public static final WorldType BWG4CAVE = (new WorldType(28, "Cave Dimension")).setCanBeCreated(false);  
    public static final WorldType BWG4HARD = (new WorldType(29, "Hardcore")).setCanBeCreated(false);  
    public static final WorldType BWG4WASTE = (new WorldType(30, "WasteLand")).setCanBeCreated(false);  
	
    private final int worldTypeId;
    private final String worldType;
    private final int generatorVersion;
    private boolean canBeCreated;
    private boolean isWorldTypeVersioned;

    public WorldType(int par1, String par2Str)
    {
        this(par1, par2Str, 0);
    }

    public WorldType(int par1, String par2Str, int par3)
    {
        this.worldType = par2Str;
        this.generatorVersion = par3;
        this.canBeCreated = true;
        this.worldTypeId = par1;
        worldTypes[par1] = this;
    }

    public String getWorldTypeName()
    {
        return this.worldType;
    }

    /**
     * Gets the translation key for the name of this world type.
     */
    public String getTranslateName()
    {
		if(worldType == "default" || worldType == "flat" || worldType == "largeBiomes" || worldType == "default_1_1")
		{
			return "generator." + this.worldType;
		}
		else
		{
			return this.worldType;
		}	
    }

    /**
     * Returns generatorVersion.
     */
    public int getGeneratorVersion()
    {
        return this.generatorVersion;
    }

    public WorldType getWorldTypeForGeneratorVersion(int par1)
    {
        return this == DEFAULT && par1 == 0 ? DEFAULT_1_1 : this;
    }

    /**
     * Sets canBeCreated to the provided value, and returns this.
     */
    private WorldType setCanBeCreated(boolean par1)
    {
        this.canBeCreated = par1;
        return this;
    }

    /**
     * Gets whether this WorldType can be used to generate a new world.
     */
    public boolean getCanBeCreated()
    {
        return this.canBeCreated;
    }

    /**
     * Flags this world type as having an associated version.
     */
    private WorldType setVersioned()
    {
        this.isWorldTypeVersioned = true;
        return this;
    }

    /**
     * Returns true if this world Type has a version associated with it.
     */
    public boolean isVersioned()
    {
        return this.isWorldTypeVersioned;
    }

    public static WorldType parseWorldType(String par0Str)
    {
        for (int var1 = 0; var1 < worldTypes.length; ++var1)
        {
            if (worldTypes[var1] != null && worldTypes[var1].worldType.equalsIgnoreCase(par0Str))
            {
                return worldTypes[var1];
            }
        }

        return null;
    }

    public int getWorldTypeID()
    {
        return this.worldTypeId;
    }
	
	//-----------------------------------------------
	//MODLOADER COMPATIBILITY FIX
	//-----------------------------------------------
	
    public WorldChunkManager getChunkManager(World var1)
    {
        return (WorldChunkManager)(this == FLAT ? new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F, 0.5F) : new WorldChunkManager(var1));
    }

    public IChunkProvider getChunkGenerator(World var1)
    {
        return (IChunkProvider)(this == FLAT ? new ChunkProviderFlat(var1, var1.getSeed(), var1.getWorldInfo().isMapFeaturesEnabled(), var1.getWorldInfo().getGeneratorOptions()) : new ChunkProviderGenerate(var1, var1.getSeed(), var1.getWorldInfo().isMapFeaturesEnabled()));
    }
	
	public IChunkProvider getChunkGenerator(World var1, String generatorOptions)
	{
        return (IChunkProvider)(this == FLAT ? new ChunkProviderFlat(var1, var1.getSeed(), var1.getWorldInfo().isMapFeaturesEnabled(), var1.getWorldInfo().getGeneratorOptions()) : new ChunkProviderGenerate(var1, var1.getSeed(), var1.getWorldInfo().isMapFeaturesEnabled()));
	}

    public int getSeaLevel(World var1)
    {
        return this != FLAT ? 64 : 4;
    }

    public boolean hasVoidParticles(boolean var1)
    {
        return this != FLAT && !var1;
    }

    public double voidFadeMagnitude()
    {
        return this != FLAT ? 0.03125D : 1.0D;
    }
}
