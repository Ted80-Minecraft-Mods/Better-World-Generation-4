package bwg4.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import bwg4.config.BWG4Config;
import bwg4.deco.old.*;

public class BWG4BiomesBeta extends BiomeGenBase
{
	private int id;

    public BWG4BiomesBeta(int par1, int i)
    {
        super(par1);
		id = i;

		if(id == 0 || id == 0)
		{
			spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		}
	
		if(id == 1 || id == 2 || id == 3 || id == 6)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		}
		
		if(id == 1 || id == 2 || id == 3 || id == 6)
		{
			spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		}
		
		if(id == 7)
		{
			spawnableCreatureList.clear();
			topBlock = (byte)Block.sand.blockID;
			fillerBlock = (byte)Block.sand.blockID;
		}	
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if (id == 0)
		{
			if(par1Random.nextInt(3) == 0)
			{
				return new BWG4oldGenBigTree(2);
			} else
			{
				return new BWG4oldGenTrees(2);
			}
		}
		else if (id == 3) 
		{
			if(par1Random.nextInt(5) == 0)
			{
				return worldGeneratorForest;
			}
			if(par1Random.nextInt(3) == 0)
			{
				return new BWG4oldGenBigTree(2);
			} else
			{
				return new BWG4oldGenTrees(2);
			}
		}
		else if (id == 6)
		{
			if (par1Random.nextInt(3) == 0)
			{
				return new WorldGenTaiga1();
			}
			else
			{
				return new WorldGenTaiga2(false);
			}
		}	
		else if (id == 10)
		{
			if (par1Random.nextInt(10) == 0)
			{
				return new BWG4oldGenBigTree(2); 
			}
			if (par1Random.nextInt(3) == 0)
			{
				return new WorldGenShrub(3, 0);
			}
			if (par1Random.nextInt(2) == 0)
			{
				if (par1Random.nextInt(40) == 0)
				{
					return new WorldGenHugeTrees(false, 60 + par1Random.nextInt(5), 3, 3);
				}
				else
				{
					return new WorldGenHugeTrees(false, 20 + par1Random.nextInt(15), 3, 3);
				}	
			}
			else
			{
				return new WorldGenTrees(false, 4 + par1Random.nextInt(7), 3, 3, true);
			}
		}	
		else
		{
			if (par1Random.nextInt(10) == 0)
			{
				return new BWG4oldGenBigTree(2);
			}
			else
			{
				return new BWG4oldGenTrees(2);
			}
		}	
    }
	
    public int getBiomeGrassColor()
    {
		if( id == 6 || id == 9 )
		{
			return ColorizerGrass.getGrassColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
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
		if( id == 6 || id == 9 )
		{
			return ColorizerFoliage.getFoliageColor(0.6F, 0.6F);
		}
		else if( id == 7 )
		{
			return ColorizerFoliage.getFoliageColor(0.8F, 0.2F);
		}
		else
		{
			double d = MathHelper.clamp_float(getFloatTemperature(), 0.0F, 1.0F);
			double d1 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
			return ColorizerFoliage.getFoliageColor(d, d1);
		}
    }
}
