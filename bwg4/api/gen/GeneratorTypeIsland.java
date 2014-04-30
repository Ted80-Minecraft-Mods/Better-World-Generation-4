package bwg4.api.gen;

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
import bwg4.world.generators.ChunkGeneratorIsland;

public class GeneratorTypeIsland extends GeneratorType
{
	public GeneratorTypeIsland(int id, int cat, String name, String screen, boolean c) 
	{
		super(id, cat, name, screen, c);
	}

	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.settings.add(new GuiSettingsButton(new String[]{"Theme: Default", "Theme: Tropical", "Theme: Paradise"}, new int[]{0, 1, 4}, 20, 80, gui.width)); //"Theme: Hell", "Theme: Iceberg",   2, 3,
		gui.settings.add(new GuiSettingsSlider(new String[]{"Size: Small", "Size: Default", "Size: Large"}, new int[]{0, 1, 2}, 1, 21, 100, gui.width, 20, new int[]{0, 1})); //, 2, 3
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {				
		int themeID = provider.trySetting(0, 4) + 1; 
		switch (themeID) 
		{
			case 5: return new WorldChunkManagerHell(BiomeList.COMMONnormal2, 0.5F);
			default: return new WorldChunkManagerHell(BiomeList.COMMONnormal1, 0.5F);
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
		int themeID = provider.trySetting(0, 4) + 1; 
		int size = provider.trySetting(1, 2) + 1; 
        return new ChunkGeneratorIsland(worldObj, worldObj.getSeed(), themeID, size);
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
    	return 128F;
    }

	@Override
    public int getAverageGroundLevel(ProviderBWG4 provider)
    {
    	return 64;
    }

	@Override
    public double getHorizon(ProviderBWG4 provider)
    {
    	return 64D;
    }

	@Override
    public boolean getWorldHasVoidParticles(ProviderBWG4 provider)
    {
    	return true;
    }
}
