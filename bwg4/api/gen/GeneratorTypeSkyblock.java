package bwg4.api.gen;

import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.api.biome.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsSlider;
import bwg4.world.ProviderBWG4;
import bwg4.world.ProviderBWG4Hell;
import bwg4.world.generators.ChunkGeneratorSkyBlock;
import bwg4.world.generators.ChunkGeneratorSurvivalNether;

public class GeneratorTypeSkyblock extends GeneratorType
{
	public GeneratorTypeSkyblock(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}

	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.settings.add(new GuiSettingsSlider(new String[]{StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.small"), StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.default"), StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.large")}, new int[]{0, 1, 2}, 1, 20, 80, gui.width));
		gui.setCredits(StatCollector.translateToLocal("credits.skyblock"), 108);
		
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		return new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
		int size = provider.trySetting(0, 2) + 1; 
		return new ChunkGeneratorSkyBlock(worldObj, worldObj.getSeed(), false, size);
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
	
	@Override
    public WorldChunkManager getHellChunkManager(ProviderBWG4Hell provider)
    {
    	return new WorldChunkManagerHell(BiomeList.COMMONnether, 0.0F);
    }
    
	@Override
    public IChunkProvider getHellChunkProvider(ProviderBWG4Hell provider)
    {
		return new ChunkGeneratorSkyBlock(provider.worldObj, provider.worldObj.getSeed(), true, 1);
    }
}
