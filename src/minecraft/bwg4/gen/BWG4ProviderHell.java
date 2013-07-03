package bwg4.gen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSky;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSkyBlock;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSurvivalNether;
import bwg4.generatordata.GeneratorType;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class BWG4ProviderHell extends WorldProviderHell
{
	@Override
    public void registerWorldChunkManager()
    {	
        if (GeneratorType.Current == GeneratorType.BWG4ISLAND || GeneratorType.Current == GeneratorType.BWG4SKYLAND)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnether, 1.0F, 0.0F);
		}
		else if (GeneratorType.Current == GeneratorType.BWG4SKY1 || GeneratorType.Current == GeneratorType.BWG4SKY2)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnether, 1.0F, 0.0F);
        }
		else
		{
			this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
		}	
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

	@Override
    public IChunkProvider createChunkGenerator()
    {
		if (GeneratorType.Current == GeneratorType.BWG4ISLAND || GeneratorType.Current == GeneratorType.BWG4SKYLAND)
        {
			return new BWG4ChunkProviderSurvivalNether(this.worldObj, this.worldObj.getSeed());
		}
		else if (GeneratorType.Current == GeneratorType.BWG4SKY1 || GeneratorType.Current == GeneratorType.BWG4SKY2)
        {
			return new BWG4ChunkProviderSky(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), 4);
        }
		else if (GeneratorType.Current == GeneratorType.BWG4SKYBLOCK)
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
			
			return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), true, themeID);
        }
		else
		{
			return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
		}	
    }
	
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }
}
