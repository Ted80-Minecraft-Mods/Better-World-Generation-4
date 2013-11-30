package bwg4.structure.city;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class BWG4CityComponentOffice1 extends BWG4CityComponent
{
    public BWG4CityComponentOffice1(Random rand, int par2, int par3)
    {
        super(rand, par2, 39, par3, 64, 40, 64);
    }
	
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
    	for(int f = 0; f < 10; f++)
    	{
			for(int x = 4; x < 60; x++)
			{
				for(int z = 4; z < 60; z++)
				{
					for(int y = 0; y < 6; y++)
					{
						if(y == 0 || y == 5)
						{
							placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, x, y + (f * 6), z, box);
						}
						else if(x == 4 || x == 59 || z == 4 || z == 59)
						{
							if((x == 4 && z == 4) || (x == 4 && z == 59) || (x == 59 && z == 4) || (x == 59 && z == 59))
							{
								placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, x, y + (f * 6), z, box);
							}
							else
							{
								if(y == 1)
								{
									placeBlockAtCurrentPosition(world, Block.stoneBrick.blockID, 0, x, y + (f * 6), z, box);
								}
								else
								{
									placeBlockAtCurrentPosition(world, Block.glass.blockID, 0, x, y + (f * 6), z, box);
								}
							}
						}
					}
				}
			}
    	}
        
        return true;
    }
}
