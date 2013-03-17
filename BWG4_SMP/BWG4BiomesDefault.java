package net.minecraft.src;

import java.util.Random;

public class BWG4BiomesDefault extends BiomeGenBase
{
	private int biomeid;
	private int type;
	private int id;

    public BWG4BiomesDefault(int par1, int vartype, int varid)
    {
        super(par1);
		biomeid = par1;	
		type = vartype;
		id = varid;
		theBiomeDecorator.usebwg4deco = true;

		if(type == 1)
		{
			if(id == 1)//Ocean
			{
				spawnableCreatureList.clear();
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2; 
				theBiomeDecorator.grass = 4; 
				theBiomeDecorator.tl1amount = 1;
			}
			if(id == 2)//Tropical Island
			{
				theBiomeDecorator.mayrandtrees = false;
				theBiomeDecorator.grass = 8;
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.sugarcane = 20;
				theBiomeDecorator.melon = 8;
				theBiomeDecorator.tl1amount = 5; 
				theBiomeDecorator.cactus = 1;
				theBiomeDecorator.usetl2 = true;
				theBiomeDecorator.tl2miny = 0; 
				theBiomeDecorator.tl2maxy = 75; 
				theBiomeDecorator.tl2amount = 4;
			}
			if(id == 3)//Jungle Island
			{
				theBiomeDecorator.mayrandtrees = false;
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.waterlily = 5;
				theBiomeDecorator.sugarcane = 20;
				theBiomeDecorator.grass = 8;
				theBiomeDecorator.melon = 8;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
				theBiomeDecorator.tl1amount = 15;
			}
			if(id == 4)//Mushroom Island
			{
				theBiomeDecorator.bigMushrooms = 1;
				theBiomeDecorator.brownmush = 1;
				theBiomeDecorator.redmush = 1;
				theBiomeDecorator.tl1amount = -20;
				theBiomeDecorator.redflowers = -20;
				theBiomeDecorator.yellowflowers = -20; 
				topBlock = (byte)Block.mycelium.blockID;
				spawnableMonsterList.clear();
				spawnableCreatureList.clear();
				spawnableWaterCreatureList.clear();
				spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
			}
			if(id == 5)//Beach
			{
				spawnableCreatureList.clear();
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2; 
				theBiomeDecorator.grass = 4; 
				theBiomeDecorator.tl1amount = 1;
				topBlock = (byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;		
			}
			if(id == 6)//Beach Dunes
			{
				spawnableCreatureList.clear();
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2; 
				theBiomeDecorator.grass = 4; 
				theBiomeDecorator.tl1amount = 1;
				topBlock = (byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;		
			}
		}		
		else if(type == 2)
		{
			if(id == 1)//Snow Pines
			{
				theBiomeDecorator.mayrandtrees = false;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.redflowers = 3;
				theBiomeDecorator.yellowflowers = 2; 
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.brownmush = 1;
				theBiomeDecorator.redmush = 1;
				theBiomeDecorator.tl1amount = 12;
			}
			if(id == 2)//Snow Forest
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.grass = 3;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.tl1amount = 3; 
			}
			if(id == 3)//Snow Taiga
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.tl1amount = 5;
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 1; 
				theBiomeDecorator.brownmush = 1;
			}
			if(id == 4)//Snow Plains
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				theBiomeDecorator.tl1amount = -20;
				theBiomeDecorator.grass = -10; 
				theBiomeDecorator.redflowers = -10;
				theBiomeDecorator.yellowflowers = -10; 
			}
			if(id == 5)//Snow Hills
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				theBiomeDecorator.tl1amount = -20;
				theBiomeDecorator.grass = -10; 
				theBiomeDecorator.redflowers = -10;
				theBiomeDecorator.yellowflowers = -10; 
			}
		}		
		else if(type == 3)
		{
			if(id == 1)//Plains
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				theBiomeDecorator.grass = 8;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.tl1amount = 0;
				theBiomeDecorator.tl1chance = 4; 
			}
			if(id == 2)//Forest
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.grass = 3;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.tl1amount = 3; 
			}
			if(id == 3)//Forest Hills
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.grass = 3;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.tl1amount = 2; 
			}
			if(id == 4)//Forest Lakes
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.grass = 3;
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.tl1amount = 3; 
			}
			if(id == 5)//Pines
			{
				theBiomeDecorator.mayrandtrees = false;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.redflowers = 3;
				theBiomeDecorator.yellowflowers = 2; 
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.brownmush = 1;
				theBiomeDecorator.redmush = 1;
				theBiomeDecorator.tl1amount = 12;
			}
			if(id == 6)//Taiga
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.emeralds = true;
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
				theBiomeDecorator.tl1amount = 4;
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.redflowers = 2;
				theBiomeDecorator.yellowflowers = 1; 
				theBiomeDecorator.brownmush = 1;
			}
			if(id == 7)//Grassland
			{
				theBiomeDecorator.emeralds = true;
				theBiomeDecorator.tl1amount = -20;
				theBiomeDecorator.grass = 10; 
				theBiomeDecorator.redflowers = 1;
				theBiomeDecorator.yellowflowers = 1; 
			}
		}		
		else if(type == 4)
		{
			if(id == 1)//Rainforest
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.sugarcane = 20;
				theBiomeDecorator.grass = 8;
				theBiomeDecorator.melon = 8;
				theBiomeDecorator.tl1amount = 5;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
			}
			if(id == 2 || id == 4)//Jungle
			{			
				theBiomeDecorator.mayrandtrees = false;
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.sugarcane = 20;
				theBiomeDecorator.grass = 8;
				theBiomeDecorator.melon = 8;
				spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
				spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));	
				theBiomeDecorator.tl1amount = 17;
			
				if(id != 4)
				{
					theBiomeDecorator.waterlily = 4;
					waterColorMultiplier = 0x99DB37;	
				}
			}
			if(id == 3 || id == 5)//Swampland
			{
				theBiomeDecorator.mayrandtrees = true;
				theBiomeDecorator.tl1amount = 5; 
				theBiomeDecorator.redflowers = 0;
				theBiomeDecorator.yellowflowers = 0; 
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.sugarcane = 40; 
				theBiomeDecorator.brownmush = 4;
				theBiomeDecorator.redmush = 3; 
				theBiomeDecorator.deadBush = 1;
				spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
			
				if(id != 5)
				{
					theBiomeDecorator.waterlily = 4;
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
				theBiomeDecorator.deadBush = 2;  
				theBiomeDecorator.cactus = 10;
				spawnableCreatureList.clear();
			}
			if(id == 2)//Savanna
			{
				theBiomeDecorator.grass = 12; 
				theBiomeDecorator.tl1amount = 1; 
				theBiomeDecorator.tl1chance = 4; 
			}
			if(id == 3)//Savanna Forest
			{
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.tl1amount = 5; 
			}
			if(id == 4)//Shrubland
			{
				theBiomeDecorator.grass = 3; 
				theBiomeDecorator.tl1amount = 6; 
				theBiomeDecorator.tl1maxy = 80; 
			}
			if(id == 5)//SandStone Hill
			{
				spawnableCreatureList.clear();
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
				theBiomeDecorator.waterlily = 2;
				waterColorMultiplier = 0x99DB37;	
				theBiomeDecorator.tl1amount = -10;
			}
			if(id == 4)//SAND RIVER
			{
				spawnableCreatureList.clear();
				topBlock = 0;		
				fillerBlock = (byte)Block.sand.blockID;		
				theBiomeDecorator.deadBush = 2;  
				theBiomeDecorator.cactus = 10;
			}
		}
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
				if(par1Random.nextInt(12) == 0) {	return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); }
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
			}
		}		
		else if(type == 4)
		{
			if(id == 1)//Rainforest
			{
				if (par1Random.nextInt(4) == 0) 
				{ 
					return new BWG4decoBigTree(4 + par1Random.nextInt(11), 0); 
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
				return new WorldGenShrub(3, 0);
			}
		}
		return new WorldGenShrub(3, 0);
    }
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		return new BWG4decoSurvival(4);
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
				for (int cacti = 0; cacti < 8; ++cacti)
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
}