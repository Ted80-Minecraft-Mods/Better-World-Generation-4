package bwg4;

import bwg4.biomes.BiomeLoader;
import bwg4.network.ConnectionManager;
import bwg4.network.PacketBWG4generatorInfo;
import bwg4.network.PacketManager;
import bwg4.support.Support;
import bwg4.world.ProviderBWG4;
import bwg4.world.ProviderBWG4Hell;
import bwg4.world.WorldTypeBWG4;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.2.0")
public class BWG4
{	
	@Instance("BWG4")
	public static BWG4 instance;
	
	public static final WorldTypeBWG4 BWG4DEFAULT = (new WorldTypeBWG4("BWG4"));  
	
	public static final PacketManager packetmanager = new PacketManager();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		BiomeLoader.load();
		
		FMLCommonHandler.instance().bus().register(new ConnectionManager());
		packetmanager.registerPacket(PacketBWG4generatorInfo.class);
		
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, ProviderBWG4.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, ProviderBWG4Hell.class, true);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		packetmanager.initialise();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		packetmanager.postInitialise();
		
		Support.load();
	}
}