package bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoJungleTree extends WorldGenerator
{
    public DecoJungleTree()
    {
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block bblock = null;
    	y += 20;
    	if(y > 255) { y = 255;}
    	while(y > 0)
    	{
    		bblock = world.getBlock(x, y, z);
    		if(!world.isAirBlock(x, y, z) && !bblock.isLeaves(world, x, y, z) && bblock != Blocks.water)
    		{
    			break;
    		}
    		y--;
    	}

    	if(bblock == Blocks.grass || bblock == Blocks.dirt)
    	{
    		int s;
    		for(s = -2; s < 30; s++)
    		{
    			world.setBlock(x, y + s, z, Blocks.log, 3, 0);
    			world.setBlock(x + 1, y + s, z, Blocks.log, 3, 0);
    			world.setBlock(x, y + s, z + 1, Blocks.log, 3, 0);
    			world.setBlock(x + 1, y + s, z + 1, Blocks.log, 3, 0);
    		}
    		
    		for(int c1 = -6; c1 <= 7; c1++)
    		{
    			for(int c2 = -6; c2 <= 7; c2++)
    			{
    				world.setBlock(x + c1, y + s - 1, z + c2, Blocks.log, 3, 0);
    				world.setBlock(x + c1, y + s, z + c2, Blocks.leaves, 3, 0);
    			}
    		}
    	}
    	
    	return true;
    }
}
