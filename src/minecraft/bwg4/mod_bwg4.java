package bwg4;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import bwg4.api.DefaultBiomeList;
import bwg4.biomes.BWG4Biomes;
import bwg4.biomes.BWG4BiomesAlpha;
import bwg4.biomes.BWG4BiomesBeta;
import bwg4.biomes.BWG4BiomesDefault;
import bwg4.biomes.BWG4BiomesIndev;
import bwg4.biomes.BWG4BiomesInfdev;
import bwg4.biomes.BWG4BiomesSurvival;
import bwg4.chunk.BWG4SkylightMap;
import bwg4.config.BWG4Config;
import bwg4.gen.BWG4Provider;
import bwg4.gen.BWG4ProviderHell;
import bwg4.gen.BWG4WorldType;
import bwg4.gen.BWG4WorldTypeArray;
import bwg4.gui.BWG4BiomeInfo;
import bwg4.network.BWG4Connection;
import bwg4.network.BWG4Packet;
import bwg4.support.Support;
import net.minecraft.block.Block;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.1.6")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"BWG4channel"}, packetHandler = BWG4Packet.class)
public class mod_bwg4
{	
	@Instance("BWG4")
	public static mod_bwg4 instance;
	
	public static final BWG4WorldType BWG4DEFAULT = (new BWG4WorldType(12, "BWG4"));  
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		BWG4Config.init(event.getSuggestedConfigurationFile());
		
		BWG4Biomes.init();
		
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, BWG4Provider.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, BWG4ProviderHell.class, true);

		LanguageRegistry.instance().addStringLocalization("generator.BWG4", "BWG4");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		Support.init();
		NetworkRegistry.instance().registerConnectionHandler(new BWG4Connection());
	}
}