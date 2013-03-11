package net.minecraft.src;

import java.util.Random;

public class BWG4BiomesBeta extends BiomeGenBase
{
	private int biomeid;

    public BWG4BiomesBeta(int par1)
    {
        super(par1);
		biomeid = par1;
		
		if(par1 == 80 || par1 == 90)
		{
			spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityOcelot.class, 2, 1, 1));
		}
		
		if(par1 == 81 || par1 == 82 || par1 == 83 || par1 == 86)
		{
			spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 8, 4, 4));
		}
		
		if(par1 == 87)
		{
			spawnableCreatureList.clear();
			topBlock = (byte)Block.sand.blockID;
			fillerBlock = (byte)Block.sand.blockID;
		}	
    }
	
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		if (biomeid == 80)
		{
			if(par1Random.nextInt(3) == 0)
			{
				return new BWG4oldGenBigTree(2);
			} else
			{
				return new BWG4oldGenTrees(2);
			}
		}
		else if (biomeid == 83) 
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
		else if (biomeid == 86)
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
		else if (biomeid == 90)
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
}
