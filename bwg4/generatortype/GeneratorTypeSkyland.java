package bwg4.generatortype;

import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.api.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsButton;
import bwg4.world.ChunkManagerOld;
import bwg4.world.ProviderBWG4;
import bwg4.world.generators.ChunkGeneratorBeta;
import bwg4.world.generators.ChunkGeneratorSky;

public class GeneratorTypeSkyland extends GeneratorType
{
	public GeneratorTypeSkyland(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}

	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		//gui.settings.add(new GuiSettingsButton(new String[]{"Biomes: Beta 1.7.3", "Biomes: Better Default"}, new int[]{1, 0}, 20, 80, gui.width));
		return false;
	}

	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		return new ChunkManagerOld(worldObj, true);
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
		return new ChunkGeneratorSky(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled(), provider.trySetting(0, 1) + 1, 0);
    }

	@Override
    public boolean getRandSpawn(ProviderBWG4 provider)
    {
    	return true;
    }

	@Override
    public float getCalculateCelestialAngle(ProviderBWG4 provider, long par1, float par3)
    {
    	return 0F;
    }

	@Override
    public boolean isSurfaceWorld(ProviderBWG4 provider)
    {
    	return true;
    }

	@Override
    public Vec3 getFogColor(ProviderBWG4 provider, World worldObj, float par1, float par2)
    {
    	return null;
    }

	@Override
    public float getCloudHeight(ProviderBWG4 provider)
    {
		return -5F;
    }

	@Override
    public int getAverageGroundLevel(ProviderBWG4 provider)
    {
    	return 64;
    }

	@Override
    public double getHorizon(ProviderBWG4 provider)
    {
		return 0.0D;
    }

	@Override
    public boolean getWorldHasVoidParticles(ProviderBWG4 provider)
    {
		return false;
    }
}
