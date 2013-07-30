package bwg4.config;

import java.io.File;
import java.util.logging.Level;

import bwg4.biomes.BWG4BiomesAlpha;
import bwg4.biomes.BWG4BiomesBeta;
import bwg4.biomes.BWG4BiomesDefault;
import bwg4.biomes.BWG4BiomesIndev;
import bwg4.biomes.BWG4BiomesInfdev;
import bwg4.biomes.BWG4BiomesSurvival;

import cpw.mods.fml.common.FMLLog;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

public class BWG4Config 
{
	public static Configuration config;
	public static int[] biomeIDs = new int[73];
	
	public static boolean alphagrass = true;
	
	public static void init(File configFile) 
	{
		config = new Configuration(configFile);

		try 
		{
			config.load();
			
			//TEXTURES
			alphagrass = config.get("Textures", "AlphaGrass", true).getBoolean(true);
			
			//BETA BIOMES
			biomeIDs[0] = config.get("Biome IDs", "BETArainforest", 80).getInt();
			biomeIDs[1] = config.get("Biome IDs", "BETAswampland", 81).getInt();
			biomeIDs[2] = config.get("Biome IDs", "BETAseasonalForest", 82).getInt();
			biomeIDs[3] = config.get("Biome IDs", "BETAforest", 83).getInt();
			biomeIDs[4] = config.get("Biome IDs", "BETAsavanna", 84).getInt();
			biomeIDs[5] = config.get("Biome IDs", "BETAshrubland", 85).getInt();
			biomeIDs[6] = config.get("Biome IDs", "BETAtaiga", 86).getInt();
			biomeIDs[7] = config.get("Biome IDs", "BETAdesert", 87).getInt();
			biomeIDs[8] = config.get("Biome IDs", "BETAplains", 88).getInt();
			biomeIDs[9] = config.get("Biome IDs", "BETAtundra", 89).getInt();
			
			//ALPHA BIOMES
			biomeIDs[10] = config.get("Biome IDs", "ALPHArainforest", 90).getInt();
			biomeIDs[11] = config.get("Biome IDs", "ALPHAswampland", 91).getInt();
			biomeIDs[12] = config.get("Biome IDs", "ALPHAseasonalForest", 92).getInt();
			biomeIDs[13] = config.get("Biome IDs", "ALPHAforest", 93).getInt();
			biomeIDs[14] = config.get("Biome IDs", "ALPHAsavanna", 94).getInt();
			biomeIDs[15] = config.get("Biome IDs", "ALPHAshrubland", 95).getInt();
			biomeIDs[16] = config.get("Biome IDs", "ALPHAtaiga", 96).getInt();
			biomeIDs[17] = config.get("Biome IDs", "ALPHAdesert", 97).getInt();
			biomeIDs[18] = config.get("Biome IDs", "ALPHAplains", 98).getInt();
			biomeIDs[19] = config.get("Biome IDs", "ALPHAtundra", 99).getInt();
			
			//INFDEV BIOMES
			biomeIDs[20] = config.get("Biome IDs", "INFDEVdefault", 100).getInt();
			biomeIDs[21] = config.get("Biome IDs", "INFDEVsnow", 101).getInt();
			
			//INDEV BIOMES
			biomeIDs[22] = config.get("Biome IDs", "INDEVnormal", 102).getInt();
			biomeIDs[23] = config.get("Biome IDs", "INDEVhell", 103).getInt();
			biomeIDs[24] = config.get("Biome IDs", "INDEVparadise", 104).getInt();
			biomeIDs[25] = config.get("Biome IDs", "INDEVwoods", 105).getInt();
			biomeIDs[26] = config.get("Biome IDs", "INDEVsnow", 106).getInt();
			
			//ISLAND BIOMES
			biomeIDs[27] = config.get("Biome IDs", "ISLAND1", 107).getInt();
			biomeIDs[28] = config.get("Biome IDs", "ISLAND2", 108).getInt();
			biomeIDs[29] = config.get("Biome IDs", "ISLAND3", 109).getInt();
			biomeIDs[30] = config.get("Biome IDs", "ISLAND4", 110).getInt();
			biomeIDs[31] = config.get("Biome IDs", "ISLAND5", 111).getInt();
			biomeIDs[32] = config.get("Biome IDs", "ISLAND6", 112).getInt();
			
			//SKYLAND BIOMES
			biomeIDs[33] = config.get("Biome IDs", "SKYLAND1", 113).getInt();
			biomeIDs[34] = config.get("Biome IDs", "SKYLAND2", 114).getInt();
			biomeIDs[35] = config.get("Biome IDs", "SKYLAND3", 115).getInt();
			biomeIDs[36] = config.get("Biome IDs", "SKYLAND4", 116).getInt();
			biomeIDs[37] = config.get("Biome IDs", "SKYLAND5", 117).getInt();
			biomeIDs[38] = config.get("Biome IDs", "SKYLAND6", 118).getInt();
			
			//SURVIVAL BIOMES
			biomeIDs[39] = config.get("Biome IDs", "SURVIVALNETHER", 119).getInt();
			
			//SKYBLOCK BIOMES
			biomeIDs[40] = config.get("Biome IDs", "SKYBLOCK", 120).getInt();

			//BETTERDEFAULT BIOMES
			biomeIDs[41] = config.get("Biome IDs", "BDocean", 121).getInt();
			biomeIDs[42] = config.get("Biome IDs", "BDtropicalisland", 122).getInt();
			biomeIDs[43] = config.get("Biome IDs", "BDjungleisland", 123).getInt();
			biomeIDs[44] = config.get("Biome IDs", "BDmushroomisland", 124).getInt();
			biomeIDs[45] = config.get("Biome IDs", "BDbeach", 125).getInt();
			biomeIDs[46] = config.get("Biome IDs", "BDbeachDunes", 126).getInt();
			biomeIDs[47] = config.get("Biome IDs", "BDsnowpines", 127).getInt();
			biomeIDs[48] = config.get("Biome IDs", "BDsnowforest", 128).getInt();
			biomeIDs[49] = config.get("Biome IDs", "BDsnowtaiga", 129).getInt();
			biomeIDs[50] = config.get("Biome IDs", "BDsnowplains", 130).getInt();
			biomeIDs[51] = config.get("Biome IDs", "BDsnowhills", 131).getInt();
			biomeIDs[52] = config.get("Biome IDs", "BDplains", 132).getInt();
			biomeIDs[53] = config.get("Biome IDs", "BDforest", 133).getInt();
			biomeIDs[54] = config.get("Biome IDs", "BDforesthills", 134).getInt();
			biomeIDs[55] = config.get("Biome IDs", "BDforestlakes", 135).getInt();
			biomeIDs[56] = config.get("Biome IDs", "BDpines", 136).getInt();
			biomeIDs[57] = config.get("Biome IDs", "BDtaiga", 137).getInt();
			biomeIDs[58] = config.get("Biome IDs", "BDgrassland", 138).getInt();
			biomeIDs[59] = config.get("Biome IDs", "BDrainforest", 139).getInt();
			biomeIDs[60] = config.get("Biome IDs", "BDjungle", 140).getInt();
			biomeIDs[61] = config.get("Biome IDs", "BDswampland", 141).getInt();
			biomeIDs[62] = config.get("Biome IDs", "BDdesert", 142).getInt();
			biomeIDs[63] = config.get("Biome IDs", "BDsavanna", 143).getInt();
			biomeIDs[64] = config.get("Biome IDs", "BDsavannaforest", 144).getInt();
			biomeIDs[65] = config.get("Biome IDs", "BDshrubland", 145).getInt();
			biomeIDs[67] = config.get("Biome IDs", "BDiceriver", 146).getInt();
			biomeIDs[68] = config.get("Biome IDs", "BDriver", 147).getInt();
			biomeIDs[69] = config.get("Biome IDs", "BDgreenriver", 148).getInt();
			biomeIDs[70] = config.get("Biome IDs", "BDsandriver", 149).getInt();
			biomeIDs[71] = config.get("Biome IDs", "BDjungle_nocolor", 150).getInt();
			biomeIDs[72] = config.get("Biome IDs", "BDswampland_nocolor", 151).getInt();
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
