package bwg4.world;

import bwg4.BWG4;
import bwg4.api.biome.BiomeList;
import bwg4.api.biome.BiomeManager;
import bwg4.api.gen.GeneratorType;
import bwg4.data.DecodeGeneratorString;
import bwg4.world.generators.ChunkGeneratorAlpha;
import bwg4.world.generators.ChunkGeneratorBeta;
import bwg4.world.generators.ChunkGeneratorIndev;
import bwg4.world.generators.ChunkGeneratorInfdev;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class ProviderBWG4 extends WorldProvider
{
	@Override
    protected void registerWorldChunkManager()
    {
		if(this.worldObj.getWorldInfo().getTerrainType() == BWG4.BWG4DEFAULT && !worldObj.isRemote)
		{
			System.out.println("BWG4 GENERATORSTRING: " + worldObj.getWorldInfo().getGeneratorOptions());
			if(worldObj.getWorldInfo().getGeneratorOptions().length() > 2)
			{
				DecodeGeneratorString.decode(worldObj.getWorldInfo().getGeneratorOptions());
				if(GeneratorType.currentGenerator == GeneratorType.DEFAULT)
				{
					if(GeneratorType.biomestring != null)
					{
						if(GeneratorType.biomestring.length() < 4)
						{
							GeneratorType.biomestring = BiomeManager.getDefaultString();
						}
					}
					else
					{
						GeneratorType.biomestring = BiomeManager.getDefaultString();
					}
				}
			}
			else
			{
				DecodeGeneratorString.decode("BetterDefault#4&0#" + BiomeManager.getDefaultString());
			}
			
			this.worldChunkMgr = GeneratorType.currentGenerator.getServerWorldChunkManager(this, this.worldObj);

			/*
	        if (GeneratorType.currentGenerator == GeneratorType.INFDEV || GeneratorType.currentGenerator == GeneratorType.ALPHA11)
	        {
				int themeID = trySetting(0, 1);
				if(themeID == 0) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICsnow, 0.5F); }
	        }
			else if (GeneratorType.currentGenerator == GeneratorType.ALPHA12 || GeneratorType.currentGenerator == GeneratorType.BETA173 || GeneratorType.currentGenerator == GeneratorType.SKYLANDS)
			{
				worldChunkMgr = new ChunkManagerOld(worldObj, true);
			}
	        else if (GeneratorType.currentGenerator == GeneratorType.INDEV)
	        {
				int themeID = trySetting(0, 4);
				if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICsnow, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F); }
	        }
			else
			{
				worldChunkMgr = new ChunkManagerOld(worldObj, true);
			}
			
			/*
			
	        if (GeneratorType.currentGenerator == GeneratorType.INFDEV || GeneratorType.currentGenerator == GeneratorType.ALPHA11)
	        {
				int themeID = trySetting(0, 1);
				if(themeID == 0) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICsnow, 0.5F); }
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.INDEV)
	        {
				int themeID = trySetting(0, 4);
				if(themeID == 4) { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICsnow, 0.5F); }
				else { this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F); }
	        }
			else if (GeneratorType.currentGenerator == GeneratorType.ISLAND)
			{
				int themeID = trySetting(0, 4) + 1; 
				switch (themeID) 
				{
					case 5: worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F); break;
					default: worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F); break;
				}
			}
			else if (GeneratorType.currentGenerator == GeneratorType.SKYISLAND)
			{
				int themeID = trySetting(0, 2) + 1; 
				switch (themeID) 
				{
					case 2: worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONsnow, 0.5F); break;
					default: worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F); break;
				}
			}
			else if (GeneratorType.currentGenerator == GeneratorType.SKYBLOCK)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
			}
			else if (GeneratorType.currentGenerator == GeneratorType.ALPHA12 || GeneratorType.currentGenerator == GeneratorType.BETA173 || GeneratorType.currentGenerator == GeneratorType.SKYLANDS)
			{
				worldChunkMgr = new ChunkManagerOld(worldObj, true);
			}
			/*else if (GeneratorType.currentGenerator == GeneratorType.WASTELAND)
			{
				worldChunkMgr = new BWG4ChunkManagerWasteland(worldObj);
			}*//*
	        else if (GeneratorType.currentGenerator == GeneratorType.CAVESURV)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.CAVE)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.CITY)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
	        }*/
	        /*else if (GeneratorType.currentGenerator == GeneratorType.DEADLYDESERT)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.ADVENTUREdesert, 0.5F, 0.5F);
	        }*//*
			else
			{
				//worldChunkMgr = new BWG4ChunkManagerDefault(worldObj, true);
				worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
			}
	        *//*
	        
	        */
		}
		else if(worldObj.isRemote && GeneratorType.currentGenerator != null)
		{
			this.worldChunkMgr = GeneratorType.currentGenerator.getClientWorldChunkManager(this);
			
			/*
	        if (GeneratorType.currentGenerator == GeneratorType.INFDEV || GeneratorType.currentGenerator == GeneratorType.ALPHA11 || GeneratorType.currentGenerator == GeneratorType.INDEV)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
	        }
			else if (GeneratorType.currentGenerator == GeneratorType.ISLAND || GeneratorType.currentGenerator == GeneratorType.SKYISLAND || GeneratorType.currentGenerator == GeneratorType.SKYBLOCK)
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
			}
			else if (GeneratorType.currentGenerator == GeneratorType.CAVESURV)
			{
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
			}
			else if (GeneratorType.currentGenerator == GeneratorType.WASTELAND)
			{
				worldChunkMgr = new BWG4ChunkManagerWasteland(worldObj);
			}*//*
	        else if (GeneratorType.currentGenerator == GeneratorType.CAVE)
	        {
	        	hasNoSky = true;
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.CITY)
	        {
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
	        }*/
	        /*else if (GeneratorType.currentGenerator == GeneratorType.DEADLYDESERT)
	        {
	        	this.worldChunkMgr = new WorldChunkManagerHell(BiomeList.ADVENTUREdesert, 0.5F);
	        }
			else
			{
				worldChunkMgr = new WorldChunkManagerHell(BiomeList.OLDplains, 0.5F);
			}*/
		}
		else 
		{
			GeneratorType.currentGenerator = null;
			GeneratorType.biomestring = null;
			GeneratorType.currentSettings = null;

	        if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT)
	        {
	            FlatGeneratorInfo var1 = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
	            this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.getBiome(var1.getBiome()), 0.5F);
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
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getChunkGenerator(this, this.worldObj);
		}
		return this.terrainType.getChunkGenerator(this.worldObj, worldObj.getWorldInfo().getGeneratorOptions());
		
		/*
  		if(terrainType == BWG4.BWG4DEFAULT)
		{
	        if (GeneratorType.currentGenerator == GeneratorType.INFDEV)
	        {
	            return new ChunkGeneratorInfdev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), false);
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.INDEV) 
			{ 
				int themeID = trySetting(0, 4) + 1;
				int typeID = trySetting(1, 2) + 1;
				int size = trySetting(2, 2) + 1;
				int layers = trySetting(3, 4) + 1;
				return new ChunkGeneratorIndev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), typeID, themeID, size, layers); 
			}
	        else if (GeneratorType.currentGenerator == GeneratorType.ALPHA12)
	        {
	            return new ChunkGeneratorAlpha(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.ALPHA11)
	        {
	        	return new ChunkGeneratorInfdev(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), true);
	        }
	        else if (GeneratorType.currentGenerator == GeneratorType.BETA173)
	        {
	            return new ChunkGeneratorBeta(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), trySetting(0, 1) + 1);
	        }
  			else
  			{
  				return new ChunkGeneratorBeta(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), trySetting(0, 1) + 1);
  			}
  			
  			
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
	            return new BWG4ChunkProviderCave(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
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
			else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.CITY)
			{
				return new BWG4ChunkProviderCity(this.worldObj, this.worldObj.getSeed());
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
		*/
    }
	
	@Override
    public boolean canRespawnHere()
    {
        return true;
    }
	
	public boolean mayRandSpawn()
	{	
		return GeneratorType.currentGenerator.getRandSpawn(this);
	}
	
	@Override
    public float calculateCelestialAngle(long par1, float par3)
    {
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			if(GeneratorType.currentGenerator.customCelestialAngle)
			{
				return GeneratorType.currentGenerator.getCalculateCelestialAngle(this, par1, par3);
			}
		}
		return super.calculateCelestialAngle(par1, par3);
    }

	@Override
    public boolean isSurfaceWorld()
    {
		return GeneratorType.currentGenerator.isSurfaceWorld(this);
    }
	
	@Override
    public Vec3 getFogColor(float par1, float par2)
    {
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			if(GeneratorType.currentGenerator.customFogColor)
			{
				return GeneratorType.currentGenerator.getFogColor(this, worldObj, par1, par2);
			}
		}
		return super.getFogColor(par1, par2);
    }
	
	@Override
    public float getCloudHeight()
    {
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getCloudHeight(this);
		}
		return super.getCloudHeight();
    }

	@Override
    public int getAverageGroundLevel()
    {
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getAverageGroundLevel(this);
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
		return true;
		
		/*
        Block k = this.worldObj.getTopBlock(par1, par2);
        
        if(GeneratorType.currentGenerator == GeneratorType.WASTELAND || GeneratorType.currentGenerator == GeneratorType.CAVESURV || (GeneratorType.currentGenerator == GeneratorType.ISLAND && (trySetting(0, 4) == 2 || trySetting(0, 4) == 3)))
        {
        	return true;
        }
        else if(GeneratorType.currentGenerator == GeneratorType.ISLAND || GeneratorType.currentGenerator == GeneratorType.BETA173 || GeneratorType.currentGenerator == GeneratorType.ALPHA12 || GeneratorType.currentGenerator == GeneratorType.INFDEV)
        {
        	if(k == Blocks.sand) { return true; } else { return false; }
        }
        else
        {
        	if(k == Blocks.grass || k == Blocks.dirt || k == Blocks.sand) { return true; } else { return false; }
        }*/
    }
	
	@Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == GameType.ADVENTURE;
        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;
        
        boolean indevcenter = false;
        if(GeneratorType.currentGenerator == GeneratorType.INDEV)
        {
			if (trySetting(1, 2) != 2)
			{
				indevcenter = true;
			}
        }
        
        if (!worldObj.provider.hasNoSky && worldObj.getWorldInfo().getGameType() != GameType.ADVENTURE)
        {
			if((GeneratorType.currentGenerator == GeneratorType.ISLAND && trySetting(0, 4) != 4) || GeneratorType.currentGenerator == GeneratorType.SKYISLAND || indevcenter)
			{
				chunkcoordinates.posX = worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posZ = worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(0, 0) + 1;
			}
			else if(GeneratorType.currentGenerator == GeneratorType.INDEV)
			{
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ) + 1;
			}
			else if(GeneratorType.currentGenerator == GeneratorType.SKYBLOCK)
			{
				chunkcoordinates.posY = 84;
				chunkcoordinates.posZ = 0;
				chunkcoordinates.posX = 0;
			}
			else if(GeneratorType.currentGenerator == GeneratorType.CAVESURV)
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
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getHorizon(this);
		}
		return super.getHorizon();
    }
	
    @Override
    public boolean getWorldHasVoidParticles()
    {
		if(terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getWorldHasVoidParticles(this);
		}
		return super.getWorldHasVoidParticles();
    }
	
	public int trySetting(int pos, int max)
	{
		if(GeneratorType.currentSettings != null) 
		{
			if(GeneratorType.currentSettings.length > pos) 
			{
				if(GeneratorType.currentSettings[pos] <= max) 
				{
					return GeneratorType.currentSettings[pos];
				}
			}
		}
		return 0;
	}
}
