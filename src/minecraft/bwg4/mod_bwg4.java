package bwg4;

import net.minecraftforge.common.DimensionManager;
import bwg4.biomes.BWG4Biomes;
import bwg4.config.BWG4Config;
import bwg4.gen.BWG4Provider;
import bwg4.gen.BWG4ProviderHell;
import bwg4.gen.BWG4WorldType;
import bwg4.network.BWG4Connection;
import bwg4.support.Support;
import bwg4.network.BWG4Packet;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.1.9")
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
		
		//load config
		BWG4Config.init(event.getSuggestedConfigurationFile());
		
		//load biomes
		BWG4Biomes.init();
		
		//load providers
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, BWG4Provider.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, BWG4ProviderHell.class, true);

		//register worldtype name
		LanguageRegistry.instance().addStringLocalization("generator.BWG4", "BWG4");
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		//register bwg4 packets
		NetworkRegistry.instance().registerConnectionHandler(new BWG4Connection());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		//try support
		Support.init();
	}
}