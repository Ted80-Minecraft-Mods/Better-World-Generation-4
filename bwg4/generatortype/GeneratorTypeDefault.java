package bwg4.generatortype;

import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import bwg4.api.BiomeList;
import bwg4.gui.GuiGeneratorSettings;
import bwg4.gui.GuiSettingsButton;
import bwg4.gui.GuiSettingsSlider;
import bwg4.world.ChunkManagerDefault;
import bwg4.world.ChunkManagerOld;
import bwg4.world.ProviderBWG4;
import bwg4.world.generators.ChunkGeneratorDefault;

public class GeneratorTypeDefault extends GeneratorType
{
	public GeneratorTypeDefault(int id, int cat, String name, boolean c) 
	{
		super(id, cat, name, c);
	}
	
	@Override
	public boolean getSettings(GuiGeneratorSettings gui)
	{
		gui.BUTTON_BIOMELIST.visible = true;
		//gui.BUTTON_WORLDSETTINGS.visible = true;
		//gui.settings.add(new GuiSettingsSlider(new String[]{"Biome size: 1", "Biome size: 2", "Biome size: 3", "Biome size: 4 (default)", "Biome size: 5", "Biome size: 6 (Large)", "Biome size: 7", "Biome size: 8"}, new int[]{1,2,3,4,5,6,7,8}, 3, 20, 120, gui.width));
		//gui.settings.add(new GuiSettingsButton(new String[]{"Amplified: OFF", "Amplified: ON"}, new int[]{0,1}, 21, 140, gui.width));
		return true;
	}
	
	@Override
	public WorldChunkManager getServerWorldChunkManager(ProviderBWG4 provider, World worldObj)
    {
		return new ChunkManagerDefault(worldObj, true);
    }

	@Override
	public WorldChunkManager getClientWorldChunkManager(ProviderBWG4 provider)
    {
		return new WorldChunkManagerHell(BiomeList.DEFAULTplains, 0.5F);
    }

	@Override
    public IChunkProvider getChunkGenerator(ProviderBWG4 provider, World worldObj)
    {	
    	return new ChunkGeneratorDefault(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
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
