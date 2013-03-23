package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.World;
import java.awt.Color;

public class BWG4BiomesGold2 extends BiomeGenBase
{
	private int packID;
	private int biomeID;

    public BWG4BiomesGold2(int id, int pack, int biome)
    {
        super(id);
		packID = pack;
		biomeID = biome;
		theBiomeDecorator.usebwg4deco = true;
		
		if(packID == 1) //PineForest
		{
			theBiomeDecorator.mayrandtrees = false;
			theBiomeDecorator.emeralds = true;
			theBiomeDecorator.redflowers = 3;
			theBiomeDecorator.yellowflowers = 2; 
			theBiomeDecorator.grass = 6; 
			theBiomeDecorator.brownmush = 1;
			theBiomeDecorator.redmush = 1; 
			theBiomeDecorator.tl1maxy = 99; 
		
			if(biomeID == 1)//Pine_Normal
			{
				theBiomeDecorator.tl1amount = 14;
			}
			if(biomeID == 2)//Pine_Hills
			{
				theBiomeDecorator.tl1amount = 14;
			}
			if(biomeID == 3)//Pine_Mix
			{
				theBiomeDecorator.tl1amount = 10;
			}
			if(biomeID == 4)//Pine_Valleys
			{
				theBiomeDecorator.tl1amount = 12;
				theBiomeDecorator.tl1miny = 70;
				theBiomeDecorator.grass = 8; 
				theBiomeDecorator.usetl2 = true;
				theBiomeDecorator.tl2maxy = 80; 
				theBiomeDecorator.tl2amount = 1;
			}
			if(biomeID == 5)//Pine_Border
			{
				theBiomeDecorator.tl1amount = 9;
			}
		}
		if(packID == 2)
		{
			theBiomeDecorator.grass = 3; 
			theBiomeDecorator.redflowers = 2;
			theBiomeDecorator.yellowflowers = 2; 
				
			if(biomeID == 1) //Forest_Normal
			{
				theBiomeDecorator.tl1amount = 5;
			}
			if(biomeID == 2) //Forest_High
			{
				theBiomeDecorator.tl1amount = 5;
			}
			if(biomeID == 3) //Forest_Lakes
			{
				theBiomeDecorator.tl1amount = 4;
			}
			if(biomeID == 4) //Forest_Plains
			{
				theBiomeDecorator.tl1amount = -20;
				theBiomeDecorator.redflowers = 4;
				theBiomeDecorator.yellowflowers = 4; 
				theBiomeDecorator.grass = 8; 
			}
			if(biomeID == 5) //Forest_Border
			{
				theBiomeDecorator.tl1amount = 4;
			}
		}
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(packID == 1) //PineForest
		{
			if(biomeID == 1 || biomeID == 2)//all
			{
				if(par1Random.nextInt(14) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(8) == 0) 
				{ 
					return new BWG4decoGold1(3, 6, 16, 3); 
				}	
				else if (par1Random.nextInt(3) == 0) 
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
			else if(biomeID == 4)//no big
			{
				if(par1Random.nextInt(16) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(12) == 0) 
				{ 
					return new BWG4decoGold1(3, 6, 16, 3); 
				}	
				else if (par1Random.nextInt(3) == 0) 
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
			else if(biomeID == 3 || biomeID == 5)//mix
			{
				if(par1Random.nextInt(12) == 0) 
				{ 
					return new BWG4decoBigTree(6 + par1Random.nextInt(9), 0); 
				}
				else if (par1Random.nextInt(7) == 0) 
				{ 
					return new BWG4decoGold1(3, 6, 16, 3); 
				}	
				else if (par1Random.nextInt(4) == 0) 
				{ 
					return new BWG4decoGold1(1, 6, 14, 0);
				}
				else if (par1Random.nextInt(3) == 0) 
				{ 
					return new BWG4decoGold1(2, 6, 12, 3); 
				}
				else 
				{ 
					return worldGeneratorTrees; 
				}
			}
		}
		if(packID == 2)
		{
			if(par1Random.nextInt(5) == 0)
			{
				return worldGeneratorForest;
			}
			if(par1Random.nextInt(3) == 0)
			{
				return new BWG4decoBigTree(8 + par1Random.nextInt(7), 0); 
			} 
			else
			{
				return worldGeneratorTrees;
			}
		}
		return worldGeneratorTrees;
    }
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		return new WorldGenShrub(3, 0);
    }	

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		super.decorate(par1World, par2Random, par3, par4);
    }
}
