package bwg4.support;

import cpw.mods.fml.common.Loader;

public class Support 
{
	public static void init()
	{
		try
		{
			if (Loader.isModLoaded("ExtrabiomesXL"))
			{
				SupportExtraBiomesXL.init();
			}
			else
			{
				System.out.println("BWG4: ExtraBiomesXL was not found!");
			}
		}
		catch(Exception e)
		{
			System.out.println("BWG4: Failed to load ExtraBiomesXL!");
		}
		
		try
		{
			if (Loader.isModLoaded("BiomesOPlenty"))
			{
				SupportBiomesOPlenty.init();
			}
			else
			{
				System.out.println("BWG4: Biomes O' plenty was not found!");
			}
		}
		catch(Exception e)
		{
			System.out.println("BWG4: Failed to load Biomes O' plenty!");
		}
	}
}
