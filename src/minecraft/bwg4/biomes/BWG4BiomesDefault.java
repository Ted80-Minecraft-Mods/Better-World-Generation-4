package bwg4.biomes;

import java.util.Random;

import bwg4.deco.BWG4decoBigTree;
import bwg4.deco.BWG4decoDefault;
import bwg4.deco.BWG4decoGold1;
import bwg4.deco.BWG4decoGold3;
import bwg4.deco.BWG4decoGold4;
import bwg4.deco.BWG4decoSurvival;
import bwg4.deco.BWG4decoSwampTrees;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesDefault extends BiomeGenBase
{
	private int type;
	private int id;
	
	private BWG4Decorator bwg4decorator;

    public BWG4BiomesDefault(int par1, int vartype, int varid)
    {
        super(par1);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		type = vartype;
		id = varid;
		bwg4decorator.usebwg4deco = true;

		if(type == 1)
		{
			if(id == 1)//Ocean
			{
				spawnableCreatureList.clear();
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2; 
				bwg4decorator.grass = 4; 
				bwg4decorator.tl1amount = 1;
			}
			if(id == 2)//Tropical Island
			{
				bwg4decorator.mayrandtrees = false;
				bwg4decorator.grass = 8;
				bwg4decorator.redflowers = 4;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.sugarcane = 20;
				bwg4decorator.melon = 8;
				bwg4decorator.tl1amount = 5; 
				bwg4decorator.cactus = 1;
				bwg4decorator.usetl2 = true;
				bwg4decorator.tl2tree = new BWG4decoSurvival(4);
				bwg4decorator.tl2miny = 0; 
				bwg4decorator.tl2maxy = 75; 
				bwg4decorator.tl2amount = 4;
			}
			if(id == 3)//Jungle Island
			{
				bwg4decorator.mayrandtrees = false;
				bwg4decorator.redflowers = 4;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.waterlily = 5;
				bwg4decorator.sugarcane = 20;
				bwg4decorator.grass = 8;
				bwg4decorator.melon = 8;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
				bwg4decorator.tl1amount = 15;
			}
			if(id == 4)//Mushroom Island
			{
				bwg4decorator.bigMushrooms = 1;
				bwg4decorator.brownmush = 1;
				bwg4decorator.redmush = 1;
				bwg4decorator.tl1amount = -20;
				bwg4decorator.redflowers = -20;
				bwg4decorator.yellowflowers = -20; 
				topBlock = (byte)Block.mycelium.blockID;
				spawnableMonsterList.clear();
				spawnableCreatureList.clear();
				spawnableWaterCreatureList.clear();
				spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
			}
			if(id == 5)//Beach
			{
				spawnableCreatureList.clear();
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2; 
				bwg4decorator.grass = 4; 
				bwg4decorator.tl1amount = 1;
				topBlock = (byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;		
			}
			if(id == 6)//Beach Dunes
			{
				spawnableCreatureList.clear();
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2; 
				bwg4decorator.grass = 4; 
				bwg4decorator.tl1amount = 1;
				topBlock = (byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;	
			}
		}		
		else if(type == 2)
		{
			if(id == 1)//Snow Pines
			{
				bwg4decorator.mayrandtrees = false;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.redflowers = 3;
				bwg4decorator.yellowflowers = 2; 
				bwg4decorator.grass = 6; 
				bwg4decorator.brownmush = 1;
				bwg4decorator.redmush = 1;
				bwg4decorator.tl1amount = 12;
			}
			if(id == 2)//Snow Forest
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.grass = 3;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.tl1amount = 3; 
			}
			if(id == 3)//Snow Taiga
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.tl1amount = 5;
				bwg4decorator.grass = 6; 
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 1; 
				bwg4decorator.brownmush = 1;
			}
			if(id == 4)//Snow Plains
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				bwg4decorator.tl1amount = -20;
				bwg4decorator.grass = -10; 
				bwg4decorator.redflowers = -10;
				bwg4decorator.yellowflowers = -10; 
			}
			if(id == 5)//Snow Hills
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				bwg4decorator.tl1amount = -20;
				bwg4decorator.grass = -10; 
				bwg4decorator.redflowers = -10;
				bwg4decorator.yellowflowers = -10; 
			}
		}		
		else if(type == 3)
		{
			if(id == 1)//Plains
			{
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				bwg4decorator.grass = 8;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.tl1amount = 0;
				bwg4decorator.tl1chance = 4; 
			}
			if(id == 2)//Forest
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.grass = 3;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.tl1amount = 3; 
			}
			if(id == 3)//Forest Hills
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.grass = 3;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.tl1amount = 2; 
			}
			if(id == 4)//Forest Lakes
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.grass = 3;
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.tl1amount = 3; 
			}
			if(id == 5)//Pines
			{
				bwg4decorator.mayrandtrees = false;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.redflowers = 3;
				bwg4decorator.yellowflowers = 2; 
				bwg4decorator.grass = 6; 
				bwg4decorator.brownmush = 1;
				bwg4decorator.redmush = 1;
				bwg4decorator.tl1amount = 12;
			}
			if(id == 6)//Taiga
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				bwg4decorator.tl1amount = 4;
				bwg4decorator.grass = 6; 
				bwg4decorator.redflowers = 2;
				bwg4decorator.yellowflowers = 1; 
				bwg4decorator.brownmush = 1;
			}
			if(id == 7)//Grassland
			{
				spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.tl1amount = 1; 
				bwg4decorator.tl1chance = 2;
				bwg4decorator.tl1maxy = 85;
				bwg4decorator.usetl2 = true;
				bwg4decorator.tl2amount = 1;
				bwg4decorator.tl2chance = 4;
				bwg4decorator.tl2maxy = 72;
				bwg4decorator.tl2tree = new BWG4decoBigTree();
				bwg4decorator.emeralds = true;
				bwg4decorator.grass = 15; 
				bwg4decorator.redflowers = 1;
				bwg4decorator.yellowflowers = 1; 
			}
		}		
		else if(type == 4)
		{
			if(id == 1)//Rainforest
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.redflowers = 4;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.sugarcane = 20;
				bwg4decorator.grass = 8;
				bwg4decorator.melon = 8;
				bwg4decorator.tl1amount = 5;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
			}
			if(id == 2 || id == 4)//Jungle
			{			
				bwg4decorator.mayrandtrees = false;
				bwg4decorator.redflowers = 4;
				bwg4decorator.yellowflowers = 2;
				bwg4decorator.sugarcane = 20;
				bwg4decorator.grass = 8;
				bwg4decorator.melon = 8;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
				bwg4decorator.tl1amount = 17;
			
				if(id != 4)
				{
					bwg4decorator.waterlily = 4;
					waterColorMultiplier = 0x99DB37;	
				}
			}
			if(id == 3 || id == 5)//Swampland
			{
				bwg4decorator.mayrandtrees = true;
				bwg4decorator.tl1amount = 5; 
				bwg4decorator.redflowers = 0;
				bwg4decorator.yellowflowers = 0; 
				bwg4decorator.grass = 6; 
				bwg4decorator.sugarcane = 40; 
				bwg4decorator.brownmush = 4;
				bwg4decorator.redmush = 3; 
				bwg4decorator.deadBush = 1;
				spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
			
				if(id != 5)
				{
					bwg4decorator.waterlily = 4;
					waterColorMultiplier = 0x99DB37;	
				}
			}
		}		
		else if(type == 5)
		{
			if(id == 1)//Desert
			{
				topBlock = 0;//(byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;		
				bwg4decorator.deadBush = 2;  
				bwg4decorator.cactus = 10;
				spawnableCreatureList.clear();
			}
			if(id == 2)//Savanna
			{
				bwg4decorator.grass = 12; 
				bwg4decorator.tl1amount = 1; 
				bwg4decorator.tl1chance = 4; 
			}
			if(id == 3)//Savanna Forest
			{
				bwg4decorator.grass = 6; 
				bwg4decorator.tl1amount = 5; 
			}
			if(id == 4)//Shrubland
			{
				bwg4decorator.grass = 5; 
				bwg4decorator.tl1amount = 3; 
			}
		}
		else if(type == 6)
		{
			if(id == 1)//ICE RIVER
			{
				spawnableCreatureList.clear();
			}
			if(id == 2)//NORMAL RIVER
			{
				spawnableCreatureList.clear();
			}
			if(id == 3)//GREEN RIVER
			{
				spawnableCreatureList.clear();
				bwg4decorator.waterlily = 2;
				waterColorMultiplier = 0x99DB37;	
				bwg4decorator.tl1amount = -10;
			}
			if(id == 4)//SAND RIVER
			{
				spawnableCreatureList.clear();
				topBlock = 0;		
				fillerBlock = (byte)Block.sand.blockID;		
				bwg4decorator.deadBush = 2;  
				bwg4decorator.cactus = 10;
			}
		}

		bwg4decorator.treesPerChunk = bwg4decorator.tl1amount;
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(type == 1)
		{
			if(id == 1)//Ocean
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
			if(id == 2)//Tropical Island
			{
				if(par1Random.nextInt(10) == 0) { return new WorldGenShrub(3, 0); }
				if(par1Random.nextInt(12) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
				if(par1Random.nextInt(5) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
				else { return worldGeneratorTrees; }
			}
			if(id == 3)//Jungle Island
			{
				if (par1Random.nextInt(8) == 0) { return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
				if (par1Random.nextInt(4) == 0) { return new WorldGenShrub(3, 0); }
				if (par1Random.nextInt(3) == 0) { return new WorldGenHugeTrees(false, 20 + par1Random.nextInt(5), 3, 3); } 
				else { return worldGeneratorTrees; }
			}
			if(id == 5)//Beach
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
			if(id == 6)//Beach Dunes
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
		}		
		else if(type == 2)
		{
			if(id == 1)//Snow Pines
			{
				if (par1Random.nextInt(5) == 0) 
				{ 
					return new BWG4decoGold1(3, 6, 16, 3); 
				}	
				else if (par1Random.nextInt(2) == 0) 
				{ 
					return new BWG4decoGold1(1, 6, 14, 0);
				}
				else if (par1Random.nextInt(2) == 0) 
				{ 
					return new BWG4decoGold1(2, 6, 12, 3); 
				}
				else 
				{ 
					return worldGeneratorTrees; 
				}
			}
			if(id == 2)//Snow Forest
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
			if(id == 3)//Snow Taiga
			{
				return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
			}
		}		
		else if(type == 3)
		{
			if(id == 1)//Plains
			{
				return new BWG4decoBigTree(4 + par1Random.nextInt(11), 0); 
			}
			if(id == 2 || id == 3 || id == 4)//Forest - Forest Hills - Forest Lakes
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
			if(id == 5)//Pines
			{
				if (par1Random.nextInt(5) == 0) 
				{ 
					return new BWG4decoGold1(3, 6, 16, 3); 
				}	
				else if (par1Random.nextInt(2) == 0) 
				{ 
					return new BWG4decoGold1(1, 6, 14, 0);
				}
				else if (par1Random.nextInt(2) == 0) 
				{ 
					return new BWG4decoGold1(2, 6, 12, 3); 
				}
				else 
				{ 
					return worldGeneratorTrees; 
				}
			}
			if(id == 6)//Taiga
			{
				return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false));
			}
			if(id == 7)//Grassland
			{
				if (par1Random.nextInt(8) == 0) 
				{ 
					return worldGeneratorForest; 
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
		}		
		else if(type == 4)
		{
			if(id == 1)//Rainforest
			{
				if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(4 + par1Random.nextInt(11), 0); 
				}
				else if (par1Random.nextInt(3) == 0) 
				{ 
					return new WorldGenShrub(3, 0); 
				}
				else if (par1Random.nextInt(3) != 0) 
				{ 
					return new BWG4decoGold3(2);
				}
				else
				{
					return worldGeneratorTrees;
				}
			}
			if(id == 2 || id == 4)//Jungle
			{
				if (par1Random.nextInt(8) == 0) 
				{ 
					return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
				}
				if (par1Random.nextInt(4) == 0) 
				{ 
					return new WorldGenShrub(3, 0); 
				}
				if (par1Random.nextInt(3) != 0) 
				{ 
					return new WorldGenHugeTrees(false, 15 + par1Random.nextInt(10), 3, 3); 
				} 
				else 
				{ 
					return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true); 
				}
			}
			if(id == 3 || id == 5)//Swampland
			{
				if(id == 5)
				{
					if (par1Random.nextInt(7) == 0) 
					{ 
						return new BWG4decoBigTree(4 + par1Random.nextInt(11), 0); 
					}
					else if (par1Random.nextInt(6) == 0) 
					{ 
						return new WorldGenShrub(3, 0); 
					}
					else if (par1Random.nextInt(6) == 0) 
					{
						return worldGeneratorTrees;
					}
				}	
				return new BWG4decoSwampTrees(par1Random.nextInt(6) + 5);
			}
		}		
		else if(type == 5)
		{
			if(id == 2)//Savanna
			{
				return new BWG4decoGold4(2);
			}
			if(id == 3)//Savanna Forest
			{
				if (par1Random.nextInt(7) == 0)
				{
					return new WorldGenShrub(3, 0);
				}	
				return new BWG4decoGold4(2);
			}
			if(id == 4)//Shrubland
			{
				if (par1Random.nextInt(17) == 0)
				{
					return new BWG4decoGold4(2);
				}	
				return new WorldGenShrub(3, 0);
			}
		}
		return new WorldGenShrub(3, 0);
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);		
		if(type == 1)
		{
			if(id == 6)//Dunes
			{
				for (int g = 0; g < 10; ++g)
				{
					int g1 = par3 + par2Random.nextInt(16) + 8;
					int g3 = par4 + par2Random.nextInt(16) + 8;
					(new BWG4decoDefault(1)).generate(par1World, par2Random, g1, par1World.getHeightValue(g1, g3), g3);
				}
			}
		}
		if(type == 5)
		{
			if(id == 4)//Shrubland
			{
				for (int cacti = 0; cacti < 4; ++cacti)
				{
					int cacti1 = par3 + par2Random.nextInt(16) + 8;
					int cacti3 = par4 + par2Random.nextInt(16) + 8;
					(new BWG4decoGold4(1)).generate(par1World, par2Random, cacti1, par1World.getHeightValue(cacti1, cacti3), cacti3);
				}
			}
		}
    }
	
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		if(type == 4)
		{
			if(id == 1)//Rainforest
			{
				return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
			}
		}
		return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
    
	public int getBiomeGrassColor()
    {
		if(type == 2)
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerGrass.getGrassColor(d, d1);
		}
    }

    public int getBiomeFoliageColor()
    {
		if(type == 2)
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}
    }
}

