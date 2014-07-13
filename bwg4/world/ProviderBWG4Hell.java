package bwg4.world;

import bwg4.BWG4;
import bwg4.api.biome.BiomeList;
import bwg4.api.gen.GeneratorType;
import bwg4.support.Support;
import bwg4.support.SupportBopHell;
import bwg4.world.generators.ChunkGeneratorSkyBlock;
import bwg4.world.generators.ChunkGeneratorSurvivalNether;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class ProviderBWG4Hell extends WorldProviderHell
{
	@Override
    public void registerWorldChunkManager()
    {	
		if(this.terrainType == BWG4.BWG4DEFAULT)
		{
			this.worldChunkMgr = GeneratorType.currentGenerator.getHellChunkManager(this);
		}
		else
		{
			if(Support.biomesoplenty)
			{
				try
				{
					this.worldChunkMgr = SupportBopHell.getBopHellManager(this);
				}
				catch(Exception e)
				{
					System.out.println("[BWG4] Failed to load BOP HELL");
					this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
				}
			}
			else
			{
				this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
			}
		}

        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = -1;
    }

	@Override
    public IChunkProvider createChunkGenerator()
    {
		if(this.terrainType == BWG4.BWG4DEFAULT)
		{
			return GeneratorType.currentGenerator.getHellChunkProvider(this);
		}
		else
		{
			if(Support.biomesoplenty)
			{
				IChunkProvider hell;
				
				try
				{
					hell = SupportBopHell.getBopHellProvider(this);
				}
				catch(Exception e)
				{
					System.out.println("[BWG4] Failed to load BOP HELL");
					hell = new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
				}
				
				return hell;
			}
			else
			{
				return new ChunkProviderHell(this.worldObj, this.worldObj.getSeed());
			}
		}
    }
	
	@SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        return Vec3.createVectorHelper(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
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
