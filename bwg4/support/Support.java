package bwg4.support;

import cpw.mods.fml.common.Loader;

public class Support 
{
	public static boolean biomesoplenty = false;
	public static boolean extrabiomesxl = false;
	public static boolean highlands = false;
	
	public static void load()
	{
		if (Loader.isModLoaded("BiomesOPlenty"))
		{
			biomesoplenty = true;
		}
	}
}
