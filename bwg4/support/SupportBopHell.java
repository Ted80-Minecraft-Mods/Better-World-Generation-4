package bwg4.support;

import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.world.ProviderBWG4Hell;

public class SupportBopHell 
{
	 public static WorldChunkManager getBopHellManager(ProviderBWG4Hell provider)
	 {
		 return new biomesoplenty.common.world.WorldChunkManagerBOPHell(provider.worldObj);
	 }
	 
	 public static IChunkProvider getBopHellProvider(ProviderBWG4Hell provider) 
	 {
		 return new biomesoplenty.common.world.ChunkProviderBOPHell(provider.worldObj, provider.worldObj.getSeed());
	 }
}
