package ted80.bwg4.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomeGenBase extends BiomeGenBase
{
	//BETA - 80/89
	public static final BiomeGenBase BETArainforest = (new BWG4BiomesBeta(80)).setColor(353825).setBiomeName("rainforest").setTemperatureRainfall(0.95F, 0.95F);
	public static final BiomeGenBase BETAswampland = (new BWG4BiomesBeta(81)).setColor(353825).setBiomeName("swampland").setTemperatureRainfall(0.55F, 0.65F);
	public static final BiomeGenBase BETAseasonalForest = (new BWG4BiomesBeta(82)).setColor(353825).setBiomeName("seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
	public static final BiomeGenBase BETAforest = (new BWG4BiomesBeta(83)).setColor(353825).setBiomeName("forest").setTemperatureRainfall(0.8F, 0.6F);
	public static final BiomeGenBase BETAsavanna = (new BWG4BiomesBeta(84)).setColor(16421912).setBiomeName("savanna").setTemperatureRainfall(0.7F, 0.1F);
	public static final BiomeGenBase BETAshrubland = (new BWG4BiomesBeta(85)).setColor(353825).setBiomeName("shrubland").setTemperatureRainfall(0.7F, 0.3F);
	public static final BiomeGenBase BETAtaiga = (new BWG4BiomesBeta(86)).setColor(16777215).setBiomeName("taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
	public static final BiomeGenBase BETAdesert = (new BWG4BiomesBeta(87)).setColor(16421912).setBiomeName("desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
	public static final BiomeGenBase BETAplains = (new BWG4BiomesBeta(88)).setColor(353825).setBiomeName("plains").setTemperatureRainfall(0.95F, 0.35F);
	public static final BiomeGenBase BETAtundra = (new BWG4BiomesBeta(89)).setColor(16777215).setBiomeName("tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();

	//ALPHA - 90/99
	public static final BiomeGenBase ALPHArainforest = (new BWG4BiomesAlpha(90)).setColor(353825).setBiomeName("rainforest").setTemperatureRainfall(0.95F, 0.95F);
	public static final BiomeGenBase ALPHAswampland = (new BWG4BiomesAlpha(91)).setColor(353825).setBiomeName("swampland").setTemperatureRainfall(0.55F, 0.65F);
	public static final BiomeGenBase ALPHAseasonalForest = (new BWG4BiomesAlpha(92)).setColor(353825).setBiomeName("seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
	public static final BiomeGenBase ALPHAforest = (new BWG4BiomesAlpha(93)).setColor(353825).setBiomeName("forest").setTemperatureRainfall(0.8F, 0.6F);
	public static final BiomeGenBase ALPHAsavanna = (new BWG4BiomesAlpha(94)).setColor(353825).setBiomeName("savanna").setTemperatureRainfall(0.7F, 0.1F);
	public static final BiomeGenBase ALPHAshrubland = (new BWG4BiomesAlpha(95)).setColor(353825).setBiomeName("shrubland").setTemperatureRainfall(0.7F, 0.3F);
	public static final BiomeGenBase ALPHAtaiga = (new BWG4BiomesAlpha(96)).setColor(353825).setBiomeName("taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
	public static final BiomeGenBase ALPHAdesert = (new BWG4BiomesAlpha(97)).setColor(353825).setBiomeName("desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
	public static final BiomeGenBase ALPHAplains = (new BWG4BiomesAlpha(98)).setColor(353825).setBiomeName("plains").setTemperatureRainfall(0.95F, 0.35F);
	public static final BiomeGenBase ALPHAtundra = (new BWG4BiomesAlpha(99)).setColor(353825).setBiomeName("tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();
	
	//INFDEV/INDEV - 100/106
	public static final BiomeGenBase INFDEVdefault = (new BWG4BiomesInfdev(100)).setColor(353825).setBiomeName("Infdev");
	public static final BiomeGenBase INFDEVsnow = (new BWG4BiomesInfdev(101)).setColor(353825).setBiomeName("Infdev Snow").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
	public static final BiomeGenBase INDEVnormal = (new BWG4BiomesIndev(102)).setColor(353825).setBiomeName("Indev");
	public static final BiomeGenBase INDEVhell = (new BWG4BiomesIndev(103)).setColor(353825).setBiomeName("Indev");
	public static final BiomeGenBase INDEVparadise = (new BWG4BiomesIndev(104)).setColor(353825).setBiomeName("Indev");
	public static final BiomeGenBase INDEVwoods = (new BWG4BiomesIndev(105)).setColor(353825).setBiomeName("Indev");
	public static final BiomeGenBase INDEVsnow = (new BWG4BiomesIndev(106)).setColor(353825).setBiomeName("Indev").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
	
	//SURVIVAL - 107/120
	public static final BiomeGenBase ISLANDnormal = (new BWG4BiomesSurvival(107)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase ISLANDhell = (new BWG4BiomesSurvival(108)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase ISLANDice = (new BWG4BiomesSurvival(109)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.0F, 0.5F);
	public static final BiomeGenBase ISLANDparadise = (new BWG4BiomesSurvival(110)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase ISLANDnormal = (new BWG4BiomesSurvival(111)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase ISLANDnormal = (new BWG4BiomesSurvival(112)).setColor(353825).setBiomeName("Survival Island").setTemperatureRainfall(0.9F, 0.8F);
	public static final BiomeGenBase SKYLANDnormal = (new BWG4BiomesSurvival(113)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase SKYLANDhell = (new BWG4BiomesSurvival(114)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
	public static final BiomeGenBase SKYLANDice = (new BWG4BiomesSurvival(115)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.0F, 0.5F);
	public static final BiomeGenBase SKYLANDjungle = (new BWG4BiomesSurvival(116)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase SKYLANDnormal = (new BWG4BiomesSurvival(117)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
	//public static final BiomeGenBase SKYLANDnormal = (new BWG4BiomesSurvival(118)).setColor(353825).setBiomeName("Survival Skyland").setTemperatureRainfall(0.9F, 0.8F);
	public static final BiomeGenBase SURVIVALnether = (new BWG4BiomesSurvival(119)).setColor(353825).setBiomeName("Hell").setTemperatureRainfall(0.8F, 0.6F);
	public static final BiomeGenBase SKYBLOCKworld = (new BWG4BiomesSurvival(120)).setColor(353825).setBiomeName("Survival Skyblock").setTemperatureRainfall(0.9F, 0.8F);
	
	//BETTER DEFAULT 121/-  
	public static final BiomeGenBase BDocean = (new BWG4BiomesDefault(121, 1, 1)).setColor(353825).setBiomeName("Ocean").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-1.1F, 0.3F);
	public static final BiomeGenBase BDtropicalisland = (new BWG4BiomesDefault(122, 1, 2)).setColor(353825).setBiomeName("Tropical Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F);
	public static final BiomeGenBase BDjungleisland = (new BWG4BiomesDefault(123, 1, 3)).setColor(353825).setBiomeName("Jungle Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F);
	public static final BiomeGenBase BDmushroomisland = (new BWG4BiomesDefault(124, 1, 4)).setColor(353825).setBiomeName("Mushroom Island").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.3F);
	public static final BiomeGenBase BDbeach = (new BWG4BiomesDefault(125, 1, 5)).setColor(353825).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.0F, 0.1F); 
	public static final BiomeGenBase BDbeachDunes = (new BWG4BiomesDefault(126, 1, 6)).setColor(353825).setBiomeName("Beach Dunes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 0.2F); 
	public static final BiomeGenBase BDsnowpines = (new BWG4BiomesDefault(127, 2, 1)).setColor(353825).setBiomeName("Snow Pines").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow();
	public static final BiomeGenBase BDsnowforest = (new BWG4BiomesDefault(128, 2, 2)).setColor(353825).setBiomeName("Snow Forest").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F).setEnableSnow();
	public static final BiomeGenBase BDsnowtaiga = (new BWG4BiomesDefault(129, 2, 3)).setColor(353825).setBiomeName("Snow Taiga").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow();
	public static final BiomeGenBase BDsnowplains = (new BWG4BiomesDefault(130, 2, 4)).setColor(353825).setBiomeName("Snow Plains").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 0.3F).setEnableSnow();
	public static final BiomeGenBase BDsnowhills = (new BWG4BiomesDefault(131, 2, 5)).setColor(353825).setBiomeName("Snow Hills").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 1.3F).setEnableSnow();
	public static final BiomeGenBase BDplains = (new BWG4BiomesDefault(132, 3, 1)).setColor(353825).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.3F);
	public static final BiomeGenBase BDforest = (new BWG4BiomesDefault(133, 3, 2)).setColor(353825).setBiomeName("Forest").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 1.0F);
	public static final BiomeGenBase BDforesthills = (new BWG4BiomesDefault(134, 3, 3)).setColor(353825).setBiomeName("Forest Hills").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 1.5F);
	public static final BiomeGenBase BDforestlakes = (new BWG4BiomesDefault(135, 3, 4)).setColor(353825).setBiomeName("Forest Lakes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.2F, 0.9F);
	public static final BiomeGenBase BDpines = (new BWG4BiomesDefault(136, 3, 5)).setColor(353825).setBiomeName("Pines").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.9F);
	public static final BiomeGenBase BDtaiga = (new BWG4BiomesDefault(137, 3, 6)).setColor(353825).setBiomeName("Taiga").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.9F);
	public static final BiomeGenBase BDgrassland = (new BWG4BiomesDefault(138, 3, 7)).setColor(353825).setBiomeName("Grassland").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 1.3F);
	public static final BiomeGenBase BDrainforest = (new BWG4BiomesDefault(139, 4, 1)).setColor(353825).setBiomeName("Rainforest").setMinMaxHeight(0.3F, 1.2F).setTemperatureRainfall(0.8F, 1.0F);
	public static final BiomeGenBase BDjungle = (new BWG4BiomesDefault(140, 4, 2)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 1.2F).setTemperatureRainfall(1.0F, 1.0F);
	public static final BiomeGenBase BDswampland = (new BWG4BiomesDefault(141, 4, 3)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F);
	public static final BiomeGenBase BDdesert = (new BWG4BiomesDefault(142, 5, 1)).setColor(353825).setBiomeName("Desert").setMinMaxHeight(0.3F, 0.8F).setTemperatureRainfall(1.0F, 0.0F);
	public static final BiomeGenBase BDsavanna = (new BWG4BiomesDefault(143, 5, 2)).setColor(353825).setBiomeName("Savanna").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(1.0F, 0.2F);
	public static final BiomeGenBase BDsavannaforest = (new BWG4BiomesDefault(144, 5, 3)).setColor(353825).setBiomeName("Savanna Forest").setMinMaxHeight(0.3F, 0.6F).setTemperatureRainfall(1.0F, 0.2F);
	public static final BiomeGenBase BDshrubland = (new BWG4BiomesDefault(145, 5, 4)).setColor(353825).setBiomeName("Shrubland").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(0.9F, 0.0F);
	public static final BiomeGenBase BDshrublandHill = (new BWG4BiomesDefault(146, 5, 5)).setColor(353825).setBiomeName("SandStone Hill").setMinMaxHeight(2.5F, 0.2F).setTemperatureRainfall(0.9F, 0.0F);
	public static final BiomeGenBase BDiceriver = (new BWG4BiomesDefault(147, 6, 1)).setColor(353825).setBiomeName("River_ice").setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(-0.8F, 0.0F).setEnableSnow();
	public static final BiomeGenBase BDriver = (new BWG4BiomesDefault(148, 6, 2)).setColor(353825).setBiomeName("River_normal").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDgreenriver = (new BWG4BiomesDefault(149, 6, 3)).setColor(353825).setBiomeName("River_green").setTemperatureRainfall(0.8F, 1.0F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDsandriver = (new BWG4BiomesDefault(150, 6, 4)).setColor(353825).setBiomeName("River_sand").setTemperatureRainfall(0.9F, 0.1F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDjungle_nocolor = (new BWG4BiomesDefault(151, 4, 4)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(1.0F, 1.0F);
	public static final BiomeGenBase BDswampland_nocolor = (new BWG4BiomesDefault(152, 4, 5)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F);	

	public int beachID;		
	public int mountainStart;
	public boolean mountainSnow;
	public boolean defaultbeach;
	public boolean lakesDisabled;
	public int snowLevel;
	public boolean isCanyon;
	public int randblock;
    public byte topBlock2;
    public byte fillerBlock2;
    
	public BWG4BiomeGenBase(int id)
	{
		super(id);		
		
		beachID = 0; 
		mountainStart = 128;
		mountainSnow = false;
		defaultbeach = false;
		lakesDisabled = false;
		snowLevel = 0;
		isCanyon = false;
		randblock = 0;
		topBlock2 = (byte)Block.grass.blockID;
		fillerBlock2 = (byte)Block.dirt.blockID;
	}
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
    }
	
    public final int getSnowLevel()
    {
        return this.snowLevel;
    }
}
