package biomesoplenty.common.world;

import java.util.List;
import java.util.Random;

import com.mojang.realmsclient.dto.McoServer.WorldType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerBOPHell extends WorldChunkManager
{
	protected WorldChunkManagerBOPHell()
	{
	}

	public WorldChunkManagerBOPHell(long par1, WorldType par3WorldType)
	{
	}

	public WorldChunkManagerBOPHell(World par1World)
	{
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getBiomesToSpawnIn()
	{
		return null;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return null;
	}

	@Override
	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getTemperatureAtHeight(float par1, int par2)
	{
		return par1;
	}

	public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
	{
		return null;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	{
		return null;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	{
		return null;
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
	{
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
	{
		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
	{
		return null;
	}

	@Override
	public void cleanupCache()
	{
	}
}
