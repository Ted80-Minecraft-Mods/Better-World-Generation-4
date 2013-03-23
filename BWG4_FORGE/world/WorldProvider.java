package net.minecraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.world.gen.*;

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
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
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
		if(theme.equals(zer)) { themeID = 1; }
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
        	worldChunkMgr = terrainType.getChunkManager(worldObj);
            //this.worldChunkMgr = new WorldChunkManager(this.worldObj);
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
		if(theme.equals(zer)) { themeID = 1; }
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
			return terrainType.getChunkGenerator(worldObj, field_82913_c);
		}
	}

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int k = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return k == Block.grass.blockID;
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
        int j = (int)(par1 % 24000L);
        float f1 = ((float)j + par3) / 24000.0F - 0.25F;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        if (f1 > 1.0F)
        {
            --f1;
        }

		if (terrainType == WorldType.BWG4CAVE || terrainType == WorldType.BWG4HARD) 
		{ 
			f1 = 0.4F; 
		}

        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }

    public int getMoonPhase(long par1)
    {
        return (int)(par1 / 24000L) % 8;
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

    @SideOnly(Side.CLIENT)

    /**
     * Returns array with sunrise/sunset colors
     */
    public float[] calcSunriseSunsetColors(float par1, float par2)
    {
        float f2 = 0.4F;
        float f3 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) - 0.0F;
        float f4 = -0.0F;

        if (f3 >= f4 - f2 && f3 <= f4 + f2)
        {
            float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
            float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * (float)Math.PI)) * 0.99F;
            f6 *= f6;
            this.colorsSunriseSunset[0] = f5 * 0.3F + 0.7F;
            this.colorsSunriseSunset[1] = f5 * f5 * 0.7F + 0.2F;
            this.colorsSunriseSunset[2] = f5 * f5 * 0.0F + 0.2F;
            this.colorsSunriseSunset[3] = f6;
            return this.colorsSunriseSunset;
        }
        else
        {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return Vec3D with biome specific fog color
     */
    public Vec3 getFogColor(float par1, float par2)
    {
        float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;

		if (terrainType == WorldType.BWG4CAVE || terrainType == WorldType.BWG4HARD)
		{
			return worldObj.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
		}
		
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)f3, (double)f4, (double)f5);
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
        return DimensionManager.createProviderFor(par0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the y level at which clouds are rendered.
     */
    public float getCloudHeight()
    {
		if (terrainType == WorldType.BWG4INDEV2 || terrainType == WorldType.BWG4SKYLAND || terrainType == WorldType.BWG4SKYBLOCK || terrainType == WorldType.BWG4SKY1 || terrainType == WorldType.BWG4SKY2 || terrainType == WorldType.BWG4SKY3)
		{
			return -5F;
		}
		else if (terrainType == WorldType.BWG4GOLD)
		{
			return 145.0F;
		}
		else
		{
			return 128.0F;
		}	
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return true;
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
        return this.terrainType.getMinimumSpawnHeight(this.worldObj);
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns true if this dimension is supposed to display void particles and pull in the far plane based on the
     * user's Y offset.
     */
    public boolean getWorldHasVoidParticles()
    {
        return this.terrainType.hasVoidParticles(this.hasNoSky);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns a double value representing the Y value relative to the top of the map at which void fog is at its
     * maximum. The default factor of 0.03125 relative to 256, for example, means the void fog will be at its maximum at
     * (256*0.03125), or 8.
     */
    public double getVoidFogYFactor()
    {
        return this.terrainType.voidFadeMagnitude();
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    public boolean doesXZShowFog(int par1, int par2)
    {
        return false;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public abstract String getDimensionName();

    /*======================================= Forge Start =========================================*/
    private IRenderHandler skyRenderer = null;
    private IRenderHandler cloudRenderer = null;
    
    /**
     * Sets the providers current dimension ID, used in default getSaveFolder()
     * Added to allow default providers to be registered for multiple dimensions.
     * 
     * @param dim Dimension ID
     */
    public void setDimension(int dim)
    {
        this.dimensionId = dim;
    }

    /**
     * Returns the sub-folder of the world folder that this WorldProvider saves to.
     * EXA: DIM1, DIM-1
     * @return The sub-folder name to save this world's chunks to.
     */
    public String getSaveFolder()
    {
        return (dimensionId == 0 ? null : "DIM" + dimensionId);
    }

    /**
     * A message to display to the user when they transfer to this dimension.
     *
     * @return The message to be displayed
     */
    public String getWelcomeMessage()
    {
        if (this instanceof WorldProviderEnd)
        {
            return "Entering the End";
        }
        else if (this instanceof WorldProviderHell)
        {
            return "Entering the Nether";
        }
        return null;
    }

    /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    public String getDepartMessage()
    {
        if (this instanceof WorldProviderEnd)
        {
            return "Leaving the End";
        }
        else if (this instanceof WorldProviderHell)
        {
            return "Leaving the Nether";
        } 
        return null;
    }

    /**
     * The dimensions movement factor. Relative to normal overworld.
     * It is applied to the players position when they transfer dimensions.
     * Exa: Nether movement is 8.0
     * @return The movement factor
     */
    public double getMovementFactor()
    {
        if (this instanceof WorldProviderHell)
        {
            return 8.0;
        }
        return 1.0;
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer()
    {
        return this.skyRenderer;
    }

    @SideOnly(Side.CLIENT)
    public void setSkyRenderer(IRenderHandler skyRenderer)
    {
        this.skyRenderer = skyRenderer;
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getCloudRenderer()
    {
        return cloudRenderer;
    }

    @SideOnly(Side.CLIENT)
    public void setCloudRenderer(IRenderHandler renderer)
    {
        cloudRenderer = renderer;
    }

    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == EnumGameType.ADVENTURE;
        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;

        if (!hasNoSky && !isAdventure)
        {
            chunkcoordinates.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posY = this.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
        }

        return chunkcoordinates;
    }
    
    /**
     * Determine if the cusor on the map should 'spin' when rendered, like it does for the player in the nether.
     * 
     * @param entity The entity holding the map, playername, or frame-ENTITYID
     * @param x X Position
     * @param y Y Position
     * @param z Z Postion
     * @return True to 'spin' the cursor
     */
    public boolean shouldMapSpin(String entity, double x, double y, double z)
    {
        return dimensionId < 0;
    }

    /**
     * Determines the dimension the player will be respawned in, typically this brings them back to the overworld.
     * 
     * @param player The player that is respawning
     * @return The dimension to respawn the player in
     */
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return 0;
    }

    /*======================================= Start Moved From World =========================================*/

    public BiomeGenBase getBiomeGenForCoords(int x, int z)
    {
        return worldObj.getBiomeGenForCoordsBody(x, z);
    }

    public boolean isDaytime()
    {
        return worldObj.skylightSubtracted < 4;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
    {
        return worldObj.getSkyColorBody(cameraEntity, partialTicks);
    }

    @SideOnly(Side.CLIENT)
    public Vec3 drawClouds(float partialTicks)
    {
        return worldObj.drawCloudsBody(partialTicks);
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return worldObj.getStarBrightnessBody(par1);
    }

    public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
    {
        worldObj.spawnHostileMobs = allowHostile;
        worldObj.spawnPeacefulMobs = allowPeaceful;
    }

    public void calculateInitialWeather()
    {
        worldObj.calculateInitialWeatherBody();
    }

    public void updateWeather()
    {
        worldObj.updateWeatherBody();
    }

    public void toggleRain()
    {
        worldObj.worldInfo.setRainTime(1);
    }

    public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
    {
        return worldObj.canBlockFreezeBody(x, y, z, byWater);
    }

    public boolean canSnowAt(int x, int y, int z)
    {
        return worldObj.canSnowAtBody(x, y, z);
    }

    public void setWorldTime(long time)
    {
        worldObj.worldInfo.setWorldTime(time);
    }

    public long getSeed()
    {
        return worldObj.worldInfo.getSeed();
    }

    public long getWorldTime()
    {
        return worldObj.worldInfo.getWorldTime();
    }

    public ChunkCoordinates getSpawnPoint()
    {
        WorldInfo info = worldObj.worldInfo;
        return new ChunkCoordinates(info.getSpawnX(), info.getSpawnY(), info.getSpawnZ());
    }

    public void setSpawnPoint(int x, int y, int z)
    {
        worldObj.worldInfo.setSpawnPosition(x, y, z);
    }

    public boolean canMineBlock(EntityPlayer player, int x, int y, int z)
    {
        return worldObj.canMineBlockBody(player, x, y, z);
    }

    public boolean isBlockHighHumidity(int x, int y, int z)
    {
        return worldObj.getBiomeGenForCoords(x, z).isHighHumidity();
    }

    public int getHeight()
    {
        return 256;
    }

    public int getActualHeight()
    {
        return hasNoSky ? 128 : 256;
    }

    public double getHorizon()
    {
        return worldObj.worldInfo.getTerrainType().getHorizon(worldObj);
    }

    public void resetRainAndThunder()
    {
        worldObj.worldInfo.setRainTime(0);
        worldObj.worldInfo.setRaining(false);
        worldObj.worldInfo.setThunderTime(0);
        worldObj.worldInfo.setThundering(false);
    }

    public boolean canDoLightning(Chunk chunk)
    {
        return true;
    }

    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return true;
    }
}
