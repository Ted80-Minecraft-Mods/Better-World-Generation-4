package bwg4.support;

import cpw.mods.fml.common.Loader;

public class Support 
{
	public static void init()
	{
		//Try ExtraBiomesXL support
		try
		{
			if (Loader.isModLoaded("ExtrabiomesXL"))
			{
				SupportExtraBiomesXL.init();
			}
		}
		catch(Exception e)
		{
			System.out.println("[BWG4] Cannot load ExtraBiomesXL support!");
		}
		
		//Try Biomes 'O Plenty support
		try
		{
			if (Loader.isModLoaded("BiomesOPlenty"))
			{
				SupportBiomesOPlenty.init();
			}
		}
		catch(Exception e)
		{
			System.out.println("[BWG4] Cannot load Biomes 'O Plenty support!");
		}
		
		//Try Highlands support
		try
		{
			if(Loader.isModLoaded("Highlands"))
			{
				SupportHighLands.init();
			}
		}
		catch(Exception e)
		{
			System.out.println("[BWG4] Cannot load Highlands support!");
		}
	}
}
