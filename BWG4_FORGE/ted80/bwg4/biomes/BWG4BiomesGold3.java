package ted80.bwg4.biomes;

import java.util.Random;
import java.awt.Color;

import ted80.bwg4.deco.BWG4decoBigTree;
import ted80.bwg4.deco.BWG4decoGold3;
import ted80.bwg4.deco.BWG4decoSurvival;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesGold3 extends BiomeGenBase
{
	private int packID;
	private int biomeID;
	private int junglebig = 10, shrub = 3, jungletall = 3, tallheight = 25;
	private int tropshrub = 14, hugetree = 15, bigtree = 4;

	private BWG4Decorator bwg4decorator;

    public BWG4BiomesGold3(int id, int pack, int biome)
    {
        super(id);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		packID = pack;
		biomeID = biome;
		bwg4decorator.usebwg4deco = true;
		bwg4decorator.lavaliquid = 8;

		if(packID == 1) //JUNGLE
		{
			bwg4decorator.mayrandtrees = false;
			bwg4decorator.redflowers = 4;
			bwg4decorator.yellowflowers = 2;
			bwg4decorator.waterlily = 5;
			bwg4decorator.sugarcane = 20;
			bwg4decorator.grass = 8;
			bwg4decorator.melon = 8;
			waterColorMultiplier = 0x99DB37;
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
			
			if(biomeID == 1) //Jungle_Flat - Jungle_Flat_Lake
			{
				bwg4decorator.tl1amount = 6; 
				junglebig = 10; shrub = 7; jungletall = 4;  tallheight = 45;
			}			
			else if(biomeID == 2) //Jungle_Flat_Open
			{
				bwg4decorator.tl1amount = 3; 
				junglebig = 30; shrub = 16; jungletall = 4;  tallheight = 50;
			}			
			else if(biomeID == 3) //Jungle_Hilly
			{
				bwg4decorator.tl1amount = 25;
				junglebig = 8; shrub = 4; jungletall = 3; tallheight = 30;
			}				
			else if(biomeID == 4) //Jungle_Deep
			{
				bwg4decorator.tl1amount = 25;
				junglebig = 8; shrub = 4; jungletall = 3; tallheight = 30;
			}			
			else if(biomeID == 5) //Jungle_Plateau
			{
				bwg4decorator.tl1amount = 8; 
				junglebig = 20; shrub = 10; jungletall = 4;  tallheight = 35;
			}		
			else if(biomeID == 6) //Jungle_Vulcano_Base - Jungle_Vulcano_Gravel
			{
				spawnableCreatureList.clear();
				junglebig = 10; shrub = 3; jungletall = 3; tallheight = 30;
				topBlock = (byte)Block.obsidian.blockID;
				fillerBlock = (byte)Block.obsidian.blockID;
			}	
			else if(biomeID == 7) //Jungle_Border
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.tl1amount = 7; 
				junglebig = 8; shrub = 14; jungletall = 5; tallheight = 25;
			}
		}
		else if(packID == 2) //Tropical
		{
			bwg4decorator.mayrandtrees = false;
			bwg4decorator.grass = 8;
			bwg4decorator.redflowers = 4;
			bwg4decorator.yellowflowers = 2;
			bwg4decorator.sugarcane = 20;
			bwg4decorator.melon = 8;
			waterColorMultiplier = 0x00BFFF;
			
			if(biomeID == 1) //Tropical_Sea
			{
				bwg4decorator.grass = -10;
				bwg4decorator.redflowers = -10;
				bwg4decorator.yellowflowers = -10;
				bwg4decorator.tl1amount = -10; 
			}
			else if(biomeID == 2) //Tropical_Island
			{
				bwg4decorator.tl1amount = 10; 
				tropshrub = 8; hugetree = 8; bigtree = 3;
			}
			else if(biomeID == 3) //Tropical_Beach - Tropical_Small
			{
				bwg4decorator.tl1amount = 5; 
				bwg4decorator.cactus = 1;
				tropshrub = 10; hugetree = 12; bigtree = 5;

				bwg4decorator.usetl2 = true;
				bwg4decorator.tl2miny = 0; 
				bwg4decorator.tl2maxy = 75; 
				bwg4decorator.tl2amount = 4;
			}
			else if(biomeID == 4) //Tropical_Border
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.tl1amount = 6; 
				tropshrub = 15; hugetree = 20; bigtree = 10;
				
				bwg4decorator.usetl2 = true;
				bwg4decorator.tl2miny = 0; 
				bwg4decorator.tl2maxy = 75; 
				bwg4decorator.tl2amount = 1;
			}
		}
		else if(packID == 3) //Rainforest
		{
			bwg4decorator.mayrandtrees = false;
			bwg4decorator.sugarcane = 30;
			bwg4decorator.melon = 6;
			bwg4decorator.redflowers = 3;
			bwg4decorator.yellowflowers = 4;
			bwg4decorator.grass = 5;
			bwg4decorator.waterlily = 5;
			bwg4decorator.sugarcane = 30;
			bwg4decorator.melon = 6;
			waterColorMultiplier = 0x99DB37;
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
			spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
			
			if(biomeID == 1) //flat
			{
				bwg4decorator.tl1amount = 9; 
			}
			if(biomeID == 2) //field
			{
				bwg4decorator.tl1amount = -10; 
				bwg4decorator.tl1chance = 2;
				bwg4decorator.grass = 9;
			}
			if(biomeID == 3) //hills
			{
				bwg4decorator.tl1amount = 9; 
			}
			if(biomeID == 4) //lakes
			{
				bwg4decorator.tl1amount = 8; 
			}
			if(biomeID == 5) //lake
			{
				bwg4decorator.tl1amount = 8; 
			}
			if(biomeID == 6) //border
			{
				bwg4decorator.tl1amount = 4; 
			}
			
			bwg4decorator.tl1amount = 14; 
		}
		else if(packID == 4) //RAINFOREST-SWAMP
		{
			bwg4decorator.mayrandtrees = true;
			bwg4decorator.grass = 8;
			bwg4decorator.redflowers = 1;
			bwg4decorator.yellowflowers = 1;
			bwg4decorator.waterlily = 3;
			bwg4decorator.sugarcane = 20;
			bwg4decorator.melon = 8;
			waterColorMultiplier = 0x99DB37;
			spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
			if(biomeID == 1) //RAINFOREST-SWAMP
			{
				bwg4decorator.tl1amount = 2; 
			}
		}
    }
	
    public int getSkyColorByTemp(float par1)
    {
		if(packID == 1) //Jungle
		{
			if(biomeID == 7)
			{
				return 0xBFFFDF;
			}
			return 0xD0FFBF;
		}
		if(packID == 2 || packID == 3) //Tropical
		{
			return 0x8DD0FC;
		}
		else
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
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(packID == 1) //JUNGLE
		{
			if (par1Random.nextInt(junglebig) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
			if (par1Random.nextInt(shrub) == 0) { return new WorldGenShrub(3, 0); }
			if(biomeID == 7) { 
				if (par1Random.nextInt(jungletall) == 0) { return new WorldGenHugeTrees(false, 25 + par1Random.nextInt(10), 3, 3); } else { return worldGeneratorTrees; }
			} else {
				if (par1Random.nextInt(jungletall) != 0) { return new WorldGenHugeTrees(false, 25 + par1Random.nextInt(10), 3, 3); } else { return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); }
			}	
		}
		else if(packID == 2) //PARADISE
		{
			if(par1Random.nextInt(tropshrub) == 0) { return new WorldGenShrub(3, 0); }
			if(par1Random.nextInt(hugetree) == 0) {	return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
			if(par1Random.nextInt(bigtree) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
			else { return worldGeneratorTrees; }
		}
		else if(packID == 3) //RAINFORESTS
		{
			if(biomeID == 6) 
			{
				if(par1Random.nextInt(3) == 0) { return new WorldGenShrub(3, 0); }
				if(par1Random.nextInt(4) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
				if(par1Random.nextInt(2) == 0) { return worldGeneratorTrees; }
				return new BWG4decoGold3(2);
			}
			if(par1Random.nextInt(6) == 0) { return new WorldGenShrub(3, 0); }
			if(par1Random.nextInt(7) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
			if(par1Random.nextInt(6) == 0) { return worldGeneratorTrees; }
			return new BWG4decoGold3(2);
		}
		else if(packID == 4) //RAINFOREST-SWAMP
		{
			if(par1Random.nextInt(6) == 0) { return new WorldGenShrub(3, 0); }
			if(par1Random.nextInt(7) == 0) { return worldGeneratorTrees; }
			return new BWG4decoGold3(3);
		}
		return worldGeneratorTrees;
    }
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		return new BWG4decoSurvival(4);
    }	
	
    public int getBiomeGrassColor()
    {
		double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F); 
		double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		return ColorizerGrass.getGrassColor(d, d1);
    }

    public int getBiomeFoliageColor()
    {
		double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
		double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
		return ColorizerFoliage.getFoliageColor(d, d1);
    }
	
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		if(packID == 3) //RAINFORESTS
		{
			return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
		}
		else
		{
			return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
		}	
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		super.decorate(par1World, par2Random, par3, par4);
		
		if(packID == 1) //JUNGLE
		{	
		}
		if(packID == 2) //TROPICAL
		{	
		}
		if(packID == 3) //RAINFOREST
		{	
			WorldGenVines var5 = new WorldGenVines();

			for (int var6 = 0; var6 < 50; ++var6)
			{
				int var7 = par3 + par2Random.nextInt(16) + 8;
				byte var8 = 64;
				int var9 = par4 + par2Random.nextInt(16) + 8;
				var5.generate(par1World, par2Random, var7, var8, var9);
			}
		}	
		if(packID == 4) //RAINFOREST-SWAMP
		{	
			WorldGenVines var5 = new WorldGenVines();

			for (int var6 = 0; var6 < 50; ++var6)
			{
				int var7 = par3 + par2Random.nextInt(16) + 8;
				byte var8 = 64;
				int var9 = par4 + par2Random.nextInt(16) + 8;
				var5.generate(par1World, par2Random, var7, var8, var9);
			}
		}	
    }
}
