package net.minecraft.src;

public abstract class WorldProvider
{
    /** world object being used */
    public World worldObj;
    public WorldType terrainType;
    public String field_82913_c;

    /** World chunk manager being used to generate chunks */
    public WorldChunkManager worldChunkMgr;

    /**
     * States whether the Hell world provider is used(true) or if the normal world provider is used(false)
     */
    public boolean isHellWorld = false;

    /**
     * A boolean that tells if a world does not have a sky. Used in calculating weather and skylight
     */
    public boolean hasNoSky = false;

    /** Light to brightness conversion table */
    public float[] lightBrightnessTable = new float[16];

    /** The id for the dimension (ex. -1: Nether, 0: Overworld, 1: The End) */
    public int dimensionId = 0;

    /** Array for sunrise/sunset colors (RGBA) */
    private float[] colorsSunriseSunset = new float[4];

    /**
     * associate an existing world with a World provider, and setup its lightbrightness table
     */
    public final void registerWorld(World par1World)
    {
        this.worldObj = par1World;
        this.terrainType = par1World.getWorldInfo().getTerrainType();
        this.field_82913_c = par1World.getWorldInfo().getGeneratorOptions();
        this.registerWorldChunkManager();
        this.generateLightBrightnessTable();
		
		if (terrainType == WorldType.BWG4CAVE || terrainType == WorldType.BWG4HARD)
		{
			hasNoSky = true;
		}
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float var1 = 0.0F;

        for (int var2 = 0; var2 <= 15; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }

    /**
     * creates a new world chunk manager for WorldProvider
     */
    protected void registerWorldChunkManager()
    {
		//THEME GENERATOR SETTINGS
		int themeID = 1;
		String zer = "", one = "1", two = "2", thr = "3", fou = "4", fiv = "5", theme = worldObj.getWorldInfo().getGeneratorOptions();
		if(theme.equals(zer)) { themeID = 0; }
		if(theme.equals(one)) { themeID = 1; }
		if(theme.equals(two)) { themeID = 2; }
		if(theme.equals(thr)) { themeID = 3; }
		if(theme.equals(fou)) { themeID = 4; }
		if(theme.equals(fiv)) { themeID = 5; }
	
		//GET BIOMES
        if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT)
        {
            FlatGeneratorInfo var1 = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
            this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.biomeList[var1.getBiome()], 0.5F, 0.5F);
        }
        else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4INFDEV)
        {
			if(themeID == 1) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INFDEVdefault, 0.5F, 0.5F); }
			else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INFDEVsnow, 0.5F, 0.5F); }
        }
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4INDEV1) 
		{ 
			if(themeID == 2) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVhell, 0.5F, 0.5F); }
			else if(themeID == 3) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVparadise, 0.5F, 0.5F); }
			else if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVwoods, 0.5F, 0.5F); }
			else if(themeID == 5) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVsnow, 0.5F, 0.5F); }
			else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVnormal, 0.5F, 0.5F); }
		}
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4INDEV2) 
		{ 
			if(themeID == 2) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVhell, 0.5F, 0.5F); }
			else if(themeID == 3) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVparadise, 0.5F, 0.5F); }
			else if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVwoods, 0.5F, 0.5F); }
			else if(themeID == 5) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVsnow, 0.5F, 0.5F); }
			else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.INDEVnormal, 0.5F, 0.5F); }
		} 
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4ISLAND)
		{
			if(themeID == 1) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.ISLANDnormal, 0.5F, 0.5F); }
			//else if(themeID == 2) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.ISLANDhell, 0.5F, 0.5F); }
			//else if(themeID == 3) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.ISLANDice, 0.5F, 0.5F); }
			else if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.ISLANDparadise, 0.5F, 0.5F); }
			else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.ISLANDnormal, 0.5F, 0.5F); }
		}
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKYLAND)
		{
			if(themeID == 1) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYLANDnormal, 0.5F, 0.5F); }
			//else if(themeID == 2) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYLANDhell, 0.5F, 0.5F); }
			else if(themeID == 3) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYLANDice, 0.5F, 0.5F); }
			else if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYLANDjungle, 0.5F, 0.5F); }
			else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYLANDnormal, 0.5F, 0.5F); }
		}
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKYBLOCK)
		{
			this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SKYBLOCKworld, 0.5F, 0.5F);
		}
        else
        {
            this.worldChunkMgr = new WorldChunkManager(this.worldObj);
        }
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
		//THEME GENERATOR SETTINGS
		int themeID = 1;
		String zer = "", one = "1", two = "2", thr = "3", fou = "4", fiv = "5", theme = worldObj.getWorldInfo().getGeneratorOptions();
		if(theme.equals(zer)) { themeID = 0; }
		if(theme.equals(one)) { themeID = 1; }
		if(theme.equals(two)) { themeID = 2; }
		if(theme.equals(thr)) { themeID = 3; }
		if(theme.equals(fou)) { themeID = 4; }
		if(theme.equals(fiv)) { themeID = 5; }
	
		//GET CHUNKGENERATORS
        if (this.terrainType == WorldType.BWG4INFDEV)
        {
            return new BWG4ChunkProviderInfdev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
        }
        else if (this.terrainType == WorldType.BWG4INDEV1) 
		{ 
			return new BWG4ChunkProviderIndev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), 1, themeID); 
		}
        else if (this.terrainType == WorldType.BWG4INDEV2) 
		{ 
			return new BWG4ChunkProviderIndev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), 2, themeID); 
		}
        else if (this.terrainType == WorldType.BWG4ALPHA)
        {
            return new BWG4ChunkProviderAlpha(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
        }
        else if (terrainType == WorldType.BWG4BETA1 || terrainType == WorldType.BWG4BETA2 || terrainType == WorldType.BWG4BETA3)
        {
			int worldID = 1; if(terrainType == WorldType.BWG4BETA2) { worldID = 2; }
            return new BWG4ChunkProviderBeta(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), worldID);
        }
        else if (terrainType == WorldType.BWG4SKY1 || terrainType == WorldType.BWG4SKY2 || terrainType == WorldType.BWG4SKY3)
        {
			int worldID = 1; if(terrainType == WorldType.BWG4SKY2) { worldID = 2; } if(terrainType == WorldType.BWG4SKY3) { worldID = 3; }
            return new BWG4ChunkProviderSky(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), worldID);
        }
        else if (this.terrainType == WorldType.BWG4CAVE)
        {
            return new BWG4ChunkProviderCave(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), themeID);
        }
		else if (this.terrainType == WorldType.BWG4ISLAND)
        {
            return new BWG4ChunkProviderIsland(this.worldObj, this.worldObj.getSeed(), themeID);
        }
		else if (this.terrainType == WorldType.BWG4SKYLAND)
        {
            return new BWG4ChunkProviderSkyIsland(this.worldObj, this.worldObj.getSeed(), themeID);
        }
		else if (this.terrainType == WorldType.BWG4SKYBLOCK)
        {
			return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), false);
        }
        else if (this.terrainType == WorldType.BWG4GOLD)
        {
            return new BWG4ChunkProviderGold(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
        }
        else if (this.terrainType == WorldType.BWG4DEFAULT || this.terrainType == WorldType.BWG4LARGE)
        {
			return new BWG4ChunkProviderDefault(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
        }
		else
		{
			return (IChunkProvider)(this.terrainType == WorldType.FLAT ? new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.field_82913_c) : new ChunkProviderGenerate(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled()));
		}    
	}

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return var3 == Block.grass.blockID;
    }

	public boolean mayRandSpawn()
	{	
		if(terrainType == WorldType.BWG4ISLAND || this.terrainType == WorldType.BWG4SKYLAND || this.terrainType == WorldType.BWG4SKYBLOCK )
		{
			return false;
		}
		return true;
	}
	
    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        int var4 = (int)(par1 % 24000L);
        float var5 = ((float)var4 + par3) / 24000.0F - 0.25F;

        if (var5 < 0.0F)
        {
            ++var5;
        }

        if (var5 > 1.0F)
        {
            --var5;
        }

		if (terrainType == WorldType.BWG4CAVE || terrainType == WorldType.BWG4HARD) 
		{ 
			var5 = 0.4F; 
		}

        float var6 = var5;
        var5 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + 1.0D) / 2.0D);
        var5 = var6 + (var5 - var6) / 3.0F;
        return var5;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
		if (terrainType == WorldType.BWG4CAVE)
		{
			return false;
		}
        return true;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return true;
    }

    public static WorldProvider getProviderForDimension(int par0)
    {
        return (WorldProvider)(par0 == -1 ? new WorldProviderHell() : (par0 == 0 ? new WorldProviderSurface() : (par0 == 1 ? new WorldProviderEnd() : null)));
    }

    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return null;
    }

    public int getAverageGroundLevel()
    {
		if (terrainType == WorldType.BWG4CAVE)
		{
			return 50;
		}
        return this.terrainType == WorldType.FLAT ? 4 : 64;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public abstract String getDimensionName();
}
