package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoCaves extends WorldGenerator
{
	public int id;
	
	public BWG4decoCaves(int i)
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
	                    if (Block.cactus.canBlockStay(world, i1, j1 + i2, k1))
	                    {
	                    	world.setBlock(i1, j1 + i2, k1, Block.cactus.blockID, 0, 2);
	                    }
	                }
	            }
	        }
		}
		if(id == 2 || id == 3)
		{        
			if (world.getBlockId(i, j + 1, k) != Block.stone.blockID)
	        {
	            return false;
	        }
	        else if (world.getBlockId(i, j - 1, k) != 0)
	        {
	            return false;
	        }
	        else
	        {
	            int l = 0;
	
	            if (world.getBlockId(i - 1, j, k) == Block.stone.blockID)
	            {
	                ++l;
	            }
	
	            if (world.getBlockId(i + 1, j, k) == Block.stone.blockID)
	            {
	                ++l;
	            }
	
	            if (world.getBlockId(i, j, k - 1) == Block.stone.blockID)
	            {
	                ++l;
	            }
	
	            if (world.getBlockId(i, j, k + 1) == Block.stone.blockID)
	            {
	                ++l;
	            }
	
	            if (l > 2)
	            {
	            	int liquidBlockId = Block.waterMoving.blockID;
	            	if(id == 3)
	            	{
	            		liquidBlockId = Block.lavaMoving.blockID;
	            	}
	            	
	                world.setBlock(i, j, k, liquidBlockId, 0, 2);
	                world.scheduledUpdatesAreImmediate = true;
	                Block.blocksList[liquidBlockId].updateTick(world, i, j, k, rand);
	                world.scheduledUpdatesAreImmediate = false;
	            }
	        }
		}
		return true;
	}
}
