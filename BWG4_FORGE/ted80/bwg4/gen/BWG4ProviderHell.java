package ted80.bwg4.gen;

import ted80.bwg4.gen.chunkproviders.BWG4ChunkProviderSky;
import ted80.bwg4.gen.chunkproviders.BWG4ChunkProviderSkyBlock;
import ted80.bwg4.gen.chunkproviders.BWG4ChunkProviderSurvivalNether;
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

	@Override
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
}
