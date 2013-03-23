package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.World;
import java.awt.Color;

public class BWG4BiomesGold4 extends BiomeGenBase
{
	private int packID;
	private int biomeID;

    public BWG4BiomesGold4(int id, int pack, int biome)
    {
        super(id);
		packID = pack;
		biomeID = biome;
		theBiomeDecorator.usebwg4deco = true;
		waterColorMultiplier = 0xC4F0FF;
		
		if(packID == 1) //DESERT
		{	
			if(biomeID == 1) //flat
			{
				topBlock = 0;//(byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;		
				theBiomeDecorator.deadBush = 1;  
				theBiomeDecorator.cactus = 5;
				spawnableCreatureList.clear();
			}
			else if(biomeID == 2) //hills
			{
				topBlock = 0;//(byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;
				theBiomeDecorator.deadBush = 2;  
				theBiomeDecorator.cactus = 10;
				spawnableCreatureList.clear();
			}
			else if(biomeID == 3) //lakes
			{
				topBlock = 0;//(byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;
				theBiomeDecorator.deadBush = 2;  
				theBiomeDecorator.cactus = 10;
				spawnableCreatureList.clear();
			}
			else if(biomeID == 4) //oasis
			{
				theBiomeDecorator.grass = 7; 
				theBiomeDecorator.tl1amount = 5; 
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 2;
				theBiomeDecorator.sugarcane = 20;
				theBiomeDecorator.melon = 8;
			}
			else if(biomeID == 5) //border
			{
				topBlock = 0; //(byte)Block.sand.blockID;
				fillerBlock = (byte)Block.sand.blockID;
				theBiomeDecorator.deadBush = 3;  
				theBiomeDecorator.cactus = 10;
				theBiomeDecorator.grass = 4; 
			}
		}		
		if(packID == 2) //SAVANNA
		{
			if(biomeID == 1) //field
			{
				theBiomeDecorator.grass = 12; 
				theBiomeDecorator.tl1amount = 1; 
				theBiomeDecorator.tl1chance = 4; 
			}
			else if(biomeID == 2) //forest
			{
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.tl1amount = 5; 
			}
			else if(biomeID == 3) //hills
			{
				theBiomeDecorator.grass = 6; 
				theBiomeDecorator.tl1amount = 7; 
			}
			else if(biomeID == 4) //borders
			{
				theBiomeDecorator.grass = 10; 
				theBiomeDecorator.tl1amount = 1; 
			}
		}
		if(packID == 3) //CANYON
		{
			theBiomeDecorator.mayrandtrees = false;
				
			if(biomeID == 1) //HILL
			{
				spawnableCreatureList.clear();
			}			
			else if(biomeID == 2 || biomeID == 3 || biomeID == 4) //valley - field - lake
			{
				theBiomeDecorator.grass = 3; 
				theBiomeDecorator.tl1amount = 6; 
				theBiomeDecorator.tl1maxy = 80; 
			}			
		}
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(packID == 1) //DESERT
		{
			if(biomeID == 4)
			{
				if(par1Random.nextInt(4) == 0) 
				{ 
					return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
				}
				else 
				{ 
					return worldGeneratorTrees; 
				}
			}	
			else if(biomeID == 5)
			{
				if(par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
				}
				else 
				{ 
					return worldGeneratorTrees; 
				}
			}	
		}
		if(packID == 2) //SAVANNA
		{
			if(biomeID == 1)
			{
				return new BWG4decoGold4(2);
			}			
			else if(biomeID == 2 || biomeID == 3)
			{
				if (par1Random.nextInt(7) == 0)
				{
					return new WorldGenShrub(3, 0);
				}	
				return new BWG4decoGold4(2);
			}				
			else
			{
				if (par1Random.nextInt(8) == 0)
				{
					return new WorldGenShrub(3, 0);
				}	
				if (par1Random.nextInt(5) != 0)
				{
					return new BWG4decoGold4(2);
				}	
				return worldGeneratorTrees;
			}	
		}
		if(packID == 3) //CANYON
		{
			if (par1Random.nextInt(16) == 0)
			{
				return new BWG4decoGold4(2);
			}	
			return new WorldGenShrub(3, 0);
		}
		return worldGeneratorTrees;
    }
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		return new BWG4decoSurvival(4);
    }	

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		super.decorate(par1World, par2Random, par3, par4);
		
		if(packID == 3) //CANYON
		{
			if(biomeID == 2 || biomeID == 3 || biomeID == 4)
			{
				for (int cacti = 0; cacti < 8; ++cacti)
				{
					int cacti1 = par3 + par2Random.nextInt(16) + 8;
					int cacti3 = par4 + par2Random.nextInt(16) + 8;
					(new BWG4decoGold4(1)).generate(par1World, par2Random, cacti1, par1World.getHeightValue(cacti1, cacti3), cacti3);
				}
				//for (int stone = 0; stone < 5; ++stone)
				//{
				//	int stone1 = par3 + par2Random.nextInt(16) + 8;
				//	int stone2 = par4 + par2Random.nextInt(16) + 8;
				//	(new BWG4decoGold4(2, Block.stone.BlockID)).generate(par1World, par2Random, stone1, par1World.getHeightValue(stone1, stone2), stone2);
				//}
			}			
		}
    }
}
