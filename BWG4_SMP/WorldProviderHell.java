package net.minecraft.src;

public class WorldProviderHell extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4ISLAND || this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKYLAND)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SURVIVALnether, 1.0F, 0.0F);
		}
		else if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKY1 || this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKY2 || this.worldObj.getWorldInfo().getTerrainType() ==WorldType.BWG4SKY3)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.SURVIVALnether, 1.0F, 0.0F);
        }
		else
		{
			this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
		}	
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float var1 = 0.1F;

        for (int var2 = 0; var2 <= 15; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
		if (this.terrainType == WorldType.BWG4ISLAND || this.terrainType == WorldType.BWG4SKYLAND)
        {
			return new BWG4ChunkProviderSurvivalNether(this.worldObj, this.worldObj.getSeed());
		}
		else if (this.terrainType == WorldType.BWG4SKY1 || this.terrainType == WorldType.BWG4SKY2 || this.terrainType == WorldType.BWG4SKY3)
        {
			return new BWG4ChunkProviderSky(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), 4);
        }
		else if (this.terrainType == WorldType.BWG4SKYBLOCK)
        {
			return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), true);
        }
		else
		{
			return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
		}	
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Nether";
    }
}
