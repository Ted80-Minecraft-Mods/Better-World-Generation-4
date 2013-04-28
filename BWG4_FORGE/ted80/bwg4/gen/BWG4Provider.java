package ted80.bwg4.gen;

import ted80.bwg4.gen.chunkproviders.*;
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
	@Override
    protected void registerWorldChunkManager()
    {
		//SKY SETTINGS
		if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4CAVE || this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4HARD)
		{
			hasNoSky = true;
		}
		else
		{
			hasNoSky = false;
		}
		
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
		else if (
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4DEFAULT ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4LARGE ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4BETA1 ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4BETA2 ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4ALPHA ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKY1 ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKY2 ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4CAVE ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4HARD ||
			this.worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4WASTE
			)
		{
			worldChunkMgr = new BWG4WorldChunkManager(worldObj);
		}
        else
        {
			this.worldChunkMgr = this.worldObj.getWorldInfo().getTerrainType().getChunkManager(this.worldObj);
        }
    }
	
	@Override
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
        else if (terrainType == WorldType.BWG4BETA1 || terrainType == WorldType.BWG4BETA2)
        {
			int worldID = 1; if(terrainType == WorldType.BWG4BETA2) { worldID = 2; }
            return new BWG4ChunkProviderBeta(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), worldID);
        }
        else if (terrainType == WorldType.BWG4SKY1 || terrainType == WorldType.BWG4SKY2 )
        {
			int worldID = 1; if(terrainType == WorldType.BWG4SKY2) { worldID = 2; } 
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
			return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), false, themeID);
        }
        else if (this.terrainType == WorldType.BWG4DEFAULT || this.terrainType == WorldType.BWG4LARGE)
        {
			return new BWG4ChunkProviderDefault(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
        }
		else
		{
			return this.terrainType.getChunkGenerator(this.worldObj, worldObj.getWorldInfo().getGeneratorOptions());
		}
	}
	
	public boolean mayRandSpawn()
	{	
		if(terrainType == WorldType.BWG4ISLAND || this.terrainType == WorldType.BWG4SKYLAND || this.terrainType == WorldType.BWG4SKYBLOCK )
		{
			return false;
		}
		return true;
	}
	
	@Override
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

	@Override
    public boolean isSurfaceWorld()
    {
		if (terrainType == WorldType.BWG4CAVE)
		{
			return false;
		}
        return true;
    }
	
	@Override
    public Vec3 getFogColor(float par1, float par2)
    {
        float var3 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        float var4 = 0.7529412F;
        float var5 = 0.84705883F;
        float var6 = 1.0F;
        var4 *= var3 * 0.94F + 0.06F;
        var5 *= var3 * 0.94F + 0.06F;
        var6 *= var3 * 0.91F + 0.09F;

		if (terrainType == WorldType.BWG4CAVE || terrainType == WorldType.BWG4HARD)
		{
			return worldObj.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
		}
		
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)var4, (double)var5, (double)var6);
    }
	
	@Override
    public float getCloudHeight()
    {
		if (terrainType == WorldType.BWG4INDEV2 || terrainType == WorldType.BWG4SKYLAND || terrainType == WorldType.BWG4SKYBLOCK || terrainType == WorldType.BWG4SKY1 || terrainType == WorldType.BWG4SKY2)
		{
			return -5F;
		}
		else
		{
			return 128.0F;
		}	
    }

	@Override
    public int getAverageGroundLevel()
    {
		if (terrainType == WorldType.BWG4CAVE)
		{
			return 50;
		}
        return this.terrainType == WorldType.FLAT ? 4 : 64;
    }
    
	@Override
	public String getDimensionName() 
	{
		return "Overworld";
	}
	
	@Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == EnumGameType.ADVENTURE;
        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;
        
        if (!worldObj.provider.hasNoSky && worldObj.getWorldInfo().getGameType() != EnumGameType.ADVENTURE)
        {
			if(worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4INDEV1 || worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4INDEV2)
			{
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ) + 1;
			}
			else if(worldObj.getWorldInfo().getTerrainType() == WorldType.BWG4SKYBLOCK)
			{
				chunkcoordinates.posY = 84;
				chunkcoordinates.posZ = 0;
				chunkcoordinates.posX = 0;
			}
			else
			{
				chunkcoordinates.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
				chunkcoordinates.posY = worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
			}
        }

        return chunkcoordinates;
    }

	@Override
    public boolean canBlockFreeze(int par1, int par2, int par3, boolean par4)
    {
        BiomeGenBase var5 = this.getBiomeGenForCoords(par1, par3);
        float var6 = var5.getFloatTemperature();
		int snowlevel = var5.getSnowLevel();

        if (var6 > 0.15F)
        {
            return false;
        }
        else
        {
            if (par2 > snowlevel && par2 >= 0 && par2 < 256 && worldObj.getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10)
            {
                int var7 = worldObj.getBlockId(par1, par2, par3);

                if ((var7 == Block.waterStill.blockID || var7 == Block.waterMoving.blockID) && worldObj.getBlockMetadata(par1, par2, par3) == 0)
                {
                    if (!par4)
                    {
                        return true;
                    }

                    boolean var8 = true;

                    if (var8 && worldObj.getBlockMaterial(par1 - 1, par2, par3) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && worldObj.getBlockMaterial(par1 + 1, par2, par3) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && worldObj.getBlockMaterial(par1, par2, par3 - 1) != Material.water)
                    {
                        var8 = false;
                    }

                    if (var8 && worldObj.getBlockMaterial(par1, par2, par3 + 1) != Material.water)
                    {
                        var8 = false;
                    }

                    if (!var8)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

	@Override
    public boolean canSnowAt(int par1, int par2, int par3)
    {
        BiomeGenBase biomegenbase = this.getBiomeGenForCoords(par1, par3);
        float f = biomegenbase.getFloatTemperature();
		int snowlevel = biomegenbase.getSnowLevel();

        if (f > 0.15F)
        {
            return false;
        }
        else
        {
            if (par2 > snowlevel && par2 >= 0 && par2 < 256 && worldObj.getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10)
            {
                int l = worldObj.getBlockId(par1, par2 - 1, par3);
                int i1 = worldObj.getBlockId(par1, par2, par3);

                if (i1 == 0 && Block.snow.canPlaceBlockAt(worldObj, par1, par2, par3) && l != 0 && l != Block.ice.blockID && Block.blocksList[l].blockMaterial.blocksMovement())
                {
                    return true;
                }
            }

            return false;
        }
    }
	
	@Override
    public double getHorizon()
    {
		if(terrainType == WorldType.BWG4INDEV2 || terrainType == WorldType.BWG4SKYLAND || terrainType == WorldType.BWG4SKYBLOCK || terrainType == WorldType.BWG4SKY1 || terrainType == WorldType.BWG4SKY2)
		{
			return 0.0D;
		}
		else
		{
			return this.terrainType.getHorizon(worldObj);
		}
    }
}
