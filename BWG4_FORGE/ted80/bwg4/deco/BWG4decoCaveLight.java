package ted80.bwg4.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BWG4decoCaveLight extends WorldGenerator
{
    private int block;
    private int water;

    public BWG4decoCaveLight(int b, int w)
    {
        block = b;
        water = w;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
        if (water == 0 && par1World.getBlockId(x, y + 1, z) != Block.stone.blockID && par1World.getBlockId(x, y + 1, z) != Block.dirt.blockID)
        {
            return false;
        }
        else if (par1World.getBlockId(x, y, z) != Block.stone.blockID && par1World.getBlockId(x, y, z) != Block.dirt.blockID)
        {
            return false;
        }
        else
        {
        	int solidcount = 0;
        	int unsolidcount = 0;
        	
        	//check solid
            if (par1World.getBlockId(x - 1, y, z) == Block.stone.blockID || par1World.getBlockId(x - 1, y, z) == Block.dirt.blockID)
            {
                ++solidcount;
            }
            if (par1World.getBlockId(x, y, z + 1) == Block.stone.blockID || par1World.getBlockId(x, y, z + 1) == Block.dirt.blockID)
            {
                ++solidcount;
            }
            if (par1World.getBlockId(x + 1, y, z) == Block.stone.blockID || par1World.getBlockId(x + 1, y, z) == Block.dirt.blockID)
            {
                ++solidcount;
            }
            if (par1World.getBlockId(x, y, z - 1) == Block.stone.blockID || par1World.getBlockId(x, y, z - 1) == Block.dirt.blockID)
            {
                ++solidcount;
            }
            
            //check unsolid
            if (par1World.getBlockId(x - 1, y, z) == water)
            {
                ++unsolidcount;
            }
            if (par1World.getBlockId(x + 1, y, z) == water)
            {
                ++unsolidcount;
            }
            if (par1World.getBlockId(x, y, z - 1) == water)
            {
                ++unsolidcount;
            }
            if (par1World.getBlockId(x, y, z + 1) == water)
            {
                ++unsolidcount;
            }

            if (solidcount > 0 && solidcount < 4 && unsolidcount > 0 && unsolidcount < 4)
            {
                par1World.setBlock(x, y, z, block, 0, 2);
                par1World.scheduledUpdatesAreImmediate = true;
                Block.blocksList[block].updateTick(par1World, x, y, z, par2Random);
                par1World.scheduledUpdatesAreImmediate = false;
            }
            
        	return true;
        }
    	
    	/*
        if (par1World.getBlockId(par3, par4 + 1, par5) != Block.stone.blockID && par1World.getBlockId(par3, par4 + 1, par5) != Block.dirt.blockID)
        {
            return false;
        }
        else if (par1World.getBlockId(par3, par4 - 1, par5) != Block.stone.blockID && par1World.getBlockId(par3, par4 - 1, par5) != Block.dirt.blockID)
        {
            return false;
        }
        else if (!water && par1World.getBlockId(par3, par4, par5) != 0 && par1World.getBlockId(par3, par4, par5) != Block.stone.blockID)
        {
            return false;
        }
        else if (water && par1World.getBlockId(par3, par4, par5) != Block.waterStill.blockID && par1World.getBlockId(par3, par4, par5) != Block.stone.blockID)
        {
            return false;
        }
        else
        {
            int var6 = 0;

            if (par1World.getBlockId(par3 - 1, par4, par5) == Block.stone.blockID || par1World.getBlockId(par3 - 1, par4, par5) == Block.dirt.blockID)
            {
                ++var6;
            }

            if (par1World.getBlockId(par3 + 1, par4, par5) == Block.stone.blockID || par1World.getBlockId(par3 + 1, par4, par5) == Block.dirt.blockID)
            {
                ++var6;
            }

            if (par1World.getBlockId(par3, par4, par5 - 1) == Block.stone.blockID ||par1World.getBlockId(par3, par4, par5 - 1) == Block.dirt.blockID)
            {
                ++var6;
            }

            if (par1World.getBlockId(par3, par4, par5 + 1) == Block.stone.blockID || par1World.getBlockId(par3, par4, par5 + 1) == Block.dirt.blockID)
            {
                ++var6;
            }

            int var7 = 0;
            if(water)
            {
                if (par1World.getBlockId(par3 - 1, par4, par5) == Block.waterStill.blockID)
                {
                    ++var7;
                }

                if (par1World.getBlockId(par3 + 1, par4, par5) == Block.waterStill.blockID)
                {
                    ++var7;
                }

                if (par1World.getBlockId(par3, par4, par5 - 1) == Block.waterStill.blockID)
                {
                    ++var7;
                }

                if (par1World.getBlockId(par3, par4, par5 + 1) == Block.waterStill.blockID)
                {
                    ++var7;
                }
            }
            else
            {
	            if (par1World.isAirBlock(par3 - 1, par4, par5))
	            {
	                ++var7;
	            }
	
	            if (par1World.isAirBlock(par3 + 1, par4, par5))
	            {
	                ++var7;
	            }
	
	            if (par1World.isAirBlock(par3, par4, par5 - 1))
	            {
	                ++var7;
	            }
	
	            if (par1World.isAirBlock(par3, par4, par5 + 1))
	            {
	                ++var7;
	            }
            }

            if (var6 == 3 && var7 == 1)
            {
                par1World.setBlock(par3, par4, par5, this.liquidBlockId, 0, 2);
                par1World.scheduledUpdatesAreImmediate = true;
                Block.blocksList[this.liquidBlockId].updateTick(par1World, par3, par4, par5, par2Random);
                par1World.scheduledUpdatesAreImmediate = false;
            }

            return true;
        }
        */
    }
}
