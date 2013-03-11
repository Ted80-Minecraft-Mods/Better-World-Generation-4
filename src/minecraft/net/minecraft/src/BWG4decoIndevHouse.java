package net.minecraft.src;

import java.util.Random;

public class BWG4decoIndevHouse extends WorldGenerator
{
	private int typeID;

    public BWG4decoIndevHouse(int type)
    {
		typeID = type;
    }

    public boolean generate(World par1World, Random par2Random, int x, int y, int z)
    {
		//STONE FLOOR
		for(int flx = -3 + x; flx < 4 + x; flx++)
		{
			for(int flz = -3 + z; flz < 4 + z; flz++)
			{
				setBlock(par1World, flx, y - 1, flz, Block.stone.blockID);
			}
		}
		for(int flx = -3 + x; flx < 4 + x; flx++)
		{
			for(int flz = -3 + z; flz < 4 + z; flz++)
			{
				setBlock(par1World, flx, y - 2, flz, Block.stone.blockID);
			}
		}
		for(int flx = -3 + x; flx < 4 + x; flx++)
		{
			for(int flz = -3 + z; flz < 4 + z; flz++)
			{
				setBlock(par1World, flx, y - 3, flz, Block.stone.blockID);
			}
		}
		
		//WOODEN FRAME
		if(typeID == 2)
		{
			for(int frx = -3 + x; frx < 4 + x; frx++)
			{
				for(int fry = 0 + y; fry < 4 + y; fry++)
				{
					for(int frz = -3 + z; frz < 4 + z; frz++)
					{
						setBlock(par1World, frx, fry, frz, Block.cobblestoneMossy.blockID);
					}
				}	
			}
		}
		else
		{
			for(int frx = -3 + x; frx < 4 + x; frx++)
			{
				for(int fry = 0 + y; fry < 4 + y; fry++)
				{
					for(int frz = -3 + z; frz < 4 + z; frz++)
					{
						setBlock(par1World, frx, fry, frz, Block.planks.blockID);
					}
				}	
			}
		}
		
		//GENERATE AIR
		for(int airx = -2 + x; airx < 3 + x; airx++)
		{
			for(int airy = 0 + y; airy < 3 + y; airy++)
			{
				for(int airz = -2 + z; airz < 3 + z; airz++)
				{
					setBlock(par1World, airx, airy, airz, 0);
				}
			}	
		}
		
		//CREATE DOOR AND TORCHES
		if(par1World.isAirBlock(x - 4, y + 1, z) && !par1World.isAirBlock(x - 4, y - 1, z) || !par1World.isAirBlock(x - 4, y - 2, z))
		{
			setBlock(par1World, x - 3, y, z, 0);
			setBlock(par1World, x - 3, y + 1, z, 0);
			setBlock(par1World, x - 4, y, z, 0);
			setBlock(par1World, x - 4, y + 1, z, 0);
			par1World.setBlockWithNotify(x, y + 1, z + 2, Block.torchWood.blockID);
			par1World.setBlockWithNotify(x, y + 1, z - 2, Block.torchWood.blockID);
		}
		else if(par1World.isAirBlock(x + 4, y + 1, z) && !par1World.isAirBlock(x + 4, y - 1, z) || !par1World.isAirBlock(x + 4, y - 2, z))
		{
			setBlock(par1World, x + 3, y, z, 0);
			setBlock(par1World, x + 3, y + 1, z, 0);
			setBlock(par1World, x + 4, y, z, 0);
			setBlock(par1World, x + 4, y + 1, z, 0);
			par1World.setBlockWithNotify(x, y + 1, z + 2, Block.torchWood.blockID);
			par1World.setBlockWithNotify(x, y + 1, z - 2, Block.torchWood.blockID);
		}
		else if(par1World.isAirBlock(x, y + 1, z - 4) && !par1World.isAirBlock(x, y - 1, z - 4) || !par1World.isAirBlock(x, y - 2, z - 4))
		{
			setBlock(par1World, x, y, z - 3, 0);
			setBlock(par1World, x, y + 1, z - 3, 0);
			setBlock(par1World, x, y, z - 4, 0);
			setBlock(par1World, x, y + 1, z - 4, 0);
			par1World.setBlockWithNotify(x + 2, y + 1, z, Block.torchWood.blockID);
			par1World.setBlockWithNotify(x - 2, y + 1, z, Block.torchWood.blockID);
		}
		else if(par1World.isAirBlock(x, y + 1, z + 4) && !par1World.isAirBlock(x, y - 1, z + 4) || !par1World.isAirBlock(x, y - 2, z + 4))
		{
			setBlock(par1World, x, y, z + 3, 0);
			setBlock(par1World, x, y + 1, z + 3, 0);
			setBlock(par1World, x, y, z + 4, 0);
			setBlock(par1World, x, y + 1, z + 4, 0);
			par1World.setBlockWithNotify(x + 2, y + 1, z, Block.torchWood.blockID);
			par1World.setBlockWithNotify(x - 2, y + 1, z, Block.torchWood.blockID);
		}
		else 
		{
			setBlock(par1World, x - 3, y, z, 0);
			setBlock(par1World, x - 3, y + 1, z, 0);
			setBlock(par1World, x - 4, y, z, 0);
			setBlock(par1World, x - 4, y + 1, z, 0);
			par1World.setBlockWithNotify(x, y + 1, z + 2, Block.torchWood.blockID);
			par1World.setBlockWithNotify(x, y + 1, z - 2, Block.torchWood.blockID);
		}
		return true;
    }
}
