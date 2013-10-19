package bwg4.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class BWG4ComponentPyramid extends BWG4ComponentScattered
{	
	private int height = -10;
	private int center = 50;
	
    public BWG4ComponentPyramid(Random rand, int par2, int par3)
    {
        super(rand, par2, 80, par3, 100, 100, 100);
    }
	
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
    {
        if (!this.func_74935_a(world, box, 0))
        {
            return false;
        }
        else
        {
        	//GENERATE STRUCTURE
        	int size = center;
        	for(int y = 0 + height; y < center + height; y++)
        	{
        		for(int x = center - size; x < center + size; x++)
        		{
        			for(int z = center - size; z < center + size; z++)
        			{
        				if(y == 0 + height)
        				{
        					fillCurrentPositionBlocksDownwards(world, Block.sandStone.blockID, 0, x, y, z, box);
        				}
        				else
        				{
        					placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 0, x, y, z, box);
        				}
        			}
        		}
        		size--;
        	}
        	 
        	//GENERATE STAIRS 
        	int stair = 24;
        	int d = this.getMetadataWithOffset(Block.stairsSandStone.blockID, 0);
        	while(stair > -1)
        	{
        		if(getBlockIdAtCurrentPosition(world, stair - 1, stair + height, center, box) == 0)
        		{
        			placeBlockAtCurrentPosition(world, Block.stairsSandStone.blockID, d, stair, stair + height, center, box);
        			placeBlockAtCurrentPosition(world, Block.stairsSandStone.blockID, d, stair, stair + height, center - 1, box);
        		}
        		else
        		{
        			break;
        		}
        		
        		stair--;
        	}
        	
        	//GENERATE ROOMS
        	int r;
        	for(r = 0; r < 3; r++)
        	{
        		generateRooms(world, box, rand, r);
        	}
        	
        	//GENERATE ENTRANCE
        	fillWithMetadataBlocks(world, box, 25, 25 - 1 + height, center - 2, center - 11, 26 + 1 + height, center + 1, Block.sandStone.blockID, 2, 0, 0, true);
        	fillWithAir(world, box, 25, 25 + height, center - 1, center - 11, 26 + height, center);
        	
        	//GENERATE DOWNSTAIRS
        	for(int ds = 0; ds < r + 1; ds++)
        	{
        		
        	}
        	
        	return true;
        }
    }
    
    public void generateRooms(World world, StructureBoundingBox box, Random rand, int layer)
    {
    	if(layer < 3)
    	{
    		//box
	    	int h = 24 - (layer * 5) + height;
	    	int center = 50;
	    	for(int y = h; y < h + 7; y++)
	    	{
	    		for(int x = -12 + center; x < 12 + center; x++)
	    		{
	    			for(int z = -12 + center; z < 12 + center; z++)
	    			{
	    				if((y == h + 5 && layer == 0) || x == -12 + center || x == 11 + center || z == -12 + center || z == 11 + center)
	    				{
	    					placeBlockAtCurrentPosition(world, Block.obsidian.blockID, 0, x, y, z, box);
	    				}
	    				else if (y == h || y == h + 4 || x == -11 + center || x == 10 + center || z == -11 + center || z == 10 + center)
	    				{
	    					if(y == h + 2)
	    					{
	    						placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 1, x, y, z, box);
	    					}
	    					else
	    					{
	    						placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y, z, box);
	    					}
	    				}
	    				else if(y < h + 4)
	    				{
	    					placeBlockAtCurrentPosition(world, 0, 0, x, y, z, box);
	    				}
	    			}
	    		}
	    	}
	    	
	    	//pillars
	    	generatePillar(world, box, rand, center - 6, h + 1, center - 6);
	    	generatePillar(world, box, rand, center + 5, h + 1, center + 5);
	    	generatePillar(world, box, rand, center - 6, h + 1, center + 5);
	    	generatePillar(world, box, rand, center + 5, h + 1, center - 6);
	    	
	    	//deco
    	}
    }
    
    public void generatePillar(World world, StructureBoundingBox box, Random rand, int x, int y, int z)
    {
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y, z, box);
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 1, x, y + 1, z, box);
    	placeBlockAtCurrentPosition(world, Block.sandStone.blockID, 2, x, y + 2, z, box);
    }
}
