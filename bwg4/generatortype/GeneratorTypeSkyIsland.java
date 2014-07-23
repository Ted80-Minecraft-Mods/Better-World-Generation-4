package bwg4.generatortype;

import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.api.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsButton;
import bwg4.world.ProviderBWG4;
import bwg4.world.ProviderBWG4Hell;
import bwg4.world.generators.ChunkGeneratorSkyIsland;
import bwg4.world.generators.ChunkGeneratorSurvivalNether;

public class GeneratorTypeSkyIsland extends GeneratorType
{
	public GeneratorTypeSkyIsland(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}
	
	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.settings.add(new GuiSettingsButton(new String[]{StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.default"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.snow"), StatCollector.translateToLocal("bwg4.setting.theme") + ": " + StatCollector.translateToLocal("bwg4.theme.jungle")}, new int[]{0, 1, 2}, 20, 80, gui.width)); 
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		int themeID = provider.trySetting(0, 2) + 1; 
		switch (themeID) 
		{
			case 2: return new WorldChunkManagerHell(BiomeList.COMMONsnow, 0.5F);
			default: return new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F);
		}
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
		int themeID = provider.trySetting(0, 2) + 1; 
        return new ChunkGeneratorSkyIsland(worldObj, worldObj.getSeed(), themeID);
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
		return new ChunkGeneratorSurvivalNether(provider.worldObj, provider.worldObj.getSeed());
    }
}
