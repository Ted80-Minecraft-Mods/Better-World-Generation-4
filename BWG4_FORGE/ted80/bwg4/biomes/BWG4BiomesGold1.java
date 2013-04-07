package ted80.bwg4.biomes;

import java.util.Random;
import java.awt.Color;

import ted80.bwg4.deco.BWG4decoGold1;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4BiomesGold1 extends BiomeGenBase
{
	private int packID;
	private int biomeID;

	private BWG4Decorator bwg4decorator;
	
    public BWG4BiomesGold1(int id, int pack, int biome)
    {
        super(id);
        theBiomeDecorator = new BWG4Decorator(this);
        bwg4decorator = (BWG4Decorator) theBiomeDecorator;
        
		packID = pack;
		biomeID = biome;
		bwg4decorator.usebwg4deco = true;
		bwg4decorator.emeralds = true;
		bwg4decorator.silverfish = true;
		bwg4decorator.melon = 40;
		bwg4decorator.pumpkin = 84; 
		waterColorMultiplier = 0x2A5091;
		
		if(packID == 1) //Taiga Hills
		{
		}
		if(packID == 2) //Ice Plains
		{
			bwg4decorator.mayrandtrees = true; 	
			
			if(biomeID == 1 || biomeID == 2 || biomeID == 3) //Snow Pine
			{
				topBlock = (byte)Block.blockSnow.blockID;
				fillerBlock = (byte)Block.blockSnow.blockID;		
				spawnableCreatureList.clear();
				bwg4decorator.tl1amount = -10; 
				bwg4decorator.grass = -10; 
				bwg4decorator.redflowers = -10;
				bwg4decorator.yellowflowers = -10; 
			}
			else
			{
				spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
				bwg4decorator.tl1amount = 1; 
				bwg4decorator.grass = 8; 
				bwg4decorator.redflowers = 1;
				bwg4decorator.yellowflowers = 1; 
			}
		}
		if(packID == 3) //Snow Pine
		{
			bwg4decorator.mayrandtrees = true; 	
			bwg4decorator.redflowers = 3;
			bwg4decorator.yellowflowers = 2; 
			bwg4decorator.grass = 8; 
			bwg4decorator.tl1amount = 5; 
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
			bwg4decorator.tl1maxy = 94;
			bwg4decorator.usetl2 = true;
			bwg4decorator.tl2miny = 85;
			bwg4decorator.tl2maxy = 105;
			bwg4decorator.tl2amount = 2;
		}
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if(packID == 1) //Taiga Hills
		{
			
		}
		if(packID == 2) //Ice Plains
		{
			
		}
		if(packID == 3) //Snow Pine
		{
			if(biomeID == 6)//SnowPine_Border
			{
				if (par1Random.nextInt(5) == 0) 
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
			else
			{
				if (par1Random.nextInt(5) == 0) 
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
		}
		return worldGeneratorTrees;
    }
	
    public WorldGenerator getRandomWorldGenForTrees2(Random par1Random)
    {
		if (par1Random.nextInt(5) == 0) 
		{ 
			return new BWG4decoGold1(3, 4, 9, 2); 
		}	
		else if (par1Random.nextInt(3) == 0) 
		{ 
			return new BWG4decoGold1(1, 4, 7, 0);
		}
		else if (par1Random.nextInt(2) == 0) 
		{ 
			return new BWG4decoGold1(2, 4, 7, 2); 
		}
		else 
		{ 
			return worldGeneratorTrees; 
		}
    }	

    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
		super.decorate(par1World, par2Random, par3, par4);
		
		for (int var12 = 0; var12 < 9; ++var12)
		{
			int snowX = par3 + par2Random.nextInt(16) + 8;
			int snowY = par4 + par2Random.nextInt(16) + 8;
			(new BWG4decoGold1(4)).generate(par1World, par2Random, snowX, 62, snowY);
		}
    }
	
    public int getBiomeGrassColor()
    {
		double d = MathHelper.clamp_float(getFloatTemperature(), 0.5F, 0.5F); 
		double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.5F, 0.5F);
		return ColorizerGrass.getGrassColor(d, d1);
    }

    public int getBiomeFoliageColor()
    {
		double d = MathHelper.clamp_float(getFloatTemperature(), 0.5F, 0.5F);
		double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.5F, 0.5F);
		return ColorizerFoliage.getFoliageColor(d, d1);
    }
}
