package ted80.bwg4.biomes;

import java.util.Random;
import java.awt.Color;

import ted80.bwg4.deco.BWG4decoBigTree;
import ted80.bwg4.deco.BWG4decoGold1;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesGold2 extends BiomeGenBase
{
	private int packID;
	private int biomeID;

	private BWG4Decorator bwg4decorator;
	
    public BWG4BiomesGold2(int id, int pack, int biome)
    {
        super(id);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		packID = pack;
		biomeID = biome;
		bwg4decorator.usebwg4deco = true;
		
		if(packID == 1) //PineForest
		{
			bwg4decorator.mayrandtrees = false;
			bwg4decorator.emeralds = true;
			bwg4decorator.redflowers = 3;
			bwg4decorator.yellowflowers = 2; 
			bwg4decorator.grass = 6; 
			bwg4decorator.brownmush = 1;
			bwg4decorator.redmush = 1; 
			bwg4decorator.tl1maxy = 99; 
		
			if(biomeID == 1)//Pine_Normal
			{
				bwg4decorator.tl1amount = 14;
			}
			if(biomeID == 2)//Pine_Hills
			{
				bwg4decorator.tl1amount = 14;
			}
			if(biomeID == 3)//Pine_Mix
			{
				bwg4decorator.tl1amount = 10;
			}
			if(biomeID == 4)//Pine_Valleys
			{
				bwg4decorator.tl1amount = 12;
				bwg4decorator.tl1miny = 70;
				bwg4decorator.grass = 8; 
				bwg4decorator.usetl2 = true;
				bwg4decorator.tl2maxy = 80; 
				bwg4decorator.tl2amount = 1;
			}
			if(biomeID == 5)//Pine_Border
			{
				bwg4decorator.tl1amount = 9;
			}
		}
		if(packID == 2)
		{
			bwg4decorator.grass = 3; 
			bwg4decorator.redflowers = 2;
			bwg4decorator.yellowflowers = 2; 
				
			if(biomeID == 1) //Forest_Normal
			{
				bwg4decorator.tl1amount = 5;
			}
			if(biomeID == 2) //Forest_High
			{
				bwg4decorator.tl1amount = 5;
			}
			if(biomeID == 3) //Forest_Lakes
			{
				bwg4decorator.tl1amount = 4;
			}
			if(biomeID == 4) //Forest_Plains
			{
				bwg4decorator.tl1amount = -20;
				bwg4decorator.redflowers = 4;
				bwg4decorator.yellowflowers = 4; 
				bwg4decorator.grass = 8; 
			}
			if(biomeID == 5) //Forest_Border
			{
				bwg4decorator.tl1amount = 4;
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
