package bwg4.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import bwg4.api.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsButton;
import bwg4.gui.GuiSettingsSlider;
import bwg4.support.Support;
import bwg4.world.ProviderBWG4;
import bwg4.world.ProviderBWG4Hell;
import bwg4.world.generators.ChunkGeneratorPlanetoids;
import bwg4.world.generators.ChunkGeneratorSkyBlock;

public class GeneratorTypePlanetoids extends GeneratorType
{
	public GeneratorTypePlanetoids(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}

	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.settings.add(new GuiSettingsButton(new String[]{StatCollector.translateToLocal("bwg4.setting.water") + ": " + StatCollector.translateToLocal("bwg4.setting.off"), StatCollector.translateToLocal("bwg4.setting.water") + ": " + StatCollector.translateToLocal("bwg4.setting.on")}, new int[]{0, 1}, 20, 80, gui.width));
		gui.setCredits(StatCollector.translateToLocal("credits.planet"), 108);
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		return new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F);
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
		int water = provider.trySetting(0, 2); 
		return new ChunkGeneratorPlanetoids(worldObj, worldObj.getSeed(), water == 1 ? true : false, 0);
    }

	@Override
    public boolean getRandSpawn(ProviderBWG4 provider)
    {
    	return false;
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
		return 200F;
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
	
	@Override
	public WorldChunkManager getHellChunkManager(ProviderBWG4Hell provider)
    {
    	return new WorldChunkManagerHell(BiomeList.COMMONnether, 0.0F);
    }
    
	@Override
    public IChunkProvider getHellChunkProvider(ProviderBWG4Hell provider)
    {
		int water = provider.trySetting(0, 2); 
    	return new ChunkGeneratorPlanetoids(provider.worldObj, provider.worldObj.getSeed(), water == 1 ? true : false, 1);
    }
}
