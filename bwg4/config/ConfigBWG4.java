package bwg4.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigBWG4 
{
	public static Configuration config;
	public static int[] biomeIDs = new int[16];
	
	public static void init(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		for(int c = 0; c < biomeIDs.length; c++)
		{
			biomeIDs[c] = 80 + c;
		}
		
		try 
		{
			config.load();

			//80 - 129
			
			//BETA-ALPHA BIOMES
			biomeIDs[0] = config.get("1 - Beta & Alpha", "Desert", 80).getInt();
			biomeIDs[1] = config.get("1 - Beta & Alpha", "Forest", 81).getInt();
			biomeIDs[2] = config.get("1 - Beta & Alpha", "Plains", 82).getInt();
			biomeIDs[3] = config.get("1 - Beta & Alpha", "Rainforest", 83).getInt();
			biomeIDs[4] = config.get("1 - Beta & Alpha", "Savanna", 84).getInt();
			biomeIDs[5] = config.get("1 - Beta & Alpha", "SeasonalForest", 85).getInt();
			biomeIDs[6] = config.get("1 - Beta & Alpha", "Shrubland", 86).getInt();
			biomeIDs[7] = config.get("1 - Beta & Alpha", "Swampland", 87).getInt();
			biomeIDs[8] = config.get("1 - Beta & Alpha", "Taiga", 88).getInt();
			biomeIDs[9] = config.get("1 - Beta & Alpha", "Tundra", 89).getInt();

			//INFDEV-INDEV-CLASSIC BIOMES
			biomeIDs[10] = config.get("2 - Infdev & Indev", "Classic", 90).getInt();
			biomeIDs[11] = config.get("2 - Infdev & Indev", "ClassicSnow", 91).getInt();

			//ISLAND-SKYLAND-SKYBLOCK
			biomeIDs[12] = config.get("3 - Common Biomes", "BWG4_com1", 92).getInt();
			biomeIDs[13] = config.get("3 - Common Biomes", "BWG4_com2", 93).getInt();
			biomeIDs[14] = config.get("3 - Common Biomes", "BWG4_com3", 94).getInt();
			biomeIDs[15] = config.get("3 - Common Biomes", "BWG4_nether", 95).getInt();
		}
		catch (Exception e) 
		{
			for(int c = 0; c < biomeIDs.length; c++)
			{
				biomeIDs[c] = 80 + c;
			}
		}
		finally 
		{
			if (config.hasChanged()) 
			{
				config.save();
			}
		}
	}
}
