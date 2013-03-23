package net.minecraft.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.*;
import net.minecraftforge.event.terraingen.*;

public abstract class BiomeGenBase
{
	//BIOME LIST
    public static final BiomeGenBase[] biomeList = new BiomeGenBase[256];
	
	//VANILLA BIOMES - 0/29
    public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setMinMaxHeight(-1.0F, 0.4F);
    public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
    public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
    public static final BiomeGenBase extremeHills = (new BiomeGenHills(3)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F);
    public static final BiomeGenBase forest = (new BiomeGenForest(4)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
    public static final BiomeGenBase taiga = (new BiomeGenTaiga(5)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
    public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
    public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setMinMaxHeight(-0.5F, 0.0F);
    public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
    public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("Sky").setDisableRain();
    public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setMinMaxHeight(-1.0F, 0.5F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase icePlains = (new BiomeGenSnow(12)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setMinMaxHeight(0.3F, 1.3F).setTemperatureRainfall(0.0F, 0.5F);
    public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.2F, 1.0F);
    public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(-1.0F, 0.1F);
    public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.1F);
    public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.8F);
    public static final BiomeGenBase forestHills = (new BiomeGenForest(18)).setColor(2250012).setBiomeName("ForestHills").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F);
    public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19)).setColor(1456435).setBiomeName("TaigaHills").setEnableSnow().func_76733_a(5159473).setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.3F, 0.8F);
    public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20)).setColor(7501978).setBiomeName("Extreme Hills Edge").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(0.2F, 0.3F);
    public static final BiomeGenBase jungle = (new BiomeGenJungle(21)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);
    public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22)).setColor(2900485).setBiomeName("JungleHills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 0.5F);
	
	//EXTRABIOMESXL - 23/79
	
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

	//BETTER DEFAULT 121/164  
	public static final BiomeGenBase BDocean = (new BWG4BiomesDefault(121, 1, 1)).setColor(353825).setBiomeName("Ocean").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-1.1F, 0.3F).beach(2);
	public static final BiomeGenBase BDtropicalisland = (new BWG4BiomesDefault(122, 1, 2)).setColor(353825).setBiomeName("Tropical Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F).beach(2);
	public static final BiomeGenBase BDjungleisland = (new BWG4BiomesDefault(123, 1, 3)).setColor(353825).setBiomeName("Jungle Island").setTemperatureRainfall(1.0F, 1.0F).setMinMaxHeight(0.2F, 0.3F).beach(2);
	public static final BiomeGenBase BDmushroomisland = (new BWG4BiomesDefault(124, 1, 4)).setColor(353825).setBiomeName("Mushroom Island").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.3F).beach(0);
	public static final BiomeGenBase BDbeach = (new BWG4BiomesDefault(125, 1, 5)).setColor(353825).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.0F, 0.1F).beach(2).defaultbeach(); 
	public static final BiomeGenBase BDbeachDunes = (new BWG4BiomesDefault(126, 1, 6)).setColor(353825).setBiomeName("Beach Dunes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 0.2F).beach(2).defaultbeach(); 
	public static final BiomeGenBase BDsnowpines = (new BWG4BiomesDefault(127, 2, 1)).setColor(353825).setBiomeName("Snow Pines").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow().beach(1);
	public static final BiomeGenBase BDsnowforest = (new BWG4BiomesDefault(128, 2, 2)).setColor(353825).setBiomeName("Snow Forest").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F).setEnableSnow().beach(1);
	public static final BiomeGenBase BDsnowtaiga = (new BWG4BiomesDefault(129, 2, 3)).setColor(353825).setBiomeName("Snow Taiga").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.9F).setEnableSnow().beach(1);
	public static final BiomeGenBase BDsnowplains = (new BWG4BiomesDefault(130, 2, 4)).setColor(353825).setBiomeName("Snow Plains").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 0.3F).setEnableSnow().beach(1);
	public static final BiomeGenBase BDsnowhills = (new BWG4BiomesDefault(131, 2, 5)).setColor(353825).setBiomeName("Snow Hills").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 1.3F).setEnableSnow().beach(1);
	public static final BiomeGenBase BDplains = (new BWG4BiomesDefault(132, 3, 1)).setColor(353825).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.3F).beach(1);
	public static final BiomeGenBase BDforest = (new BWG4BiomesDefault(133, 3, 2)).setColor(353825).setBiomeName("Forest").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 1.0F).beach(1);
	public static final BiomeGenBase BDforesthills = (new BWG4BiomesDefault(134, 3, 3)).setColor(353825).setBiomeName("Forest Hills").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.3F, 1.5F).beach(1);
	public static final BiomeGenBase BDforestlakes = (new BWG4BiomesDefault(135, 3, 4)).setColor(353825).setBiomeName("Forest Lakes").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.2F, 0.9F).beach(1);
	public static final BiomeGenBase BDpines = (new BWG4BiomesDefault(136, 3, 5)).setColor(353825).setBiomeName("Pines").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.9F).beach(1);
	public static final BiomeGenBase BDtaiga = (new BWG4BiomesDefault(137, 3, 6)).setColor(353825).setBiomeName("Taiga").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 0.9F).beach(1);
	public static final BiomeGenBase BDgrassland = (new BWG4BiomesDefault(138, 3, 7)).setColor(353825).setBiomeName("Grassland").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(0.2F, 1.3F).beach(1);
	public static final BiomeGenBase BDrainforest = (new BWG4BiomesDefault(139, 4, 1)).setColor(353825).setBiomeName("Rainforest").setMinMaxHeight(0.3F, 1.2F).setTemperatureRainfall(0.8F, 1.0F).beach(0);
	public static final BiomeGenBase BDjungle = (new BWG4BiomesDefault(140, 4, 2)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 1.2F).setTemperatureRainfall(1.0F, 1.0F).beach(0);
	public static final BiomeGenBase BDswampland = (new BWG4BiomesDefault(141, 4, 3)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F).beach(0);
	public static final BiomeGenBase BDdesert = (new BWG4BiomesDefault(142, 5, 1)).setColor(353825).setBiomeName("Desert").setMinMaxHeight(0.3F, 0.8F).setTemperatureRainfall(1.0F, 0.0F).beach(2).disableLakes();
	public static final BiomeGenBase BDsavanna = (new BWG4BiomesDefault(143, 5, 2)).setColor(353825).setBiomeName("Savanna").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(1.0F, 0.2F).beach(0).disableLakes();
	public static final BiomeGenBase BDsavannaforest = (new BWG4BiomesDefault(144, 5, 3)).setColor(353825).setBiomeName("Savanna Forest").setMinMaxHeight(0.3F, 0.6F).setTemperatureRainfall(1.0F, 0.2F).beach(0);
	public static final BiomeGenBase BDshrubland = (new BWG4BiomesDefault(145, 5, 4)).setColor(353825).setBiomeName("Shrubland").setMinMaxHeight(0.3F, 0.2F).setTemperatureRainfall(0.9F, 0.0F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase BDshrublandHill = (new BWG4BiomesDefault(146, 5, 5)).setColor(353825).setBiomeName("SandStone Hill").setMinMaxHeight(2.5F, 0.2F).setTemperatureRainfall(0.9F, 0.0F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase BDiceriver = (new BWG4BiomesDefault(147, 6, 1)).setColor(353825).setBiomeName("River_ice").setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(-0.8F, 0.0F).setEnableSnow();
	public static final BiomeGenBase BDriver = (new BWG4BiomesDefault(148, 6, 2)).setColor(353825).setBiomeName("River_normal").setTemperatureRainfall(0.8F, 0.6F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDgreenriver = (new BWG4BiomesDefault(149, 6, 3)).setColor(353825).setBiomeName("River_green").setTemperatureRainfall(0.8F, 1.0F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDsandriver = (new BWG4BiomesDefault(150, 6, 4)).setColor(353825).setBiomeName("River_sand").setTemperatureRainfall(0.9F, 0.1F).setMinMaxHeight(-0.8F, 0.0F);
	public static final BiomeGenBase BDjungle_nocolor = (new BWG4BiomesDefault(151, 4, 4)).setColor(353825).setBiomeName("Jungle").setMinMaxHeight(0.2F, 0.8F).setTemperatureRainfall(1.0F, 1.0F).beach(0);
	public static final BiomeGenBase BDswampland_nocolor = (new BWG4BiomesDefault(152, 4, 5)).setColor(353825).setBiomeName("Swampland").setMinMaxHeight(-0.2F, 0.3F).setTemperatureRainfall(0.9F, 1.0F).beach(0);
	//12 left
	
	//WASTELAND 165/- 
	
	//HARDCORE -/-
	
	//CAVE DIMENSION -/183

	//GOLD 184/255
	
	public static final BiomeGenBase GOLD1taigaHills = (new BWG4BiomesGold1(197, 1, 1)).setColor(1456435).setBiomeName("Taiga_Hills");
	
	public static final BiomeGenBase GOLD1iceOcean = (new BWG4BiomesGold1(198, 2, 1)).setColor(1456435).setBiomeName("Ice_Ocean").setMinMaxHeight(-1.1F, 0.2F).setTemperatureRainfall(0.0F, 0.0F).snow(62);
	public static final BiomeGenBase GOLD1iceBeach = (new BWG4BiomesGold1(199, 2, 2)).setColor(1456435).setBiomeName("Ice_Beach").setMinMaxHeight(0.0F, 0.6F).setTemperatureRainfall(0.0F, 0.0F).snow(62).disableLakes();
	public static final BiomeGenBase GOLD1icePlains = (new BWG4BiomesGold1(200, 2, 3)).setColor(1456435).setBiomeName("Ice_Plains").setMinMaxHeight(0.6F, 0.2F).setTemperatureRainfall(0.0F, 0.0F).snow(62).disableLakes();
	public static final BiomeGenBase GOLD1iceForest = (new BWG4BiomesGold1(201, 2, 4)).setColor(1456435).setBiomeName("Ice_Forest").setMinMaxHeight(0.1F, 0.1F).setTemperatureRainfall(0.0F, 0.0F).snow(62);
	public static final BiomeGenBase GOLD1iceBorder = (new BWG4BiomesGold1(202, 2, 5)).setColor(1456435).setBiomeName("Ice_Border").setMinMaxHeight(0.2F, 0.6F).setTemperatureRainfall(0.0F, 0.0F).snow(62);
		
	public static final BiomeGenBase GOLD1snowpineLake = (new BWG4BiomesGold1(203, 3, 1)).setColor(1456435).setBiomeName("SnowPine_Lake").setMinMaxHeight(-0.9F, 0.6F).setTemperatureRainfall(0.0F, 0.0F).beach(3).mountain(105, true).snow(62);
	public static final BiomeGenBase GOLD1snowpineBeach = (new BWG4BiomesGold1(204, 3, 2)).setColor(1456435).setBiomeName("SnowPine_Beach").setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.0F, 0.0F).beach(3).mountain(105, true).snow(62);
	public static final BiomeGenBase GOLD1snowpineForest = (new BWG4BiomesGold1(205, 3, 3)).setColor(1456435).setBiomeName("SnowPine_Forest").setMinMaxHeight(0.6F, 0.7F).setTemperatureRainfall(0.0F, 0.0F).beach(3).mountain(105, true).snow(62);
	public static final BiomeGenBase GOLD1snowpineField = (new BWG4BiomesGold1(206, 3, 4)).setColor(1456435).setBiomeName("SnowPine_Field").setMinMaxHeight(0.6F, 0.7F).setTemperatureRainfall(0.0F, 0.0F).beach(3).mountain(105, true).snow(62);
	public static final BiomeGenBase GOLD1snowpineHigh = (new BWG4BiomesGold1(207, 3, 5)).setColor(1456435).setBiomeName("SnowPine_High").setMinMaxHeight(1.6F, 1.6F).setTemperatureRainfall(0.0F, 0.0F).beach(3).mountain(105, true).snow(62);
	public static final BiomeGenBase GOLD1snowpineBorder = (new BWG4BiomesGold1(208, 3, 6)).setColor(1456435).setBiomeName("SnowPine_Border").setMinMaxHeight(0.3F, 0.4F).setTemperatureRainfall(0.6F, 0.6F).beach(3).mountain(105, false);
	public static final BiomeGenBase GOLD2pineNormal = (new BWG4BiomesGold2(209, 1, 1)).setColor(353825).setBiomeName("Pine_Normal").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.2F, 0.9F).beach(0).mountain(100, false);
	public static final BiomeGenBase GOLD2pineHills = (new BWG4BiomesGold2(210, 1, 2)).setColor(353825).setBiomeName("Pine_Hills").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.5F, 1.1F).beach(0).mountain(100, false);
	public static final BiomeGenBase GOLD2pineMix = (new BWG4BiomesGold2(211, 1, 3)).setColor(353825).setBiomeName("Pine_Mix").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.2F, 0.8F).beach(0).mountain(100, false);
	public static final BiomeGenBase GOLD2pineValleys = (new BWG4BiomesGold2(212, 1, 4)).setColor(353825).setBiomeName("Pine_Valleys").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.3F, 0.6F).beach(0).mountain(100, false);
	public static final BiomeGenBase GOLD2pineBorder = (new BWG4BiomesGold2(213, 1, 5)).setColor(353825).setBiomeName("Pine_Border").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.3F, 0.6F).beach(0).mountain(100, false);
	public static final BiomeGenBase GOLD2forestNormal = (new BWG4BiomesGold2(214, 2, 1)).setColor(353825).setBiomeName("Forest_Normal").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.2F, 0.9F).beach(1);
	public static final BiomeGenBase GOLD2forestHigh = (new BWG4BiomesGold2(215, 2, 2)).setColor(353825).setBiomeName("Forest_High").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.4F, 1.6F).beach(1);
	public static final BiomeGenBase GOLD2forestLakes = (new BWG4BiomesGold2(216, 2, 3)).setColor(353825).setBiomeName("Forest_Lakes").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(-0.3F, 1.0F).beach(1);
	public static final BiomeGenBase GOLD2forestPlains = (new BWG4BiomesGold2(217, 2, 4)).setColor(353825).setBiomeName("Forest_Plains").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.3F, 0.2F).beach(1);
	public static final BiomeGenBase GOLD2forestBorder = (new BWG4BiomesGold2(218, 2, 5)).setColor(353825).setBiomeName("Forest_Border").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.3F, 0.6F).beach(1);
	public static final BiomeGenBase GOLD3jungleFlat = (new BWG4BiomesGold3(219, 1, 1)).setColor(5470985).setBiomeName("Jungle_Flat").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3jungleFlatLake = (new BWG4BiomesGold3(220, 1, 1)).setColor(5470985).setBiomeName("Jungle_Flat_Lake").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.1F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3jungleFlatOpen = (new BWG4BiomesGold3(221, 1, 2)).setColor(5470985).setBiomeName("Jungle_Flat_Open").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3jungleHilly = (new BWG4BiomesGold3(222, 1, 3)).setColor(5470985).setBiomeName("Jungle_Hilly").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.8F, 5.0F).beach(0);
	public static final BiomeGenBase GOLD3jungleDeep = (new BWG4BiomesGold3(223, 1, 4)).setColor(5470985).setBiomeName("Jungle_Deep").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.8F, 5.0F).beach(0);
	public static final BiomeGenBase GOLD3junglePlateau = (new BWG4BiomesGold3(224, 1, 5)).setColor(5470985).setBiomeName("Jungle_Flat").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(1.6F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3jungleVulcano1 = (new BWG4BiomesGold3(225, 1, 6)).setColor(5470985).setBiomeName("Jungle_Vulcano").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(5.0F, 0.1F).beach(0);
	public static final BiomeGenBase GOLD3jungleVulcano2 = (new BWG4BiomesGold3(226, 1, 6)).setColor(5470985).setBiomeName("Jungle_Vulcano").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(5.0F, 0.1F).beach(0);
	public static final BiomeGenBase GOLD3jungleBorder = (new BWG4BiomesGold3(227, 1, 7)).setColor(5470985).setBiomeName("Jungle_Border").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.8F, 0.6F).beach(0);
	public static final BiomeGenBase GOLD3tropicSea = (new BWG4BiomesGold3(228, 2, 1)).setColor(5470985).setBiomeName("Tropical_Sea").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.7F, 0.0F).beach(2);
	public static final BiomeGenBase GOLD3tropicIsland = (new BWG4BiomesGold3(229, 2, 2)).setColor(5470985).setBiomeName("Tropical_Island").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 2.8F).beach(2);
	public static final BiomeGenBase GOLD3tropicBeach = (new BWG4BiomesGold3(230, 2, 3)).setColor(5470985).setBiomeName("Tropical_Island_Beach").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.5F, 1.6F).beach(2);
	public static final BiomeGenBase GOLD3tropicSmall = (new BWG4BiomesGold3(231, 2, 3)).setColor(5470985).setBiomeName("Tropical_Island_Small").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.9F).beach(2);
	public static final BiomeGenBase GOLD3tropicBorder = (new BWG4BiomesGold3(232, 2, 4)).setColor(5470985).setBiomeName("Tropical_Border").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.1F, 0.6F).beach(2);
	public static final BiomeGenBase GOLD3rainFlat = (new BWG4BiomesGold3(233, 3, 1)).setColor(5470985).setBiomeName("RainForest_Flat").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3rainField = (new BWG4BiomesGold3(234, 3, 2)).setColor(5470985).setBiomeName("RainForest_Field").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD3rainHills = (new BWG4BiomesGold3(235, 3, 3)).setColor(5470985).setBiomeName("RainForest_Hills").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.4F, 0.8F).beach(0);
	public static final BiomeGenBase GOLD3rainLakes = (new BWG4BiomesGold3(236, 3, 4)).setColor(5470985).setBiomeName("RainForest_Lakes").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.2F, 0.5F).beach(0);
	public static final BiomeGenBase GOLD3rainSwamp = (new BWG4BiomesGold3(237, 4, 1)).setColor(5470985).setBiomeName("RainForest_Swamp").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.4F, 0.0F).beach(0);
	public static final BiomeGenBase GOLD3rainLake = (new BWG4BiomesGold3(238, 3, 5)).setColor(5470985).setBiomeName("RainForest_Lake").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(-0.3F, 0.0F).beach(0);
	public static final BiomeGenBase GOLD3rainBorder = (new BWG4BiomesGold3(239, 3, 6)).setColor(5470985).setBiomeName("RainForest_Border").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.5F, 0.3F).beach(0);
	public static final BiomeGenBase GOLD4desertFlat = (new BWG4BiomesGold4(240, 1, 1)).setColor(353825).setBiomeName("Desert_Flat").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 0.4F).beach(2).disableLakes();
	public static final BiomeGenBase GOLD4desertHills = (new BWG4BiomesGold4(241, 1, 2)).setColor(353825).setBiomeName("Desert_Hills").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 1.6F).beach(2).disableLakes();
	public static final BiomeGenBase GOLD4desertLakes = (new BWG4BiomesGold4(242, 1, 3)).setColor(353825).setBiomeName("Desert_lakes").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.1F, 0.9F).beach(2).disableLakes();
	public static final BiomeGenBase GOLD4desertOasis = (new BWG4BiomesGold4(243, 1, 4)).setColor(353825).setBiomeName("Desert_Oasis").setTemperatureRainfall(1.0F, 0.8F).setDisableRain().setMinMaxHeight(0.1F, 0.3F).beach(0).disableLakes();
	public static final BiomeGenBase GOLD4desertBorder = (new BWG4BiomesGold4(244, 1, 5)).setColor(353825).setBiomeName("Desert_Border").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.5F, 0.3F).beach(2).disableLakes();
	public static final BiomeGenBase GOLD4savannaFields = (new BWG4BiomesGold4(245, 2, 1)).setColor(353825).setBiomeName("Savanna_Fields").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 0.2F).beach(0).disableLakes();
	public static final BiomeGenBase GOLD4savannaForest = (new BWG4BiomesGold4(247, 2, 2)).setColor(353825).setBiomeName("Savanna_Forest").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 1.0F).beach(0);
	public static final BiomeGenBase GOLD4savannaHills = (new BWG4BiomesGold4(248, 2, 3)).setColor(353825).setBiomeName("Savanna_Hills").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 1.8F).beach(0);
	public static final BiomeGenBase GOLD4savannaBorder = (new BWG4BiomesGold4(249, 2, 4)).setColor(353825).setBiomeName("Savanna_Border").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.8F, 0.3F).beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonHIGH1 = (new BWG4BiomesGold4(250, 3, 1)).setColor(353825).setBiomeName("Canyon_High1").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(3.5F, 0.2F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonHIGH2 = (new BWG4BiomesGold4(251, 3, 1)).setColor(353825).setBiomeName("Canyon_High2").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(3.5F, 0.2F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonHIGH3 = (new BWG4BiomesGold4(252, 3, 1)).setColor(353825).setBiomeName("Canyon_High3").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(3.5F, 0.2F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonValley = (new BWG4BiomesGold4(253, 3, 2)).setColor(353825).setBiomeName("Canyon_Valley").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.5F, 0.3F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonFields = (new BWG4BiomesGold4(254, 3, 3)).setColor(353825).setBiomeName("Canyon_Fields").setTemperatureRainfall(2.0F, 0.0F).setDisableRain().setMinMaxHeight(0.5F, 0.2F).canyon().beach(0).disableLakes();
	public static final BiomeGenBase GOLD4canyonLake = (new BWG4BiomesGold4(255, 3, 4)).setColor(353825).setBiomeName("Canyon_Lake").setTemperatureRainfall(2.0F, 0.2F).setDisableRain().setMinMaxHeight(-0.2F, 0.2F).canyon().beach(0).disableLakes();	
	
	public String biomeName;
    public int color;
    public byte topBlock;
    public byte fillerBlock;
    public int field_76754_C;
    public float minHeight;
    public float maxHeight;
    public float temperature;
    public float rainfall;
    public int waterColorMultiplier;
    public BiomeDecorator theBiomeDecorator;
    protected List spawnableMonsterList;
    protected List spawnableCreatureList;
    protected List spawnableWaterCreatureList;
    protected List spawnableCaveCreatureList;
    private boolean enableSnow;
    private boolean enableRain;
    public final int biomeID;
    protected WorldGenTrees worldGeneratorTrees;
    protected WorldGenBigTree worldGeneratorBigTree;
    protected WorldGenForest worldGeneratorForest;
    protected WorldGenSwamp worldGeneratorSwamp;
	
	//BWG4
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

    public BiomeGenBase(int par1)
    {
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        this.field_76754_C = 5169201;
        this.minHeight = 0.1F;
        this.maxHeight = 0.3F;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.waterColorMultiplier = 16777215;
        this.spawnableMonsterList = new ArrayList();
        this.spawnableCreatureList = new ArrayList();
        this.spawnableWaterCreatureList = new ArrayList();
        this.spawnableCaveCreatureList = new ArrayList();
        this.enableRain = true;
        this.worldGeneratorTrees = new WorldGenTrees(false);
        this.worldGeneratorBigTree = new WorldGenBigTree(false);
        this.worldGeneratorForest = new WorldGenForest(false);
        this.worldGeneratorSwamp = new WorldGenSwamp();
        this.biomeID = par1;
        biomeList[par1] = this;
		
		//BWG4
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
		
        this.theBiomeDecorator = this.createBiomeDecorator();
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
        this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    public BiomeDecorator createBiomeDecorator()
    {   
        return getModdedBiomeDecorator(new BiomeDecorator(this));
    }

    /**
     * Sets the temperature and rainfall of this biome.
     */
    public BiomeGenBase setTemperatureRainfall(float par1, float par2)
    {
        if (par1 > 0.1F && par1 < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else
        {
            this.temperature = par1;
            this.rainfall = par2;
            return this;
        }
    }

    /**
     * Sets the minimum and maximum height of this biome. Seems to go from -2.0 to 2.0.
     */
    public BiomeGenBase setMinMaxHeight(float par1, float par2)
    {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }
    
	//BWG4 ==========================
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
    }
	
	private BiomeGenBase beach(int beach)
	{
		beachID = beach;
		return this;
		// 0 = none, 1 = random, 2 = sand only, 3 = gravel only
	}
	
	private BiomeGenBase mountain(int start, boolean snow)
	{
		mountainStart = start;
		mountainSnow = snow;
		return this;
	}
	
	private BiomeGenBase defaultbeach()
	{
		defaultbeach = true;
		return this;
	}
	
	private BiomeGenBase canyon()
	{
		isCanyon = true;
		return this;
	}
	
	private BiomeGenBase disableLakes()
	{
		lakesDisabled = true;
		return this;
	}
	
	private BiomeGenBase randBlock(int number)
	{
		randblock = number;
		return this;
	}
	
	private BiomeGenBase snow(int snow)
	{
		snowLevel = snow;
		enableSnow = true;
		return this;
	}
	
    public final int getSnowLevel()
    {
        return this.snowLevel;
    }

	//BWG4 ==========================

    /**
     * Disable the rain for the biome.
     */
    public BiomeGenBase setDisableRain()
    {
        this.enableRain = false;
        return this;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    /**
     * sets enableSnow to true during biome initialization. returns BiomeGenBase.
     */
    public BiomeGenBase setEnableSnow()
    {
        this.enableSnow = true;
        return this;
    }

    public BiomeGenBase setBiomeName(String par1Str)
    {
        this.biomeName = par1Str;
        return this;
    }

    public BiomeGenBase func_76733_a(int par1)
    {
        this.field_76754_C = par1;
        return this;
    }

    public BiomeGenBase setColor(int par1)
    {
        this.color = par1;
        return this;
    }

    @SideOnly(Side.CLIENT)

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
        par1 /= 3.0F;

        if (par1 < -1.0F)
        {
            par1 = -1.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
    }

    /**
     * Returns the correspondent list of the EnumCreatureType informed.
     */
    public List getSpawnableList(EnumCreatureType par1EnumCreatureType)
    {
        return par1EnumCreatureType == EnumCreatureType.monster ? this.spawnableMonsterList : (par1EnumCreatureType == EnumCreatureType.creature ? this.spawnableCreatureList : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : (par1EnumCreatureType == EnumCreatureType.ambient ? this.spawnableCaveCreatureList : null)));
    }

    /**
     * Returns true if the biome have snowfall instead a normal rain.
     */
    public boolean getEnableSnow()
    {
        return this.enableSnow;
    }

    /**
     * Return true if the biome supports lightning bolt spawn, either by have the bolts enabled and have rain enabled.
     */
    public boolean canSpawnLightningBolt()
    {
        return this.enableSnow ? false : this.enableRain;
    }

    /**
     * Checks to see if the rainfall level of the biome is extremely high
     */
    public boolean isHighHumidity()
    {
        return this.rainfall > 0.85F;
    }

    /**
     * returns the chance a creature has to spawn.
     */
    public float getSpawningChance()
    {
        return 0.1F;
    }

    /**
     * Gets an integer representation of this biome's rainfall
     */
    public final int getIntRainfall()
    {
        return (int)(this.rainfall * 65536.0F);
    }

    /**
     * Gets an integer representation of this biome's temperature
     */
    public final int getIntTemperature()
    {
        return (int)(this.temperature * 65536.0F);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets a floating point representation of this biome's rainfall
     */
    public final float getFloatRainfall()
    {
        return this.rainfall;
    }

    /**
     * Gets a floating point representation of this biome's temperature
     */
    public final float getFloatTemperature()
    {
        return this.temperature;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        this.theBiomeDecorator.decorate(par1World, par2Random, par3, par4);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return getModdedBiomeGrassColor(ColorizerGrass.getGrassColor(d0, d1));
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double d0 = (double)MathHelper.clamp_float(this.getFloatTemperature(), 0.0F, 1.0F);
        double d1 = (double)MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
        return getModdedBiomeFoliageColor(ColorizerFoliage.getFoliageColor(d0, d1));
    }

    public BiomeDecorator getModdedBiomeDecorator(BiomeDecorator original)
    {
        BiomeEvent.CreateDecorator event = new BiomeEvent.CreateDecorator(this, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newBiomeDecorator;
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier()
    {
        BiomeEvent.GetWaterColor event = new BiomeEvent.GetWaterColor(this, waterColorMultiplier);
        MinecraftForge.EVENT_BUS.post(event);
        return event.newColor;
    }
    
    @SideOnly(Side.CLIENT)
    public int getModdedBiomeGrassColor(int original)
    {
        BiomeEvent.GetGrassColor event = new BiomeEvent.GetGrassColor(this, original);
        MinecraftForge.EVENT_BUS.post(event);
        return event.newColor;
    }
    

    @SideOnly(Side.CLIENT)
    public int getModdedBiomeFoliageColor(int original)
    {
        BiomeEvent.GetFoliageColor event = new BiomeEvent.GetFoliageColor(this, original);
        MinecraftForge.EVENT_BUS.post(event);
        return event.newColor;
    }
}
