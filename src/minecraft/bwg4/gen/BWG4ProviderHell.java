package bwg4.gen;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import biomesoplenty.world.ChunkProviderBOPNaturaHell;
import biomesoplenty.world.ChunkProviderBOPhell;
import biomesoplenty.world.WorldChunkManagerBOPhell;
import bwg4.mod_bwg4;
import bwg4.biomes.BWG4Biomes;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSky;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSkyBlock;
import bwg4.gen.chunkproviders.BWG4ChunkProviderSurvivalNether;
import bwg4.generatordata.BWG4GeneratorType;
import bwg4.support.Support;
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
        if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnether, 1.0F, 0.0F);
		}
		else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
        {
			this.worldChunkMgr = new WorldChunkManagerHell(BWG4Biomes.SURVIVALnether, 1.0F, 0.0F);
        }
		else
		{
			//if(Support.biomesOPlenty)
			//{
			//	this.worldChunkMgr = new WorldChunkManagerBOPhell(worldObj);
			//}
			//else
			//{
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 1.0F, 0.0F);
			//}
		}	
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

	@Override
    public IChunkProvider createChunkGenerator()
    {
		if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.ISLAND || BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYISLAND)
        {
			return new BWG4ChunkProviderSurvivalNether(this.worldObj, this.worldObj.getSeed());
		}
		else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYLANDS)
        {
			return new BWG4ChunkProviderSky(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), 4, 1);
        }
		else if (BWG4GeneratorType.currentGenerator == BWG4GeneratorType.SKYBLOCK)
        {
			return new BWG4ChunkProviderSkyBlock(this.worldObj, this.worldObj.getSeed(), true);
        }
		else
		{
			/*if(Support.biomesOPlenty)
			{
				if (Loader.isModLoaded("Natura"))
				{
					try 
					{
						return new ChunkProviderBOPNaturaHell(this.worldObj, this.worldObj.getSeed());
					}
					catch (Exception e) 
					{
						System.out.println("[BiomesOPlenty] There was an error while integrating Natura with Biomes O' Plenty!");
						e.printStackTrace(System.err);
					}
				}

				return new ChunkProviderBOPhell(this.worldObj, this.worldObj.getSeed());
			}
			else
			{*/
				return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
			//}
		}	
    }
	
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }
}
