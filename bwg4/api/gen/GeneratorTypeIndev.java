package bwg4.api.gen;

import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.api.biome.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsButton;
import bwg4.gui.GuiSettingsSlider;
import bwg4.world.ProviderBWG4;
import bwg4.world.generators.ChunkGeneratorIndev;

public class GeneratorTypeIndev extends GeneratorType
{
	public GeneratorTypeIndev(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}
	
	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.settings.add(new GuiSettingsButton(new String[]{StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.default"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.hell"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.paradise"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.woods"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.snow")}, new int[]{0, 1, 2, 3, 4}, 20, 80, gui.width));
		gui.settings.add(new GuiSettingsButton(new String[]{StatCollector.translateToLocal("bwg4.setting.type") + ": " + StatCollector.translateToLocal("bwg4.type.island"), StatCollector.translateToLocal("bwg4.setting.type") + ": " + StatCollector.translateToLocal("bwg4.type.floating"), StatCollector.translateToLocal("bwg4.setting.type") + ": " + StatCollector.translateToLocal("bwg4.type.inland")}, new int[]{0, 1, 2}, 21, 100, gui.width));
		gui.settings.add(new GuiSettingsSlider(new String[]{StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.small"), StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.default"), StatCollector.translateToLocal("bwg4.setting.size") + ": " + StatCollector.translateToLocal("bwg4.setting.large")}, new int[]{0, 1, 2}, 1, 22, 120, gui.width, 21, new int[]{0, 1}));
		gui.settings.add(new GuiSettingsSlider(new String[]{StatCollector.translateToLocal("bwg4.setting.layer") + ": 1", StatCollector.translateToLocal("bwg4.setting.layer") + ": 2", StatCollector.translateToLocal("bwg4.setting.layer") + ": 3", StatCollector.translateToLocal("bwg4.setting.layer") + ": 4", StatCollector.translateToLocal("bwg4.setting.layer") + ": 5"}, new int[]{0, 1, 2, 3, 4}, 1, 23, 140, gui.width, 21, new int[]{1}));
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		if(provider.trySetting(0, 4) == 4) 
		{ 
			return new WorldChunkManagerHell(BiomeList.CLASSICsnow, 0.5F); 
		}
		return new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.CLASSICnormal, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
		int themeID = provider.trySetting(0, 4) + 1;
		int typeID = provider.trySetting(1, 2) + 1;
		int size = provider.trySetting(2, 2) + 1;
		int layers = provider.trySetting(3, 4) + 1;
		return new ChunkGeneratorIndev(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled(), typeID, themeID, size, layers); 
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
		if (provider.trySetting(1, 2) == 1)
		{
			return -5F;
		}
		else
		{
	    	return 128F;
		}
    }

	@Override
    public int getAverageGroundLevel(ProviderBWG4 provider)
    {
    	return 64;
    }

	@Override
    public double getHorizon(ProviderBWG4 provider)
    {
		if (provider.trySetting(1, 2) == 1)
		{
			return 0.0D;
		}
		else
		{
			return 64D;
		}
    }

	@Override
    public boolean getWorldHasVoidParticles(ProviderBWG4 provider)
    {
		if (provider.trySetting(1, 2) == 1)
		{
			return false;
		}
		else
		{
			return true;
		}
    }
}
