package bwg4.config;

import java.io.File;
import java.util.logging.Level;

import bwg4.biomes.BWG4BiomesBeta;
import bwg4.biomes.BWG4BiomesDefault;
import bwg4.biomes.BWG4BiomesClassic;
import bwg4.biomes.BWG4BiomesSurvival;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

public class BWG4Config 
{
	public static Configuration config;
	public static int[] biomeIDs = new int[79];
	
	public static boolean OLD_ENABLED = true;
	public static boolean CLASSIC_ENABLED = true;
	public static boolean SURVIVAL_ENABLED = true;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);

		try 
		{
			config.load();

			for(int c = 0; c < biomeIDs.length; c++)
			{
				biomeIDs[c] = 80 + c;
			}

			//BETA-ALPHA BIOMES
			biomeIDs[0] = config.get("1 - Beta & Alpha", "Desert", 50).getInt();
			biomeIDs[1] = config.get("1 - Beta & Alpha", "Forest", 51).getInt();
			biomeIDs[2] = config.get("1 - Beta & Alpha", "Plains", 52).getInt();
			biomeIDs[3] = config.get("1 - Beta & Alpha", "Rainforest", 53).getInt();
			biomeIDs[4] = config.get("1 - Beta & Alpha", "Savanna", 54).getInt();
			biomeIDs[5] = config.get("1 - Beta & Alpha", "SeasonalForest", 55).getInt();
			biomeIDs[6] = config.get("1 - Beta & Alpha", "Shrubland", 56).getInt();
			biomeIDs[7] = config.get("1 - Beta & Alpha", "Swampland", 57).getInt();
			biomeIDs[8] = config.get("1 - Beta & Alpha", "Taiga", 58).getInt();
			biomeIDs[9] = config.get("1 - Beta & Alpha", "Tundra", 59).getInt();
			
			//INFDEV-INDEV-CLASSIC BIOMES
			biomeIDs[10] = config.get("2 - Infdev & Indev", "Classic", 60).getInt();
			biomeIDs[11] = config.get("2 - Infdev & Indev", "ClassicSnow", 61).getInt();
			
			//ISLAND-SKYLAND-SKYBLOCK
			biomeIDs[12] = config.get("3 - Extreme Survival", "Survival1", 62).getInt();
			biomeIDs[13] = config.get("3 - Extreme Survival", "Survival2", 63).getInt();
			biomeIDs[14] = config.get("3 - Extreme Survival", "SurvivalSnow", 64).getInt();
			biomeIDs[15] = config.get("3 - Extreme Survival", "SurvivalNether", 65).getInt();
			
			//ADVENTURE
			biomeIDs[16] = config.get("4 - Adventure", "DeadlyDesert", 66).getInt();
			
			//CAVEDIMENSION
			//biomeIDs[15] = config.get("Cave Dimension", "CaveDimension", 67).getInt();
			
			//WASTELAND

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
