package bwg4.gen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import bwg4.api.DefaultBiomeList;
import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import bwg4.gen.chunkproviders.*;
import bwg4.generatordata.BWG4DecodeGeneratorString;
import bwg4.generatordata.BWG4GeneratorType;
import bwg4.util.Coords;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class BWG4Provider extends WorldProvider
{   
	private float[] colorsSunriseSunset = new float[4];
	
	@Override
    protected void registerWorldChunkManager()
    {
		if(this.worldObj.getWorldInfo().getTerrainType() == mod_bwg4.BWG4DEFAULT && !worldObj.isRemote)
		{
			System.out.println("BWG4 GENERATORSTRING: " + worldObj.getWorldInfo().getGeneratorOptions());
			if(worldObj.getWorldInfo().getGeneratorOptions().length() > 2)
			{
				BWG4DecodeGeneratorString.decode(worldObj.getWorldInfo().getGeneratorOptions());
				if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEFAULT)
				{
					if(BWG4GeneratorType.biomestring != null)
					{
						if(BWG4GeneratorType.biomestring.length() < 4)
						{
							BWG4GeneratorType.biomestring = DefaultBiomeList.getDefaultString();
						}
					}
					else
					{
						BWG4GeneratorType.biomestring = DefaultBiomeList.getDefaultString();
					}
				}
			}
			else
			{
				BWG4DecodeGeneratorString.decode("BetterDefault#4&0#" + DefaultBiomeList.getDefaultString());
			}
			
	        if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INFDEV || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA11)
	        {
				int themeID = trySetting(0, 1);
				if(themeID == 0) { this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICnormal, 0.5F, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICsnow, 0.5F, 0.5F); }
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV)
	        {
				int themeID = trySetting(0, 4);
				if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICsnow, 0.5F, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICnormal, 0.5F, 0.5F); }
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND)
			{
				int themeID = trySetting(0, 4) + 1; 
				switch (themeID) 
				{
					case 5: worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal2, 0.5F, 0.5F); break;
					default: worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F); break;
				}
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
			{
				int themeID = trySetting(0, 2) + 1; 
				switch (themeID) 
				{
					case 2: worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALsnow, 0.5F, 0.5F); break;
					default: worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal2, 0.5F, 0.5F); break;
				}
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA12 || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.BETA173 || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
			{
				worldChunkMgr = new BWG4ChunkManagerBeta(worldObj, true);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.WASTELAND)
			{
				worldChunkMgr = new BWG4ChunkManagerWasteland(worldObj);
			}
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.ADVENTUREdesert, 0.5F, 0.5F);
	        }
			else
			{
				worldChunkMgr = new BWG4ChunkManagerDefault(worldObj, true);
			}
		}
		else if(worldObj.isRemote && BWG4GeneratorType.currentGenerator != null)
		{
	        if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INFDEV || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA11)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICnormal, 0.5F, 0.5F);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.CLASSICnormal, 0.5F, 0.5F);
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV)
			{
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
			}
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.WASTELAND)
			{
				worldChunkMgr = new BWG4ChunkManagerWasteland(worldObj);
			}
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnormal1, 0.5F, 0.5F);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
	        {
	        	this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.ADVENTUREdesert, 0.5F, 0.5F);
	        }
			else
			{
				worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.BETAplains, 0.5F, 0.5F);
			}
		}
		else 
		{
			BWG4GeneratorType.currentGenerator = null;
			BWG4GeneratorType.biomestring = null;
			BWG4GeneratorType.currentSettings = null;
			
			if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT)
	        {
	            FlatGeneratorInfo var1 = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
	            this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.biomeList[var1.getBiome()], 0.5F, 0.5F);
	        }
	        else 
	        {
				this.worldChunkMgr = this.worldObj.getWorldInfo().getTerrainType().getChunkManager(this.worldObj);
	        }
	    }
    }
	
	@Override
    public IChunkProvider createChunkGenerator()
    {		
		if(terrainType == mod_bwg4.BWG4DEFAULT)
		{
	        if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INFDEV)
	        {
	            return new BWG4ChunkProviderInfdev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), false);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV) 
			{ 
				int themeID = trySetting(0, 4) + 1;
				int typeID = trySetting(1, 2) + 1;
				int size = trySetting(2, 2) + 1;
				int layers = trySetting(3, 4) + 1;
				return new BWG4ChunkProviderIndev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), typeID, themeID, size, layers); 
			}
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA12)
	        {
	            return new BWG4ChunkProviderAlpha(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA11)
	        {
	        	return new BWG4ChunkProviderInfdev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), true);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.BETA173)
	        {
	            return new BWG4ChunkProviderBeta(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), trySetting(0, 1) + 1);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS )
	        {
	            return new BWG4ChunkProviderSky(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), trySetting(0, 1) + 1, trySetting(1, 1) + 1);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE)
	        {
	            return new BWG4ChunkProviderCave(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), trySetting(0, 1) + 1);
	        }
	        else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
	        {
	        	return new BWG4ChunkProviderDesert(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND)
	        {
				int themeID = trySetting(0, 4) + 1; 
				int size = trySetting(1, 2) + 1; 
	            return new BWG4ChunkProviderIsland(this.worldObj, this.worldObj.getSeed(), themeID, size);
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
	        {
				int themeID = trySetting(0, 2) + 1; 
	            return new BWG4ChunkProviderSkyIsland(this.worldObj, this.worldObj.getSeed(), themeID);
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV)
	        {
				return new BWG4ChunkProviderCaveSurv(this.worldObj, this.worldObj.getSeed());
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK)
	        {
				int size = trySetting(0, 2) + 1; 
				return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), false, size);
	        }
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.WASTELAND)
			{
				return new BWG4ChunkProviderWasteland(this.worldObj, this.worldObj.getSeed());
			}
	        else
	        {
				return new BWG4ChunkProviderDefault(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	        }
		}
		else
		{
			return this.terrainType.getChunkGenerator(this.worldObj, worldObj.getWorldInfo().getGeneratorOptions());
		}
	}
	
	
	@Override
    public boolean canRespawnHere()
    {
        return true;
    }
	
	public boolean mayRandSpawn()
	{	
		if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK )
		{
			return false;
		}
		return true;
	}
	
	@Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        float var5 = 0f;
        if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV) 
        {
        	var5 = 0.8f;
        }
        else 
        {
        	var5 = super.calculateCelestialAngle(par1, par3);
        }
        return var5;
    }

	@Override
    public boolean isSurfaceWorld()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV)
		{
			return false;
		}
        return true;
    }
	
	@Override
    public Vec3 getFogColor(float par1, float par2)
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE)
		{
			return worldObj.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
		}
		else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
		{
			return worldObj.getWorldVec3Pool().getVecFromPool(0.25D, 0.25D, 0.25D);
		}
		
        return super.getFogColor(par1, par2);
    }
	
	@Override
    public float getCloudHeight()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV) 
		{
			if (trySetting(1, 2) == 1)
			{
				return -5F;
			}
		}

		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
		{
			return -5F;
		}
		else
		{
			return super.getCloudHeight();
		}	
    }

	@Override
    public int getAverageGroundLevel()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVE)
		{
			return 50;
		}
        return super.getAverageGroundLevel();
    }
    
	@Override
	public String getDimensionName() 
	{
		return "Overworld";
	}

	@Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int k = this.worldObj.getFirstUncoveredBlock(par1, par2);
        
        if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.WASTELAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV || (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND && (trySetting(0, 4) == 2 || trySetting(0, 4) == 3)))
        {
        	return true;
        }
        else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.BETA173 || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ALPHA12 || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INFDEV)
        {
        	if(k == Block.sand.blockID) { return true; } else { return false; }
        }
        else
        {
        	if(k == Block.grass.blockID || k == Block.dirt.blockID || k == Block.sand.blockID) { return true; } else { return false; }
        }
    }
	
	@Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == EnumGameType.ADVENTURE;
        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;
        
        boolean indevcenter = false;
        if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV)
        {
			if (trySetting(1, 2) != 2)
			{
				indevcenter = true;
			}
        }
        
        if (!worldObj.provider.hasNoSky && worldObj.getWorldInfo().getGameType() != EnumGameType.ADVENTURE)
        {
			if((BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND && trySetting(0, 4) != 4) || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND || indevcenter)
			{
				chunkcoordinates.posX = worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posZ = worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(0, 0) + 1;
			}
			else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV)
			{
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ) + 1;
			}
			else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK)
			{
				chunkcoordinates.posY = 84;
				chunkcoordinates.posZ = 0;
				chunkcoordinates.posX = 0;
			}
			else if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CAVESURV)
			{
				chunkcoordinates.posY = 42;
				chunkcoordinates.posZ = 0;
				chunkcoordinates.posX = 0;
			}
			else
			{
				chunkcoordinates.posX += worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posZ += worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
			}
        }

        return chunkcoordinates;
    }
	
	@Override
    public double getHorizon()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV) 
		{
			if (trySetting(1, 2) == 1)
			{
				return 0.0D;
			}
		}

		if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
		{
			return 77D;
		}
		if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
		{
			return 0.0D;
		}
		else
		{
			return super.getHorizon();
		}
    }
	
    @Override
    public boolean getWorldHasVoidParticles()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.INDEV) 
		{
			if (trySetting(1, 2) == 1)
			{
				return false;
			}
		}
		
		if(BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.DEADLYDESERT)
		{
			return false;
		}
		else
		{
			return super.getWorldHasVoidParticles();
		}
    }
	
	public int trySetting(int pos, int max)
	{
		if(BWG4GeneratorType.currentSettings != null) 
		{
			if(BWG4GeneratorType.currentSettings.length > pos) 
			{
				if(BWG4GeneratorType.currentSettings[pos] <= max) 
				{
					return BWG4GeneratorType.currentSettings[pos];
				}
			}
		}
		return 0;
	}
}
