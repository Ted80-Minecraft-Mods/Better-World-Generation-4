package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoCaves extends WorldGenerator
{
	public int id;
	
	public DecoCaves(int i)
	{
		id = i;
	}
	
	public boolean generate(World world, Random rand, int i, int j, int k) 
	{
		if(id == 1) //cactus
		{
			int ybase = j;
			for(int d = j; d > 2; d--)
			{
				if(!world.isAirBlock(i, d, k))
				{
					ybase = d;
					break;
				}
			}
			
	        for (int l = 0; l < 10; ++l)
	        {
	            int i1 = i + rand.nextInt(10) - rand.nextInt(10);
	            int j1 = ybase + rand.nextInt(3) - rand.nextInt(3);
	            int k1 = k + rand.nextInt(10) - rand.nextInt(10);

	            if (world.isAirBlock(i1, j1, k1))
	            {
	                int l1 = 2 + rand.nextInt(4);

	                for (int i2 = 0; i2 < l1; ++i2)
	                {
	                    if (Blocks.cactus.canBlockStay(world, i1, j1 + i2, k1))
	                    {
	                    	world.setBlock(i1, j1 + i2, k1, Blocks.cactus, 0, 2);
	                    }
	                }
	            }
	        }
		}
		if(id == 2 || id == 3)
		{        
			if (world.getBlock(i, j + 1, k) != Blocks.stone)
	        {
	            return false;
	        }
	        else if (world.getBlock(i, j - 1, k) != Blocks.air)
	        {
	            return false;
	        }
	        else
	        {
	            int l = 0;
	
	            if (world.getBlock(i - 1, j, k) == Blocks.stone)
	            {
	                ++l;
	            }
	
	            if (world.getBlock(i + 1, j, k) == Blocks.stone)
	            {
	                ++l;
	            }
	
	            if (world.getBlock(i, j, k - 1) == Blocks.stone)
	            {
	                ++l;
	            }
	
	            if (world.getBlock(i, j, k + 1) == Blocks.stone)
	            {
	                ++l;
	            }
	
	            if (l > 2)
	            {
	            	Block liquidBlock = Blocks.water;
	            	if(id == 3)
	            	{
	            		liquidBlock = Blocks.lava;
	            	}
	            	
	                world.setBlock(i, j, k, liquidBlock, 0, 2);
	                world.scheduledUpdatesAreImmediate = true;
	                liquidBlock.updateTick(world, i, j, k, rand);
	                world.scheduledUpdatesAreImmediate = false;
	            }
	        }
		}
		return true;
	}
}
