package ted80.bwg4;

import java.lang.reflect.Array;

import ted80.bwg4.gen.BWG4Provider;
import ted80.bwg4.gen.BWG4ProviderHell;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="BWG4", name="BetterWorldGeneration4", version="1.0.3")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class mod_bwg4
{	
	@Instance("BWG4")
	public static mod_bwg4 instance;
	
	public mod_bwg4()
	{
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		DimensionManager.unregisterProviderType(0);
		DimensionManager.registerProviderType(0, BWG4Provider.class, true);
		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, BWG4ProviderHell.class, true);
		
		BiomeManager.addVillageBiome(BiomeGenBase.plains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.desert, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDsnowplains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDplains, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDsavanna, true);
		BiomeManager.addVillageBiome(BiomeGenBase.BDshrubland, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD4canyonFields, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD4savannaFields, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD4desertFlat, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD3rainField, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD3jungleFlat, true);
		BiomeManager.addVillageBiome(BiomeGenBase.GOLD2forestPlains, true);
	}
}