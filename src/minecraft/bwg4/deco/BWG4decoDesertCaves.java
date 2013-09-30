package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoDesertCaves extends WorldGenerator
{
	public int id;
	
	public BWG4decoDesertCaves(int i)
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
		return true;
	}
}
