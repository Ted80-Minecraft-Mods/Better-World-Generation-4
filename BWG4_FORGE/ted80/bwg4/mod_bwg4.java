package ted80.bwg4;

import java.lang.reflect.Array;

import ted80.api.DefaultBiomeList;
import ted80.bwg4.gen.BWG4Provider;
import ted80.bwg4.gen.BWG4ProviderHell;
import net.minecraft.client.gui.BWG4BiomeInfo;
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

		DefaultBiomeList.addBiome("Shrubland", BiomeGenBase.BDshrubland, 4);
		DefaultBiomeList.addBiome("Savanna", BiomeGenBase.BDsavanna, 4);
		DefaultBiomeList.addBiome("Desert", BiomeGenBase.BDdesert, 4);
		DefaultBiomeList.addBiome("Swampland", BiomeGenBase.BDswampland, 3);
		DefaultBiomeList.addBiome("Jungle", BiomeGenBase.BDjungle, 3);
		DefaultBiomeList.addBiome("RainForest", BiomeGenBase.BDrainforest, 3);
		DefaultBiomeList.addBiome("Grassland", BiomeGenBase.BDgrassland, 2);
		DefaultBiomeList.addBiome("Taiga", BiomeGenBase.BDtaiga, 2);
		DefaultBiomeList.addBiome("Pines", BiomeGenBase.BDpines, 2);
		DefaultBiomeList.addBiome("Forest Lakes", BiomeGenBase.BDforestlakes, 2);
		DefaultBiomeList.addBiome("Forest Hills", BiomeGenBase.BDforesthills, 2);
		DefaultBiomeList.addBiome("Forest", BiomeGenBase.BDforest, 2);
		DefaultBiomeList.addBiome("Plains", BiomeGenBase.BDplains, 2);
		DefaultBiomeList.addBiome("Snow Hills", BiomeGenBase.BDsnowhills, 1);
		DefaultBiomeList.addBiome("Snow Plains", BiomeGenBase.BDsnowplains, 1);
		DefaultBiomeList.addBiome("Snow Taiga", BiomeGenBase.BDsnowtaiga, 1);
		DefaultBiomeList.addBiome("Snow Forest", BiomeGenBase.BDsnowforest, 1);
		DefaultBiomeList.addBiome("Snow Pines", BiomeGenBase.BDsnowpines, 1);
		DefaultBiomeList.addBiome("Beach Dunes", BiomeGenBase.BDbeachDunes, 0);
		DefaultBiomeList.addBiome("Beach", BiomeGenBase.BDbeach, 0);
		DefaultBiomeList.addBiome("Mushroom Island", BiomeGenBase.BDmushroomisland, 5);
		DefaultBiomeList.addBiome("Jungle Island", BiomeGenBase.BDjungleisland, 5);
		DefaultBiomeList.addBiome("Tropical Island", BiomeGenBase.BDtropicalisland, 5);
		DefaultBiomeList.addBiome("Ocean", BiomeGenBase.BDocean, 0);
	}
}