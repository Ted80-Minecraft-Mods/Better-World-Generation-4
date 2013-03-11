package net.minecraft.src;

import java.util.Random;

public class BWG4decoDefault extends WorldGenerator
{
	private int objectID;

    public BWG4decoDefault(int object)
    {
		objectID = object;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		if(objectID == 1)//BEACH DUNES GRASS
		{
			int var11;
		
			for (boolean var6 = false; ((var11 = par1World.getBlockId(x, y, z)) == 0 || var11 == Block.leaves.blockID) && y > 0; --y)
			{
				;
			}

			for (int var7 = 0; var7 < 128; ++var7)
			{
				int var8 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
				int var9 = y + par2Random.nextInt(4) - par2Random.nextInt(4);
				int var10 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

				int block = par1World.getBlockId(var8, var9 - 1, var10); 
				if (par1World.isAirBlock(var8, var9, var10) && block == Block.sand.blockID )
				{
					if(par2Random.nextInt(8) != 0)
					{
						par1World.setBlockAndMetadata(var8, var9, var10, Block.tallGrass.blockID, 1);
					}
					else
					{
						par1World.setBlockAndMetadata(var8, var9, var10, Block.leaves.blockID, 4);
					}
					par1World.setBlock(var8, var9 - 1, var10, Block.grass.blockID);
				}
			}

			return true;
		}
		else
		{
		}
		return true;
	}
}
